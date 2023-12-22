import java.util.ArrayList;
import java.util.List;

public class BillCalc {
    private List<String> items;
    private double totalCost;

    public BillCalc() {
        items = new ArrayList<>();
        totalCost = 0;
    }

    public void addItem(String itemName, double itemPrice) {
        String item = String.format("%s, %.2f", itemName, itemPrice);
        items.add(item);
        totalCost += itemPrice;
    }

    void showTotal() {
        System.out.println(totalEnding(totalCost));
    }

    void showByPerson(int persons) {
        System.out.println(totalEnding(totalCost / persons));
    }

    void showList() {
        for (int i = 0; i < items.size(); i++) {
            String[] parts = items.get(i).split(",");
            double itemPrice = Double.parseDouble(parts[1].trim());

            System.out.println((i + 1) + ". " + parts[0].trim() + " - " + totalEnding(itemPrice));
        }
    }

    private String totalEnding(double value) {
        int rub = (int) value;
        int kop = (int) ((value - rub) * 100);

        String endRub;
        if (rub % 100 > 10 && rub % 100 < 15) endRub = "рублей";
        else if (rub % 10 > 1 && rub % 10 < 5) endRub = "рубля";
        else if (rub % 10 == 1) endRub = "рубль";
        else endRub = "рублей";

        String endKop;
        if (kop % 100 > 10 && kop % 100 < 15) endKop = "копеек";
        else if (kop % 10 > 1 && kop % 10 < 5) endKop = "копейки";
        else if (kop % 10 == 1) endKop = "копейка";
        else endKop = "копеек";

        return String.format("%d %s %02d %s", rub, endRub, kop, endKop);
    }
}
