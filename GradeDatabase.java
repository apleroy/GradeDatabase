/*
 * GradeDatabase.java
 *
 * Author:          Computer Science S-111
 * Modified by:     Andy LeRoy, apleroy@gmail.com
 * Date modified:   8/1/13
 */

import java.util.*;

/**
 * A simple in-memory database of student and grade information.
 */
public class GradeDatabase {
    /* 
     * A private inner class for storing information about a student.
     */
    private class StudentRecord {
        private int id;
        private String lastName;
        private String firstName;
        
        StudentRecord(int id, String lastName, String firstName) {
            this.id = id;
            this.lastName = lastName;
            this.firstName = firstName;
        }
    }
    
    /* 
     * A private inner class for storing information about a student's
     * grade on a particular assignment.
     */
    private class GradeRecord {
        private int studentID;
        private String assignment;    // e.g., "PS 1" or "midterm"
        private int grade;
        
        GradeRecord(int studentID, String assignment, int grade) {
            this.studentID = studentID;
            this.assignment = assignment;
            this.grade = grade;
        }
    }
    
    /**** add your instance variables here ****/
    private LLList StudentRecordTable;
    private LLList GradeRecordTable;
        
    /**
     * Constructs a LLList object for a list that is initially empty.
     */
        
    public GradeDatabase() {
        /** complete the constructor below **/
        
        StudentRecordTable = new LLList(); //LLList to hold student record objects

        GradeRecordTable = new LLList(); //LLList to hold grade record objects

    }
    
    /**
     * addStudent - add a record for the student with the specified information
     */
    public void addStudent(int id, String last, String first) {
        /* complete the method below */
        //make a new StudentRecord object that holds the above info.  This object is ceated using the constructor
        StudentRecord studentObject = new StudentRecord (id, last, first);

        //add the StudentRecord Object to the Student Record LLList, which is an object of the GradeDatabase object
        //addItem(Object item, int i)
        StudentRecordTable.addItem(studentObject, 0); //places item at beginning of list (constant time)

    }
    
    /**
     * addGrade - add a record for the grade entry with the specified details
     */
    public void addGrade(int id, String asst, int grade) {
        /* complete the method below */
        //make a new GradeRecord object that holds the above info.  This object is ceated using the constructor
        GradeRecord gradeObject = new GradeRecord (id, asst, grade);

        //add the StudentRecord Object to the Student Record LLList, which is an object of the GradeDatabase object
        //addItem(Object item, int i)
        GradeRecordTable.addItem(gradeObject, 0);  //add at 0 is constant time.

    }
    
    /**
     * printStudents - print the entries in the student table
     */
    public void printStudents() {
        System.out.println();
        System.out.println("id\tlast\t\tfirst");
        System.out.println("--------------------------------------------");
        
        /* complete the method below */
        //The student table is all StudentRecord objects in the StudentRecordTable
        //We need to traverse down the LLList and print all objects (rows of data).

        ListIterator iter = StudentRecordTable.iterator();
        
        while (iter.hasNext()) {
            StudentRecord current = (StudentRecord) iter.next();
            System.out.print("\n" + current.id + "\t" + current.lastName + "\t\t" + current.firstName);
        }

    }
    
    /**
     * printGrades - print the entries in the grade table
     */
    public void printGrades() {
        System.out.println();
        System.out.println("id\tassignment\tgrade");
        System.out.println("--------------------------------------------");
        
        /* complete the method below */
        //The student table is all StudentRecord objects in the StudentRecordList
        //We need to traverse down the LLList and print all objects (rows of data).
       
        ListIterator iter = GradeRecordTable.iterator();
        
        while (iter.hasNext()) {
            GradeRecord current = (GradeRecord) iter.next();
            System.out.print("\n" + current.studentID + "\t" + current.assignment + "\t\t" + current.grade);
        }
    }
    
    /**
     * printStudentsGrades - print a "join" of the student and grade
     * tables.  See the problem set handout for more details.
     */
    public void printStudentsGrades() {
        System.out.println();
        System.out.println("last\t\tfirst\tassignment\tgrade");
        System.out.println("------------------------------------------------");
        
        /* complete the method below */
        
        
        /*The iterator can only go down (and can't reset).  Because there cannot be grade records for students who are not entered
        in the StudentRecordTable (a "1 to many" type relationship at least from what I understand), I used the iterator to 
        traverse down the StudentRecordTable.

        I then put a for loop in place to iterate through the GradeRecordTable, each time getting the record object at position i and comparing
        the key to the key of the student record table.  If they were the same, I printed the results.

        EFFICIENCY:
        Inserting an item into either table in this case is O(1) or constant time.  Becuase this is an LLList, adding at the beginning is a 
        constant operation.

        The "Join" method in this situation requires O(n^2) time.
        In my situation, the "join" method of the StudentRecordTable traverses the length of the table for O(n) and each time it searches
        the GradeRecordTable for matches O(n) time.  Together this makes O(n^2) efficiency.

        NEXT TIME:
        This join I created seems a bit inefficient.  Particularly becuase this a "student/grade" type database, I would think that 
        a user (student, teacher, etc) would be printing or viewing the contents more often than a user would be entering new records into the
        database.  Based on this assumption, it would make sense to take more time upfront on the insert to make the join method more efficient.

        It looks like we could take O(n) time to insert the studentRecord and/or gradeRecord in order, which would then allow O(n) time on the join
        method as only one pass down with constant comparisons would be necessary.
       
        */
        ListIterator iterS = StudentRecordTable.iterator();
        int gLength = GradeRecordTable.length();

        while (iterS.hasNext()) {
            StudentRecord sr = (StudentRecord) iterS.next();
            
            for (int i = 0; i < gLength; i++) {
                GradeRecord gr = (GradeRecord) GradeRecordTable.getItem(i);
                if (sr.id == gr.studentID) {
                    System.out.print("\n" + sr.lastName + "\t\t" + sr.firstName + "\t" + gr.assignment + "\t\t" + gr.grade);
                }
            }
        }   

    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String last, first, asst;
        int id, grade, op;
        
        GradeDatabase db = new GradeDatabase();
        
        while (true) {
            System.out.println();
            System.out.println("(1) Add student");
            System.out.println("(2) Add grade");
            System.out.println("(3) Print students");
            System.out.println("(4) Print grades");
            System.out.println("(5) Print each student's grades");
            System.out.println("(6) Exit");
            System.out.print("Which operation (1-6)? ");
            op = in.nextInt();
            in.nextLine();
            
            switch (op) {
                case 1:
                    System.out.print("    id: ");
                    id = in.nextInt();
                    in.nextLine();
                    System.out.print("    last: ");
                    last = in.nextLine();
                    System.out.print("    first: ");
                    first = in.nextLine();
                    
                    db.addStudent(id, last, first);
                    break;
                case 2:
                    System.out.print("    student id: ");
                    id = in.nextInt();
                    in.nextLine();
                    System.out.print("    assignment: ");
                    asst = in.nextLine();
                    System.out.print("    grade: ");
                    grade = in.nextInt();
                    in.nextLine();
                    
                    db.addGrade(id, asst, grade);
                    break;
                case 3:
                    db.printStudents();
                    break;
                case 4:
                    db.printGrades();
                    break;
                case 5:
                    db.printStudentsGrades();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.  " + 
                                       "Please enter a number from 1-6.");
            }
        }
    }
}
