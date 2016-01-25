package textgen;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by admin on 1/24/16.
 */
public class MarkovTextGeneratorLoLTest {
    public MarkovTextGeneratorLoL markovTextGenerator;
    public String shortString;
    public String[] wordsInShortString;

    public String textString2;
    public String noWordsString;

    @Before
    public void setUp() throws Exception {
        Random random = new Random();
        markovTextGenerator = new MarkovTextGeneratorLoL(random);
        shortString = "You say yes, I say! no, sala=din";
        wordsInShortString = new String[]{"You", "say", "yes,", "I", "say!", "no,", "sala=din"};
        noWordsString = "123 , , . ; 45";

        textString2 = "You say yes, I say no, "+
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
    }

    @Test
    public void testTrain() throws Exception {
        markovTextGenerator.train(shortString);
        List<ListNode> wordsList = markovTextGenerator.getWordList();
        Random random = new Random();

        assertEquals("check prevWord", "sala=din", wordsList.get(wordsList.size()-1).getWord());
        assertEquals("check nextWord", "You", wordsList.get(wordsList.size()-1).getRandomNextWord(random));
    }

    @Test
    public void testTrainBookExample() throws Exception {
        markovTextGenerator.train("hi there hi Leo");
        List<ListNode> wordsList = markovTextGenerator.getWordList();

        assertEquals("check prevWord 0", "hi", wordsList.get(0).getWord());
        assertEquals("check prevWord 1", "there", wordsList.get(1).getWord());
        assertEquals("check prevWord 2", "Leo", wordsList.get(2).getWord());

        assertEquals("size of wordsList ", 3, wordsList.size());

        assertEquals("nextWords for hi", "there", wordsList.get(0).getNextWords().get(0));
        assertEquals("nextWords for hi", "Leo", wordsList.get(0).getNextWords().get(1));
    }

    @Test
    public void testGetWords() throws Exception {
        LinkedList<String> wordsInText = markovTextGenerator.getWordsFromText(shortString);
        for(int i=0; i<wordsInShortString.length; i++) {
            assertEquals("check splitting: ", wordsInShortString[i], wordsInText.get(i));
        }

    }

    @Test
    public void testGetWordsNegative() throws Exception {
        LinkedList<String> wordsInText = markovTextGenerator.getWordsFromText(noWordsString);
        assertEquals("noWordsText", 0, wordsInText.size());

    }

    @Test
    public void testLinkedNodesContains() throws Exception {
        LinkedList<ListNode> linkedNodesList = new LinkedList<>();
        ListNode node1 = new ListNode("AAA");
        ListNode node2 = new ListNode("BBB");
        ListNode node3 = new ListNode("CCC");

        linkedNodesList.add(node1);
        linkedNodesList.add(1,node2);
        linkedNodesList.add(node3);

        ListNode nodeToCheckPositive = new ListNode("BBB");
        ListNode nodeToCheckNegative = new ListNode("DDD");

        assertEquals("Number of nodes", 3, linkedNodesList.size());
        assertTrue("Check contains positive", linkedNodesList.contains(nodeToCheckPositive));
        assertFalse("Check contains negative", linkedNodesList.contains(nodeToCheckNegative));

    }
}