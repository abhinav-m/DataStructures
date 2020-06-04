
import sys
sys.path.insert(1, "/home/abhinav/Work/dsa/DataStructures/Graphs")
# from Tree import Node
from Graphs import GraphNode


def create_graph():
    connections = [['A','B'],['B','H'],['B','C'],['H','E'],['C','E'],['C','D'],['E','F'],['E','G']]
    return GraphNode(connections,True)




graph = create_graph()

graph.print_graph()