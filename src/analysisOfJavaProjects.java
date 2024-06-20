/*
Hint on how to use class analysisOfJavaProjects

// We get full access to our Kornegovsky catologist
String currentPatch = new java.io.File(".").getCanonicalPath();

// Print the full path to the root cataloger
System.out.println("\nОткрываю корневой котолог: " + currentPatch + "\n");
System.out.print("\n");

// We receive a file with this name
File dir = new File(currentPatch);

// Creating an array of files, directories, and folders
File[] List = dir.listFiles();

// Creating a class
analysisOfJavaProjects analysisOfJavaProjects = new analysisOfJavaProjects();

// Using the Check_Count_Java_Files method
analysisOfJavaProjects.Check_Count_Java_Files(List);

// Using the Check_Files_Class method
analysisOfJavaProjects.Check_Files_Class(List);

// Using the Check_Resources_File method
analysisOfJavaProjects.Check_Resources_File(List);

*/


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class analysisOfJavaProjects {

    // Count Java files
    private static int Count_Project_File_Java = 0;

    // Count resources file;
    private static int Count_Project_File_Resources = 0;

    // Count class
    private static int Count_Project_Class = 0;

    // Count Java code
    private static int Count_Str_JavaCode = 0;

    // Count Comment
    private static int Count_Str_Comment_JavaCode = 0;

    // Add separator (/) or (\)
    String separator = File.separator;

    // Getter - return count java files
    public int getCount_Project_File_Java(){
        return Count_Project_File_Java;
    }

    // Getter - return count resources file
    public int getCount_Project_File_Resources() {
        return Count_Project_File_Resources;
    }

    // Getter - return count class
    public int getCount_Project_Class(){
        return Count_Project_Class;
    }

    public void Check_Count_Java_Files(File[] files){

        for(File file : files){

            if(file.isDirectory()){

                // I take a directory (folder) and divide it into a list of files.
                File Directory = new File(String.valueOf(file));
                File[] Content_Directory = Directory.listFiles();

                // Checking that the directory is not empty
                assert Content_Directory != null;
                // I use recursion
                Check_Count_Java_Files(Content_Directory);

            }
            else{

                // I divide the file line by a point to find the extension
                String[] Split = String.valueOf(file).split("\\.");

                // If the extension .java is fulfilling the condition
                if(Split[Split.length-1].equals("java")){
                    // Message to the user
                    System.out.println("Найден джава файл: " + file + "\n");
                    // I'm increasing the counter by 1
                    Count_Project_File_Java++;

                    try {
                        Scanner scanner = new Scanner(file);

                        while(scanner.hasNextLine()){

                            String Java_Str = scanner.nextLine();
                            if(Java_Str.contains("//")){
                                Count_Str_Comment_JavaCode++;
                            }
                            else{
                                Count_Str_JavaCode++;
                            }

                        }

                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println("Количество Java кода в файле: " + file + " = " + Count_Str_JavaCode + "\n");
                    System.out.println("Количество коминтариев в Java коде: " + file + " = " + Count_Str_Comment_JavaCode + "\n");
                    System.out.println("\n");

                    Count_Str_Comment_JavaCode = 0;
                    Count_Str_JavaCode = 0;

                }

            }

        }

    }

    public void Check_Files_Class(File[] files) {

        for (File file : files) {

            if (file.isDirectory()) {

                File Directory = new File(String.valueOf(file));
                File[] Content_Directory = Directory.listFiles();

                assert Content_Directory != null;
                Check_Files_Class(Content_Directory);

            } else {

                String[] Split = String.valueOf(file).split("\\.");

                if (Split[Split.length - 1].equals("class")) {
                    System.out.println("Найден файл класс: " + file + "\n");
                    Count_Project_Class++;

                }
            }
        }
    }

    public void Check_Resources_File(File[] files){

        for(File file : files){

            File Directory = new File(String.valueOf(file));
            File[] file_resources = Directory.listFiles();

            if(file.isDirectory()){

                if(String.valueOf(file).contains("resources")){

                    for (File file1 : file_resources){
                        System.out.println("Найден файл ресурсов: " + file1 + "\n");
                        Count_Project_File_Resources++;
                    }

                }

                Check_Resources_File(file_resources);

            }

        }

    }

}
