package p6_package;

public class LinkListQueueClass
   {
    /**
     * Nested Class to create node class
     */
    private class NodeClass
       {
        /**
         * Data for this node
         */
        int nodeData;
        
        /**
         * Pointer to next node in list
         */
        NodeClass nextRef;
        
        /**
         * Default constructor
         */
        private NodeClass()
           {
            nodeData = 0;
            nextRef = null;
           }
        
        /**
         * Initialization constructor
         */
        private NodeClass(int newData)
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
     * Stores queue head reference
     */
    private NodeClass headRef;
    
    /**
     * Stores queue tail reference
     */
    private NodeClass tailRef;
    
    /**
     * Default constructor
     */
    public LinkListQueueClass()
       {
        headRef = null;
        tailRef = null;
       }
    
    /**
     * Copy constructor
     */
    public LinkListQueueClass(LinkListQueueClass copied)
       {
//绝对的错误，这样只会把copied的结点连接到当前queue里
//        tailRef = copied.tailRef;
//        headRef = copied.headRef;
       
/*        NodeClass cpWorkingRef = copied.headRef;
        
        NodeClass lclWorkingRef = new NodeClass( cpWorkingRef.nodeData );
        
        cpWorkingRef = cpWorkingRef.nextRef;
        
//     mistake:   if( cpWorkingRef != null )
        while( cpWorkingRef != null )
           {
            NodeClass newNode = new NodeClass( cpWorkingRef.nodeData );
            lclWorkingRef.nextRef = newNode;
            
            lclWorkingRef = lclWorkingRef.nextRef;
            
            cpWorkingRef = cpWorkingRef.nextRef;
            
           }
        this.tailRef = lclWorkingRef;*/
       NodeClass wkgRef, cpdWkgRef;
       if( copied.headRef == null )
          {
           headRef = tailRef = null;
          }
       else
          {
           headRef = new NodeClass(copied.headRef.nodeData);
           wkgRef = headRef;
           cpdWkgRef = copied.headRef.nextRef;
           
           while( cpdWkgRef != null )
              {
               wkgRef.nextRef = new NodeClass( cpdWkgRef.nodeData );
               
               wkgRef = wkgRef.nextRef;
               
               cpdWkgRef = cpdWkgRef.nextRef;
              }
           tailRef = wkgRef;
          }
    
       }
    
    
    /**
     * Clears the queue
     */
    public void clear()
       {
        headRef = tailRef = null;
       }
    
    /**
     * Reports queue empty state
     * 
     * @return Boolean evidence of empty list
     */
    public boolean isEmpty()
       {
        return headRef == null;
       }
    
    /**
     * Appends value to end of queue
     * 
     * @param newValue - Value to be enqueued
     */
    public void enqueue(int newValue)
       {
        NodeClass tempNode = new NodeClass( newValue );
        
        if( headRef == null )
           {
            headRef = tailRef = tempNode;
           }
        else   
           {
            tailRef.nextRef = tempNode;
            tailRef = tempNode;
           }
       }
    
    /**
     * Removes and returns value from front of queue
     * 
     * @return Value if successful, FAILED_ACCESS if not
     */
    public int dequeue()
       {
        int value;
        if( isEmpty() )
           {
            return FAILED_ACCESS;
           }
        
        value = headRef.nodeData;
        
        headRef = headRef.nextRef;

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
        return headRef.nodeData;
       }
   }
