import java.io.IOException;

/**
 * Created by User on 27.10.2016.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("startConsLnk_test.cmd");
        System.out.println("Hiiii !!!");
//        ProcessBuilder pb = new ProcessBuilder("C:\\Users\\gorshkov\\IdeaProjects\\Comp_N_Clear_Java\\startConsLnk.cmd");
        // 26.10.2016 Maybe I must use not "cons.exe" but "cons.lnk". Done by running "startConsLnk.cmd", which can run "cons.lnk".
        pb.start();
    }
}