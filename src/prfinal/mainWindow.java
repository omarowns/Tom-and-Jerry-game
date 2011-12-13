package prfinal;

/* * IMPORTS * */
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

public class mainWindow extends JFrame{
    private String filename = null;
    private String dir = null;
    
    private JMenuBar menuBar;
    private JMenu file;
        private JMenuItem open;
        private JMenuItem exit;
    private JMenu help;
        private JMenuItem _help;
        private JMenuItem about;
    private JLabel fileName;
        private Icon image;
    private JButton test1;
    private JButton test2;
    
    
    public mainWindow(){
        super("Tom & Jerry -- Main");
        this.setLayout(new BorderLayout());
        TheHandler handler = new TheHandler();
        ButtonHandler buttonHandler = new ButtonHandler();
        
        JPanel p = new JPanel();
        JPanel p1 = new JPanel();
            p1.setLayout(new BorderLayout());
            
        menuBar = new JMenuBar();
        file = new JMenu("File");
            open = new JMenuItem("Open");
            exit = new JMenuItem("Exit");
        help = new JMenu("Help");
            _help = new JMenuItem("Guide");
            about = new JMenuItem("About");
            
        fileName = new JLabel("No file chosen D:", JLabel.CENTER);
            image = new ImageIcon(getClass().getResource("/prfinal/res/images/title.png"));
            JLabel icon = new JLabel("");
            icon.setIcon(image);
        test1 = new JButton("Test 1");
        test2 = new JButton("Test 2");
        
        file.add(open);
        file.addSeparator();
        file.add(exit);
        
        help.add(_help);
        help.addSeparator();
        help.add(about);
        
        menuBar.add(file);
        menuBar.add(help);
        this.setJMenuBar(menuBar);
        
        p1.add(icon, BorderLayout.NORTH);
        p1.add(fileName, BorderLayout.SOUTH);
        p.add(test1);
        p.add(test2);
        this.add(p1, BorderLayout.NORTH);
        this.add(p, BorderLayout.SOUTH);
        
        open.addActionListener(handler);
        exit.addActionListener(handler);
        _help.addActionListener(handler);
        about.addActionListener(handler);
        test1.addActionListener(buttonHandler);
        test2.addActionListener(buttonHandler);
        test1.setEnabled(false);
        test2.setEnabled(false);
        
        this.add(p, BorderLayout.SOUTH);
    }
    
    public void run(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        //this.setSize(230,200);
        this.setMinimumSize(this.getSize());
        this.setVisible(true);
    }
    
    private class TheHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == exit){
                System.exit(1);
            }
            else if(e.getSource() == open){
                resetValues();
                FileChooserTest();
                if(filename != null){
                    if(filename.contains(".DAT")){ //Set to filename.contains("TOM1.DAT"); in final version
                        test1.setEnabled(true);
                        test2.setEnabled(true); //REMOVE this line on final version
                    }
                    else if(filename.contains(".DAT")) //Set to filename.contains("TOM2.DAT"); in final version
                        test2.setEnabled(true);
                    else{
                        filename = "Bad Input File";
                        dir = null;
                    }
                    fileName.setText(filename);
                    System.out.println("Filename: " + filename);
                    System.out.println("Directory: " + dir);
                }
                else{
                    fileName.setText("No file chosen D:");
                    System.out.println("Filename: NOT CHOSEN");
                    System.out.println("Directory: NOT CHOSEN");
                    test1.setEnabled(false);
                    test2.setEnabled(false);
                } 
            }
            else if(e.getSource() == _help){
                //SHOW HELP WINDOW
                helpWindow hW = new helpWindow();
                hW.run();
            }
            else if(e.getSource() == about){
                //SHOW ABOUT WINDOW
                aboutWindow aW = new aboutWindow();
                aW.run();
            }
        }
        private void resetValues() {
            test1.setEnabled(false);
            test2.setEnabled(false);
        }
    }
    
    private class ButtonHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == test1){
                System.out.println("T1-Full path: " + dir + "\\" +filename);
                try{
                    Test_One t1 = new Test_One();
                    t1.main(dir, filename);
                }catch(Exception ex){
                    ex.getStackTrace();
                }
            }
            else if(e.getSource() == test2){
                System.out.println("T2-Full path: " + dir + "\\" +filename);
                try {
                    Test_Two t2 = new Test_Two();
                    t2.main(dir, filename);
                } catch (Exception ex) {
                    ex.getStackTrace();
                }
            }
        }
    }
    
    public void FileChooserTest() {    
        JFileChooser c = new JFileChooser();
        FileFilter DATfilter = new DATfilter("DAT Files", ".DAT");
        c.setFileFilter(DATfilter);
          // Demonstrate "Open" dialog:
          int rVal = c.showOpenDialog(c);
          if (rVal == JFileChooser.APPROVE_OPTION) {
            filename = c.getSelectedFile().getName();
            dir = c.getCurrentDirectory().toString();
          }
          if (rVal == JFileChooser.CANCEL_OPTION) {
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          }

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }
    class DATfilter extends FileFilter{
        private String description;
        private String extension;
        private DATfilter(String string, String string0) {
            this.description = string;
            this.extension = string0;
        }
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().endsWith(this.extension);
            }
            @Override
            public String getDescription() {
                return this.description;
            }
        }
}
//Runtime.getRuntime().exec("notepad.exe " + fullPath);