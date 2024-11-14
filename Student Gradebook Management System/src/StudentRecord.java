

/**
 * This class represents a student’s record with their name, email and grade.
 * It guarantees that there is a unique email for each student and the
 * information provided is valid.
 * It also implements Comparable interface so that it can be sorted or compared based on emails.
 */
public class StudentRecord extends Object implements Comparable<StudentRecord> {
  
  public final String name;
  
  public final String email;
  
  private double grade;
  
  /**
   * It is constructor in this class with specified name, email and grade. 
   * @param name is the name of the student 
   * @param email is the email of the student 
   * @param grade is the grade of the student. 
   */
  public StudentRecord (String name, String email, double grade) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException();
    }
    
    if (email == null || email.isBlank()) {
      throw new IllegalArgumentException();
    }
    
    if (grade < 0.0 ||grade > 100.0) {
      throw new IllegalArgumentException();
    }
    
    this.name = name;
    this.email = email;
    this.grade = grade;
  }
  
  
  /**
   * It returns the student's current grade 
   * @return the grade of the student 
   */
  public double getGrade() {
    return this.grade;
    
  }
  
  /**
   * It updates this student's current grade
   * @param grade It is the new value of this student's grade
   */
  public void setGrade(double grade) {
    this.grade = grade;
    
  }
  
  /**
   * It returns a string representation of Student Record 
   * @return a string representation of this StudentRecord in "name (email): " grade from 
   */
  @Override
  public String toString() {
    return String.format("%s (%s): %.1f", name, email, grade);
    
  }
  
  /**
   * It compares this StudentRecord to other StudentRecord passed as input. 
   * As per the alphabetical order, in terms of lexicographical ordering, 
   * emails of students are compared.
   * @return negative if this email is less, zero if it is equal, and positive if it is greater.
   */
  public int compareTo(StudentRecord other) {
    return this.email.compareTo(other.email);
  }
  
  
  /**
   * It determines if this StudentRecord is equal to another object. 
   * The only criterion of equivalence we adhere to is the e-mail address provided.
   * 
   * @param o it is object to compare with student record. 
   * @return true if it o is same with student record or false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true; 
    }
    
    if (o == null) {
      return false; 
    }
    
    StudentRecord other = (StudentRecord) o; 
    return email != null && email.equals(other.email);
    
    
  }
  
}
