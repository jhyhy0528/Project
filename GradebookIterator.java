//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    (GradebookIterator.java)
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
// https://cs300-www.cs.wisc.edu/sp24/p09/doc/GradebookIterator.html
// 
//
///////////////////////////////////////////////////////////////////////////////
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * This class is called GradebookIterator and it It returns an iterator for moving through
 *  StudentRecord objects in a  Gradebook instance
 *  The order of this iterator is such that it can be iterated from the beginning to 
 *  the end of the grade book.
 */
public class GradebookIterator extends Object implements Iterator<StudentRecord> {
  
  /**
   * Current StudentRecord reference.
   */
  private StudentRecord current;
  
  /**
   * Gradebook to iterate over
   */
  private Gradebook gradebook;
  
  /**
   * It constructs a GradebookIterator and allows basic iteration over elements 
   * and is designed to work with a grade book that is ordered.
   * It also creates a GradebookIterator for the specified Gradebook starting with
   *  the smallest student record.
   *  
   * @param gradebook to iterate over.
   */
  public GradebookIterator(Gradebook gradebook) {
    this.gradebook = gradebook; 
    this.current = this.gradebook.getMin();
    
  }
  
  
  /**
   * This checks if there is any element in the next of the Gradebook 
   * 
   * @return true if the iteration has more elements or else return false
   */
  public boolean hasNext() {
    if (current != null) {
      return true;
    }
    return false;
  }
  
  
  /**
   * It returns any other element in the grade book, then the iterator’s position 
   * gets updated accordingly.
   * If there are no more elements left this method will throw NoSuchElementException
   * 
   * @return the next element in the iteration
   * @throws NoSuchElementException if the iteration has no more elements
   */
  public StudentRecord next() {
    
    if (current == null) {
      throw new NoSuchElementException("There is no elements in the iteration!");
      
    }
    StudentRecord record = current;
    current = gradebook.successor(current);;
    return record;
  }
  
  
  

}
