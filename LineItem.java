import java.util.Arrays;
import java.util.List;
import java.text.DecimalFormat;
public class LineItem{
    public static void categorize(String item, int i, double[] taxesArray, double[] totalsArray) {
        final List<String> exceptionsList = Arrays.asList("book", "chocolate", "pills");
        if (item.contains("imported") && (stringContainsItemFromList(item, exceptionsList))) {
            //tax at a rate of 5% if item is imported and exempt
            calculateSalesTax(item, 5, i, taxesArray, totalsArray);
        } else if (item.contains("imported")) {
            //tax at a rate of 15% if item is imported and non-exempt
            calculateSalesTax(item, 15, i, taxesArray, totalsArray);
        } else if (stringContainsItemFromList(item, exceptionsList)) {
            // tax at a rate of 0% if item is not-imported and exempt
            calculateSalesTax(item, 0, i, taxesArray, totalsArray);
        } else {
            // tax at a rate of 10% if item is not-imported and non-exempt
            calculateSalesTax(item, 10, i, taxesArray, totalsArray);
        }
    }

    private static void calculateSalesTax(String s, int tax, int i, double[] taxesArray, double[] totalsArray) {
        final DecimalFormat moneyPattern = new DecimalFormat("##0.00");
        //parse the item's shelf price as a double
        double pricePreTax = Double.parseDouble(s.substring(s.indexOf(" at ") + 4, s.length()));
        //calculate the item's tax according to given algorithm (np/100)
        double taxBeforeRound = (pricePreTax * tax) / 100;
        //round the item's tax according to given algorithm (round up to nearest 0.05)
        double salesTax = Math.ceil(taxBeforeRound * 20) / 20;
        //round the item's total price
        double roundedTotal = round(pricePreTax + salesTax);
        //print the item's description and total price in the required format
        System.out.println(s.substring(0, s.indexOf(" at ")) + ": " + (moneyPattern.format(roundedTotal)));
        //fill in the both total arrays respectively with the current values 
        taxesArray[i] = round(salesTax);
        totalsArray[i] = roundedTotal;
    }

    private static boolean stringContainsItemFromList(String inputStr, List<String> items) {
        return items.parallelStream().anyMatch(inputStr::contains);
    }

    private static double round(double number) {
        return Math.round(number * 100.0) / 100.0;
    }
}