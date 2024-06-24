import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    // Count Multiline comments
    private static int Count_Multiline_Comments = 0;

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

    public void Check_Count_Java_Files(File[] files) throws FileNotFoundException {

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
                    System.out.println("Java file found: " + file + "\n");
                    // I'm increasing the counter by 1
                    Count_Project_File_Java++;

                    try {
                        String Text = "";
                        Scanner scanner1 = new Scanner(file);

                        while(scanner1.hasNextLine()){
                            Text = Text + scanner1.nextLine() + "\n";
                        }

                        String Reg = "\\/\\*[A-zА-я0-9\\W][^\\n]+";
                        Pattern pattern = Pattern.compile(Reg);

                        Matcher matcher = pattern.matcher(Text);

                        while(matcher.find()){
                            Count_Multiline_Comments++;
                        }

                        Scanner scanner2 = new Scanner(file);

                        while(scanner2.hasNextLine()){

                            String Java_Str = scanner2.nextLine();
                            if(Java_Str.contains("//")){
                                Count_Str_Comment_JavaCode++;
                            }

                            Count_Str_JavaCode++;

                        }

                    } catch (FileNotFoundException e) {
                        System.out.println("There is no such file, or it cannot be found!\n");
                        throw new FileNotFoundException();
                    }

                    System.out.println("The amount of Java code in the file: " + file + " = " + Count_Str_JavaCode + "\n");
                    System.out.println("The number of comments in the Java code: " + file + " = " + Count_Str_Comment_JavaCode + "\n");
                    System.out.println("The number of multiline comments: " + file + " = " + Count_Multiline_Comments + "\n");
                    System.out.println("\n");

                    Count_Str_Comment_JavaCode = 0;
                    Count_Str_JavaCode = 0;
                    Count_Multiline_Comments = 0;

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
                    System.out.println("The class file was found: " + file + "\n");
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

                    assert file_resources != null;
                    for (File file1 : file_resources){
                        System.out.println("\nResource file found: " + file1 + "\n");
                        Count_Project_File_Resources++;
                    }

                }

                assert file_resources != null;
                Check_Resources_File(file_resources);

            }

        }

    }

}
