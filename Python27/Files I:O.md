# Python Files I/O

## Printing to the Screen

The simplest way to produce output is using the **print** statement where you can pass zero or more expressions separated by commas. This function converts the expressions you pass into a string and writes the result to standard output as follows.

```python
#!/usr/bin/python 
print "Let's go to starter", "Awesome"
```

## Reading Keyboard Input

### raw_input

The **raw_input([prompt])** function reads one line from standard input and returns it as a string (removing the trailing newline).

```python
str = raw_input("Which programming language to learn?");
print "It's ", str
```

### input

The **input([prompt])**function is equivalent to raw_input, except that it assumes the input is a valid Python expression and returns the evaluated result to you.

```python 
str = input("Enter you input: ");
print "Received input is : ", str;
```

## Opening and Closeing Files

### The **open** Function

Before you can read or write a file, you have to open it using Python's built-in open() function. This function creates a file object, which would be utilized to call other support methods associated with it. 

Open the file in read-only mode, if the file does not exist, open() function will throw an IOError error.

Once IOError is thrown, close() function will not be called. So in order to ensure whether or not the error could properly close the file, use try...finally realized

```python
try:
	f = open('/path/to/file', 'r')
	print f.read()
finally:
	if f:
		f.close()
```

Python provides with statement to call the close() function automatically.

```python
with open('/path/to/file', 'r') as f:
    print f.read()
```

#### Syntax

> file object = open(file_name [, access_mode][, buffering])

**parameter details**

* **file_name :** The file_name argument is a string value that contains the name of the file that you want to access.

* **access_mode :** The access_mode determines the mode int which the file has to be opened, i.e., read, write, append, etc. A complete list of possible values is given below in the table. This is optional parameter and the default file access mode is read (r).

* **buffering :** If the buffering value is set to 0, no buffering taks place. If the buffering value is 1, line buffering is performed while accessing a file. If you specify the buffering value as an integer greater than 1, then buffering action is performed with the indicated buffer size. If negative, the buffer size is the system default(default behavivor).

#### list of the different modes of opening a file

|Modes|Description|
|:---:|:----:|
|r  	|Opens a file for reading only. The file pointer is placed at the beginning of the file. This is the default mode.|
|rb		|Opens a file for reading only in binary format. The file pointer is placed at the beginning of the file. This is the default mode.|
|r+		|Opens a file for both reading and writing. The file pointer placed at the beginning of the file.|
|rb+	|Opens a file for both reading and writing in binary format. The file pointer placed at the beginning of the file.|
|w		|Opens a file for writing only. Overwrites the file if the file exists. If the file does not exist, creates a new file for writing.|
|wb		|Opens a file for writing only in binary format. Overwrites the file if the file exists. If the file does not exist, creates a new file for writing.|
|w+		|Opens a file for both writing and reading. Overwrites the existing file if the file exists. If the file does not exist, creates a new file for reading and writing.|
|wb+	|Opens a file for both writing and reading in binary format. Overwrites the existing file if the file exists. If the file does not exist, creates a new file for reading and writing.|
|a		|Opens a file for appending. The file pointer is at the end of the file if the file exists. That is, the file is in the append mode. If the file does not exist, it creates a new file for writing.|
|ab		|Opens a file for appending in binary format. The file pointer is at the end of the file if the file exists. That is, the file is in the append mode. If the file does not exist, it creates a new file for writing.|
|a+		|Opens a file for both appending and reading. The file pointer is at the end of the file if the file exists. The file opens in the append mode. If the file does not exist, it creates a new file for reading and writing.|
|ab+	|Opens a file for both appending and reading in binary format. The file pointer is at the end of the file if the file exists. The file opens in the append mode. If the file does not exist, it creates a new file for reading and writing.|

### The **file** Object Attributes

Once a file is opened and you have one file object, you can get various information related to that file.

#### list of all attributes related to file object

|attributes  | Description |
|:---:|:----:|
|file.closed     |Returns true if file is closed, false otherwise.|
|file.mode       |Returns access mode with which file was opened.|
|file.name	     |Returns name of the file.|
|file.softspace  |Returns false if space explicitly required with print, true otherwise.|

#### Example

```python
# Open a file
fo = open("input.py", "wb")
print "Name of the file: ", fo.name
print "Closed or not : ", fo.closed
print "Opening mode : ", fo.mode
print "Softspace flag : ", fo.softspace
```

### The close() Method

The **close()** method of a file object flushes any unwritten information and closes the file object, after which no more writing can be done.

Python automatically closes a file when the reference object of a file is reassigned to another file, It is a good practice to use the **close()** method to close a file.

#### Syntax

> fileObject.close();

#### Example

```python
#open a file
fo = open("input.py", "wb")
print "Name of the file: ", fo.name

# Close opend file
fo.close()
``` 

## Reading and writing Files

### The write() Method

The write()  method writes any string to an open file. It is important to note that Python strings can have binary data and not just text.

The write() method does not add a newline character ('\n') to the end of the string

#### Syntax

> fileObject.write(string);

#### Example

```python
# Open a file
fo = open("foo.txt", "wb")
fo.write( "Python is a great language.\nYeah its great!!\n");

# Close opend file
fo.close()
```

```python 
with open('/Users/michael/test.txt', 'w') as f:
    f.write('Hello, world!')
```

### The read() Method

The read() method reads a string from an open file. It is important to note that Python strings can have binary data. apart from text data.

#### Syntax

> fileObject.read([count]);

This method starts reading from the beginning of the file and if count is missing, then it tries to read as much as possible, maybe until the end of file.

read() function will be read the entire contents of the file at one time, if the file is huge, the memory will burst, so you can call the read(size) method with a maximum read size bytes of content repeatedly.

readline() can each read a line, call readlines () time to read all the contents of the line and press return list.

If the file size is small, read() function read the file at one time is most convenienet, if you can not determine the file size, calling read(size) repeatedly, if it's a configuration file, call readlines(). 

```python 
for line in f.readlines():
	print(line.strip())
```

The default text encoding format is ASCII, if read non-ASCII encoding text file, must open in binary mode, and then decoding.

E.g gbk encoding

```python
f = open('/Users/michael/gbk.txt', 'rb')
u = f.read().decode('gbk')
```

or

```python
import codecs
with codecs.open('/Users/michael/gbk.txt', 'r', 'gbk') as f:
    f.read()
```

#### Example

```python 
# Open a file 
fo = open("foo.txt", "r+")
str = fo.read(10);
print "Read String is : ", str
#Close opend file
fo.close()
```

## File Positions

The tell() method tells you the current position within the file; in other words, the next read or write will occur at that many bytes from the beginning of the file.

The seek(offset[, from]) method changes the current file position. The offset argument indicates the number of bytes to be moved. The from argument specifies the reference position from where the bytes are to be moved.

If from is set to 0, it means use the beginning of the files as the reference position and 1 means use the current position as the reference position and if it is set to 2 then the end of the file would be taken as the reference position.

#### Example

```python
#Open a file
fo = open("foo.txt", "r+")
str = fo.read(10);
print "Read String is : ", str

#Check current position
position = fo.tell();
print "Current file position : ", position

#Reposition pointer at the beginning once again
position = fo.seek(0, 0);
str = fo.read(10);
print "Again read String is : ", str
#Close opened file
fo.close()
```

## Remaming and  Deleting Files

### The rename() Method

The rename() method takes two arguments, the current filename and the new filename.

#### Syntax

> os.rename(current_file_name, new_file_name)

#### Example

```python
import os

# Rename a file from test1.txt to test2.txt
os.rename( "test1.txt", "test2.txt" )
```

## The remove() Method

Use the remove() method to delete files by supplying the name of the file to be deleted as the argument.

#### Syntax

> os.remove(file_name)

#### Example

```python
import os

# Delete file test2.txt
os.remove("text2.txt")
```

## Directories in Python

### The mkdir() Method

Use the mddir() method of the os module to create directories in the current directory. You need to supply an argument to this method which contains the name of the directory to be created.


#### Syntax

> os.mkdir("newdir")

#### Example

```python
import os

# Create a directory "test"
os.mkdir("test")
```

### The chdir() Method

Use the chdir() method to change the current directory. The chdir() method takes an argument, which is the name of directory that you want to make the current dirctory.

#### Syntax

> os.chdir("newdir")

#### Example

```python
import os

# Changing a directory to "/home/newdir"
os.chdir("/home/newdir")
```

### The getcwd() Method

The getcwd() method displays the current working directory.

#### Syntax

> os.getcwd()

#### Example

```python
import os

# This would give location of the current directory
os.getcwd()
```

### The redir() Method

The redir() method deletes the directory, which is passed as an argument in the method.

Before removing a directory, all the contents in it should be removed.

#### Syntax

> os.rmdir('dirname')

#### Example

```python
import os

# This would remove "/tmp/test" directory
os.rmdir("/tmp/test")
```

## System environment variables

```python 
import os
os.name # system name
```

```python
os.environ # environment variables
```

Get the value of an environment variables

```python 
os.getenv('PATH')
```

### join string

When the two paths into one , do not directly fight the string , but to pass os.path.join () function , because there may be a different system compatibility problems.

```python
os.path.abspath('.')

os.path.join('/Users/test1', 'test2')
```
os.path.split () function can put a path split into two parts, the latter part is always the last level directory or file name.

```python
os.path.split('/Users/michael/testdir/file.txt')
```

os.path.splitext () can allow you to get the file extension.

```python
os.path.splitext('/path/to/file.txt')
```

e.g file filter

```python 
x for x in os.listdir('.') if os.path.isdir(x)
```

```python
x for x in os.listdir('.') if os.path.isfile(x) and os.path.splitext(x)[1]=='.py'
```
