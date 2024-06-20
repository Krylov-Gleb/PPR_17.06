import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class analysisOfJavaProjects {

    // Счётчик Java файлов
    private static int Count_Project_File_Java = 0;

    // Счётчик файлов ресурсов
    private static int Count_Project_File_Resources = 0;

    // Счётчик количества классов
    private static int Count_Project_Class = 0;

    // Счётчик количества кода в джава файле
    private static int Count_Str_JavaCode = 0;

    // Счётчик количества коментариев в Java коде
    private static int Count_Str_Comment_JavaCode = 0;

    // Добавляю уневерсальный разделитель
    String separator = File.separator;

    // Гетер для пулучения значения Count_Project_File_Java
    public int getCount_Project_File_Java(){
        return Count_Project_File_Java;
    }

    // Гетер для получения значения Count_Project_File_Resources
    public int getCount_Project_File_Resources() {
        return Count_Project_File_Resources;
    }

    // Гетер для получения значения Count_Project_Class
    public int getCount_Project_Class(){
        return Count_Project_Class;
    }

    public void Check_Count_Java_Files(File[] files){

        for(File file : files){

            if(file.isDirectory()){

                // Беру директорию (папку) и разделяю её на список файлов.
                File Directory = new File(String.valueOf(file));
                File[] Content_Directory = Directory.listFiles();

                // Проверка что директория не пустая
                assert Content_Directory != null;
                // Использую рекурсию
                Check_Count_Java_Files(Content_Directory);

            }
            else{

                // Разделяю строку file по точке что бы найдти расширение
                String[] Split = String.valueOf(file).split("\\.");

                // Если расширение .java то выполняю условие
                if(Split[Split.length-1].equals("java")){
                    // Сообщение пользователю
                    System.out.println("Найден джава файл: " + file + "\n");
                    // Увеличиваю счётчик на 1
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

                // Беру директорию (папку) и разделяю её на список файлов.
                File Directory = new File(String.valueOf(file));
                File[] Content_Directory = Directory.listFiles();

                // Проверка что директория не пустая
                assert Content_Directory != null;
                // Использую рекурсию
                Check_Files_Class(Content_Directory);

            } else {

                // Разделяю строку file по точке что бы найдти расширение
                String[] Split = String.valueOf(file).split("\\.");

                // Если расширение .java то выполняю условие
                if (Split[Split.length - 1].equals("class")) {
                    // Сообщение пользователю
                    System.out.println("Найден файл класс: " + file + "\n");
                    // Увеличиваю счётчик на 1
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
