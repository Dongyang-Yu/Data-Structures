package p2_package;

public class IntDataClass extends java.lang.Object  implements java.lang.Comparable<IntDataClass> 
   {
    /**
     * data storage
     */
    private int dataVal;
    
    /**
     * Default constructor
     */
    public IntDataClass()
       {
        dataVal = 0;
       }
    
    /**
     * Initialization constructor
     * 
     * @param initialValue - integer value to be loaded into object
     */
    public IntDataClass(int initialValue)
       {
        dataVal = initialValue;
       }
    
    /**
     * Copy constructor
     * 
     * @param copied - IntDataClass object to be copied into THIS object
     */
    public IntDataClass(IntDataClass copied)
       {
        this.dataVal = copied.dataVal;
       }
    
    /**
     * Setter method to update data in object
     * 
     * @param newValue - integer value to be set in object
     */
    public void setValue(int newValue)
       {
        dataVal = newValue;
       }
    
    /**
     * Getter method to retrieve data from object
     * 
     * @return integer value retrieved from object
     */
    public int getValue()
       {
        return dataVal;
       }
    
    /**
     * Overrides toString method
     * 
     * @override toString in class java.lang.Object
     * 
     * @return String output of integer data value
     */
    public java.lang.String toString()
       {
        return dataVal + "";
       }
   
    /**
     * Method required of class extending Comparable; used by generic classes for management
     * 
     * @specify compareTo in interface java.lang.Comparable<IntDataClass>
     */
    public int compareTo(IntDataClass other)
       {
        if( this.dataVal > other.dataVal)
           {
            return 1;
           }
        else if( this.dataVal == other.dataVal )
           {
            return 0; 
           }
        
        return -1;
       }   
    
   }
