import java.text.DecimalFormat;
import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;

public class ReceiptGenerator{
    public static void main(String[] args) throws IOException {
        Scanner fromFile = new Scanner(new File("Items.txt")).useDelimiter("\\s*");
        List<String> tempItems = new ArrayList<String>();
        while (fromFile.hasNext()) {
            tempItems.add(fromFile.nextLine());
        }
        String[] itemArray = tempItems.toArray(new String[0]);
        loopThruItems(itemArray);
    }

    private static void loopThruItems(String[] itemsArray) { 
        DecimalFormat moneyPattern = new DecimalFormat("##0.00");
        List<String> exceptionsList = Arrays.asList("book", "chocolate", "pills");
        double[] taxesArray = new double[itemsArray.length];
        double[] totalsArray = new double[itemsArray.length];
        for (int i = 0; i < itemsArray.length; i++) {           
            if (itemsArray[i].contains("imported") && (stringContainsItemFromList(itemsArray[i], exceptionsList))) {
                calculateSalesTax(itemsArray[i], 5, i, taxesArray, totalsArray);
            }
            else if (itemsArray[i].contains("imported")) {
                calculateSalesTax(itemsArray[i], 15, i, taxesArray, totalsArray);
            }else if (stringContainsItemFromList(itemsArray[i], exceptionsList)) {
                calculateSalesTax(itemsArray[i], 0, i, taxesArray, totalsArray);
            }else{
                calculateSalesTax(itemsArray[i], 10, i, taxesArray, totalsArray);
            }
        }
        double totalTaxes = 0.0;
        for(double i:taxesArray){
            totalTaxes+=i;
        };
        double totalCost = 0.0;
        for(double i:totalsArray){
            totalCost+=i;
        };
        double roundedTotalTaxes = round(totalTaxes);
        double roundedTotalCost = round(totalCost);
        System.out.println("Sales Taxes: "+(moneyPattern.format(roundedTotalTaxes)));
        System.out.println("Total: "+(moneyPattern.format(roundedTotalCost)));
    }
    private static void calculateSalesTax(String s, int tax, int i, double[] taxesArray, double[] totalsArray) {
        DecimalFormat moneyPattern = new DecimalFormat("##0.00");
        double pricePreTax = Double.parseDouble(s.substring(s.indexOf(" at ") + 4, s.length()));
        double taxBeforeRound = (pricePreTax * tax) / 100;
        double salesTax = Math.ceil(taxBeforeRound * 20) / 20;
        double roundedTotal = round(pricePreTax+salesTax);
        System.out.println(s.substring(0,s.indexOf(" at "))+": "+(moneyPattern.format(roundedTotal)));
        taxesArray[i] = round(salesTax);
        totalsArray[i] = roundedTotal;
    }
    private static boolean stringContainsItemFromList(String inputStr, List<String> items) {
        return items.parallelStream().anyMatch(inputStr::contains);
    }
    private static double round(double number){
        return Math.round(number * 100.0) / 100.0;
    }
    
}