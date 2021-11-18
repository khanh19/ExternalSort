/**
 * this is the heap class
 * 
 * @author Quoc Cuong Pham
 *
 * @param <E> the generic
 */
public class Heap<E extends Comparable<? super E>> {
	private E[] Heap; // Pointer to the heap array
	private int size; // Maximum size of the heap
	private int n; // Number of things now in heap

	// Constructor supporting preloading of heap contents
	public Heap(E[] h, int max) {
		this.size = max;
		this.Heap = h;
		this.n = 0;
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
		return 2 * pos + 1;
	}

	// Return position for right child of pos
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

	public void Sort(E[] arr) {
		for (int i = n / 2 - 1; i >= 0; i--) {
			heapify(arr, n, i);
		}
		for (int i = n - 1; i >= 0; i--) {
			E temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			heapify(arr, i, 0);
		}
	}

	private void heapify(E[] arr, int n, int i) {
		int smallest = i;
		int left = 2 * i + 1;
		int right = 2 * i + 2;

		if (left < n && arr[left].compareTo(arr[smallest]) > 0) {
			smallest = left;
		}
		if (right < n && arr[right].compareTo(arr[smallest]) > 0) {
			smallest = right;
		}
		if (smallest != i) {
			E temp = arr[i];
			arr[i] = arr[smallest];
			arr[smallest] = temp;
			heapify(arr, n, smallest);
		}
	}

}
