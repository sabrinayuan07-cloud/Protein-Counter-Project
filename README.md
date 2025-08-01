# Protein Tracker Application

## Proposal

What will the application do?
- The application will help people keep track of their daily protein intake without needing to look up the amount of protein for each item in their meal. At the end of the day, they can save their intake, which will be stored in a calendar so they can track their protein intake over time. 

Who will use it?
- Anyone who wants to track their protein can use it.

Why is this project of interest to you?
- I decided to choose this project idea because I personally find it very hard to keep track of my proteins every day and I always have to look up the number of protein depending on what I'm eating and and to make sure that I meet my protein goals at the end of the day, which can be pretty

## User Stories
- As a user, I want to be able to add a food item to my daily protein tracker
- As a user, I want to be able to specific amount of X food I ate
- As a user, I want to be able to view my total protein intake and list of food I ate at the end of the day
- As a user, I want to be able to set a protein intake goal and see my progress throughout the day

- As a user, when I select the quit option from the application menu, I want to be reminded to save my meal plan list to file and have the option to do so or not.
- As a user, when I start the application, I want to be given the option to load my meal plan list from file

## Instructions for End User
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking at the option : "Add food item"
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking the option to only view high protein foods
- You can locate my visual component by looking at the pop-up image that shows up once you have added a food item, where it will say "Food item successfully added"
- You can save the state of my application by clicking the button "save file" 
- You can reload the state of my application by clicking the option to "load file" at the beginning

## Phase 4: Task 2
Sat Mar 29 19:10:17 PDT 2025
33.0g of Bacon added to meal plan
Sat Mar 29 19:10:20 PDT 2025
22.0g of Salmon added to meal plan

## Phase 4: Task 3
If I had more time to work on my project, I would refactor the methods in my ProteinCounterApp class where I call JsonReader and JsonWriter, specifically when saving and loading a meal plan from file. I would place these methods in a separate class in order to have the ProteinCounterApp class focus more on creating the application and less and saving and loading the file. this will help to improve code readability.