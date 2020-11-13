02# Hotel Reservation System #

## Requirements ##

- We are building a console-based application with no GUI or user interaction (menus and such) required.
- Project should be created in your personal space in GitLab
- Project should utilize usage of Core Java, Maven (to build project and execute tests), TestNG (All the use cases, including negative and boundary tests, should be covered and verified with functional tests using TestNG runner), Spring dependency injection and Spring Beans (see next item)
- The Hotel can be created with any given amount (and types) of rooms during the initialization. All predefined rooms should be created as Java (or XML) Spring beans
- Every room is identified by its number (216, 3A, etc.) can have a price
- Different types of rooms should be available. Example:
    - King Room (1 king bed)
    - Queen Room (1 queen bed)
    - Double Queen Room (2 queen beds)
    - Double Full Room (2 full beds)
- A Hotel Customer is identified by their Name 
- Hotel Service maintains the reservation list with rooms and corresponding customers who made a reservation
- Hotel Service can return a search results of various types
    - All rooms of all types
    - Search rooms by availability
    - Search rooms by room type
    - Search rooms reserved by particular user
- Hotel Service can make a room reservation for a specific user.
- Reserved rooms should change their room status from "available" to "occupied"
- Hotel Service allows to cancel any reservation and the room can become available again
- Hotel Service returns meaningful responses/exceptions in irregular situations, such as:
    - room of specific type is not available during reservation
    - customer cancels reservation for a room he didn’t book
    - customer doesn’t have enough money to make a reservation (see below)
- “Nice to have” Feature - Customer funds and Room price (not mandatory):
    - Each Room type has a price.
    - Each Customer is created with a certain amount of money. 
    - During the reservation the correct amount is subtracted from the user. If user doesn't have enough money - hotel doesn't allow the reservation.
    - If user cancels the reservation - money is returned back to the user.
## Verification ##
- All potential use cases (public APIs provided by Hotel service to search for rooms, making/cancelling reservations, etc.), including negative and boundary tests, should be covered and verified with TestNG.
- Please follow these basic test automation best practices:
    - All tests should be independent
    - Use data-driven instead of repeated tests
    - Use soft assertions if you need to make a list of related checks on the same web page
    - Name your tests in a meaningful way
