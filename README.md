# countdown-solver
A computer program to play the letters game from the British game show Countdown. Designed and implemented without consulting any online resources pertaining to existing programs to perform this task.

## The Game
Countdown is a British game show in which two contestants compete in a series of word and number games - called the "letters" and "numbers" rounds - earning points based on how successful they are in each. For each round of both the letters and numbers games, players have thirty seconds to come up with their answers. In the letters game, the contestants are given a sequence of nine letters, and must construct the longest word possible using each letter only once. The player who makes the longest word is awarded points equivalent to the number of letters in the word they find. For instance, if the letters are S-A-G-N-E-T-I-S-D, and the longest word either player came up with was "sandiest", the player to find that word would be awarded eight points. If a player finds a nine letter word, they are given eighteen points. Proper nouns, hyphenated words, and foreign-language words are not allowed. In the numbers game, the players are given six numbers, the largest possible number being 100, and a three-digit target number between 101 and 999. The goal is to use the six given numbers and the four elementary math operations - addition, subtraction, multiplication, and division - to reach the target number. For instance, if the given numbers are 100, 25, 7, 3, 5, and 1, and the target 545, the player could note that 100 \* 5 is 500,  7 \* 3 is 21, 21 - 1 is 20, 25 + 20 is 45, and 500 + 45 is 545. Alternatively, they could note that 7 \* 3 + 1 is 22, 22 * 25 is 550, and 550 - 5 is 545. Numbers may be used only once, and not all numbers have to be used. A player is awarded ten points for getting to the target exactly, seven points for getting within five of the target, and five points for getting within ten. 

For a more detailed walkthrough of how the game is played, including how the letters and numbers for each round are chosen, see [The format section of the Countdown Wikipedia article](https://en.wikipedia.org/wiki/Countdown_(game_show)


## My Goal
The goal for this project is to construct, without any outside help, a computer program that can play Countdown. This is a pure vanity project - I want to see if I can come up with an elegant and efficient way of generating millions of possible words or mathematical combinations and checking their validity all on my own. It's a project I plan on coming back to on an "as I feel like it" basis, which is to say, not very often, and one in which the final result probably won't be the best that could be had. No doubt there will be better ways of going about the processes I'm going about. But the goal is simply to get something that works, even if it's a bit ugly.


## How it Works - The Letters Solver
There are two distinct components to a program for playing the letters game. The first is a means of generating all of the possible "candidate" words from the set of nine letters: there 986,409 possible words that can be made from nine letters (assuming no duplicates). The second is a way of checking each candidate word against a dictionary to determine if it is a real word.

### Generating candidate words
I've chosen to tackle the task of generating candidate words by generating words of increasing length. Obviously, generating one-letter words is trivial. To generate two-letter words, we simply go through each character and combine it with each other character with the following simple loop: 

```java
for (int i = 0; i < chars.size(); i++) {
	for (int j = 0; j < chars.size(); j++) {
		if (j != i) {
			String s = chars.get(i);
			s = s + chars.get(j);
			result.add(s);
		}
	}
}
```

We'll also use this loop to generate words of longer lengths. To generate possible three-letter words, we'll start by removing the first character from our list of letters, then generating all of the two-character words that don't use that letter (we do this by calling the above loop again, with ```chars``` having only eight characters instead of nine). We then add the letter we removed to the beginning of each of these two-character strings. We repeat this process for each of nine letters in our list, and the result will be every possible three-letter string. We can then use this same logic to generate four letter words: instead of calling the loop for two-letter words directly, we call our three-letter word generator, then add the removed letter to the strings returned. We apply this process to five, six, seven, eight, and nine letter words, and eventually will have a list of all 986,409 possible words that can be made with the nine letters presented to us.

As you may well note, this approach does a *lot* of redundant work. One of the higher-order items on the to-do list for this project is to find a more optimized way of computing possible strings that doesn't require doing much of the same work over again.

### Checking word validity
The next step in the process is to determine if each of our candidate words is a real word. To do this, we must first have a dictionary that we can reference. The list of words we're using is the '''valid2.txt''' file, which itself is the '''words.txt''' after it has been passed through the validWordGenerator program. This program is a small piece of code written to take the lengthy list of words in the '''words.txt''' file and remove all of the words containing invalid character or uppercase letters, and/or which are too long. The resulting list of words is then alphabetized, so that it can be referenced like a real dictionary.

The simple way to check the validity of each word would be to simply loop through the list of candidates and, for each string, check to see if it's in the dictionary. However, this is obviously a performance nightmare, as it would require looping through the entire dictionary one million times. Instead, we'll scan through the dictionary only once. To do this, we'll alphabetize our list of candidate words, then scan through it and the dictionary "in parallel". Starting with the first candidate word in our list and the first word in the dictionary, we'll compare the two words using Java's ```String.compareTo()``` function. If the result is a negative number, then the candidate string comes alphabetically before the dictionary word, meaning that if the candidate were a real word, we would have found a match by now, so we can throw that word out and move on to the next candidate. If the result is positive, then the candidate string comes alphabetically after the dictionary word, so we need to move on to the next word in the dictionary. If the result is zero, then the two strings are a match, so our candidate is a real word and can be added to the list of valid words, and we can move on to both the next word in the list of candidates and the next word in the dictionary. 

In addition to only requiring one scan of the dictionary, this method has the added bonus of implicitly tossing out duplicates that may arise from having two of the same letter. If the duplicate string isn't a word, then it is thrown out as normal in the evaluation. If it *is* a word, however, we can still ensure that it will only be added to the list of valid words once, since upon finding a valid word, the program moves forward in both the dictionary *and* the list of candidates. Any successive occurrences of the same string will then be guaranteed to come alphabetically before the current word in the dictionary, and so will be thrown out.


## TO-DO For This Project
There is still a lot to improve and add to this project - below is a likely incomplete list of improvements and additions that I know I'd like to make in time:
* Update the validWordGenerator code to use regex (this isn't strictly necessary as this code isn't actually used in the program, but it would make it look a lot less ugly)
* Optimize candidate word generation in the letters solver
* Begin work on the numbers game