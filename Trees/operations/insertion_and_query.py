
import sys
sys.path.insert(1, "/home/abhinav/Work/dsa/DataStructures/Trees")
from Tree import Node


def create_tree():
    tree_inst = Node(1)
    tree_inst.l = Node(2)
    tree_inst.r = None
    tree_inst.l.l = Node(4)
    tree_inst.l.r = Node(4)
    # tree_inst.r.l = Node(6)
    # tree_inst.r.r = Node(7)
    return tree_inst


tree_inst = create_tree()

# Pre order traversal of tree : data -> L -> R
Node.level_order_traversal(tree_inst)


# # Pre order traversal of tree : data -> L -> R
# tree_inst.print_tree(tree_inst, "IN_ORDER")
