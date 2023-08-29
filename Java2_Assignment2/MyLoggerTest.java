/* Assignment 2 
 * CST7284_300
 * Java Object Oriented Programming
 * Student: Kowiy Gidado
 * Professor: Amandeep Kahlon
 * Due Date: June 30, 2023
 * MyLoggerTest.java
*/

public class MyLoggerTest {
    public static void main(String[] args) {
    	// Initialize MyLogger class appropriately 
        MyLogger logger = new MyLogger(System.out);
     // Use System.setOut method to set your output stream appropriately 
        System.setOut(logger);

        int x = 15;
        System.out.println(x);
        boolean b = true;
        System.out.println(b);
        char c = 'c';
        System.out.println(c);
        char[] s = {'a', 'b', 'c', 'd', 'e'};
        System.out.println(s);
        double d = 34.5;
        System.out.println(d);
        float f = 45.4f;
        System.out.println(f);
        long l = 12345678;
        System.out.println(l);
        Object obj = new Object();
        System.out.println(obj);
        System.out.println("My Name is Michel");
    }
}
