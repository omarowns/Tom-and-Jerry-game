package prfinal;

/* * IMPORTS * */
import javax.swing.*;

public class aboutWindow extends JFrame{
    
    private JLabel text;
    private boolean visible = false;
    
    public aboutWindow(){
        setTitle("Tom & Jerry -- About");
        String _contents = "<html><p>This program was created by <font color=#00e7e7>Omar Garcia (The Viking)</font> and <font color=red>Omar Baez (Freeko)</font>.<br><p>As for now it's almost complete on the GUI part and needs to work the logic of the game itself.<br><p>It was made for the sole purpose of a final project for Java Programming and Data Structures.</html>";
        text = new JLabel(_contents);
        this.add(text);
    }
    public void run(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(480,180);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        setVisible(true);
    }
}
