package p10_package;

public class LinkedHashClass 
   {
    /**
     * local node class for holding student data and next reference link
     */
    private class LinkedNodeClass
       {
        SimpleStudentClass data;
        
        LinkedNodeClass nextRef;
        
        /**
         * Constructor 
         */
        private LinkedNodeClass( SimpleStudentClass inData )
           {
            data = new SimpleStudentClass( inData );
            nextRef = null;
           }
       }
    
    /**
     * Table size default
     * <p>
     * Table size(capacity) is recommended to be a prime number
     */
    private final int DEFAULT_TABLE_SIZE = 11;
    
    /**
     * Size of the base table
     */
    private int tableSize;
    
    /**
     * Constant used to control access operation
     */
    private final boolean REMOVE = true;
    
    /**
     * Constant used to control access operation
     */
    private final boolean SEARCH = false;
    
    /**
     * Array for hash table
     */
    private LinkedNodeClass[] tableArray;
    
    /**
     * Default constructor
     */
    public LinkedHashClass()
       {
        int index;
        tableSize = DEFAULT_TABLE_SIZE;
        tableArray = new LinkedNodeClass[ tableSize ];
        for( index =0; index < tableSize; index++ )
           {
            tableArray[ index ] = null;
           }
       }
    
    /**
     * Initialization constructor
     * 
     * @param inTableSize - sets table size
     */
    public LinkedHashClass( int inTableSize )
       {
        int index;
        tableSize = inTableSize;
        tableArray = new LinkedNodeClass[ tableSize ];
        
        // not for java, just for program!
        for( index = 0; index < tableSize; index++ )
        {
         tableArray[ index ] = null;
        }
       }
    
    /**
     * Copy constructor
     * 
     * @param copied - LinkedHashClass object to be copied
     */
    public LinkedHashClass( LinkedHashClass copied )
       {
        int index;
        LinkedNodeClass lclWkgRef, cpdWkgRef;
        
        tableSize = copied.tableSize;
        
        tableArray = new LinkedNodeClass[ tableSize ];
        
        for( index = 0; index < tableSize; index++ )
           {
            cpdWkgRef = copied.tableArray[ index ];
            
            if( cpdWkgRef != null )
               {
                lclWkgRef = new LinkedNodeClass( cpdWkgRef.data );
                
                tableArray[ index ] = lclWkgRef;
                cpdWkgRef = cpdWkgRef.nextRef;
                
                while( cpdWkgRef != null )
                   {
                    
                    lclWkgRef.nextRef = new LinkedNodeClass( cpdWkgRef.data );
                    
                    lclWkgRef = lclWkgRef.nextRef;
                    
                    cpdWkgRef = cpdWkgRef.nextRef;
                   }
               }
            else
               {
                tableArray[ index ] = null;
               }
           }
       
       
       
       }
    
    /**
     * Helper method that handles both searching and removing items
     * in linked list at specified index
     * 
     * @param linkIndex - integer index specifying location in array
     * 
     * @param studentID - integer key for searching and/or removing node
     * 
     * @param removeFlag - boolean flag that indicates whether to
     *  search or remove (use SEARCH, REMOVE constants to call this method)
     *   
     * @return SimpleStudentClass data found and/or removed
     */
    private SimpleStudentClass accessLinkedData(int linkIndex,
                                                int studentID,
                                                boolean removeFlag)
       {
        SimpleStudentClass tempVal = null;
        LinkedNodeClass wkgRef = tableArray[ linkIndex ];
        
        if( wkgRef != null )
           {
            if( wkgRef.data.studentID == studentID )
               {
                tempVal = wkgRef.data;
                
                if( removeFlag )
                   {
                    tableArray[ linkIndex ] = wkgRef.nextRef;
                   }
               }
            else
               {
                while( wkgRef.nextRef != null
                      && wkgRef.nextRef.data.studentID != studentID )
                   {
                    wkgRef = wkgRef.nextRef;
                   }
                if( wkgRef.nextRef != null )
                   {
                    tempVal = wkgRef.nextRef.data;
                    if( removeFlag )
                       {
                        wkgRef.nextRef = wkgRef.nextRef.nextRef;
                       }
                   }
               }
           }
        return tempVal;
       }
    
    /**
     * Add items to hash table
     * <p>
     * Uses overloaded addItem with object
     * 
     * @return Boolean success of operation
     */
    public boolean addItem(String inName,
                           int inStudentID,
                           char inGender,
                           double inGPA)
       {
        SimpleStudentClass newItem = new SimpleStudentClass
                          ( inName, inStudentID, inGender, inGPA );
        
        addItem( newItem );
        
        return true;
       }
    
    /**
     * Adds item to hash table
     * <p>
     * Overloaded method that accepts SimpleStudentClass object
     * 
     * @param newItem - student class object
     * 
     * @return boolean success of operation
     */
    public boolean addItem(SimpleStudentClass newItem)
       {
        int listIndex = generateHash( newItem );
        
        appendToList( listIndex, newItem );
        
        return true;
       }
    
    /**
     * Appends new data to end of list at given list index;
     * handles empty node at that index as needed
     * 
     * @param listIndex - specified integer index of array
     * 
     * @param newNode - node to be appended to array/list
     */
    private void appendToList(int listIndex,
                              SimpleStudentClass newNode)
       {
        LinkedNodeClass workingRef;
        // when head is null
        if( tableArray[ listIndex ] == null )
           {
            tableArray[ listIndex ] = new LinkedNodeClass( newNode );
           }
        else
           {
            workingRef = tableArray[ listIndex ];
            
            while( workingRef.nextRef != null)  //用nextRef 才能连接
            {
               workingRef = workingRef.nextRef;
            }
           
            workingRef.nextRef = new LinkedNodeClass( newNode );
           }
        
       }
    
    /**
     * Clears hash table by clearing all linked list elements
     */
    public void clearHashTable()
       {
        int index;
        for( index = 0; index < tableSize; index++ )
           {
            tableArray[ index ] = null;
           }
       }

    /**
     * Method recursively counts number of nodes in a given linked list
     * 
     * @param workingRef - reference used for recursion;
     * initially set to head
     * 
     * @return integer number of nodes found
     */
    private int countNodes(LinkedNodeClass workingRef)
       {
        if( workingRef != null )
           {
            return 1 + countNodes( workingRef.nextRef );
           }
        
        return 0;
       }

    /**
     * Searched for item in hash table using studentID as key
     * <p>
     *  use accessLinkedData
     * @return object removed, or null if not found
     */
    public SimpleStudentClass findItem(int studentID)
       {
        int index;
        SimpleStudentClass temp = new SimpleStudentClass("", studentID, 'X', 0.00);
        
        index = generateHash( temp );
        
        return accessLinkedData( index, studentID, SEARCH );
       }
    
    /**
     * Method uses student ID to generate hash value for use as index in hash
     * 
     * @param studentData Object from which hash value will be generated
     * 
     * @return hash value to be used as array index
     */
    public int generateHash(SimpleStudentClass studentData)
       {
        int value = studentData.studentID;
        int sum = 0;
        while( value > 0 )
           { 
            // get digit ( help understand )
//            char charVal;
//            charVal = (char) ( '0' + value % 10 );
//            sum += ( int ) charVal;
             
            sum += '0' + value % 10;
            value /= 10;
           }
        
        return sum % tableSize;
       }
    
    /**
     * Removes item from hash table, using student ID as key
     * <p>
     * uses accessLinkedData()
     * @param studentID - used for requesting data
     * 
     * @return object removed, or null if not found
     */
    public SimpleStudentClass removeItem(int studentID)
       {
        int index;
        SimpleStudentClass temp = new SimpleStudentClass("", studentID, 'X', 0.00);
        index = generateHash( temp );
        
        return accessLinkedData(index, studentID, REMOVE);
       }
    
    /**
     * traverses through all array bins, finds lengths of each linked list,
     * then displays at table
     * <p>
     * Shows table of list lengths, then shows table size,
     * then shows the number of empty bins 
     * and the longest linked list of the set; adapts to any size array
     */
    public void showHashTableStatus()
       { 
        int index = 0;
        int count = 0;
        int emptyNum = 0;
        int maxNum = 0;
        
        System.out.print("Array node report: \n Index: ");
        for( index = 0; index < tableSize; index++ )
           {
            System.out.format("%6d ", index);
           }
        
        System.out.print("\n         ");
        
        for( index = 0; index < tableSize; index++ )
           {
            System.out.print("  -----");
           }
        System.out.print("\n         ");
        for( index = 0; index < tableSize; index++ )
           {
           
            count = countNodes( tableArray[index] );
            System.out.format("%6d ", count);
            
            //calculate the number of empty bins
            if( count == 0 )
               {
                emptyNum++;
               }
            
            //calculate the longest linked node list
            if( count > maxNum )
               {
                maxNum = count;
               }
            
           }
        System.out.println("\n\nWith a table size of " + tableSize);
        System.out.println("The number of empty bins was " + emptyNum 
              +", and the longest linked node list was "+ maxNum + " nodes.\n");
        
       }
   }
