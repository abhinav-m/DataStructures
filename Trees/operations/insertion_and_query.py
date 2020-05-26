
import sys
sys.path.insert(1, "/home/abhinav/Work/dsa/DataStructures/Trees")
# from Tree import Node
from BST import Node


def create_tree():
    tree_inst = Node()

    tree_inst.insert_node(3)
    tree_inst.insert_node(1)
    tree_inst.insert_node(5)
    tree_inst.insert_node(2)
    tree_inst.insert_node(4)
    tree_inst.insert_node(6)
    return tree_inst




tree_inst = create_tree()

# In order traversal of BST gives sorted list
tree_inst.in_order()

tree_inst = Node.delete_node(tree_inst,5)

print("AFTER DELETION")
# In order traversal of BST gives sorted list
tree_inst.in_order()

# # Pre order traversal of tree : data -> L -> R
# tree_inst.print_tree(tree_inst, "IN_ORDER")
