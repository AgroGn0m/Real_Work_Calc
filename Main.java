import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите операцию");
        String input = scanner.nextLine();
        System.out.println(calcilate(input));

    }

    public static  String calcilate(String input) throws Exception {
        String simvol;
        int first_number;
        String result;
        int second_number;
        boolean bool_string;
        boolean search_number;
        String[] h = input.split("[+*-/]");

        // ---- Вычисляем кол-во операндов

        if (h.length != 2) throw new Exception("Error: Вводите только одну арифметическую операцию и два числа.");

        // ---- Находим символ операции
        simvol = search_Operation(input);
        if (simvol == null) throw  new Exception("Введите арифметическую опреацию!");
        // ---- Определяем принадлежность числел

//        bool_string = isDouble(String.valueOf((h[0])));

//        if ((isDouble(h[0])) || (isDouble(h[1]))){
//            throw  new Exception("Вводите только целые числа!");
//        }
        /// Числа римские
        if (Rim.search_number(h[0]) && (Rim.search_number(h[1]))) {
            first_number = Rim.convert(h[0]);
            second_number = Rim.convert(h[1]);
            search_number = true;
        /// Числа арабские
        } else if (!Rim.search_number(h[0]) && !Rim.search_number(h[1])) {
            first_number = Integer.parseInt(h[0]);
            second_number = Integer.parseInt(h[1]);
            search_number = false;
        } else {
            throw new Exception("Числа должны быть одного типа!");
        }

        if (!isDouble(String.valueOf(first_number)) || !isDouble(String.valueOf(second_number))) {
            throw new Exception("Вводите только целые числа!");
        }


        // --- Проверяем числа

        if (first_number > 10 || first_number < 1) {
            throw new Exception("Error: Число " + first_number + " больше 10-ти или меньше 1-го.");
           }
        else {
            if (second_number > 10 || second_number < 1)
                throw new Exception("Error: Число " + second_number + " больше 10-ти или меньше 1-го.");
        }
        result = String.valueOf(calc(first_number, second_number, simvol));
        if (search_number) {
           int result1 = Integer.parseInt(result);
            if (result1 <=0) {
                throw new Exception("Результат не может быть меньше единицы!");
            }
            result = Rim.convertToRim(result1);
        }

        return result;
    }
    static String search_Operation(String input) {
        if (input.contains("+")) {
            return "+";
        } else if (input.contains("-")) {
            return "-";
        } else if (input.contains("/")) {
            return "/";
        } else if (input.contains("*")) {
            return "*";
        } else return null;
    }

    public static boolean isDouble(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }

    static int calc(int a, int b, String simvol) {

        if (simvol.equals("+")) return a + b;
        else if (simvol.equals("-")) return a - b;
        else if (simvol.equals("*")) return a * b;
        else return a / b;
    }
}

class Rim {

    static String[] arrays = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};


    public static boolean search_number(String numbers) {
        for (int i = 0; i < arrays.length; i++) {
            if (numbers.equals(arrays[i])) {
                return true;
            }
        }
        return false;
    }

    public static int convert(String num) {
        for (int i = 0; i < arrays.length; i++){
            if (num.equals(arrays[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRim(int result) {
        return arrays[result];
    }
}


