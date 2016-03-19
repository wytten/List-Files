package org.wyttenbach.dale.test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;


/**
 * https://www.eclipse.org/forums/index.php/t/1075739/
 */
public class ListFileTest {

  private int count = 0;

  private int level = 0;
  
  private String currentPath = null;

  private void walk(File dir) {
    count++;
    level++;
    currentPath = dir.getAbsolutePath();
    
    // Note that listFiles may return null depending on file system permissions
    File[] dirContents = dir.listFiles();
    if (dirContents != null) {
			for (File file : dirContents) {
				if (file.isDirectory()) {
					// The fix is to not traverse symbolic links, which works on OS X
					// however it is not working on Win 7 with java version "1.7.0_67"
					// where isSymbolicLink incorrectly returns false
					Path path = Paths.get(file.getPath());
					if (!Files.isSymbolicLink(path)) {
						walk(file);
					}
				}
			} 
		}
		level--;
  }

  @After
  public void tearDown() {
    System.out.println(count);
  }
  
  @Test
  public void testListFiles() throws Exception {
  	String homePath = System.getenv("HOME"); // OS X
  	if (homePath == null) {
  		homePath = System.getenv("USERPROFILE"); // Win 7
  	}
  	Assert.assertTrue(homePath != null);
    File homeDir = new File(homePath);
    Assert.assertTrue(homeDir.isDirectory());
    Thread thread = new Thread(new Runnable() {

      @Override
      public void run() {
        boolean interrupted = false;
        while (!interrupted) {
          System.out.printf("%s, count=%d, level=%d\n", currentPath, count, level);
          try {
            Thread.sleep(5000L);
          } catch (InterruptedException e) {
            interrupted = true;
          }
        }
      }
    });

    thread.start();
    walk(homeDir);
  }
}
