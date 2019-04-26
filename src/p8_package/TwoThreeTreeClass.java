package p8_package;

public class TwoThreeTreeClass 
   {
    /**
     * 2-3 node class using NodeDataClass data and three references
     * 
     * @author MichaelL
     */
    private class TwoThreeNodeClass
       {
        /**
         * internal 2-3 node data
         */
        private int leftData, centerData, rightData, numItems;
       
        /**
         * references from 2-3 node
         */
        private TwoThreeNodeClass leftChildRef, centerChildRef, rightChildRef;
       
        /**
         * references for managing 2-3 node adjustments
         */
        private TwoThreeNodeClass auxLeftRef, auxRightRef;
       
        /**
         * parent reference for 2-3 node
         */
        private TwoThreeNodeClass parentRef;
        
        /**
         * Default 2-3 node class constructor
         */
        private TwoThreeNodeClass()
           {
            leftData = centerData = rightData = numItems = 0;
            
            leftChildRef = centerChildRef = rightChildRef = null;
            
            auxLeftRef = auxRightRef = parentRef = null;
           }
        
        /**
         * Initialization 2-3 node class constructor
         * 
         * @param centerIn integer data sets first node initialization
         */
        private TwoThreeNodeClass( int centerIn )
           {
            centerData = centerIn;

            leftData = rightData = 0;
           
            numItems = 1;
           
            leftChildRef = centerChildRef = rightChildRef = null;
           
            auxLeftRef = auxRightRef = parentRef = null;
           }

        /**
         * Private constructor used to create new left or right child node
         * of given parent with the children linked from
         * a current three-node object
         *
         * @param childSelect - integer selection value that informs 
         * the constructor to create a left or a right child
         * along with making all the sub-child links; 
         * uses class constants LEFT_CHILD_SELECT and RIGHT_CHILD_SELECT
         * 
         * @param localRef TwoThreeNodeClass reference
         * with three-node data and associated references
         * 
         * @param parRef TwoThreeNodeclass parent reference
         * for linking with new left or right child node that is created
         */
        private TwoThreeNodeClass( int childSelect, 
                       TwoThreeNodeClass localRef, TwoThreeNodeClass parRef )
           {
            if( childSelect == LEFT_CHILD_SELECT )
               {
                this.centerData = localRef.leftData;
                this.leftChildRef = localRef.leftChildRef;
                this.rightChildRef = localRef.auxLeftRef;
                
                if( leftChildRef != null )
                   {
                    this.leftChildRef.parentRef = this;
                    this.rightChildRef.parentRef = this;
                   }
               }
            
            else  // assume right child select
               {
                this.centerData = localRef.rightData;
                this.leftChildRef = localRef.auxRightRef;
                this.rightChildRef = localRef.rightChildRef; 

                if( rightChildRef != null )
                   {
                    this.leftChildRef.parentRef = this;
                    this.rightChildRef.parentRef = this;
                   }
               }

            this.leftData = this.rightData = 0;
            this.numItems = 1;
            this.centerChildRef = null;
            this.auxLeftRef = this.auxRightRef = null;
            this.parentRef = parRef;
           }
       
        /**
         * Copy 2-3 node class constructor
         * <p>
         * Note: Only copies data; does not copy links 
         * as these would be incorrect for the new tree 
         * (i.e., they would be related to the copied tree)
         * 
         * @param copied TwoThreeNodeClass object to be copied
         */
        private TwoThreeNodeClass( TwoThreeNodeClass copied )
           {
            leftData = copied.leftData;
            centerData = copied.centerData;
            rightData = copied.rightData;
            
            numItems = copied.numItems;
 
            leftChildRef = centerChildRef = rightChildRef = null;
             
            auxLeftRef = auxRightRef = parentRef = null;
           }
       }
     
    /**
     * constant - identify one data item stored
     */
    private final int ONE_DATA_ITEM = 1;
    
    /**
     * constant - identify one data item stored
     */
    private final int TWO_DATA_ITEMS = 2;

    /**
     * constant - identify one data item stored
     */
    private final int THREE_DATA_ITEMS = 3;
    
    /**
     * constant used in constructor to indicate which child to create - Left
     */
    private final int LEFT_CHILD_SELECT = 101;
   
    /**
     * constant used in constructor to indicate which child to create - Right
     */
    private final int RIGHT_CHILD_SELECT = 102;

    /**
     * Used for acquiring ordered tree visitations in String form
     */
    private String outputString;

    /**
     * root of tree
     */
    private TwoThreeNodeClass root;

    /**
     * Default - 2-3 tree constructor
     */
    public TwoThreeTreeClass()
       {
        root = null;
        outputString = "";
       }
   
    /**
     * Copy 2-3 tree constructor
     * 
     * @param copied - TwoThreeTreeClass object to be duplicated;
     * data is copied,
     * but new nodes and references must be created
     */
    public TwoThreeTreeClass(TwoThreeTreeClass copied)
       {
        outputString = "";
        root = copyConstructorHelper(copied.root);
       }
    
    /**
     * Implements tree duplication effort with recursive method;
     * copies data into newly created nodes 
     * and creates all new references to child nodes
     * @param workingCopiedRef
     * @return
     */
    private TwoThreeNodeClass copyConstructorHelper(
                                          TwoThreeNodeClass workingCopiedRef)
       {
        TwoThreeNodeClass newNode = null;
        
        if(workingCopiedRef != null)
           {
            newNode = new TwoThreeNodeClass( workingCopiedRef );
            
            newNode.leftChildRef = copyConstructorHelper(
                                             workingCopiedRef.leftChildRef);
            if( newNode.leftChildRef != null)
               {
                newNode.leftChildRef.parentRef = newNode;               
               }
            
            newNode.centerChildRef = copyConstructorHelper(
                                             workingCopiedRef.centerChildRef);
            if( newNode.centerChildRef != null)
               {
                newNode.centerChildRef.parentRef = newNode;               
               }
            
            newNode.rightChildRef = copyConstructorHelper(
                                             workingCopiedRef.rightChildRef);
            if( newNode.rightChildRef != null )
               {
                newNode.rightChildRef.parentRef = newNode;               
               }
            
           }
        return newNode;
       }
   
    
    /**
     * Adds item to 2-3 tree using addItemHelper as needed
     * 
     * @param itemVal - integer value to be added to the tree
     */
    public void addItem(int itemVal)
       {
        if(root == null)
           {
            root = new TwoThreeNodeClass(itemVal);
           }
        else
           {
            addItemHelper( root, root, itemVal);           
           }
       }
    
    /**
     * Helper method searches from top of tree to bottom using divide and conquer strategy 
     * to find correct location (node) for new added value;
     * 
     * once location is found, item is added to node using addAndOrganizeData 
     * and then fixUpInsert is called in case the updated node has become a three-value node
     * 
     * @param parRef - reference to the parent of the current reference at a given point in the recursion process
     * 
     * @param localRef - reference to the current item at the same given point in the recursion process
     * 
     * @param itemVal - integer value to be added to the tree
     */
    private void addItemHelper(TwoThreeNodeClass parRef,
                               TwoThreeNodeClass localRef,
                               int itemVal)
       {
        // check for one item and not bottom
        if( localRef.numItems == ONE_DATA_ITEM 
                                         && localRef.leftChildRef != null)
           {
            if( itemVal < localRef.centerData )
               {
                addItemHelper(localRef, localRef.leftChildRef, itemVal);
               }
            else  // item >= centerData
               {
                addItemHelper(localRef, localRef.rightChildRef, itemVal);
               }
           }
        // check for two items and not bottom
        else if(localRef.numItems == TWO_DATA_ITEMS 
                                         && localRef.centerChildRef != null)
           {
            if( itemVal < localRef.leftData )
               {
                addItemHelper(localRef, localRef.leftChildRef, itemVal);
               }
            else if( itemVal > localRef.rightData)
               {
                addItemHelper(localRef, localRef.rightChildRef, itemVal);
               }
            else  // this is the center
               {
                addItemHelper(localRef, localRef.centerChildRef, itemVal);
               }
           }
        else   // this is a leaf, which means at the bottom
           {
            addAndOrganizeData(localRef, itemVal);
            
            fixUpInsert(localRef);
           }
        
       }
    
    /**
     * Method is called when addItemHelper arrives at the bottom of the 2-3 search tree 
     * (i.e., all node's children are null);
     * <p>
     * Assumes one- or two- value nodes and adds one more to the appropriate one resulting
     * in a two- or three- value node
     * 
     * @param localRef - has original node data and contains added value 
     * when method completes;
     * method does not manage any node links
     * 
     * @param itemVal - integer value to be added to 2-3 node
     */
    private void addAndOrganizeData(TwoThreeNodeClass localRef, int itemVal)
       {
        
        if(localRef.numItems == ONE_DATA_ITEM)
           {
            localRef.numItems++;
            
            if(itemVal < localRef.centerData)
               {
                localRef.leftData = itemVal;
                localRef.rightData = localRef.centerData;
               }
            else  //itemVal >= centerData
               {
                localRef.rightData = itemVal;
                localRef.leftData = localRef.centerData;
               }
           }
        // numItems == TWO
        else
           {
            localRef.numItems++;
            
            if( itemVal < localRef.leftData)
               {
                localRef.centerData = localRef.leftData;
                localRef.leftData = itemVal;
               }
            else if( itemVal > localRef.rightData)
               {
                localRef.centerData = localRef.rightData;
                localRef.rightData = itemVal;
               }
            else  // itemVal is between left- and right- Data
               { 
                localRef.centerData = itemVal;
               }
           }

       }
    
    /**
     * Method used to fix tree any time a three-value node has been added to the bottom of the tree;
     * it is always called but decides to act only if it finds a three-value node
     * <p>
     * Resolves current three-value node which may add a value to the node above;
     * if the node above becomes a three-value node, then this is resolved with the next recursive call
     * <p>
     * Recursively climbs from bottom to top of tree resolving any three-value nodes found
     * 
     * @param localRef - reference initially given the currently updated node,
     * then is given parent node recursively each time a three-value node was resolved
     */
    private void fixUpInsert(TwoThreeNodeClass localRef)
       {
        
        TwoThreeNodeClass newNode = null;
        
        if( localRef.numItems == THREE_DATA_ITEMS)
           {
            // check for no parent
            // case: localRef == root
            if(localRef.parentRef == null )  
               {
                newNode = new TwoThreeNodeClass(localRef.centerData);
                
                localRef.parentRef = newNode;
                
                root = newNode;
                
                newNode.leftChildRef = new TwoThreeNodeClass(
                           LEFT_CHILD_SELECT, localRef, newNode);
                newNode.rightChildRef = new TwoThreeNodeClass(
                           RIGHT_CHILD_SELECT, localRef, newNode);
              }
            // check for one item parent
            else if( localRef.parentRef.numItems == ONE_DATA_ITEM)
               {
                localRef.parentRef.numItems++;
               
                //check for localRef to be left child of the parent
                if( localRef == localRef.parentRef.leftChildRef )
                   {
                    localRef.parentRef.rightData = localRef.parentRef.centerData;
                    localRef.parentRef.leftData = localRef.centerData;
                    
                    localRef.parentRef.leftChildRef = new TwoThreeNodeClass(
                            LEFT_CHILD_SELECT, localRef, localRef.parentRef );
                    localRef.parentRef.centerChildRef = new TwoThreeNodeClass(
                            RIGHT_CHILD_SELECT, localRef, localRef.parentRef);
                   }
                else // otherwise, assume localRef is right child of parent
                   {
                    localRef.parentRef.leftData = localRef.parentRef.centerData;
                    localRef.parentRef.rightData = localRef.centerData;
                    
                     localRef.parentRef.centerChildRef = new TwoThreeNodeClass(
                             LEFT_CHILD_SELECT, localRef, localRef.parentRef);
                     localRef.parentRef.rightChildRef = new TwoThreeNodeClass(  
                             RIGHT_CHILD_SELECT, localRef, localRef.parentRef);
                    }
              
               }
            // assume a two item parent
            else
               {
                localRef.parentRef.numItems++;
               
                // check for being left child of parent
                if( localRef == localRef.parentRef.leftChildRef )
                   {
                    localRef.parentRef.centerData
                                         = localRef.parentRef.leftData;
                    localRef.parentRef.leftData = localRef.centerData;
                   
                    //set parentRef's auxRightChild to parent's centerChild
                    localRef.parentRef.auxRightRef
                                         = localRef.parentRef.centerChildRef;
                   
                    // set parentRef's leftChild to a new - LEFT -
                    localRef.parentRef.leftChildRef = new TwoThreeNodeClass(
                             LEFT_CHILD_SELECT, localRef, localRef.parentRef);
                   
                    // set parentRef's auxLeftChild to constructor - Right -
                    localRef.parentRef.auxLeftRef = new TwoThreeNodeClass(
                             RIGHT_CHILD_SELECT, localRef, localRef.parentRef);
                   }
               
                // check for being right child of parent
                else if( localRef == localRef.parentRef.rightChildRef)
                   {
                    localRef.parentRef.centerData
                                          = localRef.parentRef.rightData;
                    localRef.parentRef.rightData = localRef.centerData;
                   
                    localRef.parentRef.auxLeftRef
                                          = localRef.parentRef.centerChildRef;
                    
                    localRef.parentRef.auxRightRef = new TwoThreeNodeClass(
                             LEFT_CHILD_SELECT, localRef, localRef.parentRef );
                    localRef.parentRef.rightChildRef = new TwoThreeNodeClass(
                             RIGHT_CHILD_SELECT, localRef, localRef.parentRef );
                   }
               
                // otherwise, assume localRef is center child of parent 
                else
                   {
                    localRef.parentRef.centerData = localRef.centerData;
                    localRef.parentRef.auxLeftRef = new TwoThreeNodeClass(
                             LEFT_CHILD_SELECT, localRef, localRef.parentRef);
                    localRef.parentRef.auxRightRef = new TwoThreeNodeClass(
                             RIGHT_CHILD_SELECT, localRef, localRef.parentRef);
                   }
               
               }
           
            fixUpInsert(localRef.parentRef);
        
           }
       }
    
    /**
     * clears tree so that new items can be added again
     */
    public void clear()
       {
        root = null;
        outputString = "";
       }

    
    /**
     * Tests center value if single node, tests left and right values if two-value node;
     * returns true if specified data is found in any given node 
     * <p>
     * Note: Method does not use any branching operations such as if/else/etc.
     * 
     * @param localRef - reference to node with one or two data items in it
     * 
     * @param searchVal - integer value to be found in given node
     * 
     * @return boolean result of test
     */
    private boolean foundInNode(TwoThreeNodeClass localRef, int searchVal)
       {
        return ( searchVal == localRef.centerData
        || searchVal == localRef.leftData || searchVal == localRef.rightData );
       }
    
    /**
     * Public method called by user to display data in order
     * 
     * @return String result to be displayed and/or analyzed
     */
    public String inOrderTraversal()
       {
        outputString = "";
        if( root != null )
           {
            inOrderTraversalHelper(root);
           }
        else
           {
            outputString += "The 2-3 Tree is empty.";
           }
        return outputString;
       }
    
    /**
     * Helper method conducts in order traversal with 2-3 tree
     * <p>
     * Shows location of each value in a node: "C" at center of single node "L" or "R" at left or right of two-value node
     * 
     * @param localRef - reference to current location at any given point in the recursion process
     */
    private void inOrderTraversalHelper(TwoThreeNodeClass localRef)
       {
        if(localRef != null)
           {
            if( localRef.numItems == ONE_DATA_ITEM )
               {
                inOrderTraversalHelper(localRef.leftChildRef);
                outputString += "C" + localRef.centerData + " ";
                inOrderTraversalHelper(localRef.rightChildRef);
               }
            else if( localRef.numItems == TWO_DATA_ITEMS )
               {
                inOrderTraversalHelper( localRef.leftChildRef );
                outputString += "L" + localRef.leftData + " ";
                
                inOrderTraversalHelper( localRef.centerChildRef );
                
                outputString += "R" + localRef.rightData + " ";
                inOrderTraversalHelper( localRef.rightChildRef );
               }
           }
       }
    
    /**
     * 
     * Search method used by programmer to find specified item in 2-3 tree
     * 
     * @param searchVal - integer value to be found
     * 
     * @return boolean result of search effort
     */
    public boolean search(int searchVal)
       {
        return searchHelper(root, searchVal);
       }
    
    /**
     * 
     * Search helper method that traverses through tree in a recursive divide 
     * and conquer search fashion to find given integer in 2-3 tree
     * 
     * @param localRef - reference to given node at any point during the recursive process
     * 
     * @param searchVal - integer value to be found in tree
     *
     * @return boolean result of search effort
     */
    private boolean searchHelper(TwoThreeNodeClass localRef,
                                 int searchVal)
       {
        if( localRef == null )
           {
            return false;
           }
        
        if( foundInNode(localRef, searchVal) )
           {
            return true;
           }
        else
           {
            // check for one node
            if( localRef.numItems == ONE_DATA_ITEM )
               {
                if(searchVal < localRef.centerData)
                   {
                    return searchHelper(localRef.leftChildRef, searchVal);
                   }
                else if(searchVal > localRef.centerData)
                   {
                    return searchHelper(localRef.rightChildRef, searchVal);
                   }
               }
            else if( localRef.numItems == TWO_DATA_ITEMS)
               {
                if(searchVal < localRef.leftData)
                   {
                    return searchHelper(localRef.leftChildRef, searchVal);
                   }
                else if(searchVal > localRef.rightData)
                   {
                    return searchHelper(localRef.rightChildRef, searchVal);
                   }
                else 
                   {
                    return searchHelper(localRef.centerChildRef, searchVal);
                   }
               } 
           }
        return false;
       }
//        if(localRef != null)
//           {
//            //check for one Node and not bottom
//            if(localRef.numItems == ONE_DATA_ITEM && localRef.leftChildRef != null )
//               {
//                if(searchVal < localRef.centerData)
//                   {
//                    return searchHelper(localRef.leftChildRef, searchVal);
//                   }
//                else if(searchVal > localRef.centerData)
//                   {
//                    return searchHelper(localRef.rightChildRef, searchVal);
//                   }
//                return true;  // they are equal.
//               }
//            // check for two Node and not bottom
//            else if(localRef.numItems == TWO_DATA_ITEMS &&  localRef.leftChildRef != null )
//               {
//                if(searchVal < localRef.leftData)
//                   {
//                    return searchHelper(localRef.leftChildRef, searchVal);
//                   }
//                else if(searchVal > localRef.rightData)
//                   {
//                    return searchHelper(localRef.rightChildRef, searchVal);
//                   }
//                else if(searchVal > localRef.leftData 
//                                  && searchVal < localRef.rightData)
//                   {
//                    return searchHelper(localRef.centerChildRef, searchVal);
//                   }
//                return true;  //searchVal == leftData or rightData
//               }
//            
//            //this is a leaf
//            return foundInNode(localRef, searchVal);
//            
//           }
//        return false;
//       }
   
   }
