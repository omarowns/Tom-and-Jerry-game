/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prfinal;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import prfinal.mainTest.Obstacles;

/**
 *
 * @author Omar
 */
class jUnreachableGraph extends JFrame{
    private JLabel text;
    private JButton[][] matrix_graph;
    private Icon tom;
    private Icon jerry;
    private Icon obstacle;
    private JPanel panel;

    private int sizeX, sizeY;
    private int tomX, tomY, jerryX, jerryY;
    private ArrayList<Obstacles> obstacles = new ArrayList<Obstacles>();
        private char[][]matrix_temp;
    private LinkedList path;

    public void setJerryX(int jerryX) {
        this.jerryX = jerryX;
    }

    public void setJerryY(int jerryY) {
        this.jerryY = jerryY;
    }

    public void setObstacles(ArrayList<Obstacles> obstacles) {
        this.obstacles = obstacles;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public void setTomX(int tomX) {
        this.tomX = tomX;
    }

    public void setTomY(int tomY) {
        this.tomY = tomY;
    }
    
    public void setPath(LinkedList path){
        this.path = path;
    }
    
    public jUnreachableGraph(){
        super("UNREACHABLE");
        String _content = "<html><FONT COLOR=RED>UNREACHABLE PATH</FONT>";
        text = new JLabel(_content);
        text.setFont( new Font("Serif", Font.PLAIN, 30));
        this.setLayout(new BorderLayout());
    }

    public void init(){
        tom = new ImageIcon(getClass().getResource("/prfinal/res/images/tom.png"));
        jerry = new ImageIcon(getClass().getResource("/prfinal/res/images/jerry.png"));
        obstacle = new ImageIcon(getClass().getResource("/prfinal/res/images/obstacle.png"));
        panel = new JPanel();
        panel.setLayout(new GridLayout(sizeX,sizeY));
        
        initGraphMatrix();
        setTempMatrix();
        setGraphMatrix();
        setUpPanel();
        
        this.add(text, BorderLayout.NORTH);
        this.add(panel, BorderLayout.SOUTH);
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
    }

    private void setTempMatrix() {
        matrix_temp = new char[sizeX][sizeY];
        stackCharMatrix(matrix_temp);
        if( obstacles.size() >= 2 ){
            for(Obstacles o : obstacles){
                for(int i=o.getUpperLeftX(); i<=o.getLowerRightX(); i++){
                    for(int j=o.getUpperLeftY(); j<=o.getLowerRightY(); j++){
                        if(matrix_temp[i][j] == 'o')
                            matrix_temp[i][j] = 'x';
                        else{
                            
                            break;
                        }
                    }
                }
            }
        }
        matrix_temp[tomX][tomY] = 'T';
        matrix_temp[jerryX][jerryY] = 'J';
    }
    private void stackCharMatrix(char[][] matrix) {
        for(int i=0; i<sizeX; i++)
            for(int j=0; j<sizeY; j++)
                matrix[i][j] = 'o';
    }

    private void setGraphMatrix() {
        for(int i=0; i<sizeX; i++){
            for(int j=0; j<sizeY; j++){
                if(matrix_temp[i][j] == 'T')
                    matrix_graph[i][j].setIcon(tom);
                else if(matrix_temp[i][j] == 'J')
                    matrix_graph[i][j].setIcon(jerry);
                else if(matrix_temp[i][j] == 'x'){
                    matrix_graph[i][j].setIcon(obstacle);
                }
            }
        }
    }

    private void setUpPanel() {
        for(int i=0; i<sizeX; i++)
            for(int j=0; j<sizeY; j++)
                panel.add(matrix_graph[i][j]);
    }

    private void initGraphMatrix() {
        matrix_graph = new JButton[sizeX][sizeY];
         for(int i=0; i<sizeX; i++)
            for(int j=0; j<sizeY; j++)
                matrix_graph[i][j] = new JButton();
    }
}
