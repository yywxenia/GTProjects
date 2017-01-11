Design Information:

1.A grocery list consists of items the users want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity of items in the list (e.g., change from one to two pounds of apples).

To realize this requirement, I added to the design a class “User” with attribute “userName”. I also added a class “Grocery list” with attributes “listName”. The Grocery list class has 3 functions: add item, delete item, and change quantity of item. Also, a class “item” is added, with attributions “itemName” and “itemType”.


2.The application must contain a database (DB) of​ i​tems and corresponding i​tem types.

To realize this requirement, I added to the design a database. The database stores the information of items and item types. 


3.Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the item type (e.g., cereal), and the second level is the name of the actual item (e.g., shredded wheat). After adding an item, users must be able to specify a quantity for that item.

To realize this requirement, I added to the design a class “hierarchical list”. This class has 2 functions: getItemType: returns a list of items belong to the selected type; getItem: add the selected item to the list. User can also specify the quantity of that item at in this function.


4.Users must also be able to specify an item by typing its name. In this case, the application must look in its DB for items with similar names and ask the users, for each of them, whether that is the item they intended to add. If a match cannot be found, the application must ask the user to select a type for the item and then save the new item, together with its type.

To realize this requirement, I added to the design a “search” function between user and database. I also added a function “addNewItem” to the “item” class, which saves the new item to the database together with its type. 


5.Lists must be saved automatically and immediately after they are modified.

To realize this requirement, I added to the design a function “save list” between Grocery list and database. The function saves list to database if “addItem”, “deleteItem” or “changeQuantity” functions were performed. 


6.Users must be able to check off items in a list (without deleting them).

To realize this requirement, I added to the design an intermediate class “addToList”, with an attribute “checkOffMark” and a function “checkOff”. Items added to a list will be assigned a checkoff mark, the mark can be true or false to represent it status. The class also has an attribute “quantity” which stores the quantity of the item in a list.


7.Users must also be able to clear all the check­off marks in a list at once.

To realize this requirement, I added a “clearAllMarks” function to the class “addToList”. This function clears all checkoff marks at once. 


8.Check­off marks for a list are persistent and must also be saved immediately.

To realize this requirement, I added to the design a “save” function between “addToList” and the database. The function saves checkoff marks to database if “checkOff” or “clearAllMarks” were performed.


9.The application must present the items in a list grouped by type, so as to allow users to shop for a specific type of products at once (i.e., without having to go back and forth between aisles).

To realize this requirement, I added to the design a class “group” with attribute “groupName”. Group is a subset of Grocery list. When items are added to a list, they are assigned to a group based on their item types.
 

10.The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly farmer’s market list”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete lists.

To realize this requirement, I added 4 functions to the “User” class: “createList”, “renameList”, “selectList”, and “deleteList”.


11.The User Interface (UI) must be intuitive and responsive.

Not considered because it does not affect the design directly.

