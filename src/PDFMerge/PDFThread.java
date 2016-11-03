package PDFMerge;

import javax.swing.*;
import java.nio.file.Path;

/**
 * Created by Boomatang on 08/10/16.
 */
public class PDFThread implements Runnable{
    private Thread cThread = new Thread(this);
    private JLabel display;
    private Boolean  go;
    private Path start;

    public PDFThread(JLabel windowIn){
        display = windowIn;
        go = true;
    }
    public void run(){
        while (go) {

            display.setText("Starting with using threads");
            try {
                ProcessFiles pf = new ProcessFiles(start);
                go = pf.process();

                cThread.sleep(1000);
                display.setText("Done");
                cThread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        display.setText("Stopped");

    }
    public void begin(Path start){
        if (!go){
            go = true;
        }
        this.start = start;

        cThread.start();


    }
    public void finish(){
        go = false;
    }
}
