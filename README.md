# CORBA

## Program Code

CORBA is a middeware design that allows application programs to communicate with one
another irrespective of their programming languages, their hardware and software
platforms, the networks they communicate over and their implementors.

Applications are built from CORBA objects, which implement interfaces defined in
CORBA’s interface definition language, IDL.

Clients access the methods in the IDL
interfaces of CORBA objects by means of RMI.

The middleware component that supports
RMI is called the Object Request Broker or ORB.

The specification of CORBA has been sponsored by members of the Object
Management Group (OMG).

Many different ORBs have been implemented from the
specification, supporting a variety of programming languages including Java and C++.

CORBA services provide generic facilities that may be of use in a wide variety of
applications.

They include the Naming Service, the Event and Notification Services, the
Security Service, the Transaction and Concurrency Services and the Trading Service.


### IDL Module

```
module Whiteboard {
	struct Rectangle{
	...} ;
	struct GraphicalObject {
	...};
	interface Shape {
	...};
	typedef sequence <Shape, 100> All;
	interface ShapeList {
	...};
};
```
