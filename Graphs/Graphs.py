from collections import defaultdict
from queue import Queue

class GraphNode:
    # Assuming that connections are in the form of arrays
    # (a,b) means a -> b are connected and so on.
    # Graph containing dictionary of lists
    def __init__(self,connections,directed=False):
        self._graph = defaultdict(list)
        self._directed = directed
        self.add_connections(connections)
        
    def add_connections(self,connections):
        # Assuming connections of tuples 
        for [vertex1,vertex2] in connections:
            self.add(vertex1,vertex2)
    
    def add(self,vertex1,vertex2):
        # Add vertex 2 to the connection list of vertex 1
        self._graph[vertex1].append(vertex2)
        if not self._directed:
            # Add vertex 1 back to vertex 2 if graph is not 
            # directed ( connect both of them to each other)
            self._graph[vertex2].append(vertex1)

    def _dfs_util(self,vertex,visited):
        if vertex not in visited:
            print(vertex)
            visited.add(vertex)
            # Traversing all connected nodes of current graph
            for neighbour in self._graph[vertex]:
                self._dfs_util(neighbour,visited)

        


    # Driver function for dfs
    def dfs(self,vertex='A'):
        # Initially, visited is an empty set 
        # Set ensures each vertex is visted only once.
        visited = set()
        self._dfs_util(vertex,visited)

    def _bfs_util(self,vertex,visited,cur_queue):
        cur_neighbours = self._graph[vertex]
        for v in cur_neighbours:
            if(v not in visited):
                print(v)
                visited.add(v)
                cur_queue.put(v)
        if(not cur_queue.empty()):
            cur_vertex = cur_queue.get()
            self._bfs_util(cur_vertex,visited,cur_queue)

    def bfs(self, vertex='A'):
        visited = set()
        cur_queue = Queue()
        self._bfs_util(vertex,visited,cur_queue)
        print("**GRAPH TRAVERSED**")
        



    def print_graph(self):
        print("***** GRAPH NODES *****")
        graph = self._graph
        for key in graph:
            cur_vertices = graph[key]
            for v in cur_vertices:
                print(key,"->",v)

        print("***** GRAPH NODES *****")