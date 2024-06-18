import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Analysis_of_Java_projects {

    // Счётчик Java файлов
    private int Count_Project_File_Java = 0;
    // Счётчик файлов ресурсов
    private int Count_Project_File_Resources = 0;
    // Счётчик количества классов
    private int Count_Project_Class = 0;
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

                    // Создаю файл
                    File fileCache = new File("resources" + separator + "Count_Java_File.txt");

                    // Если значение счётчика > 0 то реализую условие
                    if(Count_Project_File_Java > 0) {
                        // Обработка исключения
                        try {
                            FileWriter fileWriter = new FileWriter(fileCache);
                            // Произвожу запись
                            fileWriter.write(String.valueOf(Count_Project_File_Java));
                            // Закрываю FileWriter
                            fileWriter.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
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

                    // Создаю файл
                    File fileCache = new File("resources" + separator + "Count_Class_File.txt");

                    // Если значение счётчика > 0 то реализую условие
                    if (Count_Project_File_Java > 0) {
                        // Обработка исключения
                        try {
                            FileWriter fileWriter = new FileWriter(fileCache);
                            // Произвожу запись
                            fileWriter.write(String.valueOf(Count_Project_Class));
                            // Закрываю FileWriter
                            fileWriter.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }

            }

        }

    }

    public void Check_Resources_File(File[] files){

        String currentPatch = "";

        // Получаём полный путь до нашего корнегово котолога
        try {
            currentPatch = new File(".").getCanonicalPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (File file : files) {

            if (file.isDirectory()) {

                if(String.valueOf(file).equals(currentPatch + separator + "resources")){

                    // Беру директорию (папку) и разделяю её на список файлов.
                    File Directory = new File(String.valueOf(file));
                    File[] Content_Directory = Directory.listFiles();

                    for(File file_res : Content_Directory){
                        System.out.println("Был найден файл ресурсов: " + file + "\n");
                        Count_Project_File_Resources++;
                    }

                    File file_Cache = new File("resources" + separator + "Count_Resources_File.txt");

                    try {
                        FileWriter fileWriter = new FileWriter(file_Cache);
                        fileWriter.write(String.valueOf(Count_Project_File_Resources));
                        fileWriter.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }


                }

            }

        }

    }

}
