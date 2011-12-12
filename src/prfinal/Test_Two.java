/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prfinal;

import java.io.File;
import java.util.LinkedList;

/**
 *
 * @author Omar
 */
public class Test_Two extends mainTest{
    
    LinkedList<Vertex> path;

     public void main(String dir, String filename){ //Arg is the input file
        file = new File(dir + "\\" + filename);
        if (!file.exists()) {
          System.out.println(dir + "\\" + filename + " does not exist.");
          return;
        }
        if (!(file.isFile() && file.canRead())) {
          System.out.println(file.getName() + " cannot be read from.");
          return;
        }
        allocateVariables();
        try{
            checkErrors();
        }catch (ErrorFound ef){
            ef.getStackTrace();
        }finally{
            output(dir,filename);
        }
    }
    
    @Override
    void generateFile(String dir, String filename) {
        try {
            doGraph();
        } catch(GraphNoDrawable ex) {
            ex.getStackTrace();
        }finally{
            outputGraph(dir,filename);
        }
        

    } //Here the method will call other methods to create the nodes and do the shortest path thingy
    
}
