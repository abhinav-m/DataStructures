class Node:
    def __init__(s,d):
        s.l = None
        s.r = None
        s.d = d
    
    def print_tree(s):
        print(s.d)



root = Node(10)
root.print_tree()