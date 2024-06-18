import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        // Получаём полный путь до нашего корнегово котолога
        String currentPatch = new java.io.File(".").getCanonicalPath();
        // Выводим полный путь до корневого католога
        System.out.println("\nОткрываю корневой котолог: " + currentPatch + "\n");
        System.out.print("\n");

        // Получаем файл с таким названием
        File dir = new File(currentPatch);
        // Создаём массив файлов и дерикторий и папок
        File[] List = dir.listFiles();
        
        Analysis_of_Java_projects analysisOfJavaProjects = new Analysis_of_Java_projects();
        analysisOfJavaProjects.Check_Count_Java_Files(List);
        analysisOfJavaProjects.Check_Files_Class(List);
        analysisOfJavaProjects.Check_Resources_File(List);

        System.out.println("Итоги поиска:" + "\n");
        System.out.println("Количество найденых Java файлов: " + analysisOfJavaProjects.getCount_Project_File_Java());
        System.out.println("Количество найденых файлов классов: " + analysisOfJavaProjects.getCount_Project_Class());
        System.out.println("Количество найденых файлов ресурсов: " + analysisOfJavaProjects.getCount_Project_File_Resources());


    }
}