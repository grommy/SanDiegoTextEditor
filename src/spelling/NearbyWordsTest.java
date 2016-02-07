package spelling;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NearbyWordsTest {

    private NearbyWords nw;
    NearbyWords suggNw;

    @Before
    public void setUp() {
        Dictionary d = new DictionaryHashSet();
        DictionaryLoader.loadDictionary(d, "test_cases/dict.txt");
        nw = new NearbyWords(d);

        Dictionary suggDict = new DictionaryHashSet();
        DictionaryLoader.loadDictionary(suggDict, "test_cases/dict2.txt");
        suggNw = new NearbyWords(suggDict);
    }
    @Test
    public void testSubsitution() throws Exception {
        List<String> d1 = new ArrayList<String>();

        nw.subsitution("word", d1, true);

        assertTrue("Some substitutions found", d1.size()>0);

    }

    @Test
    public void testInsertions() throws Exception {
        List<String> d1 = new ArrayList<String>();

        nw.insertions("or", d1, true);

        assertTrue("Some insertions found", d1.size()>0);

    }

    @Test
    public void testDeletions() throws Exception {
        List<String> d1 = new ArrayList<String>();

        nw.deletions("makers", d1, true);

        assertTrue("Some deletions found", d1.size()>0);
        assertEquals("Testing isWord on large: no", 2, d1.size());

    }

    @Test
    public void testSuggestions() throws Exception {

        List<String> d1 = suggNw.suggestions("dag", 4);
        assertTrue("d1", d1.size()>0);

    }
}