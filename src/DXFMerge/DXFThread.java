package DXFMerge;

import javax.swing.*;

/**
 * Created by boomatang on 27/10/16.
 */
public class DXFThread implements Runnable {

    private Boolean go;
    private Thread cThread = new Thread(this);
    private JLabel display;

    public DXFThread(JLabel display){
        go = true;
        this.display = display;
    }

    public void run(){
        while (go) {
            System.out.println("Starting thread");
            display.setText("Starting to collect dxf files...");
        }

    }

    public void begin(){
        cThread.start();

    }

    public void finish(){
        go = false;
    }
}
