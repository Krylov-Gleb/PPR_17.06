import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalysisOfJavaProjects {

    // Count Java files
    private static int countProjectFileJava = 0;

    // Count resources file;
    private static int countProjectFileResources = 0;

    // Count class
    private static int countProjectClass = 0;

    // Count Java code
    private static int countStrJavaCode = 0;

    // Count Comment
    private static int countStrCommentJavaCode = 0;

    // Count Multiline comments
    private static int countMultilineComments = 0;

//    // Count Method Public
//    private static int Count_Method_Public = 0;
//
//    // Count Method Private
//    private static int Count_Method_Private = 0;
//
//    // Count Method Protected
//    private static int Count_Method_Protected = 0;
//
//    // Count Method Default
//    private static int Count_Default_Method = 0;

    // Add separator (/) or (\)
    String separator = File.separator;

    // Getter - return count java files
    public int getCountProjectFilJava(){
        return countProjectFileJava;
    }

    // Getter - return count resources file
    public int getCountProjectFileResources() {
        return countProjectFileResources;
    }

    // Getter - return count class
    public int getCountProjectClass(){
        return countProjectClass;
    }

    // Delete Count_Project_File_Java
    public void deleteCountProjectFileJava(){
        countProjectFileJava = 0;
    }

    // Delete Count_Project_File_Resources
    public void deleteCountProjectFileResources(){
        countProjectFileResources = 0;
    }

    // Delete Count_Project_Class
    public void deleteCountProjectClass(){
        countProjectClass = 0;
    }

    public void checkCountJavaFiles(File[] files) throws FileNotFoundException {

        for(File file : files){

            if(file.isDirectory()){

                // I take a directory (folder) and divide it into a list of files.
                File Directory = new File(String.valueOf(file));
                File[] Content_Directory = Directory.listFiles();

                // Checking that the directory is not empty
                assert Content_Directory != null;
                // I use recursion
                checkCountJavaFiles(Content_Directory);

            }
            else{

                // I divide the file line by a point to find the extension
                String[] Split = String.valueOf(file).split("\\.");

                // If the extension .java is fulfilling the condition
                if(Split[Split.length-1].equals("java")){
                    // Message to the user
                    System.out.println("Java file found: " + file + "\n");
                    // I'm increasing the counter by 1
                    countProjectFileJava++;

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
                            countMultilineComments++;
                        }

//                        String RegMethod = "(|final )(|public |private |protected )(|static)(void| void|\\w+) \\w+\\((\\w+(\\[\\]|) \\w+|)\\)";
//                        Pattern patternMethod = Pattern.compile(RegMethod);
//
//                        Matcher matcherMethod = patternMethod.matcher(Text);
//
//                        while(matcherMethod.find()){
//
//                            System.out.println(matcherMethod.group());
//
//                           String MethodStr = matcherMethod.group();
//                           String RegMD = "(public|private|protected)";
//
//                           Pattern patternMD = Pattern.compile(RegMD);
//                           Matcher matcherMD = patternMD.matcher(MethodStr);
//
//                           while(matcherMD.find()){
//
//                               if(matcherMD.group().equals("public")){
//                                   System.out.println(matcherMD.group());
//                                   Count_Method_Public++;
//                                   break;
//                               }
//                               if(matcherMD.group().equals("private")){
//                                   System.out.println(matcherMD.group());
//                                   Count_Method_Private++;
//                                   break;
//                               }
//                               if (matcherMD.group().equals("protected")){
//                                   System.out.println(matcherMD.group());
//                                   Count_Method_Protected++;
//                                   break;
//                               }
//                               else{
//                                   System.out.println(matcherMD.group());
//                                   Count_Method_Private++;
//                               }
//                           }
//                        }

                        Scanner scanner2 = new Scanner(file);

                        while(scanner2.hasNextLine()){

                            String Java_Str = scanner2.nextLine();
                            if(Java_Str.contains("//")){
                                countStrCommentJavaCode++;
                            }

                            countStrJavaCode++;

                        }

                    } catch (FileNotFoundException e) {
                        System.out.println("There is no such file, or it cannot be found!\n");
                        throw new FileNotFoundException();
                    }

                    System.out.println("The amount of Java code in the file: " + file + " = " + countStrJavaCode + "\n");
                    System.out.println("The number of comments in the Java code: " + file + " = " + countStrCommentJavaCode + "\n");
                    System.out.println("The number of multiline comments: " + file + " = " + countMultilineComments + "\n");
//                    System.out.println("Number of public methods: " + file + " = " + Count_Method_Public + "\n");
//                    System.out.println("Number of private methods: " + file + " = " + Count_Method_Private + "\n");
//                    System.out.println("Number of protected methods: " + file + " = " + Count_Method_Protected + "\n");
//                    System.out.println("Number of default methods: " + file + " = " + Count_Default_Method + "\n");
                    System.out.println("\n");

                    countStrCommentJavaCode = 0;
                    countStrJavaCode = 0;
                    countMultilineComments = 0;
//                    Count_Method_Public = 0;
//                    Count_Method_Private = 0;
//                    Count_Method_Protected = 0;
//                    Count_Default_Method = 0;

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
                    countProjectClass++;

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
                        countProjectFileResources++;
                    }

                }

                assert file_resources != null;
                Check_Resources_File(file_resources);

            }

        }

    }
}
