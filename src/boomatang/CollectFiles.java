package boomatang;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.regex.Pattern;

import static java.nio.file.FileVisitResult.*;

/**
 * Created by boomatang on 28/09/16.
 *
 * The information here has been taken from https://docs.oracle.com/javase/tutorial/essential/io/walk.html
 *
 * I will be modifying this code to suit my use case
 */
public class CollectFiles
    extends SimpleFileVisitor<Path> {

    private ArrayList<PdfObj> fileList;

    public CollectFiles(){
        fileList = new ArrayList<>();
    }

    // Print information about each type of file
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
        if (attr.isSymbolicLink()) {
            System.out.format("Symbolic link: %s ", file);
        } else if (attr.isRegularFile()) {
            //System.out.format("Regular file: %s ", file);

            String temp = file.getFileName().toString();
            String[] temp2 = temp.split(Pattern.quote("."));

            if (temp2[1].equalsIgnoreCase("pdf")) {
                System.out.format("Regular file: %s ", temp);
                fileList.add(new PdfObj(file));
            }
        } else {
            System.out.format("Other: %s ", file);
        }
        System.out.println("(" + attr.size() + "bytes)");
        return CONTINUE;
    }

    // Print each directory visited.
    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        System.out.format("Directory: %s%n", dir);
        return CONTINUE;
    }

    // If there is some error accessing the file, let the user know.
    // If you don't override this method and an error occurs, an IOException is thrown
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        System.err.println(exc);
        return CONTINUE;
    }

    public void countList(){
        System.out.println(fileList.size());
    }

}
