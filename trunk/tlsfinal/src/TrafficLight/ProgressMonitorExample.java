/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TrafficLight;

/**
 *
 * @author luiz
 */
import static TrafficLight.ProgressMonitorExample.pbar;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ProgressMonitorExample extends JFrame implements ActionListener {

  static ProgressMonitor pbar;
  static int counter = 0;

  public ProgressMonitorExample() {
    super("Importing Files - Please wait...");
    setSize(250,100);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    pbar = new ProgressMonitor(null, "Importing Files - Please wait...","Initializing . . .", 0, 100);

    // Fire a timer every once in a while to update the progress.
    Timer timer = new Timer(500, this);
    timer.start();
    setVisible(true);
  }

  public static void main(String args[]) {
    UIManager.put("ProgressMonitor.progressText", "Importing");
    UIManager.put("OptionPane.cancelButtonText", "Cancel");
    new ProgressMonitorExample();
  }

  public void actionPerformed(ActionEvent e) {
    // Invoked by the timer every half second. Simply place
    // the progress monitor update on the event queue.
    SwingUtilities.invokeLater(new ProgressMonitorExample.Update());
  }

  class Update implements Runnable {
    public void run() {
      if (pbar.isCanceled()) {
        pbar.close();
        System.exit(1);
      }
    pbar.setProgress(counter);
    pbar.setNote("Operation is "+counter+"% complete");
    counter += 2;
    }
  }
}
