# Design Document

**Author**: Jeremy Reid, Team 52
####Revisions
| Revision No. | Description |
| ------------ | ----------- |
| Revision 0 | Initial Issue |
| Revision 1 | Updated to more closely describe completed design, specifically, the class diagram section was updated to include descriptions for the major classes. |

**Updates**:  Revision 1, Document was updated to more closely describe the completed design.  Specifically, the Class Diagram section was updated to include descriptions for the major individual classes. 

## 1 Design Considerations

Several design consideration were taken into account while developing our design.  Chief among these design considerations were the the design specification provided to the team as a part of deliverable.  Next, and almost as important, the design considered the usability of the software.  The design also considered what types of platforms on which the design would ultimately be implemented.   Finally, modularity of the software was considered.  

The design specification provided to the team was the major design consideration.  The team looked closely at the specification to ensure all points were considered.  Of the eleven enumerated points, ten of them were largely focused on the design and *how* the software would work.  The final enumerated specification dealt with ensuring adequate performance of the software.  

Almost as important as the provided specification, was the consideration of the *usability* of the design.  It is important to our team that the software not only meets the requirement, but ultimately acts in an intuitive manner that make since to the user.  The user will *expect* the software to perform in a certain way, so our design had to take into consideration the user’s expectation..

Finally, our team considered the modularity of the design.  The platform for the design was not specified, but it was implied that the user would use the design “on the go”, further implying a mobile platform.  The mobile platform certainly lends itself to a modular design.  Additionally, our team spoke about what features might be desired in later versions, and made our design modular to accommodate future enhancements. 

The only issue that impacted our design consideration discussion was the use of a database.  The specification spoke of the database, but without knowing specifics of the platform, it was hard to make an accurate design for persistent storage of data.   

### 1.1 Assumptions

The provided design specification did not specify a platform on which the software would ultimately run, but one can infer that the design would be implemented on a mobile platform.  Due to previous projects by individuals on our team, we further assumed that the platform would be *Android* based.

Assuming that the design would be implemented on the *Android* platform, our team had further discussions on the use of a database to maintain persistent data storage.  None of our team had significant experience using the *Android* data store (SQLite), but knew this would be a viable option for data storage.  

No other significant project issues were identified for which our team needed to make assumptions to complete the design.  

### 1.2 Constraints

By making the assumption that the design would be implemented on the *Android* platform, we understood that there would be several constraints.  The user interface would constrain the size and layout of the application user interface.  Additionally, persistence of data on the mobile platform could constrain the software unless our team could correctly implement the database “backend”.  

### 1.3 System Environment

Knowing that the user interface would probably be a smaller format mobile device (phone), the team had to consider how the user would use the application.  The classes would need to support the mobile device form factor.  

A good example of the was the system environment was considered in the design is how a user might add a new item to the grocery list.  First, the user might tap on the “Add Item” button.  The application would then display a list of item types, allowing the user to choose a type.  After a type is chosen, a list of items of that type could be displayed.  When the user chose one of these items, then a menu could allow the user to input a quantity.  

Again, since the application will be implemented on the *Android* platform, the application must interact with the *Android* provided database, and must follow the “rules” that an application must follow when interacting with that database.  

By leveraging our knowledge of how “apps” on the mobile platform typically function with taps, swipes, and displaying user information, the team understands how the application should ultimately *feel*.  

## 2 Architectural Design

This application allows a user to maintain a grocery list on a mobile device.  The application can host multiple grocery lists, grouping the lists by item type when displayed.  Users can check items off of the list as they are collected, as well as add new items, delete items, change quantities, etc.  

### 2.1 Component Diagram

![Component Diagram](https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team52/blob/master/GroupProject/Design-Team/ComponentDiagram.png "Component Diagram")

### 2.2 Deployment Diagram

![Deployment Diagram](https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team52/blob/master/GroupProject/Design-Team/DeploymentDiagram.png "Deployment Diagram")

## 3 Low-Level Design

Previous sections of this document spoke of the design on a high level.  This section speaks on a lower level about the design.  First we’ll look at the class diagram, followed by a brief discussion, then finally a look at the User Interface.  

### 3.1 Class Diagram

![Final Class Diagram](https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team52/blob/master/GroupProject/Docs/AppPics/RevisedClassDiagram.png "Final Class Diagram")

####DBHandler
The DBHander class is a class for the project to use to interface with the SQLite Database.  All of the SQL interactions happen in this class.  There are individual methods for each of the interactions the rest of our application requires.

####GList
The GList class is a very basic class with "getters" and "setters" to define a grocery list.  On a high level, the grocery list is made up of a "List Name" and an associated "Store Name".

####GroceryList
The GroceryList class is the "worker" class that is handles the "front end" user enterface for the grocery list.  It handles the display and interaction of a specific list.  It allows the user to edit the list, change quantities, interact with checkboxes, and all other activities associated with the grocery list.

####ListManager
The ListManager is the class that holds the list of grocery list.  It is closely related to the first screen the user encounters when opening the application which allows the user to select which grocery list to work with.  From the ListManager class, the user can select a list, create a list, rename a list, delete a list, or add a new item which is available from all grocery lists.

####Item
The item class defines an item on a specific grocery list.  It is made up of a "MasterItem" a quantity, whether or not the item is checked, and finally, the grocery list the item is associated with.  There are also the necessary "getters and setters".

####MasterItem
The *MasterItem* is a class to define the basics of all items.  Master items have only a name and an item type only. 

####ItemSelector
The *ItemSelector* is used by the user interface to allow the user to select a specific item to add to a list.

####TypeSelector
The *TypeSelector* is allows the user interface to allow the user to select form a list of all types in order to drill down to a specific item.

####NewList
The *NewList* allos the user to create a new grocery list.  This class handles both the user interfaces as well as interactions with the DBHandler to create a new list.

####newMasterItem
The *newMasterItem* allows the user to create a new item by defining a name and a type.  It's important to note that when a user enters a type that hasn't been entered into the database before the type will be incorprated, and appear on the type selection page.  

####SearchItem
The *searchItem* allows the user to seach for a specific item in the database.  

### 3.2 Other Diagrams

No other diagrams have been defined.  

## 4 User Interface Design

![User Interface Design](https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team52/blob/master/GroupProject/Docs/AppPics/newlist.png "User Interface Design")
![User Interface Design](https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team52/blob/master/GroupProject/Docs/AppPics/search.png "User Interface Design")
![User Interface Design](https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team52/blob/master/GroupProject/Docs/AppPics/item.png "User Interface Design")
![User Interface Design](https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team52/blob/master/GroupProject/Docs/AppPics/type.png "User Interface Design")
![User Interface Design](https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team52/blob/master/GroupProject/Docs/AppPics/inlist.png "User Interface Design")
![User Interface Design](https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team52/blob/master/GroupProject/Docs/AppPics/listmanager.png "User Interface Design")
