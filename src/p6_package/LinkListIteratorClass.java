package p6_package;

public class LinkListIteratorClass
   {
    /**
     *
     * Nested Class to create node class
     */
    private class NodeClass
       {
        int nodeData;
    
        NodeClass nextRef;
    
        private NodeClass()
           {
            nodeData = 0;
            nextRef = null;
           }
    
        private NodeClass( int newData )
           {
            nodeData = newData;
            nextRef = null;
           }
       }
    
    /**
     * constant -999999 for access failure messaging
     */
    public static final int FAILED_ACCESS = -999999;

    /**
     * reference to head node
     */
    private NodeClass headRef;  
    
    /**
     * reference to current/cursor node
     */
    private NodeClass cursorRef;
    
    /**
     * Default constructor
     */
    public LinkListIteratorClass()
       {
        headRef = null;
        cursorRef = null;
       }
    
    /**
     * Copy instructor
     */
    public LinkListIteratorClass(LinkListIteratorClass copied) 
       {
//        headRef = copied.headRef;  //this is wrong
//        cursorRef = copied.cursorRef;   //they use the same node
        if( copied.headRef == null )
           {
            headRef = cursorRef = null;
           }
        else
           {
            this.headRef = new NodeClass(copied.headRef.nodeData);
           
            NodeClass lclWorkingRef = this.headRef;
           
            NodeClass cpWorkingRef = copied.headRef;
           
            if( copied.cursorRef == copied.headRef )
               {
                this.cursorRef = lclWorkingRef;
               }
           
            cpWorkingRef = cpWorkingRef.nextRef;
           
            while( cpWorkingRef != null)
               {
              
                lclWorkingRef.nextRef = new NodeClass( cpWorkingRef.nodeData );
              
                lclWorkingRef = lclWorkingRef.nextRef; //lclWorkingRef point to next node
              
                if( copied.cursorRef == cpWorkingRef )
                 {
                  this.cursorRef = lclWorkingRef;
                 }   
              
                cpWorkingRef = cpWorkingRef.nextRef;
               }
           }
       }
    
    /**
     * Reports list empty status
     * 
     * @return Boolean evidence of empty list
     */
    public boolean isEmpty()
       {
        return headRef == null;
       }
    
    
    /**
     * Clears list
     */
    public void clear()
       {
        headRef = null;
        cursorRef = null;
       }
    
    /**
     * Acquires data at cursor
     * 
     * @return integer value at cursor location if available, FAILED_ACCESS otherwise
     */
    public int getDataAtCursor()
       {
        if(cursorRef != null)
           {
            return cursorRef.nodeData;
           }
        return FAILED_ACCESS;
       }
    
    /**
     * Checks for at last item in list
     * 
     * @return Boolean result of test
     */
    public boolean isAtEndOfList()
       {
        return (cursorRef.nextRef == null);
       }
    
    /**
     * Moves cursor to next node if it is not at end
     */
    public void moveNext()
       {
        if( !isAtEndOfList() )
           {
            cursorRef = cursorRef.nextRef;
           }
       }
    
    /**
     * Moves cursor to previous node if it is not already at head
     */
    public void movePrevious()
       {
        if( cursorRef != headRef )
           {
            cursorRef = getRefBeforeCursor(headRef);
           }
       }
    
    
    /**
     * Inserts value after cursor
     * 
     * @param newValue- Value to be inserted in list
     */
    public void insertAfterCursor(int newValue)
       {
        if( headRef != null )
           {
            NodeClass tempNode = new NodeClass(newValue);
            
            tempNode.nextRef = cursorRef.nextRef;
            
            cursorRef.nextRef = tempNode;
            
           }
        else
           {
            headRef = new NodeClass(newValue);
            
            cursorRef = headRef;
           }
        
       }
    
    
    /**
     * Inserts value prior to cursor
     * 
     * @param newRef - record current position of cursor 
     * 
     * @param newValue - Value to be inserted in list
     */
    public void insertBeforeCursor(int newValue)
       {
        NodeClass priorRef;
        NodeClass newNode = new NodeClass( newValue );
        if( isEmpty() )
           {
            headRef = newNode;
            
            cursorRef = headRef;
           }
        else if( cursorRef == headRef )
           {
            newNode.nextRef = headRef;
           
            headRef= newNode;
           }
        else
           {
            priorRef = getRefBeforeCursor( headRef );
                  
            priorRef.nextRef = newNode;
            
            newNode.nextRef = cursorRef;
            
           }
       }
    
    
    /**
     * Removes item at current location/cursor if available
     * <p>
     * Sets cursor to previous node unless cursor is at head
     * 
     * @return integer value removed if available, FAILED_ACCESS otherwise
     */
    public int removeDataAtCursor()
       {
        int value;
        NodeClass tempNode;
        if( isEmpty() ) // don't forget !!!!
           {
            value = FAILED_ACCESS;
           }
        else if( cursorRef == headRef )
           {
            value = cursorRef.nodeData;
            headRef = headRef.nextRef;
            cursorRef = headRef;
           }
        else
           {
            value = cursorRef.nodeData;
            
            //record  nextRef of cursorRef
            tempNode = cursorRef.nextRef;
            
            movePrevious();
            cursorRef.nextRef = tempNode;
           }
        return value;
       
       }
    
    /**
     * Sets cursor to first item in list
     */
    public void setToFirstItem()
       {
        if( !isEmpty() )
           {
            cursorRef = headRef;          
           }
       }

    /**
     * Sets cursor to last item in list
     */
    public void setToLastItem()
       {
        if( !isEmpty() )
           {
            while( cursorRef.nextRef != null )
               {
                moveNext();
               }
           }
       }
    
    /**
     * Recursive method finds a reference to the node just prior to the cursor;
     * initially called with head reference
     * 
     * @param workingRef - current NodeClass reference in the list
     * 
     * @return NodeClass reference to the item just prior to the cursor location
     */
    private NodeClass getRefBeforeCursor(NodeClass workingRef)
       {
        if( workingRef == null || cursorRef == headRef )
           {
            return null;
           }
        if( workingRef.nextRef != cursorRef )
           {
            return getRefBeforeCursor( workingRef.nextRef );
           }
        return workingRef;
       }
    
    /**
     * Displays linked list for diagnostic purposes
     */
    public void displayList()
       {
        NodeClass index = headRef;
        
        System.out.print("Data Display: ");
        
        if(index == null)
           {
            System.out.print("Empty List\n");
           }
        
        while(index != null)
           {
            if(index == cursorRef)
               {
                System.out.print("[");
               }
            
            System.out.print( index.nodeData);
            
            if(index == cursorRef)
               {
               System.out.print("]");
               }
            
            System.out.print(' ');
            
            index = index.nextRef;
           }
        System.out.println();
           
       }
    
    
    
   }
