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
            size++;
        }
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
}
//TBD
class ListQueue
{
    LinkedList queueList;
    int front,rear;
    int size =;
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
