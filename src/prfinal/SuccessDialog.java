/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prfinal;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Omar
 */
public class SuccessDialog extends JDialog implements ActionListener{

    private JLabel text;
    private JButton ok;
    private JButton cancel;
    private String dir;
    private String filename;
    
    SuccessDialog(Frame owner, String title, String dir ,String filename){
        super(owner, title);
        this.dir = dir;
        this.filename = filename;
        
        this.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        text = new JLabel("File created successfully");
        ok = new JButton("OK");
        cancel = new JButton("Cancel");
        
        panel.add(ok, BorderLayout.WEST);
        panel.add(cancel, BorderLayout.EAST);
        
        this.add(text, BorderLayout.NORTH);
        this.add(panel, BorderLayout.SOUTH);
        
        ok.addActionListener(this);
        cancel.addActionListener(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == ok){
            try{
                Runtime.getRuntime().exec("notepad.exe " + dir + "\\" + filename);
                this.setVisible(false);
            }catch(Exception ex){
                ex.getStackTrace();
            }
        }
        else if(e.getSource() == cancel){
            this.setVisible(false);
        }
    }
    
    public void run(){
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(170, 90);
        this.setLocationRelativeTo(null);
        setVisible(true);
        Toolkit.getDefaultToolkit().beep();
    }
    
}
