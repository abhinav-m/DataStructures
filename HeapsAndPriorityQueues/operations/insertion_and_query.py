
import sys
sys.path.insert(1, "/home/abhinav/Work/practice/DataStructures/HeapsAndPriorityQueues")
# from Tree import Node
from Heaps import Heap


def create_heap():
    heap_inst = Heap(10)
    
    heap_inst.insert_element(15)
    heap_inst.insert_element(12)
    heap_inst.insert_element(10)
    heap_inst.insert_element(8)
    heap_inst.insert_element(4)
    heap_inst.insert_element(3)

    heap_inst.properties()
    
    return heap_inst




heap_inst = create_heap()

heap_inst.delete_max()
heap_inst.properties()

print("INSERTING ELEMENT")
heap_inst.insert_element(11)
heap_inst.properties()


print("DELETING ELEMENT")
heap_inst.delete_max()
heap_inst.properties()
