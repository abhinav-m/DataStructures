import java.util.Arrays;
import java.util.Stack;
import java.util.LinkedList;
/*Corrected Queue implementation The queue implementation here is better than the one coded earlier.
It uses modulus operator to determine new index of the queue array rather than testing for all boundary conditions like
the previous implementation.
Basic concept behind this is m%n when m<=n m>=0 will give 0,1,2...n-1 and cycle back to 0(as n%n = 0)
Also, dividend = divisor*quotient + remainder,when quotient is 0 (always the remainder will be the dividend)
when dividend = divisor = n remainder = n-n => 0*/
class CircularArrayQueue
{
    static final int maxSize = 20;
    int[] elements;
    int front,rear;
    int size;
    CircularArrayQueue() {
        elements = new int[maxSize];
        front= -1;
        rear = -1;
        size =0;
    }

    public boolean isFullQueue()
    {
       return ((rear + 1) % maxSize == front);
    }

    public boolean enQueue(int x) {

        if (isFullQueue()){
            System.out.println("\nOverflow.");
            return false;
        } else {
            //See above explanation.
            rear = (rear + 1) % maxSize;
            elements[rear] = x;
        }
        if (this.isEmpty()) {
            front = rear;

        }
        size++;
        return true;
    }

    public boolean isEmpty()
    {
        return (front == -1);
    }

    public int deQueue() {
        int elemDeleted = 0;
        if (isEmpty()) {
            System.out.println("\nUnderflow");
            return -1;
        } else {
            elemDeleted = elements[front];
            //Only one element left in the queue.
            if (front == rear) {
                front = -1;
                rear = -1;
            } else
                //Increment size of queue in the same manner as rear( this also cycles  from 0 to maxSize-1)
                front = (front + 1) % maxSize;
        }

        size--;
        return elemDeleted;
    }

    int queueSize()
    {
    return(maxSize-front+rear+1)%maxSize;
    }


    /*PROBLEM STATEMENT: Reverse a queue using only queue ADT
    * Time complexity: O(n) , this gave me a headache as i realized
    * i had made many mistakes in my Queue implementation.*/
    public void revQueue()
    {
        Stack stack = new Stack();
        while(!this.isEmpty())
        stack.push(this.deQueue());
        while(!stack.isEmpty())
        this.enQueue((int)stack.pop());


    }

    public void display()
    {
        if(front<rear)
        for(int i = front;i<=rear;i++) {
            if(i==front)
            System.out.print(elements[i] + "f<-");
            else if(i==rear)
            System.out.print(elements[i] + "r<-");
            else
            System.out.print(elements[i] + "<-");
        }
        else if(front>rear) {
            for (int i = front; i <= maxSize - 1; i++) {
                if (i == front)
                    System.out.print(elements[i] + "f<-");
                else
                    System.out.print(elements[i] + "<-");
            }

            for (int i = 0; i <= rear; i++) {
                if(i==rear)
                System.out.print(elements[i] + "r<-");
                else
                System.out.print(elements[i] + "<-");
            }
        }
        else
            System.out.print(elements[front]);
    }
/*PROBLEM STATEMENT: Interleaving a queue.
 * An interleaved queue means for a given queue of size n (where n%2 == 0) ie,
  * even number of elements if Q is {1,2,3,4,5,6}, Interleafed q will be
   * 1,4,2,5,3,6
   * Time complexity is O(n) since we are scanning the queue of size n and splitting it into two equal
   * queues, i found this approach more intuitive than the one in karumanchi.
   * The second for loop operates only n/2 times therefore the time complexity here will be
   * O(n) + O(n/2) = O(n)
   * Dequeuing and queueing are O(1) operations.*/
    public boolean interLeafQueue()
    {
        //According to definition only queue where even number of elements occur can be interleaved.
        if(this.size%2!=0)
        return false;
        int originalSize = this.size;
        CircularArrayQueue firstQueueHalf = new CircularArrayQueue();
        CircularArrayQueue secondQueueHalf = new CircularArrayQueue();
        for(int i =0;i<originalSize;i++) {
            if (i < originalSize / 2)
                firstQueueHalf.enQueue(this.deQueue());
            else
                secondQueueHalf.enQueue(this.deQueue());
        }
        for(int i=0;i<originalSize/2;i++)
        {
            this.enQueue(firstQueueHalf.deQueue());
            this.enQueue(secondQueueHalf.deQueue());
        }
        return true;
    }

    /*PROBLEM STATEMENT: INTERLEAVING A QUEUE
    Solution 2. This involves usage of stack, and can be considered as an alternative solution where
    using other queues isn't allowed.The basic concept behind this method is to reverse the queues
   first half and then store it in a stack.Thus we will get half of the original queue in the stack ,
    and half in the queue itself. Dequeuing the queue and enqueueing the popped stack will give us the inter
    leaved queue.
     Time complexity: O(n) Space complexity: O(n) for stack space*/
    public boolean interLeafQueueUsingStack()
    {
        //According to definition only queue where even number of elements occur can be interleaved.
        if(this.size%2!=0)
            return false;
        Stack stack = new Stack();
        //Store size as the size changes and we need this value
        int originalSize = this.size;
        //Reverse front half of the queue by pushing it to stack.
        for(int i =0;i<originalSize/2;i++)
         stack.push(this.deQueue());
        //Queue the reversed first half of the queue back to the end of the queue
         while(!stack.isEmpty())
         this.enQueue((int)stack.pop());
        //Rotate half of the queue so the reversed first half is in the first place.
        for(int i =0;i<originalSize/2;i++)
           this.enQueue(this.deQueue());
        //Put the first half of the queue in stack again to reverse it, stack and queue are ready now to be
        //used simultaneously to interleaf.
        for(int i=0;i<originalSize/2;i++)
        stack.push(this.deQueue());
        //Pop from stack to get the first half in original sequence and dequeue the queue to get the interleaved
        //part queue both in a single loop.
        for(int i=0;i<originalSize/2;i++)
        {
            this.enQueue((int)stack.pop());
            this.enQueue(this.deQueue());
        }
        return true;
    }
}
//TBD
class ListQueue
{
    LinkedList queueList;
    int front,rear;
    int size;
    ListQueue()
    {
        queueList = new LinkedList();
        front = -1;
        rear = -1;
        size =0;
    }
    public  boolean enQueue(int x)
    {
        if(queueList.isEmpty())

        queueList.addLast(x);
        return false;
    }
}

class DynamicArrayQueue{
    int maxSize;
    int[] elements;
    int front,rear;
    int size;

    DynamicArrayQueue(int size)
    {
        maxSize = size;
        elements = new int[maxSize];
        front = -1;
        rear = -1;
        this.size = 0;
    }


    public boolean isFullQueue()
    {
        return ((rear + 1) % maxSize == front);
    }


    public int deQueue() {
        int elemDeleted = 0;
        if (isEmpty()) {
        System.out.println("\nUnderflow");
        return -1;
        } else {
        elemDeleted = elements[front];
        //Only one element left in the queue.
        if (front == rear) {
            front = -1;
            rear = -1;
        } else
        //Increment size of queue in the same manner as rear( this also cycles  from 0 to maxSize-1)
        front = (front + 1) % maxSize;
        }

        size--;
        return elemDeleted;
    }

    public boolean enQueue(int x) {

        if (isFullQueue()) {
            System.out.println("RESIZING QUEUE");
            resizeQueue();
        }
        //See above explanation.
        rear = (rear + 1) % maxSize;
        elements[rear] = x;

        if (this.isEmpty()) {
            front = rear;
            size++;
        }
        return true;
    }

    public void resizeQueue()
    {
        int oldSize = this.maxSize;
        this.maxSize =this.maxSize*2;
        //Other way to do this is manually copying each element from the previous array to the element in a newly sized array.
        elements= Arrays.copyOf(elements,maxSize);
        //We shift the elements to the back of the queue by adding the old size to their
        //respective indices , this is done so that the condition for full queue check
        //ie, front->next = rear doesn't become true when the size of the queue has increased.
        //this would be a false positive.
        if(front>rear) {
            for (int i = 0; i < front; i++)
                elements[i + oldSize] = elements[i];
            //rear will be shifted because it's behind front
            rear = rear + size;
        }
    }

        public boolean isEmpty()
        {
            return (front == -1);
        }


    public void display()
    {
        if(front<rear)
            for(int i = front;i<=rear;i++) {
                if(i==front)
                    System.out.print(elements[i] + "f<-");
                else if(i==rear)
                    System.out.print(elements[i] + "r<-");
                else
                    System.out.print(elements[i] + "<-");
            }
        else if(front>rear) {
            for (int i = front; i <= maxSize - 1; i++) {
                if (i == front)
                    System.out.print(elements[i] + "f<-");
                else
                    System.out.print(elements[i] + "<-");
            }

            for (int i = 0; i <= rear; i++) {
                if(i==rear)
                    System.out.print(elements[i] + "r<-");
                else
                    System.out.print(elements[i] + "<-");
            }
        }
        else
            System.out.print(elements[front]);
    }

    int queueSize()
    {
        return(maxSize-front+rear+1)%maxSize;
    }
}


public class QueuesDS {

    public static void main(String[] args) {
	CircularArrayQueue arrayQueue = new CircularArrayQueue();
    for(int i =1;i<=20;i++)
    arrayQueue.enQueue(i);
    System.out.println("\nNormal queue");
    arrayQueue.display();
    arrayQueue.interLeafQueueUsingStack();
    System.out.println("\nInterleafed queue");
    arrayQueue.display();
    DynamicArrayQueue  dynArrayQueue = new DynamicArrayQueue(20);
    for(int i =1;i<=40;i++) {
    dynArrayQueue.enQueue(i);
    }
    dynArrayQueue.display();
    System.out.println("Enqueueing 20");
    for(int i =1;i<=20;i++) {
        arrayQueue.enQueue(i);
        System.out.println(arrayQueue.queueSize());
    }
    arrayQueue.display();
    System.out.println("Reversing the queue\n");
    arrayQueue.revQueue();
    arrayQueue.display();
    System.out.println("\nDequeueing 5");
    for(int i =1;i<=5;i++)
    arrayQueue.deQueue();
    arrayQueue.display();
    System.out.println("Enqueueing 5");
    for(int i =1;i<=5;i++)
    arrayQueue.enQueue(i);
    arrayQueue.display();

    }
}
