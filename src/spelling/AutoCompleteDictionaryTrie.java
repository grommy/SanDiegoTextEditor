package spelling;

import java.util.*;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	


	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
		String wordToAdd = word.toLowerCase();
        TrieNode currNode = root;
        TrieNode nextNode;
        for (Character letter: wordToAdd.toCharArray()) {

            nextNode = currNode.insert(letter);
            if (nextNode!=null) {
                currNode = nextNode;
            }
            else {
                currNode = currNode.getChild(letter);
            }

        }

        if (currNode.endsWord()) {
            // this word already exists in dict
            return false;
        }

        currNode.setEndsWord(true);
	    size++;
        return true;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) {
        TrieNode currNode = findStem(s);

        return currNode != null && currNode.endsWord();
    }

    private TrieNode findStem (String s) {
        String stringToSearch = s.toLowerCase();
        TrieNode currNode = root;
        for (Character character: stringToSearch.toCharArray()) {
            if (currNode.getValidNextCharacters().contains(character)) {
                currNode = currNode.getChild(character);
            }
            else {
                return null;
            }
        }

        return currNode;
    }

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions

         List<String> completions = new LinkedList<>();
         Set <Character> characters;

         TrieNode currNode = findStem(prefix);

         Queue<TrieNode> completionsQueue = new LinkedList<>();
         completionsQueue.add(currNode);
         while (completions.size()<numCompletions && !completionsQueue.isEmpty()) {
             currNode = completionsQueue.poll();
             if (currNode!=null) {
                 if (currNode.endsWord()) {
                     completions.add(currNode.getText());
                 }
                 characters = currNode.getValidNextCharacters();
                 for (Character currCharacter : characters) {
                     completionsQueue.add(currNode.getChild(currCharacter));
                 }
             }


         }

//          completions.sort(Comparator.<String>naturalOrder());

         return completions;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}