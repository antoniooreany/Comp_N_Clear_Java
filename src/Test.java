import java.io.File;
import java.io.IOException;

/**
 * Created by User on 27.10.2016.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        System.out.println("U're here now:  " + new File(".").getAbsolutePath());
        ProcessBuilder pb = new ProcessBuilder("..\\..\\..\\startConsLnk_test.cmd");
        System.out.println("Hiiii !!!");
        // 26.10.2016 Maybe I must use not "cons.exe" but "cons.lnk". Done by running "startConsLnk.cmd", which can run "cons.lnk".
        pb.start();
        System.out.println("Hiiii again!!!");
    }
}
