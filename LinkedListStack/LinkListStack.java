/*
Wirtten by Dimiter Dinkov
Student No:C15334276
*/



class Node {

	public char data;
	public Node next;

	public Node(char data)
	{
		this.data = data;
	}

	public void displayNode() 
	{
		System.out.print(data);
		System.out.print(" \n");

	}
}

class LinkList {

		private Node first = null;

		public void insertFirst(char data) {
			Node n = new Node(data);
			n.next = first;
			first = n;
		}

		public void deleteFirst() {
			Node temp = first;
			first = first.next;
			System.out.print("Removing "+ temp.data+"\n");
		}

		public void displayList() {
			Node current = first;
			while (current != null) {
				current.displayNode();
				current = current.next;
			}
		}


		public boolean isEmpty() {
			return (first == null);
		  }
}


class LinkLS {

	LinkList li = new LinkList();

	public void push(char data) {
		li.insertFirst(data);
	}

	public void pop() {
		while(!li.isEmpty()){
		li.deleteFirst();
		}
	}

	public	void displayStack() {
		System.out.println("  ");
		li.displayList();
	}
}

class LinkListStack{

	public static void main(String[] args)
	{
		LinkLS st = new LinkLS();

		st.push('s');
		st.push('d');
		st.push('k');
		st.displayStack();
		st.pop();
		st.displayStack();	

	}
 }