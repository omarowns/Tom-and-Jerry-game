/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prfinal;

import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import prfinal.mainTest.Obstacles;

/**
 *
 * @author Omar
 */
abstract class jGraph extends JFrame{
    private JLabel text;
    private JButton[][] matrix_graph;
    
    private int sizeX, sizeY;
    private int tomX, tomY, jerryX, jerryY;
    private ArrayList<Obstacles> obstacles = new ArrayList<Obstacles>();
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
    
    jGraph(String title){
        super(title);
    }
}
