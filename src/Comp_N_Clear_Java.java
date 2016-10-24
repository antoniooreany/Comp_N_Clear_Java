import java.io.*;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gorshkov on 21.10.2016.
 */
public class Comp_N_Clear_Java {
    private static String SYSTEM = "SYSTEM";
    private static String consPath = "D:\\cons_1\\";
    private static String uVedaPath = "U:\\Veda\\VEDA3000\\CONS_1\\";
    private static String srcPath = "D:\\cons_1\\receive\\LAST_REC.TXT";
    private static String dstPath = "D:\\cons_1\\base";

    public static void main(String[] args) throws IOException {
        HashSet<String> holdNamesArray = ReadRawLinesAndWriteToArray(srcPath);
        RemoveUnnecessaryDirectories(holdNamesArray, dstPath);
        LaunchKPlus(uVedaPath, consPath, SYSTEM);
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
            Delete.deleteDirectory(dir);
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

    private static void LaunchKPlus(String uVedaPath, String consPath, String SYSTEM) throws IOException {
        // TODO: 21.10.2016 Realize
        String uVedaSYSTEMPath = uVedaPath + SYSTEM;
        String dstSYSTEMPath = consPath + SYSTEM;
        Delete.deleteDirectory(new File(dstSYSTEMPath));
        if (!Copy.copyDir(uVedaSYSTEMPath, dstSYSTEMPath)) {
            throw new FileNotFoundException("Files has not copied from " + uVedaPath + " to " + consPath);
        }
        Delete.deleteFile(dstSYSTEMPath + "\\cd.zip");
        Delete.deleteFile(dstSYSTEMPath + "\\cef2272.zip");
        ProcessBuilder pb = new ProcessBuilder("D:\\cons_1\\cons.exe");
        // TODO: 22.10.2016 Maybe I must use not "cons.exe" but "cons.lnk"
        pb.start();
    }
}
