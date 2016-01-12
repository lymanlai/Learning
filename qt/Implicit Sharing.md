# Implicit Sharing

## Implicit Sharing

### Overview

A shared class consists of a pointer to a shared data block that contains a reference count and the data.

When a shared object is created, It sets the reference count to 1. The reference count is incremented whenever a new objec references the shared data, and decremented when the object dereferences the shared data. The shared data is deleted when the reference count becomes zero.

When dealing with shared objects, there are two ways of copying an object. We usually speak about deep and shallow copies. A deep copy implies duplicating an object. A shallow copy is reference copy, i.e. just a pointer to a shared data block. Making a deep copy can be expensive in terms of memory and CPU. Making a shallow copy is very fast, because it only involves setting a pointer and incrementing the reference count.

Object assignment(with operator=()) for implicitly shared objects is implemented using shallow copies.

The benefit of sharing is that a program does not need to duplicate data unnecessarily, which results in lower memory use and less copying of data. Object can easily be assigned, send as fucntion arguments, and returned from functions.

Implicit sharing mostly takes place behind the scenes; the programmer rarely needs to worry about it. However, Qt's container iterators have different behavior than those from the STL. Read Implicit sharing iterator problem.

In multithreaded applications, implicit sharing takes place, as explained in Threads and Implicitly Shared Classes.

When implemeting your own implicitly shared classes, use the QSharedData and QSharedDataPointer classes.


### Implicit Sharing in Detail

Implicit sharing automatically detaches the object from a shared block if the object is about to change and the reference count is greater than one. (This is often called copy-on-write or value semantics.)

An implicitly shared class has control of its internal data. In any member functions that modify its data, it automatically detaches before modifying the data. Notice, however, the special case with container iterators; see Implicit sharing iterator problem. 

The QPen class, which uses implicit sharing, detaches from the shared data in all member funtions that change the internal data.

Code fragment:

```c++
void QPen::setStyle(Qt::PenStyle style)
{
    detach();           // detach from common data
    d->style = style;   // set the style member
}

void QPen::detach()
{
    if (d->ref != 1) {
        ...             // perform a deep copy
    }
}
```




