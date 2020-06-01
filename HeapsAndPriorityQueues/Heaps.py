class Heap:
    def __init__(self,max_size,heap_type="MAX_HEAP"):
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

    # Element index to perlocate or heapify down
    def percolate_down(self, idx):
        l_idx = self.left_child(idx)
        r_idx = self.right_child(idx)
        
        # Setting max index value to curent index.
        max_idx = idx

        if(l_idx != -1 and self.heap[l_idx]  > self.heap[max_idx]):
            max_idx = l_idx
        if(r_idx != -1 and self.heap[r_idx] > self.heap[max_idx]):
            max_idx = r_idx
        

        # Swap elements at indexes and go down the tree if current and max_idx differ
        # Call heapify again to check if operation is complete ( and indexes differ)
        if(max_idx != idx):
            self.heap[idx] ,self.heap[max_idx] = self.heap[max_idx],self.heap[idx]
            self.percolate_down(max_idx)

    def delete_max(self):
        # Heap is empty, return -1
        if self.size == 0:
            return -1
        else:
            # First element is deleted from the heap always. ( Both max and min heap case)
            deleted_el = self.heap[0]
            # Last( smallest leaf element assigned to heap)
            last_el = self.heap[self.size-1]
            # First element assigned to smallest element and percolated down.
            self.heap[0] = last_el
            # Since size is reduced, percolate_down will not consider the last element anymore
            self.size = self.size -1
            # Perlocate down the first index element back to it's place
            self.percolate_down(0)
            # Return deleted element after completion
            return deleted_el

    def properties(self):
        print("******HEAP PROPERTIES*******")
        print("HEAP ARRAY: ",self.heap)
        print("CURRENT SIZE: ",self.size)
        print("MAX SIZE: ",self.max_size);
        print("TYPE: ",self.type);
        print("******HEAP PROPERTIES*******")
       
       
    def insert_element(self,data):
        # Increase size by 1
        self.size += 1
        # Pointer to the last index where
        # element will be initially stored.
        idx = self.size -1 
        # self.heap[idx] = data doesn't need to be done since index of parent node will exist 
        
        # Swap starting from last index, going up parents 
        while(idx >= 0 and self.parent_node(idx)!=-1 and data > self.heap[self.parent_node(idx)]):
            # swap parent and curent node starting from bottom till at correct position
            self.heap[idx],self.heap[self.parent_node(idx)] =  self.heap[self.parent_node(idx)],data
            idx = self.parent_node(idx)
        
        # Base case to handle insertion of new element or insertion at bottom 
        self.heap[idx] = data
            
