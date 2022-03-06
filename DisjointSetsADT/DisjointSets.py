'''
https://www.ics.uci.edu/~eppstein/PADS/UnionFind.py -> Optimised
https://code.activestate.com/recipes/577225-union-find/  -> Original

Union find implementation based on weight of sets
following python standards
'''

'''
NOTES:
1. Path compression is only possible while using weights / size as heights resizing of tree can be costly
2. Time complexity is logN for UNION -> m operations mlogn 

Todo:
1. Understand time complexity

'''

class UnionFind:
    """Union Find data structure.

    Each UnionFind instance X maintains family of disjoint sets of hashable objects supporting two methods:

    - Find(item) returns name of the set containing the given item. Each set is named after arbitrarily
    chosing one of its members, as long as the set remains unchanged, it will keep the same name. If item is not a part
    of a set in X, a new singleton set is created for it.

    - Union(item1,item2) merges the sets containing each item into a single, larger set. If any item is
    not a part of the set yet, it as added to X as one of the members of a merged set

    """

    def __init__(self):
        """Creates a new empty Union-Find data structure."""
        self.weights = {}
        self.parents = {}

    
    def FIND(self,item):

        # Doesn't exist in the set
        if item not in self.parents:
            # Add to parents dictionary
            self.parents[item] = item
            # Assign weight as 1 ( connected to itself )
            self.weights[item] = 1
            return item
        
        # Find path of the object leading to the root
        # To perform path compression (collapsing find)
        # And add the node directly to the root

       

        # While not reached the end of 
        # root chains
        # Eg roots (parent dict) -> {1:1 , 3:1, 2:3, 4:2 }
        # Here path will be (on finding 4) 4 -> 2->3 -> 1
        # Thus all of these end up at 1 as the parent and should
        # be directly assigned 1 as their parents

        # While we find the root node
        # ie, the parent which points to itself


        # Path starts from item
        path = [item]

        # Root starts from direct parent of node
        root = self.parents[item]
        while root != path[-1]:
            # Append root
            path.append(root)
            # Traverse till finding root node
            root = self.parents[root]

        # Assign all nodes linked to each other
        # stored in path
        # to the final root
        for ancestor in path:
            self.parents[ancestor] = root
        
        # Return final root
        return root

    def __iter__(self):
        return iter(self.parents)
    
    # Unions and appends all items to one set
    def union(self,*items):
        """ Finds all sets of items and merges them """

        # All roots of items
        roots = [self.parents[item] for item in items]

        # Find the heaviest root amongst all
        # Finds maximum of weight and returns the ROOT
        heaviest = max([(self.weights[r],r) for r in roots])[1]

        for r in roots:
            # For all the roots that are not the heaviest
            if r != heaviest:
                # Add the weights of all nodes of this root to heaviest
                self.weights[heaviest] += self.weights[r]
                # Make parent of root to heaviest
                self.parents[r] = heaviest


uf = UnionFind()


# Adds one to set ADT and returns the same
uf.FIND(1)

# Adds two to set ADT and returns the same
uf.FIND(2)

# Gets value 1 and returns
uf.FIND(1)


# Add 3 and 4 to ADT
uf.FIND(3)
uf.FIND(4)

print("WEIGHTS AFTER ADDING ALL VALUES",uf.weights)
print("PARENTS AFTER ADDING ALL VALUES",uf.parents)

# Union 3 and 4
uf.union(3,1)

# See current data structure after unions
print("WEIGHTS AFTER UNION 3,1",uf.weights)
print("PARENTS AFTER UNION 3,1",uf.parents)


# uf.union(3,2)

# # See current data structure after unions
# print("WEIGHTS AFTER UNION 3,2",uf.weights)
# print("PARENTS AFTER UNION 3,2",uf.parents)

uf.union(4,2)

# See current data structure after unions
print("WEIGHTS AFTER UNION 4 2",uf.weights)
print("PARENTS AFTER UNION 4,2",uf.parents)


uf.union(2,1)

# See current data structure after unions
print("WEIGHTS AFTER UNION 2,1",uf.weights)
print("PARENTS AFTER UNION 2,1",uf.parents)

# This will path compress
# After finding root of 4->3->2->1
# Collapsing find ?
uf.FIND(4)

# See current data structure after find operation
# (NON COLLAPSED FIND)
print("WEIGHTS AFTER FIND 4 (NON COLLAPSED)",uf.weights)
print("PARENTS AFTER FIND 4 (NON COLLAPSED)",uf.parents)


# This will path compress
# After finding root of 1->3->4
# Collapsing find 
uf.FIND(1)

# See current data structure after find operation
# Collapsing their roots as find
print("WEIGHTS AFTER FIND 1 (COLLAPSING TO 4) ",uf.weights)
print("PARENTS AFTER FIND 1 (COLLAPSING TO 4)",uf.parents)
