


class SimpleStack
{
    static final int maxSize = 100;
    int numElements,TOP;
    int[] array;

    SimpleStack()
    {
        array = new int[maxSize];
        numElements=0;
        TOP=0;
    }

   public boolean push (int x)
    {
        if(numElements==maxSize) {
            System.out.println("\nStack overflow");
            return true;
        }
        else {
            array[numElements++] = x;
            TOP = numElements-1;
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



public class Stacks {

    //Stacks through array.
    public static void main(String[] args) {
	SimpleStack arrayStack = new SimpleStack();
    DynamicArrayStack dynArrayStack = new DynamicArrayStack();
        for(int i =1;i<=20;i++)
            dynArrayStack.push(i);
        dynArrayStack.display();
        for(int i =1;i<=4;i++)
            dynArrayStack.pop();
        dynArrayStack.display();


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
