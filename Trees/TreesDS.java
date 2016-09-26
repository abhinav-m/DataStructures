import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class TreeNode
{
    int data;
    TreeNode leftNode,rightNode;
    TreeNode()
    {
        leftNode  = null;
        rightNode = null;
        data =0;
    }

    public void setData(int x)
    {
        this.data = x;
    }

    boolean isleafNode()
    {
        return(leftNode==null&&rightNode==null);
    }

    TreeNode getLeft()
    {
        return this.leftNode;
    }

    TreeNode getRight()
    {
        return this.rightNode;
    }




}

class BinaryTree {
    TreeNode root;

    BinaryTree() {
        root = null;
    }
//Preorder, postorder and inOrder can be simply understood by adding pre ,post or in in front of the root node
//the root node is traversed in that order.

    /*1) VISIT TREE IN INORDER -> Recursive*/
//This is INORDER traversal, visit left node, root node, then the right
//this is continued till all nodes are traversed in the tree
//Time complexity: O(n) for traversing the tree, O(n) recursive stack space
    public void printTreeinOrder(TreeNode node) {
        if (node != null) {
            printTreeinOrder(node.leftNode);
            System.out.println(node.data);
            printTreeinOrder(node.rightNode);
        }
    }

    //INORDER traversal -> Iterative
//Visits left node, root, then right in an iterative manner using stack.
//Visits left node till it reaches the end, storing intermediate values in stack
//Then stack is popped to do the same with the previous node.
//element popped is printed thus nodes are printed in left->right->parent manner for each subtree
//Time complexity: O(n) Space complexity: O(n) for stack.
    public void printTreeInOrderIter(TreeNode node) {
        Stack stack = new Stack();
        while (true) {
            while (node != null) {
                stack.push(node);
                node = node.leftNode;

            }
            if (stack.isEmpty())
                break;
            node = (TreeNode) stack.pop();
            System.out.println(node.data);
            node = node.rightNode;
        }
    }

    /*               */
    public void printTreePreOrderIter(TreeNode node) {
        Stack stack = new Stack();
        while (true) {
            while (node != null) {
                System.out.println(node.data);
                stack.push(node);
                node = node.leftNode;

            }
            if (stack.isEmpty())
                break;
            node = (TreeNode) stack.pop();
            node = node.rightNode;
        }
    }
/*2)VISIT TREE  IN PREORDER -> Recursive
* PREORDER -> root,left,right */

    public void printTreepreOrder(TreeNode node) {
        if (node != null) {
            System.out.println(node.data);
            printTreeinOrder(node.leftNode);
            printTreeinOrder(node.rightNode);
        }
    }


    /*3) VISIT TREE IN POSTORDER -> Recursive*/
//This is postorder traversal, visit left node, right then the root node.
//Note if the root becomes null, the previous function call is reached for the right node
//this is continued till all nodes are traversed in the tree
//Time complexity: O(n) for traversing the tree, O(n) recursive stack space
    public void printTreePostOrder(TreeNode root) {
        if (root != null) {
            printTreePostOrder(root.leftNode);
            printTreePostOrder(root.rightNode);
            System.out.println(root.data);
        }

    }

    /*2) VISIT TREE IN POSTORDER -> Iterative
    * Involves alot of base cases to be checked for.
    * Time complexity: O(n) for pushing and popping n elements onto the stack.
    * Space complexity: O(n) for auxilliary stack space.*/
    public void printTreePostOrderIter(TreeNode root) {
        Stack nodeStack = new Stack();
        nodeStack.push(root);
        //Previous node starts at null in the start since the root node has no previous.
        TreeNode previous = null;
        //Traverse stack till it becomes empty
        while (!nodeStack.isEmpty()) {
            //The top element is peeked. Note that the current element  can be "behind" the previous,
            //such a case arises when stack is popped and we are travelling back up the stack.
            TreeNode curNode = (TreeNode) nodeStack.peek();
            //Previous = null is a base case for the first node,the rest are for traversing down
            //the tree , left node first then the right node.
            if (previous == null || previous.leftNode == curNode || previous.rightNode == curNode) {
                //push the left node or the right node if they exist, and continue with the same procedure.
                if (curNode.leftNode != null)
                    nodeStack.push(curNode.leftNode);
                else if (curNode.rightNode != null)
                    nodeStack.push(curNode.rightNode);
            }
            //We can travel back up the tree from the left node OR the right node
            //the trick is to print the value when we are traversing from the right node
            //this means the left node is already traversed and we need to print the node now if
            //this condition fails,otherwise push the right nodes children on to the stack
            else if (curNode.leftNode == previous) {
                if (curNode.rightNode != null)
                    nodeStack.push(curNode.rightNode);
            }
            //We have reached a leaf node and we need to print.
            else {
                System.out.println(curNode.data);
                nodeStack.pop();
            }
            previous = curNode;
        }

    }

    /*PROBLEM STATEMENT: 3)FIND MAXIMUM element in a tree.
    If we solve this problem with the same approach as traversing the tree using recursion,
    and just keep saving the value we get from the left traversal and right traversal we can
    easily solve the problem by comparing the current node's value and the left and child value for successive trees.
    Note: IN case of null nodes, minimum value of integer will be returned and for leaf nodes, their value will be returned
    (and stored in left or right respectively)
    Time complexity: O(n) for traversal of n tree elements in a stack with space complexity O(n)
     */
    public int findMaxinTree(TreeNode node) {
        int curValue, left, right, max = Integer.MIN_VALUE;
        if (node != null) {
            curValue = node.data;
            left = findMaxinTree(node.leftNode);
            right = findMaxinTree(node.rightNode);
            if (left > right)
                max = left;
            else
                max = right;
            if (curValue > max)
                max = curValue;

        }

        return max;
    }

    /*LEVEL ORDER TRAVERSAL
    * Can be understood as similar to breadth first search. The nodes at a particular level l+1  are added to the queue, while the previous level
    * is dequeued and printed.
    * Time complexity: O(n) Space complexity: O(n) for queue.*/
    public void levelOrderTraversal(TreeNode node) {
        //Using a linkedlist as queue here
        LinkedList queueList = new LinkedList();
        queueList.addLast(node);
        System.out.println();
        while (!queueList.isEmpty()) {
            TreeNode curNode = (TreeNode) queueList.removeFirst();
            System.out.print(curNode.data);
            if (curNode.leftNode != null)
                queueList.addLast(curNode.leftNode);
            if (curNode.rightNode != null)
                queueList.addLast(curNode.rightNode);

        }

    }

/*PROBLEM STATEMENT 4) Check if an element exists in binary tree recursively
* The trick to understanding such recursive problems is to understand
* basics of tree pre order traversal and post order traversal techniques,
* which are easy to visualize thinking about a stack
* Time complexity: O(n) for travelling through the tree, Space complexity: O(n) for recursive stack space*/

    public boolean findElementRecur(TreeNode node, int data) {
        boolean check;
        //Base case, this can happen after a leaf node or if a link is null
        //or tree is empty.
        if (node == null)
            return false;
        else {
            //Check the current node for the data
            if (node.data == data)
                return true;
                //Check the left node recursively, if true return
            else if (findElementRecur(node.leftNode, data))
                return true;
                //Check right node, if this is not true we return false.
            else if (findElementRecur(node.rightNode, data))
                return true;
            return false;
        }
    }

/*PROBLEM STATEMENT 4) Check if an element exists in binary tree iteratively
* This can be done by iteratively travelling through the tree in pre post or inorder,
* Levelorder is also valid here
* The following uses level order traversal and  a queue to check if the element in the
* queue exists or not.
* Time complexity: O(n)  Space complexity:O(n) for queue */

    public boolean findElementIter(TreeNode node, int data) {
        LinkedList queueList = new LinkedList();
        if (node == null)
            return false;
        boolean isFound = false;
        queueList.addLast(node);
        while (!queueList.isEmpty()) {
            TreeNode curNode = (TreeNode) queueList.removeFirst();
            if (curNode.data == data) {
                isFound = true;
                break;
            }
            if (curNode.leftNode != null)
                queueList.addLast(curNode.leftNode);
            if (curNode.rightNode != null)
                queueList.addLast(curNode.leftNode);
        }
        return isFound;
    }

    /*PROBLEM STATEMENT 4) Insert an element into the tree
*   This problem is meant to be done in a level order traversal manner.
 * because if at any level of the tree , the next level has null pointers at the
 * left or right we need to insert there. If we used preorder postorder or inorder techniques,
 * we would end up traversing to the end of the tree thus wasting intermediate elements space.*/

    public boolean insertElement( int data) {
        LinkedList queueList = new LinkedList();
        if (root == null) {
            TreeNode newNode = new TreeNode();
            newNode.data = data;
            root = newNode;
            return true;
        }
        queueList.addLast(root);
        while (!queueList.isEmpty()) {
            TreeNode curNode = (TreeNode) queueList.removeFirst();
            if (curNode.leftNode != null)
                queueList.addLast(curNode.leftNode);
            else {
                TreeNode newNode = new TreeNode();
                newNode.data = data;
                curNode.leftNode = newNode;
                return true;
            }
            if (curNode.rightNode != null)
                queueList.addLast(curNode.rightNode);
            else {
                TreeNode newNode = new TreeNode();
                newNode.data = data;
                curNode.rightNode = newNode;
                return true;
            }
        }
     return false;
    }



    /* PROBLEM STATEMENT 5)Find out the size of the tree
     Similar to preOrder traversal this will add 1 to size of tree per node.
     First the left subtree will be traversed then the right one , each recursively returning
     the current size.
     Time complexity:O(n) Space complexity:O(n) for recursive stack space*/

    int sizeOfTree(TreeNode node)
    {
        int size =1;
        if(node==null)
            return 0;
        size += sizeOfTree(node.leftNode);
        size += sizeOfTree(node.rightNode);
        return size;
    }

     /* PROBLEM STATEMENT 5)Find out the size of the tree
   This is the solution given in karumanchi, although less complex this does
   simplify the code a little, node we are simply adding a constant one and returning the value
   here , the left subtree will be traversed first here as well till we encounter a leaf node which will return 0
   and traverse to the right node in a similar manner. Although the given function is same it does require a bit of thinking.*/

    int size(TreeNode node)
    {

        if(node==null)
            return 0;
        else return(size(node.leftNode)+1+size(node.rightNode));
    }

    /* PROBLEM STATEMENT 6)Find the size of the tree using level order traversal instead of recursion
     *  Time complexity:O(n) Space complexity:O(n) for Queue*/
    public int sizeOfTreeIter(TreeNode node) {
        //Using a linkedlist as queue here
        int size =0;
        if(node == null)
        return size;

        LinkedList queueList = new LinkedList();
        queueList.addLast(node);
        while (!queueList.isEmpty()) {
            TreeNode curNode = (TreeNode) queueList.removeFirst();
            size++;
            System.out.print(curNode.data);
            if (curNode.leftNode != null)
                queueList.addLast(curNode.leftNode);
            if (curNode.rightNode != null)
                queueList.addLast(curNode.rightNode);

        }
    return size;
    }

    /*PROBLEM STATEMENT 7)Print a tree in reverse order (Assuming level order traversal)
    * Time complexity: O(n) Space complexity: O(n) For storing the queue.*/
    public void printTreeReverseLevelOrder(TreeNode node) {
        if (node == null)
            System.out.println("\nList is empty");
        else {
            LinkedList queueList = new LinkedList();
            queueList.addLast(node);
            Stack reversedTree = new Stack();
            while (!queueList.isEmpty()) {
                TreeNode curNode = (TreeNode) queueList.removeFirst();
                reversedTree.push(curNode);
                System.out.print(curNode.data);
                if (curNode.leftNode != null)
                    queueList.addLast(curNode.leftNode);
                if (curNode.rightNode != null)
                    queueList.addLast(curNode.rightNode);

            }
            System.out.println("Reversed = ");
            while (!reversedTree.isEmpty()) {
                TreeNode curNode = (TreeNode)reversedTree.pop();
                System.out.println("\n" +curNode.data);
            }
        }
    }
    /* PROBLEM STATEMENT 8)Print the height of the given tree
    * Recurse till the left node then,right node(whichever is at most depth will give 1 or more value)
    * if they are equal 1 will be returned Keep adding edges as you go upwards.
    * NOTE: This algorithm considers the element at first level at height 1, NOT ZERO.
    * This can be understood in a manner similar to finding the maximum element in the tree where
    * we recursively traverse each node and compare to the previous node
    * Similar to DFS as we are traversing the depth of the tree first (UNLIKE LEVEL ORDER where we
    * traverse a complete level of BFS)
    * Time complexity:O(n) Space complexityO(n) for recursive stack space.*/
    public int treeHeight(TreeNode node)
    {
    if(node==null||(root.leftNode==null&&root.rightNode==null))
        return 0;
    else {
        int left = treeHeight(node.leftNode);
        int right = treeHeight(node.rightNode);
        if (left > right)
            return left+1;
        else
            return right+1;
    }
    }
}

public class TreesDS {

    public static void main(String[] args) {
	BinaryTree tree = new BinaryTree();
    for(int i = 1;i<=7;i++)
    tree.insertElement(i);
    System.out.println("Height of tree ="+tree.treeHeight(tree.root));
    tree.levelOrderTraversal(tree.root);
    tree.printTreeReverseLevelOrder(tree.root);
    System.out.println("Size of tree = "+ tree.sizeOfTreeIter(tree.root));
    System.out.println("Size of tree ="+tree.size(tree.root));
    System.out.println("Element is in tree ="+tree.findElementRecur(tree.root,3));
    System.out.println("Maximum = "+tree.findMaxinTree(tree.root));

    }
}
