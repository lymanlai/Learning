# Python Decision Making

Decision making is anticipation of conditions occurring while exection of the program and specifying actions taken according to the conditions.

Decision structures evaluate multiple expressions which produce TRUE or FALSE as outcome. You need to determine which action to take and which statements to execute if outcome is TRUE or FALSE otherwise.

Following is the general form of a typical decision making structure found in most of the programming languages -







Python programming language assumes any **non-zero** and **non-null** values as TRUE, and if it is either zero or null, then it is assumed as FALSE value.

Python programming language provides following types of decision making statements, Click the following links to check their detail.

Statement	Description

if statements

An if statement consists of a boolean expression followed by one or more statements.

if...else statements

An if statement can be followed by an optional else statement, which executes when the boolean expression is FALSE.

if...elif...else statements

You can use one if or else if statement inside another if or else if statement(s).


## Single Statement Suites

If the suite of an if clause consists only of a single line, it may go on the same line as the header statement.

Here is an example of a one-line if clause -

```python
#!/usr/bin/python
var = 100
if (var == 100):
	print "Value of expression is 100"

print "That's awesome!"
```

When the above code is executed, it produces the following result -

> Value of expression is 100
  That's awesome!
