package boomatang;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * Created by boomatang on 30/09/16.
 */
public class ProcessFiles {

    private CollectFiles cf;
    private SortFiles sf;
    private CreateFolder createFolder;
    private ArrayList<String> wbsGroup;

    private Path start;

    public ProcessFiles(Path start){
        this.start = start;
        cf = new CollectFiles();
    }

    public boolean process(){


        // collect the files

        System.out.println("Collecting files...");

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
        System.out.println();
        System.out.println("------------------------  Working below this  ------------------------------");
        System.out.println();
        sortPDFS();

        System.out.println("All Files have been created!");

        // Merge the pdfs
        // End

        return false;
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


    public void sortPDFS(){
        Map workingMap = sf.getDisciplineFiltered();
        ArrayList<String> tempKeys = new ArrayList<String>();
        ArrayList PDFnames = new ArrayList();
        ArrayList<PdfObj> PDFlist = new ArrayList<PdfObj>();

        for (Object key : workingMap.keySet()){
            tempKeys.add(key.toString());

        }

        for (String keys : tempKeys){
            System.out.println(keys);
            ArrayList<String> namesSorted = new ArrayList<>();
            PDFlist = (ArrayList<PdfObj>) workingMap.get(keys);
            for (PdfObj PDF : PDFlist) {
//                System.out.println(PDF.getName());
                namesSorted.add(PDF.getName());
                Collections.sort(namesSorted);
            }

            Path createPDFname = null;
            PDFMergerUtility ut = new PDFMergerUtility();

            for (String nameText : namesSorted) {

                for (PdfObj PDF : PDFlist) {
                    if (createPDFname == null) {
                        createPDFname = Paths.get(createFolder.getDateFolder().toString(), PDF.getWbs(),
                                PDF.getDiscipline() + " (" + LocalDate.now() + ").pdf");
                    }

                    if (PDF.getName().equalsIgnoreCase(nameText)) {
                        System.out.println(PDF.getFilePath());
                        try {
                            ut.addSource(new File(PDF.getFilePath().toString()));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        //createFolder.createSubFolder(PDF.getWbs(), PDF.getDiscipline());
                    }
                }
            }
                try {
                    ut.setDestinationFileName(createPDFname.toString());
                    ut.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
                } catch (IOException e) {
                    e.printStackTrace();

                createPDFname = null;
            }

            System.out.println();
            System.out.println("------------------------------------------------------");
            System.out.println();

        }

    }
}
