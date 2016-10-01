//Node for doubly ended linked list which is used in DEQUEUE.
class Node
{
    Node previousNode;
    Node nextNode;
    int data;
    Node()
    {
       previousNode = null;
       nextNode = null;
    }
}

//Dequeue is often referred to as a head - tail linked list owing to this common
//implementation.
class DeQueue
{
    Node head;
    Node tail;
    int size;

    DeQueue()
    {
        head = null;
        tail = null;
    }

public boolean pushFront(int data)
{
    Node newNode = new Node();
    newNode.data = data;
    if(head==null)
    {
        head = newNode;
        tail = newNode;
    }
    else
    {
    newNode.nextNode = head;
    head.previousNode = newNode;
    head = newNode;
    }
    this.size++;
    return true;
}

public boolean pushBack(int data)
{
    Node newNode = new Node();
    newNode.data = data;
    if(head==null)
    {
        head = newNode;
        tail = newNode;
    }
    else
    {
        newNode.previousNode = tail;
        tail.nextNode = newNode;
        tail = newNode;
    }
    this.size++;
    return true;
}

public int popFront()
{
    int temp = -1;
    if(head==null)
        return temp;
   else if(head==tail)
    {
        temp = head.data;
        head= null;
        tail =null;
    }
    else
    {
        temp = head.data;
        head = head.nextNode;
        head.previousNode = null;

    }
    this.size--;
    return temp;
}

public int popBack()
{
    int temp = -1;
    if(head==null)
    return temp;
    else if(head==tail)
    {
    temp =head.data;
    head = null;
    tail = null;
    }
    else
    {
        temp = tail.data;
        tail = tail.previousNode;
        tail.nextNode = null;
    }
    this.size--;
    return temp;
}

public boolean isEmpty()
{
    return(head==null);
}

public int size()
{
    return size;
}

public void display()
{
    if(head==null)
    System.out.println("Empty list.");
    else
    {
        Node curNode  = head;
        while(curNode!=null) {
            System.out.print(curNode.data);
            curNode = curNode.nextNode;
            if(curNode!=null)
            System.out.print("->");

        }
    }
}
}

public class DeQueueDS {

    public static void main(String[] args) {
        DeQueue dQu = new DeQueue();
    for(int i =1;i<=10;i++)
        if(i%2==0)
            dQu.pushBack(i);
        else
            dQu.pushFront(i);

        dQu.display();
    }
}

