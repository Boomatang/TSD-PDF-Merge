package boomatang;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.regex.Pattern;


/**
 * Created by boomatang on 28/09/16.
 */
public class PdfObj {
    private long createdDate;
    private String revision;
    private String name;
    private Path filePath;

    public PdfObj(Path input){
        filePath = input;
        setName();
        setRevision();
        setCreatedDate();

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

    private void setName(){
        String temp = filePath.getFileName().toString();
        String[] temp2 = temp.split("_");
        name = temp2[0];
    }

    private void setRevision(){
        try {
            String temp = filePath.getFileName().toString();
            String[] temp2 = temp.split("_");
            temp2 = temp2[1].split(Pattern.quote("."));
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
}
