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

/*1) VISIT TREE IN POSTORDER -> Recursive*/
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
/*2) VISIT TREE IN POSTORDER -> Iterative*/
    public void printTreePostOrderIter(TreeNode root) {
    Stack nodeStack = new Stack();
    nodeStack.push(root);
        while(!nodeStack.isEmpty())
        {
            while(root.leftNode!=null) {
                root = root.leftNode;
                nodeStack.push(root);
            }


            System.out.println(root.data);
            nodeStack.pop();
            root = (TreeNode)nodeStack.peek();
           String
        String[] copyOfString = new String[size];
            while(root.rightNode!=null) {
                root = root.rightNode;
                nodeStack.push(root.rightNode);
            }

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
    tree.printTreePostOrderIter(tree.root);

    }
}
