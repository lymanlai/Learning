# Python Variable Types

## Assigning Values to Variables

Python variables do not need explicit declaration to reserve memeory space. The declaration happens automatically when you assign a value to a variable. The equal sign (=) is used to assign values to variables.

The operand to the left of the = operator is the name of the variable and the operand to the right of the = operator is the value stored in the variable.

```python
#!/usr/bin/python

counter = 100		# An integer assigment
miles = 1000.0		# A floating point
name = "John"		# A String

print counter
print miles
print name
```

## Multiple Assignment

Python allows you a assign a single value to several variables simultaneously.

```python
a = b = c = 1
```

Here, an integer object is created with the value 1, and all three variables are assigned to the name memory location. You can assign multiple objects to multiple variables.

```python
a, b, c, = 1, 2, "john"
```

Here, two integer objects with values 1 and 2 are assigned to variables a and b respectively, and one string object with the value "john" is assigned to the variable c.

## Standdard Data Types

The data stored in memory can be of many types. For example. a person's age is stored as a numeric value and his or her address is stored as alphanumeric characters. Python has various stanted data types that are used to define the operations possible on them and the storage method for each of them.

Python has five standard data 

* Numbers
* String 
* List
* Tuple
* Dictionary

Python Numbers

Number data types store numeric values. Number objects are created when you assign a value to them.

```python
var1 =1 
var2 = 10
```

You can also delete the reference to a number object by using the del statement. The syntax of the del statement is 

```python
del var1
del [var1:var2]
```

You can delete a s single object or multiple objects by using the del statement.

```python
del var
del var_a, var_b
```

Python supports four different numerical types

* int (signed integers)
* long (long integers, they can also be represented in octal and hexadecimal)
* float (floating point real values)
* complex(complex numbers)

Here are some examples of numbers

| int	    | long	                | float	     | complex    |
|:---------:|:---------------------:|:----------:|:----------:|
| 10	    | 51924361L	            | 0.0	     | 3.14j      |
| 100	    | -0x19323L	            | 15.20	     | 45.j       |
| -786	    | 0122L	                | -21.9	     | 9.322e-36j |
| 080	    | 0xDEFABCECBDAECBFBAEl	| 32.3+e18	 | .876j      |
| -0490	    | 535633629843L	        | -90.	     | -.6545+0J  |
| -0x260	| -052318172735L	    | -32.54e100 |	3e+26J    |
| 0x69	    | -4721885298529L	    | 70.2-E12	 | 4.53e-7j   |

* Python  allows you to use a lowercase L with lonm, but it is recommanded that you use only an uppercase L to avoid confusion with the number 1. Python displays long integers with an uppercase L.

* A complex number consis of an ordered pair of real floating-point numbers denoted by x+yj, where x and y are the real numbers and j is the imaginary unit.

## Python Strings

Strings in Python are identified as a contiguous set of characters represented in the quotation marks. Python allows for either pairs of single or double quotes. Subsets of strings can be taken using the slice operator ([] and [:]) with indexes starting at 0 in the beginning of the string and working their way from -1 at the end.

The plus (+) sign is the string concatenation operator and the asterisk (*) is the repetition operator.

```python
#!/usr/bin/python

str = 'Hello World!'

print str          # Prints complete string
print str[0]       # Prints first character of the string
print str[2:5]     # Prints characters starting from 3rd to 5th
print str[2:]      # Prints string starting from 3rd character
print str * 2      # Prints string two times
print str + "TEST" # Prints concatenated string
```

## Python Lists

Lists are the most versatile of Python's compound data types. A list contains items spearated by commas and enclosed within square brackets ([]). To some extent, lists are similar to arrays in C. One different between them is that all the items belonging to a list can be of different data type.

The values stored in a list can be accessed using the slice operator ([] and [:]) with indexes starting at 0 in the beginning of the list and working their way to end -1. The plus (+) sign is the list concatenation operator, and the aserisk (*) is the repetition operator

```python
#!/usr/bin/python

list = [ 'abcd', 786 , 2.23, 'john', 70.2 ]
tinylist = [123, 'john']

print list          # Prints complete list
print list[0]       # Prints first element of the list
print list[1:3]     # Prints elements starting from 2nd till 3rd 
print list[2:]      # Prints elements starting from 3rd element
print tinylist * 2  # Prints list two times
print list + tinylist # Prints concatenated lists
```

## Python Dictionary

Python's dictionary are kind of hash table type. They work like associativie arrays or hashes found in Perl and consist of key-value pairs. A dictionary key can be almost any Python type, but are usually numbers or strings. Values, on the other hand, can be any arbitrary Python object.

Dictionaries are enclosed by curly braces ({}) and values can be assigned and accessed using square braces ([]).

```python
#!/usr/bin/python

dict = {}
dict['one'] = "This is one"
dict[2]     = "This is two"

tinydict = {'name': 'john','code':6734, 'dept': 'sales'}


print dict['one']       # Prints value for 'one' key
print dict[2]           # Prints value for 2 key
print tinydict          # Prints complete dictionary
print tinydict.keys()   # Prints all the keys
print tinydict.values() # Prints all the values
```

Dictionaries have no concept of order among elements. It is incorrect to say that the elements are out of order; they are simple unordered.

## Data Type Conversion

Sometimes, you may need to perform conversions between the build-in types. To convert between types, you simply use the type name as a function.

There are several built-in functions to perform conversion from one data type to another. These functions return a new object representing the converted value. 

| Function              | Description                                                            |
|:---------------------:|:----------------------------------------------------------------------:|
| int(x [,base])        | Converts x to an integer. base specifies the base if x is a string.    |
| long(x [,base])       | Converts x to a long integer. base specifies the base if x is a string.|
| float(x)              | Converts x to a floating-point number.                                 |
| complex(real [,imag]) | Creates a complex number.                                              |
| str(x)                | Converts object x to a string representation.                          |
| repr(x)               | Converts object x to an expression string.                             |
| eval(str)             | Evaluates a string and returns an object.                              |
| tuple(s)              | Converts s to a tuple.                                                 |
| list(s)               | Converts s to a list.                                                  |
| set(s)                | Converts s to a set.                                                   |
| dict(d)               | Creates a dictionary. d must be a sequence of (key,value) tuples.      |
| frozenset(s)          | Converts s to a frozen set.                                            |
| chr(x)                | Converts an integer to a character.                                    |
| unichr(x)             | Converts an integer to a Unicode character.                            |
| ord(x)                | Converts a single character to its integer value.                      |
| hex(x)                | Converts an integer to a hexadecimal string.                           |
| oct(x)                | Converts an integer to an octal string.                                |


