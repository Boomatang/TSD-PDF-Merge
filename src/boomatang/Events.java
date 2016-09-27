package boomatang;

import javax.swing.*;

/**
 * Created by boomatang on 27/09/16.
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

}
