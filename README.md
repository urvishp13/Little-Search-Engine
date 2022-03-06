# Little Search Engine

This project is solely written in Java. Please run it using Java 8.

## Overview

This project was an assignment given to Rutgers CS112 Data Structures students in the Fall of 2014. 
It was redone in November 2021 with no recollection
of how the project was initially completed. The focus of this project is to scan all the words in any number of documents of choice,
store them in a database (specifically a Hash Table), and put the number of times that word occurs in each document in descending order.
An additional feature of this project is to provide the top five documents of which two words of choice are in.
The focus of this project was Hash Tables.

## Code & Test Cases

The code written can be found in the [src](https://github.com/urvishp13/Expression-Evaluation/tree/main/src) folder. Look for the 
`@author Urvish Patel` tag before Classes to see the code I have written.
The code was tested using self-created documents that can be found in [this](https://github.com/urvishp13/Expression-Evaluation/blob/main/docs/testcases.md) 
file. If you want to create your own document, simply create a text file and populate it with the textual contents of your desire.
The files for the documents I used are in the [data](https://github.com/urvishp13/Expression-Evaluation/tree/main/data) folder. 

## How to Test

To test the program, access the contents of this repository as appropriate for you and follow the instructions presented to you in the 
(eg. command line, console in Eclipse, etc.). 

If running the program using the command line, go into the `bin` 
folder of this repo and type `java seach.LittleSearchEngineDriver`--as the .class files are already there.

To access the document files (.txt files) when running the program, create a file that has the full file names (eg. <file_name>.txt) of 
the files to be used. In order to use this file, you will have to backout/follow-directory-paths until you are 
in the directory of that file (eg. in the command line, you'll have to use `../data/<file_name>.txt`; in Eclipse, you'll have to use 
`data/<file_name>.txt`, etc.). ***Be sure to use the file that has all the file names in it and not the individual file documents.***