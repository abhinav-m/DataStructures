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
    
    # Max will always be in right subtree for BST
    def findMax(self):
        if(self is None):
            return -1
        
        # Traverse right subtree till Max is found
        if(self.r):
            self.r.findMax()
        else:
            return self.d

    def findMin(self):
        if(self is None):
            return -1
        
        # Traverse left subtree till min is found
        if(self.l):
            self.l.findMin()
        else:
            return self.d

    @staticmethod
    def delete_node(root,data):
        # Base case for empty tree.
        if(root is None):
            return None

        # Update roots as you go back up
        if(data < root.d):
          
            root.l = root.delete_node(root.l,data)
        elif(data > root.d):
            root.r =  root.delete_node(root.r,data)
        else:
            if(root.l is None):
                temp = root.r
                root = None
                return temp
            elif(root.r is None):
                temp = root.l
                root = None
                return temp       
            # Maximum value of  left subtree
            # Inorder predecessor
            # Can also be replaced by Inorder successor
            max_value = root.l.findMax()
            root.d = max_value
            # Assign back the tree root after deleting
            root.l = root.delete_node(root.l,max_value)

        return root   
                     
            
                       
                  
                   
                        
        
       

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