package p9_package;

/**
 * Array-based generic min heap class used as a priority queue for generic data
 * 
 * @author Dongyang Yu
 */
public class GenericHeapClass <GenericData 
                                  extends java.lang.Comparable<GenericData>>
   {
    /**
     * Initial array capacity
     */
    public final int DEFAULT_ARRAY_CAPACITY = 10;
    
    /**
     * Array for heap
     */
    private Object[] heapArray;
   
    /**
     * Management data for array
     */
    private int arraySize;
    
    /**
     * Management data for array
     */
    private int arrayCapacity;
    
    /**
     * Display flag can be set to observe bubble up and trickle down operations
     */
    private boolean displayFlag;
    
    /**
     * Default constructor sets up array management conditions 
     * and default flag setting
     */
    public GenericHeapClass()
       {
        arrayCapacity = DEFAULT_ARRAY_CAPACITY;
        arraySize = 0;
        heapArray = new Object[ arrayCapacity ];
        displayFlag = false;
       }
    
    /**
     * Copy constructor copies array and array management conditions 
     * and default display flag setting
     * 
     * @param copied - GenericHeapClass object to be copied
     */
    public GenericHeapClass(GenericHeapClass<GenericData> copied)
       {
        int index;
        
        arrayCapacity = copied.arrayCapacity;
        arraySize = copied.arraySize;
        displayFlag = copied.displayFlag;
        
        heapArray = new Object[ arrayCapacity ];
       
        for( index = 0; index < arraySize; index++ )
           {
            heapArray[ index ] = copied.heapArray[ index ];
           }
        
       }
    
    /**
     * Accepts GenericData item and adds it to heap
     * <p>
     * Note: uses bubbleUpArrayHeap to resolve unbalanced heap after data addition
     * 
     * @param newItem - GenericData item to be added
     */
    public void addItem(GenericData newItem)
       {
        checkForResize();
        heapArray[ arraySize ] = newItem;
        
        if( displayFlag )
           {
            System.out.println("\nAdding new process: " + newItem.toString() );
           }
        
        bubbleUpArrayHeap( arraySize );
        
        arraySize++;
       }
    
    /**
     * Recursive operation to reset data in the correct order for the min
     * heap after 
     * 
     * @param currentIndex - index of current item being assessed,
     * and moved up as needed
     */
    @SuppressWarnings("unchecked")
   private void bubbleUpArrayHeap(int currentIndex)
       {
        int parentIndex = ( currentIndex - 1 ) / 2;
        GenericData currentData, prtData;
        
        if( currentIndex > 0 )
           {
            currentData = ( GenericData )heapArray[ currentIndex ];
            prtData = ( GenericData )heapArray[ parentIndex ];
            
            if( currentData.compareTo(prtData) < 0 )
               {
                if( displayFlag )
                   {
                    System.out.println( "   - Bubble up:");
                    System.out.println("     - Swapping parent: " 
                               + prtData.toString() 
                                 + "; with child: " + currentData.toString()); 
                   }
                heapArray[ currentIndex ] = prtData;
                heapArray[ parentIndex ] = currentData;
                bubbleUpArrayHeap(parentIndex);
               }
           }
       }
    
    /**
     * Automatic resize operation used prior to any new data addition in the heap
     * <p>
     * Tests for full heap array, and resizes to twice the current capacity as required
     */
    private void checkForResize()
       {
        int index;
        Object newArray[];
        if( arraySize == arrayCapacity )
           {
            arrayCapacity *= 2;
            newArray = new Object[ arrayCapacity  ];
            for(index = 0; index < arraySize; index++)
               {
                newArray[ index ] = heapArray[ index ];
               }
            heapArray = newArray;
           }
       }
    
    /**
     * Tests for empty heap
     * 
     * @return boolean result of test
     */
    public boolean isEmpty()
       {
        return arraySize == 0;
       }
    
    /**
     * Removes GenericData item from top of min heap, thus being the operation
     * with the lowest priority value
     * <p>
     * Uses trickleDownArratHeap to resolve unbalanced heap after removal
     * 
     * @return Item removed
     */
@SuppressWarnings("unchecked")
    public GenericData removeItem()
       {
        GenericData itemRemoval = null;
        
        if( !isEmpty() )
           {
            itemRemoval = (GenericData) heapArray[ 0 ];
            
            if( displayFlag )
               {
                System.out.println("\nRemoving process: " 
                               + itemRemoval.toString() );
               }
            
            heapArray[ 0 ] = heapArray[ arraySize - 1];
            arraySize--;
            
            trickleDownArrayHeap( 0 );
            
           }
        return itemRemoval;
       }
    
    /**
     * Utility method to set the display flag for displaying internal operations
     * of the heap bubble and trickle operations
     * 
     * @param setState - flag used to set the state to display, or not
     */
    public void setDisplayFlag(boolean setState)
       {
        displayFlag = setState;
       }
    
    
@SuppressWarnings("unchecked")
    public void showArray()
       {
        int index;
        for(index = 0; index < arraySize; index++)
           {
            System.out.print(( (GenericData) heapArray[index]).toString() + " - ");
           }
        System.out.println();
       }
    
    /**
     * Recursive operation to reset data in the correct order for the min
     * heap after data removal
     * 
     * @param currentIndex - index of current item being assessed,
     * and move down as required
     */
    @SuppressWarnings("unchecked")
    private void trickleDownArrayHeap(int currentIndex)
       {
        int leftIndex = currentIndex * 2 + 1;
        int rightIndex = currentIndex * 2 + 2;
        GenericData leftChild, rightChild;
        GenericData currentData = (GenericData)heapArray[ currentIndex ];
        
        if( rightIndex < arraySize )
           {
            leftChild = (GenericData)heapArray[ (currentIndex * 2) + 1 ];
            rightChild = (GenericData)heapArray[ (currentIndex * 2) + 2 ];
            if( rightChild.compareTo(leftChild) < 0
                  && rightChild.compareTo(currentData) < 0 )
               {
                if( displayFlag )
                   {
                    System.out.println("   - Trickle down:");
                    System.out.println("     - Swapping Parent: " 
                      + currentData.toString() 
                      + " with right child: " + rightChild.toString());
                   }
                heapArray[ rightIndex ] = currentData;
                
                heapArray[ currentIndex ] = rightChild;
                
                trickleDownArrayHeap(rightIndex);
               }
           }
        
        if( leftIndex < arraySize )
           {
            leftChild = (GenericData)heapArray[ leftIndex ];
            
            if( leftChild.compareTo(currentData) < 0 )
               {
                if( displayFlag )
                   {
                    System.out.println("   - Trickle down:");
                    System.out.println("     - Swapping Parent: " 
                     + currentData.toString() 
                     + " with left child: " + leftChild.toString());
                   }
                heapArray[ leftIndex ] = currentData;
                heapArray[ currentIndex ] = leftChild;
                
                trickleDownArrayHeap( leftIndex );
               }
           }
        
//            if( leftChild.compareTo(rightChild) < 0)
//               {
//                
//                if( leftChild.compareTo(currentData) < 0 )
//                   {
//                    if( displayFlag )
//                       {
//                        System.out.println("   - Trickle down:");
//                        System.out.println("     - Swapping Parent: " 
//                            + currentData.toString() 
//                            + " with left child: " + leftChild.toString());
//                       }
//                    heapArray[ (currentIndex * 2) + 1 ] = heapArray[ currentIndex ];
//                    heapArray[ currentIndex ] = leftChild;
//                    
//                    trickleDownArrayHeap( (currentIndex * 2) + 1 );
//                   }
//                
//               }
//            else // rightChild <= leftChild
//               {
//                if( rightChild.compareTo(currentData) < 0) 
//                   {
//                    if( displayFlag )
//                       {
//                       System.out.println("   - Trickle down:");
//                       System.out.println("     - Swapping Parent: " 
//                            + currentData.toString() 
//                            + " with right child: " + rightChild.toString());
//                       }
//                    heapArray[ ( currentIndex * 2 ) + 2 ] = heapArray[ currentIndex ];
//                    heapArray[ currentIndex ] = rightChild;
//                    
//                    trickleDownArrayHeap( (currentIndex * 2) + 2 );
//                    
//                   }
//               }
//           }
//        else if( ( currentIndex * 2 ) + 1 < arraySize )
//           {
//            leftChild = (GenericData)heapArray[ (currentIndex * 2) + 1 ];
//            currentData = (GenericData)heapArray[ (currentIndex * 2) ];
//            
//            if( leftChild.compareTo(currentData) < 0 )
//               {
//                if( displayFlag )
//                   {
//                    System.out.println("   - Trickle down:");
//                    System.out.println("     - Swapping Parent: " 
//                         + currentData.toString() 
//                           + " with left child: " + leftChild.toString());
//                   }
//                    heapArray[ (currentIndex * 2) + 1 ] = heapArray[ currentIndex ];
//                    heapArray[ currentIndex ] = leftChild;
//            
//                }
//           }
       }
   }
