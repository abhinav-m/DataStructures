'''
Basic hashtable implementation
'''
class MyHashMap:

    def __init__(self,capacity = pow(10,5)):
        self.capacity = capacity
        self.data = [None for _ in range(self.capacity)]
        self.load_factor = 0.75
        self.size = 0
        

    def put(self, key: int, value: int) -> None:
        
        hashed_key = key % self.capacity
        # If key exists,
        # Linearly probe 
        # if self.data[hashed_key]:
        #     while self.data[hashed_key]:
        #         hashed_key += 1
        #         hashed_key = 0 if hashed_key == self.size else hashed_key
                
            
        self.data[hashed_key] = value
        self.size += 1
        
        # if self.size >= (self.load_factor * self.capacity):
        #     # Rehash here
        #     self.capacity = 2 * self.capacity 
            
   
            
            
        

    def get(self, key: int) -> int:
        hashed_key = key % self.capacity
        
        data = self.data[hashed_key]
        
        return data if data is not None else -1
        

    def remove(self, key: int) -> None:
        
        hashed_key = key % self.capacity
        
        self.data[hashed_key] = None
        
        


# Your MyHashMap object will be instantiated and called as such:
# obj = MyHashMap()
# obj.put(key,value)
# param_2 = obj.get(key)
# obj.remove(key)