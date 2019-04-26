package p7_package;

public class test {
   
   public static void main(String[] args )
      {
       BST_Class testTree = new BST_Class();
       StudentClassNode stu1= new StudentClassNode( "Yu", 445006, 'F', 4.0 ) ;
       StudentClassNode stu2= new StudentClassNode( "Dong", 11006, 'F', 4.0 ) ;
       StudentClassNode stu3= new StudentClassNode( "Yang", 33, 'F', 4.0 ) ;
       
       testTree.insert( stu1 );
       testTree.insert( stu2 );
       testTree.insert( stu3 );
       
       System.out.println(testTree.outputInOrder());
       
      }
      
   
}
