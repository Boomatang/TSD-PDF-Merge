package boomatang;

import java.util.*;

/**
 * Created by boomatang on 30/09/16.
 */
public class SortFiles {

    private ArrayList<PdfObj> fileList;
    private Map nameFilteredPDF;
    private ArrayList<PdfObj> nameShortList;
    private Map disciplineFiltered;
    private ArrayList<String> wbs;

    public SortFiles(ArrayList<PdfObj> fileList) {
        nameFilteredPDF = new HashMap<String, List<PdfObj>>();
        nameShortList = new ArrayList<>();
        disciplineFiltered = new HashMap<String, List<PdfObj>>();
        wbs = new ArrayList<>();


        this.fileList = fileList;
        System.out.println("Grouping files by name...");
        buildNameFilterPDF();
        System.out.println("Select the most up-to-date files...");
        newestRevision();
        System.out.println("Sorting files into disciplines...");
        setDisciplineFilter();
        System.out.println("Getting WBS areas..");
        setWbs();
    }

    /**
     * The function create a map of all the pdfs that have the same name
     */
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


    private void newestRevision() {
        for (Object key : nameFilteredPDF.keySet()) {
            List<Object> temp = (List<Object>) nameFilteredPDF.get(key);

            PdfObj pdf = new PdfObj();


            for (Object t : temp) {
                PdfObj test = (PdfObj) t;

                if (test.getHashValue() >= pdf.getHashValue() | test.getCreatedDate() >= pdf.getCreatedDate()) {
                    pdf = test;
                }
            }
            nameShortList.add(pdf);
        }
    }

    private void setDisciplineFilter() {
        for (PdfObj PDF : nameShortList) {
            if (disciplineFiltered.containsKey(PDF.getDiscipline())) {
                ArrayList temp = (ArrayList) disciplineFiltered.get(PDF.getDiscipline());
                temp.add(PDF);
                disciplineFiltered.put(PDF.getDiscipline(), temp);
            } else {
                List<Object> temp = new ArrayList<Object>();
                temp.add(PDF);
                disciplineFiltered.put(PDF.getDiscipline(), temp);
            }
        }
    }

    private void setWbs(){
        for (PdfObj pdf : nameShortList){
            if(!wbs.contains(pdf.getWbs())){
                wbs.add(pdf.getWbs());
            }
        }
    }

    public ArrayList<String> getWbs(){
        return wbs;
    }

    public Map getDisciplineFiltered() {
        return disciplineFiltered;
    }


}
