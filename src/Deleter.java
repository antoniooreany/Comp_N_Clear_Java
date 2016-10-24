import java.io.File;

/**
 * Created by gorshkov on 24.10.2016.
 */
public class Deleter {
    /**
     * Deletes directory with subdirs and subfolders
     *
     * @param dir Directory to delete
     * @author Cloud
     */
    public static void deleteDirectoryContent(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                File f = new File(dir, children[i]);
                deleteDirectory(f);
            }
        } else dir.delete();
    }

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


    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.delete()) {
            return true;
        } else {
            return false;
        }
    }
}
