import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.File;

public class Seminar2_lesson {
    public static void main(String[] args) {
        // System.out.println(task1(40, 'h', 'a'));
        // task2("aaaabbbcdd");
        // System.out.println(task3("cacac"));
        // task4(task4_1(10, "Cool"));
        // task5();
        task6();
    }

    static String task1(int n, char c1, char c2) {
        // Дано четное число N (>0) и символы c1 и c2.
        // Написать метод, который вернет строку длины N, которая
        // состоит из чередующихся символов c1 и c2, начиная с c1.

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n / 2; i++) {
            stringBuilder.append(c1).append(c2);
        }
        return stringBuilder.toString();
    }

    static void task2(String str) {
        // Напишите метод, который сжимает строку.
        // Пример: вход aaaabbbcdd.

        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        int count = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] != chars[i - 1]) {
                stringBuilder.append(chars[i - 1]).append(count + 1);
                count = 0;
            } else {
                count++;
            }
        }
        stringBuilder.append(chars[chars.length - 1]).append(count + 1);
        System.out.println(stringBuilder);
    }

    static boolean task3(String str) {
        // Напишите метод, который принимает на вход строку (String) и
        // определяет является ли строка палиндромом (возвращает
        // boolean значение).

        StringBuilder stringBuilder = new StringBuilder(str);
        String str2 = stringBuilder.reverse().toString();

        return str.equals(str2);
    }

    static void task4(String str) {
        // Напишите метод, который составит строку, состоящую из 100
        // повторений слова TEST и метод, который запишет эту строку в
        // простой текстовый файл, обработайте исключения.

        try (FileWriter writer = new FileWriter("text.txt", true)) {
            writer.write(str);
            writer.flush();
            System.out.println("Succesful");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String task4_1(int n, String str) {
        return str.repeat(n);
    }

    static void task5() {
        // Напишите метод, который вернет содержимое текущей папки в виде
        // массива строк.
        // Напишите метод, который запишет массив, возвращенный предыдущим
        // методом в файл.
        // Обработайте ошибки с помощью try-catch конструкции. В случае
        // возникновения исключения, оно должно записаться в лог-файл.
        Logger logger = Logger.getAnonymousLogger();
        try {
            FileHandler ch = new FileHandler("log.txt", true);
            logger.addHandler(ch);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            ch.setFormatter(simpleFormatter);
            String[] folderContents = getCurrentFolderContents();

            // Записываем массив строк в файл
            writeArrayToFile(folderContents, "output.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    static String[] getCurrentFolderContents() {
        File currentFolder = new File(".");
        return currentFolder.list();
    }

    static void writeArrayToFile(String[] array, String fileName) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            for (String line : array) {
                writer.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + e.getMessage());
        }
    }

    static void task6() {
        // Напишите метод, который определит тип (расширение) файлов из
        // текущей папки и выведет в консоль результат вида
        // 1 Расширение файла: txt
        // 2 Расширение файла: pdf
        // 3 Расширение файла:
        // 4 Расширение файла: jpg
        File folder = new File(".");
        File[] files = folder.listFiles();

        if (files != null) {
            int count = 0;

            for (File file : files) {
                if (file.isFile()) {
                    count++;
                    String fileName = file.getName();
                    int dotIndex = fileName.lastIndexOf(".");

                    String exception = (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);

                    System.out.println(count + " Расширение файла: " + exception);

                    if (count == 0) {
                        System.out.println("В текущей папке нет файлов.");
                    }
                }
            }
        } else {
            System.out.println("Ошибка при доступе к содержимому текущей папки");
        }
    }
}