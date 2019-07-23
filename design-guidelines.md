**Run code often. Catch mistakes as you go, instead of having to dig through everything later.**

**Use consistent, self-explanatory names. If that is not possible, leave a comment.**

**Attempt to keep comments within one line. Otherwise, use a diagram if needed.**

Adapted from UBCx HtC
---

### Function Design: ###
Figure out what the function takes as an input, what it does, and what it will output.

Leave a comment describing the function and what it does.

Name the function. Pick a name that is useful and self-explanatory.

Define function body.

Define test cases. Cover all the possible combinations of inputs and outputs for the function.

(Optional) Create a mock-up of the function body, based off of good code.

Finish coding the body of the function.

Test and debug.

#### One task per function. Minimize redundancy. Be specific. ####

---

### Data Design: ###
Figure out what you are trying to represent, and what would be the best way to do so.

(Context-dependent) Define your data structure.

Name your data type (and describe how to form the data if necessary).

Leave a comment describing what it is or how to interpret it.

Define at least one example of the data. Cover all the possible cases.

Provide an example function that makes full use of this new data.

>### RECURSIVE (SELF-REFERENTIAL) DATA: ###
>
>Data must have at least one case that does not refer to itself (base case, to break the loop).
>
>Data must have at least one case that does refer to itself (to start the loop).
>
>Functions which use this data must have corresponding base case and recursions.
>
>When making examples for self-referential data, always put the base case first.
>
>At least one test case must cover a situation where the data is at least 2 levels deep.
>
>Cover all possible cases.

---

### Interactive Program Design: ###

Plan out what the program will do by hand. 

Note down constant elements such as backgrounds, window/screen size, etc.

Note down elements that change such as positions of elements, numbers in a countdown, etc.


Load required libraries.

Leave a comment describing the program’s behaviour.

Define constants.

Define data.

Define functions in descending order of importance (i.e. most important at top).

Note: You may choose to simply leave empty function definitions as a to-do list. If so, mark them with an identifier that is easy to search for (e.g. !!!) so you can come back to it.

---

### Abstraction from examples (generalizing specific functions): ###

Code everything first and ensure it works.

Identify the repetitive parts.

Use the function body of one function as a skeleton to create a general function with parameters for each point of variance.

Test the new function. Make sure to use a wide variety of test cases.

Describe the behaviour of the new function in a comment.

Rewrite the original functions to call the new abstract function.
