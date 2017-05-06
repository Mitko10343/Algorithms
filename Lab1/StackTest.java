// StackTest.java
// Linked list implementation of Stack
import java.util.Scanner;

class Stack {
    
    class Node {
        int data;
        Node next;  
    }
    private Node top;
      
    public Stack()
    { 
        top = null;
    }
        
    public void push(int x) {
        Node  t = new Node();
        t.data = x;
        t.next = top;
        top = t;
    }

    // only to be called if list is non-empty.
    // Otherwise an exception should be thrown.
    public int pop(){
	   int value=0;
       Node t = top;
	   
	   while(t != null)
	   {
			value = top.data;
			top = top.next;
			return value;
	   }
	   return value;
    } 

    /*
    public boolean isEmpty(){
       do yourself
    }*/


    public void display() {
        Node t = top;
        //Console.Write("\nStack contents are:  ");
        System.out.println("\nStack contents are:  ");
        
        while (t != null) {
            //Console.Write("{0} ", t.data);
            System.out.print(t.data + " ");
            t = t.next;
        }
        //Console.Write("\n");
        System.out.println("\n");
    }
	
	public boolean isMember(int x)
	{
	   while(top != null)
	   {
			if(top.data == x)
			{
				return true;
			}
			top = top.next;
	   }
	   return false;
	}
	
	public int size()
	{
		int size=0;
		while(top != null)
	    {
			size++;
			top =top.next;
	    }
		return size;
	}
}


public class StackTest
{
    public static void main( String[] arg){
		Scanner in = new Scanner(System.in);
        Stack s = new Stack();
		
        
        System.out.println("Stack is created\n");
        
        s.push(10); s.push(3); s.push(11); s.push(7);
        s.display();
        
        int i = s.pop();
		if(i !=0)
		{
			System.out.println("Just popped " + i);
			s.display();
		}
		else
		{
			System.out.println("Stack is empty");
		}
        
		System.out.println("Please enter in a number to check if its in the stack");
		int num = in.nextInt();
		
		boolean isMember =s.isMember(num);
		if(isMember == true)
		{
			System.out.println("This number is in the stack");
		}
		else{
			System.out.println("Number not in Stack");
		}
		
		int size = s.size();
		System.out.println("Size of the stack is:"+size);
    }
}


