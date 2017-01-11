#Design Discussion

##Individual Designs
From the three design diagrams below,  all teammates provide a high level view of the system that generally satisfy the requirements. Specifically, design 1 from Jeremy is simple and straightforward which holds the least number of classes; design 2 from Chunqing shows the relationships of classes and database; design 3 from Yiwei utilizes domain knowledge on create attributes of User class.

###Design 1 (Jeremy Reid)

####Pros
This design is simple and makes it clear to the reader the intent of the classes and associated methods.

####Cons
The GroceryList class seemed to take on too much work including many methods that might better be better positioned elsewhere in the code to make the design more efficient.  The design itself didn’t speak to specifics on how the database would be used.

####Design Diagram
![jreid35 design](https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team52/blob/master/GroupProject/Design-Individual/JReid35/jreid35.png "JReid Design")

###Design 2 (Chunqing Yuan)

####Pros
Designed an association class “AddToList” to manage checkoff marks and quantity. Used more detailed database structure.

####Cons
Unnecessary “Group” class and “Hierarchical List” class. Missing “ItemType” class. Unnecessary relationship between “user” and “addToList” classes. Connection to database structure made digram confusing. 

####Design Diagram
![cyuan47 design](https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team52/blob/master/GroupProject/Design-Individual/cyuan47/cyuan47.png "cyuan47 Design")

###Design 3 (Yiwei Yan)

####Pros
Added attributes like "FirstName", "LastName", "UserID", "Paaword" for "User" based on domain knowledge. Created a seperate "AllGroceryList" that hold operations create/delete/(re)name/select lists.

####Cons
Name of class "SortedItems" is confusing, which could be more clear using "Item". Class "UnknownItem" is not very necessary.

####Design Diagram
![yyan76 design](https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team52/blob/master/GroupProject/Design-Individual/yyan76/yyan76.png "yyan76 Design")

##Team Design

###Design Diagram
![Group design](https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team52/blob/master/GroupProject/Design-Team/group_design.png "Group Design")

###Commonality
1. All three designs implemented a class to hold multiple grocery lists;
2. All three designs had a similar GroceryList class holding operations for User to add/delete items and change quantity for items;
3. Two designs used an association class to represent the items on a GroceryList. For instance, "onList" in Jeremy's and "AddToList" in Chunqing's.
4. Two designs used a sorted list for implementation.

###Difference
1. Yiwei's design for User class included attributes username, password, email, etc.;
2. Three designs include similar operations, but put those certain operations under different classes;
3. Three designs used different level of details on how to show database in diagram.

###Design Information
1. A grocery list consists of items the users want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity of items in the list (e.g., change from one to two pounds of apples).

   We modeled the first requirements description into our design by adding classes named `GroceryList`, `User` and `Application`. For `User`, we create attributes like `UserID`, `FirstName`, `LastName`, `Password` and `Email` for login and identification; For `GroceryList`, we contains operations like `dddItem()`, `deleteItem()`, `changeQty()` for `Users` to edit those `GroceryList`. The quantity is a variable in the `AddToList` class which can be changed by the `changeQty()` method in the `GroceryList`.

2. The application must contain a database (DB) of items and corresponding item types.

   We create a `Database`. We add classes `Item` and `ItemType`, and each `Item` must belong to one `ItemType`. 


3. Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the item type (e.g., cereal), and the second level is the name of the actual item (e.g., shredded wheat). After adding an item, users must be able to specify a quantity for that item.

   We describe the relationship between `Application` and `GroceryList`. This is done by displaying a list of items type (for the user to choose) using `getItemTypes()`.  Once the user chooses a specific type, then that type is used to display items of a specific type using `getItemsByType(type)`. Users can add quantities for items by using `changeQty()`.

4. Users must also be able to specify an item by typing its name. In this case, the application must look in its DB for items with similar names and ask the users, for each of them, whether that is the item they intended to add. If a match cannot be found, the application must ask the user to select a type for the item and then save the new item, together with its type, in its DB.

   Users can specify an item by searching its name using `SearchByName()` operation from `GroceryList`. If the User cannot find the desired items, User can add the new items to the DB using `addNewItem()` operation.

5.  Lists must be saved automatically and immediately after they are modified;  #8. Check-off marks for a list are persistent and must also be saved immediately.

   For requirement #5, all modifications will be saved to the Database. All our classes have relationships to the Database. So does requirement #8.


6. Users must be able to check off items in a list (without deleting them).
7. Users must also be able to clear all the check-off marks in a list at once.

   We create an association class between `GroceryList` and `Item` named `AddToList`. For requirement #6, Users can check off items using operation `checkOff(item)`.  For requirement #7, Users can clear all check-off marks using `clearAllMarks()`.

8. Check-off marks for a list are persistent and must also be saved immediately.
   See #5.

9. The application must present the items in a list grouped by type, so as to allow users to shop for a specific type of products at once (i.e., without having to go back and forth between aisles).

  For requirement #9, we define an operation `sortItem()` under `GroceryList` to group items by type.


10. The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly farmer’s market list”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete lists.

   The `Application` class can support multiple lists for each User. `Application` has operations `selectList()`, `createList()`, `renameList(String name)`, and `deleteList()`.  The relationship between `User` and `Application` is many to 1.

11. The User Interface (UI) must be intuitive and responsive.
   We did not consider it because that it did not affect the design directly.

##Summary

During discussion, all team members shared their thoughts on their individual designs.  Team members talked about pros and cons of the individual designs and made agreements on the fintal team design.

Our comparison of designs started with a creating a tabel, with a column for each team member, and a row for each class.  This allowed us to deside which classes were similar, and which were different.  From this table we were able to decide which classes should remain in the final design.

After placing our classes into a design editor, then we discussed the best relationships between each class.  We discovered differences, but after discussion, decided on relationships that best represented how our model should function.  

Finally, we discussed the placement of methods within the classes.  This forced us to reconsider the classes and relationships to ensure that each method could correctly "see" variables and methods from other classes as well as ensure that relationships were in place of support our methods.  

