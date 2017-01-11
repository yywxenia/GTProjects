DESIGH INFORMATION    yyan76    Yiwei Yan


1.  A grocery list consists of items the users want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity of items in the list (e.g., change from one to two pounds of apples):

We modeled the first requirements description into our design by adding classes named "GroceryList" and "Users". For Users, we create attributes like UserID, FirstName, LastName, Password and Email for login and identification; For GroceryList, we contains operations like AddQuality(), AddItem(), and DeleteItem() for Users to edit those GroceryLists. We will discuss Items that listed in the GroceryList in later requirements.



2. The application must contain a database (DB) of items and corresponding item types:

We add classes "SortedItems" and "ItemTypes" for the #2 requirement. Each SortedItem must belong to one ItemType, and all available items are sorted by Type for displayment. 

For SortedItems class, it contains attributes TypeID, ItemName, Picked(boolean). Besides, it has two operations that "DisplayAllItems()" to show all available items for Users to pick up, and "SearchByName()" which will be useful for requirement #4.



3. Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the item type (e.g., cereal), and the second level is the name of the actual item (e.g., shredded wheat). After adding an item, users must be able to specify a quantity for that item.

For #3, we define the relationship between class Users and SortedItems. If the listed items in SortedItems have been selected, the attribute "Picked" from "SortedItems" will turn to be "true". Also, the attribtes "TypeID and ItemName" of SortedItems match the description in #3. After desired items are added to the "GroceryList", the operation from GroceryList "AddQuantity" will allow Users to specify the quantities of items.



4. Users must also be able to specify an item by typing its name. In this case, the application must look in its DB for items with similar names and ask the users, for each of them, whether that is the item they intended to add. If a match cannot be found, the application must ask the user to select a type for the item and then save the new item, together with its type.

As we mentioned in #2, Users can specify an item by searching its name using "SearchByName()" operation from "SortedItem". If the searched list of items do not satisfy the Users, Users can add a new unknown item to the GroceryList. To model this part, we create a "UnknownItem" as a association class. It contains attriute ItemName and operation AddNewItem() for saving. "UnknownItem" has a relationship with "ItemType" where it can select a suitable Type for the unknown new item.



6. Users must be able to check off items in a list (without deleting them).
7. Users must also be able to clear all the check­off marks in a list at once.

In class "GeoceryList", there is an attribute named "CheckedoffMarks" and an operation "CheckOffItem()" and "ClearMarks()". If Users check off one item, the item's CheckedoffMarks in the GroceryList will become "true" without delete any item. Then, if Users use "ClearMarks()", they can clear all the CheckoffMarks of items at once.



5. Lists must be saved automatically and immediately after they are modified.
8. Check­off marks for a list are persistent and must also be saved immediately.

We assume that all modifications will be saved immediately. All classes are related to each other.



9. The application must present the items in a list grouped by type, so as to allow users to
shop for a specific type of products at once (i.e., without having to go back and forth
between aisles).

For this requirement #9, we define that the class "SortedItems" groups by TypeID in a list format. We also can use "SortedByType()" operation to set.  



10. The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly
farmer’s market list”). Therefore, the application must provide the users with the ability to
create, (re)name, select, and delete lists.

We create a class Named "AllGroceryLists". It contains attribute ListID, DateTime(the time that the list be created), ShopLocation(where Users want to shop). The "GroceryList" has an aggregation relationship with the "AllGroceryLists". Users can create/select/delete/rename an empty grocery list here by using operations like CreateList(), SelectList(), DeleteList(), and RenameList().



11. The User Interface (UI) must be intuitive and responsive:

We did not consider it because that it did not affect the design directly.



