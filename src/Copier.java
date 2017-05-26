import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by gorshkov on 24.10.2016.
 */
public class Copier {
    // 25.10.2016 Added ArrayOfNamesExceptions which should not be copied.
    static boolean copyDir(final String src, final String dst, String exceptFilename0, String exceptFilename1) {
        System.out.println("Copying directory: " + src);
        final File srcFile = new File(src);
        final File dstFile = new File(dst);
        if (srcFile.exists() && srcFile.isDirectory() && !dstFile.exists()) {
            dstFile.mkdir();
            File nextSrcFile;
            String nextSrcFilename, nextDstFilename;
            for (String filename : srcFile.list()) {
                if (!(filename.equals(exceptFilename0)) && !(filename.equals(exceptFilename1))) {
                    nextSrcFilename = srcFile.getAbsolutePath()
                            + File.separator + filename;
                    nextDstFilename = dstFile.getAbsolutePath()
                            + File.separator + filename;
                    nextSrcFile = new File(nextSrcFilename);
                    if (nextSrcFile.isDirectory()) {
                        copyDir(nextSrcFilename, nextDstFilename, exceptFilename0, exceptFilename1);
                    } else {
                        copyFile(nextSrcFilename, nextDstFilename);
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    static boolean copyFile(final String src, final String dst) {
        final int BUFFER_SIZE = 1024;
        System.out.println("Copying file: " + src);
        final File srcFile = new File(src);
        final File dstFile = new File(dst);
        if (srcFile.exists() && srcFile.isFile() && !dstFile.exists()) {
            try (InputStream in = new FileInputStream(srcFile);
                 OutputStream out = new FileOutputStream(dstFile)) {
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytes;
                while ((bytes = in.read(buffer)) > 0) {
                    out.write(buffer, 0, bytes);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Comp_N_Clear_Java.class.getName())
                        .log(Level.SEVERE, null, ex);
                return false;
            } catch (IOException ex) {
                Logger.getLogger(Comp_N_Clear_Java.class.getName())
                        .log(Level.SEVERE, null, ex);
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
}
