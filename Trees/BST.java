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
}



public class BST {

    public static void main(String[] args)
    {
	BinarySearchTree bSTree = new BinarySearchTree();
    BinarySearchTree bSTree2 = new BinarySearchTree();

    bSTree2.insertElement(bSTree2.root,4);
    bSTree2.insertElement(bSTree2.root,2);
    bSTree2.insertElement(bSTree2.root,8);
    bSTree2.insertElement(bSTree2.root,5);
    bSTree2.insertElement(bSTree2.root,10);
    bSTree2.insertElement(bSTree2.root,7);
    bSTree2.insertElement(bSTree2.root,6);
    if(BinarySearchTree.findNodeIter(bSTree.root,13)!=null)
    System.out.println("Node found");
    bSTree2.deleteElement(bSTree2.root,10);
    System.out.println(BinarySearchTree.minElementIter(bSTree.root));
    System.out.println(BinarySearchTree.maxElementIter(bSTree.root));

    }
}
