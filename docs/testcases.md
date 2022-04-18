# Test Cases

## insertLastOccurrence()

These are the test cases that were used with ONE word--if these tests pass with one word, the orderings of the document-occurrence pairs 
for all words will be correct.

|   |Insertion| Details |
|:-:|:-------------:|:-:|
|1|`at index 0`|
|2|`at last index`|
|3|`in between the list`|
|4|`a duplicate at the end`| duplicate should be listed second |
|5|`2 duplicates at the end`| 3 of the same frequency |
|6|`2 duplicates in the middle`| 3 of the same frequency |

## top5search()

These are some of the words that gather the number of documents listed. The gathered documents are then limited to the 
documents with the top five frequencies.

|   |No. of docs| Words |
|:-:|:---:|:-----------:|
|1|0|`dog`,`cat`|
|2|<5|`scalars`,`built`|
|3|5|`operators`,`place`|
|4|>5|`otherwise`,`descending`|

The following test cases consider other possibilities.

|   |Possibility| Words |
|:-:|:---:|:-----------:|
|1|1 repeat document|`values`,`point`|
|2|2 (3) repeat documents|`otherwise`,`descending`|
|3|ties in frequencies|`built`,`scalars`|

In the cases of `#1` and `#2`, the repeated documents are not listed multiple times in the top five, 
only the document during the time when it had 
the maximum number of occurrences is listed.

For `#3`, in the method description in the source code, there is a condition that has to be met. That condition is:
"ties in frequency values are broken in favor of the first keyword." This means, if there is a tie in FREQUENCY values for both 
keywords,
the DOCUMENT associate with this frequency of the first keyword will be listed first, then that of the second keyword will be listed
second (if space in the top five is available).