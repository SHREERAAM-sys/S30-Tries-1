
/**
 L.C: 208. Implement Trie (Prefix Tree)

 Approach: Design with array of size 26 - lower case, 52 - lower+upper case or 256 - all characters

 Working:
     Implement a TrieNode class
     isEnd -> boolean
     [] childrens -> TriNode

     Declare a root -> TriNode
     insert(word)

         curr = root
         iterate through the word

             get the index word[i] - 'a'             //zero based lower case alphabets index
             check if curr.childrens[index] is NULL  //there is no word present already with the character
                 assign a new TriNode to the current index
             curr = childrens[index]

         set curr.isEnd = true         //Marks the end of a word in the Trie


     search(word)
         curr = root
         iterate through the word

             get the index word[i] - 'a'
             check if curr.childrens[index] is NULL //the char is not present in Trie which in word
                return false

         return curr.isEnd //the word length is iterated and the value in isEnd determines wether the
         //word is presen in the Trie or not

     startsWith(String prefix)

         curr = root
         iterate through the prefix

             get the index word[i] - 'a'
             check if curr.childrens[index] is NULL //the char is not present in Trie which in prefix[i]
                return false

     return true


 Time Complexity: O(n) - insert, search, startsWith
 Space Complexity: O(n) - insert,
 O(1) - search, startsWith
 */

class Trie {


    class TrieNode {

        boolean isEnd;
        TrieNode[] childrens;

        TrieNode() {

            isEnd = false;
            childrens = new TrieNode[26];
        }
    }

    TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {

        TrieNode curr = root;
        for(int i=0;i<word.length(); i++){

            if(curr.childrens[word.charAt(i) - 'a'] == null) {

                curr.childrens[word.charAt(i) - 'a'] = new TrieNode();
            }

            curr = curr.childrens[word.charAt(i) - 'a'];

        }
        curr.isEnd = true;
    }

    public boolean search(String word) {

        TrieNode curr = root;
        for(int i=0;i<word.length(); i++){

            if(curr.childrens[word.charAt(i) - 'a'] == null) {

                return false;
            }
            curr = curr.childrens[word.charAt(i) - 'a'];
        }
        return curr.isEnd;

    }

    public boolean startsWith(String prefix) {

        TrieNode curr = root;
        for(int i=0;i<prefix.length(); i++){

            if(curr.childrens[prefix.charAt(i) - 'a'] == null) {
                return false;
            }
            curr = curr.childrens[prefix.charAt(i) - 'a'];
        }

        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */