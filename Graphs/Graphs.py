from collections import defaultdict

class GraphNode:
    # Assuming that connections are in the form of tuples
    # (a,b) means a -> b are connected and so on.
    def __init__(self,connections,directed=False):
        self._graph = defaultdict(list)
        self._directed = directed
        self.add_connections(connections)
        
    def add_connections(self,connections):
        
        for vertex1,vertex2 in connections:
            self.add(vertex1,vertex2)
    
    def add(self,vertex1,vertex2):
        # Add vertex 2 to the connection list of vertex 1
        self._graph[vertex1].append(vertex2)
        if not self._directed:
            # Add vertex 1 back to vertex 2 if graph is not 
            # directed ( connect both of them to each other)
            self._graph[vertex2].append(vertex1)