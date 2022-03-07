# Test Cases

## top5search()

Enter these words to the number of documents in the top five as listed.

|   |No. of docs| Words |
|:-:|:---:|:-----------:|
|1|0|`dog`,`cat`|
|2|<5|`scalars`,`built`|
|3|5|`operators`,`place`|
|4|>5|`otherwise`,`descending`|


These test cases consider other possibilities.

|   |Possibility| Words |
|:-:|:---:|:-----------:|
|1|1 repeat document|`values`,`point`|
|2|2 (3) repeat documents|`otherwise`,`descending`|
|3|ties in frequencies|`built`,`scalars`|

In the cases of `#1` and `#2`, the repeated documents are not listed multiple times, only the document during the time when they had 
the maximum number of occurrences is listed.

For `#3`, in the method description in the source code for this method, there is a condition that has to be met. That condition is:
"ties in frequency values are broken in favor of the first keyword." This means, if there is a tie in FREQUENCY values for both keywords,
the DOCUMENT associate with this frequency of the first keyword will be listed first, then that of the second keyword (if space permits).