# HotelBooker
A simple hotel reservation management application from scratch
HOTEL RESERVATION APPLICATION

MAIN COMPONENTS OF THE APP

The major components of the Hotel Reservation Application will consist of the following:

1. CLI for the User Interface. We'll use the Command Line Interface (or CLI for the user interface. For this, we'll need to have Java monitor the CLI for user input, so the user can enter commands to search for available rooms, book rooms, and so on.

2. Java code. The second main component is the Java code itself—this is where we add our business logic for the app.

3. Java collections. Finally, we'll use Java collections for in-memory storage of the data we need for the app, such as the users' names, room availability, and so on.


MY SUMMARY STEPS BEFORE WRITING CODE

1. USER SCENARIO: 
  
    1.1 Customer account

            1.1.1 Customer Requirements
                  
                  1.1.1.1 A unique email for the customer --> RegEx is used to check that the email is in the correct format
                  1.1.1.2 A first name and last name

    1.2 Searching for rooms
          
            1.2.1 Room requirements

                  1.2.1.1 Room cost
                  1.2.1.2 Unique room numbers
                  1.2.1.3 Room type  --> Enumeration: SINGLE, DOUBLE

    1.3 Booking a room
           
            1.3.1 Reserving a Room – Requirements -- use loops

                 1.3.1.1 Avoid conflicting reservations
                 1.3.1.2 Search for recommended rooms
                         Example: If the customers date range search is 1/1/2020 – 1/5/2020 and all rooms are booked, the system will search again for recommended rooms using the date range 1/8/2020 - 1/12/2020. If there are no recommended rooms, the system will not return any rooms.

    1.4 Viewing reservations


2. ADMIN SCENARIO:

    Displaying all customers accounts.
    Viewing all of the rooms in the hotel.
    Viewing all of the hotel reservations.
    Adding a room to the hotel application.  


3. ERROR REQUIREMENTS

    No crashing :: The application does not crash based on user input.
    No unhandled exceptions :: The app has try and catch blocks that are used to capture exceptions and provide useful information to the user. There are no unhandled exceptions.

    ********* There should exist at least one example in the model classes (Room, Customer, Reservation) that overrides both the hashcode and equals methods to utilize Collections functions 
    like contains.


ANALYSIS BEFORE WRITING A CODE

From taking above steps

OBJECT ORIENTED PROGRAMMING 

1. We need to create three model classes --> Customer, Room, Reservation classes

       Polymorphism :: The hotel reservation application contains the IRoom interface, which is implemented by the Room class.

       The FreeRoom class extends the Room class

       Access Modifiers :: ‘public’, ‘private’, and ‘final’.
 
       You should use the final for the data model classes variables and public methods

       There is at least one example of the model classes (Room, Customer, Reservation) overriding the toString method.


 * Room Class ::
       
       Create IRoom interface and create all the methods need for a room class, Here we are creating IRoom interface because we cam implement this class more than one time.

       All the methods creating inside the IRoom interface are abstract methods.

       Abstraction is defined as hiding the unnecessary details (implementation) from the user and to focus on essential details (functionality). It increases the efficiency and thus reduce 
  complexity.

       Room class implements IRoom interface and its methods.


2. We need to create a Service classes --> CustomerService , ReservationService classes

       Because our services are stateful they will need to provide static references, making them singleton objects.


3. We need to create a Resource Classes --> HotelResource, AdminResource classes

       we will need to create the resource classes so we can provide an intermediary between the UI components and services. Resource classes are used for defining the Application Programming Interface (API).

       APIs are a best practice and used to separate backend software from frontend software. This provides a clean separation in behavior and responsibilities for the software components.

       The HotelResource should have little to no behavior contained inside the class and should make use of the Service classes to implement its methods

4. Create Command Line Menus

        In this section, we will need to create the different command line menus, so the user can interact with the application from command line. Menus will be displayed to the user from the console with different numbered options. Next, we will be using the Scanner class to read in the user's responses.

