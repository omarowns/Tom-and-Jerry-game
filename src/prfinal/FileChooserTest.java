package prfinal;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class FileChooserTest extends JFrame {
    public JTextField getDir() {
        return dir;
    }
    public JTextField getFilename() {
        return filename;
    }
    
    
  private JTextField filename = new JTextField(), dir = new JTextField();

  private JButton open = new JButton("Open"), save = new JButton("Save");

  public FileChooserTest() {
    JFileChooser c = new JFileChooser();
      // Demonstrate "Open" dialog:
      int rVal = c.showOpenDialog(FileChooserTest.this);
      if (rVal == JFileChooser.APPROVE_OPTION) {
        filename.setText(c.getSelectedFile().getName());
        dir.setText(c.getCurrentDirectory().toString());
      }
      if (rVal == JFileChooser.CANCEL_OPTION) {
        System.exit(1);
      }
      
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    //frame.setSize(width, height);
    setVisible(true);
  }

  public static void main() {
    run(new FileChooserTest());
  }

  public static void run(JFrame frame) {
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    //frame.setSize(width, height);
    frame.setVisible(true);
  }
}