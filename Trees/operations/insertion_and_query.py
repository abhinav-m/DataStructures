import sys

sys.path.insert(1,"/home/abhinav/Work/practice/DataStructures/Trees")

from Tree import Node



def create_tree():
    tree_inst =  Node(1)
    tree_inst.l =  Node(2)
    tree_inst.r = Node(3)
    tree_inst.l.l = Node(4)
    tree_inst.l.r =  Node(5)
    tree_inst.r.l = Node(6)
    tree_inst.r.r =  Node(7)
    return tree_inst


tree_inst = create_tree()


# Pre order traversal of tree : data -> L -> R
tree_inst.print_tree(tree_inst,"IN_ORDER")