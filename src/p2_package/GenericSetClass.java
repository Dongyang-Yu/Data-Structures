package p2_package;

/**
 * Class for managing sets of GenericData that extend Comparable
 * 
 * @author MichaelL
 *
 */
public class GenericSetClass<GenericData extends Comparable<GenericData>>
   {
    /**
     * constant with default array capacity
     */
    public static final int DEFAULT_ARRAY_CAPACITY = 10;
   
    /**
     * constant used for base two exponential calculations
     */
    private static final int BASE_TWO = 2;
   
    /**
     * Object array for data
     */
    private Object[] genericSetArray;

    /**
     * GenericSetClass array for management of power sets
     * 
     */
    private GenericSetClass<GenericData>[] genericPwrSetArray;
   
    /**
     * capacity of array
     */
    private int arrayCapacity;
   
    /**
     * number of values in array
     */
    private int arraySize;
   
    /**
     * Default constructor
     * <p>
     * Initializes set array, sets power set array to null
     */
    public GenericSetClass()
       {
        genericSetArray = new Object[ DEFAULT_ARRAY_CAPACITY ];
       
        genericPwrSetArray = null;
       
        arrayCapacity = DEFAULT_ARRAY_CAPACITY;
       
        arraySize = 0;
       }

    /**
     * Initialization constructor
     * <p>
     * Allows specification of set array capacity
     * <p>
     * Initializes set array, sets power set array to null
     * 
     * @param initialCapacity integer that specifies array capacity
     */
    public GenericSetClass( int initialCapacity )
       {
        genericSetArray = new Object[ initialCapacity ];
       
        genericPwrSetArray = null;
        
        arrayCapacity = initialCapacity;
       
        arraySize = 0;
       }

// no copy constructor for generic classes
// possible clone
    
    /**
     * Adds generic element to set
     * <p>
     * increases capacity using checkForResize if array is full
     * <p>
     * does not allow duplicate values to be added to the set
     * @param item GenericData value to be added to set
     */
    public void addItem( GenericData item )
       {
        checkForResize();
       
        if( !hasElement( item ) ) 
           {
            genericSetArray[ arraySize ] = item;
           
            arraySize++;
           }
       }

    /**
     * Local function tests for resize of the set array
     * <p>
     * If array needs to be resized, array capacity is doubled;
     * otherwise, no change
     * 
     * @return boolean report that resize was conducted
     */
    private boolean checkForResize()
       {
        int index;
        Object[] tempArray;
       
        if( arraySize == arrayCapacity )
           {
            arrayCapacity *= 2;
           
            tempArray = new Object[ arrayCapacity ];
           
            for( index = 0; index < arraySize; index++ )
               {
                tempArray[ index] = genericSetArray[ index ];
               }
           
            genericSetArray = tempArray;
           
            return true;
           }
       
        return false;
       }
   
    /**
     * copies one Object array to another
     * 
     * @param destArray Object array being copied to
     * 
     * @param sourceArray Object array being copied from
     * 
     * @param size number of items in source array to be copied 
     * to destination array
     */
    private void copyArray( Object[] destArray, Object[] sourceArray, int size )
       {
        int index;
        
        for( index = 0; index < size; index++ )
           {
            destArray[ index ] = sourceArray[ index ];
           }
       }

    /**
     * Returns the intersection of THIS set and the given other set
     * 
     * @param other GenericSetClass with which intersection is found
     * 
     * @return GenericSetClass object with intersection of two sets
     */
    @SuppressWarnings( "unchecked" )    
    public GenericSetClass<GenericData> 
                       findIntersection( GenericSetClass<GenericData> other )
       {
        int index;
        GenericData arrayElement;
        GenericSetClass<GenericData> intersectionSet 
                                        = new GenericSetClass<GenericData>();

        for( index = 0; index < arraySize; index++ )
           {
            arrayElement = (GenericData)genericSetArray[ index ];
            
            if( other.hasElement( arrayElement ) )
               {
                intersectionSet.addItem( arrayElement );
               }
           }
       
        return intersectionSet;
       }
   
    /**
     * Generates the power set of the data in THIS set
     * <p>
     * No action if set is empty
     * 
     */
    @SuppressWarnings( "unchecked" ) 
    public void findPowerSet()
       {
        int setIndex, numSets;
       
        if( arraySize > 0 )
           {
            numSets = intToPower( BASE_TWO, arraySize );

            genericPwrSetArray = new GenericSetClass[ numSets ];

            // loop to number of items in set
            for( setIndex = 0; setIndex < numSets; setIndex++ )
               {
                genericPwrSetArray[ setIndex ] 
                                        = new GenericSetClass<GenericData>();
            
                getPowerSet( genericPwrSetArray[ setIndex ], 0, setIndex );
               }
           }
       }
   
    /**
     * Finds relative complement of THIS set in given other set
     * <p>
     * Returns other set having removed any items intersecting 
     * with THIS set
     * 
     * @param other GenericSetClass object from which THIS GenericSetClass item 
     * will be removed
     * 
     * @return GenericSetClass object with data as specified
     */
     @SuppressWarnings( "unchecked" )     
     public GenericSetClass<GenericData> 
       findRelativeComplementOfThisSetIn( GenericSetClass<GenericData> other )
        {
         int index;
         GenericData arrayElement;
         GenericSetClass<GenericData> relCompSetClass 
                                         = new GenericSetClass<GenericData>();
        
         for( index = 0; index < other.arraySize; index++ )
            {
             relCompSetClass.addItem( 
                                (GenericData)other.genericSetArray[ index ] );
            }
         
         for( index = 0; index < arraySize; index++ )
            {
             arrayElement = (GenericData)genericSetArray[ index ];
            
             if( relCompSetClass.hasElement( arrayElement ) )
                {
                 relCompSetClass.removeItem( arrayElement );
                }
            }
        
         return relCompSetClass;
        }
    
    /**
     * Returns the union of THIS set and the given other set
     * 
     * @param other GenericSetClass value with which union is found
     * 
     * @return GenericSetClass object with union of two sets
     */
    @SuppressWarnings( "unchecked" )    
    public GenericSetClass<GenericData> 
                              findUnion( GenericSetClass<GenericData> other )
       {
        int thisIndex, otherIndex;
        GenericData arrayElement;
        GenericSetClass<GenericData> unionSet 
                                        = new GenericSetClass<GenericData>();
       
        for( thisIndex = 0; thisIndex < arraySize; thisIndex++ )
           {
            arrayElement = (GenericData)genericSetArray[ thisIndex ];
            
            unionSet.addItem( arrayElement );
           }
       
        for( otherIndex = 0; otherIndex < other.arraySize; otherIndex++ )
           {
            arrayElement = (GenericData)other.genericSetArray[ otherIndex ];
            
            unionSet.addItem( arrayElement );
           }
       
        return unionSet;
       }
   
    /**
     * Finds the nth set of data for one set in the power set
     * from THIS set
     *  
     * @param set GenericSetClass object that has power set Nth item result;
     * used as output of this method
     * 
     * @param currentIndex integer index used in recursion to get data
     * from THIS data set array
     * 
     * @param currentValue integer value indicating the Nth set
     */
    @SuppressWarnings( "unchecked" ) 
    private void getPowerSet( GenericSetClass<GenericData> set,
                                         int currentIndex, int currentValue )
       {
        int workingValue;
        GenericData arrayElement;
       
        if( currentValue > 0 )
           {
            workingValue = currentValue % 2;
           
            if( workingValue == 1 )
               {
                arrayElement = (GenericData)genericSetArray[ currentIndex ];
                
                set.addItem( arrayElement ); 
               }
           
            getPowerSet( set, currentIndex + 1, currentValue / 2 );
           }    
       }
   
    /**
     * Tests to indicate if GenericData value is one
     * of the set elements
     * 
     * @param testElement GenericData element to be found in set
     * 
     * @return boolean result of test
     */
    @SuppressWarnings( "unchecked" )    
    public boolean hasElement( GenericData testElement )
       {
        int index;
        GenericData arrayElement;
        
        for( index = 0; index < arraySize; index++ )
           {
            arrayElement = (GenericData)genericSetArray[ index ];
            
            if( testElement.compareTo( arrayElement ) == 0 )
               {
                return true;
               }
           }
       
        return false;
       }
   
    /**
     * recursively calculates integer base to power (exponent)
     * 
     * @param base integer base value
     * 
     * @param exponent int exponent value
     * 
     * @return integer base to the exponent result
     */
    private int intToPower( int base, int exponent )
       {
        if( exponent > 0 )
           {
            return base * intToPower( base, exponent - 1 );
           }
       
        return 1;
       }
    /**
     * Tests to indicate if set is subclass of another given set
     * 
     * @param other GenericSetClass set to be tested
     * 
     * @return boolean result of test
     */
    @SuppressWarnings( "unchecked" )    
    public boolean isSubsetOf( GenericSetClass<GenericData> other )
       {
        int localIndex, otherIndex;
        boolean found;
        GenericData testElement, arrayElement;
       
        for( localIndex = 0; localIndex < arraySize; localIndex++ )
           {
            found = false;
           
            arrayElement = (GenericData)genericSetArray[ localIndex ];
           
            for( otherIndex = 0; 
                       otherIndex < other.arraySize && !found; otherIndex++ )
               {
                testElement = (GenericData)other.genericSetArray[ otherIndex ];
                
                if( arrayElement.compareTo( testElement ) == 0 )
                   {
                    found = true;
                   }
               }
           
            if( !found )
               {
                return false;
               }
           }
       
        return true;
       }
   
    /**
     * Provides list of power set elements
     * <p>
     * Set elements are comma delimited; Sets are semicolon delimited
     * <p>
     * Uses dash ('-') to indicate null set
     * 
     * @return String holding complete power set;
     * sets are semicolon-delimited, 
     * individual set values are comma-delimited 
     */
    public String powerSetToString()
       {
        int index;
        String outString = "";
       
        if( genericPwrSetArray != null )
           {
            for( index = 0; index < genericPwrSetArray.length; index++ )
               {
                if( index > 0 )
                   {
                    outString += "; ";                
                   }
               
                if( genericPwrSetArray[ index ].arraySize == 0 )
                   {
                    outString += '-';
                   }
           
                else
                   {
                    outString += genericPwrSetArray[ index ].toString();
                   }
               }            
           }
       
        return outString;
       }

    /**
     * Removes value at given index;
     * moves all succeeding data down in array
     * 
     * @param indexToRemove integer index of element value to remove
     */
    private void removeAtIndex( int indexToRemove )
       {
        int index;
       
        arraySize--;
       
        for( index = indexToRemove; index < arraySize; index++ )
           {
            genericSetArray[ index ] = genericSetArray[ index + 1 ];
           }
       }
   
    /**
     * Removes element if it is found in set
     * 
     * @param valToRemove GenericData value to be removed
     * 
     * @return boolean result of operation success
     */
    @SuppressWarnings( "unchecked" )
    public boolean removeItem( GenericData valToRemove )
       {
        int index;
        boolean found = false;
        GenericData arrayElement;
        
        for( index = 0; index < arraySize; index++ )
           {
            arrayElement = (GenericData)genericSetArray[ index ];
            
            if( valToRemove.compareTo( arrayElement ) == 0 )
               {
                removeAtIndex( index );
               
                found = true;
               }
           }
       
        return found;
       }
   
    /**
     * Description: Sorts elements using the bubble sort algorithm
     * <p>
     * Note: The data is sorted using the compareTo method of the given
     * data set; the compareTo method decides the key and the order
     * resulting
     * <p>
     * Creates separate list to be sorted, and returns sorted list;
     * does not sort local set array
     * 
     * @return String data holding list of sorted objects
     */
    @SuppressWarnings( "unchecked" )
    public String runBubbleSort()
       {
        int index;
        boolean swapped = true;
        GenericData oneItem, otherItem;
        Object[] localArray = new Object[ arraySize ];
        
        copyArray( localArray, genericSetArray, arraySize );
        
        while( swapped )
           {
            swapped = false;
           
            oneItem = (GenericData)localArray[ 0 ];
            
            for( index = 0; index < arraySize - 1; index++ )
               {
                otherItem = (GenericData)localArray[ index + 1 ];
               
                if( oneItem.compareTo( otherItem  ) > 0 )
                   {
                    swapValues( localArray, index, index + 1 );  

                    swapped = true; 
                   }
               
                else
                   {
                    oneItem = otherItem;
                   }
               
               } // end loop across list
           
           }  // end loop while swap occurred

        return toStringHelper( localArray );
       }
      
    /**
     * Description: Sorts elements using the insertion sort algorithm
     * <p>
     * Note: The data is sorted using the compareTo method of the given
     * data set; the compareTo method decides the key and the order
     * resulting
     * <p>
     * Creates separate list to be sorted, and returns sorted list;
     * does not sort local set array
     * 
     * @return String data holding list of sorted objects
     */
    @SuppressWarnings( "unchecked")
    public String runInsertionSort()
       {
        int listIndex, searchIndex;
        GenericData insertVal, testVal;
        boolean continueSearch;
        Object[] localArray = new Object[ arraySize ];
       
        copyArray( localArray, genericSetArray, arraySize );
        
        for( listIndex = 1; listIndex < arraySize; listIndex++ )
           {
            insertVal = (GenericData)localArray[ listIndex ];
           
            searchIndex = listIndex;
            
            continueSearch = true;
           
            while( continueSearch )
               {
                testVal = (GenericData)localArray[ searchIndex - 1 ];
               
                if( insertVal.compareTo( testVal ) < 0 )
                   {
                    localArray[ searchIndex ] = localArray[ searchIndex - 1 ];                       

                    searchIndex--;
                   }
               
                else
                   {
                    continueSearch = false;
                   }
               
                if( searchIndex == 0 )
                   {
                    continueSearch = false;
                   }
               
               }  // end search loop
           
            localArray[ searchIndex ] = (Object)insertVal;
           
           }  // end list loop

        return toStringHelper( localArray );
       }
   
    /**
     * Description: Sorts elements using the selection sort algorithm
     * <p>
     * Note: The data is sorted using the compareTo method of the given
     * data set; the compareTo method decides the key and the order
     * resulting
     * <p>
     * Creates separate list to be sorted, and returns sorted list;
     * does not sort local set array
     * 
     * @return String data holding list of sorted objects
     */
    @SuppressWarnings( "unchecked" )    
    public String runSelectionSort()
       {
        int listIndex, searchIndex, lowestIndex;
        GenericData lowestVal, searchVal;
        Object[] localArray = new Object[ arraySize ];

        copyArray( localArray, genericSetArray, arraySize );
        
        for( listIndex = 0; listIndex < arraySize - 1; listIndex++ )
           {
            lowestIndex = listIndex;
           
            lowestVal = (GenericData)localArray[ lowestIndex ];
 
            for( searchIndex = lowestIndex + 1; 
                                  searchIndex < arraySize; searchIndex++ )
               {
                searchVal = (GenericData)localArray[ searchIndex ];
               
                if( searchVal.compareTo( lowestVal ) < 0 )
                   {
                    lowestIndex = searchIndex;

                    lowestVal = (GenericData)localArray[ lowestIndex ];                       
                   }
               
               }  // end search loop

            swapValues( localArray, lowestIndex, listIndex );
           
           }  // end list item loop

        return toStringHelper( localArray );
       }
      
    /**
     * Swaps values within given array
     * 
     * @param localArray array of Objects used for swapping
     * 
     * @param indexOne integer index for one of the two items to be swapped
     * 
     * @param indexOther integer index for the other of the two items 
     * to be swapped
     */
    private void swapValues( Object[] localArray, int indexOne, int indexOther )
       {
        Object temp = localArray[ indexOne ];
        
        localArray[ indexOne ] = localArray[ indexOther ];
        
        localArray[ indexOther ] = temp;        
       }

    /**
     * Provides list of set array elements
     * as comma-delimited string
     * <p>
     * Overrides Object toString
     * 
     * @return String holding object from array;
     * uses toStringHelper
     */
    @Override
    public String toString()
       {
        return toStringHelper( this.genericSetArray );
       }

    /**
     * Provides list of set array elements
     * as comma-delimited string
     * 
     * @param localArray Object array holding Generic Data
     * 
     * @return String holding objects from array
     */
    public String toStringHelper( Object[] localArray )
       {
        int index;
        String outString = "";
       
        for( index = 0; index < arraySize; index++ )
           {
            if( index > 0 )
               {
                outString += ", ";
               }
           
            outString += localArray[ index ].toString();
           }
       
        return outString;        
       }

   }