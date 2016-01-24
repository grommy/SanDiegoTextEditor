package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

    // The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}

    public List<ListNode> getWordList() {
        return wordList;
    }

	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
        LinkedList<String> wordsFromText;
        try {
            wordsFromText = getWordsFromText(sourceText);
            starter = wordsFromText.getFirst();
        }
        catch (Exception e) {
            wordsFromText = null;
        }


        if ((!starter.equals("")) && (wordsFromText!=null)) {
            String prevWord = starter;
            String nextWord;
            ListNode nodeToAddWords;

            for (int i=1; i<wordsFromText.size(); i++) {
                nextWord = wordsFromText.get(i);
                nodeToAddWords = getNodeToAddWord(prevWord);
                nodeToAddWords.addNextWord(nextWord);
                prevWord = nextWord;
            }

            // add starter to be a next word for the last word in the source text.
            nodeToAddWords = getNodeToAddWord(wordsFromText.getLast());
            nodeToAddWords.addNextWord(starter);
        }


	}

    private ListNode getNodeToAddWord(String prevWord) {
        // check if word is already added
        ListNode nodeToAddWords;
        ListNode prevWordNode = new ListNode(prevWord);
        if (wordList.contains(prevWordNode)) {

            nodeToAddWords = wordList.get(wordList.indexOf(prevWordNode));
        }
        else {
            nodeToAddWords = prevWordNode;
            wordList.add(nodeToAddWords);
        }

        return nodeToAddWords;
    }

    /**
     * Generate the number of words requested.
     */
    @Override
	public String generateText(int numWords) {

        if (numWords<0) {
            throw new IllegalArgumentException("Parameter numWords is incorrect");
        }
        else {
            if ((wordList.size() == 0) || (Objects.equals(starter, ""))) {
                return "";
            }
            else {
                String currentWord = starter;
                String output = "";
                ListNode currentNode;
                ListNode sameWordNode;
                int indexOfCurrentWordInList;
                for(int i=0; i<numWords; i++) {

                    if (i!=numWords-1) {
                        output = output.concat(currentWord+" ");
                    }
                    else output = output.concat(currentWord);

                    sameWordNode = new ListNode(currentWord);
                    indexOfCurrentWordInList = wordList.indexOf(sameWordNode);
                    assert indexOfCurrentWordInList > 0;
                    assert indexOfCurrentWordInList < wordList.size();

                    currentNode = wordList.get(indexOfCurrentWordInList);
                    currentWord =  currentNode.getRandomNextWord(rnGenerator);

                }
                return output;
            }
        }

	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
        starter = "";
        wordList = new LinkedList<ListNode>();
        train(sourceText);
	}
	
    public LinkedList<String> getWordsFromText(String sourceText)
    {

        LinkedList<String> wordsList = new LinkedList<>();
        String[] wordsAndSigns = sourceText.split("[\\s]+");

        for (String stringToCheck : wordsAndSigns) {
            if (stringToCheck.matches(".*[a-zA-Z]+.*")) {
                wordsList.add(stringToCheck);
            }

        }
        return wordsList;
    }
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;

    public List<String> getNextWords() {
        return nextWords;
    }

    // The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
	    // The random number generator should be passed from
	    // the MarkovTextGeneratorLoL class
	    return nextWords.get(generator.nextInt(nextWords.size()));
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
    @Override
    public boolean equals(Object other) {
    // Not strictly necessary, but often a good optimization
    if (this == other)
        return true;
    if (!(other instanceof ListNode))
        return false;
    ListNode otherListNode = (ListNode) other;

    return word.equals(otherListNode.word);
}
    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 31 + word.hashCode();
        hash = hash * 31
                + (nextWords == null ? 0 : nextWords.hashCode());
        return hash;
    }
	
}


