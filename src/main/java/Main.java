import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    // мессадж аборигенов считаем из файла
    static final String FILE_NAME = "messagetext.txt";

    public static void main(String[] args) {
        String msg = readTextFile(FILE_NAME);
        if (msg == null) System.exit(1);
        //все что было выше - не функциональный стиль так как используются не чистые функции ввода


        //а теперь функциональный стиль-монада.
        // "Функция, которая составляет словарь, должна быть оформлена как реализатор Function"
        Function<String, String> dictiMaker = str ->
                Arrays.stream(str.split(" "))
                        .distinct()
                        .sorted(Comparator.naturalOrder())
                        .collect(Collectors.joining(" "));

        //а теперь опять не функциональный - вывод.
        System.out.println("Исходный мессадж аборигенов:\n" + msg +
                        "\n\nСловарь:\n" + makeDictionary(dictiMaker, msg));
    }

    /**
     * Строит словарь из переданной строки с помощью переданной функции - построителя
     *
     * @param function - построитель словаря
     * @param inputStr - данные для помещения в словарь
     * @return - словарь
     */
    public static String makeDictionary(Function<String, String> function, String inputStr) {
        return function.apply(inputStr);
    }


    /**
     * загрузчик текстового файла
     *
     * @param fileName -имя файла
     * @return зачитанная строка или null в случае ошибки
     */
    public static String readTextFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            //чтение построчно
            StringBuilder stringBuffer = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null) {
                stringBuffer.append(str);
            }
            return stringBuffer.toString();
        } catch (IOException ex) {
            System.out.println("Ошибка ввода-вывода" + ex.getMessage());
            return null;
        }
    }
}
