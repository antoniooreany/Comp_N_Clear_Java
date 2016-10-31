import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gorshkov on 21.10.2016.
 */
public class Comp_N_Clear_Java {
    private static final String SYSTEM = "SYSTEM";
    private static final String consPath = "D:\\cons_1\\";
    private static final String uVedaPath = "U:\\Veda\\VEDA3000\\CONS_1\\";
    private static final String srcPath = "D:\\cons_1\\receive\\LAST_REC.TXT";
    private static final String dstPath = "D:\\cons_1\\base";
    private static final String exceptFilename1 = "cef2272.zip";
    private static final String exceptFilename0 = "cd.zip";

    public static void main(String[] args) throws IOException {
        Set<String> holdNamesArray = ReadLinesAndWriteParsedLinesToSet(srcPath);
        RemoveUnnecessaryDirectories(holdNamesArray, dstPath);
        LaunchKPlus(uVedaPath, consPath, SYSTEM, exceptFilename0, exceptFilename1);
    }

    private static Set<String> ReadLinesAndWriteParsedLinesToSet(String srcPath) throws IOException {
        String line;
        String parsedLine;
        Set<String> parsedLines = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(srcPath))) {
            while ((line = reader.readLine()) != null) {
                parsedLine = parseLines(line);
                parsedLines.add(parsedLine);
            }
        }
        return parsedLines;
    }

    private static String parseLines(String line) {
        Pattern p = Pattern.compile("([A-Z]+),.*$");
        Matcher m = p.matcher(line);
        m.matches();
        return m.group(1);
    }

    private static void RemoveUnnecessaryDirectories(Set<String> holdNamesSet, String dstPath) {

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
            Deleter.deleteDirectory(dir);
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

    private static void LaunchKPlus(String uVedaPath, String consPath, String SYSTEM, String exceptFilename0, String exceptFilename1) throws IOException {
        // TODO: 21.10.2016 Realize
        String uVedaSYSTEMPath = uVedaPath + SYSTEM;
        String dstSYSTEMPath = consPath + SYSTEM;
        Deleter.deleteDirectory(new File(dstSYSTEMPath));
        if (!Copier.copyDir(uVedaSYSTEMPath, dstSYSTEMPath, exceptFilename0, exceptFilename1)) {
            throw new FileNotFoundException("Files has not copied from " + uVedaPath + " to " + consPath);
        }
//        Deleter.deleteFile(dstSYSTEMPath + "\\cd.zip");
//        Deleter.deleteFile(dstSYSTEMPath + "\\cef2272.zip");
//        ProcessBuilder pb = new ProcessBuilder("D:\\cons_1\\cons.lnk");
//        ProcessBuilder pb = new ProcessBuilder("startConsLnk.cmd");
        ProcessBuilder pb = new ProcessBuilder(
                "C:\\Users\\gorshkov\\IdeaProjects\\Comp_N_Clear_Java\\out\\production\\Comp_N_Clear_Java\\startConsLnk.cmd");
        // 26.10.2016 Maybe I must use not "cons.exe" but "cons.lnk". Done by running "startConsLnk.cmd", which can run "cons.lnk".
        pb.start();
    }
}
