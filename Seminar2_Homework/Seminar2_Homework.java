import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
// import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Seminar2_Homework {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // task1();
        System.out.print("input a size of array: ");
        int size = scanner.nextInt();

        int[] array = new int[size];
        System.out.println("Input elements: ");

        for (int i = 0; i < size; i++) {
            System.out.print("input element " + (i + 1) + ": ");
            array[i] = scanner.nextInt();
        }

        task2(array);

        scanner.close();
    }

    static void task1() {
        // Дана строка sql-запроса "select * from students where ". Сформируйте часть
        // WHERE этого запроса,
        // используя StringBuilder. Данные для фильтрации приведены ниже в виде json
        // строки.
        // Если значение null, то параметр не должен попадать в запрос.
        // Параметры для фильтрации: {"name":"Ivanov", "country":"Russia",
        // "city":"Moscow", "age":"null"}

        Map<String, String> filters = new HashMap<>();
        filters.put("name", "Ivanov");
        filters.put("country", "Russia");
        filters.put("city", "Moscow");
        filters.put("age", null);

        String whereClause = buildWhereClause(filters);
        System.out.println(whereClause);

    }

    static String buildWhereClause(Map<String, String> filters) {
        StringBuilder stringBuilder = new StringBuilder("WHERE: ");

        for (Map.Entry<String, String> entry : filters.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (value != null) {
                stringBuilder.append(key).append(" = '").append(value).append("' AND ");
            }

            int length = stringBuilder.length();
            if (length >= 5) {
                stringBuilder.delete(length - 5, length);
            }
        }

        return stringBuilder.toString();

    }

    static void task2(int[] array) {
        // Реализуйте алгоритм сортировки пузырьком числового массива, результат после
        // каждой итерации
        // запишите в лог-файл.
        try (FileWriter writer = new FileWriter("log.txt", true)) {

            writer.write("\n");
            int n = array.length;
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (array[j] > array[j + 1]) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                        // System.out.println("Iteration " + (i + 1) + ", step " + (j + 1) + ": " +
                        // Arrays.toString(array));
                        writer.write("Iteration " + (i + 1) + ", step " + (j + 1) + ": ");
                        for (int k : array) {
                            writer.write(k + " ");
                        }
                        writer.write("\n");
                    }
                }
            }
            // System.out.println("Final sorted array: " + Arrays.toString(array));
            writer.write("\nFinal sorted array: ");
            for (int k : array) {
                writer.write(k + " ");
            }
            writer.write("\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Final");

    }

    static void task3() {

        // ** Дана json строка (можно сохранить в файл и читать из файла)
        // [{"фамилия":"Иванов","оценка":"5","предмет":"Математика"},{"фамилия":"Петрова","оценка":"4","предмет":"
        // Информатика"},{"фамилия":"Краснов","оценка":"5","предмет":"Физика"}]
        // Написать метод(ы), который распарсит json и, используя StringBuilder, создаст
        // строки вида: Студент
        // [фамилия] получил [оценка] по предмету [предмет].
        // Пример вывода:
        // Студент Иванов получил 5 по предмету Математика.
        // Студент Петрова получил 4 по предмету Информатика.
        // Студент Краснов получил 5 по предмету Физика.
    }

    static void task4() {
        // К калькулятору из предыдущего дз добавить логирование.
    }
}
