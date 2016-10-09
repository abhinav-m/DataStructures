public class DisjointSets {

    int[] array;
    int capacity;

    DisjointSets(int size)
    {
    capacity = size;
    array = new int[capacity];
    for(int i = 0;i<capacity;i++)
        array[i] = i;

    }
    //returns root of the set.
    int find(int X)
    {
        if (!(X>=0&&X<=capacity))
        return -1;
        if(array[X]==X)
        return X;
        else
       return( find(array[X]));

    }

    void UNION(int root1,int root2)
    {
        if(find(root1)==find(root2))
            return;
        else if(!(root1>=0&&root1<=capacity&&root2>=0&&root2<=capacity))
            return;
        else
            array[root1]=root2;
    }
    public static void main(String[] args) {
	// write your code here
    }
}
