/**
 * this is the heapArray class
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
/**
 * The heapArray class
 *
 * @author Quoc Cuong Pham
 * @author Khanh Pham
 * @version 11/17/2021
 *
 * @param <E> the generic
 */
public class Heap<E extends Comparable<? super E>> {
    private E[] heapArray; // Pointer to the heapArray array
    private int size; // Maximum size of the heapArray
    private int n; // Number of things now in heapArray

    /**
     * this is the constructor of the heapArray
     * 
     * @param h   the array of the heapArray
     * @param max the maximum size of the heapArray
     */
    public Heap(E[] h, int max) {
        this.size = max;
        this.heapArray = h;
        this.n = 0;
    }

    /**
     * return the number of current element in the heapArray
     * 
     * @return the size
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
        return heapArray[post];
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
        return 2 * pos;
    }

    /**
     * the helper function to check the right child
     * 
     * @param pos the position of that record
     * @return the number
     */
    private int rightchild(int pos) {
        return 2 * pos + 1;
    }

    /**
     * the helper function to create a parent
     * 
     * @param pos the specific position of the record
     * @return the parent node position
     */
    private int parent(int pos) {
        return (pos) / 2;
    }

    /**
     * this function to swap the root
     */
    public void swapFirst() {
        E temp = heapArray[0];
        heapArray[0] = heapArray[n - 1];
        heapArray[n - 1] = temp;
        n--;
    }

    /**
     * the helper function to swap two record in an array
     * 
     * @param first  the first record
     * @param second the second record
     */
    private void swap(int first, int second) {
        E temp;
        temp = heapArray[first];
        heapArray[first] = heapArray[second];
        heapArray[second] = temp;
    }

    /**
     * the function that insert the record
     * 
     * @param key the key record
     */
    public void insert(E key) {
        if (n >= size) {
            System.out.println("heapArray is full");
        }
        heapArray[n] = key;
        int curr = n;
        while ((curr != 0) && heapArray[curr].
                compareTo(heapArray[parent(curr)]) < 0) {
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
    public void siftdown(int pos) {
        if ((pos < 0) || (pos >= n)) {
            return;
        }
        if (!isLeaf(pos)) {
            int left = leftchild(pos);
            int right = rightchild(pos);
            if (heapArray[pos].compareTo(heapArray[left]) > 0 
                    || heapArray[pos].compareTo(heapArray[right]) > 0) {
                if (heapArray[right].compareTo(heapArray[left]) > 0) {
                    swap(pos, left);
                    siftdown(left);
                } 
                else {
                    swap(pos, right);
                    siftdown(right);
                }
            }

        }
    }

    /**
     * get root function
     * 
     * @return the root
     */
    public E getRoot() {
        return heapArray[0];
    }

    /**
     * replace the root
     * 
     * @param record the record
     */
    public void replacementRoot(E record) {
        this.heapArray[0] = record;
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
        E removeMin = heapArray[0];
        heapArray[0] = heapArray[--n];
        siftdown(0);
        return removeMin;
    }

}
