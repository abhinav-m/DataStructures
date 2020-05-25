
import sys
sys.path.insert(1, "/home/abhinav/Work/dsa/DataStructures/HeapsAndPriorityQueues")
# from Tree import Node
from Heaps import Heap


def create_heap():
    heap_inst = Node()

    heap_inst.insert_node(3)
    heap_inst.insert_node(1)
    heap_inst.insert_node(5)
    heap_inst.insert_node(2)
    heap_inst.insert_node(4)
    heap_inst.insert_node(6)
    return heap_inst




heap_inst = create_heap()

# In order traversal of BST gives sorted list
heap_inst.in_order()

heap_inst = Node.delete_node(heap_inst,5)

print("AFTER DELETION")
# In order traversal of BST gives sorted list
heap_inst.in_order()

# # Pre order traversal of tree : data -> L -> R
# heap_inst.print_tree(heap_inst, "IN_ORDER")
