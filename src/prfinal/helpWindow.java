package prfinal;

import java.awt.BorderLayout;
import javax.swing.*;

/* * IMPORTS * */
public class helpWindow extends JFrame{
    
    private JLabel text;
    
    public helpWindow(){
        super("Tom & Jerry -- Guide");
        String _contents = "<html><p>Click on File->Open and select the correct file.<br>The correct filename must be <font color=blue>'TOM1.DAT'</font> for Test 1 and <font color=blue>'TOM2.DAT'</font> for Test 2. <br><p>Afterwards click the 'Test 1' button or 'Test 2' button, where it applies. <br>When finished, click the <font color=red> X </font> button on the upper right corner to close the program. <br></html>";
        text = new JLabel(_contents);
        this.add(text);
    }
    public void run(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500,180);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        setVisible(true);
    }
}
