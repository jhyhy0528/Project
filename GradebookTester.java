//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    (GradebookTester.java)
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
// Persons: TA Tuesday session with removeStudentTester(), successorTester(), 
// and prettyStringTester()
// Online Sources:
// 
// 
//
///////////////////////////////////////////////////////////////////////////////
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * The class is containing of a set of static methods that are use to prove several functionalities
 * in relation to Gradebook class. There are different methods which prove
 * the following; constructors, adding students and testing for empty,
 * look up, string representations and iterators.
 */
public class GradebookTester {
  
  
  /**
   * It is a tests that aim at invalid and valid constructor inputs to test whether exceptions 
   * @return true if all constructor is true or else return false 
   */
  public static boolean constructorTester() {
    
    //Check if it throws exception if the course is null 
    try {
      new Gradebook (null, 80.0);
      return false; 
    } catch (IllegalArgumentException e) {
      
    }
      
    //Check if it throws exception if the course is blank 
    try {
      new Gradebook ("", 80.0);
      return false; 
    } catch (IllegalArgumentException e) {
      
    }
    
    //Check if it throws exception if the grade is smaller than 0. 
    try {
      new Gradebook("Compsci", -800.0);
      return false; 
    } catch (IllegalArgumentException e) {   
      
    }
    
    //Check if it throws exception if the grade is bigger than 100. 
    try {
      new Gradebook("Compsci", 1000.0);
      
      return false;
    
    }catch (IllegalArgumentException e) {
      
    }
    
    //Check if it has the right course name and passing grade 
    try {
      Gradebook Gradebook = new Gradebook("Compsci", 75.0);
      if (Gradebook.course != "Compsci" || Gradebook.PASSING_GRADE != 75.0) {
          return false; 
          }
      } catch (Exception e) {
        
        return false; 
        
      }
    
    return true; 
    
  }
      
  
  /**
   * It tests the isEmpty and size methods of the Gradebook class and 
   * adding a student to ensure these functionalities behave as expected. 
   * @return true if all test pass or else fale 
   */
  public static boolean isEmptySizeAddTester() {
    
    Gradebook Gradebook = new Gradebook("Compsci 300", 88.0);
    
    if (Gradebook.isEmpty() && Gradebook.size() == 0) {
        
      Gradebook.addStudent(new StudentRecord("Annabelle", "hjeong58@wisc.edu", 90.0));
        
      if (!Gradebook.isEmpty() && Gradebook.size() == 1) {
            return true;
        }
    }
    return false;

  }

  /**
   * It is a test that test lookup method of Gradebook to check whether it correctly
   * finds or fails to find students given their email addresses.
   * @return if all lookup test passes or else return false 
   */
  public static boolean lookupTester() {
    Gradebook Gradebook = new Gradebook("Mechanical", 97.0);

    //This insert records which ensures that they can occupy both left and right children
    Gradebook.addStudent(new StudentRecord("Zibal", "zkim78@wisc.edu", 40.0));  
    Gradebook.addStudent(new StudentRecord("Annabelle", "hjeong58@wisc.edu", 94.0));  
    Gradebook.addStudent(new StudentRecord("Nugaebee", "nsibal20@wisc.edu", 44.0)); 

    //This checks the lookup for a email that should be on the left
    StudentRecord leftStudent = Gradebook.lookup("hjeong58@wisc.edu");
    if (leftStudent == null || !leftStudent.name.equals("Annabelle")) {
        return false;  // This return false if the left is node is null 
    }

    // This lookup for a email that should be on the right
    StudentRecord rightStudent = Gradebook.lookup("nsibal20@wisc.edu");
    if (rightStudent == null || !rightStudent.name.equals("Nugaebee")) {
        return false;  // Test should fail if Nora is not found or incorrectly retrieved
    }

    // This shows that non-existent email will  return null
    if (Gradebook.lookup("gkoim@wisc.edu") != null) {
        return false;
    }

    //Return true if no error 
    return true;
    
  }
  
  
  /**
   * This test verify that toString method of object Gradebook will return correct 
   * string presentation of its student in order. 
   * @return true if the actual and expected string math or else return false 
   */
  public static boolean toStringTester() {
    
    Gradebook testGradebook = new Gradebook("Compsci 300", 86.0);

    // Add three studentRecords 
    testGradebook.addStudent(new StudentRecord("Yennifer", "ykim34@wisc.edu", 72.0));
    testGradebook.addStudent(new StudentRecord("Boo", "bkim12@wisc.edu", 96.0));
    testGradebook.addStudent(new StudentRecord("Cibal", "cpark27@wisc.edu", 57.0));

    // It is the expected output of the strings in order. 
    String expected = "Boo (bkim12@wisc.edu): 96.0\nCibal (cpark27@wisc.edu): "
        + "57.0\nYennifer (ykim34@wisc.edu): 72.0\n";
    String actual = testGradebook.toString();

    
    if (!actual.equals(expected)) {
        return false; // It returns false if expected does not match the actual string
    }

    // If everything is correct, return true at the end
    return true;
    
    
  }
  
  /**
   * It checks the prettyString method if it correctly represents the student's string
   * @return true if actual string matches the expected string 
   * in pretty format or else return false 
   */
  public static boolean prettyStringTester() {
    
    //Create new gradebook
    Gradebook gradebook = new Gradebook("Math 340", 92.0);
    
    if (!gradebook.prettyString().equals("")) {
        return false;
    }

    //Add new student record to the gradebook 
    gradebook.addStudent(new StudentRecord("Eric", "eric54@wisc.edu", 95.0));
    gradebook.addStudent(new StudentRecord("Joy", "jhong99@email.com", 90.0));
    gradebook.addStudent(new StudentRecord("Ddong", "Ddong89@email.com", 75.0));
   
    String pretty = gradebook.prettyString();
    String expected = "    Joy\n" +
            "Eric\n" +
            "    Ddong\n";
    return pretty.equals(expected); //return true if the prettyString equals to the expected result 



}

  /**
   * This test, tests the getMin() if it correctly identify the minimum email adress among the 
   * gradebook and return null if the gradebook is empty. 
   * @return true if all the getMin test returns true or else return false 
   */
  public static boolean getMinTester() {
    
    Gradebook Gradebook = new Gradebook("Music 113", 98.0);

   
    if (Gradebook.getMin() != null) {
        return false; // Return false  if the min is null or doesn't exist at all 
    }

    // Adding students to the gradebook
    Gradebook.addStudent(new StudentRecord("Yennifer", "ykim45@wisc.edu", 34.0));
    Gradebook.addStudent(new StudentRecord("Annabelle", "ajeong58@wisc.edu", 99.0));
    Gradebook.addStudent(new StudentRecord("Cibal", "ckim44@wisc.edu", 44.0));

   
    if (!Gradebook.getMin().email.equals("ajeong58@wisc.edu")) {
        return false; 
    }

    return true; // All tests passed, return true
    
  }
  
  
  /**
   * It is a test to test if the successor method is able to identify the next student and return 
   * null if there is no any successor. 
   * @return true if all of the testers pass or else return false 
   */
  public static boolean successorTester() {
    
    Gradebook Gradebook = new Gradebook("Chem 109", 77.0);

    
    if (Gradebook.successor(new StudentRecord("Annabelle", "ajeong58@wisc.edu", 95.0)) != null) {
        return false; // return false if a successor that doesn't exit is found in an gradebook
    }

    // add student record in the gradebook 
    Gradebook.addStudent(new StudentRecord("Yennifer", "ykim68@wisc.edu", 66.0));
    Gradebook.addStudent(new StudentRecord("Annabelle", "ajeong58@wisc.edu", 95.0));
    Gradebook.addStudent(new StudentRecord("Eve", "eloz20@wisc.edu", 38.0));

  
    StudentRecord successor = Gradebook.successor
        (new StudentRecord("Eve", "eloz20@wisc.edu", 38.0));
    if (successor == null || !successor.email.equals("ykim68@wisc.edu")) {
        return false; // return false if Eve's successor is the Yennifer 
    }

    return true;
    
  }
  
  /**
   * This tester verifies if the remove method will be able to correctly remove a specific element 
   * and tree reconstructing. 
   * @return true if all remove test return true or else return false 
   */
  public static boolean removeStudentTester() {
    
    //First, create new gradebook
    Gradebook Gradebook = new Gradebook("Eng 200", 76.0);
    
    Gradebook.addStudent(new StudentRecord("Dike", "dkim58@wisc.edu", 98.0));
    Gradebook.removeStudent("dkim58@wisc.edu");
    
    if (!(Gradebook.isEmpty()) ) {
     return false; //return false if the gradebook is not empty after removing one student record 
  }
  
  
  Gradebook firstGradebook = new Gradebook("Eng 200", 76.0);
  
    firstGradebook.addStudent(new StudentRecord("Dike", "dkim58@wisc.edu", 98.0));
    firstGradebook.addStudent(new StudentRecord("Bruno", "bruno38@wisc.edu", 60.0));
    firstGradebook.addStudent(new StudentRecord("Eve", "eoid43@wisc.edu", 75.0));
    firstGradebook.addStudent(new StudentRecord("Annabelle", "ajeong58@wisc.edu", 99.0));
    firstGradebook.addStudent(new StudentRecord("Cve", "cyun46@wisc.edu", 28.0));
    firstGradebook.removeStudent("bruno38@wisc.edu");//remove one element with new set of Gradebook
    
    String firstExpected = "    Eve\n"
        + "Dike\n"
        + "    Cve\n"
        + "        Annabelle\n";
    
    String firstActual = firstGradebook.prettyString();
    
    if(!(firstExpected.equals(firstActual))) {
      
      return false; //return false if the expected gradebook and actual are different 
    
    }
    
    
    Gradebook secondGradebook = new Gradebook("Eng 200", 76.0);
    
    secondGradebook.addStudent(new StudentRecord("Dike", "dkim58@wisc.edu", 98.0));
    secondGradebook.addStudent(new StudentRecord("Bruno", "bruno38@wisc.edu", 60.0));
    secondGradebook.addStudent(new StudentRecord("Eve", "eoid43@wisc.edu", 75.0));
    secondGradebook.addStudent(new StudentRecord("Annabelle", "ajeong58@wisc.edu", 99.0));
    secondGradebook.addStudent(new StudentRecord("Cve", "cyun46@wisc.edu", 28.0));
    secondGradebook.removeStudent("cyun46@wisc.edu"); //remove different element from the gradebook 
    
    String secondExpected = "    Eve\n"
        + "Dike\n"
        + "    Bruno\n"
        + "        Annabelle\n";
    
    String secondActual = secondGradebook.prettyString();
    
    if(!secondExpected.equals(secondActual)) {
      return false; //return false if the expected and actual are different 
    }
    
    return true;

  }
  
  
  /**
   * It is a tester that tests the functionality of the iterator method and also if it is empty 
   * and also properly iterates the student record. 
   * @return
   */
  public static boolean iteratorTester() {
    
    
    Gradebook Gradebook = new Gradebook("Dance 101", 98.0);

    
    Iterator<StudentRecord> Iterator = Gradebook.iterator();
    if (Iterator.hasNext()) {
        return false; // return false if there is next element in empty gradebook
    }

    // Add new student records to the gradebook 
    Gradebook.addStudent(new StudentRecord("Annabelle", "ajeong58@wisc.edu", 99.0));
    Gradebook.addStudent(new StudentRecord("Brown", "bueio53@wisc.edu", 26.0));
    Gradebook.addStudent(new StudentRecord("Cibal", "cefae30@wisc.edu", 40.0));
    Gradebook.addStudent(new StudentRecord("Down", "djikk34@wisc.edu", 86.0));
    

    
    Iterator = Gradebook.iterator();
    if (!Iterator.next().email.equals("ajeong58@wisc.edu") ||!Iterator.hasNext() ) {
        return false; //return if the iterator doesn't start off with the correct element 
    }
    

    return true; 
  
  }
  
  
  /**
   * This also checks if the passingIterator method correctly iterates students grade with passing 
   * grades. It also makes sures the order. 
   * @return true if all the passingIterator tests return true or else return false
   */
  public static boolean passingIteratorTester() {
    
    
    Gradebook Gradebook = new Gradebook("Compsci 300", 75.0);
    
    Iterator<StudentRecord> Iterator = Gradebook.iterator(); // Uses the normal iterator for an empty tree test
    if (Iterator.hasNext()) {
        return false; // return false if there is element to next 
    }

    // Add new student record to the gradebook 
    Gradebook.addStudent(new StudentRecord("Fukuto", "fkamisa56@wisc.edu", 20.0)); 
    Gradebook.addStudent(new StudentRecord("Hayoon", "hkim78@wisc.edu", 89.0)); 
    Gradebook.addStudent(new StudentRecord("Isac", "ihfed23@wisc.edu", 85.0)); 
    Gradebook.addStudent(new StudentRecord("June", "jgfd12@wisc.edu", 82.0)); 
    Gradebook.addStudent(new StudentRecord("Katie", "kdgf78@wisc.edu", 20.0)); 

    // Start the passingGradeIterator to check
    Gradebook.enablePassingGradeIterator();
    Iterator = Gradebook.iterator();

    
    if (!Iterator.hasNext()) {
        return false; 
    }

    StudentRecord first = Iterator.next();
    StudentRecord second = Iterator.hasNext() ? Iterator.next() : null;

    // check if the order is correct 
    if (first == null || !first.email.equals("hkim78@wisc.edu") || second == null 
        || !second.email.equals("ihfed23@wisc.edu")) {
        return false; // it returns false if first two aren't Hayoon and Isac
    }

    return true;
    
  }
  
  /**
   * The main method of the tester which returns and prints all the tests and also run the tester. 
   * @param args
   */
  private static void main (String[] args) {
    System.out.println("constructorTester: " + constructorTester());
    System.out.println("isEmptySizeAddTester: " + isEmptySizeAddTester());
    System.out.println("lookupTester: " + lookupTester());
    System.out.println("toStringTester: " + toStringTester());
    System.out.println("prettyStringTester: " + prettyStringTester());
    System.out.println("getMinTester: " + getMinTester());
    System.out.println("successorTester: " + successorTester());
    System.out.println("removeStudentTester: " + removeStudentTester());
    System.out.println("iteratorTester: " + iteratorTester());
    System.out.println("passingIteratorTester: " + passingIteratorTester());
    
  }
  
}
