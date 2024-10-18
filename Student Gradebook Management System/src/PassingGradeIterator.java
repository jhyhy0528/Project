//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    (PassingGradeIterator.java)
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
// Persons: TA Tuesday session with next()       
// Online Sources:
// https://cs300-www.cs.wisc.edu/sp24/p09/doc/PassingGradeIterator.html
// 
//
///////////////////////////////////////////////////////////////////////////////
import java.util.NoSuchElementException;
import java.util.Iterator;

/** 
 * This class is called PassingGradeIterator and is used for the Gradebook traversal. 
 * Specifically, it traverses student records with grades that are at or above the 
 * minimum passing grade. It extends the GradebookIterator and eliminates 
 * students with grades below the minimum required mark.
 */
public class PassingGradeIterator extends GradebookIterator {
  
  /**
   *It is a rference to the current StudentRecord with a 
   *passing grade to be returned by this iterator
   */
  private StudentRecord next;
  
  /**
   * It is a minimum grade for Passing. 
   */
  private double passingGrade;
  
  
  /**
   * This constructs a new PassingGradeIterator to iterate over the StudentRecords 
   * with passing grades in a given Gradebook (StudentRecords with NO passing grades 
   * are skipped by this iterator).This iterator sets the passing grade to the gradebook passing 
   * grade and advances the iterator to the first student record with passing grade in the iteration
   *  by calling the advanceToNextPassingGrade() helper method.
   * @param gradebook  Gradebook to iterate over.
   */
  public PassingGradeIterator(Gradebook gradebook) {
    super(gradebook);
    this.passingGrade = gradebook.PASSING_GRADE;
    advanceToNextPassingGrade();

    
  }
  
  
  /**
   * This is a private helper method that advances this iterator to the next StudentRecord with a 
   * passing grade. Then, it stores it into next. If no more StudentRecord with a passing grade are
   * available in the iteration, this method sets next to null. This method uses super.hasNext() 
   * and super.next() in a while loop to operate.
   */
  private void advanceToNextPassingGrade() {
    
    next = null; 
    while (super.hasNext()) {
        StudentRecord currentRecord = super.next();
        if (currentRecord.getGrade() >= passingGrade) {
            next = currentRecord;
            break; 
        }
    }
    
  }
  
  
  /**
   * It checks if there are any more student records with a passing grade.
   * 
   * @return true if there is next sutdent with passing grade, or else return false 
   */
  @Override
  public boolean hasNext() {
    if (next != null) {
      return true;
    }
    
    return false; 
    
  }
  
  
  /**
   * Returns the next StudentRecord object with a passing grade in the iteration and advances 
   * the iteration to the next record with passing grade.
   * 
   * @return true the next StudentRecord with a passing grade in the iteration
   * @throws NoSuchElementException if the iteration has no more elements
   */
  @Override
  public StudentRecord next() {
    if (next == null) {
      throw new NoSuchElementException("There is no more "
          + "StudentRecord objects with a passing grade!");
    }
    StudentRecord currentRecord = next;
    advanceToNextPassingGrade();
    return currentRecord;

    
  }

}


