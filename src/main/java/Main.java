import java.util.Scanner;

public class Main {
    public static int customers = 0;
    public static String[] strings = {
            "Привет. Это калькулятор счета.",
            "Введите кол-во человек:",
            "Число должно быть целое и больше 1",
            "Завершить",
            "Добавленные товары:",
            "Товар успешно добавлен в счет",
            "Введите название товара (или 'завершить' для выхода): ",
            "Название должно содержать только текст (буквы и пробелы)",
            "Введите стоимость товара: ",
            "Цена не может содержать букв и не должна быть отрицательной",
            "Общая сумма счета: ",
            "Каждый посетитель должен заплатить: ",
            "\nВсе позиции списком: "};

    static BillCalc calculator = new BillCalc();

    public static void main(String[] args) {

        print(0);
        customersCount();
        addItems(customers);
    }

    public static void customersCount() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            print(1);
            if (scanner.hasNextInt()) {
                customers = scanner.nextInt();
                if (customers > 1) break;
                else print(2);
            } else print(2);
        }
    }

    public static void addItems(int persons) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            print(6);
            String itemName = scanner.next();
            if (!isText(itemName)) { print(7); continue; }
            if (itemName.equalsIgnoreCase("завершить")) break;

            print(8);
            String itemPrice = scanner.next();
            if (isDouble(itemPrice) && Double.parseDouble(itemPrice) > 0) {
                calculator.addItem(itemName, Double.parseDouble(itemPrice));
                print(5);
            } else {print(9);}
        }
        print(10); calculator.showTotal();
        print(11); calculator.showByPerson(persons);
        print(12); calculator.showList();
    }

    private static boolean isText(String value) {
        return value.matches("[a-zA-Zа-яА-Я ]+");
    }

    private static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void print(int arg) {
        System.out.println(strings[arg]);
    }
}