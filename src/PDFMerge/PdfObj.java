package PDFMerge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.regex.Pattern;


/**
 * Created by Boomatang on 28/09/16.
 */
public class PdfObj {
    private long createdDate;
    private String revision;
    private String name;
    private Path filePath;
    private String wbs;
    private String discipline;

    public PdfObj(){
        revision = "0";
        createdDate = 0;

    }

    public PdfObj(Path input){
        filePath = input;
        setName();
        setRevision();
        setCreatedDate();
        setDiscipline();
        setWbs();


    }

    public Path getFilePath() {
        return filePath;
    }

    public long getCreatedDate(){
        return createdDate;
    }

    public String getRevision(){
        return revision;
    }

    public String getName(){
        return name;
    }

    public String getWbs(){
        return wbs;
    }

    public String getDiscipline(){
        return discipline;
    }

    private void setName(){
        String temp = filePath.getFileName().toString();
        String[] temp2 = temp.split("_");
        name = temp2[0];
    }

    private void setRevision(){
        try {
            String temp = filePath.getFileName().toString();
            String[] temp2 = temp.split("_");

            temp2 = temp2[-1].split(Pattern.quote("."));

            revision = temp2[0];
        }
        catch (ArrayIndexOutOfBoundsException exc){
            revision = null;
        }
    }


    private void setCreatedDate(){
        try {
            BasicFileAttributes attr = Files.readAttributes(filePath, BasicFileAttributes.class);
            createdDate = attr.creationTime().toMillis();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void setDiscipline(){
        try {
            String[] temp = name.split("-", 2);

            if (temp[1].toLowerCase().startsWith("vn")) {
                discipline = "VENDOR";
            } else if (temp[1].toLowerCase().startsWith("pl")) {
                discipline = "PLATE";
            } else if (temp[1].toLowerCase().startsWith("se")) {
                discipline = "SECTION";
            } else if (temp[1].substring(3, 5).toLowerCase().startsWith("ga")) {
                temp = name.split("-");
                discipline = temp[3];
            }
            else {
                discipline = temp[1].substring(2, 4).toUpperCase();
            }
        }
        catch (ArrayIndexOutOfBoundsException exc) {
            discipline = "UNKNOWN";
        }
    }

    private void setWbs(){
        try {
            String[] temp = name.split("-", 2);

            if (temp[1].toLowerCase().startsWith("vn")) {
                wbs = "Global";
            } else if (temp[1].toLowerCase().startsWith("pl")) {
                wbs = "Global";
            } else if (temp[1].toLowerCase().startsWith("se")) {
                wbs = "Global";
            } else {
                wbs = temp[1].substring(0, 2).toUpperCase();
            }
        }
        catch (ArrayIndexOutOfBoundsException exc) {
            wbs = "UNKNOWN";
        }
    }

    public int getHashValue(){

        if (revision != null){
            return revision.hashCode();
        }
        else {
            return 0;
        }
    }
}
