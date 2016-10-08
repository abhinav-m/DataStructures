/*> A heap is a tree with some special properties.The basic property known as the "Heap" property is
that all nodes in a heap must be >= (max) heap or <= than it's child nodes.
>Another property of a heap is that all leaves must be at h or h-1 level for some h>0. This means that
the heap should form a complete binary tree.
>A complete binary tree is a tree where all the nodes at a level are complete except the last level,
and the last level should have nodes towards the left.
>A heap can be understood as a data structure which supports the operations "Insert" and deletemax/deletemin
ie, a priority based queue. These can be understood as Enqueue and delete operations of the queue. Difference
is that the order in which elements are queued are not necessarily the order in which they are processed.
 */

/*Enum type declaring two types of heap, max heap and min heap,
where both can be initialised with the constructor.
 */
enum HEAP_TYPE{
    MAX_HEAP,MIN_HEAP;
}

/*Implementing heap with the help of array*/
class BinaryHeap
{
public HEAP_TYPE heapType;
int[] array;
int capacity;
int size;

//Constructor for heap , with heap_type as an enum passed by user
//a max heap has the maximum element in the first node
//a min heap has the minimum element in the first node.
//Capacity is passed to the constructor, initial size is 0.
    BinaryHeap(HEAP_TYPE heapType,int initialCapacity)
    {
        this.heapType = heapType;
        this.capacity = initialCapacity;
        array = new int[capacity];
        size = 0;
    }
/* This is because nodes are stored in level order traversal form in a
heap ,in an array whose index starts at 0. and every node is positioned according to it's priority.
 */
    int  parentOf(int i)
    {
        if(i<=0||i<size)
        return -1;
        else
        return i-1/2;
    }
/*Since heaps are stored from array index  0, left child will be at 2*i+1,right child will
be at 2*i+2 if that is greater than the current number of elements in the heap(note this can be
less than the capacity, the rest of the elements are empty)
 */
    int leftChild(int i)
    {
        return (2*i+1<size?2*i+1:-1);
    }

    int rightChild(int i)
    {
        return (2*i+2<size?2*i+2:-1);
    }

/* ALL the above operations are O(1) operations and are used internally by the heap.
NOTE the insertion and deletion only happens according to the priority.
 */
/*Since a heap is a priority queue . It will be either min heap or max heap,
and the element with max priority will be stored in the first location.
 */
  int  peek()
    {
        return(size>0?array[0]:-1);
    }

/* This function takes an element at position i,
and inserts it at it's correct position in the heap ASSUMING
the element is in the incorrect position in the first place.
Can be understood as a driver function for removing elements from the
heap, which copy the last element into the first position to delete it,
reduce the size of the heap, and percolate down the first element to
it's correct position.
 */
    void perlocateDown(int  i)
    {
        int min,left,right,temp;
        left = leftChild(i);
        right = rightChild(i);
        if(left!=-1&&array[left]<array[i])
        min = left;
        else
        min =i;
        if(right!=-1&&array[right]<array[i])
        min = right;
        if(min!=i)
        {
             temp = array[i];
            array[i] = array[min];
            array[min]=temp;
        }
        perlocateDown(min);
    }
/*Above is the driver function for deletion,
the last element is copied to the first element of the heap and
percolated down back to it's correct position.
 */
    int delete()
    {
        int data;
        if(this.size==0)
        return -1;
        data = array[0];
        array[0] = array[size-1];
        size--;
        if(size>0)
        this.perlocateDown(0);
        return data;
    }
/*Insertions involve percolating up ,
similar to deletion function, element is inserted
in the last position and compared with it's previous elements,
and shifted upwards accordingly.
 */
    void insert(int data)
    {
        if(size==capacity)
        resizeHeap();
        int i =size;
        size++;
        array[i]=data;
        while(i>0&&data<array[parentOf(i)])
        {
            int temp = array[i];
            array[i] = array[parentOf(i)];
            array[parentOf(i)] = temp;
            i= parentOf(i);
        }

    }
/*Heap is implemented through dynamic array, thus if array size
is insufficient we increase it by repeated doubling.
 */
    public void resizeHeap()
    {
        int[] newArray = new int[this.capacity*2];
        for(int i=0;i<array.length;i++)
        newArray[i] =  array[i];
        this.array= newArray;
        capacity=capacity*2;
    }

    void destroyHeap()
    {
       this.array = null;
        this.size =0;
        this.capacity = 0;
    }
}


public class PQAndHeaps {

    public static void main(String[] args) {
	// write your code here
    }
}
