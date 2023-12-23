import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class BillCalc {
    private final List<String> items;
    private BigDecimal totalCost;
    private BigDecimal totalCostPerPerson;
    private final BigDecimal persons;

    public BillCalc(int persons) {
        this.items = new ArrayList<>();
        this.totalCost = BigDecimal.valueOf(0.00);
        this.totalCostPerPerson = BigDecimal.valueOf(0.00);
        this.persons = BigDecimal.valueOf(persons);
    }

    public void addItem(String itemName, String price) {
        BigDecimal itemPrice = new BigDecimal(price.trim()).setScale(2, RoundingMode.HALF_UP);
        String item = itemName + ", " + itemPrice;
        items.add(item);
        totalCost = totalCost.add(itemPrice);
        totalCostPerPerson = totalCost.divide(persons, RoundingMode.HALF_UP);
    }

    public void printTotal() {
        System.out.println("Количество посетителей: " + persons.setScale(0, RoundingMode.DOWN));
        System.out.println("Общая сумма счета: " + totalEnding(totalCost));
        System.out.println("Каждый посетитель должен заплатить: " + totalEnding(totalCostPerPerson));
        System.out.println("\nВсе позиции меню: ");
        for (int i = 0; i < items.size(); i++) {
            String name = items.get(i).split(",")[0];
            BigDecimal price = new BigDecimal(items.get(i).split(", ")[1]);
            System.out.println((i+1) + ". " + name + " - " + totalEnding(price));
        }
    }

    private String totalEnding(BigDecimal val) {
        double rub = val.setScale(0, RoundingMode.DOWN).doubleValue();
        double kop = val.multiply(BigDecimal.valueOf(100)).setScale(0, RoundingMode.DOWN).doubleValue() % 100;

        String endRub;
        if (rub % 100 > 10 && rub % 100 < 15) endRub = "рублей";
        else if (rub % 10 > 1 && rub % 10 < 5) endRub = "рубля";
        else if (rub % 10 == 1) endRub = "рубль";
        else endRub = "рублей";

        String endKop;
        if (kop > 10 && kop < 15) endKop = "копеек";
        else if (kop % 10 > 1 && kop % 10 < 5) endKop = "копейки";
        else if (kop % 10 == 1) endKop = "копейка";
        else endKop = "копеек";

        return String.format("%.0f %s %.0f %s", rub, endRub, kop, endKop);
    }
}
