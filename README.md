# Receipt Generator

### Description
This application takes in a list of items and their shelf prices, calculates the sales tax for each item, and prints out a receipt displaying the total price of each item (including sales tax), the total amount of sales tax and the total price for the list.

### Instructions for Use
* List items in the Items.txt file in the following format:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>1 _______ at ##.##</code>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Example: For an imported picture frame that costs $8.99, a graphic t-shirt that costs $12.50, and a coloring book that costs $5, you would enter:

```
1 imported picture frame at 8.99
1 graphic t-shirt at 12.50
1 coloring book at 5.00
```

* Save the Items.txt file

* In the project directory, run the following 2 commands:

1. <code>$ javac ReceiptGenerator.java</code>

2. <code>$ java ReceiptGenerator</code>


### Assumptions
* You will only be purchasing 1 of any given item
* Any food item will have the word "chocolate" in it
* Any medications will have the word "pills" in it
* Everything except for food, medicine, and books will be taxed at 10%
* Anything that is imported will be taxed an additional 5%
* The sales tax rounding rule is as follows: for a tax rate of n%, a shelf price of p will be taxed at (np/100 rounded up to the nearest 0.05)

### Design
The code works by scanning each item for the word "imported" as well as checking if any of the exception words (in this case: "chocolate", "pills", or "book") are present. Based on what it finds, it categorizes the item into 1 of 4 categories (imported and exempt, imported and non-exempt, non-imported and exempt, and non-imported and non-exempt). Finally, the item's shelf price is parsed as a number and its sales tax is calculated according to its category.

### Future Versions
* You should be able to purchase more than just 1 of a given item
* The exempt categories will be more robust (each its own array/list filled with items) rather than just searching for 3 key words
* The tax rates for each category should be up to the user rather than hard coded