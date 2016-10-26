package Other;

import PDFMerge.FolderWalk;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

/**
 * Created by Boomatang on 19/10/2016.
 */
public class EndUser {
    public static void openFolder(String path) throws IOException {
        Runtime.getRuntime().exec("explorer.exe /open," + getPath(path));
    }

    private static String getPath(String startPoint){
        Path somePoint = Paths.get(startPoint);
        String path;
        Path possibleFolder;
        FolderWalk fw = new FolderWalk();

        try {
            Files.walkFileTree(somePoint, fw);
        } catch (IOException e) {
            System.out.print("Error in getting path");
            e.printStackTrace();
        }
        possibleFolder = fw.getFolder();
        path = Paths.get(possibleFolder.toString(), "COMBINED", LocalDate.now().toString(), File.separator).toAbsolutePath().toString();

        return path;
    }
//    TODO this might be need to fix the cross platform issue "Process p = new ProcessBuilder(\"explorer.exe\", \"/select,C:\\\\directory\\\\selectedFile\").start();"
}
