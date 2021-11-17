
public class Heap<E extends Comparable<? super E>> {
    private E[] Heap; // Pointer to the heap array
    private int size; // Maximum size of the heap
    private int n; // Number of things now in heap

    // Constructor supporting preloading of heap contents
    public Heap(E[] h ,int max) {
        this.size = max;
        this.Heap = h;
        this.n = 0;
        buildheap();
    }


    // Return current size of the heap
    public int heapsize() {
        return n;
    }

    public E getRecord(int post) {
    	return Heap[post];
    }
    // Return true if pos a leaf position, false otherwise
    private boolean isLeaf(int pos) {
        return (pos >= n / 2) && (pos < n);
    }


    // Return position for left child of pos
    private int leftchild(int pos) {
        if (pos >= n / 2) {
            return -1;
        }
        return 2 * pos + 1;
    }


    // Return position for right child of pos
    private int rightchild(int pos) {
        if (pos >= (n - 1) / 2) {
            return -1;
        }
        return 2 * pos + 2;
    }


    // Return position for parent
    private int parent(int pos) {
        if (pos <= 0) {
            return -1;
        }
        return (pos - 1) / 2;
    }


    private void swap(int first, int second) {
        E temp;
        temp = Heap[first];
        Heap[first] = Heap[second];
        Heap[second] = temp;
    }


    // Insert val into heap
    public void insert(E key) {
        if (n >= size) {
            System.out.println("Heap is full");
        }
        int curr = n++;
        Heap[curr] = key;
        // Now sift up until curr's parent's key > curr's key
        while ((curr != 0) && Heap[curr].compareTo(Heap[parent(curr)]) < 0) {
            swap(curr, parent(curr));
            curr = parent(curr);
        }
    }


    private void buildheap() {
        for (int i = n / 2 - 1; i >= 0; i--) {
            siftdown(i);
        }
    }


    // Put element in its correct place
    private void siftdown(int pos) {
        if ((pos < 0) || (pos >= n)) {
            return;
        } // Illegal position
        while (!isLeaf(pos)) {
            int j = leftchild(pos);
            if ((j < (n - 1)) && (Heap[j].compareTo(Heap[j + 1]) < 0)) {
                j++; // j is now index of child with greater value
            }
            if (Heap[pos].compareTo(Heap[j]) >= 0) {
                return;
            }
            swap(pos, j);
            pos = j; // Move down
        }
    }


    // Remove and return maximum value
    public E removeMin() {
        if (n == 0) {
            return null;
        } // Removing from empty heap
        swap(0, --n); // Swap maximum with last value
        siftdown(0); // Put new heap root val in correct place
        return Heap[n];
    }


    // Remove and return element at specified position
    public E remove(int pos) {
        if ((pos < 0) || (pos >= n)) {
            return null;
        }
        if (pos == (n - 1)) {
            n--;
        }
        else {
            swap(pos, --n);
            update(pos);
        }
        return Heap[n];
    }


    /**
     * modify that value at a given position
     * 
     * @param pos
     *            position of the value
     * @param newVal
     *            new value
     */
    public void modify(int pos, E newVal) {
        if ((pos < 0) || (pos >= n)) {
            return;
        }
        Heap[pos] = newVal;
        update(pos);
    }

    public static void heapsort(Record[] A) {
  	  Heap H = new Heap(A, A.length);
  	  for (int i=0; i<A.length; i++) {  
  	    H.removeMin(); 
  	  }
  	}
    /**
     * function to update
     * 
     * @param pos
     */
    private void update(int pos) {
        // If it is a big value, push it up
        while ((pos > 0) && (Heap[pos].compareTo(Heap[parent(pos)]) < 0)) {
            swap(pos, parent(pos));
            pos = parent(pos);
        }
        siftdown(pos); // If it is little, push down
    }
}
