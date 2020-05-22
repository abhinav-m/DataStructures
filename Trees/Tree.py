from queue import Queue

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


    @staticmethod
    def level_order_traversal(root):
        current_level = [root]
       
        while(len(current_level) > 0):
            next_level = []
            for i in range(0, len(current_level)):
                current_node = current_level[i]
                if(current_node):
                    print(current_node.d)
                    next_level.append(current_node.l)
                    next_level.append(current_node.r)
                
            # Swap current and next level once current level has been traversed  
            current_level  = next_level
    

    ## Better than above, because doesn't need to swap two lists O(n) space of queue at max
    ## at last level, O(n) traverses each node once.
    @staticmethod
    def breadth_first_traversal(root):
        if root is None:
            return
        q = Queue(maxsize=0)
        q.put(root)
        while(not q.empty()):
            # Get the current level element and put the next
            # in queue
            cur_node = q.get();
            print(cur_node.d)
            # Put the next nodes on queue 
            if(cur_node.l):
                q.put(cur_node.l)
            if(cur_node.r):
                q.put(cur_node.r)


        # Using a list as queue( correct if needed)
                    
           



root = Node(10,None,None)
