# Grid Game Box

### Introduction

The goal of this project was to improve my Java skills with SOLID principles and Design Patterns in mind. It's a plain
Java project without any fancy tools, libraries, or frameworks. The Grid Game Box is designed so any grid-based game,
like Minesweeper or Tic-Tac-Toe, can be easily implemented and added to the box.

### Why I chose this project

A big part of my software development apprenticeship was concerned with the SOLID principles. Every week, I had a 4h
timeslot dedicated to learning one principle: reading articles and watching the hilarious, yet helpful, videos by Robert
C. Martin ("Uncle Bob"). I chose this specific task because I thought it would combine all the principles and show you
the benefits of using them (and highlight the friction if you don't)

### The Approach

I started at the core: the grid itself. I wanted to solve common problems (like handling edges, corners, and neighbor
logic) once and for all, so the games can simply trust the grid. Consequently, the grid is entirely agnostic of its
content. By separating the grid from the game logic, I created a clean cut between responsibilities, making the grid
fully reusable for any game type.

I took the same approach with Input Handling. Instead of repeating the "ask-convert-validate" loop in every game, I
encapsulated the process behind an interface. This allows the game runners to call one simple method and trust they will
get valid data in return.

### Testing

For the testing part of this project, I wanted to ensure that my core components are 100% reliable. I focused on writing
Unit Tests for the most critical logic.

By using Dependency Injection, I made my classes much easier to test. It allowed me to mock the services a class relies
on, so I could test the logic in isolation. A perfect example is
the [AbstractInputServiceTest.java](src/test/java/dev/saathoff/io/input/AbstractInputServiceTest.java).

This class handles the "retry logic" for all user inputs. The workflow follows a strict pattern:

1. Get user input

2. Convert (e.g., String to Integer)

3. Validate (e.g., is the coordinate within the grid?)

If any part of this chain fails, the error handling needs to catch it and restart the process. By testing this service
with mocked "plugged-in" converters and validators, I can be sure that the input process works perfectly for every game
in the box.

You can find the implementation for these tests
here: [AbstractInputService.java](src/main/java/dev/saathoff/io/input/AbstractInputService.java)

### AI

Did I use AI? The short answer is yes.

I view AI as a powerful tool for two specific areas of my development process:

**1. Handling "Boilerplate" & Implementation**

A good example is the AbstractGridRenderService.java. For a task like this, I provide the AI with the method
structure I want, and it generates the detailed implementation, like making the grid actually look good in the
console.
However, AI often tends to "dump" all the logic into one single method. My job as the developer is to take that raw
draft and refactor it. I split the code into smaller, logical responsibilities to ensure it actually fits the SOLID
principles I established for this project.

**2. AI as a Pair Programmer**

I also use AI to talk through my code, similar to Pair Programming. Sometimes when you spend hours on a problem, you
get "tunnel vision" and miss simpler solutions. Discussing my architecture with an AI helps me break out of that
narrowed view and consider different perspectives.

My Philosophy on AI
I believe the developer's role is to hold the big picture. You are the architect who designs the system. While the AI
can handle specific, well-defined implementation tasks, it is your responsibility to integrate those pieces into a
clean, maintainable architecture.

### Learnings

Through this project, I learned a lot about the power of Generic Typing in Java. I always knew how to use generics, but
creating them myself was not something I had to do often before. By building this project, I really got the hang of it
and experienced how useful they are for creating reusable code.

However, I also learned that you have to be cautious. It is easy to make code too complex to the point where it becomes
hard to understand. In general, abstraction comes at a cost, and finding the balance between flexibility and simplicity
is key.

To sum it up, I had a lot of fun programming this project. it was challenging, and it was great to practice my Java
skills again and refresh what I've learned during my apprenticeship


