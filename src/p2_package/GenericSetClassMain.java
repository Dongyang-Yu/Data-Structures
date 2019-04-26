package p2_package;

public class GenericSetClassMain
   {
    /**
     * constant for control of load operation
     */
    public static final int ODD_VALUES = 101;

    /**
     * constant for control of load operation
     */
    public static final int EVEN_VALUES = 102;

    /**
     * constant for control of load operation
     */
    public static final int PRIME_VALUES = 103;
    
    /**
     * constant for control of load operation
     */
    public static final int INCREMENTED_VALUES = 104;
    
    /**
     * Main function of driver/test class
     * 
     * @param args String arguments not used
     */
    public static void main(String[] args)
       {
        GenericSetClass<IntDataClass> gscOdd, gscPrime, gscSmall;
        GenericSetClass<IntDataClass> gscIncremented, gscResult;
        GenericSetClass<IntDataClass> gscRandom;
        String sortedVals;
        int startValue, lowLimit, highLimit, numItems, characteristic, incrementBy;

        startValue = 5; numItems = 15; 
        characteristic = PRIME_VALUES; incrementBy = 0;
        gscPrime = loadGSC( startValue, numItems, characteristic, incrementBy );
        System.out.println( "Prime values: " + gscPrime.toString() );
        
        startValue = 5; numItems = 30; 
        characteristic = ODD_VALUES; incrementBy = 0;
        gscOdd = loadGSC( startValue, numItems, characteristic, incrementBy );
        System.out.println( "Odd values: " + gscOdd.toString() );

        startValue = 5; numItems = 30; 
        characteristic = INCREMENTED_VALUES; incrementBy = 3;
        gscIncremented = loadGSC( startValue, numItems, characteristic, incrementBy );
        System.out.println( "Incremented values: " + gscIncremented.toString() );

        startValue = 5; numItems = 4; 
        characteristic = INCREMENTED_VALUES; incrementBy = 5;
        gscSmall = loadGSC( startValue, numItems, characteristic, incrementBy );
        System.out.println( "Small set of values: " + gscSmall.toString() );

        gscResult = gscOdd.findIntersection( gscPrime );
        System.out.println( "Intersection of odd and prime: " + gscResult.toString() );
        
        gscResult = gscOdd.findUnion( gscPrime );
        System.out.println( "Union of odd and prime: " + gscResult.toString() );
        
        gscResult = gscOdd.findRelativeComplementOfThisSetIn( gscIncremented);
        System.out.println( "Relative Complement of odd and incremented: " + gscResult.toString() );

        gscSmall.findPowerSet();
        System.out.println( "Power Set of small: " + gscSmall.powerSetToString() );
        
        numItems = 10; lowLimit = 10; highLimit = 99;
        gscRandom = loadGscWithRands( numItems, lowLimit, highLimit );
        System.out.println( "Before bubble sort: " + gscRandom.toString() );
        sortedVals = gscRandom.runBubbleSort();
        System.out.println( "After bubble sort: " + sortedVals );
        
        numItems = 10; lowLimit = 10; highLimit = 99;
        gscRandom = loadGscWithRands( numItems, lowLimit, highLimit );
        System.out.println( "Before selection sort: " + gscRandom.toString() );
        sortedVals = gscRandom.runSelectionSort();
        System.out.println( "After selection sort: " + sortedVals );
        
        numItems = 10; lowLimit = 10; highLimit = 99;
        gscRandom = loadGscWithRands( numItems, lowLimit, highLimit );
        System.out.println( "Before insertion sort: " + gscRandom.toString() );
        sortedVals = gscRandom.runInsertionSort();
        System.out.println( "After insertion sort: " + sortedVals );
        
      }

    /**
     * Method to load IntDataClass data to GenericSetClass
     * with varying numerical sequences
     * 
     * @param startAt integer identifies starting value of set
     * 
     * @param numVals integer identifies number of items to be loaded into set
     * 
     * @param dataCharacteristic integer values uses one of four constants
     * to identify characteristics of data loaded into set
     * 
     * @param delta integer value used if INCREMENTED_VALUES constant
     * is used for loading data into set
     * 
     * @return GenericSetClass object with IntDataClass data loaded
     */
    public static GenericSetClass<IntDataClass> loadGSC( int startAt, 
                             int numVals, int dataCharacteristic, int delta )
       {
        int index, runningValue = startAt;
        GenericSetClass<IntDataClass> newGscObject 
                                       = new GenericSetClass<IntDataClass>();
        
        if( dataCharacteristic == ODD_VALUES 
                                       || dataCharacteristic == EVEN_VALUES )
           {
            if( dataCharacteristic == ODD_VALUES && isEven( runningValue ) 
                || dataCharacteristic == EVEN_VALUES 
                                                 && !isEven( runningValue ) )
               {
                runningValue++;
               }
           
            for( index = 0; index < numVals; index++ )
               {
                newGscObject.addItem( new IntDataClass( runningValue ) );
               
                runningValue += 2;
               }
           }
       
        else if( dataCharacteristic == PRIME_VALUES )
           {
            // decrement by one for the first iteration
            runningValue--;
           
            for( index = 0; index < numVals; index++ )
               {
                runningValue = getNextPrime( runningValue + 1 );
               
                newGscObject.addItem( new IntDataClass( runningValue ) );
               }
           }
       
        else  // assume incremented
           {
           for( index = 0; index < numVals; index++ )
              {
               newGscObject.addItem( new IntDataClass( runningValue ) );
              
               runningValue += delta;
              }
           }

        return newGscObject;
       }

    /**
     * Method to load IntDataClass data to GenericSetClass
     * with random values
     * 
     * @param numRands integer identifies number of values to generate
     * 
     * @param lowLimit integer identifies low limit of generated randoms
     * 
     * @param highLimit integer identifies high limit of generated randoms
     * 
     * @return GenericSetClass object with IntDataClass data loaded
     */
    public static GenericSetClass<IntDataClass> loadGscWithRands( 
                                  int numRands, int lowLimit, int highLimit )
       {
        int index, randNum;
        GenericSetClass<IntDataClass> newGscObject 
                                       = new GenericSetClass<IntDataClass>();
        
        for( index = 0; index < numRands; index++ )
           {
            randNum = generateRandBetween( lowLimit, highLimit );
            
            newGscObject.addItem( new IntDataClass( randNum ) );
           }

        return newGscObject;
       }

    /**
     * Generate random value between the lowest and highest
     * specified limits inclusive
     * 
     * @param lowLimit specified integer low limit of random value range
     * 
     * @param highLimit specified integer high limit of random value range
     * 
     * @return random integer value generated between the inclusive limits
     */
    public static int generateRandBetween( int lowLimit, int highLimit )
       {
        int randVal, range;
        
        if( highLimit > lowLimit )
           {
            // create range of numbers
            range = highLimit - lowLimit + 1;
            
            // find random integer value from value between 0 and 1
            randVal = (int)( Math.random() * 1000000 );
            
            return randVal % range + lowLimit;
           }
        
        return 0;
       }
    
    /**
     * Seeks and finds prime starting at given value
     * 
     * @param value integer value to start search for prime
     * 
     * @return next prime number
     */
    public static int getNextPrime( int value )
       {
        while( !isPrime( value ) )
           {
            value++;
           }
                
        return value;
       }
    
    /**
     * Tests to indicate if given integer value is prime
     * 
     * @param testVal integer value given
     * 
     * @return boolean result of test
     */
    private static boolean isPrime( int testVal )
       {
        int modVal = 2;
        int testDivide = (int)( Math.ceil( Math.sqrt( (double)testVal ) ) );
        
        while( modVal <= testDivide ) 
           {
            if( testVal % modVal == 0 )
               {
                return false;
               }
            
            modVal++;
           }
        
        return true;
       }
    
    /**
     * Compares two strings with integer result
     * s1 lt s2: result lt 0; s1 gt s2: result gt 0;
     * s1 == s2: result == 0;
     * two strings equal up to the point that s1 is longer: result gt 0;
     * two strings equal up to the point that s2 is longer: result lt 0
     * 
     * @param strOne String value with one of the two strings to be tested
     * 
     * @param strTwo String value with the other of the two strings to be tested
     * 
     * @return integer difference between strings as specified
     */
    public static int compareStrings( String strOne, String strTwo )
       {
        int difference, index = 0;
        
        while( index < strOne.length() && index < strTwo.length() )
           {
            difference = strOne.charAt( index ) - strTwo.charAt( index );
            
            if( difference != 0 )
               {
                return difference;
               }
            
            index++;
           }
        
        return strOne.length() - strTwo.length();
       }
    
    /**
     * Tests integer value for even condition
     * 
     * @param testValue integer value to be tested
     * 
     * @return boolean result of test
     */
    public static boolean isEven( int testValue )
       {
        return testValue % 2 == 0;
       }
   }