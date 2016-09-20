import sun.java2d.pipe.SpanShapeRenderer;

import java.util.Arrays;

class SimpleStack
{
    static final int maxSize = 100;
    int numElements,TOP;
    int[] array;

    SimpleStack()
    {
        array = new int[maxSize];
        numElements=0;
        TOP=-1;
    }

   public boolean push (int x)
    {
        if(numElements==maxSize) {
            System.out.println("\nStack overflow");
            return true;
        }
        else {
            array[++TOP] = x;
            numElements= TOP+1;
            return true;
        }


    }

   public boolean pop() {
        if (this.isEmpty()) {
            System.out.println("\nUnderflow");
            return false;
        } else {
            numElements--;
            TOP--;
            return true;
        }
    }

  public boolean isEmpty()
    {
        return (numElements==0);
    }


    public void display()
    {
        if(this.isEmpty())
            System.out.println("Stack is empty.");
        else {
            for (int i = TOP; i >= 0; i--)
                if (i == TOP)
                    System.out.println("["+array[i] +"]<-TOP");
                else
                    System.out.println("["+array[i]+"]");
        }

    }

    /*PROBLEM STATEMENT:1) Given an array A, span S[i] of A[i] is the max number of consecutive elements A[j],
    immediately preceding A[i] such that A[j] <= A[i].
    NOTE: IMMEDIATELY PRECEDING IS IMPORTANT.
    This is a famous problem in the stock market.
    The simplest solution which comes to mind is to traverse the stack and each element in a double loop
    this will allow us to compare which elements are smaller than our current element and thus calculate span.
    SOLUTION 1: O(n^2) time complexity due to nested loops.
     */

    public static int[] maxSpan(int arr[])
    {
        int span[] = new int[arr.length];
        span[0]=1;
        for(int i =1;i<arr.length;i++) {
            span[i] = 1;
            for (int j = i - 1; j >= 0&&arr[j] <= arr[i]; j--)
                    span[i]++;
        }
        return span;
    }

    /*SOLUTION 2: This involves maintaining a stack with the elements as they appear in the array.*/

    public  int[] maxSpanUsingStack(int arr[])
    {
        int span[] = new int[arr.length];
        span[0]=1;
        this.push(arr[0]);
        for(int i =1;i<arr.length;i++)
        {
            int j =i;
            span[i]=1;

            while(!this.isEmpty()&&arr[i]>=this.array[TOP])
            {
                span[i]+=span[j-1];
                this.pop();
                j--;
            }
            this.push(arr[i]);
        }
           return span;
    }

    /*SOLUTION 3: This is the same as the above solution, however uses a better approach that stores
    * index of the array in the stack instead of actual values, and also doesn't need arbitrary variables to calculate
    * span .*/

    public  int[] maxSpanUsingStackGFOG(int arr[])
    {
        //Span array length will be the same as the current array length
        int span[] = new int[arr.length];
        //First element in the span array stores 1 as the span of the first item will always be 1.
        span[0]=1;
        //Instead of pushing elements we can push index of elements and compare these while using the stack
        this.push(0);
        for(int i =1;i<arr.length;i++)
        {
            //While the stack doesnt become empty and the current element is greater than the previous elements on the stack
            //Note we are checking this through the INDEXES stored in the stack and not the elements themselves.
            //ie,the elements are being accessed with the indexes stored in the stack.
            while(!this.isEmpty()&&arr[i]>=arr[this.array[TOP]])
                this.pop();
            //This part is critical. If the stack was popped till it was empty,the span is i+1
            //since, the current number of elements in the stack was at most i and including the current element
            //itself, this gives i+1, else it's the difference between i and TOP  (this gives the number of elements POPPED+1)
            //for the current element
          span[i]= this.isEmpty()?i+1:i-this.array[TOP];
            this.push(i);
        }
        return span;
    }
/*2)Check if given string is a palindrome.
TIME COMPLEXITY:O(n/2) since we're traversing from both ends of the string.
 */
    public static boolean palindromeString(String passedString)
    {
        char[] charArray = passedString.toCharArray();
        int i = 0,j=charArray.length-1;
        boolean isPalindrome = true;
        while(i<=j)
        {
            if(charArray[i++]!=charArray[j--])
            isPalindrome = false;

        }
        return isPalindrome;
    }
/*3)// TBD
Convert the given expression from INFIX to POSTFIX*/
 */

    public  String infixToPostFix(String passedString)
    {
        char[] charArray = passedString.toCharArray();
        char ch;
        for(int i =0;i<charArray.length;i++)
        {
            ch = charArray[i];
            if(isSymbol(ch))
                pushToStack(ch)

    }
}


class DynamicArrayStack {
    int stackSize;
    int numElements, TOP;
    int[] array;

    DynamicArrayStack() {
        numElements = 0;
         stackSize=8;
        array = new int[stackSize];
        TOP = -1;
    }


    public boolean push(int x) {
        if (TOP ==  stackSize-1) {
            System.out.println("\nResizing stack");
            int newArray[] = new int[stackSize * 2];
            for (int i = 0; i < stackSize;i++)
                newArray[i] = array[i];
            this.array = newArray;
            this.stackSize = stackSize*2;
                return true;
        }
            array[++TOP] = x;
            return true;
    }


    public boolean isEmpty()
    {
        return (TOP==-1);
    }

    public boolean pop()
    {
        if(this.isEmpty()) {
            System.out.println("\n Underflow");
            return false;
        }
        else
            TOP--;
        return true;
    }

    public void display()
    {
        if(this.isEmpty())
            System.out.println("Stack is empty.");
        else {
            for (int i = TOP; i >= 0; i--)
            if (i == TOP)
            System.out.println("["+array[i] +"]<-TOP");
            else
            System.out.println("["+array[i]+"]");
        }

    }
}


class Node
{
    Node next;
    int data;
    Node()
    {
        next = null;
        data = 0;
    }
}


 class LinkedListStack
{
    Node TOP;
    int size;
    LinkedListStack()
    {
        TOP = null;
        size =0;
    }

    public boolean push(int x)
    {

        Node newNode = new Node();
        newNode.data = x;
        if(this.isEmpty())
            newNode.next=null;
        else
            newNode.next=TOP;
        TOP = newNode;
        size++;
        return true;
    }

    public boolean isEmpty()
    {
        return(size==0);
    }

    public boolean pop()
    {
        if(this.isEmpty()){
            System.out.println("Underflow");
            return false;
        }
        else
        TOP = TOP.next;
        size--;
        return true;
    }

    public boolean display()
    {
        if(this.isEmpty()) {
            System.out.println("Stack is empty");
            return false;
        }
        Node curNode = TOP;
        while(curNode!=null)
        {
            if(curNode == TOP)
            System.out.println("["+curNode.data+"] <- TOP");
            else
            System.out.println("["+curNode.data+"]");
            curNode = curNode.next;
        }
        return true;

    }

}



public class Stacks {

    //Stacks through array.
    public static void main(String[] args) {
	SimpleStack arrayStack = new SimpleStack();
    DynamicArrayStack dynArrayStack = new DynamicArrayStack();
    LinkedListStack llStack = new LinkedListStack();
        for(int i =1;i<=20;i++)
            llStack.push(i);
        llStack.display();
        for(int i =1;i<=5;i++)
            llStack.pop();
        llStack.display();
        for(int i =1;i<=20;i++)
            dynArrayStack.push(i);
        dynArrayStack.display();
        for(int i =1;i<=4;i++)
            dynArrayStack.pop();
        dynArrayStack.display();
        SimpleStack stack = new SimpleStack();
        int[] arr ={100, 80, 60, 70, 60, 75, 85};
        System.out.println(Arrays.toString(stack.maxSpanUsingStackGFOG(arr)));
        System.out.println(SimpleStack.palindromeString("asaa"));
        System.out.println("\nPushing 5 elements");
        for(int i =1;i<=101;i++)
            arrayStack.push(i);
        arrayStack.display();
        System.out.println("\nPopping 5  elements");
        for(int i =1;i<=6;i++)
            arrayStack.pop();
        arrayStack.display();

    }
}
