/*
 * Algorithms Assignment 2nd Year
 * Implementing Kruskal's and Prims Algorithms
 * Written by:Dimiter Dinkov
 * Student Number: C15334276
*/


import java.io.*;
import java.util.Scanner;

// Kruskal's Minimum Spanning Tree Algorithm
// Union-find implemented using disjoint set trees without compression
//Making an edge class for Krims
class KEdge {
	//Variables 
    public int u, v, wgt;
	
	
    public KEdge() {
        u = 0;
        v = 0;
        wgt = 0;
    }
	
	//Ege constructor
    public KEdge( int x, int y, int w) {
		//missing lines
        u = x;// current vertex
        v = y;//next vertex
        wgt = w;//weight of edge
    }
	
    public void show() {
        System.out.print("Edge " + toChar(u) + "--" + wgt + "--" + toChar(v) + "\n") ;
    }

    // convert vertex into char for pretty printing
    private char toChar(int u)
    {
        return (char)(u + 64);
    }
}

class KHeap
{
    public int[] h;
    int N, Nmax;
    KEdge[] edge;

    // Bottom up heap construc
    public KHeap(int _N, KEdge[] _edge) {
        int i;
        Nmax = N = _N;
        h = new int[N+1];
        edge = _edge;
        // initially just fill heap array with
        // indices of edge[] array.
        for (i=0; i <= N; ++i) {
            h[i] = i;
        }

        // Then convert h[] into a heap
        // from the bottom up.
        for(i = N/2; i > 0; --i) {
            siftDown(i);// missing line;
        }
    }

    private void siftDown( int k) {
        int e, j;

        e = h[k];
        while( k <= N/2) {
            // missing lines
            int leftchild = k*2;
            int rightchild = (k*2)+1;
            int smallerchild;

            if((rightchild <(N))&&(edge[h[leftchild]].wgt > edge[h[rightchild]].wgt))
            {
                smallerchild = rightchild;
            }
            else
            {
                smallerchild = leftchild;
            }

            //the indices in the heap represent indices to edge array. The top of
            //the heap will be the indice of the the edge with the lowest weight.
            //comparing parent with smallest we swap if greater

            if(edge[h[k]].wgt >= edge[h[smallerchild]].wgt)
            {
                int temp = h[smallerchild];
                h[smallerchild] = h[k];
                h[k] = temp;
            }
            k=smallerchild;
        }
        h[k] = e;
    }

    public int remove() {
        h[0] = h[1];
        h[1] = h[N--];
        siftDown(1);
        return h[0];
    }
}

/****************************************************
 *
 *       UnionFind partition to support union-find operations
 *       Implemented simply using Discrete Set Trees
 *
 *****************************************************/
class UnionFindSets
{
    private int[] treeParent;
    private int N;

	//Populating the set
    public UnionFindSets( int V)
    {
        N = V;
        treeParent = new int[V+1];
        // missing lines

        for(int i =1; i<=V; i++)
        {
            treeParent[i] =i;
        }

        N=V;
    }
	
	//Finiding the root of the set
    public int findSet( int vertex)
    {
        // missing lines
        while(treeParent[vertex] != vertex)
        {
            vertex = treeParent[vertex];
        }

        return vertex;
    }
	
	//Joining the two sets
    public void union( int set1, int set2)
    {
        int set1Root, set2Root;
        // missing
        set1Root = findSet(set1);
        set2Root = findSet(set2);
        treeParent[set2Root] = set1Root;
    }
	
	//Displaying the tree
    public void showTrees()
    {
        int i;
        for(i=1; i<=N; ++i)
            System.out.print(toChar(i) + "->" + toChar(treeParent[i]) + "  " );
        System.out.print("\n");
    }
	
	//Displaying each set
    public void showSets()
    {
        int u, root;
        int[] shown = new int[N+1];
        for (u=1; u<=N; ++u)
        {
            root = findSet(u);
            if(shown[root] != 1) {
                showSet(root);
                shown[root] = 1;
            }
        }
        System.out.print("\n");
    }

    private void showSet(int root)
    {
        int v;
        System.out.print("Set{");
        for(v=1; v<=N; ++v)
            if(findSet(v) == root)
                System.out.print(toChar(v) + " ");
        System.out.print("}  ");

    }
	//Converting th evertex number to a letter for easier display
    private char toChar(int u)
    {
        return (char)(u + 64);
    }
}
//Class for the Kruskals Graph
class KruskalsGraph
{
	//Variables
    private int NumVert,NumEdges;
    private KEdge[] edge;
    private KEdge[] mst;

    public KruskalsGraph(String graphFile) throws IOException
    {
        int u, v;
        int w, e;

        FileReader fr = new FileReader(graphFile);
        BufferedReader reader = new BufferedReader(fr);

        String splits = "\t";  // multiple whitespace as delimiter
        String line = reader.readLine();
        String[] parts = line.split(splits);
        System.out.println("Parts[] = " + parts[0] + " " + parts[1]);

        NumVert = Integer.parseInt(parts[0]);
        NumEdges = Integer.parseInt(parts[1]);

        // create edge array
        edge = new KEdge[NumEdges+1];

        // read the edges
        System.out.println("Reading edges from text file");
        for(e = 1; e <= NumEdges; ++e)
        {
            line = reader.readLine();
            parts = line.split(splits);
            u = Integer.parseInt(parts[0]);
            v = Integer.parseInt(parts[1]);
            w = Integer.parseInt(parts[2]);

            System.out.println("Edge " + toChar(u) + "--(" + w + ")--" + toChar(v));

            // create Edge object
            edge[e]= new KEdge(u,v,w);
        }
    }

    /**********************************************************
     *
     *       Kruskal's minimum spanning tree algorithm
     *
     **********************************************************/
    public KEdge[] MST_Kruskal()
    {
        int ei, i = 0;
        KEdge e;
        int uSet, vSet;
        UnionFindSets partition;
        // create edge array to store MST
        // Initially it has no edges.
        mst = new KEdge[NumVert-1];

        // priority queue for indices of array of edges
        KHeap h = new KHeap(NumEdges, edge);

        // create partition of singleton sets for the vertices
        partition = new UnionFindSets(NumVert);

        partition.showSets();

        while(i<NumVert -1)
        {
            uSet = edge[h.h[1]].u;
            vSet = edge[h.h[1]].v;

            if(partition.findSet(uSet) != partition.findSet(vSet))
            {
                partition.union(uSet,vSet);

                ei=h.remove();

                mst[i] = edge[ei];
                partition.showSets();
                i++;
            }
            else
            {
                h.remove();
            }
        }
        return mst;
    }

    // convert vertex into char for pretty printing
    private char toChar(int u)
    {
        return (char)(u + 64);
    }

    public void showMST()
    {
        int totalMst =0;
        System.out.print("\nMinimum spanning tree build from following edges:\n");
        for(int e = 0; e < NumVert-1; ++e) {
            mst[e].show();
            totalMst += mst[e].wgt;
        }
        System.out.println();
        System.out.println("Total Mst for Kruskals ="+totalMst);
    }

} // end of KGraph class
//End of the code for Kruskals Algorithm


//Begining of Prims code
//Making  heap for prims
class Heap
{
    private int[] h;	   // heap array
    private int[] hPos;	   // hPos[h[k]] == k
    private int[] dist;    // dist[v] = priority of v

    private int N;         // heap size

    // The heap constructor gets passed from the Graph:
    //    1. maximum heap size
    //    2. reference to the dist[] array
    //    3. reference to the hPos[] array
    public Heap(int maxSize, int[] _dist, int[] _hPos)
    {
        N = 0;
        h = new int[maxSize + 1];
        dist = _dist;
        hPos = _hPos;
    }

	//Checking if the heap is empty
    public boolean isEmpty()
    {
        return N == 0;
    }

	//siftUp
    public void siftUp( int k)
    {
        //The new value assigned to v
        int v = h[k];

        // code yourself
        // must use hPos[] and dist[] arrays

        //Put a dummy node at the top of the heap.
        h[0] = 0;
        dist[0] = Integer.MIN_VALUE;

        //While the new vertex that has been added is greater than its parent
        //The switch places
        while(dist[v] < dist[h[k/2]])
        {

            h[k] = h[k/2];
            hPos[h[k]] = k;
            k = k/2;
        }

        h[k] =v;
        hPos[v] =k;
    }

	//SiftDown 
    public void siftDown( int k)
    {
        int v, j;

        v = h[k];
        j= 2*k;
        // code yourself
        // must use hPos[] and dist[] arrays
        //Until top node is not at the end of the heap
        while(j <=N)
        {
            //if the right child is greater than the left child
            //increment j so you can compare the bigger child to the new top node

            if(dist[h[j]] > dist[h[j+1]])
            {
                j++;
            }

            //Compare the max child to the new top node and if greater swap
            if( dist[h[v]] < dist[h[j]])
            {
                h[k] = h[j];
                k = j;
                j= 2*k;
            }
            else
            {
                break;
            }

        }//end while


        h[k] = v;
        hPos[v] = k;

    }

	//Inserting vertex into heap
    public void insert( int x)
    {
        //The array size is increased by one and
        //the new vertex is assigned to the end of the array
        h[++N] = x;
        //The new value that has been entered into the heap is passed to be SiftUp
        siftUp( N);
    }

	//removing vertex from heap
    public int remove()
    {
        int v = h[1];
        hPos[v] = 0; // v is no longer in heap
        h[N+1] = 0;  // put null node into empty spot

        //Puts the value before the null node at the top of the heap
        h[1] = h[N--];
        //SiftDown the new top node to sort the heap
        siftDown(1);

        return v;
    }

}

class PrimsGraph {
    class Node {
        public int vert;
        public int wgt;
        public Node next;
    }

    // V = number of vertices
    // E = number of edges
    // adj[] is the adjacency lists array
    private int V, E;
    private Node[] adj;
    private Node z;
    private int[] mst;

    // used for traversing graph
    private int[] visited;
    private int id;


    // default constructor
    public PrimsGraph(String graphFile)  throws IOException
    {
        int u, v;
        int e, wgt;
        Node t;

        FileReader fr = new FileReader(graphFile);
        BufferedReader reader = new BufferedReader(fr);

        String splits = "\t";  // multiple whitespace as delimiter
        String line = reader.readLine();
        String[] parts = line.split(splits);
        System.out.println("Parts[] = " + parts[0] + " " + parts[1]);

        //Find out the number of vertices and edges
        V = Integer.parseInt(parts[0]);
        E = Integer.parseInt(parts[1]);

        // create sentinel node
        z = new Node();
        z.next = z;

        // create adjacency lists, initialised to sentinel node z
        adj = new Node[V+1];
        for(v = 1; v <= V; ++v)
            adj[v] = z;

        // read the edges
        System.out.println("Reading edges from text file");
        for(e = 1; e <= E; ++e)
        {
            line = reader.readLine();
            parts = line.split(splits);
            u = Integer.parseInt(parts[0]);
            v = Integer.parseInt(parts[1]);
            wgt = Integer.parseInt(parts[2]);

            System.out.println("Edge " + toChar(u) + "--(" + wgt + ")--" + toChar(v));

            // write code to put edge into adjacency matrix
            t = new Node();
            t.wgt= wgt;
            t.vert =u;
            t.next =adj[v];

            adj[v] =t;

            t = new Node();
            t.wgt = wgt;
            t.vert = v;
            t.next = adj[u];

            adj[u]=t;
        }
    }

    // convert vertex into char for pretty printing
    private char toChar(int u)
    {
        return (char)(u + 64);
    }

    // method to display the graph representation
    public void display() {
        int v;
		int startVertex=2;
        Node n;
				
        for(v=1; v<=V; ++v){
            System.out.print("\nadj[" + toChar(v) + "] ->" );
            for(n = adj[v]; n != z; n = n.next)
                System.out.print(" |" + toChar(n.vert) + " | " + n.wgt + "| ->");
        }
        System.out.println("");
        MST_Prim(startVertex);
    }

	//Prims Algorithm
    public void MST_Prim(int s)
    {
        int v, u;
        int wgt, wgt_sum = 0;
        int[]  dist, parent, hPos;
        Node t;

        //code here
        //distance from node to node
        dist = new int[V+1];

        //The parent node
        parent = new int [V+1];

        //The current heap position
        hPos = new int[V+1];

        //initialising parent position to zero, and dist to the max value
        for(v=1; v<=V; v++)
        {
            dist[v] = 999;
            parent[v] = 0;
            hPos[v] =0;
			
			
			
        }

        dist[s] = 0;

        Heap pq =  new Heap(V, dist, hPos);
        pq.insert(s);

        while (!pq.isEmpty())
        {
            // most of alg here
            v = pq.remove();

            System.out.print("\nAdding edge" +toChar(parent[v])+"---"+dist[v]+"---"+toChar(v));

            //calculate the sum of the weights
            wgt_sum += dist[v];
            dist[v] = -dist[v];

            for(t = adj[v]; t != z; t=t.next)
            {
                if(t.wgt < dist[t.vert])
                {
                    dist[t.vert] = t.wgt;
                    parent[t.vert] = v;

					System.out.print(dist[t] + parent[t]);
                    //if the vertex is empty insert new vertex
                    if(hPos[t.vert] ==0)
                    {
                        pq.insert(t.vert);
                    }
                    else
                    {
                        pq.siftUp(hPos[t.vert]);
                    }
                }
            }
        }
        System.out.print("\n\nWeight of MST = " + wgt_sum + "\n");
        mst = parent;
    }

    public void PrimsShowMST()
    {
        System.out.print("\n\nMinimum Spanning tree parent array is:\n");
        for(int v = 1; v <= V; ++v)
            System.out.println(toChar(v) + " -> " + toChar(mst[v]));
        System.out.println("");
    }

}

class AlgorithmsAssignment{
    public static void main(String args[]) throws IOException
    {
        String fname;
        int option=0;

        Scanner sc = new Scanner(System.in);

        System.out.print("\nInput name of file with graph definition: ");
        fname = sc.nextLine();

        while(option !=3) {
            System.out.print("\n\n\nPlease choose one of the following options\n");
            System.out.print("(1)Run Kruskal's Algorithm to get Min Spanning Tree\n");
            System.out.print("(2)Run Prim's Algorithm to get Min Spanning tree\n");
            System.out.print("(3)Exit Program \n\n\n");
            option = sc.nextInt();

            if (option == 1) {
                KruskalsGraph g = new KruskalsGraph(fname);
                g.MST_Kruskal();
                g.showMST();
            } else if (option == 2) {
                PrimsGraph g = new PrimsGraph(fname);
                g.display();
            } else {
                System.exit(0);
            }
        }

    }
}



