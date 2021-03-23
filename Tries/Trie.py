
class TrieNode:
    def __init__(self,is_end_of_word=False) -> None:
        # Representing characters of the alphabet
        self.children = [None] * 26
        self.is_end_of_word = is_end_of_word


class Trie:
    def __init__(self):
        self.root = TrieNode()

    
    def getNode(self):
        return TrieNode()

    # Private helper function to 
    # get character index (ascii value)
    def _get_char_index(self,ch):
        return  ord(ch.lower()) - ord('a')
           

    def insert(self,word):
        current = self.root
        for ch in word:
            ch_idx = self._get_char_index(ch)
            # If key didn't exist, create it
            if not current.children[ch_idx]:
                current.children[ch_idx] = TrieNode()
            # Traverse to next key
            current = current.children[ch_idx]
        
        # Mark last node traversed as true
        # to mark as word end
        current.is_end_of_word = True
            
    def search(self,word):
        current = self.root
        for ch in word:
            ch_idx = self._get_char_index(ch)
            if not current.children[ch_idx]:
                return False
            current = current.children[ch_idx]
        
        if current and  current.is_end_of_word:
            return True
        
        return False
        
# Driver function
def main():
  
    # Input keys (use only 'a' through 'z' and lower case)
    words = ["the","a","there","anaswe","any",
            "by","their"]
    output = ["Not present in trie",
              "Present in trie"]
  
    # Trie object
    t = Trie()
  
    # Construct trie
    for word in words:
        t.insert(word)
  
    # Search for different words
    # True translates to 1 
    # False translates to 0 in array
    print("{} ---- {}".format("the",output[t.search("the")]))
    print("{} ---- {}".format("these",output[t.search("these")]))
    print("{} ---- {}".format("their",output[t.search("their")]))
    print("{} ---- {}".format("thaw",output[t.search("thaw")]))
    print("{} ---- {}".format("anasw",output[t.search("anasw")]))
    print("{} ---- {}".format("anaswe",output[t.search("anaswe")]))
  
if __name__ == '__main__':
    main()