package PDFMerge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
/**
 * Created by Boomatang on 30/09/16.
 */
public class CreateFolder {

    private Path rootFolder;
    private Path combined;
    private Path dateFolder;


    public CreateFolder(Path start){

        System.out.println("Setting root folder...");
        setRootFolder(start);
        combined = Paths.get(rootFolder.toString(), "COMBINED");
    }

    private void setRootFolder(Path somePoint){
        Path possibleFolder;
        FolderWalk fw = new FolderWalk();

        try {
            Files.walkFileTree(somePoint, fw);
        } catch (IOException e) {
            System.out.print("Error in setRootFolder");
            e.printStackTrace();
        }
        possibleFolder = fw.getFolder();

        rootFolder = possibleFolder;

    }


    public boolean combineExist() {

        return Files.exists(combined);

    }

    public void createCombine() {
        try {
            Files.createDirectory(combined);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createDateFolder(){
        String date = LocalDate.now().toString();
        dateFolder = Paths.get(combined.toString(), date);
        if (!Files.exists(dateFolder)) {
            try {
                Files.createDirectory(dateFolder);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

    public void createWbsFolder(String wbs){
        Path create = Paths.get(dateFolder.toString(), wbs);
        if (!Files.exists(create)) {
            try {
                Files.createDirectory(create);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

    public void createSubFolder(String wbs, String folderName){
        Path create = Paths.get(dateFolder.toString(), wbs, folderName);
        if (!Files.exists(create)) {
            try {
                Files.createDirectory(create);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

    public Path getDateFolder(){
        return dateFolder;
    }
}
