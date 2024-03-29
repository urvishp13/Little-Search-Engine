# Little Search Engine

This project is solely written in Java. Please run it using Java 8.

## Overview

The focus of this project is to scan all the words from all documents used and gather and
store them in a Hash Table. Each word is associated with the documents it occurred
in and the
number of times it occurred in those documents.
An additional feature of this project is to provide the COMBINED top five documents two user-inputted words occur in; 
if both words occur in a
document, the number of times they occur is not combined.
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

To test the program, follow the instructions presented to you in the 
(eg. command line, console in Eclipse, etc.) when running the program. 

If running the program using the command line, go into the `bin` 
folder of this repo and type `java search.LittleSearchEngineDriver`--as the .class files are already there.

To access the document files when running the program, **enter the file name that has all the file names you
want to read data from, do not enter the names of the individual files** (eg. in my case, this file is `mydocs.txt`). 
In order to use this file, 
you will have to backout/follow-directory-paths until you are 
in the directory of this file (eg. in the command line, you'll have to use `../data/mydocs.txt`; in Eclipse, you'll have to use 
`data/mydocs.txt`, etc.).
Inside this file, make sure each .txt file has the correct path to it; they should have a similar path as that
used to access the file with all the document file names, the only difference being the name of the file. 
For use-as-is purposes, the [mydocs.txt](https://github.com/urvishp13/Little-Search-Engine/blob/main/data/mydocs.txt) 
file in this repository has its files
with the paths that will allow execution of the code from the command line; if you are running this code in a different environment, 
feel free to 
change the paths of all the files in this folder. 

You will also have to enter the "noisewords" file which has "commonplace words (such as 'the')". The name of this file is `noisewords.txt`;
 input it using the directory-path method outlined in the previous paragraph.