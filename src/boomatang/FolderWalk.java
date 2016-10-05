package boomatang;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;


import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.TERMINATE;

/**
 * Created by boomatang on 30/09/16.
 */
public class FolderWalk extends SimpleFileVisitor<Path> {
    private Path folder;


    public FolderWalk() {

    }

    // Print information about each type of file

    // Print each directory visited.
    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {


        if (dir.getFileName().toString().equals("Pdf's")){
            folder = dir;
            return TERMINATE;
        }


        return CONTINUE;
    }

    // If there is some error accessing the file, let the user know.
    // If you don't override this method and an error occurs, an IOException is thrown
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        System.err.println(exc);
        return CONTINUE;
    }


    public Path getFolder() {
        return folder;
    }
}

