// QueueTest.java
// Allocation of Queue objects in main()
import java.util.Scanner;

class Queue {

    private class Node {
        int data;
        Node next;
    }

    Node z;
    Node head;
    Node tail;

    public Queue() {
        z = new Node(); z.next = z;
        head = z; 
		tail = null;
    }


  public void display() {
    System.out.println("\nThe queue values are:\n");

    Node temp = head;
    while( temp != temp.next) {
        System.out.print( temp.data + "  ");
        temp = temp.next;
    }
    System.out.println("\n");
  }


  public void enQueue( int x) {
    Node temp;

    temp = new Node();
    temp.data = x;
    temp.next = z;

    if(head == z)    // case of empty list
        head = temp;
    else                // case of list not empty
        tail.next = temp;

    tail = temp;        // new node is now at the tail
  }


  // assume the queue is non-empty when this method is called
  public int deQueue() {
	int value=0;;
	
	if(head != null)
	{
		Node temp = head;
		
		value = temp.data;
		head = head.next;
		return value;
	}
	return value;
  }


  public boolean isEmpty() {
	if( head == null)
	{
        return true;
    }
	else
	{
		return false;
	}
  }
  
  public boolean isMember(int x)
  {
	Node temp = head;
	
	while(temp != temp.next)
	{
		if(temp.data == x)
		{
			return true;
		}
		temp=temp.next;
	}
	
	return false;
  }

} // end of Queue class



class QueueTest {
  
  // try out the ADT Queue using static allocation
  public static void main(String[] arg) {
	Scanner in = new Scanner(System.in);
    Queue q = new Queue();

    System.out.println("Inserting ints from 9 to 1 into queue gives:\n");
    for (int i = 9; i > 0; --i) {
       q.enQueue( i);
    }

    q.display();

	if(!q.isEmpty())
	{
        System.out.println("Deleting value from queue " + q.deQueue() + "\n");
	}

	System.out.println("Adding value to queue " + 27 + "\n");
    q.enQueue(27);
    q.display();
	
	System.out.println("Please enter in a number to chack if its in the queue");
	int num = in.nextInt();
	boolean isMeme =q.isMember(num);
	if(isMeme == true)
	{
		System.out.println("The number is in the queue");
	}
	else{
		System.out.println("Number not in queue");
	}
  }

} //end of Test class

