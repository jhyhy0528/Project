
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
