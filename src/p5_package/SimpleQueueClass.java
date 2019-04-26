package p5_package;

public class SimpleQueueClass
   {
    /**
     * constant for default capacity
     */
    private final int DEFAULT_CAPACITY = 10;
    
    /**
     * constant -999999 for access failure messaging
     */
    public static final int FAILED_ACCESS = -999999;
    
    /**
     * current capacity of queue class
     */
    private int capacity;
   
    /**
     * current size of queue class
     */
    private int size;
    
    /**
     * queue head index
     */
    private int headIndex;
   
    /**
     * queue tail index
     */
    private int tailIndex;
   
    /**
     * Integer array stores queue data
     */
    private int[] queueData;
   
    /**
     * Default constructor 
     */
    public SimpleQueueClass()
       {     
        capacity = DEFAULT_CAPACITY;
        size = 0;
        headIndex = 0;
        tailIndex = -1;
        queueData = new int [ capacity ];
       }
   
    /**
     * Initialization constructor
     * 
     * @param capacitySetting - initial capacity of queueData
     */
    public SimpleQueueClass( int capacitySetting )
       {
        capacity = capacitySetting;
        size = 0;
        headIndex = 0;
        tailIndex = -1;
        queueData = new int [ capacity ];
       }
       
    /**
     * Copy constructor
     * <p>
     * Note: queue is copied so that head index is at zero and tail index is at size - 1;
     * i.e., this resets the array to start at zero
     * 
     * @param copied - SimpleQueueClass object to be copied
     */
    public SimpleQueueClass( SimpleQueueClass copied )
       {
        int index;
        
        capacity = copied.capacity;
        size = copied.size;
        headIndex = copied.headIndex;
        
        queueData = new int[ capacity ];
        
        for( index = 0; index < size; index++, updateHeadIndex() ) 
           {
            queueData[ index ] = copied.queueData[ headIndex ];
           }
        
        headIndex = 0;
        
        tailIndex = size - 1;
       }
   
    /**
     * Checks for resize and resizes to twice the current capacity if needed
     * <p>
     * Note: Returns true if resize is necessary and is conducted;
     * returns false if no action is taken
     * 
     * @param tempArray - temporary array to be copied
     * 
     * @return success of operation
     */
    private boolean checkForReSize()
       {
        if( size == capacity )
           {
            int[] tempArray = new int[ capacity * 2 ];
            int index;
//            for( index = 0; index < size; index++ )
//               {
//                tempArray[ index ] = queueData[ index ]; //因为queue 的index 不一定在 [0] 处，可能pop过
//               }
            
            for( index = 0; index < size; updateHeadIndex(), index++ )
               {
                tempArray[ index ] = queueData[ headIndex ]; 
               }
            capacity *= 2;
            queueData = tempArray;
            return true;
           }
        return false;
       }
   
    /**
     * Reports queue empty state
     * 
     * @return Boolean evidence of empty list
     */
    public boolean isEmpty()
       {
        return size == 0;
       }
    
    /**
     * Checks for resize, then enqueues value
     * <p>
     * Note: Updates tail index, then appends value to array at tail index
     * 
     * @param newValue - Value to be enqueued
     */
    public void enqueue(int newValue)
       {
        checkForReSize();
        updateTailIndex();
        queueData[ tailIndex ] = newValue;
        size++;
          
       }
   
    /**
     * Removes and returns value from front of queue
     * <p>
     * Note: Acquires data from head of queue, then updates head index
     * 
     * @param value - retain the value of the headIndex of the queue.
     * 
     * @return Value if successful, FAILED_ACCESS if not
     */
    public int dequeue()
       {
        if( isEmpty() )
           {
            return FAILED_ACCESS;
           }
       
         int value = queueData[ headIndex ];
         updateHeadIndex();
         size--;
         return value;
       
       }
   
    /**
     * Provides peek at front of queue
     * 
     * @return Value if successful, FAILED_ACCESS if not
     */
    public int peekFront()
       {
        if( isEmpty() )
           {
            return FAILED_ACCESS;
           }
        return queueData[ headIndex ];
       
       }
   
    /**
     * Clears the queue by setting the size to zero, the tail index to -1 and the head index to zero
     */
    public void clear()
       {
        size = 0;
        headIndex = 0;
        tailIndex = -1;
       }
    
    
    /**
     * Updates queue head index to wrap around array as needed
     */
    private void updateHeadIndex()
       {
        headIndex = ( headIndex + 1 ) % capacity;
       }
   
    /**
     * Updates queue tail index to wrap around array as needed
     */
    private void updateTailIndex()
       {
        tailIndex = ( tailIndex + 1 ) % capacity;
       }
    
   
   }
