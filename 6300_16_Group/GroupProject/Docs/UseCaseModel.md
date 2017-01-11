# Use Case Model

**Author**: Chunqing Yuan, Team 52
####Revisions
| Revision No. | Description |
| ------------ | ----------- |
| Revision 0 | Initial Issue |
| Revision 1 | Removed tests for user registartion/login activities (Originally Use case 1) |

## 1 Use Case Diagram

![Use Case Model](https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team52/blob/master/GroupProject/Design-Team/UseCaseModel.png "Use Case Model")

## 2 Use Case Descriptions

###Use case 1: Create a grocery list

*Requirements: This use case allows the actor (user) to create a grocery list.
*Pre-conditions: The user should log into the system.
*Post-conditions: A new grocery list is created.
*Scenarios: 
The user starts to create a new grocery list by selecting “Create new list” and enter a name for the list.
A new blank grocery list is presented to the user. The user can add items into the list by selecting “Add item”.
To add an item into the list, the user first select the item type from a drop-down list. All items with the selected item type will then show up. The user then select the desired item from the item list, choose the quantity, and then add it to the grocery list.
If the desired item is not in the list, the user can choose “add new item” to create a new item and add it to the list. By selecting “add new item”, the user will be able to enter the item name, item type, a description and/or a photo.
When finishing adding items, the user can save the grocery list. 

###Use case 2: Manage grocery lists

*Requirements: This use case allows the actor (user) to manage existing grocery lists.
*Pre-conditions: The user should log into the system.
*Post-conditions: A grocery list is renamed, modified or deleted.
*Scenarios: 
If the user wants to rename a grocery list, she can select the list, choose “Rename” and enter a new name for the grocery list.
If the user wants to delete a grocery list, she can select the list, and choose “Delete”.
The user can also copy an existing list by selecting a list and choose “Copy”.
The user can modified an existing list, such as add another item, delete an item or change the quantity of an item. These functions are the same as in the following Use case 4.

###Use case 3: Use a grocery list

*Requirements: This use case allows the actor (user) to use a grocery list: check and uncheck an item, add, delete or edit an item.
*Pre-conditions: The user should select an existing grocery list.
*Post-conditions:
*Scenarios: 
When using a grocery list, the user should have an existing list selected. All items are unchecked at the beginning. 
If the user find an item in the store, she can check the item. The item will be marked as “checked”.
If the user want to change the quantity of an item, she can select the item and choose “Change quantity”.
If the user no longer wants an item, she can choose the item and select “Delete”.
If the user wants to add another item in the list, she can choose “Add item” like in Use case 2.
The user is able to clear all check marks at a time by choosing “Clear all marks”.
When the user finishes shopping, she can mark the entire list as “Done”.