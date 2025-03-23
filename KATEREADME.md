# Overview

For my autocorrect project, the user inputs a word, and if it's misspelled, my program will return a list of words that 
the user might have intended. To do this, I start by narrowing down a dictionary into a short list of words that somewhat
resemble the user's input. When I say "resemble," I mean that the two words must share a certain number of n-grams. The 
exact number changes depending on the length of the user's input.

Then, I use a tabulation approach to calculate the edit distance between the user's input and each word on the short list. 
If the edit distance is less than or equal to a given threshold, that word is indeed a matching word, and it is printed in the
terminal for the user.

# Time Complexity

# a. Finding Edit Distance:
n = length of word 1 & m = length of word 2
1. Must fill every space in a n*m array = O(n*m)
2. Must index into final space of the array = O(1)

OVERALL: O(n*m)

# b. Making Short List from OG Dictionary:
a = size of dictionary & b = length of user input String & c = average length of word in dictionary
1. For every word in dictionary = O(a)
   2. check if it's a good word by:
      3. Calculating n-grams for user input & dictionary word: O(b) + O(c)
      4. iterating through n-grams of user input & calling .contains() on n-grams for dictionary word = O(b) * O(c)
      5. Checking if the word has high enough similarity  = O(1)
   6. If it's good, add to short list = O(1)
7. Convert shortlist arraylist into array = O(a)

OVERALL: O(a) * O(b + c + bc) + O(a)






