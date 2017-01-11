# Grocery List Manager (GLM) User Menual

**Author**: Yiwei Yan, Team 52

## 1.INTRODUCTION
The android mobile application GroceryListManager  (GLM) is intended to help you manage and interact with your groceries’ shopping lists more efficiently. This is a mobile application. After install this mobile app, you will be able to:
* a. Review your available grocery lists;
* b. Create/delete/rename your lists;
* c. Go into one specific list and edit the items in it with editing quantities;
* d. Add new shopping items by item types that cannot find in the system.

## 2. APPLICATION PAGES
### (1) List Management Page	
As shown in Figure 1-1, there are three buttons in the list management main menu page. “NEW LIST” for creating new lists, “DELETE” for deleting selected lists, and “ITEM” for reviewing available items grouped by item type.

<img src="https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team52/blob/master/GroupProject/Docs/AppPics/f1-1.png" alt="alt text" width="220" height="400">

Figure 1-1: List Management Page

### (2) Grocery List Page
As shown in Figure 1-2, there are four buttons in the grocery list page. “NEW LIST” for creating new lists, “DELETE” for deleting selected lists, and “ITEM” for reviewing available items grouped by item type.

<img src="https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team52/blob/master/GroupProject/Docs/AppPics/f1-222.png" alt="alt text" width="220" height="400">
<img src="https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team52/blob/master/GroupProject/Docs/AppPics/f1-2.png" alt="alt text" width="220" height="400">

Figure 1-2: Grocery List Page

### (3) Item Page (Grouped by item types)
When user want to add an item on the list, they can select/search from the database with items grouped by types.
Figure 1-3 illustrates item types “A,B,C,” etc., and Figure 1-4 shows the items under each type.

<img src="https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team52/blob/master/GroupProject/Docs/AppPics/f1-3.png" alt="alt text" width="220" height="400">
<img src="https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team52/blob/master/GroupProject/Docs/AppPics/f1-4.png" alt="alt text" width="220" height="400">

Figure 1-3, 1-4: Item Pages

### (4) Item Search Page 
On the grocery list page (Figure 1-2), user can click “SEARCH” button to search the database for available items by typing the name, which is shown in Figure 1-5.

<img src="https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team52/blob/master/GroupProject/Docs/AppPics/f1-5.png" alt="alt text" width="220" height="400">

Figure 1-5: Item Search Page 

### (5) Add Item Page (Can add unavailable items by providing name and type) 
As shown in Figure 1-6, after click “ITEM” from Figure 1-1, users can be directed to the add-item page. Users can provide a name and type of the unavailable items and add them back to the database.
The following sections will give an overview of the various operations of the GLM and how to perform those. 

## 3. START TO USE
Download the whole project GroceryListManager App “GLM” from folder “Team Project” at Github of Team52. After the mobile app has been installed and opened, it will prompt you directly to the main menu of List Management page (Figure 1-1). 
	
### (1) Creating a new list/lists
On the list management main menu as shown in Figure 1-1:
* Click “NEW LIST” and then direct to the creating grocery list page.	
* Provide a name for each created list with a store name (Figure 2-1).
* List cannot be created without a list name. There will be alert message with empty naming fields.	

<img src="https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team52/blob/master/GroupProject/Docs/AppPics/f2-1.png" alt="alt text" width="220" height="400">

Figure 2-1: Create a New List 

### (2) Deleting a list/lists
On the list management main menu as shown in Figure 2-2:
* Select a list/lists by clicking the check-off mark from the main menu of list management page. 
* Delete the particular list/lists by click “DELETE”.					 				

<img src="https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team52/blob/master/GroupProject/Docs/AppPics/f2-2.png" alt="alt text" width="220" height="400">

Figure 2-2: Delete List 

### (3) Adding items to a list
There are three ways to add items to lists:
######First:
* Click into a list.
* In the grocery list page, click "EDIT".
* In the grocery list edition page, click "ADD".
* Select an item type from a type list, and click to open the specific type.
* Select one item from the list of items under this specific type.
* Item is added back to the list.
* Click "DONE" button.
* Click "SAVE" button.

######Second:
* Click into a list.
* In the grocery list page, click "EDIT".
* In the grocery list edition page, click "SEARCH".
* Search an item by its name.
* A list of matched items show.
* Click one item from the list grouping by types alphabetically.
* Item is added back to the list.
* Click "DONE" button.
* Click "SAVE" button.

######Third:
If the items cannot be found in the database, we can add items into database (Figure 2-3):
* In the list management page main menu, click "ITEM".
* Type an item name and item type.
* Click "ADD", and this item will be add to the database. Users now can find the item in the database for selection/search.

<img src="https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team52/blob/master/GroupProject/Docs/AppPics/f2-3.png" alt="alt text" width="220" height="400">

Figure 2-3: Add New Items

### (4) Deleting items from a list

* Click into a list.
* In the grocery list page, click "EDIT".
* Check the items that user want to delete in the check-off marks.
* Click "DELETE" button.
* Items is deleted from the list.
* Click "DONE" button.
* Click "SAVE" button.

###(5) Searching items
As shown in Figure 3:
* Click into a list.
* In the grocery list page, click "EDIT".
* In the grocery list edition page, click "SEARCH".
* Search an item by its name.
* A list of matched items show.

<img src="https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team52/blob/master/GroupProject/Docs/AppPics/f3.png" alt="alt text" width="220" height="400">

Figure 3: Search items

###(6) Adding items to item page
* In the list management page main menu, click "ITEM".
* Type an item name and item type.
* Click "ADD", and this item will be add to the database. Users now can find the item in the database.

###(7) Changing items quantity
As shown in Figure 4:
* Click into a list.
* In the grocery list page, click "EDIT".
* Change the quantity of items by clicking "+" or "-".
* Click "DONE" button.
* Click "SAVE" button.

<img src="https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team52/blob/master/GroupProject/Docs/AppPics/f4.png" alt="alt text" width="220" height="400">

Figure 4: Change Items Quantity

### (8) Rename a list/lists
On the list management main menu, click into a list:
* Click "EDIT" and provide a new name and store name for the list.
* Click "DONE" and "SAVE"
* The name and store name for this list has been changed.					 				

<img src="https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team52/blob/master/GroupProject/Docs/AppPics/f1-22.png" alt="alt text" width="220" height="400">
<img src="https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team52/blob/master/GroupProject/Docs/AppPics/f5.png" alt="alt text" width="220" height="400">

Figure 5: Rename List
