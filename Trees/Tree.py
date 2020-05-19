class Node:
    def __init__(self,d,l=None,r=None):
        self.d = d
        self.l = None
        self.r = None
       


    @classmethod
    def in_order(self,s):
        if(s):
            print(s.d)
        else:
            return;
        s.in_order(s.l)
        s.in_order(s.r)

    
    @classmethod
    def print_tree(self,s):
        self.in_order(s)




root = Node(10,None,None)
