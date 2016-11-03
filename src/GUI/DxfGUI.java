package GUI;

import DXFMerge.DXFThread;

import javax.swing.*;
import java.awt.*;

/**
 * Created by boomatang on 27/10/16.
 */
public class DxfGUI extends PanelDefaults {
    private JButton run, cancel;
    private JCheckBox allFiles;
    private JPanel content;
    private DXFThread dxfThread;
    private JLabel display;

    public DxfGUI(JLabel display){
        this.display = display;
        createPanels();
        makeButtons();
    }

    @Override
    void addEventHandlers() {
        dxfThread = new DXFThread(display);

        run.addActionListener(e -> dxfThread.begin());

    }

    void createPanels(){
        content = new JPanel(new GridLayout(2, 2));
    }

    void makeButtons(){
        run = new JButton("Collect DXF's");
        content.add(run);

        allFiles = new JCheckBox("All Files", false);
        content.add(allFiles);

        cancel = new JButton("Cancel");
        content.add(cancel);

    }

    public JPanel getPanel(){
        return content;
    }



}
