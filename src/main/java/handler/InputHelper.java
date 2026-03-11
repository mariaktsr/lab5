package handler;

import java.util.Scanner;

//Вспомогательный класс для ввода данных с консоли с валидацией

public class InputHelper {

    private static final Scanner scanner = new Scanner(System.in);

    public static String readString(String prompt, boolean allowEmpty) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            if (!line.isEmpty() || allowEmpty) {
                return line;
            }
            System.out.println("  Поле не может быть пустым");
        }
    }

    public static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("  Введите корректное число");
            }
        }
    }

    public static long readLong(String prompt, Long min) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                long value = Long.parseLong(line);
                if (min != null && value <= min) {
                    System.out.println("  Значение должно быть больше " + min);
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("  Введите корректное целое число");
            }
        }
    }

    public static boolean readBoolean(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim().toLowerCase();
            if (line.equals("true")) return true;
            if (line.equals("false")) return false;
            System.out.println("  Введите true или false");
        }
    }

    public static <T extends Enum<T>> T readEnum(String prompt, Class<T> enumClass) {
        while (true) {
            System.out.print(prompt);
            System.out.print("(");
            T[] constants = enumClass.getEnumConstants();
            for (int i = 0; i < constants.length; i++) {
                System.out.print(constants[i].name());
                if (i < constants.length - 1) System.out.print(", ");
            }
            System.out.print("): ");

            String input = scanner.nextLine().trim().toUpperCase();
            try {
                return Enum.valueOf(enumClass, input);
            } catch (IllegalArgumentException e) {
                System.out.println("  Недопустимое значение");
            }
        }
    }
}