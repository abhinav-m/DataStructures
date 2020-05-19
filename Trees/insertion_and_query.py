from Tree import Node

tree_inst =  Node(5)
tree_inst.l =  Node(3)
tree_inst.r = Node(4)
tree_inst.l.l = Node(2)
tree_inst.l.r = None


# Pre order traversal of tree : data -> L -> R
tree_inst.print_tree(tree_inst)