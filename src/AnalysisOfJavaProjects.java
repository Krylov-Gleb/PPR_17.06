import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalysisOfJavaProjects {

    // Count Java files
    private static int countProjectFileJava = 0;

    private static int countProjectFileResources = 0;

    private static int countProjectClass = 0;

    private static int countStrJavaCode = 0;

    private static int countStrCommentJavaCode = 0;

    private static int countMultilineComments = 0;

    private static int countMethodPublic = 0;

    private static int countMethodPrivate = 0;

    private static int countMethodProtected = 0;

    private static int countImports = 0;

    private static int countNumberPackages = 0;

    // Add separator (/) or (\)
    String separator = File.separator;

    // Getter - return count java files
    public int getCountProjectFilJava(){
        return countProjectFileJava;
    }

    public int getCountProjectFileResources() {
        return countProjectFileResources;
    }

    public int getCountProjectClass(){
        return countProjectClass;
    }

    // Delete Count_Project_File_Java
    public void deleteCountProjectFileJava(){
        countProjectFileJava = 0;
    }

    public void deleteCountProjectFileResources(){
        countProjectFileResources = 0;
    }

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
                    System.out.println("JAVA FILE FOUND: " + file + "\n");
                    // I'm increasing the counter by 1
                    countProjectFileJava++;

                    try {

                        // I'm collecting the text
                        String Text = "";
                        Scanner scanner1 = new Scanner(file);

                        // I'm collecting the text
                        while(scanner1.hasNextLine()){
                            Text = Text + scanner1.nextLine() + "\n";
                        }

                        // Checking for multi-line comments
                        String Reg = "\\/\\*[A-zА-я0-9\\W][^\\n]+";
                        Pattern pattern = Pattern.compile(Reg);

                        Matcher matcher = pattern.matcher(Text);

                        // If I see a multi-line comment, I increase the counter
                        while(matcher.find()){
                            countMultilineComments++;
                        }

                        // Checking for methods
                        String RegMethod = "(public|private|protected)[A-z0-9 ,]+\\(([A-z0-9 ,]+|)\\)";
                        Pattern patternMethod = Pattern.compile(RegMethod);

                        Matcher matcherMethod = patternMethod.matcher(Text);

                        // If there are methods, I look at the access modifier
                        while(matcherMethod.find()){

                           String MethodStr = matcherMethod.group();
                           String RegMD = "(public|private|protected)";

                           Pattern patternMD = Pattern.compile(RegMD);
                           Matcher matcherMD = patternMD.matcher(MethodStr);

                           while(matcherMD.find()){

                               if(matcherMD.group().equals("public")){
                                   countMethodPublic++;
                                   break;
                               }
                               if(matcherMD.group().equals("private")){
                                   countMethodPrivate++;
                                   break;
                               }
                               if (matcherMD.group().equals("protected")){
                                   countMethodProtected++;
                                   break;
                               }
                           }
                        }

                        // Checking for imports
                        String RegImport = "import [ .А-яA-z0-9*]+;";
                        Pattern patternImports = Pattern.compile(RegImport);
                        Matcher matcherImports = patternImports.matcher(Text);

                        while(matcherImports.find()){
                            countImports++;
                        }

                        // Checking for package
                        String RegPackage = "package [A-zА-я0-9.$*]+;";
                        Pattern patternPackage = Pattern.compile(RegPackage);
                        Matcher matcherPackage = patternPackage.matcher(Text);

                        while(matcherPackage.find()){
                            countNumberPackages++;
                        }

                        Scanner scanner2 = new Scanner(file);

                        // Counting the number of lines of code and comments
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

                    // Data output
                    System.out.println("The amount of Java code in the file: " + file + " = " + countStrJavaCode);
                    System.out.println("The number of comments in the Java code: " + file + " = " + countStrCommentJavaCode);
                    System.out.println("The number of multiline comments: " + file + " = " + countMultilineComments + "\n");
                    System.out.println("Number of public methods: " + file + " = " + countMethodPublic);
                    System.out.println("Number of private methods: " + file + " = " + countMethodPrivate);
                    System.out.println("Number of protected methods: " + file + " = " + countMethodProtected + "\n");
                    System.out.println("Number of imported modules: " + file + " = " + countImports +  "\n");
                    System.out.println("Number of packages: " + file + " = " + countNumberPackages);

                    System.out.println("\n");

                    // Cleaning the counter
                    countStrCommentJavaCode = 0;
                    countStrJavaCode = 0;
                    countMultilineComments = 0;
                    countMethodPublic = 0;
                    countMethodPrivate = 0;
                    countMethodProtected = 0;
                    countImports = 0;
                    countNumberPackages = 0;

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

                // Checking for classes
                if (Split[Split.length - 1].equals("class")) {
                    System.out.println("THE CLASS FILE WAS FOUND: " + file + "\n");
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

                    // Checking for resources
                    assert file_resources != null;
                    for (File file1 : file_resources){
                        System.out.println("\nRESOURCE FILE FOUND: " + file1 + "\n");
                        countProjectFileResources++;
                    }

                }

                assert file_resources != null;
                Check_Resources_File(file_resources);

            }

        }

    }
}
