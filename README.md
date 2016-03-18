# List-Files
See https://www.eclipse.org/forums/index.php/t/1075739/

Demonstrates how circular links within the filesystem cause commit to hang using egit Eclipse plugin, which uses java.io.File listFiles 
method.

```
bash$ pwd
/c/Users/dwyttenb
bash$ find . -type l -ls | head -1
281474976832258      0 lrwxrwxrwx   1  dwyttenb 1049089        31 Jul 31  2014 ./AppData/Local/Application\ Data -> /c/Users/dwyttenb/AppData/Local
```


-------------------------------------------------------
 T E S T S
-------------------------------------------------------
```
Running org.wyttenbach.dale.test.ListFileTest
C:\Users\dwyttenb, count=1, level=1
C:\Users\dwyttenb\AppData\Local\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Google\Chrome\User Data\Default\IndexedDB\https_svn.decaresystems.ie_0.indexeddb.leveldb, count=2664, level=40
C:\Users\dwyttenb\AppData\Local\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\NetBeans\Cache\8.0.2\index\s1006\java\14\classes, count=3705, level=41
C:\Users\dwyttenb\AppData\Local\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\NetBeans\Cache\8.0.2\index\s130\java\14\refs, count=5417, level=42
C:\Users\dwyttenb\AppData\Local\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\Application Data\NetBeans\Cache\8.0.2\index\s18, count=7136, level=39
^C
```
