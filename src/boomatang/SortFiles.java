package boomatang;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Created by boomatang on 30/09/16.
 */
public class SortFiles {

    private CollectFiles cf;
    private ArrayList<PdfObj> fileList;
    private Map nameFilteredPDF;
    private ArrayList<PdfObj> nameShortList;
    private Map disciplineFilterd;

    public SortFiles(Path start) {
        nameFilteredPDF = new HashMap<String, List<PdfObj>>();
        nameShortList = new ArrayList<PdfObj>();
        disciplineFilterd = new HashMap<String, List<PdfObj>>();
        cf = new CollectFiles();

        try {
            Files.walkFileTree(start, cf);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        fileList = cf.getFileList();
        buildNameFilterPDF();
        newestRevision();
        setDisciplineFilterd();
    }

    private void buildNameFilterPDF() {
        for (PdfObj PDF : fileList) {
            if (nameFilteredPDF.containsKey(PDF.getName())) {
                List<Object> temp = (ArrayList<Object>) nameFilteredPDF.get(PDF.getName());
                temp.add(PDF);
                nameFilteredPDF.put(PDF.getName(), temp);
            } else {
                List<Object> temp = new ArrayList<Object>();
                temp.add(PDF);
                nameFilteredPDF.put(PDF.getName(), temp);
            }
        }
    }

    @NotNull
    private Set getKeys(){
        return nameFilteredPDF.keySet();
    }

    private void newestRevision(){
        for (Object key : getKeys()){
            List<Object> temp = (List<Object>) nameFilteredPDF.get(key);

            PdfObj pdf = new PdfObj();


            for (Object t : temp){
                PdfObj test = (PdfObj) t;

                if (test.getHashValue() >= pdf.getHashValue() | test.getCreatedDate() >= pdf.getCreatedDate()){
                    pdf = test;
                }
            }
            nameShortList.add(pdf);
        }
    }

    private void setDisciplineFilterd() {
        for (PdfObj PDF : nameShortList) {
            if (disciplineFilterd.containsKey(PDF.getName())) {
                List<Object> temp = (ArrayList<Object>) disciplineFilterd.get(PDF.getName());
                temp.add(PDF);
                disciplineFilterd.put(PDF.getName(), temp);
            } else {
                List<Object> temp = new ArrayList<Object>();
                temp.add(PDF);
                disciplineFilterd.put(PDF.getName(), temp);
            }
        }
    }

    public Map getDisciplineFilterd(){
        return disciplineFilterd;
    }

    public void countList() {
        cf.countList();
        System.out.println(nameFilteredPDF.size());
    }
}
