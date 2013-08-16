GradeDatabase
=============

Overall, this program is an in-memory database that accepts and adds student records and grade records (each are objects).  

There are then options to print the records or "join" them together in print form.

The framework for the database (GradeDatabase.java, List.java, and LLList.java) was provided by the course instructors.
List.java is an abstract data type - an interface.  
LLList is an implementation of that List using a Linked List as the data structure.
ListIterator is also an interface that is implemented within the GradeDatabase program.
All of the above classes and interfaces were provided by the instructors.

For this program, I instatiated two instances of LLList (StudentRecordTable and GradeRecordTable),
to hold student record objects and grade record objects.

I then added constructors for each object (Student records and grade objects) and implemented the method to add them
to the appropriate LLList instantiated earlier.

I then instantiated two ListIterators in order to loop through each LLList to print the objects.

The final method (printStudentGrades) "joins" the two tables together in a print function.  To do this, I used a
ListIterator to traverse down the list of students and for each grab any associated grade objects and print them together.

As indicated in the comments of GradeDatabase.java, the "join" method which prints the two lists and associated objects
together has efficiency of O(n^2) but the insertion of each record in O(1).  In the future, I would consider alternative
design that may be slower in insertion O(n) in order to provide a more efficient join O(n).

