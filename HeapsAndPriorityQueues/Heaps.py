class Heap:
    def __init(self,max_size,heap_type="MAX_HEAP"):
        self.max_size = max_size
        self.size = 0
        self.type = heap_type
        # Initializing array to make it more memory efficient
        self.heap = [0] * max_size


    # Left child of element at index k , if exists
    def left_child(self,k):
        child_pos = 2 * k + 1
        # count is the actual number of elements in the heap right now
        if (child_pos < self.size):
            return child_pos
        return -1

    # Right child of element at index k , if exists
    def right_child(self,k):
        child_pos = (2*k) + 2
        if(child_pos < self.size):
            return child_pos
        return -1
    
    # Parent index 
    def parent_node(self,k):
        parent_index = (k - 1)//2
        # Boundary check
        if(parent_index < self.size and parent_index >= 0):
            return parent_index
        return -1

    def max_el(self,k):
        if(self.size == 0):
            return -1
        else:
            return self.heap[0]