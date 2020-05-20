class Node:
    def __init__(self,d,l=None,r=None):
        self.d = d
        self.l = None
        self.r = None
       


    @classmethod
    def pre_order(self,s):
        if(s):
            print(s.d)
            self.pre_order(s.l)
            self.pre_order(s.r)
        else:
            return;
      
    @classmethod
    def post_order(self,s):
        if(s):
            s.post_order(s.l)
            s.post_order(s.r)
            print(s.d)
        else:
            return;

    @classmethod
    def in_order(self,s):
        if(s):
            s.in_order(s.l)
            print(s.d)
            s.in_order(s.r)      
        else:
            return

    
    @classmethod
    def print_tree(self,s,ORDER="IN_ORDER"):
      
        
        traversal_order = {
            "IN_ORDER":Node.in_order,
            "POST_ORDER":Node.post_order,
            "PRE_ORDER":Node.pre_order         
        }
        traversal_order.get(ORDER)(s);




root = Node(10,None,None)
