package boomatang;

import javax.swing.*;
import java.nio.file.Path;

/**
 * Created by boomatang on 08/10/16.
 */
public class MyThread implements Runnable{
    private Thread cThread = new Thread(this);
    private JLabel display;
    private Boolean  go;
    private Path start;

    public MyThread(JLabel windowIn){
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
