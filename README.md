# Little Search Engine

This project is solely written in Java. Please run it using Java 8.

## Overview

This project was an assignment given to Rutgers CS112 Data Structures students in the Fall of 2014. 
It was redone in November 2021 with no recollection
of how the project was initially completed. The focus of this project is to scan all the words in any number of documents, gather and
store them in a database (specifically a Hash Table) so there are no duplications. Each word is associated with the documents and the
number of times it occurred in those documents and this information is listed in descending order based on the number of occurrences.
An additional feature of this project is to provide the top five documents of which two words of choice are in.
The focus of this project was Hash Tables.

## Code & Test Cases

The code written can be found in the [src](https://github.com/urvishp13/Little-Search-Engine/tree/main/src/search) folder. Look for the 
`@author Urvish Patel` tag before Classes to see the code I have written.
The code was tested using self-created documents; if you want to create your own document, simply create a text file and 
populate it with the textual contents of your desire.
The files for the documents I used are in the [data](https://github.com/urvishp13/Little-Search-Engine/tree/main/data) folder. 

## How to Test

To test the program, access the contents of this repository as appropriate for you and follow the instructions presented to you in the 
(eg. command line, console in Eclipse, etc.). 

If running the program using the command line, go into the `bin` 
folder of this repo and type `java seach.LittleSearchEngineDriver`--as the .class files are already there.

To access the document files (.txt files) when running the program, enter the file that has the full file names (eg. <file_name>.txt) of 
the files to be used--if using the documents I used to test the code, this would be `docs.txt`. In order to use this file, 
you will have to backout/follow-directory-paths until you are 
in the directory of that file (eg. in the command line, you'll have to use `../data/<file_name>.txt`; in Eclipse, you'll have to use 
`data/<file_name>.txt`, etc.). ***Be sure to use the file that has all the file names in it and not the individual file documents.***