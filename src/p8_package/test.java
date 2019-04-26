package p8_package;

public class test 
   {

    public static void main(String[] args) 
       {
        TwoThreeTreeClass test = new TwoThreeTreeClass();
        
        int newVal;
        for( newVal = 35; newVal > 0; newVal -= 3)
           {
            test.addItem(newVal);
            System.out.println(test.inOrderTraversal());
           }
//        System.out.println(test.inOrderTraversal());
        
        
        // test for copying
//        TwoThreeTreeClass copy = new TwoThreeTreeClass(test);
//        System.out.println("\n" + copy.inOrderTraversal());
        
        //test for searching
        int searchVal = 11;
        boolean searchRes = test.search( searchVal );
        System.out.print("search: " + searchVal + "  result: " + searchRes);
       
       }

   }
