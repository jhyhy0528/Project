import java.util.NoSuchElementException;
import java.util.Arrays;

public class TaskQueue {

  private Task[] heapData;
  
  private int size;
  
  private CompareCriteria priorityCriteria;
  
  public TaskQueue(int capacity,
      CompareCriteria priorityCriteria) {
    
    if ( capacity <=0 ) {
      throw new IllegalArgumentException();
    }
   
    this.heapData = new Task[capacity];
    this.size = 0;
    this.priorityCriteria = priorityCriteria;
  }
  
  public CompareCriteria getPriorityCriteria() {
    return priorityCriteria; 
  }
  
  public boolean isEmpty() {
    return size == 0; 
    
  }
  
  public int size() {
    return size; 
  }
  
  public Task peekBest() {
    if (size == 0) {
      throw new NoSuchElementException();
      
    }
    return heapData[0];
  }
  
  public void enqueue(Task newTask) {
    if(newTask.isCompleted()) {
      throw new IllegalArgumentException();
    }
    
    if(size >= heapData.length) {
      throw new IllegalStateException();
    }
    heapData[size] = newTask; 
    percolateUp(size);
    size++;
    
  }
  
  protected void percolateUp(int index) {
    Task hold = heapData[index];
    while (index > 0) {
      int parent = (index - 1) / 2;
      if (heapData[index].compareTo(heapData[parent], priorityCriteria) > 0) {
        heapData[index] = heapData[parent];
        index = parent;
      } else {
        break;
      }
    }
    heapData[index] = hold;
  }

 
  
  public Task dequeue() {
    if(size == 0) {
      throw new NoSuchElementException();
    }
    
    Task prior = heapData [0];
    heapData[0] = heapData[size - 1];
    heapData[size - 1] = null;
    size--;
    
   if (size > 0) {
     percolateDown(0);
   }
   
   return prior; 
   
  }
  
  protected void percolateDown(int index) {
    int child;
    int right;
    Task hold = heapData[index];
    while ((child = 2 * index + 1) < size) {
      right = child + 1;
      int largest = child;

      if (right < size && heapData[right].compareTo(heapData[child], priorityCriteria) > 0) {
        largest = right;
      }

      if (heapData[largest].compareTo(hold, priorityCriteria) > 0) {
        heapData[index] = heapData[largest];
        index = largest;
      } else {
        break;
      }
    }
    heapData[index] = hold;
  }

  
  public void reprioritize(CompareCriteria priorityCriteria) {
    this.priorityCriteria = priorityCriteria;
    for (int i = size / 2 - 1; i >= 0; i--) {
      percolateDown(i);
    }

    
  }
  
  public Task[] getHeapData() {
    return Arrays.copyOf(heapData, size);
    
  }
  
}
