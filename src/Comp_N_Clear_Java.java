import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gorshkov on 21.10.2016.
 */
public class Comp_N_Clear_Java {
    public static void main(String[] args) throws IOException {
        String srcPath = "D:\\cons_1\\receive\\LAST_REC.TXT";
        String dstPath = "D:\\cons_1\\base";

        ArrayList holdNamesArray = ReadFromFileToArray(srcPath);
        RemoveUnnecessaryDirectories(holdNamesArray, dstPath);
        LaunchKPlus();
    }

    private static ArrayList ReadFromFileToArray(String srcPath) throws IOException {

        // TODO: 21.10.2016 Realize
        ReadRawLinesAndWriteToArray(srcPath);
        return null;
    }

    private static void ReadRawLinesAndWriteToArray(String srcPath) throws IOException {
        String rawLine;
        String line;
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(srcPath))) {
            while ((rawLine = reader.readLine()) != null) {
                line = parseLines(rawLine);
                lines.add(line);
            }
        }
    }

    private static String parseLines(String rawLine) {
        Pattern p = Pattern.compile("([A-Z]+),.*$");
        Matcher m = p.matcher(rawLine);
        boolean matches = m.matches();
        String line = m.group(1);
        return line;
    }

    private static void RemoveUnnecessaryDirectories(ArrayList holdNamesArray, String dstPath) {
// TODO: 21.10.2016 Realize by Java IO

    }

    private static void LaunchKPlus() {
        // TODO: 21.10.2016 Realize
    }
}
