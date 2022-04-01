# Recipe Buddy App 

## What will this application do?

This application will help organize and store an ever-growing 
list of recipes in a collection. The user will be able to add/view
each individual recipe with ingredients, and instructions that are easy to follow.
As well, it will provide suggestions for possible recipes that can be made with given ingredients.

## Who is it for?

This recipe manager application will be helpful for people with kitchen skills of all
levels who are looking for a way to organize their favorite recipes. It will
also help with planning meals as the application will also be able to 
suggest recipes with ingredients on hand.


## Why a Recipe Manager?

I chose to create a Recipe Manager because cooking is a hobby of mine that I enjoy.
However, I often ran into the problem of not knowing what to cook
or what recipes I could make with ingredients I had on hand. The recipe manager
is helpful because it stores all possible recipes in a convenient list for the user.

## User Stories

- As a user, I want to be able to add my own recipe to a Cookbook, complete with a name, ingredients, and instructions.
- As a user, I want to be able to view a list of all  recipes that are contained in my cookbook. 
- As a user, I want to be able to access and read a detailed version of the saved recipe from the Cookbook.
- As a user, I want to be able to delete a recipe that was added to the Cookbook.
- As a user, I want to be able to save my added recipes to file before quitting the program.
- As a user, I want to be able to load and view a list of previously saved recipes.


## References/Process
 
Initially, my classes were structured such that Recipe took in Lists of 
Strings to handle the ingredient/instruction component. I later made the design
decision to reference the package structure from the FitLifeGym starter file to give the
recipe components their own classes.

### Phase 4 Task 2
Wed Mar 30 20:41:46 PDT 2022
Toast was added to the Cookbook.

Wed Mar 30 20:42:04 PDT 2022
Oatmeal was added to the Cookbook.

Wed Mar 30 20:42:15 PDT 2022
Dumpling was added to the Cookbook.

Wed Mar 30 20:42:18 PDT 2022
Toast was removed from the Cookbook.

Wed Mar 30 20:42:22 PDT 2022
Oatmeal was removed from the Cookbook.

Wed Mar 30 20:42:36 PDT 2022
Shrimp Fried Rice was added to the Cookbook.

Wed Mar 30 20:42:39 PDT 2022
Shrimp Fried Rice was removed from the Cookbook.

Wed Mar 30 20:42:42 PDT 2022
Dumpling was removed from the Cookbook.


###Phase 4 Task 3
Given more time, I would improve the general structure of my
program by implementing more abstraction to reduce duplication between certain methods. While all the classes are fairly cohesive,
I could use an abstract class or interface for the IngredientList/StepList classes to accomplish this.
