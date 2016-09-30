package boomatang;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Created by boomatang on 30/09/16.
 */
public class ProcessFiles {

    private CollectFiles cf;
    private SortFiles sf;
    private CreateFolder createFolder;
    private ArrayList<String> wbsGroup;

    public ProcessFiles(Path start){

        // collect the files

        System.out.println("Collecting files...");
        cf = new CollectFiles();
        try {
            Files.walkFileTree(start, cf);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        System.out.println("Files Collected...");

        // Sort the files
        System.out.println("Starting to sort the files...");
        sf = new SortFiles(cf.getFileList());
        // get the WBS listing
        wbsGroup = sf.getWbs();
        // Create the required folders
        System.out.println("Getting ready to set up required folders...");
        createFolder = new CreateFolder(start);

        System.out.println("Checking if COMBINED folder exist's...");
        if (!createFolder.combineExist()) {
            System.out.println("Creating Combine Folder...");
            createFolder.createCombine();
        } else {
            System.out.println("Folder exists, moving on...");
        }
        System.out.println("Creating date folder...");
        createFolder.createDateFolder();
        makeWbsFolders();

        // Merge the pdfs
        // End

    }


    private void makeWbsFolders(){
        int counter = 1;
        int total = wbsGroup.size();

        System.out.format("Creating %s WBS folders...", total);
        System.out.println();

        for (String wbs : wbsGroup){
            System.out.format("Creating %s of %s WBS Folders...", counter, total);
            System.out.println();
            counter += 1;
            createFolder.createWbsFolder(wbs);
        }
    }

}
