# Test Plan

*This is the template for your test plan. The parts in italics are concise explanations of what should go in the corresponding sections and should not appear in the final document.*

**Author**: Yiwei Yan, Team 52
####Revisions
| Revision No. | Description |
| ------------ | ----------- |
| Revision 0 | Initial Issue |
| Revision 1 | Removed tests for user registartion/login activities.  Also included additional test results. |

## 1 Testing Strategy

### 1.1 Overall strategy

#### 1. Introduction
An Android application that manages a user’s grocery list will be built. The functionality of this application will allow the users to create, delete, rename and select the list. Users’ can add, delete and change quantity of items in the list. In addition, the users can choose to “check-off” items and clear the check-off marks in lists. Lastly, multiple users can use the application hosting one or more of their own grocery lists.

#### 2.  Testing Process and Strategies
The testing process for the GroceryList Management Android Application will be broken down into different phases:

*Unit Testing*
This unit testing will evaluate the individual module/unit of the application. Unit testing is done at the developer's site to check whether a particular piece/unit of code is working fine. We will test the components mentioned in section “2.1 Component Diagram”.
For our application, unit testing will be done by developer team (xxx and xxx) to check the each piece of unit code using Junit, which is a testing framework that was developed for the Java programming providing an easy way for unit tests.

*Integration Testing*
This test will evaluate the interaction among different modules/units in unit testing. It is performed when various modules are integrated with each other to form sub-systems.
In this phase, we will mostly focuses in the design and construction of the software architecture. The developer team will perform this phase also using JUnit.

*System Testing*
This test will test the complete system of the application. There might be a set of test cases focused on evaluation of the user interface functionality. It will be done by tester team.

*Regression Testing*
Regression testing of this application will consist of retesting the successful execution of the system testing. Any errors detected in the formal testing process will be recorded in the bug tracker.
Retesting for fixed bugs will be done by respective tester team once it is resolved by respective developer team and bug/defect status will be updated accordingly.

### 1.2 Test Selection

*Black box testing*
Internal system design is not considered in this type of testing. Tests are based on requirements and functionality: Therefore, we will use this methodology for system testing which is tested as per the requirements and based on overall requirements specifications, covers all combined parts of a system, regression testing.

*White box testing*
This testing is based on knowledge of the internal logic of an application’s code. Tests are based on coverage of code statements, branches, paths, conditions: Therefore, we will use this method for unit testing, integration testing.

### 1.3 Adequacy Criterion

Branch and Condition Coverage: 
Branch Coverage: On Integration Testing, System Testing 
Condition Coverage: On Unit Testing

*Modified Condition/Decision Coverage: On the important combinations of conditions instead of all of other details.Below is a simple sample:

| TEST CASE | CREATE LIST | ADD ITEMS | CHANGE QUANTITY | CHECK OFF | OUTCOM |
| --------- | ----------- | --------- | --------------- | --------- | ------ |
| 1 | true | true | true | true | true |
| 2 | false | true | true | true | false |
| 3 | true | true | true | true | false |
| 4 | true | false | true | true | false |
| 5 | true | true | false | true | false |
| 6 | true | true | true | false | false | 

### 1.4 Bug Tracking

*Describe how bugs and enhancement requests will be tracked.*

During the testing process, all the issues found while testings will be logged into bug tracker (Bugzilla). We will define the percentage of accomplishment for each level, and make sure we gain enhancements and updates.

### 1.5 Technology

Since the implementation of our application is based on Java, JUnit will be used to test the application.

## 2 Test Cases

| TEST AREA | PURPOSE | STEPS | EXPECTED RESULTS | ACTUAL RESULTS | PASS(P) / FAIL (F) |
| --------- | ------- | ----- | ---------------- | -------------- | ------------------ |
| Functional | Create List | Click "CreateList" button to create an empty new list. | Create an empty new grocery list. | New list created | P |
| Functional | Delete List | Create "DeleteList" button to delete an existing list. | Deleteted a list from the user's list. | List Deleted | P |
| Functional | Select List | Click a list from the list menu. | The specific list data presented. | List presened | P |
| Functional | Rename/Name List | Click "NameList" button to name/rename a list. | Successfully name/rename a list. | List renamed | P |
| Functional | Pick Item | Click "Items" button to display all items sorted by types.  Pick one. | Successfully display items; The picked item should be highlighted. | Items selected, list displayed | P |
| Functional | Add Item | Click "Items", and type an item name on the seach text dialog; Select a type for this item and add it to the system with the name and type. | If searched item not available, add item to the system with type classification. | Items added successfully | P |
| Functional | Present Item by Type | Click "Items". | Items clustered by types and display in a list format. | Items displayed by type | P |
| Functional | Add Item to List | Click into one list from the lists menu of user page, click "Item" button. Select items from the listed items, and click to add to the list. | Add selected item to the specified list. | Item added | P |
| Functional | Delete Item from List | Click into one list from the lists menu of user page, click "RemoveItem button.  Select items form the listed items (with checkmark)  and click "Delete" to delete from the list | Delete selected item from the specified list. | Item Deleted | P |
| Functional | Give Quantity to Item | Click into one list, click "+ or -" button beside each item qty | Add quantity for items. Users can change this quantity. | Qty changes and saves | P |
| Functional | Check Off | Click into a list with items.  Click the small check-off blank on the left side of items. | Selected items should have check-off marks on the left. | Check marks created | P |
| Functional | Clear CheckOff | Click into a list with items with check-off marks.  Click "ClearMarks" button. | Cear all check-off marks in the list. | List Checks cleared | P |
| Functional | Save Item | Lof out of the application. Log into the application.  Open a newly created list which created before the first log out. | All modifications should be saved automatically. When re-opened, the newly created list, allinfor should still be there. | List Saved | P |

## 3 Test Cases and Results on Version 1.0 of Application:

Time period: Oct 8 to Oct 14

| TEST AREA | PURPOSE | STEPS | ACTUAL RESULTS | PASS(P) / FAIL (F) |
| --------- | ------- | ----- | -------------- | ------------------ |
| Functional | Show Lists Button | Click "Show List" on the user management main page | Guide user to the page that show all existing lists of the particular user | P | 
| Functional | Show Grocery Lists Button | Click "Show Grocery Lists" button | Guide user to the page that show the items added to the grocery list | P | 
| Functional | Check-off Items | Click boxs on the left side of items in the grocery list | Items be selected  | P  | 
| Functional | Clear Check-off Marks | Click "Clear" button on the top of list page | all selected item marks are removed  | P | 
| Functional | All List Button |Click "All List" on the user management page | all existing grocery lists will be shown  | P  | 
