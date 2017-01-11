# Use Case Model for Grocery List Manager (GLM)

**Author**: Yiwei Yan, Team 52

## 1 Use Case Diagram

<img src="https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team52/blob/master/GroupProject/Docs/AppPics/UC.png" alt="alt text" width="600" height="500">

Figure 1: User Case Diagram 

## 2 Use Case Descriptions

###Use case 1: Manage lists

#### Use case 1.1: Create a new grocery list
* Requirements: This use case allows the user to create a grocery list.
* Pre-conditions: Sucecessfully install the GLM application.
* Post-conditions: A new grocery list is created.
* Scenarios: 
    1. User click "NEW LIST" to create a new list.
    2. User Provide a list name and store name and "SAVE" the edition.
    3. Go back to the main menu, a created new list shows on the page.

#### Use case 1.2: Delete a grocery list
* Requirements: This use case allows the user to delete a grocery list.
* Pre-conditions: Sucecessfully install the GLM application.
* Post-conditions: A new grocery list is deleted.
* Scenarios: 
    1. User select/check-off a list from the lists management main menu.
    2. User click "DELETE LIST".
    3. The list is disapeared from the main menu and deleted from the database.

#### Use case 1.3: Rename a grocery list
* Post-conditions: A grocery list is renamed.
* Scenarios: 
    1. User click into a list from the lists management main menu.
    2. User click "EDIT" and provide a new list name/store name and "SAVE" the edition.
    3. Go back to the main menu, the selected list is renamed.

### Use case 2: Edit a grocery list
#### Use case 2.1 Add items to a grocery list
* Post-conditions: New items added to the grocery list.
* Scenarios: 
    1. User click into a list from the lists management main menu.
    2. User click "EDIT" then click "ADD".
    3. User select a item from the listed item page.
    4. Go back to the list page, the item is added to the list.
    5. User click "SEARCH" and type the desired item name.
    6. If item is found, click the item and go back to the list page. the item is added to the list.

#### Use case 2.2 Change items quantity
* Post-conditions: quantities of items are changed.
* Scenarios: 
    1. User click into a list from the lists management main menu.
    2. User click "EDIT".
    3. User select a item from the listed item page.
    4. User click "+" or "-" to change the quantity.
    5. User click "DONE" and item quantity is changed.

#### Use case 2.3 Delete items to a grocery list
* Post-conditions: Checked/selected items are deleted from the list.
* Scenarios: 
    1. User click into a list from the lists management main menu.
    2. User click "EDIT".
    3. User check-off items desired to delete.
    4. Click "DELETE" and "DONE", and items are deleted from the list.

### Use case 3: Add new items to database
* Post-conditions: Unavailable items are added to the database with item names and types.
* Scenarios: 
    1. User click "ITEMS" on management main menu.
    2. User provide a new item name with a type and click "ADD NEW ITEMS".
    3. New items added to the database.

### Use case 4: Search items
* Post-conditions: Matched items are displayed with providing item names.
* Scenarios: 
    1. User click into a list from the lists management main menu.
    2. User click "EDIT" then click "SEARCH".
    3. User can search items by typing item name.
