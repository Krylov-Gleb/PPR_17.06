import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void Check_File_Directory_or_File(File[] files){

        // Счётчик Java файлов
        int Count_Project_File_Java = 0;

        // Счётчик файлов ресурсов
        int Count_Project_File_Resources = 0;

        // Счётчик количества классов
        int Count_Project_Class = 0;

        // Сообщение пользователю
        System.out.println("Начинаю поиск!\n");

        // Использую цикл (фор-эч)
        for(File file : files){
            // Проверка если элемент (file) является Директорией то выполняю условие
            if(file.isDirectory()){
                // Сообщение для пользователя
                System.out.println("Открываю папку с именем: " + file + "\n");
                try {
                    String currentPatch = new File(".").getCanonicalPath();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                // Беру директорию (папку) и разделяю её на список файлов.
                File Directory = new File(String.valueOf(file));
                File[] Content_Directory = Directory.listFiles();

                // Проверка что директория не пустая
                assert Content_Directory != null;
                // Использую рекурсию
                Check_File_Directory_or_File(Content_Directory);


            }
            else{
                // Вывожу сообщение пользователю
                System.out.println("Найден файл с именем: " + file + "\n");

                // Разделяю строку file по точке что бы найдти расширение
                String[] Split = String.valueOf(file).split("\\.");

                // Если расширение .java то выполняю условие
                if(Split[Split.length-1].equals("java")){
                    // Сообщение пользователю
                    System.out.println("Найден джава файл: " + file + "\n");
                    // Увеличиваю счётчик на 1
                    Count_Project_File_Java++;

                    // Добавляю уневерсальный разделитель
                    String separator = File.separator;
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

                // Если расширение .class то выполняю условие
                if(Split[Split.length-1].equals("class")){
                    // Сообщение пользователю
                    System.out.println("Найден класс: " + file + "\n");
                    // Увеличиваю счётчик на 1
                    Count_Project_Class++;

                    // Добавляю уневерсальный разделитель
                    String separator = File.separator;
                    // Создаю файл
                    File fileCache = new File("resources" + separator + "Count_Class_File.txt");

                    // Если значение счётчика > 0 то реализую условие
                    if(Count_Project_Class > 0) {
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


    public static void main(String[] args) throws IOException {

        // Получаём полный путь до нашего корнегово котолога
        String currentPatch = new java.io.File(".").getCanonicalPath();
        // Выводим полный путь до корневого католога
        System.out.println("\nКорневой котолог: " + currentPatch + "\n");

        // Получаем файл с таким названием
        File dir = new File(currentPatch);
        // Создаём массив файлов и дерикторий и папок
        File[] List = dir.listFiles();

        // Вызываю метод и передаю туда List
        Check_File_Directory_or_File(List);

        String separator = File.separator;
        File file_Java_Files = new File("resources" + separator + "Count_Java_File.txt");
        // Считываю файл Count_Java_File.txt
        Scanner scanner = new Scanner(file_Java_Files);

        // Вывожу количество java файлов в проекте
        System.out.println("\nКоличество Java файлов в проекте = " + scanner.nextLine());

        File file_Class_Files = new File("resources" + separator + "Count_Class_File.txt");
        // Считываю файл Count_Class_File.txt
        scanner = new Scanner(file_Java_Files);

        // Вывожу количество классов в проекте
        System.out.println("\nКоличество класов в проекте = " + scanner.nextLine());


    }
}