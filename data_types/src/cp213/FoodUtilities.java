package cp213;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utilities for working with Food objects.
 *
 * @author Iman Chaudhry
 * @version 2023-05-07
 */
public class FoodUtilities {

    /**
     * Determines the average calories in a list of foods. No rounding necessary.
     * Foods list parameter may be empty.
     *
     * @param foods a list of Food
     * @return average calories in all Food objects
     */
    public static int averageCalories(final ArrayList<Food> foods) {

	int total = 0;
	int count = 0;
	
	for (Food fd : foods) {
	    total += fd.getCalories();
	    count += 1;
	}
	
	int average = total / count;
	    
	return average;
    }

    /**
     * Determines the average calories in a list of foods for a particular origin.
     * No rounding necessary. Foods list parameter may be empty.
     *
     * @param foods  a list of Food
     * @param origin the origin of the Food
     * @return average calories for all Foods of the specified origin
     */
    public static int averageCaloriesByOrigin(final ArrayList<Food> foods, final int origin) {

	int total = 0;
	int count = 0;
	
	for (Food fd : foods) {
	    if (fd.getOrigin() == origin)
		total += fd.getCalories();
	    	count += 1;
	}
	
	int average = total / count;
	    
	return average;
    }

    /**
     * Creates a list of foods by origin.
     *
     * @param foods  a list of Food
     * @param origin a food origin
     * @return a list of Food from origin
     */
    public static ArrayList<Food> getByOrigin(final ArrayList<Food> foods, final int origin) {

	ArrayList<Food> list = new ArrayList<>();
	for (Food fd : foods) {
	    if (fd.getOrigin() == origin)
		list.add(fd);
	}
	    
	return list;
    }

    /**
     * Creates a Food object by requesting data from a user. Uses the format:
     *
     * <pre>
    Name: name
    Origins
     0 Canadian
     1 Chinese
    ...
    11 English
    Origin: origin number
    Vegetarian (Y/N): Y/N
    Calories: calories
     * </pre>
     *
     * @param keyboard a keyboard Scanner
     * @return a Food object
     */
    public static Food getFood(final Scanner keyboard) {
	    System.out.print("Name: ");
	    String name = keyboard.nextLine();
	    
	    System.out.println(Food.originsMenu());
	    System.out.print("Origin: ");
	    int origin = keyboard.nextInt();
	    keyboard.nextLine();
	    
	    System.out.print("Vegetarian (Y/N): ");
	    String YN = keyboard.nextLine();
	    boolean isVegetarian = false;
	    if (YN.equals("Y"))
		isVegetarian = true;
	    else if (YN.equals("N"))
		isVegetarian = false;
	    
	    System.out.print("Calories: ");
	    int calories = keyboard.nextInt();
	    
	    Food obj = new Food(name, origin, isVegetarian, calories);
	    
	return obj;
    }

    /**
     * Creates a list of vegetarian foods.
     *
     * @param foods a list of Food
     * @return a list of vegetarian Food
     */
    public static ArrayList<Food> getVegetarian(final ArrayList<Food> foods) {

	ArrayList<Food> veglist = new ArrayList<>();
	for (Food fd : foods) {
	    if (fd.isVegetarian())
		veglist.add(fd);
	}
	    
	return veglist;
    }

    /**
     * Creates and returns a Food object from a line of string data.
     *
     * @param line a vertical bar-delimited line of food data in the format
     *             name|origin|isVegetarian|calories
     * @return the data from line as a Food object
     */
    public static Food readFood(final String line) {
	
	String array[] = line.strip().split("\\|");
	
	String name = array[0];
	int origin = Integer.valueOf(array[1]);
	boolean isVegetarian = Boolean.valueOf(array[2]);
	int calories = Integer.valueOf(array[3]);
	
	Food obj = new Food(name, origin, isVegetarian, calories);

	return obj;
    }

    /**
     * Reads a file of food strings into a list of Food objects.
     *
     * @param fileIn a Scanner of a Food data file in the format
     *               name|origin|isVegetarian|calories
     * @return a list of Food
     */
    public static ArrayList<Food> readFoods(final Scanner fileIn) {
	
	ArrayList<Food> foods = new ArrayList<>();
	
	while (fileIn.hasNextLine()) {
		foods.add(readFood(fileIn.nextLine().strip()));
	}

	return foods;
    }

    /**
     * Searches for foods that fit certain conditions.
     *
     * @param foods        a list of Food
     * @param origin       the origin of the food; if -1, accept any origin
     * @param maxCalories  the maximum calories for the food; if 0, accept any
     * @param isVegetarian whether the food is vegetarian or not; if false accept
     *                     any
     * @return a list of foods that fit the conditions specified
     */
    public static ArrayList<Food> foodSearch(final ArrayList<Food> foods, final int origin, final int maxCalories,
	    final boolean isVegetarian) {

	ArrayList<Food> foodsearch = new ArrayList<>();
	
	boolean anyOrigin = false;
	boolean anyCals = false;
	boolean anyVeg = false;
	
	if (origin == -1)
	    anyOrigin = true;
	if (maxCalories == 0)
	    anyCals = true;
	if (isVegetarian == false)
	    anyVeg = true;
	    
	for (Food fd : foods) {
	    if (!anyOrigin && fd.getOrigin() != origin)
		continue;
	    if (!anyCals && fd.getCalories() > maxCalories)
		continue;
	    if (isVegetarian != fd.isVegetarian())
		continue;
	    foodsearch.add(fd);
	}
	
	return foodsearch;
		
    }

    /**
     * Writes the contents of a list of Food to a PrintStream.
     *
     * @param foods a list of Food
     * @param ps    the PrintStream to write to
     */
    public static void writeFoods(final ArrayList<Food> foods, PrintStream ps) {

	    for (Food food : foods) {
	        ps.println("Name: " + food.getName());
	        ps.println("Origin: " + food.getOrigin());
	        ps.println("Vegetarian: " + (food.isVegetarian() ? "Y" : "N"));
	        ps.println("Calories: " + food.getCalories());
	        ps.println();
	        }
    }
}
