package GUI;

import javax.swing.*;

/**
 * Created by boomatang on 27/10/16.
 * A guide for making the panels that will be used for controls
 */
public abstract class PanelDefaults {
    abstract void addEventHandlers();
    abstract void makeButtons();
    abstract void createPanels();
    public abstract JPanel getPanel();
}
