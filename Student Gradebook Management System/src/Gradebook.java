//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    (Gradebook.java)
// Course:   CS 300 Spring 2024
//
// Author:   (Annabelle Jeong)
// Email:    (hjeong58@wisc.edu)
// Lecturer: (Mouna Kacem)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
// 
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:        
// Online Sources
// https://cs300-www.cs.wisc.edu/sp24/p09/doc/Gradebook.html
// 
//
///////////////////////////////////////////////////////////////////////////////
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * This class represents a gradebook that keeps student records for a specific course, and also 
 * allows managing student grades and related information in a BST data structure. 
 */
public class Gradebook extends Object implements Iterable<StudentRecord> {
  
  public final String course;
  
  public final double PASSING_GRADE;
  
  private BSTNode<StudentRecord> root;
  
  private int size;
  
  private boolean passingGradeIteratorEnabled;
  
  /**
   * It is a constructor named Gradebook with specified course and passing grade. 
   * @param course It is the name of the course
   * @param passingGrade It is the minimum number passing grade for specific course. 
   * @throws IllegalArgumentException if the course is null or blank or if the passing
   * grade is not in between 0 to 100. 
   */
  public Gradebook(String course, double passingGrade) {
    if (course == null || course.isBlank()) {
      throw new IllegalArgumentException();
    }
    
    if (passingGrade < 0 || passingGrade > 100) {
      throw new IllegalArgumentException();
    }
    
    this.course = course; 
    this.PASSING_GRADE = passingGrade; 
  }
  
  
  /**
   * It makes it possible to iterate through students with grades above the pass mark. 
   */
  public void enablePassingGradeIterator() {
    this.passingGradeIteratorEnabled = true;
    
  }
  
  
  /**
   * It will disable the passing grade iterator
   * and reverting to default iteration over all student.
   * 
   */
  public void disablePassingGradeIterator() {
    this.passingGradeIteratorEnabled = false; 
    
  }
  
  
  /**
   * It checks if the gradbook is empty or not 
   * @return true if it is empty or else return false.
   */
  public boolean isEmpty() {
    return size == 0;
  }
  
  
  /**
   * It returns the number of student records. 
   * @return number of records 
   */
  public int size() {
    return size;
  }
  
  
  /**
   * This method adds a student record to the gradebook 
   * @param record It is a record of student to add in the gradebook. 
   * @throws IllegalStateException If there is a duplicate record in the tree. 
   */
  public void addStudent(StudentRecord record) {
    try {
      root = addStudentHelper(record, root);
      size ++;
    } catch (IllegalStateException e){
      throw new IllegalStateException("A match with record is alreay in this tree!");
    }
  }
  
  
/**
 * It is a helper function that is recursive to add a student record to the BST. 
 * 
 * @param record the student to add
 * @param node is the current node in the recursion
 * @return the new node after the insertion
 * @throws IllegalStateException if you add a duplicate record 
 */
  protected static BSTNode<StudentRecord> addStudentHelper(StudentRecord record,
      BSTNode<StudentRecord> node){
    if (node == null) {
      return new BSTNode<>(record);
    }
   
  if (record.compareTo(node.getData()) == 0) {
    throw new IllegalStateException("It has duplicate record!");
  } else if (record.compareTo(node.getData()) < 0 ) {
    node.setLeft(addStudentHelper(record, node.getLeft()));
  } else {
    node.setRight(addStudentHelper(record, node.getRight()));
  }
  return node;
  }
  
  
  /**
   * It is a method that looks up student record with email 
   * @param email of student to search up 
   * @return the student record with specific email that is given 
   * if email is null or empty, return null 
   */
  public StudentRecord lookup(String email) {
    if (email == null || email.isBlank()) {
      return null;
    }
    
    StudentRecord target = new StudentRecord("Dummy", email, 0); 
    return lookupHelper(target, root);
    
    
    
  }
  
  
  /**
   * It is a helper function for the look up function and it is recursive
   * It helps to lookup a student's record by dummy student. 
   * 
   * @param target it is a dummy Student target email 
   * @param node It is the current node in the recursion 
   * @return the founded student record or else return null if it cannot be found
   */
  protected static StudentRecord lookupHelper(StudentRecord target,
      BSTNode<StudentRecord> node) {
    
    if (node == null){
      return null;
    }
    
    if (target.compareTo(node.getData()) == 0) {
      return node.getData();
    } else if (target.compareTo(node.getData()) < 0) {
      return lookupHelper(target, node.getLeft());
    } else {
      return lookupHelper(target, node.getRight());
    }
  }
  
  
  /**
   * It is a method that checks if specific student have a passing grade for their course. 
   * @param email It is a email address to look for a student. 
   * @return "PASS" or "FAIL" based on their grades or even "No match found" if the email is null,
   * blank, or cannot be found. 
   */
  public String checkPassingCourse(String email) {
    if (email == null || email.isBlank()) {
      return "No match found.";
    }
    
    StudentRecord student = lookup(email);
    
    if (student == null ) {
      return "No match found.";
    }
    
    if (student.getGrade() >= PASSING_GRADE) {
        return student.toString() + ": PASS";
    } else {
        return student.toString() + ": FAIL";
    }
   
  }
    
    
  
  /**
   * It finds and returns the StudentRecord with the lexicographically
   * 
   * @return smallest email in this BST or else null if this Gradebook is empty
   */
  protected StudentRecord getMin() {
    if (root == null) {
      return null;
      
    }
    
    return getMinHelper(root);
    
  }
  
  
  /**
   * It is a helper that is recursive to get the student's record with minimum amount of email. 
   * @param node is the current node in the recursion 
   * @return student record with minimum email or else return null if the root is null. 
   */
  protected static StudentRecord getMinHelper(BSTNode<StudentRecord> node) {
    
    if(node.getLeft() == null) {
      return node.getData();
    }else {
      return getMinHelper(node.getLeft());
    }
    

    
  }
  
  /**
   * It returns the successor of a target StudentRecord which is smallest value in the BST that is 
   * larger than the target. 
   * 
   * @param target the StudentRecord to find the successor of
   * @return the successor of the target in the Gradebook or null if none exists
   */
  protected StudentRecord successor(StudentRecord target) {
    
    if (root == null) {
      return null;
    
      
    }
    return successorHelper(target, root);
    
    
  }

  
  /**
   * It is a helper that returns the successors of a target StudentRecord within the subtree. 
   * 
   * @param target the StudentRecord to find the successor of
   * @param node the subtree to search for a successor to the target
   * @return the successor of the target in the subtree rooted at node, or null if none exists. 
   */
  protected static StudentRecord successorHelper(StudentRecord target,
      BSTNode<StudentRecord> node) {
    if (node == null) {
      return null;
  }

  if (target.compareTo(node.getData()) >= 0) {
     
      return successorHelper(target, node.getRight());
  } else {
      
      StudentRecord left = successorHelper(target, node.getLeft());
      if (left != null) {
         
          return left;
      } else {
          
          return node.getData();
      }
  }
}
 
    
    
  /**
   * It is a method that removes a studentrecord from this gradebook based on their emails. 
   * 
   * @param emails It is a email from the students that should be deleted. 
   * @throws NoSuchElementException if there is no matching student record from this gradebook. 
   */
  public void removeStudent(String email) {
    if (lookup(email) == null) {
      throw new NoSuchElementException("There is no matching StudentRecord in this Gradebook.");
    }
    
    if (email == null || email.isEmpty()) {
      throw new NoSuchElementException("The email can't be empty.");
    }
    
    try{
      root = removeStudentHelper(new StudentRecord("Rei", email, 0.0), root);
      size--; 
     }catch(NoSuchElementException e) {
     
     }
    
    
    
  }
  
  
  /**
   * It is a helper that removes the matching StudentRecord with toDrop if its in the tree. 
   * 
   * @param toDrop the StudentRecord to be removed from this tree
   * @param node  the root of the subtree to remove the student from
   * @return It is the modified root after removing the matched StudentRecord 
   * @throws NoSuchElementException if there is no matching StudentRecord in this subtree
   */
  protected static BSTNode<StudentRecord> removeStudentHelper(StudentRecord toDrop,
      BSTNode<StudentRecord> node){
    
    if (node == null) {
      throw new NoSuchElementException("There is no mathcing student record found in this subtree!");
  }
  int comparison = toDrop.compareTo(node.getData());
  if (comparison < 0) {
      node.setLeft(removeStudentHelper(toDrop, node.getLeft()));
  } else if (comparison > 0) {
      node.setRight(removeStudentHelper(toDrop, node.getRight()));
  } else { 
      if (node.getLeft() == null) {
          return node.getRight();  
      }
      if (node.getRight() == null) {
          return node.getLeft();
      }
      
      StudentRecord successor = getMinHelper(node.getRight());
      node.setData(successor);
      node.setRight(removeStudentHelper(successor, node.getRight()));
  }
  return node;

    
  }
  
  
  /**
   * This method returns a string representing of the contents of this GradeBook in 
   * smallest to biggest order. 
   * @return String representation  of GradeBook
   */
  @Override
  public String toString() {
    return toStringHelper(root);
    
  }
  
  
  /**
   * It is a helper of toString that returns the subtree rooted at node 
   * @param node It is a root of a subtree 
   * @return ordered string representation of subtree rooted at node 
   */
  protected static String toStringHelper(BSTNode<StudentRecord> node) {
    if (node == null) {
      return "";
    }
    
    return toStringHelper(node.getLeft()) + node.getData().toString() 
        + "\n" + toStringHelper(node.getRight());
    
  }
  
  
  /**
   * It is a method that represents the structure of this BST. 
   * must exhibit the StudentRecords in a descending order (highest to lowest) and each
   *  StudentRecord should be indented (space from the left side of the screen to the student 
   *  names) by four spaces at every level of depth in the tree.
   * @return a String representation of the structure of this BST
   */
  public String prettyString() {
    return prettyStringHelper(root,0);
   
    
    
  }
  
  
  /**
   * It is a helper of prettyStrings that returns a decreasing order of String representation 
   * of this subtree. There is four spaces for each level of depth in a large tree.
   * @param node current subtree within the larger tree
   * @param depth depth of the current node within the larger tree
   * @return a String representation of the structure of this subtree
   */
  protected static String prettyStringHelper(BSTNode<StudentRecord> node,
      int depth) {
    
    if (node == null) {
      return "";
      }
    
      String indent = " ".repeat(depth * 4);
      return prettyStringHelper(node.getRight(), depth + 1) + indent + node.getData().name
      + "\n" + prettyStringHelper(node.getLeft(), depth + 1);
    
  }
  
  
  /**
   * It is a method that checks rather the BST has an identical layout to those given tree. 
   * @param node tree to compare this Gradebook to
   * @return true if the given tree looks identical to the root of this Gradebook
   */
  public boolean equalBST(BSTNode<StudentRecord> node) {
    return root == node || (root != null && root.equals(node));
    
  }

  
  /**
   * It returns an iterator that is used to get through all the student records from this gradebook.
   *  When it has a passing grade iterator, this approach does not take in any recording 
   *  which is not able to pass.
   *  
   *  @return an Iterator over the elements in this gradebook in proper sequence.
   */
  public Iterator<StudentRecord> iterator(){
    
    if (passingGradeIteratorEnabled) {
      return new PassingGradeIterator(this);
    } else {
      return new GradebookIterator(this);
    }
    
  }
   
  
  
}
