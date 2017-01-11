# Project 5:  Design Information #

## Description of Requirements ##

1. A grocery list consists of items the users want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity of items in the list (e.g., change from one to two pounds of apples).

   This requirement gives a hint as to the nature of several classes.  The classes I created based on this requirement was:
   *GroceryList:  This list consists of the second class (Item).
   *Item: These are the things to be added to the GroceryList.

   Because the GroceryList can contain multiple items, and Item is a "supplier" for the GroceryList "client", I made this a dependency with a one to many relationship

2. The application must contain a database (DB) of items and corresponding item types.

   I implemented this by creating a utility called Database.  The classes will hold the data, but use the database to maintain (save) the data (discussed further in item 5).

3. Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the item type (e.g., cereal), and the second level is the name of the actual item (e.g., shredded wheat). After adding an item, users must be able to specify a quantity for that item.

   In order for the user to pick from the item type, I added the class ItemType.  I considered just adding this as a variable in the Item class, but decided to make it its own class for future expandability.

   I also added the method "getTypeList()" in the Grocery List class to present the user with a list of ItemTypes, then when an ItemType is chosen, then the method getItemsByType(ItemType type) can be called to get a list of items. 

   Finally, the changeQty(int qty) method was added to allow the user to change the quantity after it is added.

4. Users must also be able to specify an item by typing its name. In this case, the application must look in its DB for items with similar names and ask the users, for each of them, whether that is the item they intended to add. If a match cannot be found, the application must ask the user to select a type for the item and then save the new item, together with its type, in its DB.

   I implemented this with the method `getItemByName(String name)` which will return a list of items with similar names.  It this doesn't work, the `getTypeList()` can be used for the user to select a type, then the `newItem(String name, Type item)` method can be used to add the new item (this is then saved in the DB).  

5. Lists must be saved automatically and immediately after they are modified.

   The `saveList()` method can be used to either manually save the list to the DB, or be called by other methods to save the DB after a list change.

6. Users must be able to check off items in a list (without deleting them).

   I first implemented this in the `Item` class, but since any item can be saved to multiple list (see item 10), I moved this to a separate class called `OnList` similar to what we see in P3L2 and the `CheckedOut` Association class.  The intent here is that an `Item` can be on several different `GroceryList`, but the quantity and whether of not it is checked, can be done in the Association class.  The association class holds the `isChecked` boolean variable, and a method `toggleCheck()` to change that variable.

7.  Users must also be able to clear all the check-off marks in a list at once.

   The `GroceryList` has a class called `clearAll()` that will clear all of the check marks. It does this by looking at the items on the list that `isChecked`, and calling the `toggleCheck()` on each of them.  

8. Check-off marks for a list are persistent and must also be saved immediately.

   The `toggleCheck()` method can call the `saveList()` method of the `GroceryList` class.

9. The application must present the items in a list grouped by type, so as to allow users to shop for a specific type of products at once (i.e., without having to go back and forth between aisles).

   The `sortList()` method sorts the list inside the data-structure by `ItemType`.  When the list is displayed by the GUI, it is given the list in order of type.

10.  The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly farmer’s market list”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete lists.

   This is the first place the `Application` class is needed.  It can hold multiple `GroceryList` objects in its data-structure. The `Application` class provides a method to `selectList(GroceryList list)` a list to view, `createList(String name)` to create a new list, `renameList(String name)` to rename a list (which actually uses the `changeName(String name)` method in the `GroceryList` itself), and a `deleteList(GroceryList list)` method to remove lists.  

11. The User Interface (UI) must be intuitive and responsive.

   Per the assignment instructions, we do not have to do anything about the GUI.  This requirement should have no impact on our class diagram for the system, other than, the design should be efficient for a GUI.  I believe my design meets this requirement.  

## Contact Information ##
* Name:  Jeremy D. Reid
* gatech ID:  JReid35
* Email:  JeremyDReid@gmail.com 
