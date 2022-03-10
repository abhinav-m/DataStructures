'''
Hashtable implementation with
load factor, rehashing and separate chaining (Arraylist)

Hashing is an example of space-time tradeoff. 
If there exists a condition where the memory is infinite, 
single memory access using the key as an index in a (potentially huge) 
array would retrieve the value—which also implies possible key values are huge.
 On the other hand, if time is infinite, the values can be stored in minimum 
 possible memory and a linear search through the array can be used to retrieve the element.
 [16]: 458  In separate chaining, the process involves building a linked list with key-value 
 pair for each search array index. The collided items are chained together through a single linked list, which can be traversed to access the item with a unique search key.[16]: 464  Collision resolution through chaining
i.e. with a linked list is a common method of implementation.
'''

_PRIMES = [2**4 - 3, 
           2**5 - 2, 
           2**6 - 3, 
           2**7 - 1,
           2**8 - 5,
           2**9 - 3,
           2**10 - 3,
           2**11 - 9,
           2**12 - 3,
           2**13 - 1,
           2**14 - 3,
           2**15 - 1,
           2**16 - 1,
           2**17 - 1,
           2**18 - 5,
           2**19 - 1,
           2**20 - 3,
           2**21 - 9,
           2**22 - 3,
           2**23 - 1,
           2**24 - 3,
           2**25 - 3,
           2**26 - 5,
           2**27 - 3,
           2**28 - 5,
           2**29 - 3,
           2**30 - 3,
           2**31 - 1,
           2**32 - 5]

class HashTable:

    def __init__(self,prime_idx=0,load_factor=0.75):
        self.prime_idx = prime_idx
        
        # Setting a prime capacity helps in hash table uniformity
        # when using modular operator
        # because of different values obtained from modulus operation
        self.capacity = _PRIMES[self.prime_idx]
        # factor after which the table will be rehashed
        self.load_factor = load_factor

        # Actual data which will be kept in
        # the format of hashed_key -> value (ArrayList) 
        # collisions will result in multiple values being added to the hashtable
        self.data = [None for _ in range(self.capacity)]

        # Currently empty hash table
        self.size = 0
        
        # Unsure of usage
        self.keyslist = []

    """
    Private method to compute hash for a key
    """
    def __compute_hash(self,key):
        
        # First convert the key to integer form
        key_hash = 0

        # Adding values of all characters
        for ch in str(key):
            key_hash += ord(ch)
        
        # Fit in current hash table size by modulus operation
        return key_hash % self.capacity
    
    """
    Gets a value in the hashtable,
    returns `default` if not present
    """
    def get(self,key,default=None):
        hashed_key = self.__compute_hash(key)

        if not self.data[hashed_key]:
            return default
        
        # Hashed values are in the form of tuple
        # key value pairs
        # Wherever original match is found
        for data_key,data_val in self.data[hashed_key]:
            if data_key == key:
                return data_val
        
        # Technically shouldn't hit this situation
        return None
    

    """
    Puts key value pair in hashtable
    if not present
    else, updates the same
    """
    def put(self,key,value):
        hashed_key = self.__compute_hash(key)

        if not self.data[hashed_key]:
            self.data[hashed_key] = [[key,value]]
            self.keyslist.append(key)
            self.size += 1
        else:
            key_exists = False
            for i,values in enumerate(self.data[hashed_key]):
                k,v = values
                # Update value dont update size
                if k == key:
                    values[1] = value
                    key_exists = True
                    break
            
            # Add to list
            if not key_exists:
                self.data[hashed_key].append([key,value])
                self.keyslist.append(key)
                self.size += 1
        
        
        # Loadfactor = self.size / self.capacity
        # Thus can be rewritten this way
        if self.size >= self.load_factor * self.capacity:
            self.__rehash()
        
        ## OR:
        # if self.size / self.capacity >= self.load_factor:
            # self.__rehash()
        
        return True

    def __rehash(self):
        print("REHASHING, current stats SIZE CAPACITY PRIME_IDX", self.size,self.capacity,self.prime_idx)
        new_hashtable = HashTable(prime_idx=self.prime_idx +1,load_factor=self.load_factor)

        # Add all entries to new hashtable
        for key in self.keyslist:
            val = self.get(key)
            new_hashtable.put(key,val)

        # Replace current hashtable with newly created entries
        self.capacity = new_hashtable.capacity
        self.data = new_hashtable.data
        self.load_factor = new_hashtable.load_factor
        self.size = new_hashtable.size
        self.keyslist = new_hashtable.keyslist
        self.prime_idx = new_hashtable.prime_idx
        
        # # Clean memory
        # del new_hashtable

h_map = HashTable()

for n in range(10000):
    h_map.put(n,str(n))

for n in range(10000):
    
    ans = h_map.get(n)
    if n % 1000 == 0:
        print(ans)




# '''


# Basic hashtable implementation - LC question
# '''
# class MyHashMap:

#     def __init__(self,capacity = pow(10,5)):
#         self.capacity = capacity
#         self.data = [None for _ in range(self.capacity)]
#         self.load_factor = 0.75
#         self.size = 0
        

#     def put(self, key: int, value: int) -> None:
        
#         hashed_key = key % self.capacity
#         # If key exists,
#         # Linearly probe 
#         # if self.data[hashed_key]:
#         #     while self.data[hashed_key]:
#         #         hashed_key += 1
#         #         hashed_key = 0 if hashed_key == self.size else hashed_key
                
            
#         self.data[hashed_key] = value
#         self.size += 1
        
#         # if self.size >= (self.load_factor * self.capacity):
#         #     # Rehash here
#         #     self.capacity = 2 * self.capacity 
            
   
            
            
        

#     def get(self, key: int) -> int:
#         hashed_key = key % self.capacity
        
#         data = self.data[hashed_key]
        
#         return data if data is not None else -1
        

#     def remove(self, key: int) -> None:
        
#         hashed_key = key % self.capacity

#         for data in self.data:
#         self.data[hashed_key] = None
        
        


# # Your MyHashMap object will be instantiated and called as such:
# # obj = MyHashMap()
# # obj.put(key,value)
# # param_2 = obj.get(key)
# # obj.remove(key)