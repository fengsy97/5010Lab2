Please upload the zip file containing your program and the accompanying complexity analysis of your code. Follow the assignment instructions below.

Assignment 2: Solving the Brewery Problem

Scenario

A brewery control system is composed of an inventory system, a production system, and a recipe library. Each component has specific tasks it must complete successfully. In this assignment, you are responsible for designing a program that manages each of these systems, each with their own subset of classes and methods. Below is a general description of what each “system” should simulate. 

The Production System: monitor the creation of a beverage from mixing ingredients all the way to bottling.
The Inventory System: monitors beverages on hand and all ingredients needed to make beverages.
The Recipe Library: contains instructions for making any given beverage that the brewery produces.
Note that each of these systems either relies on information from another system or provides an output that forces the other system to update its data. How do we create an efficient object-oriented system to manage all of the tasks inherent with running a brewery?

While you are not being given a specific design pattern to work with for this lab, you are required to adhere as strongly as possible to the following restrictions that were imposed on the original Brewery Problem. While the designers in the original problem treated these restrictions as absolute, you will not be punished if your system cannot find a means of operating without one or two instances of an imposed restriction. That is, you may need to use an external library to manage data storing techniques for the recipe library depending on your design strategy (I’m not going to require you to rewrite the code for managing hashtables from scratch). Or you might decide you need flexibility elsewhere in your program. Whatever you do, be sparing and limit yourself in how and where you choose to break a restriction. Because this is a simulation, you also are allowed to use limited importing to manage user input and information output. No need to re-invent the Scanner class either.


Restrictions:

Variables can be defined from a built-in type (as in, a data type that already exists in Java) or can be references to objects created from the classes defined in each design. Imported functionality from external libraries should be avoided, meaning that Arrays and Hashmaps are discouraged.
Special meaning should not be ascribed to specific values of variables.
Functions or procedures outside of the methods of an object or class should not be used.
Avoid using global variables as much as possible (ideally, use none).
Methods should (to the best of their ability) provide no more than one item of information. If more than one is provided, then it should be a necessary consequence of the service being performed by that method.
Scenarios: Given these restrictions, attempt to write a program that solves the problems present across these scenarios.

Add ingredients to the Inventory: There needs to be a way to track the ingredients on hand in the brewery’s inventory and, based on what beverages are made, subtract ingredients and re-order new ingredients when supply drops below a certain threshold.
Bottle a batch of beverages: A beverage is done brewing and needs to be bottled. The container that was holding the batch is marked as “dirty” for the next scenario.
Clean container: A user or client notifies the system that a dirty container has been cleaned, and that action is remembered by the system.
Make batch: A user or client needs to be able to instruct the system to create a batch of a beverage. They need to be able to indicate the size of the batch (how many servings?), provide a recipe, and then allow the system to verify that enough ingredients are available to complete the task.
Create Recipe: A user or client should be able to define a new recipe and store it in the library. The recipes should indicate ingredient quantities and how many bottles of the beverage it will produce.
Deadlines: This assignment is due by Wednesday, October 11, at the end of the day. You are not required to develop a Graphical User Interface to display outputs, but are welcome to try out components of this if you desire. Those components can belong to the javax or swing libraries and can be imported with the specific goal of just managing display and user input. If you do not implement a GUI, then you are expected to manage inputs and outputs through the command line / terminal.

Deliverables: In addition to turning in the program, you must turn in a complexity analysis saved as a PDF file. Your complexity analysis should speak to each of the metrics outlined below. Each metric should have about a paragraph of description for it. The paragraph should note how you applied this metric to your own code. Do not bother applying the metrics to imported packages if you end up using Scanner or a GUI package.

Metrics: There are a number of metrics the original Brewery Problem scored itself on. Consider these metrics the guidelines for your own grading criteria. It is not expected that you develop a perfect program nor that you compete with your colleagues. However, you should strive to satisfy these metrics to the best of your abilities. Review them below:

Weighted Methods per Class - A class’s complexity is determined by its attributes and methods. In short, how many lines of code are there in each method? Complexity can also be measured through the cyclomatic complexity of a class and its methods. That is, how many linearly independent paths can be measured in a program’s source code? This measurement is typically estimated using a program’s control flow graph.
Depth of Inheritance Tree - The more classes that a subclass inherits (remember how far down JFrame was nested?) the more methods it also inherits, thus giving that class more complexity.
Number of Children - How many child classes directly inherit from a class? This determines a class’s potential impact on the rest of the program.
Coupling between Objects - How many objects (that are not connected through inheritance) are coupled through other means? That is, how many objects are dependent on another object’s functionality?
Response for a Class - How many methods does an individual class contain, and how many other methods does it invoke through calling other objects or another class’s methods?
Cohesion across Methods - What attributes do each method in a given class operate on, and are they incredibly different in their operations or relatively uniform?