class Node:
    def __init__(self,d=None,l=None,r=None):
        self.d = d
        self.l = None
        self.r = None
    

  
    def insert_node(self,data):
       
        if(self.d):    
            if(data < self.d):
                if(self.l is None):
                    self.l = Node(data)
                else:
                    # Make self point to right node.
                    self.l.insert_node(data)
            else:
                if(self.r is None):
                    self.r = Node(data)
                else:
                    # Make self point to right node.
                    self.r.insert_node(data)
        else:
             # Base case if tree is defined with None
               self.d = data
           
        
       

    def in_order(self):
        if(self.d):
            if(self.l):
                # Setting self to self.l
                self.l.in_order()
            print(self.d)
            if(self.r):
                # Setting self to self.r
                self.r.in_order() 
        else:
            return