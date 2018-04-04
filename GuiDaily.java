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
import java.util.regex.*;
import javafx.scene.text.*;

public class GuiDaily extends Application{
   //TO-DO 
   /* make a "totals.txt" folder, where info for each file is stored.
    * loop through the files, find the file that matches the specific 
    * subject, and add it to the comboBox that displays all previously
    * entered information. Have the selection of a comboBox entry
    * fill up the textfield.
    */

   public static BorderPane mainPane = new BorderPane();
   public static GridPane gridPane = new GridPane();
   public static ScrollPane scrollPane = new ScrollPane();
   public static HBox titleBox = new HBox(30);
   public static String archiveFileStringName;
   public static Double studyDouble;

   public static String studyText;

   public static int yesStudyText = 0;
   public static int yesFoodText = 0;
   public static int yesExerciseText = 0;
   public static int yesNightroutineText = 0;

   public static int u = 0;

   public static String tempDate;

   public static TreeMap<String, Double> studyHashMap = new TreeMap<>();
   //studyHashMapTotals is a temporary hashmap that stores Total's data folder entries
   //and overwrites with added info upon submitting the initialize() day.
   public static TreeMap<String, Double> studyHashMapTotals = new TreeMap<>();
   //submitTemp is the day's hashMap to be submitted 
   public static TreeMap<String, Double> studyHashMapSubmitTemp = new TreeMap<>();

   //submitTotals is the updates total's HashMap that is to overwrite the totals file
   //these values are to be dealt with and finalized in archiveInitialize();
   //archiveInitialize() will be called by the submit button in initialize(), and every
   //totals will be updated to include temp's hashmaps, and then they are to overwrite totals
   //values. In addition, a new directory will be created for that day, and the 
   //temporary directory's contents will be written to that particular day.
   public static TreeMap<String, Double> studyHashMapSubmitTotals = new TreeMap<>();


   public static TreeMap<String, Double> foodHashMap = new TreeMap<>();
   public static TreeMap<String, Double> foodHashMapTotals = new TreeMap<>();

   public static TreeMap<String, Double> exerciseHashMap = new TreeMap<>();
   public static TreeMap<String, Double> exerciseHashMapTotals = new TreeMap<>();

   public static TreeMap<String, Double> nightHashMap = new TreeMap<>();
   public static TreeMap<String, Double> nightHashMapTotals = new TreeMap<>();


   @Override
      public void start(Stage primaryStage) throws IOException{

         dropDownInitialize();

         initialize();

         gridPane.setPrefSize(500,600);
         gridPane.setPadding(new Insets(40,40,40,40));
         gridPane.setHgap(5);
         gridPane.setVgap(15);

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

      File dataDir = new File("data");
      File[] dataDirFiles = dataDir.listFiles();


      ComboBox<String> cbo = new ComboBox<>();
      for(int i = 0; i < dataDirFiles.length; i++){
         String item = dataDirFiles[i].getName();
         if(!item.equals("temporary") && !item.equals(".DS_Store")
               && !item.equals("totals")){
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

         1) set mainPane center to null*/
      mainPane.setCenter(null);

      /*      2) add return button to current temporary date that calls initialize()
              for the current date written in the temporary file */
      Button returnInitialize = new Button("Return");



      /*    3) using the parameter's directory name, loop through the files in 
            the directory */


      /*      4) in the loop, get .txt filename. Use a scanner to read the file.
              The file will have information about whether to print the info or not. 
              close the scanner before continuing to loop */

   }

   public static void archiveCurrentDay() throws IOException{
      // navigate to temporary data directory (/data/temporary)

      //     1) Create a directory for current date
      File dataDir = new File("data");
      File currDir = new File(dataDir,tempDate);
      if(!currDir.exists()){
         currDir.mkdir();
      }
      File tempDir = new File(dataDir, "temporary");
      File totalsDir = new File(dataDir, "totals");
      File totalsWater = new File(totalsDir, "water.txt");
      File waterTemp = new File(tempDir, "water.txt");
      File submitWaterTemp = new File(currDir, "water.txt");
      if(!submitWaterTemp.exists()){
         submitWaterTemp.createNewFile();
      }
      Scanner scanner = new Scanner(waterTemp);
      PrintWriter pw = new PrintWriter(new FileOutputStream(submitWaterTemp, false));
      if(scanner.hasNext()){
         String next = scanner.next();
         if(next.equals("nowater")){
            pw.print("nowater");
            pw.close();
            scanner.close();
            scanner = new Scanner(totalsWater);
            if(!scanner.hasNext()){
               System.out.println("initializing 0..");
               pw = new PrintWriter(new FileOutputStream(totalsWater, false));

               pw.println(0);
               pw.close();
               scanner.close();
            }
            else{
               int entry = Integer.parseInt(scanner.next());
               {
                  int days = entry;
                  if(!scanner.hasNext()){
                     pw = new PrintWriter(new FileOutputStream(totalsWater, false));
                     pw.println(0);
                     pw.println(days);
                     pw.close();
                     scanner.close();
                  }

                  else{
                     int daysOld = Integer.parseInt(scanner.next());
                     if (days>=daysOld){
                        pw = new PrintWriter(new FileOutputStream(totalsWater, false));
                        pw.println(0);
                        pw.println(days);
                        pw.close();
                        scanner.close();
                        System.out.println("new longest streak is: " + days);
                     }
                     else if(days<daysOld){
                        pw = new PrintWriter(new FileOutputStream(totalsWater, false));
                        pw.println(0);
                        pw.println(daysOld);
                        pw.close();
                        scanner.close();
                        System.out.println("longest streak is still: " + daysOld);
                     }
                  }
               }
            }
         }
         else if(next.equals("yeswater")){
            pw.print("yeswater");
            pw.close();
            scanner.close();
            scanner = new Scanner(totalsWater);
            int day = 0;
            int recordDay = 0;
            if(scanner.hasNext()){
               day = Integer.parseInt(scanner.next());
               System.out.println("current day streak is: " + day);
            }
            if(scanner.hasNext()){
               recordDay = Integer.parseInt(scanner.next());
               System.out.println("current record streak is: " + recordDay);
            }

            if(day ==  0){
               pw = new PrintWriter(new FileOutputStream(totalsWater, false));

               pw.println(1);
               pw.println(recordDay);
               pw.close();
               scanner.close();
               System.out.println("beginning new streak...");
            }
            else if(day>0){
               day++;
               System.out.println("current streak: " + day);
               pw = new PrintWriter(new FileOutputStream(totalsWater, false));
               if(day >= recordDay){
                  System.out.println("new totals water.txt streak: " + day);

                  pw.println(day);
                  pw.println(day);
               }
               else if(day < recordDay){
                  System.out.println("totals water.txt streak remains: " + recordDay);

                  pw.println(day);
                  pw.println(recordDay);
               }
               pw.close();
               scanner.close();
            }
            else{
               pw = new PrintWriter(new FileOutputStream(totalsWater, false));
               day++;
               pw.println(day);
               pw.close();
               scanner.close();
            }
         }
         else{
            pw.close();
            scanner.close();
         }
      }


      File studyTemp = new File(tempDir, "study.txt");
      File submitStudyTemp = new File(currDir, "study.txt");
      if(!submitStudyTemp.exists()){
         submitStudyTemp.createNewFile();
      }
      pw = new PrintWriter(new FileOutputStream(submitStudyTemp, false));
      for(String key: studyHashMap.keySet()){
         Double keyValue = studyHashMap.get(key);
         if(studyHashMapTotals.containsKey(key)){
            Double totalKeyValue = studyHashMapTotals.get(key);
            studyHashMapTotals.put(key, keyValue+totalKeyValue);
         }
         else{
            studyHashMapTotals.put(key, keyValue);
         }
         pw.print(key + " ");
         pw.println(studyHashMap.get(key));
      }
      pw.close();
      scanner.close();
      File totalsStudy = new File(totalsDir, "study.txt");
      pw = new PrintWriter(new FileOutputStream(totalsStudy, false));
      for(String key: studyHashMapTotals.keySet()){
         pw.print(key + " ");
         pw.println(studyHashMapTotals.get(key));
      }
      pw.close();


      File foodTemp = new File(tempDir, "fastfood.txt");
      File submitFoodTemp = new File(currDir, "fastfood.txt");
      if(!submitFoodTemp.exists()){
         submitFoodTemp.createNewFile();
      }
      pw = new PrintWriter(new FileOutputStream(submitFoodTemp, false));
      for(String key:foodHashMap.keySet()){
         Double keyValue = foodHashMap.get(key);
         if(foodHashMapTotals.containsKey(key)){
            Double totalKeyValue = foodHashMapTotals.get(key);
            foodHashMapTotals.put(key, keyValue+totalKeyValue);
         }
         else{
            foodHashMapTotals.put(key, keyValue);
         }
         pw.print(key + " ");
         pw.println(foodHashMap.get(key));
      }
      pw.close();
      scanner.close();
      File totalsFood = new File(totalsDir, "fastfood.txt");
      pw = new PrintWriter(new FileOutputStream(totalsFood, false));
      for(String key: foodHashMapTotals.keySet()){
         pw.print(key + " ");
         pw.println(foodHashMapTotals.get(key));
      }
      pw.close();


      File exerciseTemp = new File(tempDir, "exercise.txt");
      File submitExerciseTemp = new File(currDir, "exercise.txt");
      if(!submitExerciseTemp.exists()){
         submitExerciseTemp.createNewFile();
      }
      pw = new PrintWriter(new FileOutputStream(submitExerciseTemp, false));
      for(String key:exerciseHashMap.keySet()){
         Double keyValue = exerciseHashMap.get(key);
         if(exerciseHashMapTotals.containsKey(key)){
            Double totalKeyValue = exerciseHashMapTotals.get(key);
            exerciseHashMapTotals.put(key, keyValue+totalKeyValue);
         }
         else{
            exerciseHashMapTotals.put(key, keyValue);
         }
         pw.print(key + " ");
         pw.println(exerciseHashMap.get(key));
      }
      pw.close();
      scanner.close();
      File totalsExercise = new File(totalsDir, "exercise.txt");
      pw = new PrintWriter(new FileOutputStream(totalsExercise, false));
      for(String key: exerciseHashMapTotals.keySet()){
         pw.print(key + " ");
         pw.println(exerciseHashMapTotals.get(key));
      }
      pw.close();


      File nightRoutineTemp = new File(tempDir, "nightroutine.txt");
      File submitNRTemp = new File(currDir, "nightroutine.txt");
      File totalsNR = new File(totalsDir, "nightroutine.txt");
      scanner = new Scanner(nightRoutineTemp);
      pw = new PrintWriter(new FileOutputStream(submitNRTemp, false));
      if(scanner.hasNext()){
         String next = scanner.next();
         if(next.equals("nonightroutine")){
            pw.print("nonightroutine");
            pw.close();
            scanner.close();
            scanner = new Scanner(totalsNR);
            if(!scanner.hasNext()){
               System.out.println("initializing 0..");
               pw = new PrintWriter(new FileOutputStream(totalsNR, false));

               pw.println(0);
               pw.close();
               scanner.close();
            }
            else{
               int entry = Integer.parseInt(scanner.next());
               {
                  int days = entry;
                  if(!scanner.hasNext()){
                     pw = new PrintWriter(new FileOutputStream(totalsNR, false));
                     pw.println(0);
                     pw.println(days);
                     pw.close();
                     scanner.close();
                  }

                  else{
                     int daysOld = Integer.parseInt(scanner.next());
                     if (days>=daysOld){
                        pw = new PrintWriter(new FileOutputStream(totalsNR, false));
                        pw.println(0);
                        pw.println(days);
                        pw.close();
                        scanner.close();
                        System.out.println("new longest streak is: " + days);
                     }
                     else if(days<daysOld){
                        pw = new PrintWriter(new FileOutputStream(totalsNR, false));
                        pw.println(0);
                        pw.println(daysOld);
                        pw.close();
                        scanner.close();
                        System.out.println("longest streak is still: " + daysOld);
                     }
                  }
               }
            }
         }
         else if(next.equals("yesnightroutine")){
            pw.print("yesnightroutine");
            pw.close();
            scanner.close();
            scanner = new Scanner(totalsNR);
            int day = 0;
            int recordDay = 0;
            if(scanner.hasNext()){
               day = Integer.parseInt(scanner.next());
               System.out.println("current day streak is: " + day);
            }
            if(scanner.hasNext()){
               recordDay = Integer.parseInt(scanner.next());
               System.out.println("current record streak is: " + recordDay);
            }

            if(day ==  0){
               pw = new PrintWriter(new FileOutputStream(totalsNR, false));

               pw.println(1);
               pw.println(recordDay);
               pw.close();
               scanner.close();
               System.out.println("beginning new streak...");
            }
            else if(day>0){
               day++;
               System.out.println("current streak: " + day);
               pw = new PrintWriter(new FileOutputStream(totalsNR, false));
               if(day >= recordDay){
                  System.out.println("new totals nightroutine.txt streak: " + day);

                  pw.println(day);
                  pw.println(day);
               }
               else if(day < recordDay){
                  System.out.println("totals nightroutine.txt streak remains: " + recordDay);

                  pw.println(day);
                  pw.println(recordDay);
               }
               pw.close();
               scanner.close();
            }
            else{
               pw = new PrintWriter(new FileOutputStream(totalsNR, false));
               day++;
               pw.println(day);
               pw.close();
               scanner.close();
            }
         }
         else{
            pw.close();
            scanner.close();
         }
      }
      //   initialize();


   }

   public static void initialize() throws IOException{
      /* This method will clear all current printwriters, scanners, etc,
         to make way for fresh input
      //       1) clear temporary file directory(MAYBE NOT);

      2) add files to the temporary directory(water.txt, study.txt, etc)
      2.5) if the file is already there, do not overwrite */

      File dataDir = new File("data");
      File tempDir = new File(dataDir, "temporary");
      File totalsDir = new File(dataDir, "totals");

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
      tempDate = "" + today.getMonth() + ":" + today.getDay() + ":" + today.getYear();
      Label titleText = new Label("Today: " + tempDate);
      System.out.println(tempDate);
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

      //////////////////////////////////////////////////////////////////////////////

      //WATER.TXT 

      Label waterLabel = new Label("Did you drink a gallon of water?");
      gridPane.add(waterLabel,0,1);

      RadioButton noButton = new RadioButton("No");
      RadioButton yesButton = new RadioButton("Yes");
      ToggleGroup group = new ToggleGroup();
      noButton.setToggleGroup(group);
      //     noButton.setSelected(true);
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

      //END OF WATER.TXT

      ////////////////////////////////////////////////////////////////////////////////

      //STUDY.TXT
      Label studyLabel = new Label("Did you study today?");
      gridPane.add(studyLabel, 0, 3);

      RadioButton noButtonStudy = new RadioButton("No");
      RadioButton yesButtonStudy = new RadioButton("Yes");
      ToggleGroup groupStudy = new ToggleGroup();
      noButtonStudy.setToggleGroup(groupStudy);
      yesButtonStudy.setToggleGroup(groupStudy);
      gridPane.add(noButtonStudy, 1,3);
      gridPane.add(yesButtonStudy,2,3);

      HBox studyBox = new HBox();

      Label studyLabelAdd = new Label("Enter Subject");
      gridPane.add(studyLabelAdd, 0, 4);
      TextField studyLabelAddTF = new TextField();
      studyLabelAddTF.setPrefWidth(175);
      studyLabelAddTF.setDisable(true);
      studyBox.getChildren().add(studyLabelAddTF);


      File totalsStudyFile = new File(totalsDir, "study.txt");
      Scanner studyScanner = new Scanner(totalsStudyFile);
      if(!studyScanner.hasNext()){
         System.out.println("study.txt in totals dir is empty");
      }
      while(studyScanner.hasNext()){
         studyHashMapTotals.put(studyScanner.next(), studyScanner.nextDouble());
      }
      studyScanner.close();

      ComboBox<String> cbo3 = new ComboBox<>();
      //loop through totals file, get all string entries for study subjects
      for(String key:studyHashMapTotals.keySet()){
         String first = key;
         cbo3.getItems().add(first);
      }

      cbo3.setValue("");
      cbo3.setPrefWidth(10);

      cbo3.setOnAction( e ->{
            studyLabelAddTF.setText(cbo3.getValue());
            });

      cbo3.setVisibleRowCount(5);
      cbo3.setDisable(true);

      studyBox.getChildren().add(cbo3);
      gridPane.add(studyBox, 0, 5);

      Label studyHoursAdd = new Label("# Hours");
      TextField studyHoursAddTF = new TextField();

      studyHoursAddTF.setDisable(true);
      studyHoursAddTF.setPrefWidth(60);
      gridPane.add(studyHoursAdd, 1, 4);
      gridPane.add(studyHoursAddTF, 1, 5);

      HBox addHBox = new HBox();

      Button addStudy = new Button("Add");
      addHBox.getChildren().add(addStudy);
      gridPane.add(addHBox, 2, 5);
      addStudy.setDisable(true);
      studyHoursAdd.setDisable(true);
      studyLabelAdd.setDisable(true);

      Label removeStudyEntry = new Label("Select to\nremove entry");
      removeStudyEntry.setWrapText(true);
      gridPane.add(removeStudyEntry, 2,4);
      removeStudyEntry.setVisible(false);

      yesButtonStudy.setOnAction(f ->{
            studyHoursAddTF.setDisable(false);
            studyLabelAddTF.setDisable(false);
            addStudy.setDisable(false);
            studyHoursAdd.setDisable(false);
            studyLabelAdd.setDisable(false);
            // cbo2.setDisable(false);
            cbo3.setDisable(false);
            // cbo3.setVisible(false);
            if(yesStudyText > 0){
            removeStudyEntry.setVisible(true);
            }
            removeStudyEntry.setDisable(false);
            addHBox.setVisible(true);
            });
      noButtonStudy.setOnAction(f ->{
            studyHoursAddTF.setDisable(true);
            studyHoursAdd.setDisable(true);
            studyLabelAdd.setDisable(true);
            studyLabelAddTF.setDisable(true);
            addStudy.setDisable(true);
            //  cbo2.setDisable(true);
            cbo3.setDisable(true);
            studyHoursAddTF.clear();
            studyLabelAddTF.clear();
            removeStudyEntry.setVisible(false);
            removeStudyEntry.setDisable(true);
            addHBox.setVisible(false);
            });

      addStudy.setOnAction(g ->{
            Pattern patternCheck = Pattern.compile("\\s");
            Matcher matcherCheck = patternCheck.matcher(studyLabelAddTF.getText());
            int i = 0;
            String studyText = "";
            Double studyDouble = 0.0;

            if((studyHoursAddTF.getText().length() == 0)  
                  || (studyLabelAddTF.getText().length() == 0)){
            return;
            }

            removeStudyEntry.setVisible(true);
            yesStudyText++;

            addHBox.getChildren().clear();
            addHBox.getChildren().add(addStudy);

            if((!matcherCheck.find()  && 
                     //  (studyLabelAddTF.getText().chars().allMatch(Character::isLetter)
                     //  || studyLabelAddTF.getText().chars().allMatch(Character::isDigit)) && 
                     (studyLabelAddTF.getText() != "") && (studyLabelAddTF.getText() != null))){
               studyText = studyLabelAddTF.getText();
               if(studyText != null && studyText.length() >= 1){
                  System.out.println("add " + studyText + " to hashmap");
               }
               i++;
            }
            else{
               studyLabelAddTF.setText("Please enter valid string");
               studyLabelAddTF.requestFocus();
            }

            try{
               studyDouble = Double.parseDouble(studyHoursAddTF.getText());
               i++;
               if(i == 2){
                  studyHoursAddTF.clear();
                  studyLabelAddTF.clear();
               }

            }
            catch(NumberFormatException ex){
               try{
                  String studyDoubleString = String.valueOf(studyHoursAddTF.getText());
                  studyDoubleString = studyDoubleString + ".0";
                  studyDouble = Double.parseDouble(studyDoubleString);
                  i++;
                  if(i == 2){
                     //   studyHashMap.put(studyText, studyDouble);
                     studyHoursAddTF.clear();
                     studyLabelAddTF.clear();
                  }


               }
               catch(NumberFormatException ex2){
                  studyHoursAddTF.setText("invalid");
                  studyHoursAddTF.requestFocus();
               }
            }
            if(i == 2 && (studyDouble!= 0.0) && (!studyText.equals("")) && 
                  (studyText.length() >= 1) && (studyDouble!= 0)){
               studyHashMap.put(studyText, studyDouble);
            }
            for(String key : studyHashMap.keySet()){
               System.out.print(key + " ");
               System.out.println(studyHashMap.get(key));
            }

            ComboBox<String> cbo2 = new ComboBox<>();
            for(String key:studyHashMap.keySet()){
               String first = key;
               String second = String.valueOf(studyHashMap.get(key));
               String entry = first + " " + second;
               cbo2.getItems().add(entry);
            }

            cbo2.setValue("");
            cbo2.setPrefWidth(10);

            if(cbo2.getItems().size() > 0){

               cbo2.setOnAction( e ->{
                     try{
                     String hashEntry = (cbo2.getValue());
                     Scanner scanner = new Scanner(hashEntry);

                     while(scanner.hasNext()){
                     String scannerFirst = scanner.next();
                     studyHashMap.remove(scannerFirst);
                     if(scanner.hasNextDouble()){
                     Double scannerSecond = scanner.nextDouble();
                     }
                     }
                     scanner.close();

                     addHBox.getChildren().remove(cbo2);
                     addHBox.getChildren().add(cbo2);
                     System.out.println("New studyHashMap: ");
                     //         for(String key : studyHashMap.keySet()){
                     //          System.out.print((key + " "));
                     //        System.out.println((studyHashMap.get(key)));
                     //   }
                     }catch(NullPointerException ex){}

               });
            }

            cbo2.setVisibleRowCount(5);

            addHBox.getChildren().add(cbo2);

            for(String key: studyHashMap.keySet()){
               studyHashMapSubmitTemp.put(key, studyHashMap.get(key));
            }
            System.out.println("The temp hashmap to be submitted to study.txt is: ");
            for(String key:studyHashMap.keySet()){
               System.out.print(key + " ");
               System.out.println(studyHashMap.get(key));
            }
            System.out.println("The total hashmap is: ");
            for(String key:studyHashMapTotals.keySet()){
               System.out.print(key + " ");
               System.out.println(studyHashMapTotals.get(key));
            }

      }); 

      ///////////////////////////////////////////////////////////////////////////
      //FASTFOOD.TXT
      Label foodLabel = new Label("Did you eat fast food today?");
      foodLabel.setStyle("-fx-font-weight: bold");
      gridPane.add(foodLabel, 0, 6);

      RadioButton noButtonFood = new RadioButton("No");
      RadioButton yesButtonFood = new RadioButton("Yes");
      ToggleGroup groupFood = new ToggleGroup();
      noButtonFood.setToggleGroup(groupFood);
      yesButtonFood.setToggleGroup(groupFood);
      gridPane.add(noButtonFood, 1, 6);
      gridPane.add(yesButtonFood, 2, 6);

      HBox foodBox = new HBox();

      Label foodLabelAdd = new Label("Enter Restaurant");
      gridPane.add(foodLabelAdd, 0, 7);
      TextField foodLabelAddTF = new TextField();
      foodLabelAddTF.setMaxWidth(175);
      foodLabelAddTF.setDisable(true);
      foodBox.getChildren().add(foodLabelAddTF);

      File totalsFoodFile = new File(totalsDir, "fastfood.txt");
      Scanner foodScanner = new Scanner(totalsFoodFile);
      if(!foodScanner.hasNext()){
         System.out.println("fastfood.txt in totals dir is empty");
      }
      while(foodScanner.hasNext()){
         foodHashMapTotals.put(foodScanner.next(), foodScanner.nextDouble());
      }
      foodScanner.close();

      ComboBox<String> cbFood = new ComboBox<>();
      for(String key:foodHashMapTotals.keySet()){
         String first = key;
         cbFood.getItems().add(first);
      }
      cbFood.setValue("");
      cbFood.setMaxWidth(1);
      cbFood.setOnAction( e->{
            foodLabelAddTF.setText(cbFood.getValue());
            });
      cbFood.setVisibleRowCount(5);
      cbFood.setDisable(true);
      foodBox.getChildren().add(cbFood);
      gridPane.add(foodBox, 0, 8);

      Label foodCostAdd = new Label("$ Spent\n($0.00)");
      //  Label foodCostAdd = new Label("$ Spent");

      TextField foodCostAddTF = new TextField();

      foodCostAddTF.setDisable(true);
      foodCostAddTF.setMaxWidth(60);
      gridPane.add(foodCostAdd,1,7);
      gridPane.add(foodCostAddTF, 1, 8);

      HBox addFoodHBox = new HBox();

      Button addFood = new Button("Add");
      addFoodHBox.getChildren().add(addFood);
      gridPane.add(addFoodHBox,2,8);
      addFood.setDisable(true);
      foodCostAdd.setDisable(true);
      foodLabelAdd.setDisable(true);

      Label removeFoodEntry = new Label("Select to\nremove entry");
      // Label removeFoodEntry = new Label("");

      removeFoodEntry.setWrapText(true);
      gridPane.add(removeFoodEntry, 2, 7);
      removeFoodEntry.setVisible(false);

      yesButtonFood.setOnAction(e->{
            foodCostAddTF.setDisable(false);
            foodLabelAddTF.setDisable(false);
            addFood.setDisable(false);
            foodCostAdd.setDisable(false);
            foodLabelAdd.setDisable(false);
            cbFood.setDisable(false);
            if(yesFoodText > 0){
            removeFoodEntry.setVisible(true);
            }
            removeFoodEntry.setVisible(false);
            addFoodHBox.setVisible(true);
            });
      noButtonFood.setOnAction(e->{
            foodCostAddTF.setDisable(true);
            foodLabelAddTF.setDisable(true);
            addFood.setDisable(true);
            foodCostAdd.setDisable(true);
            foodLabelAdd.setDisable(true);
            cbFood.setDisable(true);
            removeFoodEntry.setVisible(false);
            removeFoodEntry.setDisable(true);
            addFoodHBox.setVisible(false);
            foodCostAddTF.clear();
            foodLabelAddTF.clear();
            });

      addFood.setOnAction(e->{
            Pattern patternCheck = Pattern.compile("\\s");
            Matcher matcherCheck = patternCheck.matcher(foodLabelAddTF.getText());
            int i = 0;
            String foodText = "";
            Double foodDouble = 0.0;

            if((foodCostAddTF.getText().length() == 0) 
                  || (foodLabelAddTF.getText().length() == 0)){
            return;
            }
            removeFoodEntry.setVisible(true);
            yesFoodText++;
            addFoodHBox.getChildren().clear();
            addFoodHBox.getChildren().add(addFood);

            if((!matcherCheck.find() && (foodLabelAddTF.getText() != "")
                     && (foodLabelAddTF.getText() != null))){
            foodText = foodLabelAddTF.getText();
            if(foodText != null && foodText.length() >= 1){
            System.out.println("Add " + foodText + " to hashmap");
            }
            i++;
            }
            else{
               foodLabelAddTF.setText("Please enter valid string");
               foodLabelAddTF.requestFocus();
            }

            try{
               foodDouble = Double.parseDouble(foodCostAddTF.getText());
               i++;
               if(i==2){
                  foodCostAddTF.clear();
                  foodLabelAddTF.clear();
               }
            }
            catch(NumberFormatException ex){
               try{
                  String foodDoubleString = String.valueOf(foodCostAddTF.getText());
                  foodDoubleString = foodDoubleString + ".00";
                  foodDouble = Double.parseDouble(foodDoubleString);
                  i++;
                  if(i==2){
                     foodCostAddTF.clear();
                     foodLabelAddTF.clear();
                  }
               }
               catch(NumberFormatException ex2){
                  foodCostAddTF.setText("invalid");
                  foodCostAddTF.requestFocus();
               }
            }
            if(i==2 && (foodDouble!=0.0) && (!foodText.equals("")) &&
                  (foodText.length() >= 1) && (foodDouble!= 0)){
               foodHashMap.put(foodText, foodDouble);
            }
            for(String key: foodHashMap.keySet()){
               System.out.print(key + " ");
               System.out.println(foodHashMap.get(key));
            }

            ComboBox<String> cbFood2 = new ComboBox<>();
            for(String key:foodHashMap.keySet()){
               String first = key;
               String second = String.valueOf(foodHashMap.get(key));
               String entry = first + " " + second;
               cbFood2.getItems().add(entry);
            }

            cbFood2.setValue("");
            cbFood2.setMaxWidth(1);

            if(cbFood2.getItems().size() > 0){
               cbFood2.setOnAction(f->{
                     try{
                     String hashEntry = (cbFood2.getValue());
                     Scanner scanner = new Scanner(hashEntry);
                     while(scanner.hasNext()){
                     String scannerFirst = scanner.next();
                     foodHashMap.remove(scannerFirst);
                     if(scanner.hasNextDouble()){
                     Double scannerSecond = scanner.nextDouble();
                     }
                     }
                     scanner.close();
                     addFoodHBox.getChildren().remove(cbFood2);
                     addFoodHBox.getChildren().add(cbFood2);
                     }catch(NullPointerException ex){}
                     });
            }
            cbFood2.setVisibleRowCount(5);
            addFoodHBox.getChildren().add(cbFood2);

            System.out.println("The temp hashmap to be submitted to fastfood.txt is: ");
            for(String key:foodHashMap.keySet()){
               System.out.print(key + " ");
               System.out.println(foodHashMap.get(key));
            }
            System.out.println("the total hashmap for fastfood is: ");
            for(String key: foodHashMapTotals.keySet()){
               System.out.print(key + " ");
               System.out.println(foodHashMapTotals.get(key));
            }
      });





      ///////////////////////////////////////////////////////////////////////////
      //EXERCISE.TXT
      Label exerciseLabel = new Label("Did you exercise today?");
      exerciseLabel.setStyle("-fx-font-weight: bold");
      gridPane.add(exerciseLabel, 0, 9);

      RadioButton noButtonExercise = new RadioButton("No");
      RadioButton yesButtonExercise = new RadioButton("Yes");
      ToggleGroup groupExercise = new ToggleGroup();
      noButtonExercise.setToggleGroup(groupExercise);
      yesButtonExercise.setToggleGroup(groupExercise);
      gridPane.add(noButtonExercise, 1, 9);
      gridPane.add(yesButtonExercise, 2, 9);

      HBox exerciseBox = new HBox();

      Label exerciseLabelAdd = new Label("Enter Exercise");
      gridPane.add(exerciseLabelAdd, 0, 10);
      TextField exerciseLabelAddTF = new TextField();
      exerciseLabelAddTF.setMaxWidth(175);
      exerciseLabelAddTF.setDisable(true);
      exerciseBox.getChildren().add(exerciseLabelAddTF);

      File totalsExerciseFile = new File(totalsDir, "exercise.txt");
      Scanner exerciseScanner = new Scanner(totalsExerciseFile);
      if(!exerciseScanner.hasNext()){
         System.out.println("exercise.txt in totals dir is empty");
      }
      while(exerciseScanner.hasNext()){
         exerciseHashMapTotals.put(exerciseScanner.next(), exerciseScanner.nextDouble());
      }
      exerciseScanner.close();

      ComboBox<String> cbExercise = new ComboBox<>();
      for(String key:exerciseHashMapTotals.keySet()){
         String first = key;
         cbExercise.getItems().add(first);
      }
      cbExercise.setValue("");
      cbExercise.setPrefWidth(10);
      cbExercise.setOnAction( e->{
            exerciseLabelAddTF.setText(cbExercise.getValue());
            });
      cbExercise.setVisibleRowCount(5);
      cbExercise.setDisable(true);
      exerciseBox.getChildren().add(cbExercise);
      gridPane.add(exerciseBox, 0, 11);

      Label exerciseHoursAdd = new Label("#Mins/\n#Miles");
      TextField exerciseHoursAddTF = new TextField();

      exerciseHoursAddTF.setDisable(true);
      exerciseHoursAddTF.setMaxWidth(60);
      gridPane.add(exerciseHoursAdd,1,10);
      gridPane.add(exerciseHoursAddTF, 1, 11);

      HBox addExerciseHBox = new HBox();

      Button addExercise = new Button("Add");
      addExerciseHBox.getChildren().add(addExercise);
      gridPane.add(addExerciseHBox,2,11);
      addExercise.setDisable(true);
      exerciseHoursAdd.setDisable(true);
      exerciseLabelAdd.setDisable(true);

      Label removeExerciseEntry = new Label("Select to\nremove entry");
      //  Label removeExerciseEntry = new Label("");


      //  removeExerciseEntry.setMaxWidth(30);
      removeExerciseEntry.setWrapText(true);
      gridPane.add(removeExerciseEntry, 2, 10);
      removeExerciseEntry.setVisible(false);

      yesButtonExercise.setOnAction(e->{
            exerciseHoursAddTF.setDisable(false);
            exerciseLabelAddTF.setDisable(false);
            addExercise.setDisable(false);
            exerciseHoursAdd.setDisable(false);
            exerciseLabelAdd.setDisable(false);
            cbExercise.setDisable(false);
            if(yesExerciseText > 0){
            removeExerciseEntry.setVisible(true);
            }
            removeExerciseEntry.setVisible(false);
            addExerciseHBox.setVisible(true);
            });
      noButtonExercise.setOnAction(e->{
            exerciseHoursAddTF.setDisable(true);
            exerciseLabelAddTF.setDisable(true);
            addExercise.setDisable(true);
            exerciseHoursAdd.setDisable(true);
            exerciseLabelAdd.setDisable(true);
            cbExercise.setDisable(true);
            removeExerciseEntry.setVisible(false);
            removeExerciseEntry.setDisable(true);
            addExerciseHBox.setVisible(false);
            exerciseHoursAddTF.clear();
            exerciseLabelAddTF.clear();
            });

      addExercise.setOnAction(e->{
            Pattern patternCheck = Pattern.compile("\\s");
            Matcher matcherCheck = patternCheck.matcher(exerciseLabelAddTF.getText());
            int i = 0;
            String exerciseText = "";
            Double exerciseDouble = 0.0;

            if((exerciseHoursAddTF.getText().length() == 0) 
                  || (exerciseLabelAddTF.getText().length() == 0)){
            return;
            }
            removeExerciseEntry.setVisible(true);
            yesExerciseText++;
            addExerciseHBox.getChildren().clear();
            addExerciseHBox.getChildren().add(addExercise);

            if((!matcherCheck.find() && (exerciseLabelAddTF.getText() != "")
                     && (exerciseLabelAddTF.getText() != null))){
            exerciseText = exerciseLabelAddTF.getText();
            if(exerciseText != null && exerciseText.length() >= 1){
            System.out.println("Add " + exerciseText + " to hashmap");
            }
            i++;
            }
            else{
               exerciseLabelAddTF.setText("Please enter valid string");
               exerciseLabelAddTF.requestFocus();
            }

            try{
               exerciseDouble = Double.parseDouble(exerciseHoursAddTF.getText());
               i++;
               if(i==2){
                  exerciseHoursAddTF.clear();
                  exerciseLabelAddTF.clear();
               }
            }
            catch(NumberFormatException ex){
               try{
                  String exerciseDoubleString = String.valueOf(exerciseHoursAddTF.getText());
                  exerciseDoubleString = exerciseDoubleString + ".00";
                  exerciseDouble = Double.parseDouble(exerciseDoubleString);
                  i++;
                  if(i==2){
                     exerciseHoursAddTF.clear();
                     exerciseLabelAddTF.clear();
                  }
               }
               catch(NumberFormatException ex2){
                  exerciseHoursAddTF.setText("invalid");
                  exerciseHoursAddTF.requestFocus();
               }
            }
            if(i==2 && (exerciseDouble!=0.0) && (!exerciseText.equals("")) &&
                  (exerciseText.length() >= 1) && (exerciseDouble!= 0)){
               exerciseHashMap.put(exerciseText, exerciseDouble);
            }
            for(String key: exerciseHashMap.keySet()){
               System.out.print(key + " ");
               System.out.println(exerciseHashMap.get(key));
            }

            ComboBox<String> cbExercise2 = new ComboBox<>();
            for(String key:exerciseHashMap.keySet()){
               String first = key;
               String second = String.valueOf(exerciseHashMap.get(key));
               String entry = first + " " + second;
               cbExercise2.getItems().add(entry);
            }

            cbExercise2.setValue("");
            cbExercise2.setPrefWidth(10);

            if(cbExercise2.getItems().size() > 0){
               cbExercise2.setOnAction(f->{
                     try{
                     String hashEntry = (cbExercise2.getValue());
                     Scanner scanner = new Scanner(hashEntry);
                     while(scanner.hasNext()){
                     String scannerFirst = scanner.next();
                     exerciseHashMap.remove(scannerFirst);
                     if(scanner.hasNextDouble()){
                     Double scannerSecond = scanner.nextDouble();
                     }
                     }
                     scanner.close();
                     addExerciseHBox.getChildren().remove(cbExercise2);
                     addExerciseHBox.getChildren().add(cbExercise2);
                     }catch(NullPointerException ex){}
                     });
            }
            cbExercise2.setVisibleRowCount(5);
            addExerciseHBox.getChildren().add(cbExercise2);

            System.out.println("The temp hashmap to be submitted to exercise.txt is: ");
            for(String key:exerciseHashMap.keySet()){
               System.out.print(key + " ");
               System.out.println(exerciseHashMap.get(key));
            }
            System.out.println("the total hashmap for exercise is: ");
            for(String key: exerciseHashMapTotals.keySet()){
               System.out.print(key + " ");
               System.out.println(exerciseHashMapTotals.get(key));
            }
      }); 




      ///////////////////////////////////////////////////////////////////////////
      //NIGHTROUTINE.TXT

      Label nightRoutineLabel = new Label("Did you brush,floss, and wash your face?");
      gridPane.add(nightRoutineLabel,0,12);

      RadioButton noButtonNR = new RadioButton("No");
      RadioButton yesButtonNR = new RadioButton("Yes");
      ToggleGroup groupNR = new ToggleGroup();
      noButtonNR.setToggleGroup(groupNR);
      //     noButton.setSelected(true);
      yesButtonNR.setToggleGroup(groupNR);
      gridPane.add(noButtonNR, 1,12);
      gridPane.add(yesButtonNR,2,12);

      yesButtonNR.setOnAction(e ->{
            if(yesButtonNR.isSelected()){
            try{
            PrintWriter pw = new PrintWriter(new FileOutputStream(nightRoutineTemp, false));
            pw.print("yesnightroutine");
            pw.close();
            System.out.println("printing yesnightroutine to nightroutine.txt");
            } catch(IOException ex){}}
            });
      noButtonNR.setOnAction(f ->{
            if(noButtonNR.isSelected()){
            try{
            PrintWriter pw2 = new PrintWriter(new FileOutputStream(nightRoutineTemp, false));
            pw2.print("nonightroutine");
            pw2.close();
            System.out.println("printing nonightroutine to nightroutine.txt");
            } catch(IOException ex){}}
            });




      ///////////////////////////////////////////////////////////////////////////
      //SUBMIT BUTTON
      Button submitButtonPreConfirm = new Button("Submit Day");
      gridPane.add(submitButtonPreConfirm, 2, 14);
      //  int u = 0;

      submitButtonPreConfirm.setOnAction(e->{

            if(group.getSelectedToggle()!=null && groupStudy.getSelectedToggle()!=null 
                  && groupFood.getSelectedToggle()!=null && 
                  groupExercise.getSelectedToggle()!=null
                  && groupNR.getSelectedToggle()!=null && u==0){

            gridPane.getChildren().remove(submitButtonPreConfirm);
            Button submitButton = new Button("Are you\nsure?");
            gridPane.add(submitButton, 2, 14);
            submitButton.setOnAction(f->{
                  try{

                  if(group.getSelectedToggle()!=null && groupStudy.getSelectedToggle()!=null 
                        && groupFood.getSelectedToggle()!=null && 
                        groupExercise.getSelectedToggle()!=null
                        && groupNR.getSelectedToggle()!=null && u==0){
                  submitButton.setDisable(false);
                  System.out.println("archiveCurrentDay();");
                  u++;
                  archiveCurrentDay();
                  }
                  }
                  catch(IOException ex){}});
            }
      });


      ///////////////////////////////////////////////////////////////////////////

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
