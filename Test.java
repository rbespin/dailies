import java.util.*;
import java.io.*;

public class Test{

   public static void main(String[] args) throws IOException{
      Time newTime = new Time();
      File dataFile = new File("data");
      File newFile = new File(dataFile, newTime.getDate());
      if (!newFile.exists()) {
         System.out.println("creating directory: " + newFile.getName());
         boolean result = false;

         try{
            newFile.mkdir();
            result = true;
         } 
         catch(SecurityException se){
            //handle it
         }        
         if(result) {    
            System.out.println("Folder for: " + newFile.getName() + " created");  
         }
      }

      File[] dataDirectories = dataFile.listFiles(new FilenameFilter(){
            @Override
            public boolean accept(File current, String name){
            return new File(current, name).isDirectory();
            }
            });  

      for(int i = 0; i<dataDirectories.length; i++){
         System.out.println(dataDirectories[i].getName());

         File printFiles = new File(dataFile, dataDirectories[i].getName());
         if(printFiles.exists()){
            File[] listOfFiles = printFiles.listFiles(new FilenameFilter(){
                  public boolean accept(File dir, String name){
                  return name.toLowerCase().endsWith(".txt");
                  }
                  }); 
            //  File[] listOfFiles = dataDirectories[i].listFiles();
            for(int j = 0; j<listOfFiles.length; j++){
               System.out.println(listOfFiles[j].getName());
            }
            System.out.println();
         }
      }
   }
}
