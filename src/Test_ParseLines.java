import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gorshkov on 21.10.2016.
 */
public class Test_ParseLines {
    public static void main(String[] args) {
        String rawLine = "LAW,Российское законодательство (Версия Проф),82,82,21.10.2016,#HOST\n";
        rawLine = "LAW,Российское законодательство (Версия Проф),82,82,21.10.2016,#HOST";
//        rawLine = "PBI, gdgdgdgd,xfgsffsaf";
        String line = parseLines(rawLine);
        System.out.println(line);
    }

    static String parseLines(String rawLine) {
        Pattern p = Pattern.compile("([A-Z]+),.*$");
        Matcher m = p.matcher(rawLine);
        boolean matches = m.matches();
        String line = m.group(1);
        return line;
    }
}
