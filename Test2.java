import java.util.*;
import java.io.*;

public class Test2{

   public static void main(String[] args) throws IOException{

      File dataFile = new File("data");
      File[] dataDirectories = dataFile.listFiles(new FilenameFilter(){
            @Override
            public boolean accept(File current, String name){
            return new File(current, name).isDirectory();
            }
            });  

      for(int i = 0; i<dataDirectories.length; i++){

         File printFiles = new File(dataFile, dataDirectories[i].getName());
         if(printFiles.exists()){
            File[] listOfFiles = printFiles.listFiles(new FilenameFilter(){
                  public boolean accept(File dir, String name){
                  return name.toLowerCase().endsWith(".txt");
                  }
                  }); 
            for(int j = 0; j<listOfFiles.length; j++){
               Scanner scanner = new Scanner(listOfFiles[j]);
               if(scanner.hasNext()){
                  System.out.println();
                  System.out.println(dataDirectories[i].getName());
                  System.out.println(listOfFiles[j].getName());
                  while(scanner.hasNext()){
                     System.out.print(scanner.next() + " ");
                  }
                  System.out.println();
                  scanner.close();
               }

            }
         }
      }
   }
}
