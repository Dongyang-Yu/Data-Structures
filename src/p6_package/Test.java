package p6_package;

public class Test 
   {

    public static void main(String[] args)
       {
        int size = 7; 
        int value = 5;
        LinkListIteratorClass test = new LinkListIteratorClass();
        
        System.out.println("Inserting after cursor");
        int index;
        for(index = 0; index <size; index++ )
           {
            test.insertAfterCursor( value * index);
            test.displayList();
           }
        
        
        System.out.println("\nInserting before cursor");
        for(index = 0; index < 3; index++)
           {
            test.insertBeforeCursor( value + index * 2);
            test.displayList();
            index++;
            size++;
           }
        
        
        System.out.println("\nSet to last,Moving previous");
        test.setToLastItem();
        
        test.movePrevious();
        test.displayList();
        test.movePrevious();
        test.displayList();
        
        System.out.println("\nRemoving data");
        test.removeDataAtCursor();
        test.displayList();
        size--;

        System.out.println( "\nSet to beginning of list");
        test.setToFirstItem();
        for(index = 0; index < size; index++)
           {
            test.displayList();
            test.moveNext();
           }
        
        System.out.println("\nSetting to end, looping down, removing ");
        
        for(index = 0; index < size; index++)
           {
            test.setToLastItem();
            System.out.println("Removed: " + test.getDataAtCursor());
            test.removeDataAtCursor();
            test.displayList();
           }
        
       }
    
   
   
   
   
   
   }
