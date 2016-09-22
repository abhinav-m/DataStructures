
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

class BinaryTree
{
    TreeNode root;
    BinaryTree()
    {
        root = new TreeNode();
    }

   public void printTree(TreeNode root)
   {
       if(root.leftNode==null&&root.rightNode==null)
       {
           System.out.println(root.data);
             return;
       }

       printTree(root.leftNode);
       printTree(root.rightNode);
     System.out.println(root.data);
       return;
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
    tree.printTree(tree.root);

    }
}
