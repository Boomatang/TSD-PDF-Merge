package Other;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Boomatang on 27/09/16.
 */
public class Events {

    /**
     * method exits the program with use of a dialog box
     *
     * @param exit boolean value to ensue the method should have been called
     */
    public void quit(Boolean exit) {

        int result = JOptionPane.showConfirmDialog(null, "You are about to Exit",
                "alert", JOptionPane.OK_CANCEL_OPTION);
        if (result == 0 && exit) {
            System.exit(0);
        } else {
            result = 0;
        }

    }


    /**
     * Makes a pop up massage with a single ok button
     *
     * @param infoMessage Text to be displayed to the user
     * @param titleBar    The Title of the window
     */
    public void popUpMassage(String infoMessage, String titleBar) {

        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }


    /**
     * Code copied form stack over flow
     *
     * @param parent
     * @return
     */
    public String promptForFolder(Component parent) {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setCurrentDirectory(new java.io.File(System.getProperty("user.home")));

        if (fc.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFile().getAbsolutePath();
        }

        return null;
    }

    public void testMethod(Path start) {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(start)) {
            for (Path file : stream) {
                System.out.println(file.getFileName());
            }

        } catch (IOException | DirectoryIteratorException x) {
            System.err.println(x);
        }
    }

}
