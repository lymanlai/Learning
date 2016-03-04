# Python Classes Objects

## Overview of OOP Terminology

* Class: A user-defined prototype for an object that defines a set of attributes that characterize any object of the class. The attributes are data members (class variables and instanve variables) and methods, accessed via dot notation.

* Class variable: A variable that is shared by all instances of a class. Class variables are defined within a class but outside any of the class's methods. Class variables are not used as frequently as instance variables are.

* Data member: A class variable or instance varible that hols data associated with a class and its objects.

* Function overloading: The assignment of more than one behavior to a particular function. The operation performed varies by the types of objects or arguments involved.

* Instance variable: A variable that is defined inside a method and belongs only to the current instance of a class.

* Inheritance: The transfer of the characteristics of a class to other classes that are derived from it.

* Instantiation: The creation of an instance of a class.

* Method: A special kind of funtion that is defined in a class definition.

* Object: A unique instance of a data structure that's defined by its class. An object comprises both data members (class variables and instance variables) and methods.

* Operator overloading: The assignment of more than one function to a particular operator.


## Creating Classes

The class statement creates a new class definition. The name of the class immediately follows the keyword class followed by a colon as follows -

> class ClassName:
	'Optional class documentation string'
	class_suite

* The class has a documentation string, which can be accessed via ClassName.__doc__.

* The class_suite consists of all the component statements defining class members, data attributes and functions.

## Example 

Following is the example of a simple Python class -

```python

class Employee:
	'Common base class for all employees'
	empCount = 0

	def __init__(self, name, salary):
		self.name = name
		self.salary = salary
		Employee.empCount += 1

	def displayCount(self):
		print "Total Employee %d" % Empoyee.empCount

	def displayEmployee(self):
		print "Name : ", self.name, ", Salary: ", self.salary
```

* The variable empCount is a class variable whose value is shared among all instances of a this class. This can be accessed as Employee.empCount from inside the class or outside the class.

* The first method __init__() is a special method, which is called class constructor or initialization method that Python calls when you create a new instance of this class.

* You declare other class methods like normal functions with the exception that the first argument to each method is self. Python adds the self argument to the list for you; you do need to include it when you call the methods.

## Creating Instance Objects

To create instances of a class, you call the class using class name and pass in whatever arguments its __init__ method acccepts.

> "This would create first object of Employee class"
	emp1 = Employee("Zara", 2000)
  "This would create second object of Employee class"
  	emp2 = Employee("Manni", 5000)

## Accessing Attributes

You access the object's attributes using the dot operator with object. Class variable would be accessed using class name as follows -

> emp1.displayEmployee()
  emp2.displayEmployee()
  print "Total Employee %d" % Employee.empCount

Now, putting all the concepts together - 

```python
#!/usr/bin/python

class Employee:
   'Common base class for all employees'
   empCount = 0

   def __init__(self, name, salary):
      self.name = name
      self.salary = salary
      Employee.empCount += 1
   
   def displayCount(self):
     print "Total Employee %d" % Employee.empCount

   def displayEmployee(self):
      print "Name : ", self.name,  ", Salary: ", self.salary

"This would create first object of Employee class"
emp1 = Employee("Zara", 2000)
"This would create second object of Employee class"
emp2 = Employee("Manni", 5000)
emp1.displayEmployee()
emp2.displayEmployee()
print "Total Employee %d" % Employee.empCount
```

When the above code is executed, it produces the following result -

> Name :  Zara ,Salary:  2000
Name :  Manni ,Salary:  5000
Total Employee 2

You can add, remove or modify attributes of classes and objects at any time - 

> emp1.age = 7  # Add an 'age' attribute.
emp1.age = 8  # Modify 'age' attribute.
del emp1.age  # Delete 'age' attribute.

Instead of using the normal statements to access attributes, you can use the following functions - 

* The getattr(obj, name[,default]) : to access the attribute of object.
* The hasattr(obj, name) : to check if an attribute exists or not.
* The setattr(obj, name, value) : to set an attribute. If attribute does not exist, then it would be created.
* The delattr(obj, name) : to delete an attribute.

>hasattr(emp1, 'age')    # Returns true if 'age' attribute exists
getattr(emp1, 'age')    # Returns value of 'age' attribute
setattr(emp1, 'age', 8) # Set attribute 'age' at 8
delattr(empl, 'age')    # Delete attribute 'age'

## Built-In Class Attributes

Every Python class keeps following built-in attributes and they can be accessed using dot operator like any other attribute - 

* __dict__  : Dictionary containing the class's namespace.
* __doc__   : Class documentation string or none, if undefined.
* __name__  : Class name.
* __module__: Module name in which the class is defined. This attribute is "__main__" in interactive mode.
* __bases__ : A possibly empty tuple containing the base classes, in the order of their occurrence in the base class list.

For the above class let us try to access all these attributes - 

```python
#!/usr/bin/python

class Employee:
   'Common base class for all employees'
   empCount = 0

   def __init__(self, name, salary):
      self.name = name
      self.salary = salary
      Employee.empCount += 1
   
   def displayCount(self):
     print "Total Employee %d" % Employee.empCount

   def displayEmployee(self):
      print "Name : ", self.name,  ", Salary: ", self.salary

print "Employee.__doc__:", Employee.__doc__
print "Employee.__name__:", Employee.__name__
print "Employee.__module__:", Employee.__module__
print "Employee.__bases__:", Employee.__bases__
print "Employee.__dict__:", Employee.__dict__
```

When the above code is executed, it produces the following result -
>Employee.__doc__: Common base class for all employees
Employee.__name__: Employee
Employee.__module__: __main__
Employee.__bases__: ()
Employee.__dict__: {'__module__': '__main__', 'displayCount':
<function displayCount at 0xb7c84994>, 'empCount': 2, 
'displayEmployee': <function displayEmployee at 0xb7c8441c>, 
'__doc__': 'Common base class for all employees', 
'__init__': <function __init__ at 0xb7c846bc>}

## Destroying Objects (Garbage Collection)

Python deletes unneeded objects (built-in types or class instances) automatically to free the memory space. The process by which Python periodically reclaims blocks of memory that no longer are in use is termed Garbage Collection.

Python's garbage collector runs during program execution and is triggered when an object's reference count reaches zero. An object's reference count changes as the number of aliases that point to it changes.

An object's reference count increases when it is assinged a new name or placed in a container (list, tuple, or dictionary). The object's reference count decreases when it's deleted with del, its reference is reassigned, or its reference gose out of scope. When an object's reference count reaches zero, Python collects it automatically.

```python
a = 40      # Create object <40>
b = a       # Increase ref. count  of <40> 
c = [b]     # Increase ref. count  of <40> 

del a       # Decrease ref. count  of <40>
b = 100     # Decrease ref. count  of <40> 
c[0] = -1   # Decrease ref. count  of <40> 
```

