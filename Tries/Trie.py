
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
  
    # Search for different keys
    print("{} ---- {}".format("the",output[t.search("the")]))
    print("{} ---- {}".format("these",output[t.search("these")]))
    print("{} ---- {}".format("their",output[t.search("their")]))
    print("{} ---- {}".format("thaw",output[t.search("thaw")]))
  
if __name__ == '__main__':
    main()