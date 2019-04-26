/**
 * description: Students will develop a simple array-based data type
 * that holds data in set form
 * <p>
 * @author Dongyang,Yu
 */
package p1_package;


public class SetClass
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
     * integer array for data
     */
    int[] setArray;
    
    /**
     * SetClass array for management of power sets
     * 
     */
    SetClass[] powerSetArray;
    
    /**
     * capacity of array
     */
    int arrayCapacity;
    
    /**
     * number of values in array
     */
    int arraySize;
    
    /**
     * constants for specifying set data
     */
    public static final int INCREMENTED = 101;
    public static final int ODD = 102;
    public static final int EVEN = 103;
    public static final int PRIME = 104;
   
    /**
     * Default constructor
     * <p>
     * Initializes set array but sets power set array to null
     */
    public SetClass()
       {
        arrayCapacity = DEFAULT_ARRAY_CAPACITY; 
        
        arraySize = 0;
        
        setArray = new int[ arrayCapacity ];
        
        powerSetArray = null;
       }
   
    /**
     * Initialization constructor
     * <p>
     * Allows specification of set array capacity
     * <p>
     * Initializes set array but sets power set array to null
     * 
     * @param initialCapacity integer that specifies array capacity
     */
    public SetClass(int initialCapacity)
       {
        arrayCapacity = initialCapacity;
        
        setArray = new int[ arrayCapacity ];
        
        powerSetArray = null;
        
        arraySize = 0;
       }
    
    /**
     * Copy constructor
     * <p>
     * Duplicates copied set class
     * <p>
     * Also responsible for correct initialization/update 
     * of power set array
     * 
     * @param copied SetClass object to be copied
     */    
    public SetClass( SetClass copied )
       {
        int index;
        
        arraySize = copied.arraySize;
        
        arrayCapacity = copied.arrayCapacity;
        
        setArray = new int [ arrayCapacity ];
        
        for( index = 0; index < arraySize; index++ )
           {
            setArray[ index ] = copied.setArray[ index ];
           }
        
        if( copied.powerSetArray != null )
           {
            powerSetArray = new SetClass[ copied.powerSetArray.length ];
            
            for( index = 0; index < copied.powerSetArray.length; index++ )
               {
                powerSetArray[ index ] = new SetClass( powerSetArray[ index ] );
               }
           }
        else
           {
            powerSetArray = null;
           }
        
        // 因为类型是int,所以不需要重新创建个数组
        //还有powerSetArray 别忘了
//        int tempArr[] = new int[ arraySize ];
//        for( index = 0; index < arraySize; index++ )
//        	{
//        	 tempArr[ index ] = copied.setArray[ index ];
//        	 powerSetArray[ index ] = copied.powerSetArray[ index ];
//        	}
//        setArray = tempArr;
       }

    /**
     * Adds integer to set
     * <p>
     * increases capacity using checkForResize if array is full
     * <p>
     * does not allow duplicate values in set
     * 
     * @param item integer value to be added to set
     */   
    void addItem( int item )
       {
        checkForResize();
        if( !hasElement( item )  ) // 最初版本中 - Add item doesn't check if in set
           {
            setArray[ arraySize ] = item;
            
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
        int[] tempArray;
        
        if( arraySize == arrayCapacity )    //don't need >=, just == is ok.
           {
            arrayCapacity *= 2;
            
            tempArray = new int[ arrayCapacity ];
            
            for( index = 0; index < arraySize; index++)
               {
                tempArray[ index ] = setArray[ index ];
               }
          setArray = tempArray;
          return true;
       }
        return false;
    }

    /**
     * Returns the intersection of THIS set and the given other set
     * 
     * @param other SetClass data with which intersection is found
     * 
     * @return SetClass object with intersection of two sets
     */
    public SetClass findIntersection(SetClass other) 
       {
        int index;
        SetClass intersectionSet = new SetClass();
        
        for( index = 0; index < arraySize; index++ )
           {
            if( other.hasElement( setArray[ index ] ) )
               {
                intersectionSet.addItem( setArray[index] );
               }
           }
        
        return intersectionSet;
       }

    /**
     * Calculates the power set of the data in THIS set;
     * stores in member array
     * <p>
     * No action if set is empty
     * 
     */
    public void findPowerSet()
       {
        int setIndex, numSets;
        
        if( arraySize > 0)
           {
            numSets = toPower( BASE_TWO, arraySize ); 
            
            powerSetArray = new SetClass[ numSets ];
            
            // loop to number of items in set
            for( setIndex = 0; setIndex < numSets; setIndex++ )
               {
                powerSetArray[ setIndex ] = new SetClass();
                
                getPowerSet( powerSetArray[ setIndex ], 0, setIndex );
               }
            
           }
//        int PowSetSize = toPower(BASE_TWO, arraySize);
//        int index;
//        if( setArray != null)
//           {
//            powerSetArray = new SetClass[ PowSetSize ];
//            for( index = 0; index < PowSetSize; index++ )
//               {
//                powerSetArray[ index ] = new SetClass( arraySize );
//                getPowerSet( powerSetArray[ index ], 0, index );
//               }
//           }
       }
    
    /**
     * Returns the union of THIS set and the given other set
     * 
     * @param other SetClass data with which union is found
     * 
     * @return SetClass object with union of two sets
     */
    public SetClass findUnion(SetClass other)
       {
        int otherIndex;
       
        // inspiration: use copy constructor!
        SetClass unionSet = new SetClass( this );
       
        for( otherIndex = 0; otherIndex < other.arraySize; otherIndex++ )
           {
            unionSet.addItem( other.setArray[otherIndex] );
           }
       
        return unionSet;
       }
    
    /**
     * Finds relative complement of THIS set in given other set
     * <p>
     * Returns other set having removed any items intersecting 
     * with THIS set
     * 
     * @param other SetClass object from which THIS SetClass items 
     * will be removed
     * 
     * @return SetClass object with data as specified
     */
    public SetClass findRelativeComplementOfThisSetIn(SetClass other)
       {
        int index;
        SetClass relCompSet = new SetClass( other );
        
        for( index = 0; index < arraySize; index++ )
           {
            if( relCompSet.hasElement( setArray[ index ] ) )
               {
                relCompSet.removeValue( setArray[ index ] );
               }
           }
        
        return relCompSet;
        
//        SetClass tempClass = new SetClass();
//        for( index = 0; index < other.arraySize; index++)
//           {
//            if( !this.hasElement( other.setArray[index] ))
//               {
//                tempClass.addItem( other.setArray[index] );
//               }
//           }
//        return tempClass;
       } 

    /**
     * Seeks and finds prime starting at given value
     * 
     * @param value integer value to start search for prime
     * 
     * @return next prime number
     */
    private int getNextPrime( int value )
       {
        while ( !isPrime( value ) )
           {
            value++;  
           }
        return value;
       }
    
    /**
     * Removes value if it is found in set
     * 
     * @param valToRemove integer value to be removed
     * 
     * @return boolean result of operation success
     */
    public boolean removeValue(int valToRemove)
       {
    	  int index;
    	  
        for( index = 0; index < arraySize; index++ )
           {
            if( setArray[index] == valToRemove )
               {
                removeAtIndex( index );
                return true;
               }
           }
        return false;
       }
    
    /**
     * Removes value at given index;
     * moves all succeeding data down in array
     * 
     * @param indexToRemove integer index of element value to remove
     */
    private void removeAtIndex(int indexToRemove)
       {
    	  int index;
    	  
    	  arraySize--;
    	  
        for( index = indexToRemove; index < arraySize; index++)
           {
            setArray[ index ] = setArray[ index + 1 ];
           }
       }


    
    private void getPowerSet(SetClass set,
                               int currentIndex,
                                  int currentValue)
       {
        int workingValue;
        
        if( currentIndex > 0 )  // instead of crtIndex < arraySize
           {
            workingValue = currentValue % 2;
            
            if( workingValue == 1 )
               {
                set.addItem( setArray[ currentIndex ] );
               }
            
            getPowerSet( set, currentIndex + 1, currentValue / 2);
           }
       
       }
    
    /**
     * Tests to indicate if integer value is one
     * of the set elements
     * 
     * @param testElement integer element to be found in set
     * 
     * @return boolean result of test
     */
    public boolean hasElement( int testElement )  // - Declares var in loop (-2)
       {
    	  int index;
    	  
        for( index = 0; index < arraySize; index++ )
           {
            if( testElement == setArray[ index ] )
               {
                return true;
               }
           }
        
        return false;
       }
   
    private int toPower(int base, int exponent)
       {
//        int result = 1;
//        if( exponent == 0)
//           {
//           }
//        else if( exponent == 1)
//           {
//            result *= base;
//           }
//        else 
//           {
//            toPower(base, exponent - 1);
//           }
//        return result;
       
        if( exponent > 0 )
           {
            return base *= toPower( base, exponent - 1 );
           }
          
        return 1;
       }
    
    /**
     * Tests for even, reports
     * 
     * @param value integer value to be tested
     * 
     * @return boolean result as specified
     */
    private boolean isEven(int value)
       {
        return value % 2 == 0;
       }
    
    /**
     * Tests to indicate if given integer value is prime
     * 
     * @param testVal integer value given
     * 
     * @return boolean result of test
     */
    private boolean isPrime( int testVal )
       {
        int modVal;
        int testDivide = (int)( Math.ceil( Math.sqrt( (double)testVal ) ) );
        
        for( modVal = 2; modVal < testDivide; modVal++)
           {
            if( testVal % modVal == 0)
               {
                return false;
               }
           }
        return true;
       }
    
    /**
     * Tests to indicate if set is subclass of another given set
     * 
     * @param other SetClass object to be tested
     * if THIS set is a subset of it
     * 
     * @return boolean result of test
     */
    public boolean isSubsetOf(SetClass other)
       {
    	  int index;
        for( index = 0; index < arraySize; index++ )
           {
            if( !other.hasElement( setArray[ index ] ) )
               {
                return false;
               }
           }
        return true;
       }
    
    /**
     * Loads a number of specified integers to set
     * <p>
     * Characteristics may be odd, even, incremented, or prime
     * <p> 
     * Parameter four is only used with INCREMENTED
     * 
     * @param startValue integer value indicates starting value
     * 
     * @param numItems integer number of items to load
     * 
     * @param valueCharacteristic integer characteristic code 
     * (ODD, EVEN, INCREMENTED, PRIME )
     * 
     * @param incrementBy integer value used to specify increment
     * if INCREMENTED characteristic is set
     */
    public void loadItems(int startValue, int numItems,
                               int valueCharacteristic,int incrementBy)
       {
        int index, runningValue = startValue;
        
        for( index = 0 ; index < numItems; index++)
           {
            switch( valueCharacteristic )
              {
               case ODD: if( !isEven( runningValue ))
                            {
                             addItem( runningValue );
                             runningValue += 2;
                             break;
                            }
               case EVEN: if( isEven( runningValue ))
                             {
                              addItem( runningValue );
                              runningValue += 2;
                              break;
                             }
               case PRIME: if( isPrime( runningValue ))
                              {
                               addItem( runningValue );
                               runningValue = getNextPrime( runningValue + 1 );
                               break;
                              }
               case INCREMENTED: addItem( runningValue );
                                 runningValue += incrementBy;
                                 break;
               default: break;
              }
           }
       }
    
    /**
     * Provides list of set array elements
     * as comma-delimited string
     */
    @Override
    public java.lang.String toString()
       {
        int index;
        String outString = "";
        
//        if( arraySize == 0)
//           {
//            outString = "-";
//           }
//        else
//           {
//            outString = setArray[0] + ""; 
//            for( index = 1; index < arraySize; index++ )
//               {
//                outString += ", " + setArray[index]; 
//               }
//           }
        for( index = 0; index < arraySize; index++ )
           {
            if( index > 0 )
               {
                outString += ", ";
               }
            
            outString += setArray[ index ];
           }
        
        return outString;
       }
    /**
     * Provides list of set array elements as comma-delimited string
     */
    
    /**
     * Provides list of power set elements
     * <p>
     * Set elements are comma delimited; Sets are semicolon delimited
     * <p>
     * Uses dash ('-') to indicate null set
     * 
     * @return String holding power set from member power set array
     */
    public String powerSetToString()
       {
        int index;
        String result = ""; 
        int PowSetSize = toPower(BASE_TWO, arraySize);
        
        if( powerSetArray == null )
           {
            return "-; ";            
           }
        for( index = 0; index < PowSetSize; index++ )
        {
           result += powerSetArray[index].toString() + "; ";
        }
        return result;
        
//        int index;
//        String outString = "";
//        
//        if( powerSetArray != null )
//           {
//            for( index = 0; index < powerSetArray.length; index++ )
//               {
//                if( index > 0 )
//                   {
//                    outString += "; ";                
//                   }
//                
//                if( powerSetArray[ index ].arraySize == 0 )
//                   {
//                    outString += '-';
//                   }
//            
//                else
//                   {
//                    outString += powerSetArray[ index ].toString();
//                   }
//               }            
//           }
//        
//        return outString;
       }

   

    }