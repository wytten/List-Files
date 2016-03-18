package org.wyttenbach.dale.test;

import java.io.File;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;


/**
 * https://www.eclipse.org/forums/index.php/t/1075739/
 */
public class ListFileTest {

  private int count = 0;

  private int level = 0;
  
  private String lastDir = null;

  private void walk(File dir) {
    count++;
    level++;
    lastDir = dir.getAbsolutePath();
    File[] dirContents = dir.listFiles();
    for (File file : dirContents) {
      if (file.isDirectory()) {
        walk(file);
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
    File home = new File(System.getenv("TEMP"));
    Assert.assertTrue(home.isDirectory());
    Thread thread = new Thread(new Runnable() {

      @Override
      public void run() {
        boolean interrupted = false;
        while (!interrupted) {
          System.out.printf("%s, count=%d, level=%d\n", lastDir, count, level);
          try {
            Thread.sleep(5000L);
          } catch (InterruptedException e) {
            interrupted = true;
          }
        }
      }
    });

    thread.start();
    walk(home);
  }
}
