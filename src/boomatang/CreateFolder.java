package boomatang;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
/**
 * Created by boomatang on 30/09/16.
 */
public class CreateFolder {

    private Path rootFolder;
<<<<<<< HEAD
    private Path combined;
=======
    private Path combine;
>>>>>>> master
    private Path dateFolder;


    public CreateFolder(Path start){

        System.out.println("Setting root folder...");
        setRootFolder(start);
<<<<<<< HEAD
        combined = Paths.get(rootFolder.toString(), "COMBINED");
=======
        combine = Paths.get(rootFolder.toString(), "COMBINE");
>>>>>>> master
    }

    private void setRootFolder(Path somePoint){
        Path possibleFolder;
        FolderWalk fw = new FolderWalk();

        try {
            Files.walkFileTree(somePoint, fw);
        } catch (IOException e) {
<<<<<<< HEAD
            System.out.print("Error in setRootFolder");
=======
>>>>>>> master
            e.printStackTrace();
        }
        possibleFolder = fw.getFolder();

        rootFolder = possibleFolder;

    }


    public boolean combineExist() {

<<<<<<< HEAD
        return Files.exists(combined);
=======
        return Files.exists(combine);
>>>>>>> master

    }

    public void createCombine() {
        try {
<<<<<<< HEAD
            Files.createDirectory(combined);
=======
            Files.createDirectory(combine);
>>>>>>> master
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createDateFolder(){
        String date = LocalDate.now().toString();
<<<<<<< HEAD
        dateFolder = Paths.get(combined.toString(), date);
=======
        dateFolder = Paths.get(combine.toString(), date);
>>>>>>> master
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
