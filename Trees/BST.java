class Node
{
    Node leftNode;
    Node rightNode;
    int key;

    Node(int data)
    {
        leftNode = null;
        rightNode = null;
        key =data;
    }
}

/* Binary search tree is a special kind of binary tree which is specially designed for searching purpose.
In a normal tree , if we need to find an element we need to traverse both the left and right subtree to
find the given element. Due to this the worst case time complexity is O(n).
In a binary search tree, we impose restrictions on what kind of data may be contained in a node, this
reduces the average searching time in the tree to log(n) as we can skip entire subtrees based on the
current node's data or key.
 */
class BinarySearchTree {
    Node root;
    int numNodes;

    BinarySearchTree() {
        root = null;
        numNodes = 0;
    }

    /*Finding  a node in the binary search tree
    * Time complexity: O(n) for a skew tree(with all elements same)
    * Space complexity: O(n) for recursive stack space.*/
    public static Node findNode(Node curNode, int data) {
        //Base case when we have reached the end of the left or right subtree
        //and curNode = null
        if (curNode == null)
            return null;
        //Traverse left or right subtree based on the key of the current node.
        if (data > curNode.key)
            return (findNode(curNode.rightNode, data));
        else if (data < curNode.key)
            return (findNode(curNode.leftNode, data));
        return curNode;
    }

    /*Time complexity is same here:O(n) but no stack space needed
       for recursion
        */
    public static Node findNodeIter(Node curNode, int data) {
        while (curNode != null) {
            if (curNode.key == data)
                return curNode;
            else if (data > curNode.key)
                curNode = curNode.rightNode;
            else
                curNode = curNode.leftNode;
        }
        return curNode;
    }

    /*Time complexity for both minimum and maximum element is
    O(n) in case of  skew trees and space complexity: O(n) for
    recursive stack space.
     */
    public static int minElement(Node curNode) {
        if (curNode.leftNode == null)
            return curNode.key;
        else
            return (minElement(curNode.leftNode));
    }

    public static int minElementIter(Node curNode) {
        if (curNode == null)
            return -1;
        while (curNode.leftNode != null)
            curNode = curNode.leftNode;
        return curNode.key;
    }

    public static int maxElement(Node curNode) {
        if (curNode.rightNode == null)
            return curNode.key;
        else
            return (maxElement(curNode.rightNode));
    }

    public static int maxElementIter(Node curNode) {
        if (curNode == null)
            return -1;
        while (curNode.rightNode != null)
            curNode = curNode.rightNode;
        return curNode.key;
    }

    /*Inserting an element in a binary search tree
    Note the base case can be reached in two conditions (If the tree is empty or we have reached the position where we wanna insert
    if the tree is empty , we should mark the root too with the insertion, else return the new node to the previous level of the stack and make
    it the child of the current node.
    Also, if a element is already present, it is NOT INSERTED in the tree again.
     */
    public Node insertElement(Node curNode, int data) {
        if (curNode == null) {
            Node newNode = new Node(data);
            curNode = newNode;
            if (root == null)
                root = newNode;
            return newNode;
        } else {
            if (data > curNode.key)
                curNode.rightNode = insertElement(curNode.rightNode, data);
            else if (data < curNode.key)
                curNode.leftNode = insertElement(curNode.leftNode, data);
        }
        return curNode;
    }

    /*DELETION of element from binary search tree
    * DELETION of element in binary search tree can involve 3 cases:
    * When the element to be delted is a leaf node ->Recursively traverse to that element, and return null to the previous level in
    * stack thus making the link "null" and deleting the element
    * When the element to be deleted has one child ->Recursively traverse to the element,make it "null" and return the child to the
    * previous level in stack making it the new child of previous level.
    * When the element has both children. In this case, we traverse to the left subtree and find the largest element in the left subtree
    * this will be the rightmost leafnode in the left subtree(we recursively delete this using the same function) and this is the
    * candidate for replacement of the current element. We copy it's key to the element to be deleted and delete the leaf node of the rightmost
    * element in the left subtree! case 2 above.
    * Time complexity:O(n) for skew tree space complexity:O(n) for recursive stack space.*/
    public Node deleteElement(Node curNode, int data) {
        if(curNode==null)
        {
            System.out.println("Not found");
            return null;
        }
        else if (curNode.key == data) {
            if (curNode.leftNode == null && curNode.rightNode == null)
                return null;
            else if (curNode.leftNode != null && curNode.rightNode != null) {
                Node tempNode;
                int tempData = curNode.key;
                tempNode = findNode(curNode, BinarySearchTree.maxElement(curNode.leftNode));
                curNode.key = tempNode.key;
                curNode.leftNode = deleteElement(curNode.leftNode, curNode.key);
            } else
                return (curNode.leftNode != null ? curNode.leftNode : curNode.rightNode);

        }
        else
        {
            if(data<curNode.key)
            curNode.leftNode = deleteElement(curNode.leftNode,data);
            else
            curNode.rightNode = deleteElement(curNode.rightNode,data);
        }
        return curNode;
    }

    /*PROBLEM STATEMENT 1) Find LCA of two nodes in a given binary tree
    * Karumanchi implementation, However this fails when the two nodes have parent->child relation
    * between them*/
    public static Node LCA(Node curNode,int key1,int key2)
    {
       while(true)
       {
    if(curNode==null)
      return null;
    if(curNode.key<key1&&curNode.key>key2||curNode.key>key1&&curNode.key<key2)
        return curNode;
       if(curNode.key<key1)
           curNode = curNode.rightNode;
           else
           curNode =curNode.leftNode;

       }

    }

    /* This solution is intuitively easier and better than the one above.
    and handles the parent->child relation as well.
    Reaching a null node is the base case, and we return from there LCA
    isn't found in this case.
    Else we check whether they keys lie towards left of the current root(less than the current root)
    or the right of the current root (greater than current root) if both of these conditions fail,
    we return the current node as it will be the LCA of the two nodes.
     Time complexity: O(n) Space complexity: O(n) for recursive stack space.*/
    public static Node LCAGFOG(Node curNode,int key1,int key2)
    {
        if(curNode==null)
        return null;
        if(curNode.key>key1&&curNode.key>key2)
        return(LCAGFOG(curNode.leftNode,key1,key2));
        else if(curNode.key<key1&&curNode.key<key2)
        return(LCAGFOG(curNode.rightNode,key1,key2));
        return curNode;
    }

    /*Problem statement 2): Check whether the given binary tree is BST
    * Although this method looks correct, it's not so.
    * We can't simply check each node at a level with it's left and right children.
    * The problem might be at different levels in case of a tall tree.*/
    public static boolean isBST(Node curNode)
    {
        if(curNode==null)
        return true;
        else
        {
            if(curNode.leftNode!=null&&curNode.key<curNode.leftNode.key)
                return false;
            if(curNode.rightNode!=null && curNode.key>curNode.rightNode.key)
                return false;
            if(!isBST(curNode.leftNode)||!isBST(curNode.rightNode))
                return false;
            return true;
        }
    }
/* SOLUTION 1: This involves recursively checking the maximum element in the
left subtree and confirming that it is less than the current root (otherwise the BST property fails)
and the minimum element in the right subtree and ensuring that it is greater than the root.

 */
    public static boolean isBSTMinMax(Node curNode)
    {
        if(curNode==null)
            return true;
        else
        {
            if(curNode.leftNode!=null&&curNode.key<BinarySearchTree.maxElement(curNode.leftNode))
                return false;
            if(curNode.rightNode!=null&&curNode.key>BinarySearchTree.minElement(curNode.rightNode))
                return false;
            if(!isBSTMinMax(curNode.leftNode)||!isBSTMinMax(curNode.rightNode))
                return false;
            return true;

        }
    }
/* SOLUTION 2: This method involves a utility function and a root function.
We use the utility function to supply the values of maximum and minimum as
the maximum integer value and the minimum integer value, then proceed to
check the tree recursively, changing the maximum value to the current value
when we traverse left (Where each node should be less than the current) and
minimum value as current when we traverse right (where each node should be
greater than the current node.Since we reassign max and min we check eacch
level and if any level fails, we return false.
NOTE: The driver / root function exists so that we can have the minimum and
maximum value passed implicitly to check if the tree is BST.
Time complexity: O(n) Space complexity: O(n)
for recursive stack space.
This is better than the previous method because of traversing the elements only once ,
unlike the previous method.

 */
    public static boolean isBSTEff(Node curNode)
    {
        return isBSTEff(curNode,Integer.MAX_VALUE,Integer.MIN_VALUE);
    }

    public static boolean isBSTEff(Node curNode,int max,int min)
    {
        if(curNode==null)
            return true;
        else if(curNode.key<=min||curNode.key>=max)
        return false;
       return(isBSTEff(curNode.leftNode,curNode.key,min)&&isBSTEff(curNode.rightNode,max,curNode.key));
    }
/*SOLUTION 3:Another method which is possible is to traverse the tree
INORDER which would result in a sorted list. If while traversing inorder,
elements are found to be misplaced return false, else true.
NOTE: It is important to note that the leftmost element (or the first element to be reached in
inorder is the minimum element, thus this needs to be initialised as the prevNode initially,
and we move up from there,checking if previous node is greater than or equal to current(for duplicate
check) , if we find it to be true , list is not in ascending order thus, it's not a BST
Time complexity:O(n) Space complexity: O(n)
 */

    public static boolean isBSTInOrder(Node curNode)
    {
        //Driver function to check whether the current node (passed as root)
        //is null or not, this utilizes the driver function to check for
        //empty trees and also makes the prevNode = null (which is implicit for
        //the problem with the root node) and doesn't have to be explicitly passed.
        if(curNode==null)
        return false;
        else
        return isBSTInOrder(curNode,null);
    }
    public static boolean isBSTInOrder(Node curNode,Node prevNode)
    {
        //Base case
        if(curNode==null)
            return true;
        else
        {
            //Traversing in in order -> left most node traversed first
            //if we get false anywhere while returning , the tree is not
            //BST.
            if(!isBSTInOrder(curNode.leftNode,curNode))
            return false;
            //prevNode will be null in initial traversal to the left node,
            //we dont need to compare till it has been initialised with the
            //"first element of the sorted list", after that we can compare
            if(prevNode!=null&&prevNode.key>=curNode.key)
            return false;
            //NOTE: The prevNode will be initialised to the current node while traversing back from
            //left most element. Thus we are actually not using prevNode while traversing down the
            //stack but while traversing back up from the stack.
            prevNode = curNode;
            return(isBSTInOrder(curNode.rightNode,curNode));
        }
    }
}



public class BST {

    public static void main(String[] args)
    {
	BinarySearchTree bSTree = new BinarySearchTree();
    BinarySearchTree bSTree2 = new BinarySearchTree();
    BinarySearchTree notBST = new BinarySearchTree();
        notBST.root = new Node(6);
        notBST.root.leftNode = new Node(2);
        notBST.root.rightNode = new Node(8);
        notBST.root.leftNode.leftNode = new Node(1);
        notBST.root.leftNode.rightNode = new Node(9);
    System.out.println("IS BST ? Correct method ="+BinarySearchTree.isBSTEff(notBST.root,Integer.MAX_VALUE,Integer.MIN_VALUE)+"Wrong method = "+BinarySearchTree.isBST(notBST.root));
    bSTree2.insertElement(bSTree2.root,4);
    bSTree2.insertElement(bSTree2.root,2);
    bSTree2.insertElement(bSTree2.root,8);
    bSTree2.insertElement(bSTree2.root,5);
    bSTree2.insertElement(bSTree2.root,10);
    bSTree2.insertElement(bSTree2.root,7);
    bSTree2.insertElement(bSTree2.root,6);
    if(BinarySearchTree.findNodeIter(bSTree.root,13)!=null)
    System.out.println("Node found");
    System.out.println("LCA ="+BinarySearchTree.LCAGFOG(bSTree2.root,8,10).key);
    bSTree2.deleteElement(bSTree2.root,10);
    System.out.println(BinarySearchTree.minElementIter(bSTree.root));
    System.out.println(BinarySearchTree.maxElementIter(bSTree.root));

    }
}
