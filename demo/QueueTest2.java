// Exercise to separate ADT Queue from its implementation
// and to provide 2 implementations. Also exception handling.

class QueueException extends Exception 
{
    public QueueException(String s) 
	{
        super(s);
    }
}    

// In Java an interface can often be the best way to 
// describe an Abstract Data Type (ADT) such as Queue or Stack
interface Queue 
{
	public void display() throws QueueException;
    public void enQueue(int x) throws QueueException;
    public int deQueue() throws QueueException;
    public boolean isEmpty();   
}

class QueueLL implements Queue 
{
    private class Node 
	{
        int data;
        Node next;
    }

    Node z;
    Node head;
    Node tail;

     public QueueLL() 
	 {
        z = new Node(); 
		z.next = z;
        head = z;  
		tail = null;
    }
	
	public void display() throws QueueException
    {
		if(this.isEmpty()) 
		{
            throw new QueueException("\nQueueLL is empty\n");
		}
		
	    System.out.println("\nThe queue(LL) values are:");

	    Node temp = head;
	    while( temp != temp.next) 
		{
			System.out.print( temp.data + "  ");
			temp = temp.next;
		}
		System.out.println("\n");
	}
	
	public void enQueue(int x) throws QueueException  
	{
		Node temp;

		temp = new Node();
		temp.data = x;
		temp.next = z;

		if(this.isEmpty())   
		{
			head = temp;
		}//end if
		else               
		{
			tail.next = temp;
		}//end else
			
		tail = temp; 
		System.out.println("\nQueued(LL):" + x);		
    }
	
    // assume the queue is non-empty when this method is called, otherwise thro exception
    public int deQueue() throws QueueException 
	{
		if(this.isEmpty()) 
		{
            throw new QueueException("\nIllegal to deQueue from an empty QueueLL\n");
		}
		
        int value = head.data;
		Node temp = new Node();
		temp = head;
		head = head.next;
		
		if(this.isEmpty())
		{
			tail = null;
		}
		return value;
    }
	public boolean isEmpty() 
	{
        if(head == z)
		{
			return true;
		}
		else
		{
			return false;
		}
    }
} // end of QueueLL class



class QueueCB implements Queue
{
    private int q[], back, front;
    private int qmax, size;

 
    public QueueCB() 
	{
        qmax = 4;
        size = front = back = 0;
        q = new int[qmax];
    }
	
	public void display() throws QueueException
    {
		if(this.isEmpty()) 
		{
			throw new QueueException("\nQueueCB is empty\n");
		}//end if 
	    System.out.println("\nThe queue(CB) values are:");
		
		if(front < back)
		{
			for(int i = front; i < back; i++)
			{
				System.out.print(q[i]+ "  ");
			}
		}
		else if(front >= back)
		{
			for(int i = front; i < qmax; i++)
			{
				System.out.print(q[i]+ "  ");
			}
			for(int i = 0; i < back; i++)
			{
				System.out.print(q[i]+ "  ");
			}
		}
		System.out.println("\n");
		
	}
	
    public void enQueue( int x) throws QueueException  
	{
        if(qmax == size) 
		{
			throw new QueueException("\nIllegal to enQueue to a full QueueCB");
		}//end if
		 q[back] = x;
		 back = (back + 1) % qmax;
		 ++size;
		 System.out.println("\nQueued(CB):" + x + "\n");		 
    }
  
    public int deQueue()  throws QueueException 
    {
		if(isEmpty()) 
		{
			throw new QueueException("\nIllegal to deQueue from an empty QueueCB");
		}//end if
		 
		int x = q[front];
		front = (front + 1) % qmax;
		 --size;
        return x;
    }

    public boolean isEmpty() 
	{
		if(size == 0)
		{
			return true;
		}
        else
		{
			return false;
		}
    }
}


// here we test both implementations
class QueueTest2 {
    public static void main(String[] arg)
	{
        Queue q1, q2;
        q1 = new QueueLL();
        q2 = new QueueCB();
        
        for(int i = 1; i<6; ++i)
		{
			try 
			{ 
				q1.enQueue(i);            
			} 
			catch (QueueException e) 
			{
				System.out.println("Exception thrown: " + e); 
			}
		}//end for
		
		try
		{
			q1.display();
		} 
		catch (QueueException e) 
		{
			System.out.println("Exception thrown: " + e); 
		}
		
		for(int i = 0; i<6; ++i)
		{
			try 
			{ 
				System.out.println("Deleting value from the queLL : " + q1.deQueue());          
			} 
			catch (QueueException e) 
			{
				System.out.println("Exception thrown: " + e); 
			}
		}
		
		try
		{
			q1.display();
		} 
		catch (QueueException e) 
		{
			System.out.println("Exception thrown: " + e); 
		}
		
		for(int i = 1; i<6; ++i)
		{
			try 
			{ 
				q2.enQueue(i);            
			} 
			catch (QueueException e) 
			{
				System.out.println("Exception thrown: " + e); 
			}
		}//end for
		
		try
		{
			q2.display();
		} 
		catch (QueueException e) 
		{
			System.out.println("Exception thrown: " + e); 
		}
		
		for(int i = 0; i<5; ++i)
		{
			try 
			{ 
				System.out.println("Deleting from the queue CB : " + q2.deQueue());          
			} 
			catch (QueueException e) 
			{
				System.out.println("Exception thrown: " + e); 
			}
		}
		
		try
		{
			q2.display();
		} 
		catch (QueueException e) 
		{
			System.out.println("Exception thrown: " + e); 
		}
		
    }   
}

