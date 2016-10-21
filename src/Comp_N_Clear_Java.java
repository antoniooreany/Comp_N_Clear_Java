import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gorshkov on 21.10.2016.
 */
public class Comp_N_Clear_Java {
    public static void main(String[] args) throws IOException {
        String srcPath = "D:\\cons_1\\receive\\LAST_REC.TXT";
        String dstPath = "D:\\cons_1\\base";

        HashSet<String> holdNamesArray = ReadRawLinesAndWriteToArray(srcPath);
//        for (String s:
//             holdNamesArray) {
//            System.out.println(s);
//        }

        RemoveUnnecessaryDirectories(holdNamesArray, dstPath);


        LaunchKPlus();
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

//        for (String s :
//                dstSet) {
//            System.out.println(s);
//        }

        // TODO: 21.10.2016 Remove from dst everything except holdNamesSet, using deleteDirectory(File dir)
//        HashSet<String> dirsToRemove = getDirsToRemove(holdNamesSet, dstSet);
//        for (String s :
//                dirsToRemove) {
//            System.out.println(s);
//        }
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
//        for (String s :
//                dstSet) {
//            System.out.println(s);
//        }
        for (String holdNamesSetElem :
                holdNamesSet) {
//            System.out.println(holdNamesSetElem);
            dstSet.remove(holdNamesSetElem);
        }

//        Iterator iterator = holdNamesSet.iterator();
//        while (iterator.hasNext()) {
//            dstSet.remove(iterator.next());
//        }

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


    private static void LaunchKPlus() {
        // TODO: 21.10.2016 Realize
    }
}
