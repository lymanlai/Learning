# How to Create a Sublime Text 2 Plugin

We're going to recreate my popular Sublime plugin that sends CSS through the Nettuts + Prefixr API for easy cross-browser CSS.

The extension model for Sublime Text2 is fairly full-featured. There are ways to change the syntax highlighting, the actual chrome of the editor and all of the menus. Additionally, It is possible to create new build systems, auto-compleitons, language definitions, snippets, macros, key bindings, mouse bindings and plugins. All of these different types of modifications are implemented via files which are organized into packages.

A package is a folder that is stored in your **Packages** directory. You can access your Packages directory by clicking on the Preferences > Browse Packages... menu entry. It is also possible to bundle a package into a single file by creating a zip file and changing the extension to **.sublime-package**. We'll dicuss packaging a bit more further on in this tutorial.

Sublime comes bundled with quite a number of different packages. Most of the bundled packages are language specific. These contain language packages, there are two other packages: **Default** and **User**. The Default package contains all of the standard key bindings, menu definitions, file settings and a whole bunch of plugins written in Python. The **User** package is special in that it is always loaded last. This allows users to override defaults by customizing files in their **User** package.

During the process of writing a plugin, the Sublime Text 2 API reference will be essential. In addition, the **Default** pakcage acts as a good refence for figuring out how to do things and what is possible. Much of the functionally of the editor is exposed via commands. Any operation other typing characters is  accomplished via commands. By viewing the Preferences > Key Bindings - Defaultmenu entry, it is possible to find a treasure trove of built-in functionality.

Now that the distinction between a plugin and package is clear, let’s begin writing our plugin.

## Step 1 - Starting a Plugin

Sublime commes with functionality that generates a skeleton of Python code needed to write a simple plugin. Select the Tools > New Plugin... menu entry, and a new buffer will be opened with this boilerplate.

```python 
import sublime, sublime_plugin

class ExampleCommand(sublime_plugin.TextCommand):
	def run(self, edit):
		self.view.insert(edit, 0, "Hello, World!")
```

Here you can see the two Sublime Python modules are imported to allow for use of the API and a new command class is created. Before editing this and starting to create our own plugin, let's save the file and trigger the built in functionality.

When we save the file we are going to create a new package to store it in. Press **ctrl+s** (Window/Linux) or cmd+s(OS X) to save the file. The save dialog will open to **User** package. Don't save the file there, but instead browse up a folder and create a new folder named **Prefixr**

> Packages/
...
- OCaml/
- Perl/
- PHP/
- Prefixr/
- Python/
- R/
- Rails/
...

Now save the file insdie of the Prefixr folder as **Prefixr.py**. It doesn't actually matter what the filename is, just that it ends in **.py**. However, by convention we will use the name of the plugin for the filename.

Now that the plugin is saved, let's try it out. Open the Sublime console by pressing **ctrl+**. This is a Python console that has access to the API. Enter the following Python to test out the new plugin.

> view.run_command('example')

You should see **Hello World** inserted into the beginning of the plugin file. Be sure to undo this change before we continue.

## Step 2 - Command Types and Naming

For plugins, Sublime provides three different types of commands.

* Text commands provide access to the contents of the selected file/buffer via a **View** object.

* Window commands provides references to the current window via a **Window** object

* Application commands do not have a reference to any specific window or file/buffer and are more rarely used

Since we will be manipulating the content of a CSS file/buffer with this plugin, we are going to use the **sublime_plugin.TextCommand** class as the basis of our custom Prefixr command. This brings us to the topic of naming command classes.

In the plugin skeleton provided by Sublime, you'll notice the class:

> class ExampleCommand(sublime_plugin.TextCommand):

When we wanted to run the command, we executed the following code in the console:

> view.run_command('example')

Sublime will take any class that extends one of the **sublime_plugin** classes **(TextCommand, WindowCommand or ApplicationCommand)**, remove the suffix **Command** and then convert the **CamelCase** into **underscore_notation** for the command name.

Thus, to create a command with the name **profixr**, the class needs to be **PrefixrCommand**.

> class PrefixrCommand(sublime_plugin.TextCommand):

## Step 3 - Selecting Text

Now that we have our plugin named properly, we can begin the process of grabbing CSS from the current buffer and sending it to the Prefixr API. One of the most useful features of Sublime is the ability to have multiple selections. As we are grabbing the selected text, we need to write out plugin into handle not just the first selection, but all ot them. 

Since we are writing a text command, we have access to the current view via **self.view**. The **sel()** method of the **View** object returns an iterable **RegionSet** of the current selections. We start by scanning through these for curly braces. If curly braces are not present we can expand the selection to the surrounding braces to ensure the whole block is prefixed. Whether or not our selection included curly braces will also be useful later to know if we can tweak the whitespace and formatting on the result we getback from the Prefixr API.

```python
braces = False
sels = self.view.sel()
for sel in sels:
	if self.view.substr(sel).find('{') != -1:
		braces = True
```

This code replaces the content of the skeleton **run()** method.

If we did not find any curly braces we loop through each selection and adjust the selections to the closet closing curly brace. Next, we use the build-in command **expand_selection** with the **to** arg set to **brackets** to ensure we have the complete contents of each CSS block selected.

```python
if not braces:
	new_sels = []
	for set in sels:
		new_sels.append(self.view.find('\}', sel.end()))
	sels.clear()
	for sel in new_sels:
		sels.add(sel)
	self.view.run_command("expand_selection", {"to": "brackets"})
```

If you would like to double check your work so far, please compare the source to the file **Prefixr-1.py** in the source code zip file.

## Step 4 - Threading

At this point, the selections have been expanded to grab the full contents of each CSS block. Now, we need to send them to the Prefixr API. This is a simple HTTP request, which we are going to use the **urlib** and **urllibs** modules for. However, before we start firing off web requests, we need to think about how a potentially laggy web request could affect the performance of the editor. If, for some reason, the user is on a high-latency, or show connection, the requests to the Prefixr API could easily take a couple of seconds or more.

To prevent a poor connection from interrupting other work, we need to make sure that the Prefixr API calls are happening in the backgroud. If you don't know anything about threading, a very basic explanation is that threads are a way for a program to shedule multiple sets of code to run seemingly at the same time. It is essential in our case because it lets the code that is sending data to, and waiting for a response from, the Prefixr API from preventing the rest of the Sublime user interface from freezing. 

## Step 5 - Creating Threads

We will be using the Python **threading** module to create threads. To use the threading module, we create a new that extends **threading.Thread** called **PrefixrApiCall**. Classes that extend **threading.Thread** include a **run()** method that contains all code to be executed in the thread.

```python
class PrefixrApiCall(threading.Thread):
	def __init__(self, sel, string, timeout):
		self.sel = sel
		self.original = string
		self.timeout = timeout
		self.result = None
		threadinig.Thread.__init__(self)

	def run(self):
		try:
			data = urllib.urlencode({'css': self.original})
			request = urllib2.Request('http://prefixr.com/api/index.php', data, headers={"User-Agent": "Sublime Prefixr"})
			http_file = urllib2.urlopen(request, timeout=self.timeout)
			self.result = http_file.read()
			return

		except (urllib2.HTTPError) as (e):
			err = '%s: HTTP error %s contacting API' % (__name__, str(e.code))
		except
			err = '%s: URL error %s contacting API' % (__name__, str(e.reason))

		sublime.error_message(err)
		self.result = False
```

Since we just started using some modules in out plugin, we must add them to the import statements at the top of the script.

```python
import urllib
import urlib2
import threading
```

Noew that we have a threaded class to perform the HTTP calls, we need to create a thread for each selection. To do this we jump back into the **run()** method of our **PrefixrCommand** class and use the following loop:

```python
threads = []
for sel in sels:
	string = self.view.substr(sel)
	thread = PrefixrApiCall(sel, string, 5)
	thread.append(thread)
	thread.start()
```

We keep track of each thread we create and then call the **start()** method to start each.

If you would like to double check your work so far, please compare the source to the file **Prefixr-2.py** in the source code zip file.

## Step 6 - Preparing for Results

Now that we've begun the actual Prefixr API requests we need toset up a few last details before handing the responses.

First, we clear all of the selections because we modified them earlier. Later we will set them back to reasonable state.

> self.view.sel().clear()

In addition we start a new **Edit** object. This groups operations for undo and redo.
we specify that we are creating a group for the **prefixr** command.

> edit = self.view.begin_edit('prefixr')

As the final step, we call a method we will write next that will handle the result of the API requests.

> self.handle_threads(edit, threads, braces)

## Step 7 - Handling Threads

At this point our threads are running, or possibly even completed. Next, we need to implement the **handle_thread()** method we just referenced. This method is going to loop through the list of threads and look for threads that are no longer running.

```python
def handle_threads(self, edit, threads, braces, offset=0, i=0, dir=1):
	next_threads = []
	for thread in threads:
		if thread.is_alive():
			next_threads.append(thread)
			continue
		if thread.result == False:
			continue
		offset = self.replace(edit, thread, braces, offset)
	threads = next_threads
```

If a thread is still alive, we add it to the list of threads to check again later. If the result was a failure, we ignore it, however for good results we call a new **replace()** method that we'll be writing soon.

If there are any threads that are still alive, we need to check those again shortly. In addition, it is a nice user interface enhancement to provide an activity indicator to show that our plugin is still running.

```python
if len(threads):
    # This animates a little activity indicator in the status area
    before = i % 8
    after = (7) - before
    if not after:
        dir = -1
    if not before:
        dir = 1
    i += dir
    self.view.set_status('prefixr', 'Prefixr [%s=%s]' % \
        (' ' * before, ' ' * after))
 
    sublime.set_timeout(lambda: self.handle_threads(edit, threads,
        braces, offset, i, dir), 100)
    return
```

The first section of code uses a simple integer value stored in the variable **i** to move an **=** back and forth between two brackets. The last part is the most important though. This tells Sublime to run the **handle_threads()** method again, with new values, in another 100 milliseconds. This is just like the **setTimeout()** function in JavaScript.

The **sublime.set_timeout()** method requires a function or method and the number of milliseconds until it should be ececuted. Without **lambda** we could tell it we wanted to run **handle_thread()**, but we would not be able to specify the parameters.

The **sublime.set_timeout()** method requires a function or method and the number of milliseconds until it should be executed. Without **lambda** we could tell it we wanted to run **handle_threads()**, but we would not be able to specify the parameters.

If all of the threads have completed, we don't need to set another timeout, but instead we finish our undo group and update the user interface to let the user know everything is done.

```python
self.view.end_edit(edit)
 
self.view.erase_status('prefixr')
selections = len(self.view.sel())
sublime.status_message('Prefixr successfully run on %s selection%s' %
    (selections, '' if selections == 1 else 's'))
```

If you would like to double check your work so far, please compare the source to the file **Prefixr-3.py** in the source code zip file.

## Step 8 - Performing Replacements

With our threads handled, we now just need to write the code that replaces the original CSS with the resutl from the Prefixr API. As we referenced earlier, we are going to write a method called **replace()**.

This method accepts a number of parameters, including the **Edit** object for undo, the thread that grabbed the result from the Prefixr API, if the original selection included braces, and finally the selection offset.

```python
def replace(self, edit, thread, braces, offset):
    sel = thread.sel
    original = thread.original
    result = thread.result
 
    # Here we adjust each selection for any text we have already inserted
    if offset:
        sel = sublime.Region(sel.begin() + offset,
            sel.end() + offset)
```

The offset is necessary when dealing with multiple selections. When we replace a block of CSS with the prefixed CSS, the length of that block will increase. The offset ensures we are replacing the correct content for subsequent selections since the text positions all shift upon each replacement.

The next step is to prepare the result from the Prefixr API to be dropped in as replacement CSS。 This includes converting line endings and indentation to match the current document and original selection.

```python
result = self.normalize_line_endings(result)
(prefix, main, suffix) = self.fix_whitespace(original, result, sel, braces)
self.view.replace(edit, set, prefix + main + suffix)
```

As a final step we set the user's selction to include the end of the last line of the new CSS we inserted, and adjusted offset to use for any further selections.

```python
end_point = sel.begin() + len(prefix) + len(main)
self.view.sel().add(sublime.Region(end_point, end_point))
return offset + len(prefix + main + suffix) = len(original)
```

If you would like to double checek you work so far, please compare the source to the file **Prefixr-4.py** in the source code zip file.

## Step 9 - Whitespace Manipulation

We used two custom methods during the replacement process to prepare the new CSS for the document. These methods take the result of Prefixr and modify it to match the current document.

**normalize_line_endings()** takes the string and makes sure it matches the line endings of the current file. We use the **Settings** class from Sublime API to get the proper line endings.

```python
def normalize_line_endings(self, string):
	string = string.replace('\r\n', '\n').replace('\r', '\n')∏
    line_endings = self.view.settings().get('default_line_ending')
    if line_endings == 'windows':
        string = string.replace('\n', '\r\n')
    elif line_endings == 'mac':
        string = string.replace('\n', '\r')
    return string
``` 

The **fix_whitespace()** method is a little more complicated, but does the same kind of manipulation, just for the indentation and whitespace in the CSS block. This manipulation only really works with a single block of CSS, so we exit if one or more braces was included in the original selection.

```python
def fix_whitespace(self, original, prefixed, sel, braces):
    # If braces are present we can do all of the whitespace magic
    if braces:
        return ('', prefixed, '')
```

Otherwise, we start by determining the indent level of the original CSS. This is done by searching for whitespace at the beginning of the selection.

```python
(row, col) = self.view.rowcol(sel.begin())
indent_region = self.view.find('^\s+', self.view.text_point(row, 0))
if self.view.rowcol(indent_region.begin())[0] == row:
    indent = self.view.substr(indent_region)
else:
    indent = ''
```

Next we trim the whitespace from the prefixed CSS and use the current view settings to indent the trimmed CSS to the original level using either tabs or spaces depending on the current editor settings.

```python
prefixed = prefixed.strip()
prefixed = re.sub(re.compile('^\s+', re.M), '', prefixed)
 
settings = self.view.settings()
use_spaces = settings.get('translate_tabs_to_spaces')
tab_size = int(settings.get('tab_size', 8))
indent_characters = '\t'
if use_spaces:
    indent_characters = ' ' * tab_size
prefixed = prefixed.replace('\n', '\n' + indent + indent_characters)
```

We finish the method up by using the original beginning and trailing white space to ensure the new prefixed CSS file exactly in place of the original.

```python
match = re.search('^(\s*)', original)
prefix = match.groups()[0]
match = re.search('(\s*)\Z', original)
suffix = match.groups()[0]
 
return (prefix, prefixed, suffix)
```

With the **fix_whitespace()** method we used the Python regular expression

