import java.util.NoSuchElementException;
import java.util.Arrays;

public class TaskQueueTester {
  
  public static boolean testCompareToTime() {
    
      
    Task longTime = new Task("Annabelle Task", "description of Annabelle", 30, PriorityLevel.HIGH);
    Task shortTime = new Task("Belle Task", "description of Belle", 10, PriorityLevel.LOW);
   
    int expected1 = longTime.compareTo(shortTime, CompareCriteria.TIME);
     
  
    if (expected1 <= 0) {
      
      return false;
    }
    
    
    int expected2 = shortTime.compareTo(longTime, CompareCriteria.TIME);
   
    
    if (expected2 >= 0) {
      
      return false;
    }
  
  
  
    Task annabelleTask = new Task("Annabelle Task", "description of Annabelle",
        30, PriorityLevel.HIGH);
    Task belleTask = new Task("Belle Task", "description of Belle", 30, PriorityLevel.LOW);
    
    int result = annabelleTask.compareTo(belleTask, CompareCriteria.TIME);

     
    if (result != 0) {
     
      return false;
    }
  
    if (result < 0|| result > 0 ) {
     
      return false;
   
    }
       
    return true; 

  }
  
  
  
  public static boolean testCompareToLevel() {
     
      Task annabelleTask = new Task("Annabelle Task", "description of Ananbelle", 55, PriorityLevel.HIGH);
      Task belleTask = new Task("Belle Task", "description of Belle", 55, PriorityLevel.URGENT);
      
      
      int expected = annabelleTask.compareTo(belleTask, CompareCriteria.LEVEL);
      
      if (expected >= 0) {
       
        return false;
      
      }

     
      int expected1 = belleTask.compareTo(annabelleTask, CompareCriteria.LEVEL);
      
      if (expected1 <= 0) {
        
        return false;
      
      }
    
    
   
      Task annabelleTask1 = new Task("Annabelle Task", "description of Ananbelle", 45, PriorityLevel.LOW);
      Task belleTask1 = new Task("Belle Task", "description of Belle", 30, PriorityLevel.LOW);
      
      int result = annabelleTask1.compareTo(belleTask1, CompareCriteria.LEVEL);
      
      if (result != 0) {
        
        return false;
      
      }   
      
      int switchedResult = belleTask1.compareTo(annabelleTask1, CompareCriteria.LEVEL);
      
      if (switchedResult != 0) {
        
        return false;
      
      }
    
    return true; 
 
  }
  
  
  
  
  
  public static boolean testCompareToTitle() {
    
      Task annabelleTask = new Task("Annabelle Task", "description of Ananbelle", 56, PriorityLevel.HIGH);
      Task belleTask = new Task("Belle Task", "description of Belle", 56, PriorityLevel.LOW);
      
      int result = annabelleTask.compareTo(belleTask, CompareCriteria.TITLE);

      
      if (result <= 0) {
        
        return false;
      
      }
    
    
    
      Task belleTask1 = new Task("Belle Task", "description of Belle", 56, PriorityLevel.HIGH);
      Task catTask = new Task("Cat Task", "description of Cat", 56, PriorityLevel.LOW);
      
      int result1 = catTask.compareTo(belleTask1, CompareCriteria.TITLE);

      
      if (result1 >= 0) {
        
        return false;
      
      }
    
    
    
      Task lowTask = new Task("annabelle", "description of annabelle", 30, PriorityLevel.URGENT);
      Task upTask = new Task("Annabelle", "description of Annabelle", 20, PriorityLevel.HIGH);
      int result2 = lowTask.compareTo(upTask, CompareCriteria.TITLE);
      
    
      if (result2 >= 0) {
        
        return false;
      }
      
     
      int result3 = upTask.compareTo(lowTask, CompareCriteria.TITLE);
      
      if (result3 <= 0) {
       
        return false;
      }
      
      Task lowtask1 = new Task("annabelle", "description of annabelle", 30, PriorityLevel.LOW);
      int result4 = lowTask.compareTo(lowtask1, CompareCriteria.TITLE);
      
      if (result4!= 0) {
        
        return false;
      
      }
    
      { 
        
        Task annabelleTask1 = new Task("A", "A description", 55, PriorityLevel.HIGH);
        Task annabelleTask2 = new Task("A", "A description", 55, PriorityLevel.LOW);
        
        int compare = annabelleTask1.compareTo(annabelleTask2, CompareCriteria.TITLE);

        if (compare != 0) {
          return false;
        }
        
        int compare1 = annabelleTask2.compareTo(annabelleTask1, CompareCriteria.TITLE);
        if (compare1 != 0) {
          return false;
        }
      
      }
    
      return true; 
  }
  
  
  
  
  public static boolean testEnqueue() {
   
    {
      
      try { 
        
        TaskQueue queue = new TaskQueue(6, CompareCriteria.TIME);
        
        
        Task aTask = new Task("A", "description of A", 26, PriorityLevel.URGENT);
        
        queue.enqueue(aTask);
        
        if (!queue.peekBest().equals(aTask)) {
          return false;
          
        }
            
        
        Task bTask = new Task("B", "description of B", 31, PriorityLevel.LOW);
        queue.enqueue(bTask);
       
        if (!queue.peekBest().equals(bTask)) {
          
          return false;
          
        }
        
        Task cTask = new Task("C", "description of C", 39, PriorityLevel.HIGH);
        
        queue.enqueue(cTask);
       
        if (!queue.peekBest().equals(cTask)) {
          
          return false;
          
        }
             
       
        Task[] expectedOrder = {cTask, aTask, bTask, null, null, null};
        
        if (!Arrays.equals(expectedOrder, queue.getHeapData())) {
          
          return false;
          
        }
        
       
        Task completeTask = new Task("Complete", "descript", 28, PriorityLevel.OPTIONAL);
        completeTask.markCompleted();
       
        try {
          
          queue.enqueue(completeTask);
          
          return false; 
          
        }  catch (IllegalArgumentException e) {
          
         
          if (e.getMessage().isEmpty() || e.getMessage() == null) 
         
          {return false;}
        
        }
        
        
        Task dTask = new Task("D", "description of D", 39, PriorityLevel.LOW);
        
        queue.enqueue(dTask);
        
        
        Task[] order = {cTask, dTask, bTask, aTask, null, null};
        
        if (!Arrays.equals(order, queue.getHeapData())) {
          
          return false;
          
        }
        
       
        Task taskE = new Task("E", "description of E", 31, PriorityLevel.HIGH);
        
        queue.enqueue(taskE);
        
       
        Task[] order2 = {cTask, dTask, bTask, aTask, taskE, null};
       
        if (!Arrays.equals(order2, queue.getHeapData())) {
          
          return false;
          
        }
        
        
        Task taskF = new Task("F", "description of F", 4, PriorityLevel.LOW);
       
        queue.enqueue(taskF);
        
        Task[] order3 = {cTask, dTask, bTask, aTask, taskE, taskF};
       
        if (!Arrays.equals(order3, queue.getHeapData())) {
          
          return false;
          
        }
        
        
        Task fulledTask = new Task("Fulltask", "descript", 28, PriorityLevel.URGENT);
        try {
         
          queue.enqueue(fulledTask);
          
          return false; 
          
        } catch (IllegalStateException e) {
         
          if (e.getMessage() == null || e.getMessage().isEmpty()) {
            
            return false;
            
          }
        
        }
        
      
      } catch (IllegalArgumentException e) {
        
        if (e.getMessage() == null || e.getMessage().isEmpty()) {
          
          return false;
          
        }
      
      } catch (IllegalStateException e) {
        
        if (e.getMessage() == null || e.getMessage().isEmpty()) {
          
          return false;
          
        }
      
      } catch (Exception e) {
        
        e.printStackTrace();
        
        return false;
      
      }
    
    }
    
   
    {
     
      try { 
        
        TaskQueue queue = new TaskQueue(6, CompareCriteria.TIME);

         
        Task aTask = new Task("A", "description of A", 25, PriorityLevel.URGENT);
        Task bTask = new Task("B", "description of B", 15, PriorityLevel.LOW);
        Task cTask = new Task("C", "description of C", 28, PriorityLevel.HIGH);
        Task dTask = new Task("D", "description of D", 28, PriorityLevel.MEDIUM);
        Task eTask = new Task("E", "description of E", 4, PriorityLevel.LOW);
        Task fTask = new Task("F", "description of F", 36, PriorityLevel.URGENT);

       
        queue.enqueue(aTask);
       
        if (!queue.peekBest().equals(aTask)) {
          return false;
          
        }

        queue.enqueue(bTask);
        queue.enqueue(cTask);
        
        if (!queue.peekBest().equals(cTask)) 
       
        {
          return false;
          }
            
        
        Task[] expected = {cTask, bTask, aTask, null, null, null};
        if (!Arrays.equals(expected, queue.getHeapData())) 
        {
          return false;
          
        }
        
        queue.enqueue(dTask);
        queue.enqueue(eTask);
        
        if (!queue.peekBest().equals(cTask)) {
          return false;
          }
        
        Task[] order1 = {cTask, dTask, aTask, bTask, eTask, null};
        if (!Arrays.equals(order1, queue.getHeapData())) {
          return false;
          }
             
        
        queue.enqueue(fTask);
        if (!queue.peekBest().equals(fTask)) {
          return false;
          } 
        
        Task[] order2 = {fTask, dTask,cTask, bTask, eTask, aTask};
        if (!Arrays.equals(order2, queue.getHeapData())) {
          return false;
          }
        
        
        Task completeTask = new Task("Completed", "description", 10, PriorityLevel.OPTIONAL);
        completeTask.markCompleted();
        
        try {
         
          queue.enqueue(completeTask);
          
          return false; 
        
        } catch (IllegalArgumentException e) {
          
          if (e.getMessage() == null || e.getMessage().isEmpty()) {
           
            return false;
          }
        }

       
        try {
          Task gTask = new Task("G", "description of G", 55, PriorityLevel.HIGH);
          queue.enqueue(gTask);
          
          return false; 
          
        } catch (IllegalStateException e) {
          
          if (e.getMessage() == null || e.getMessage().isEmpty()) {
            return false;
            }
        }
      } 
      catch (Exception e) {
        
        e.printStackTrace();
        
        return false;
      
      }
    }
    

    
    
    
    return true; 
  }
        
  
  
  public static boolean testDequeue() {
    
    
    
    
    TaskQueue queue = new TaskQueue(5, CompareCriteria.TITLE);
    
    if(!queue.isEmpty()){
      return false;
    }

    
    try {
      
      queue.dequeue();
      
      return false;
    
    } catch (NoSuchElementException e) {
     
    }
    
    Task bTask = new Task("Belle", "description of B", 5, PriorityLevel.OPTIONAL);
    queue.enqueue(bTask);
    Task bTask1 = new Task("belle", "description of b", 2, PriorityLevel.HIGH);
    queue.enqueue(bTask1);
    Task aTask = new Task("Annabelle", "description of A", 5, PriorityLevel.OPTIONAL);
    queue.enqueue(aTask);
    Task aTask1 = new Task("annabelle", "description of a", 5, PriorityLevel.OPTIONAL);
    queue.enqueue(aTask1);

    Task[] heap1 = queue.getHeapData();
    if (queue.size() != 4) {
      return false;
    }
    if (!heap1[0].equals(aTask) || !heap1[1].equals(aTask1) 
        || !heap1[2].equals(bTask) ||
        !heap1[3].equals(bTask1)) {
      return false;
    }

    queue.dequeue();
    Task[] heap2 = queue.getHeapData();
    if (queue.size() != 3) {
      return false;
    }
    if (!heap2[0].equals(bTask) || !heap2[1].equals(aTask1) ||
        !heap2[2].equals(bTask1)) {
      return false;
    }

    queue.dequeue();
    Task[] heap3 = queue.getHeapData();
    if (queue.size() != 2) {
      return false;
    }
    if (!heap3[0].equals(aTask1) || !heap3[1].equals(bTask1)) {
      return false;
    }

    queue.dequeue();
    Task[] heap4 = queue.getHeapData();
    if (queue.size() != 1) {
      return false;
    }
    if (!heap4[0].equals(bTask1)) {
      return false;
    }

    queue.dequeue();
    if (!queue.isEmpty()) {
      return false;
    }

    
    
    
    TaskQueue queueTask1 = new TaskQueue(5, CompareCriteria.LEVEL);
    
    if(!queueTask1.isEmpty()){
      
      return false;
    }

    try {
      
      queueTask1.dequeue();
      
      return false;
    } catch (NoSuchElementException e) {
      
    }

    Task taskA = new Task("Annabelle", "description of A", 3, PriorityLevel.HIGH);
    queueTask1.enqueue(taskA);
    Task taksB = new Task("Belle", "description of B", 15, PriorityLevel.OPTIONAL);
    queueTask1.enqueue(taksB);
    Task taskC = new Task("annabelle", "description of a", 30, PriorityLevel.URGENT);
    queueTask1.enqueue(taskC);
    Task taskD = new Task("belle", "description of b", 3, PriorityLevel.MEDIUM);
    queueTask1.enqueue(taskD);

    Task[] heapA = queueTask1.getHeapData();
    if (queueTask1.size() != 4) {
      return false;
    }
    if (!heapA[0].equals(taskC) || !heapA[1].equals(taskD) || !heapA[2].equals(taskA) ||
        !heapA[3].equals(taksB)) {
      return false;
    }

    queueTask1.dequeue();
    Task[] heapB = queueTask1.getHeapData();
    if (queueTask1.size() != 3) {
      return false;
    }
    if (!heapB[0].equals(taskA) || !heapB[1].equals(taskD) ||
        !heapB[2].equals(taksB)) {
      return false;
    }

    queueTask1.dequeue();
    Task[] heapC = queueTask1.getHeapData();
    if (queueTask1.size() != 2) {
      return false;
    }
    if (!heapC[0].equals(taskD) || !heapC[1].equals(taksB)) {
      return false;
    }

    queueTask1.dequeue();
    Task[] heapD = queueTask1.getHeapData();
    
    if (queueTask1.size() != 1) {
      
      return false;
    
    }
   
    if (!heapD[0].equals(taksB)) {
      return false;
    
    }

    queueTask1.dequeue();
    if (!queueTask1.isEmpty()) {
      return false;
    
    }

   
    
    TaskQueue queueTask2 = new TaskQueue(5, CompareCriteria.TIME);
    
    if(!queueTask2.isEmpty()){
     
      return false;
    }

   
    try {
      
      queueTask2.dequeue();
      
      return false;
    
    } catch (NoSuchElementException e) {
      
    }

    Task task3 = new Task("Annabelle", "description of A", 20, PriorityLevel.HIGH);
    queueTask2.enqueue(task3);
    Task task1 = new Task("Belle", "description of B", 18, PriorityLevel.OPTIONAL);
    queueTask2.enqueue(task1);
    Task task4 = new Task("annabelle", "description of a", 50, PriorityLevel.URGENT);
    queueTask2.enqueue(task4);
    Task task5 = new Task("belle", "description of b", 88, PriorityLevel.MEDIUM);
    queueTask2.enqueue(task5);

   
    Task[] heap1a = queueTask2.getHeapData();
    if (queueTask2.size() != 4) {
      return false;
    }
    if (!heap1a[0].equals(task5) || !heap1a[1].equals(task4) || !heap1a[2].equals(task3) ||
        !heap1a[3].equals(task1)) {
      return false;
    }

    queueTask2.dequeue();
   
    Task[] heapDataT1 = queueTask2.getHeapData();
    
    if (queueTask2.size() != 3) {
      
      return false;
    }
   
    if (!heapDataT1[0].equals(task4) || !heapDataT1[1].equals(task1) ||
        !heapDataT1[2].equals(task3)) {
      
      return false;
    }

    queueTask2.dequeue();
    
    Task[] heap2a = queueTask2.getHeapData();
   
    if (queueTask2.size() != 2) {
      return false;
  
    }
    
    if (!heap2a[0].equals(task3) || !heap2a[1].equals(task1)) {
     
      return false;
    }

    queueTask2.dequeue();
    
    Task[] heap3a = queueTask2.getHeapData();
    
    if (queueTask2.size() != 1) {
      return false;
   
    }
    
    if (!heap3a[0].equals(task1)) {
      return false;
    }

    queueTask2.dequeue();
    if (!queueTask2.isEmpty()) {
      return false;
    }
    
    
    return true;
  }
  
  
  
  public static boolean testPeek() {
   
    TaskQueue queue = new TaskQueue(5, CompareCriteria.LEVEL);
    
    
    try {
       
        queue.enqueue(new Task("Annabelle", "Description 1", 10, PriorityLevel.LOW));
        queue.enqueue(new Task("Belle", "Description 2", 20, PriorityLevel.MEDIUM));
        queue.enqueue(new Task("Cat", "Description 3", 30, PriorityLevel.HIGH));
        queue.enqueue(new Task("Dog", "Description 4", 40, PriorityLevel.URGENT));

        
        Task highestPriorityTask = queue.peekBest();
        if (!highestPriorityTask.getTitle().equals("Dog")) {
            return false;
        }
        
        
        if (queue.size() != 4) {
            
          return false;
        }
    } catch (NoSuchElementException e) {
   
        return false;
    }

    
    try {
        
        TaskQueue noQueue = new TaskQueue(5, CompareCriteria.LEVEL);
        noQueue.peekBest();
        
        return false;
   
    } catch (NoSuchElementException e) {
        
        return true;
    }
}

  
  
  
  
  public static boolean testReprioritize() {
    
    TaskQueue queue = new TaskQueue(5, CompareCriteria.TIME);
   
    Task taskA = new Task("Annabelle", "Description A", 65, PriorityLevel.LOW);
    Task taskB = new Task("Belle", "Description B", 35, PriorityLevel.MEDIUM);
    Task taskC = new Task("Cat", "Description C", 15, PriorityLevel.HIGH);

   
    queue.enqueue(taskA);
    queue.enqueue(taskB);
    queue.enqueue(taskC);

    queue.reprioritize(CompareCriteria.LEVEL);

    
    boolean expect = true;
    Task first = queue.dequeue(); 
    expect &= (first == taskC);

    Task second = queue.dequeue(); 
    expect &= (second == taskB);

    Task third = queue.dequeue(); 
    expect &= (third == taskA);

    return expect;
    
  }
    
    
  
  
  public static void main(String[] args) {
    System.out.println("testCompareToTime: " + testCompareToTime());
    System.out.println("testCompareToTitle: " + testCompareToTitle());
    System.out.println("testCompareToLevel: " + testCompareToLevel());
    System.out.println("testEnqueue: " + testEnqueue());
    System.out.println("testDequeue: " + testDequeue());
    System.out.println("testPeek: " + testPeek());
    System.out.println("testReprioritize: " + testReprioritize());
    
  }

}