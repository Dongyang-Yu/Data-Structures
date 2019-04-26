package p5_package;

public class SimpleStackClass extends java.lang.Object
   {
    /**
     * constant for default array capacity
     */
    private final int DEFAULT_CAPACITY = 10;
    
    /**
     * constant -999999 for access failure messaging
     */
    public static final int FAILED_ACCESS = -999999;
   
    /**
     * current capacity of stack class
     */
    private int capacity;
   
    /**
     * current size of stack class
     */
    private int size;
    
    /**
     * stack top index
     */
    private int stackTopIndex;
    
    /**
     * Integer array stores data
     */
    private int[] stackArray;
    
    /**
     * Default constructor
     */
    public SimpleStackClass()
       {
        capacity = DEFAULT_CAPACITY;
        size = 0;
        stackTopIndex = -1;
        stackArray = new int[ capacity ];
       }
    
    /**
     * Initialization constructor
     * @param capacitySetting - initial capacity of stackData class
     */
    public SimpleStackClass( int capacitySetting)
       {
        capacity = capacitySetting;
        size = 0;
        stackTopIndex = -1;
        stackArray = new int[ capacity ];
       }
    
    /**
     * Copy constructor
     * 
     * @param copied - SimpleStackClass object to be copied
     */
    public SimpleStackClass( SimpleStackClass copied )
       {
        int index;
        capacity = copied.capacity;
        size = copied.size;
        stackTopIndex = copied.stackTopIndex;
        stackArray = new int[capacity];
        
        for( index = 0; index < size; index++ )
           {
            stackArray[ index ] = copied.stackArray[ index ];
           }
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
            int index;
            capacity *= 2;
            int[] tempArray = new int[ capacity ];
            for( index = 0; index < size; index++ )
               {
                tempArray[ index ] = stackArray[ index ];
               }
            stackArray = tempArray;

            return true;
           }
        return false;
       }
    
    /**
     * Reports stack empty status
     * <p>
     * Note: Does not use if/else
     * 
     * @return Boolean evidence of empty list
     */
    public boolean isEmpty()
       {
//        return ( stackTopIndex == -1 );
        return size == 0;
       }

    
    /**
     * Checks for resize, then pushes value onto stack
     * <p>
     * Note: end of array is always the top of the stack;
     * index is incremented and then value is appended to array
     * 
     * @param newValue - Value to be pushed onto stack
     */
    public void push(int newValue)
       {
        checkForReSize();
        size++;
        stackTopIndex++;
        stackArray[ stackTopIndex ] = newValue;
       
       }
    
    /**
     * Removes and returns data from top of stack
     * 
     * @return value if successful, FAILED_ACCESS if not
     */
    public int pop()
       {
        if( isEmpty() )
           {
            return FAILED_ACCESS;
           }
        
        stackTopIndex--;
        return stackArray[ stackTopIndex + 1];
        
       }
    
    /**
     * Clears stack by setting size to zero and top index to negative one
     */
    public void clear()
       {
        size = 0;
        stackTopIndex = -1;
       }
    
    /**
     * provides peek at top of stack
     * 
     * @return value if successful, FAILED_ACCESS if not
     */
    public int peekTop()
       {
        if( isEmpty() )
           {
            return FAILED_ACCESS;
           }
        return stackArray[ stackTopIndex ];
       }
  
   }
