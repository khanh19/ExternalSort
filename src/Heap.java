/**
 * this is the heap class
 * 
 * @author Quoc Cuong Pham
 *
 * @param <E> the generic
 */
//On my honor:
//
//- I have not used source code obtained from another student,
//or any other unauthorized source, either modified or
//unmodified.
//
//- All source code and documentation used in my program is
//either my original work, or was derived by me from the
//source code published in the textbook for this course.
//
//- I have not discussed coding details about this project with
//anyone other than my partner (in the case of a joint
//submission), instructor, ACM/UPE tutors or the TAs assigned
//to this course. I understand that I may discuss the concepts
//of this program with other students, and that another student
//may help me debug my program so long as neither of us writes
//anything during the discussion or modifies any computer file
//during the discussion. I have violated neither the spirit nor
//letter of this restriction.
public class Heap<E extends Comparable<? super E>> {
	private E[] Heap; // Pointer to the heap array
	private int size; // Maximum size of the heap
	private int n; // Number of things now in heap

	/**
	 * this is the constructor of the heap
	 * 
	 * @param h   the array of the heap
	 * @param max the maximum size of the heap
	 */
	public Heap(E[] h, int max) {
		this.size = max;
		this.Heap = h;
		this.n = 0;
	}

	/**
	 * return the number of current element in the heap
	 * 
	 * @return
	 */
	public int heapsize() {
		return n;
	}

	/**
	 * the function to return the record in specific pos
	 * 
	 * @param post the position of the record
	 * @return the record
	 */
	public E getRecord(int post) {
		return Heap[post];
	}

	/**
	 * helper function to test if it's the leaf
	 * 
	 * @param pos the position of the element
	 * @return the true if it is the leaf
	 */
	private boolean isLeaf(int pos) {
		return (pos >= n / 2) && (pos < n);
	}

	/**
	 * the helper function to test if it is the left child
	 * 
	 * @param pos the position of the record
	 * @return the number
	 */
	private int leftchild(int pos) {
		return 2 * pos + 1;
	}

	/**
	 * the helper function to check the right child
	 * 
	 * @param pos the position of that record
	 * @return the number
	 */
	private int rightchild(int pos) {
		return 2 * pos + 2;
	}

	/**
	 * the helper function to create a parent
	 * 
	 * @param pos the specific position of the record
	 * @return the parent node position
	 */
	private int parent(int pos) {
		return (pos - 1) / 2;
	}

	/**
	 * the helper function to swap two record in an array
	 * 
	 * @param first  the first record
	 * @param second the second record
	 */
	private void swap(int first, int second) {
		E temp;
		temp = Heap[first];
		Heap[first] = Heap[second];
		Heap[second] = temp;
	}

	/**
	 * the function that insert the record
	 * 
	 * @param key the key record
	 */
	public void insert(E key) {
		if (n >= size) {
			System.out.println("Heap is full");
		}
		Heap[n] = key;
		int curr = n;
		while ((curr != 0) && Heap[curr].compareTo(Heap[parent(curr)]) < 0) {
			swap(curr, parent(curr));
			curr = parent(curr);
		}
		n++;
	}

	/**
	 * the helper function to sift down the tree
	 * 
	 * @param pos the position of the record
	 */
	private void siftdown(int pos) {
		if ((pos < 0) || (pos >= n)) {
			return;
		}
		if (!isLeaf(pos)) {
			int left = leftchild(pos);
			int right = rightchild(pos);
			if (Heap[pos].compareTo(Heap[left]) > 0 || Heap[pos].compareTo(Heap[right]) > 0) {
				if (Heap[right].compareTo(Heap[left]) > 0) {
					swap(pos, left);
					siftdown(left);
				} else {
					swap(pos, right);
					siftdown(right);
				}
			}

		}
	}

	/**
	 * the function to remove the minimum node
	 * 
	 * @return the minimum record
	 */
	public E removeMin() {
		if (n == 0) {
			return null;
		}
		E removeMin = Heap[0];
		Heap[0] = Heap[--n];
		siftdown(0);
		return removeMin;
	}

}
