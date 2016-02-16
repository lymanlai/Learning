# Python Basic Syntax

## Python Identifiers

A python identifier is a name used to identify a varible, function, class, module or other object. An identifier starts with a letter A to Z or a to z or an underscore(_) followed by zero or more letters, underscores and digits(0 to 9).

Python does not allow punctuation characters such as @, $, and % within identifiers. Python is a case sensitive programming language. Thus, Manpower and manpower are two different identifiers in Python.

Here are naming conventions for Python identifiers:

* Class names start with an uppercase letter. All other identifiers start with a lowercase letter.

* Starting an identifier with leading underscores indicates a strongly private identifier.

* If the identifier also ends with two trailing underscores, the identifier is a language-defined special name.

## Reserved Words

|:---:		|:---:		|:---:|
|And		|exec		|Not|
|Assert		|finally	|or|
|Break		|for		|pass|
|Class		|from		|print|
|Continue	|global		|raise|
|def		|if			|return|
|del		|import		|try|
|elif		|in			|while|
|else		|is			|with|
|except		|lambda		|yield|

## Lines and Indentation

Python provides no braces indicate blocks of code for class and function definitions or flow control. Blocks of code are denoted by line indentation, which is rigidly enforced.

The number of spaces in the indentaion is variable, but all statements within the block must be indented the same amount.

For example

```python
if True:
	print "True"
else:
  print "False"
```

However, the following block generates an error

```python
if True:
	print "Answer"
	print "True"
else:
	print "Answer"
  print "False"
```

Thus, in Python all the continuous line indented with same number of spaces would form a block. The following example has various statement blocks.

## Multi-Line Statements

Statements in Python typically end with a new line. Python does, however, allow the use of the line continuation character (\) to denote that line should continue.

For example

```python 
total = item_one + \
		item_two + \
		item_three
```

Statements contained within the [], {}, or () brackets do not need to use the line continuation character.

For example

```python
days = ['Monday', 'Tuesday', 'Wednesday',
		'Thursday', 'Friday']
```

## Quotation in Python

Python accepts sigle ('), double (") and triple (''' or """) quotes to denote string literals, as long as the same type of quote starts and ends the string.

The triple quotes are used to span the string across multiple lines. 

## Comments in Python

A hash sign (#) that is not inside a string literal begins a comment. All characters after the # and up to the end of the physical line are part of the comment and the Python interperter ignores them.

## Using Blank Lines

A line containing only whitespace, possibly with a comment, is known as a blank line and Python ignore it.

In an interactive interperter session, you must enter an empty physical line to terminate a multiline statement.

## Waiting for the User

```python
#!/usr/bin/python

raw_input("\n\n Press the ennter key to exit.")
```

## Multiple Statements on a Single Line

The semicolon (;) allows multiple statements on the single line given that neither statement starts a new code block. Here is a sample snip using the semicolon.

## Multiple Statements Groups as Suites

A group of individual statements, which make a single code block are called suites in Python. Compound or complex statements, such as if, while, def and class require a header line and a suite.

Header lines begin the statement (with the keyword) adn terminate with a colon(:) and are followed by one or more lines which make up the suite. 

For example

```python
if expression:
	suite
elif expression:
	suite
else:
	suite
```

## Command Line Arguments

Many programs can be run to provide you with some basic information about how they should be run. 







