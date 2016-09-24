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
        root = new TreeNode();
    }
//Preorder, postorder and inOrder can be simply understood by adding pre ,post or in in front of the root node
//the root node is traversed in that order.

    /*1) VISIT TREE IN INORDER -> Recursive*/
//This is INORDER traversal, visit left node, root node, then the right
//this is continued till all nodes are traversed in the tree
//Time complexity: O(n) for traversing the tree, O(n) recursive stack space
    public void printTreeinOrder(TreeNode node)
    {
        if(node!=null)
        {
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
    public void printTreeInOrderIter(TreeNode node)
    {
        Stack stack = new Stack();
        while(true)
        {
            while(node!=null)
            {
                stack.push(node);
                node = node.leftNode;

            }
            if(stack.isEmpty())
            break;
            node = (TreeNode)stack.pop();
            System.out.println(node.data);
            node = node.rightNode;
        }
    }

    /*               */
    public void printTreePreOrderIter(TreeNode node)
    {
        Stack stack = new Stack();
        while(true)
        {
            while(node!=null)
            {
                System.out.println(node.data);
                stack.push(node);
                node = node.leftNode;

            }
            if(stack.isEmpty())
                break;
            node = (TreeNode)stack.pop();
            node = node.rightNode;
        }
    }
/*2)VISIT TREE  IN PREORDER -> Recursive
* PREORDER -> root,left,right */

    public void printTreepreOrder(TreeNode node)
    {
        if(node!=null)
        {
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
        while(!nodeStack.isEmpty())
        {
        //The top element is peeked. Note that the current element  can be "behind" the previous,
        //such a case arises when stack is popped and we are travelling back up the stack.
        TreeNode curNode  = (TreeNode)nodeStack.peek();
        //Previous = null is a base case for the first node,the rest are for traversing down
        //the tree , left node first then the right node.
        if(previous==null||previous.leftNode==curNode||previous.rightNode==curNode)
        {
        //push the left node or the right node if they exist, and continue with the same procedure.
        if(curNode.leftNode!=null)
        nodeStack.push(curNode.leftNode);
        else if(curNode.rightNode!=null)
        nodeStack.push(curNode.rightNode);
        }
        //We can travel back up the tree from the left node OR the right node
        //the trick is to print the value when we are traversing from the right node
        //this means the left node is already traversed and we need to print the node now if
        //this condition fails,otherwise push the right nodes children on to the stack
        else if (curNode.leftNode == previous)
        {
        if(curNode.rightNode!=null)
        nodeStack.push(curNode.rightNode);
        }
        //We have reached a leaf node and we need to print.
        else{
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
    public int findMaxinTree(TreeNode node)
    {
        int curValue,left,right,max = Integer.MIN_VALUE;
        if(node!=null) {
            curValue = node.data;
            left = findMaxinTree(node.leftNode);
            right = findMaxinTree(node.rightNode);
            if (left>right)
                max = left;
            else
                max =right;
            if(curValue>max)
                max = curValue;

        }

          return max;
    }

/*LEVEL ORDER TRAVERSAL
* Can be understood as similar to breadth first search. The nodes at a particular level l+1  are added to the queue, while the previous level
* is dequeued and printed.
* Time complexity: O(n) Space complexity: O(n) for queue.*/
    public void levelOrderTraversal(TreeNode node)
    {
        //Using a linkedlist as queue here
        LinkedList queueList = new LinkedList();
        queueList.addLast(node);
        System.out.println();
        while(!queueList.isEmpty())
        {
            TreeNode curNode = (TreeNode) queueList.removeFirst();
            System.out.print(curNode.data);
            if(curNode.leftNode!=null)
            queueList.addLast(curNode.leftNode);
            if(curNode.rightNode!=null)
            queueList.addLast(curNode.rightNode);

        }

    }


}

public class TreesDS {

    public static void main(String[] args) {
	BinaryTree tree = new BinaryTree();
    tree.root.data =1;
    tree.root.leftNode = new TreeNode();
    tree.root.leftNode.data =2;
    tree.root.leftNode.leftNode = new TreeNode();
    tree.root.leftNode.rightNode = new TreeNode();
    tree.root.leftNode.leftNode.data = 4;
    tree.root.leftNode.rightNode.data = 5;

    tree.root.rightNode = new TreeNode();
    tree.root.rightNode.data = 3;
    tree.root.rightNode.leftNode = new TreeNode();
    tree.root.rightNode.rightNode = new TreeNode();
    tree.root.rightNode.leftNode.data = 6;
    tree.root.rightNode.rightNode.data = 7;
    tree.levelOrderTraversal(tree.root);
    System.out.println("Maximum = "+tree.findMaxinTree(tree.root));

    }
}
