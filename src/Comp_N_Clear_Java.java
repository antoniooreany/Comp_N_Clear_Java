import java.util.ArrayList;

/**
 * Created by gorshkov on 21.10.2016.
 */
public class Comp_N_Clear_Java {
    public static void main(String[] args) {
        String srcPath = "D:\\cons_1\\receive\\LAST_REC.TXT";
        String dstPath = "D:\\cons_1\\base";

        ArrayList holdNamesArray = ReadFromFileToArray(srcPath);
        RemoveUnnecessaryDirectories(holdNamesArray, dstPath);
        LaunchKPlus();
    }

    private static ArrayList ReadFromFileToArray(String srcPath) {
        ArrayList result = new ArrayList();
        // TODO: 21.10.2016 Realize

        return result;
    }

    private static void RemoveUnnecessaryDirectories(ArrayList holdNamesArray, String dstPath) {
// TODO: 21.10.2016 Realize by Java IO

    }

    private static void LaunchKPlus() {
        // TODO: 21.10.2016 Realize
    }
}
