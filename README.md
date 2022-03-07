# Little Search Engine

This project is solely written in Java. Please run it using Java 8.

## Overview

This project was an assignment given to Rutgers CS112 Data Structures students in the Fall of 2014. 
It was redone in November 2021 with no recollection
of how the project was initially completed. The focus of this project is to scan all the words in any number of documents and gather and
store them in a database (specifically a Hash Table) so there are no duplications. Each word is associated with the documents it occurred
in and the
number of times it occurred in those documents, and this information is listed in descending order based on the number of occurrences.
An additional feature of this project is to provide the (maximum) top five COMBINED documents of two words of choice.
The focus of this project was Hash Tables.

## Code & Test Cases

The code written can be found in the [src](https://github.com/urvishp13/Little-Search-Engine/tree/main/src/search) folder. Look for the 
`@author Urvish Patel` tag before Classes to see the code I have written.
The code was tested using self-created documents; if you want to create your own document, simply create a text file and 
populate it with the textual contents of your desire.
The files for the documents I used are in the [data](https://github.com/urvishp13/Little-Search-Engine/tree/main/data) folder.
The test cases used are in [this](https://github.com/urvishp13/Little-Search-Engine/blob/main/docs/testcases.md) file.
The problem specs are in [this](https://github.com/urvishp13/Little-Search-Engine/blob/main/docs/problem_specs.pdf) file. 

## How to Test

To test the program, access the contents of this repository as appropriate for you and follow the instructions presented to you in the 
(eg. command line, console in Eclipse, etc.). 

If running the program using the command line, go into the `bin` 
folder of this repo and type `java search.LittleSearchEngineDriver`--as the .class files are already there.

To access the document files (.txt files) when running the program, enter the file that has the full file names (eg. `<file_name>.txt`) of 
the files to be used--if using the documents I used to test the code, this file is `mydocs.txt`. In order to use this file, 
you will have to backout/follow-directory-paths until you are 
in the directory of that file (eg. in the command line, you'll have to use `../data/<file_name>.txt`; in Eclipse, you'll have to use 
`data/<file_name>.txt`, etc.). **Be sure to use the file that has ALL the file names in it and not the individual file documents.**
Inside this file, make sure each document (.txt) file has the correct path to it; they should have a similar path as that
used to access the file with all the document file names, with the only difference being the file name. 
For the purposes of use-as-is, the [mydocs.txt](https://github.com/urvishp13/Little-Search-Engine/blob/main/data/mydocs.txt) 
file in this repository has its files
with the paths that would allow execution of the code from the command line; if you are running this code in a different environment, 
feel free to 
change the paths of all the files in this folder. 


You will also have to enter the "noisewords" file which are "commonplace words (such as 'the')". The name of this file is `noisewords.txt`
and access it using the method outlined in the previous paragraph.