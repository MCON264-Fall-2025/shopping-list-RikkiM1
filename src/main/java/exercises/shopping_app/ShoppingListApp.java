package exercises.shopping_app;

import edu.touro.mcon264.apps.collections.ArrayBasedList;
import edu.touro.mcon264.apps.collections.ListInterface;

import java.util.Scanner;

/**
 * Creates a ListInterface<ShoppingItem> instance.
 * Has a main method that runs a console application.
 * Provides a simple text-based menu to:
 * Add items (in sorted order).
 * View the current list.
 * “Shop” the next item on the list.
 * Exit the program.
 */


public class ShoppingListApp {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        ListInterface<ShoppingItem> shoppingList = new ArrayBasedList<>();
        boolean running = true;
        while (running) {
            int choice = Menu();
            switch (choice) {
                case 1:
                    System.out.println("Enter aisle: ");
                    String aisle = input.nextLine();
                    System.out.println("Enter item: ");
                    String item = input.nextLine();
                    ShoppingItem si = new ShoppingItem(aisle, item);
                    insertSorted(shoppingList, si);
                    break;
                case 2:
                    System.out.println("\nCurrent shopping list:");
                    for (int i = 0; i < shoppingList.size(); i++) {
                        System.out.println(shoppingList.get(i).toString());
                    }
                    break;
                case 3:
                    ShoppingItem next = shopNext(shoppingList);
                    if (next == null) {
                        System.out.println("All done! No items left to shop.");
                    } else {
                        System.out.println("Next item to buy: " + next);
                    }
                    break;
                case 0:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Unknown option. Please try again.");
            }

        }
    }

    /**
     * Inserts the given item into the list so that the list remains sorted
     * by aisle and then item name.
     */
    public static void insertSorted(ListInterface<ShoppingItem> list, ShoppingItem item) {
        // TODO: implement using list.get(i), list.add(i, item), list.size()
        for (int i = 0; i < list.size(); i++) {
            ShoppingItem current = list.get(i);
            if (item.compareTo(current) < 0) {
                list.add(i, item);
                return;
            }
        }
        // If item wasn't inserted, it's the largest → add at end
        list.add(list.size(), item);
    }

    /**
     * Returns the "next" item to shop and removes it from the list.
     * If the list is empty, returns null.
     */
    public static ShoppingItem shopNext(ListInterface<ShoppingItem> list) {
        // TODO: implement using ListInterface methods
        if (list.size() == 0) {
            return null;
        } else {
            ShoppingItem next = list.get(0);
            list.remove(0);
            return next;
        }
    }

    public static int Menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=== Shopping List Menu ===\n" +
                "1. Add item\n" +
                "2. Show current shopping list\n" +
                "3. Shop next item\n" +
                "0. Exit");

        int choice = sc.nextInt();
        sc.nextLine();
        return choice;
    }


}
