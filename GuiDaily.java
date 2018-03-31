import javafx.application.Application;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.*;
import javafx.scene.control.SelectionMode;  
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;
import java.util.*;
import java.io.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class GuiDaily extends Application{

   public static BorderPane mainPane = new BorderPane();
   public static GridPane gridPane = new GridPane();
   public static ScrollPane scrollPane = new ScrollPane();
   public static HBox titleBox = new HBox(35);

   public static String archiveFileStringName;


   @Override
      public void start(Stage primaryStage) throws IOException{

         dropDownInitialize();

         initialize();

         gridPane.setPrefSize(500,500);
         gridPane.setPadding(new Insets(40,40,40,40));
         gridPane.setHgap(30);

         scrollPane = new ScrollPane(gridPane);
         mainPane.setCenter(scrollPane);
         mainPane.setTop(titleBox);
         Scene scene = new Scene(mainPane);
         primaryStage.setTitle("Dailies");
         primaryStage.setScene(scene);
         primaryStage.show();

      }


   //ARCHIVE DROPDOWN MENU
   public static void dropDownInitialize(){

      //in place of titles, loop through data directory and get names 
      //of every folder that has a date
      String[] titles = {"temporary", "3/30/18", "3/29/18", "3/28/18", "3/27/18", 
         "3/29/18", "3/28/18", "3/27/18", "3/29/18", "3/28/18", "3/27/18",
         "3/29/18", "3/28/18", "3/27/18", "3/29/18", "3/28/18", "3/27/18",
         "3/29/18", "3/28/18", "3/27/18", "3/29/18", "3/28/18", "3/27/18",
         "3/29/18", "3/28/18", "3/27/18", "3/29/18", "3/28/18", "3/27/18",
         "3/29/18", "3/28/18", "3/27/18", "3/29/18", "3/28/18", "3/27/18", 
         "3/29/18", "3/28/18", "3/27/18"};

      ComboBox<String> cbo = new ComboBox<>();
      for(int i = 0; i < titles.length; i++){
         String item = new String(titles[i]);
         if(!item.equals("temporary")){
            cbo.getItems().add(item);
         }
      }

      cbo.setValue("Archived Dates");

      cbo.setOnAction( e ->{
            System.out.println(cbo.getValue());
            archiveFileStringName = cbo.getValue();
            //call archiveInitialize();
            });

      cbo.setVisibleRowCount(10);

      titleBox.getChildren().add(cbo);

      ///////////////////////////////////////////////////// */
   }


   /* @param archiveFileStringName - the filename of directory with archived file 
      of the pathname form data/archiveFileStringName. We will loop through the files
      in this directory. 
    */
   public static void archiveInitialize(String archiveFileStringName){
      /* This method will populate the mainPane with old date information

         1) set mainPane center to null

         2) add return button to current temporary date that calls initialize()
         for the current date written in the temporary file

         3) using the parameter's directory name, loop through the files in 
         the directory

         4) in the loop, get .txt filename. Use a scanner to read the file.
         The file will have information about whether to print the info or not. 
         close the scanner before continuing to loop
       */
   }

   public static void archiveCurrentDay(){
      /* navigate to temporary data directory (/data/temporary)

         1) Create a directory for current date

         2) loop through temporary directory files 

         3) write information from temporary files into correct format 
         for the current date's directory. Create files as needed.

         4) call initialize() for new day. If it is not a new day, overwrite 
         the date with whatever new information input

       */

   }

   public static void initialize() throws IOException{
      /* This method will clear all current printwriters, scanners, etc,
         to make way for fresh input
      //       1) clear temporary file directory(MAYBE NOT);

      2) add files to the temporary directory(water.txt, study.txt, etc)
      2.5) if the file is already there, do not overwrite */

      File dataDir = new File("data");
      File tempDir = new File(dataDir, "temporary");

      File waterTemp = new File(tempDir, "water.txt");
      waterTemp.createNewFile();
      File studyTemp = new File(tempDir, "study.txt");
      studyTemp.createNewFile();
      File foodTemp = new File(tempDir, "fastfood.txt");
      foodTemp.createNewFile();
      File exerciseTemp = new File(tempDir, "exercise.txt");
      exerciseTemp.createNewFile();
      File nightRoutineTemp = new File(tempDir, "nightroutine.txt");
      nightRoutineTemp.createNewFile();

      /*3) remove all children from titleBox, gridPane */
      titleBox.getChildren().clear();
      gridPane.getChildren().clear();

      /*4) re-call dropDownInitialize(); */
      dropDownInitialize();

      /*5) print current date at the top */
      Time today = new Time();
      Label titleText = new Label("Today: " + today.getDate());
      titleBox.getChildren().add(titleText);
      titleBox.setAlignment(Pos.CENTER_LEFT);

      /*6) for respective tasks, there will be respective files. i.e., if there
        is a portion for studying, there will be a studying.txt file written
        to the temporary folder. If there is a water section, there will be a 
        water.txt file. The files will begin with information about whether
        or not the task was performed(this applies only to universal items
        that we wish to keep track of for extended periods of time, such as 
        number of days we've had a gallon of water, hours studied per day,
        stuff like that.) For the settled upon sections, there will be a radio
        button asking yes or no questions. */

      // File[] tempList = tempDir.listFiles();
      // for(int i = 0; i < tempList.length; i++){
      //    }

      Label waterLabel = new Label("Did you drink a gallon of water today?");
      gridPane.add(waterLabel,0,1);

      RadioButton noButton = new RadioButton("No");
      RadioButton yesButton = new RadioButton("Yes");
      ToggleGroup group = new ToggleGroup();
      noButton.setToggleGroup(group);
      noButton.setSelected(true);
      yesButton.setToggleGroup(group);
      gridPane.add(noButton, 1,1);
      gridPane.add(yesButton,2,1);

      yesButton.setOnAction(e ->{
            if(yesButton.isSelected()){
            try{
            PrintWriter pw = new PrintWriter(new FileOutputStream(waterTemp, false));
            pw.print("yeswater");
            pw.close();
            System.out.println("printing yeswater to water.txt");
            } catch(IOException ex){}}
            });
      noButton.setOnAction(f ->{
            if(noButton.isSelected()){
            try{
            PrintWriter pw2 = new PrintWriter(new FileOutputStream(waterTemp, false));
            pw2.print("nowater");
            pw2.close();
            System.out.println("printing nowater to water.txt");
            } catch(IOException ex){}}
            });


      /*      6 tldr) loop through temporary directory files. Add radio buttons 
              as necessary, asking if the user has done whatever the radio button asks.
              If there is information already in the temporary files, (i.e. the user
              has added info in that current day, add it in a label above);

              7) If no, print necessary text to respective file (such as nowater), 
              so long as the first token for the scanner indicates that the user said no.

              7.5) if yes, create label, textfields, and add buttons in previous
              radio button rows and columns in the gridPane. If there is info already 
              submitted, (i.e. the user has added info in that current day, 
              add it in a label above).

              8) add event listeners to add buttons.the add button 
              will put the submitted info into a temporary HashMap, and print
              the HashMap to a ComboBox.If the item is selected in the ComboBox, 
              it will remove the item. if ComboBox is empty, remove it the ComboBox.
              Dynamically write HashMap to respective file, overwriting with a blank
              HashMap as necessary

              9) Once completed, insert Submit button at the bottom right. 

              9.5) Add a reset button that calls initialize() to clear all files 
              in the directory to enter everything in again.

              10) add event listener to Submit button, call archiveCurrentDay(), 
              then call initialize() to clear out the gui and start fresh. 
       */
   }


}
