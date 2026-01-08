# Grid Game Box

## Introduction

The goal of this project was to improve my Java skill with solid principles and Design patterns in mind.
It's a plain Java project without any fancy tools, libraries or frameworks.
The Grid Game Box should be designed so any grid based game like Minesweeper or Tic Tac Toe can be easily implemented
and added to the game box.

## Why I chose this project

A big part of my software development apprenticeship was concerned with the SOLID principles. Every week I had a 4h
timeslot dedicated to learning and practicing one of the solid principles. I would read through articles, watch the
hilarious though really helpful videos of the "inventor" of those principles Robert C. Martin aka Uncle Bob. After
studying the principle in theory I would work on a small project which should be implemented in respect of the
Principles I've learned so far.
I chose this specific task because I thought it would really show you the benefits of using these principles and punish
you if you don't respect them.

## The Approach

I started at the core of a grid game - the grid itself. Important was to figure out what a grid needs to handle so it's
applicable for any kind of grid game. So basic operations like initializing a grid, getting specific grid cells and
positional information like getting all the neighbors are all responsibilities of a grid. If you have ever programmed a
grid, one problem always needs to be solved: How do I handle cells at the edges and corners? I really wanted
to solve this once and for all games so they can trust the grid that these cases are handled gracefully. Same goes for
the input of my games. Handling inputs from the console are always pain. I need to ask for the input, wait till the user
enters something that can be converted into the datatype that is needed and once that's done we have to validate if the
input is qualified to be processed further. instead of handling this whole process over and over again I tried to
encapsulate it behind an interface so the client, the actual game runners can call one simple method and can trust
they'll get something useful in return.

## Learnings



