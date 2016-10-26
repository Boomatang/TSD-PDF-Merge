package GUI;

import Information.IAbout;

import javax.swing.*;

/**
 * Created by Boomatang on 26/10/2016.
 */
public class About implements IAbout {

    private String infoMassge;
    private String titleBar;

    public About(){
        infoMassge = "Version: " + VERSION + "\nAuthor: " + AUTHOR;
        titleBar = TITLE;
        aboutMassage(infoMassge, titleBar);
    }

    /**
     * Makes a pop up massage with a single ok button
     *
     * @param infoMessage Text to be displayed to the user
     * @param titleBar    The Title of the window
     */
    private void aboutMassage(String infoMessage, String titleBar) {

        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

}
