import java.util.Scanner;

public class Main {
    public static int customers = 0;
    public static String[] strings = {
            "Привет. Это калькулятор счета.",
            "Введите КОЛ-ВО человек (или 'завершить' для выхода):",
            "Неверно: Должно быть целое число. 1 < ЧИСЛО < 1000",
            "Товар успешно добавлен в счет",
            "Введите НАЗВАНИЕ товара (или 'завершить' для выхода): ",
            "Неверно: Название должно содержать текст (буквы, цифры, некоторые знаки препинания, кроме запятой)",
            "Введите СТОИМОСТЬ товара (или 'завершить' для выхода): ",
            "Неверно: 0.00 <= ЦЕНА < 150000000.00,\n Цена, это число не более 2х знаков после запятой."};

    static BillCalc calculator;

    public static void main(String[] args) {
        printString(0);

        if (customersCount()) return;
        calculator = new BillCalc(customers);
        addItems();
        calculator.printTotal();
    }

    public static boolean customersCount() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printString(1);
            String input = scanner.nextLine();
            if (input.trim().equalsIgnoreCase("завершить")) return true;
            try {
                customers = Integer.parseInt(input);
                if (customers > 1 && customers < 1000) break;
            } catch (NumberFormatException ignored) {}
            printString(2);
        }
        return false;
    }

    public static void addItems() {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            printString(4);
            String itemName = scanner.nextLine();
            if (itemName.trim().equalsIgnoreCase("завершить")) flag = false;
            else if (!isText(itemName)) {
                printString(5);
                continue;
            }
            while(flag) {
                printString(6);
                String itemPrice = scanner.nextLine();
                if (itemPrice.trim().equalsIgnoreCase("завершить")) flag = false;
                else if (isPrice(itemPrice)) {
                    calculator.addItem(itemName, itemPrice);
                    printString(3);
                    break;
                }
                else printString(7);
            }
        }
    }

    private static boolean isText(String value) {
        return value.matches("[a-zA-Zа-яА-Я\\d\\p{Punct} &&[^,]]+");
    }

    private static boolean isPrice(String value) {
        try {
            double number = Double.parseDouble(value);
            int decimalPlaces = value.indexOf('.') >= 0 ? value.length() - value.indexOf('.') - 1 : 0;
            return number >= 0 && number < 150000000.00 && decimalPlaces <= 2;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void printString(int arg) {
        System.out.println(strings[arg]);
    }
}