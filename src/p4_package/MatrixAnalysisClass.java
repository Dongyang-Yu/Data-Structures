package p4_package;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class MatrixAnalysisClass
   {
    /**
     * constant definition for maximum input string
     */
    private static final int MAX_INPUT_CHARS = 80;
    
    /**
     * constant definition of EOF marker
     */
    private static final int EOF_MARKER = -1;

    /**
     * constant specification of number of spaces 
     * for each recursive level indent
     */
    private final int REC_LEVEL_INDENT = 3;
    
    /**
     * constant definition for COLON    
     */
    private static final char COLON = ':';
    
    /**
     * constant definition for SPACE    
     */
    private static final char SPACE = ' ';
    
    /**
     * constant definition for minus sign
     */
    private static final char MINUS_SIGN = '-';
    
    /**
     * constant control code for display method
     */
    private static final int VALID_ITEM = 101;
    
    /**
     * constant control code for display method
     */
    private static final int INVALID_ITEM = 102;
    
    /**
     * constant control code for display method
     */
    private static final int OVER_SUM = 103;
    
    /**
     * constant control code for display method
     */
    private static final int OUT_OF_BOUNDS = 104;
    
    /**
     * constant control code for display method
     */
    private static final int TEST_LOCATION = 105;
    
    /**
     * constant control code for display method
     */
    private static final int NO_SOLUTION = 106;
    
    /**
     * FileReader object used for data upload
     */
    private FileReader fileIn;
   
    /**
     * flag selects show operations
     */
    private boolean verboseFlag;
    
    /**
     * specification of member row height
     */
    private int arrayRowHeight;
    
    /**
     * specification of member row width
     */
    private int arrayRowWidth;
    
    /**
     * counts number of recursive calls
     */
    private int iterationCount;
    
    /**
     * two dimensional array for data storage
     */
    private int[][] dataArray;

    /**
     * SimpleGenericSetClass holds locations found during search
     */
    private SimpleGenericSetClass<CellDataClass> locationSet;
    
    /**
     * Default constructor
     */
    public MatrixAnalysisClass()
       {
        dataArray = null;
        
        arrayRowWidth = arrayRowHeight = 0;
        
        verboseFlag = false;
        
        locationSet = new SimpleGenericSetClass<CellDataClass>();
       }
    
    /**
     * Initialization constructor
     * 
     * @param verboseSetting boolean flag for verbose setting;
     * indicates whether or not to show search process
     */
    public MatrixAnalysisClass( boolean verboseSetting )
       {
        dataArray = null;
       
        arrayRowWidth = arrayRowHeight = 0;
        
        locationSet = new SimpleGenericSetClass<CellDataClass>();
        
        verboseFlag = verboseSetting;
       }
    
    /**
     * Method calls helper to find contiguous values
     * adding to a specified number in an array
     * <p>
     * Note: Displays success or failed results
     * <p>
     * Rules of the searching process:
     * <p> 
     * The search starts in the upper left corner, 
     * reporting each valid location found
     * <p>
     * The method must search to a current location's right,
     * then below it, and then to its left, exactly in that order
     * <p>
     * The method must report what it finds, 
     * either as a successful candidate value
     * or a failed candidate value (and why it failed)
     * <p>
     * The method must use recursive backtracking,
     * it must backtrack the recursion upon any failure,
     * but it must continue forward recursion upon any success
     * until the solution is found
     * <p>
     * The method must be able to handle the condition
     * that the value in the upper left corner does not support the solution 
     * 
     * @param sumRequest integer value indicating desired sum
     * 
     * @return boolean indication of success
     */
    public boolean findSum( int sumRequest )
       {
        int startingXIndex = 0, startingYIndex = 0;
        int startingTotal = 0, startingRecLevel = 0;
        //为什么不能直接在findSumHelper 里设0,0,0,0, 因为别人不知道这些0代表是什么，
        //他需要往下翻， 找到这个函数,查看这些参数代表什么,waste time!
        
        iterationCount = 0;
        
        System.out.println( "Search Start:" );
        if( findSumHelper(sumRequest, startingTotal,
                       startingXIndex, startingYIndex, startingRecLevel) )
           {
            System.out.println( "\nSearch End: Successful Set: "
                                                + locationSet.toString() );
            System.out.println( "Iteration Count: " + iterationCount );
            
            return true;
           }
        
        System.out.println( "\nSearch End: Solution Not Found" );
        System.out.println( "Iteration Count: " + iterationCount );
        
        return false;
       }
    
    /**
     * Helper method to find requested sum in an array
     * <p>
     * Note: Uses displayStatus method for all output
     * (no other printing from this method);
     * displayStatus provides appropriately indented current test location
     * 
     * @param sumRequest integer value indicating desired sum
     * 
     * @param runningTotal integer current sum of search process
     * 
     * @param xIndex integer current x position of search process
     * 
     * @param yIndex integer current y position of search process
     * 
     * @param recLevel integer current level of recursion, used to set display indent
     * 
     * @return boolean indication of success
     */
    //shopping list
    
    // check for not in bounds
    
       //return false
    
    // get value from the array
    
    // calculate the total
    
    // create the cell object
    
    // check if element is already in set
    
       // return false
    
    // check if value is above sumRequest
    
       // return false
    
    // add item to the set
    
    // check for success
    // check for correct sum && bottom of array
    
       // return true;
    
    // call recursion to the right, test for success
    
       // return true
    
    // call recursion down, test for success
    
       // return true
    
    // call recursion to the left, test for success
       
       // return true
    
    // remove item from set
    
    // check for first call and current x index less than the row width
       
       // return next recursive call with next position to the right
    
    // return false
    private boolean findSumHelper( int sumRequest, int runningTotal, 
                                       int xIndex, int yIndex, int recLevel )
       {
        CellDataClass currentCellData = new CellDataClass( 0, xIndex, yIndex );
        int currentValue = 0;
        
        iterationCount++;
        
        displayStatus( recLevel, "Trying Location: ", currentCellData, TEST_LOCATION );

        // check if location not found
        if( !isInBounds(xIndex, yIndex) )
           {
            displayStatus( recLevel, "Location failed: ", currentCellData, OUT_OF_BOUNDS);
            
            return false;
           }
        
        currentValue = dataArray[ yIndex ][ xIndex ];
        
        runningTotal += currentValue;
        
        currentCellData = new CellDataClass( dataArray[ yIndex ][ xIndex ], xIndex, yIndex);

        // check if the element already in set
        if( locationSet.hasItem( currentCellData ) )
           {
            displayStatus( recLevel, "Location failed: ", currentCellData, INVALID_ITEM);
            
            return false;
           }
        
        // check if solution is over sum
        if( runningTotal > sumRequest )
           {
            displayStatus( recLevel, "Location failed: ", currentCellData, OVER_SUM );
            
            return false;
           }
        
        // successful location found
        displayStatus( recLevel, "Valid Location found: ", currentCellData, VALID_ITEM );
        
        locationSet.addItem( currentCellData ); 
        
        // check if path found
        if( runningTotal == sumRequest && yIndex == arrayRowHeight - 1 )
           {
            return true;
           }
        
        // recursive backtrack
        if( findSumHelper( sumRequest,
                          runningTotal, xIndex + 1, yIndex, recLevel + 1) )
           {
            displayStatus( recLevel, "Found at: ", currentCellData, VALID_ITEM);
            return true;
           }
        
        else if( findSumHelper( sumRequest,
                          runningTotal, xIndex, yIndex + 1, recLevel + 1) )
           {
            displayStatus( recLevel, "Found at: ", currentCellData, VALID_ITEM);
            return true;
           }
        
        else if( findSumHelper(sumRequest,
                          runningTotal, xIndex - 1, yIndex, recLevel + 1) )
           {
            displayStatus( recLevel, "Found at: ", currentCellData, VALID_ITEM);
            return true;
           }
        
        // item has exhausted all child tests and does not support the solution
        else
           {
            displayStatus( recLevel, "Location failed: ", currentCellData, NO_SOLUTION );
            
            locationSet.removeItem( currentCellData );
           }
        
        //check if the recursiveLevel == 0 && out of the right boundary
        if( recLevel == 0 && xIndex < arrayRowWidth )
           {
            runningTotal = 0;
           
            return findSumHelper( sumRequest, runningTotal,
                                          xIndex + 1, yIndex, recLevel + 1);
           }
        
        return false;
       
       }

    /**
     * checks that requested x, y indices of array
     * are not out of bounds
     * 
     * @param xLocTest integer x index to be tested
     * 
     * @param yLocTest integer y index to be tested
     * 
     * @return boolean result of specified test
     */
    private boolean isInBounds( int xLocTest, int yLocTest )
       {       
        return ( xLocTest < arrayRowWidth && xLocTest >= 0 && yLocTest >= 0 && yLocTest < arrayRowHeight );
       }
    
    /**
     * displays status at indented level
     * <p>
     * Displays for 
     * <p>1) location not found, 
     * <p>2) item already in set,
     * <p>3) item causes over sum condition (opSuccess code OVER_SUM), 
     * <p>4) item has exhausted all child tests and does not support the solution
     * (opSuccess code INVALID_ITEM),
     * <p>5) item accepted (opSuccess code VALID_ITEM), shows current location 
     * 
     * @param recLevel integer specification of current level
     * 
     * @param status String indication of success or failure
     * 
     * @param opSuccess integer code indicating success or different failures
     * 
     * @param current CellDataClass data at current level
     */
    private void displayStatus( int recLevel, 
                     String status, CellDataClass current, int opSuccess )
       {
        int index, recLevelIndent = recLevel * REC_LEVEL_INDENT;
       
        if( verboseFlag )
           {
            for( index = 0; index < recLevelIndent; index++ )
               {
                System.out.print( SPACE );
               }
       
            System.out.print( status );
       
            if( opSuccess == TEST_LOCATION )
               {
                System.out.println( "(" + current.xPos + ", "
                                                  + current.yPos + ")" );
               }
           
            else if( current == null || opSuccess == OUT_OF_BOUNDS )
               {
                System.out.println( "Location Not Found" );
               }
       
            else if( opSuccess == VALID_ITEM )
               {
                System.out.println( current.toString() );
               }
           
            else if( opSuccess == NO_SOLUTION )
               {
                System.out.println( "Doesn't Support Solution" );
               }

            else if( locationSet.hasItem( current ) )
               {
                System.out.println( "Already In Set" );
               }
           
            else if( opSuccess == OVER_SUM )
               {
                System.out.println( "Over Requested Sum" );
               }

           }
       }

    /**
     * uploads data from requested file
     * 
     * @param fileName name of file to access
     * 
     * @return Boolean result of data upload
     */
    public boolean uploadData( String fileName )
       {
        int rowIndex, colIndex;
        
        try
           {
            // Open FileReader 
            fileIn = new FileReader( fileName );
           
            // get leader line ahead of array height
            getALine( MAX_INPUT_CHARS, COLON );
           
            // get row height
            arrayRowHeight = getAnInt( MAX_INPUT_CHARS );
            
            // get leader line ahead of array width
            getALine( MAX_INPUT_CHARS, COLON );
           
            // get row width
            arrayRowWidth = getAnInt( MAX_INPUT_CHARS );
            
            dataArray = new int[ arrayRowHeight ][ arrayRowWidth ];
            
            for( rowIndex = 0; rowIndex < arrayRowHeight; rowIndex++ )
               {                
                for( colIndex = 0; colIndex < arrayRowWidth; colIndex++ )
                   {
                    dataArray[ rowIndex ][ colIndex ] 
                                               = getAnInt( MAX_INPUT_CHARS );
                   }
               }
           }
      
        catch( FileNotFoundException fnfe )
           {
            System.out.println( "DATA ACCESS ERROR: Failure to open input file" );
          
            return false;
           }

        return true;
       }
   
   /**
    * gets a string up to a maximum length or to specified delimiter
    * 
    * @param maxLength maximum length of input line
    * 
    * @param delimiterChar delimiter character to stop input
    * 
    * @return String captured from file
    */
   private String getALine( int maxLength, char delimiterChar )
      {
       int inCharInt;
       int index = 0;
       String strBuffer = "";

       try
          {
           inCharInt = fileIn.read();

           // consume leading spaces
           while( index < maxLength && inCharInt <= (int)( SPACE )  )
              {
               if( inCharInt == EOF_MARKER )
                  {
                   return "EndOfFile";
                  }
               
               index++; 
               
               inCharInt = fileIn.read();
              }
           
           while( index < maxLength && inCharInt != (int)( delimiterChar ) )
              {   
               if( inCharInt >= (int)( SPACE ) )
                  {
                   strBuffer += (char)( inCharInt );

                   index++;
                 }
               
               inCharInt = fileIn.read();             
              }
           
           //inCharInt = fileIn.read();
          }
       
       catch( IOException ioe )
          {
           System.out.println( "INPUT ERROR: Failure to capture character" );
          
           strBuffer = "";
          }
          
       return strBuffer;
      }

    /**
     * gets an integer from the input string
     * 
     * @param maxLength maximum length of characters
     * input to capture the integer
     * 
     * @return integer captured from file
     */
    private int getAnInt( int maxLength )
       {
       int inCharInt;
       int index = 0;
       String strBuffer = "";
       int intValue;
       boolean negativeFlag = false;

       try
          {
           inCharInt = fileIn.read();

           // clear space up to number
           while( index < maxLength && !charInString( (char)inCharInt, "0123456789+-" ) )
              {
               inCharInt = fileIn.read();
               
               index++;
              }
           
           if( inCharInt == MINUS_SIGN )
              {
               negativeFlag = true;
               
               inCharInt = fileIn.read();
              }

           while( charInString( (char)inCharInt, "0123456789" ) )
              {   
               strBuffer += (char)( inCharInt );

               index++;
               
               inCharInt = fileIn.read();
              }            
          }
       
       catch( IOException ioe )
          {
           System.out.println( "INPUT ERROR: Failure to capture character" );
          
           strBuffer = "";
          }
          
       intValue = Integer.parseInt( strBuffer );
       
       if( negativeFlag )
          {
           intValue *= -1;
          }
       
       return intValue;
       }

    public void dumpArray()
       {
        int rowIndex, colIndex;
        
        System.out.println( "\nMatrixAnalysisClass Diagnostic Array Dump:" );
        
        for( rowIndex = 0; rowIndex < arrayRowHeight; rowIndex++ )
           {
            for( colIndex = 0; colIndex < arrayRowWidth; colIndex++ )
               {
                if( colIndex > 0 )
                   {
                    System.out.print( ", " );
                   }
                
                System.out.print( dataArray[ rowIndex ][ colIndex ] );
               }
            
            System.out.println();
           }
        
        System.out.println();
       }
    
    /**
     * tests and reports if a character is found in a given string
     * 
     * @param testChar character to be tested against the string
     * 
     * @param testString string within which the character is tested
     * 
     * @return Boolean result of test
     */
    private boolean charInString( char testChar, String testString )
       {
        int index;
       
        for( index = 0; index < testString.length(); index++ )
           {
            if( testChar == testString.charAt( index ) )
               {
                return true;
               }
           }
       
        return false;
       }
   

   }