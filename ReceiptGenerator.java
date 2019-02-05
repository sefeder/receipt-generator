import java.text.DecimalFormat;
import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public class ReceiptGenerator{
    public static void main(String[] args) throws IOException {
        //read through each item in the "shopping cart" and put each into an array, then loop through this array
        Scanner fromFile = new Scanner(new File("Items.txt")).useDelimiter("\\s*");
        List<String> tempItems = new ArrayList<String>();
        while (fromFile.hasNext()) {
            tempItems.add(fromFile.nextLine());
        }
        String[] itemsArray = tempItems.toArray(new String[0]);
        loopThruItems(itemsArray);
    }

    private static void loopThruItems(String[] itemsArray) {
        //create sales tax and total price arrays that will be filled in 1 by 1 by each line item to make summing totals easy  
        double[] taxesArray = new double[itemsArray.length];  
        double[] totalsArray = new double[itemsArray.length];
        // for each item in the shopping cart, categorize it and calculate sales tax accordingly
        for (int i = 0; i < itemsArray.length; i++) {           
            LineItem.categorize(itemsArray[i], i, taxesArray, totalsArray);
        }
        //sum up the total sales tax for the shopping cart
        double totalTaxes = 0.0;
        for(double i:taxesArray){
            totalTaxes+=i;
        };
        //sum up the total cost of the shopping cart
        double totalCost = 0.0;
        for(double i:totalsArray){
            totalCost+=i;
        };
        final DecimalFormat moneyPattern = new DecimalFormat("##0.00");
        // print out the shopping cart's rounded total sales tax and rounded total price in the required format
        System.out.println("Sales Taxes: "+(moneyPattern.format(round(totalTaxes))));
        System.out.println("Total: "+(moneyPattern.format(round(totalCost))));
    }
    private static double round(double number){
        return Math.round(number * 100.0) / 100.0;
    }
    
}