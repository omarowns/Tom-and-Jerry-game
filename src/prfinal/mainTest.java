/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prfinal;

import java.awt.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Omar
 */
public abstract class mainTest {
    public File file;
    
    private Vertex[][] matrix = null;
    private List<Vertex> nodes = new ArrayList<Vertex>();
    private List<Edge> edges = new ArrayList<Edge>(); 
    
    
    public int _error = -1;
    public int sizeX,sizeY;
    public int tomX,tomY,jerryX,jerryY;
    public ArrayList<Obstacles> obstacles = new ArrayList<Obstacles>();
            public class Obstacles{
                public int getLowerRightX() {
                    return lowerRightX;
                }
                public void setLowerRightX(int lowerRightX) {
                    this.lowerRightX = lowerRightX;
                }
                public int getLowerRightY() {
                    return lowerRightY;
                }
                public void setLowerRightY(int lowerRightY) {
                    this.lowerRightY = lowerRightY;
                }
                public int getUpperLeftX() {
                    return upperLeftX;
                }
                public void setUpperLeftX(int upperLeftX) {
                    this.upperLeftX = upperLeftX;
                }
                public int getUpperLeftY() {
                    return upperLeftY;
                }
                public void setUpperLeftY(int upperLeftY) {
                    this.upperLeftY = upperLeftY;
            }
        
        public int upperLeftX;
        public int upperLeftY;
        public int lowerRightX;
        public int lowerRightY;

        public void printValues() {
            System.out.println("uLX: " + this.getUpperLeftX());
            System.out.println("uLY: " + this.getUpperLeftY());
            System.out.println("lRX: " + this.getLowerRightX());
            System.out.println("lRY: " + this.getLowerRightY());
        }
    }

    public void allocateVariables() {
        try {
          FileInputStream fis = new FileInputStream(file);
          BufferedReader br = new BufferedReader(new InputStreamReader(fis));
          String current;
          int counter = 0;
          while( (current = br.readLine()) != null ) {
              if(counter <= 1){
                  switch( counter ){
                      case 0:
                          setGrid(current); break;
                      case 1:
                          setTJ(current); break;
                  }
              }else{
                  setObstacles(current);
              }
              counter++;
          }
          fis.close();
        } catch (IOException e) {
          e.getStackTrace();
        }
        
        print();
    }
    
    public void setGrid(String current) {
        String[] tokens = current.split("[ ]");
        try{
            sizeX = Integer.parseInt(tokens[0]);
            sizeY = Integer.parseInt(tokens[1]);
        }catch(NumberFormatException ex){
            ex.getStackTrace();
        }
    }

    public void setTJ(String current) {
        String[] tokens = current.split("[ ]");
        try{
            tomX = Integer.parseInt(tokens[0]) -1;
            tomY = Integer.parseInt(tokens[1]) -1;
            jerryX = Integer.parseInt(tokens[2]) -1;
            jerryY = Integer.parseInt(tokens[3]) -1;
        }catch(NumberFormatException ex){
            ex.getStackTrace();
        }
    }
    
    public void setObstacles(String current) {
        String[] tokens = current.split("[ ]");
        Obstacles obs = new Obstacles();
        try{
            obs.setUpperLeftX( Integer.parseInt(tokens[0])  -1);
            obs.setUpperLeftY( Integer.parseInt(tokens[1])  -1);
            obs.setLowerRightX( Integer.parseInt(tokens[2])  -1);
            obs.setLowerRightY( Integer.parseInt(tokens[3])  -1);
            obstacles.add(obs);
        }catch(NumberFormatException ex){
            ex.getStackTrace();
        }
    }
    
    public void checkErrors() throws ErrorFound {  //Should I replace with further functions
        if(sizeX == 0 || sizeY == 0){ 
            _error = 0;
            throw new ErrorFound();
        }
        else if( tomX > sizeX || tomY > sizeY || jerryX > sizeX || jerryY > sizeY ){ //
            _error = 1;
            throw new ErrorFound();
        }
        else if( tomX == jerryX && tomY == jerryY ){
            _error = 2;
            throw new ErrorFound();
        }
        else if( isObstaclesOutOfBorders() ){
            _error = 3;
            throw new ErrorFound();
        }
        else if( isVertexOK() ){
            _error = 4;
            throw new ErrorFound();
        }
        else if( isObstaclesOverlapping() ){
            _error = 5;
            throw new ErrorFound();
        }
        else if( isTJonObstacle() ){
            _error = 6;
            throw new ErrorFound();
        }  
    }
    
    public boolean isVertexOK(){
        boolean flag = false;
        for(Obstacles o : obstacles){
            if( o.getLowerRightX() < o.getUpperLeftX() || o.getLowerRightY() < o.getUpperLeftY() ){
                flag = true;
                break;
            }
            else
                continue;
        }
        return flag;
    }
    
    public boolean isObstaclesOutOfBorders() {
        boolean flag = false;
        for(Obstacles o : obstacles){
            if( o.getUpperLeftX() > sizeX || o.getUpperLeftY() > sizeY ){
                flag = true;
                break;
            }
            else if( o.getLowerRightX() > sizeX || o.getLowerRightY() > sizeY ){
                flag = true;
                break;
            }
            else
                flag = false;
        }
        return flag;
    }

    public boolean isObstaclesOverlapping() {
        boolean flag = false;
        char [][]matrix_temp = new char[sizeX][sizeY];
        stackCharMatrix(matrix_temp);
        if( obstacles.size() >= 2 ){
            for(Obstacles o : obstacles){
                for(int i=o.getUpperLeftX(); i<=o.getLowerRightX(); i++){
                    for(int j=o.getUpperLeftY(); j<=o.getLowerRightY(); j++){
                        if(matrix_temp[i][j] == 'o')
                            matrix_temp[i][j] = 'x'; //Or also flag = true; This means that the cell hasn't been used, therefore there hasn't been a collision
                        else{
                            flag = true; //This means that the cell already has a part of an obstacle, therefore a collision happened
                            break;
                        }
                    }
                }
            }
        }
        else
            flag = false;
        return flag;
    }

    public boolean isTJonObstacle() {
        boolean flag = false;
        for(Obstacles o : obstacles){
            if( (tomX >= o.getUpperLeftX() && tomX <= o.getLowerRightX()) && 
                (tomY >= o.getUpperLeftY() && tomY <= o.getLowerRightY()) ){
               flag = true;
               break;
            }
            else if( (jerryX >= o.getUpperLeftX() && jerryX <= o.getLowerRightX()) && 
                     (jerryY >= o.getUpperLeftY() && jerryY <= o.getLowerRightY())  ){
                flag = true;
                break;
            }
            else continue;
        }
        return flag;
    }

    public void output(String dir, String filename) {
        if( _error != -1 ){
            generateErrorFile(dir,filename);
        }
        else{
            generateFile(dir,filename);
        }
    }

            public void generateErrorFile(String dir, String filename) {
                try{
                    String res = "RES";
                    filename = filename.replaceAll("DAT",res);                    
                    FileOutputStream fos = new FileOutputStream(dir + "\\" + filename);
                    PrintStream ps = new PrintStream(fos);
                    ps.println("ERROR E" + _error);
                    fos.close();
                    runErrorDialog(dir, filename);
                }catch(IOException ioex){
                    ioex.getStackTrace();
                }
            }

            abstract void generateFile(String dir, String filename);

    public void stackCharMatrix(char[][] matrix) {
        for(int i=0; i<sizeX; i++)
            for(int j=0; j<sizeY; j++)
                matrix[i][j] = 'o';
    }

    public void allocateTJ(char[][] matrix) {
        matrix[tomX][tomY] = 'T';
        matrix[jerryX][jerryY] = 'J';
    }

    public void allocateObstacles(char[][] matrix) {
        for(Obstacles o : obstacles){
            for(int i=o.getUpperLeftX(); i<=o.getLowerRightX(); i++)
                for(int j=o.getUpperLeftY(); j<=o.getLowerRightY(); j++)
                    matrix[i][j] = 'x';
        }
    }
    
    public void print() {
        System.out.println("Size X = " + sizeX);
        System.out.println("Size Y = " + sizeY);
        System.out.println("Tom X: " + tomX);
        System.out.println("Tom Y: " + tomY);
        System.out.println("Jerry X: " + jerryX);
        System.out.println("Jerry X: " + jerryX);
        for(Obstacles e : obstacles){
            e.printValues();
        }
    }
    
    private void runErrorDialog(String dir, String filename){
        //JOptionPane.showMessageDialog(null, "File has an error","FILE ERROR",JOptionPane.ERROR_MESSAGE);
        ErrorDialog ed = new ErrorDialog(new Frame(), "FILE ERROR", dir, filename);
        ed.run();
    }

    public void runSuccessDialog(String dir, String filename){
        SuccessDialog sd = new SuccessDialog(new Frame(), "FILE SUCCESS", dir, filename);
        sd.run();
    }
    
    private boolean isValid(Vertex vertex) {
        return true;
    }
    
    private void fillVertexMatrix(Vertex[][] matrix) {
        int c = 0;
        for(int i=0; i<sizeX; i++){
            for(int j=0; j<sizeY; j++){
                matrix[i][j] = new Vertex("Node_"+c,"Node_"+c,i,j);
                c++;
            }
        }
    }
    
    
    public void create(){
        //Create vertex matrix and fill it up
        matrix = new Vertex[sizeX][sizeY];
        fillVertexMatrix(matrix);
        
        //Create Char matrix as before, exactly as before ¬¬, and fill it up
        char[][] matrix_char = new char[sizeX][sizeY];
        stackCharMatrix(matrix_char);
        allocateTJ(matrix_char);
        allocateObstacles(matrix_char);
        
        //Add edges
        int c = 0;
        for(int i=0; i<sizeX; i++){
            for(int j=0; j<sizeY; j++){
                
                //Try left side
                try{
                    if( isValid(matrix[i -1][j]) ){
                        if( matrix_char[i -1][j] != 'x'){
                            //Add edge
                            Edge e = new Edge("Edge_"+c, matrix[i][j], matrix[i -1][j], 1);
                            edges.add(e);
                            c++;
                        }
                    }
                }catch(Exception ex){
                    ex.getStackTrace();
                }
                
                //Try up side
                try{
                    if( isValid(matrix[i][j -1]) ){
                        if( matrix_char[i][j -1] != 'x'){
                            //Add edge
                            Edge e = new Edge("Edge_"+c, matrix[i][j], matrix[i][j -1], 1);
                            edges.add(e);
                            c++;
                        }
                    }
                }catch(Exception ex){
                    ex.getStackTrace();
                }
                
                //Try right ride
                try{
                    if( isValid(matrix[i +1][j]) ){
                        if( matrix_char[i +1][j] != 'x'){
                            //Add edge
                            Edge e = new Edge("Edge_"+c, matrix[i][j], matrix[i +1][j], 1);
                            edges.add(e);
                            c++;
                        }
                    }
                }catch(Exception ex){
                    ex.getStackTrace();
                }
                
                //Try down side
                try{
                    if( isValid(matrix[i][j +1]) ){
                        if( matrix_char[i][j +1] != 'x'){
                            //Add edge
                            Edge e = new Edge("Edge_"+c, matrix[i][j], matrix[i][j +1], 1);
                            edges.add(e);
                            c++;
                        }
                    }
                }catch(Exception ex){
                    ex.getStackTrace();
                }
            }
        }
        for(int i=0; i<sizeX; i++){
            for(int j=0; j<sizeY; j++){
                nodes.add(matrix[i][j]);
            }
        }
    }
    
    public void doGraph(){
        Graph graph = new Graph(nodes, edges);
	DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
	dijkstra.execute(searchTom());
	LinkedList<Vertex> path = dijkstra.getPath(searchJerry());

        for (Vertex vertex : path) {
            System.out.print(vertex); System.out.println(": " + vertex.getCoordX() + " " + vertex.getCoordY());
	}

    }
    
    private Vertex searchTom() {
        Vertex ret = null;
        for(Vertex v : nodes){
            if(v.getCoordX() == tomX && v.getCoordY() == tomY){
                ret = v;
                break;
            }
            else
                continue;
        }
        return ret;
    }
    
    private Vertex searchJerry() {
        Vertex ret = null;
        for(Vertex v : nodes){
            if(v.getCoordX() == jerryX && v.getCoordY() == jerryY){
                ret = v;
            }
            else
                continue;
        }
        return ret;
    }
}
