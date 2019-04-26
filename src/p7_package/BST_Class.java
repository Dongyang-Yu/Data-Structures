package p7_package;

public class BST_Class
   {
    /**
     * Root of BST
     */
    private StudentClassNode BST_Root;
   
    /**
     * Used for acquiring ordered tree visitations in String form
     */
    private String outputString;
    
    /**
     * Default constructor, initializes BST
     */
    public BST_Class()
       {
        BST_Root = null;
        outputString = "";
       }
    
    /**
     * Copy constructor
     * <p>
     * Note: Uses copyConstHelper
     * 
     * @param copied - BST_Class object to be copied
     */
    public BST_Class(BST_Class copied)
       {
        outputString = "";
        BST_Root = copyConstHelper( copied.BST_Root );
       }
    
    /**
     * Copy constructor helper, recursively adds nodes to duplicate tree
     * 
     * @param copiedRef - StudentClassNode reference for accessing copied object data
     * 
     * @return StudentClassNode reference to node added at current level of recursion
     */
    private StudentClassNode copyConstHelper(StudentClassNode copiedRef)
       {
        StudentClassNode newNode = null;
        
        if( copiedRef != null )
           {
            newNode = new StudentClassNode( copiedRef );        
            newNode.leftChildRef = copyConstHelper(copiedRef.leftChildRef);
            newNode.rightChildRef = copyConstHelper(copiedRef.rightChildRef);
           }

        return newNode;
       }
   
    /**
     * insert method for BST
     * <p>
     * Note:uses insert helper method
     * 
     * @param inData - StudentClassNode data to be added to BST
     */
    
    public void insert(StudentClassNode inData)
       {
        if( BST_Root == null)
           {
            BST_Root = new StudentClassNode( inData );
           }
        else
           {
            insertHelper(BST_Root, inData);          
           }
       }
    
    /**
     * Insert helper method for BST insert action
     * 
     * @param localRoot - tree root reference at current recursion level
     * 
     * @param inData - item to be added    to BST
     * 
     * @param lclParent - record the parent of localRoot 
     */
    
    private void insertHelper(StudentClassNode localRoot,
                              StudentClassNode inData)
       {
        
        if( inData.studentID > localRoot.studentID )
           {
            if( localRoot.rightChildRef == null )
               {
                localRoot.rightChildRef = new StudentClassNode(inData);
               }
            else
               {
                insertHelper(localRoot.rightChildRef, inData);
               }
           }
        // when inData.studentID <= localRoot.studentID 
        else
           {
            if( localRoot.leftChildRef == null )
               {
                localRoot.leftChildRef = new StudentClassNode( inData );
               }
            else
               {
                insertHelper(localRoot.leftChildRef, inData);
               }
           }
       }
    
    
    /**
     * Remove data node from tree using given key
     * <p>
     * Note: uses remove helper method
     * 
     * @param inData - StudentClassnode that includes the necessary key
     * 
     * @return StudentClassNode result of remove action
     */
    public StudentClassNode removeNode(StudentClassNode inData)
       {
        return  removeNodeHelper(BST_Root, inData);
       }
    
    /**
     * Remove helper for BST remove action
     * <p>
     * Note:uses removeFromMin method
     * 
     * @param localRoot - tree root reference at current recursion level
     * 
     * @param outData - item that includes the necessary key
     * 
     * @return StudentClassNode reference result of remove helper action
     */
    private StudentClassNode removeNodeHelper(StudentClassNode localRoot,
                                              StudentClassNode outData)
       {
        StudentClassNode tempNode;

        // check for null
        if( localRoot == null )
           {
            // set to null
            localRoot = null;
           }
        // test for less than
        else if( outData.studentID < localRoot.studentID)
           {
            // recurse left
            localRoot.leftChildRef = removeNodeHelper(localRoot.leftChildRef, outData);
           }
        // test for greater than
        else if( outData.studentID > localRoot.studentID )
           {
            // recurse right
            localRoot.rightChildRef = removeNodeHelper(localRoot.rightChildRef, outData);
           }
        // test for no children
        else if( localRoot.leftChildRef == null
                && localRoot.rightChildRef == null )
           {
            // set to null
            localRoot =null;
           }
        
        // test for no right child
        else if( localRoot.rightChildRef == null )
           {
            // set to left child
            localRoot = localRoot.leftChildRef;
           }
        
        // test for no left child
        else if( localRoot.leftChildRef == null )
           {
            // set to right child
            localRoot = localRoot.rightChildRef;
           }
        
         // test for right child without left child   //此时是有两个孩子的前提
        else if( localRoot.rightChildRef.leftChildRef == null )
           {
            // assign data from right child to local
            localRoot.setStudentClassData( localRoot.rightChildRef );
            
            //assign local right child to right child's right child
            localRoot.rightChildRef = localRoot.rightChildRef.rightChildRef;
            
           }
        //  assume right child has left child   
        else
           {
            // get node from removeFromMin
            tempNode = removeFromMin( localRoot, localRoot.rightChildRef );

           // transfer data from node to local
           localRoot.setStudentClassData( tempNode );
           }
        
        return localRoot;
       }
        
        //found it
//        else
//           {
//            if( localRoot != null )
//               {
//                if( localRoot.leftChildRef == null )
//                   {
//                    localRoot = localRoot.rightChildRef;
//                   }
//                else if( localRoot.rightChildRef == null )
//                   {
//                    localRoot = localRoot.leftChildRef;
//                   }
//                //has two children
//                else
//                   {
//                    // special case
//                    if( localRoot.rightChildRef.leftChildRef == null)
//                       {
//                        localRoot.setStudentClassData( localRoot.rightChildRef);
//                        localRoot.rightChildRef = localRoot.rightChildRef.rightChildRef;
//                       }
//                    else
//                       {
//                        StudentClassNode minNode = removeFromMin(localRoot, localRoot.rightChildRef);
//                        localRoot.setStudentClassData(minNode);
//                       }
//                   }
//               
//               }
//           }
//        return localRoot;
//       }
    
    /**
     * Searches tree from given node to minimum value node below it,
     * stores data value found, and then unlinks the node
     * 
     * @param minParent -  StudentClassNode reference to current node
     * 
     * @param minChild - StudentClassNode reference to child node to be tested
     * 
     * @return StudentClassNode reference containing removed node
     */
    private StudentClassNode removeFromMin(StudentClassNode minParent,
                                           StudentClassNode minChild)
       {
        if(minChild.leftChildRef != null)
           {
            return removeFromMin(minChild, minChild.leftChildRef);
           }
        minParent.leftChildRef = minChild.rightChildRef;   //用它的前提是minCHild左孩子不为空
        
        return minChild;
       }
    
    /**
     * Searches for data in BST given StudentClassNode with necessary key
     * 
     * @param searchData - StudentClassNode item containing key
     * 
     * @return reference to find data
     */
    public StudentClassNode search(StudentClassNode searchData)
       {
        return searchHelper(BST_Root, searchData);
       }
    
    /**
     * Helper method for BST search action
     * 
     * @param localRoot - tree root reference at the current recursion level
     * 
     * @param searchData - item containing key
     * 
     * @return - StudentClassNode item found
     */
    private StudentClassNode searchHelper(StudentClassNode localRoot,
                                          StudentClassNode searchData)
       {
        if(localRoot == null )
           {
            return null;
           }
        
        if(localRoot.studentID > searchData.studentID)
           {
            return searchHelper(localRoot.leftChildRef, searchData);
           }
        else if(localRoot.studentID < searchData.studentID)
           {
            return searchHelper(localRoot.rightChildRef, searchData);
           }
        
        // equal
        return localRoot;
       }
    
    /**
     * Provides preOrder traversal for user as a string
     * 
     * @return String containing pre oreder output
     */
    public java.lang.String outputPreOrder()
       {
        outputString = "";
        if(BST_Root == null)
           {
            outputString += "The BST is empty.";
           }
        else
           {
            outputPreOrderHelper(BST_Root);
           }
        
        return outputString;
       }
    
    /**
     * Provides preOrdere traversal action helper
     * 
     * @param localRoot - tree root reference at the current recursion level
     */
    private void outputPreOrderHelper(StudentClassNode localRoot)
       {
        if( localRoot != null )
           {
            outputString += localRoot.toString() + '\n';
            outputPreOrderHelper(localRoot.leftChildRef);
            outputPreOrderHelper(localRoot.rightChildRef);
           }
       }
       
    /**
     * Provides postOrder traversal for use as a string
     * 
     * @return String containing post order output
     */
    public java.lang.String outputPostOrder()
       {
        outputString = "";
        if(BST_Root == null)
           {
            outputString +="The BST is empty.";
           }
        else
           {
            outputPostOrderHelper(BST_Root);
           }
          
        return outputString;
       }
    
    /**
     * Provides postOrder traversal action helper
     * 
     * @param localRoot - tree root reference at the current recursion level
     */
    private void outputPostOrderHelper(StudentClassNode localRoot)
       {
        if( localRoot != null )
           {
            outputPostOrderHelper(localRoot.leftChildRef);
            outputPostOrderHelper(localRoot.rightChildRef);
            outputString += localRoot.toString() + '\n';
           }
       }
    
    /**
     * Provides inOrder traversal for user as a string
     * 
     * @return String containing in order output
     */
    public java.lang.String outputInOrder()
       {
        outputString = "";
        if( BST_Root == null)
           {
            outputString += "The BST is empty.";
           }
        else
           {
            outputInOrderHelper(BST_Root);
           }
        
        return outputString;
       }
    
    /**
     * Provides inOrder traversal action helper
     * 
     * @param localRoot - tree root reference at the current recursion level
     */
    private void outputInOrderHelper(StudentClassNode localRoot)
       {
        if( localRoot != null )
           {
            outputInOrderHelper(localRoot.leftChildRef);
            outputString += localRoot.toString() + '\n';
            outputInOrderHelper(localRoot.rightChildRef);
           }
       }
    
    /**
     * Clears tree
     */
    public void clearTree()
       {
        BST_Root = null;
     // error: null pointer
//      BST_Root.leftChildRef = BST_Root.rightChildRef = null;
       }
    
    /**
     * Tests for empty tree
     * 
     * @return Boolean result of test
     */
    public boolean isEmpty()
       {
        return BST_Root == null;       
       }
    
//    public void insert(StudentClassNode inData)
//    {
//     BST_Root = insertHelper(BST_Root, inData);
//    }
//
//    private StudentClassNode insertHelper(StudentClassNode localRoot,
//                              StudentClassNode inData)
//       {
//        if( localRoot == null)
//           {
//            localRoot = new StudentClassNode(inData);
//            return localRoot;
//           }
//        
//        if(inData.studentID < localRoot.studentID)
//           {
//            localRoot.leftChildRef = insertHelper(localRoot.leftChildRef, inData );
//           }
//        else if(inData.studentID > localRoot.studentID )
//           {
//            localRoot.rightChildRef = insertHelper(localRoot.rightChildRef, inData);
//           }
//        return localRoot;
//       }
    
   }
