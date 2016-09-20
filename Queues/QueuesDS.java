import java.util.Stack;
/*TBD: Correct Queue implementation The queue implementation here is sort of arbitrary. A better implmentation is possible.*/
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

    public boolean enQueue(int x) {
        if (this.isEmpty()) {
            elements[++rear] = x;
            front = rear;
        } else if (this.size == maxSize) {
            System.out.println("\nOverflow.");
            return false;
        } else if (rear == maxSize - 1) {
            elements[0] = x;
            rear = 0;
        } else
            elements[++rear] = x;

        size++;
        return true;
    }

    public boolean isEmpty()
    {
        return (size==0);
    }

    public int deQueue(){
        int elemDeleted;
        if(isEmpty())
        {
            System.out.println("\nUnderflow");
            return -1;
        }
        else if(front == maxSize-1) {
            elemDeleted = elements[maxSize-1];
            front = 0;

        }
        else {
            elemDeleted = elements[front];
            ++front;
        }
        size--;
        if(size==0)
        {
            front =-1;
            rear =-1;
        }
        return elemDeleted;


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




public class QueuesDS {

    public static void main(String[] args) {
	CircularArrayQueue arrayQueue = new CircularArrayQueue();
    System.out.println("Enqueueing 20");
    for(int i =1;i<=20;i++)
    arrayQueue.enQueue(i);
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
