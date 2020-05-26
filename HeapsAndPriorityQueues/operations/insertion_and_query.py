
import sys
sys.path.insert(1, "/home/abhinav/Work/dsa/DataStructures/HeapsAndPriorityQueues")
# from Tree import Node
from Heaps import Heap


def create_heap():
    heap_inst = Heap()

    heap_inst.insert_node(3)
    heap_inst.insert_node(1)
    heap_inst.insert_node(5)
    heap_inst.insert_node(2)
    heap_inst.insert_node(4)
    heap_inst.insert_node(6)
    return heap_inst




heap_inst = create_heap()

