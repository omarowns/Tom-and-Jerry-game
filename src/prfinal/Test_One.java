/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prfinal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 *
 * @author Omar
 */
public class Test_One extends mainTest{

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
            output(dir, filename);
        }
    }
    
    @Override
    void generateFile(String dir, String filename) {
        try{
            String res = "RES";
            filename = filename.replaceAll("DAT", res);                    
            FileOutputStream fos = new FileOutputStream(dir + "\\" + filename);
            PrintStream ps = new PrintStream(fos);

            char [][]matrix = new char[sizeX][sizeY];
            stackCharMatrix(matrix);
            allocateTJ(matrix);
            allocateObstacles(matrix);
            for(char[]c : matrix){
                ps.println(c);
            }

            fos.close();
            runSuccessDialog(dir, filename);
        }catch(IOException ioex){
            ioex.getStackTrace();
        }
    }
    
}
