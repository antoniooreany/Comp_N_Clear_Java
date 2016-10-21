import java.io.*;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gorshkov on 21.10.2016.
 */
public class Comp_N_Clear_Java {
    public static void main(String[] args) throws IOException {
        String srcPath = "D:\\cons_1\\receive\\LAST_REC.TXT";
        String dstPath = "D:\\cons_1\\base";
        String uVedaPath = "U:\\Veda\\VEDA3000\\CONS_1\\SYSTEM";

        HashSet<String> holdNamesArray = ReadRawLinesAndWriteToArray(srcPath);

        RemoveUnnecessaryDirectories(holdNamesArray, dstPath);

        LaunchKPlus(uVedaPath, dstPath);
    }

    private static HashSet<String> ReadRawLinesAndWriteToArray(String srcPath) throws IOException {
        String rawLine;
        String line;
        HashSet<String> lines = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(srcPath))) {
            while ((rawLine = reader.readLine()) != null) {
                line = parseLines(rawLine);
                lines.add(line);
            }
        }
        return lines;
    }

    private static String parseLines(String rawLine) {
        Pattern p = Pattern.compile("([A-Z]+),.*$");
        Matcher m = p.matcher(rawLine);
        boolean matches = m.matches();
        String line = m.group(1);
        return line;
    }

    private static void RemoveUnnecessaryDirectories(HashSet<String> holdNamesSet, String dstPath) {

        HashSet<String> dstSet = getDstDirsSet(dstPath);
        HashSet<String> elemsToDelete = (HashSet<String>) dstSet.clone();

        for (String dstSetElem :
                dstSet) {
            for (String holdNamesSetElem :
                    holdNamesSet) {
                if (dstSetElem.equals(holdNamesSetElem)) {
                    elemsToDelete.remove(holdNamesSetElem);
                }
            }
        }
        for (String elemToDelete :
                elemsToDelete) {
            File dir = new File(dstPath + "//" + elemToDelete);
            deleteDirectory(dir);
        }


    }

    private static HashSet<String> getDirsToRemove(HashSet<String> holdNamesSet, HashSet<String> dstSet) {
        HashSet<String> dirsToRemove = new HashSet<String>();
        for (String holdNamesSetElem :
                holdNamesSet) {
            dstSet.remove(holdNamesSetElem);
        }


        dstSet.removeAll(holdNamesSet);

        return dirsToRemove;
    }

    private static HashSet<String> getDstDirsSet(String dstPath) {
        File dstFile = new File(dstPath);
        String[] dstDirs = dstFile.list();
        HashSet<String> dstSet = new HashSet();
        for (String dstDir : dstDirs) {
            dstSet.add(dstDir);
        }
        return dstSet;
    }


    /**
     * Deletes directory with subdirs and subfolders
     *
     * @param dir Directory to delete
     * @author Cloud
     */
    public static void deleteDirectory(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                File f = new File(dir, children[i]);
                deleteDirectory(f);
            }
            dir.delete();
        } else dir.delete();
    }


    private static void LaunchKPlus(String uVedaPath, String dstPath) throws IOException {
        // TODO: 21.10.2016 Realize
        if (!copyDir(uVedaPath, dstPath)) {
            throw new FileNotFoundException("Files has not copied from " + uVedaPath + " to " + dstPath);
        }
        deleteFile(dstPath + "cd.zip");
        deleteFile(dstPath + "cef2272.zip");

        ProcessBuilder pb = new ProcessBuilder("D:\\cons_1\\cons.exe");
        // TODO: 22.10.2016 Maybe I must use not "cons.exe" but "cons.lnk"
        pb.start();
    }

    private static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.delete()) {
            return true;
        } else {
            return false;
        }
    }


    private static final int BUFFER_SIZE = 1024;

    private static boolean copyDir(final String src, final String dst) {
        System.out.println("Копируем каталог: " + src);
        final File srcFile = new File(src);
        final File dstFile = new File(dst);
        if (srcFile.exists() && srcFile.isDirectory() && !dstFile.exists()) {
            dstFile.mkdir();
            File nextSrcFile;
            String nextSrcFilename, nextDstFilename;
            for (String filename : srcFile.list()) {
                nextSrcFilename = srcFile.getAbsolutePath()
                        + File.separator + filename;
                nextDstFilename = dstFile.getAbsolutePath()
                        + File.separator + filename;
                nextSrcFile = new File(nextSrcFilename);
                if (nextSrcFile.isDirectory()) {
                    copyDir(nextSrcFilename, nextDstFilename);
                } else {
                    copyFile(nextSrcFilename, nextDstFilename);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private static boolean copyFile(final String src, final String dst) {
        System.out.println("Копируем файл: " + src);
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
