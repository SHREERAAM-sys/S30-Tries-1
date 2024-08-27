/*
    L.C 648. Replace Words

    Approach: Trie to find the shortest occurrence of a word


    Working:
        Declare a TrieNode
        Declare and initialize a ROOT

        replaceWords()
            Add all the DICTIONARY elements to the trie by implementing an insert(word) method
            convert the SENTENCE into array of words
            Declare and initialize RESULT -> StringBuffer

            Iterate through the string array
                //add white space characters before each word when i!=0
                get the current word
                Declare a variable CURR = ROOT
                Declare sb -> StringBuffer
                iterate through each character

                    get the character index word[i] - 'a'      //zero indexed
                    check if the CURR.children[index] is null  //the character is not present
                            OR if CURR.isEnd is true           //to get the first occurring shortest string

                        break
                    sb.append() the current char to a string buffer

                check CURR.isEnd is true
                    RESULT.append(sb)
                else
                    append the original word

            return RESULT as string

    m - length of the string array
    n - length of the words in the string array
    k - length of the input List -> dictionary
    l - length of the words in the dictionary


    Time Complexity: O(k*l) + O(m*n)

            O(m*n)   //time taken to find and replace words
            O(k*l) // time taken to insert the words from the dictionary to a Trie

    Space Complexity: O(k*l) //space taken by insertion in a Trie

*/

class ReplaceWords {

    //TrieNode class definition
    class TrieNode {

        boolean isEnd;
        TrieNode[] childrens;

        TrieNode() {
            this.isEnd = false;
            this.childrens = new TrieNode[26];
        }
    }

    TrieNode root = new TrieNode();

    public void insert(String word) {

        TrieNode curr = root;
        for(int i=0;i<word.length(); i++) {

            int index = word.charAt(i) - 'a';

            if(curr.childrens[index] == null) {
                curr.childrens[index] = new TrieNode();
            }
            curr = curr.childrens[index];
        }
        curr.isEnd = true;
    }





    public String replaceWords(List<String> dictionary, String sentence) {

        if(sentence == null || sentence.length() == 0) {
            return sentence;
        }


        //adding the values in the dictionary to the Trie

        for(String word: dictionary) { // O(k)

            insert(word); //O(l)
        }

        String[] strArr = sentence.split(" ");

        StringBuffer result = new StringBuffer();

        for(int k=0; k<strArr.length; k++) { //O(m)

            if(k!=0) {
                result.append(" ");
            }

            String word = strArr[k];

            TrieNode curr = root;
            StringBuffer sb = new StringBuffer();
            for(int i=0; i < word.length(); i++) { //O(n)

                char c = word.charAt(i);
                int index = word.charAt(i) - 'a';

                if(curr.childrens[index] == null || curr.isEnd) {
                    break;
                }
                sb.append(c);

                curr = curr.childrens[index];
            }

            if(curr.isEnd) {
                result.append(sb);
            }
            else {
                result.append(word);
            }

        }

        return result.toString();

    }
}
