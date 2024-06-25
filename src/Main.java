import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        String currentPatch = scanner.nextLine();

        // Output the full path to the root cat
        System.out.println("\nI'm opening the root catlog: " + currentPatch + "\n");
        System.out.print("\n");

        // We get a file with this name
        File dir = new File(currentPatch);
        // Creating an array of files and directories and folders
        File[] List = dir.listFiles();

        AnalysisOfJavaProjects analysisOfJavaProjects = new AnalysisOfJavaProjects();

        try {
            analysisOfJavaProjects.checkCountJavaFiles(List);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        analysisOfJavaProjects.Check_Files_Class(List);
        analysisOfJavaProjects.Check_Resources_File(List);

        System.out.println("\nSearch results:" + "\n");
        System.out.println("The number of Java files found: " + analysisOfJavaProjects.getCountProjectFilJava());
        System.out.println("The number of class files found: " + analysisOfJavaProjects.getCountProjectClass());
        System.out.println("The number of resource files found: " + analysisOfJavaProjects.getCountProjectFileResources());

        analysisOfJavaProjects.deleteCountProjectFileJava();
        analysisOfJavaProjects.deleteCountProjectClass();
        analysisOfJavaProjects.deleteCountProjectFileResources();

    }
}