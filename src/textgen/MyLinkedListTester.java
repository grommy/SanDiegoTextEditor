/**
 * 
 */
package textgen;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

    // testGet
	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own

	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");

		emptyList = new MyLinkedList<Integer>();

		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}

		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
        // testRemove
        MyLinkedList<Integer> listForRemoval;

        listForRemoval = new MyLinkedList<Integer>();
        listForRemoval.add(65);
        listForRemoval.add(21);
        listForRemoval.add(42);

        int a = listForRemoval.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer) 21, listForRemoval.get(0));
		assertEquals("Remove: check size is correct ", 2, listForRemoval.size());
        assertEquals("Remove: check element 0 is correct ", (Integer) 42, listForRemoval.get(1));

		try {
			listForRemoval.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}

        try {
            listForRemoval.remove(3);
            fail("Check out of bounds");
        }
        catch (IndexOutOfBoundsException e) {
        }
		
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // testAdd
        MyLinkedList<Integer> listTestAdd;

        listTestAdd = new MyLinkedList<Integer>();
        listTestAdd.add(65);
        listTestAdd.add(21);
        listTestAdd.add(42);

        assertEquals("testAddEnd: total number of elements ", 3, listTestAdd.size());
        assertEquals("Check first", (Integer) 65, listTestAdd.get(0));
        assertEquals("Check second", (Integer) 21, listTestAdd.get(1));
        assertEquals("Check third", (Integer) 42, listTestAdd.get(2));

        try {
            listTestAdd.add(null);
            fail("Check possibility to add null");
        }
        catch (NullPointerException e) {
        }
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
        int testSize = 10;  // should be mor than 3
        MyLinkedList<Integer> listTestSize;

        listTestSize = new MyLinkedList<Integer>();
        assertEquals("testSize: new empty list", 0, listTestSize.size());

        for (int i=0; i<testSize; i++) {
            listTestSize.add(i);
        }
        assertEquals("testSize: total number of elements ", testSize, listTestSize.size());


        // remove 1st element
        listTestSize.remove(0);
        assertEquals("testSize: remove 1st", testSize - 1, listTestSize.size());

        // remove last element
        listTestSize.remove(testSize-2);
        assertEquals("testSize: remove last", testSize-2, listTestSize.size());

        // remove element in the middle
        listTestSize.remove(testSize-4);
        assertEquals("testSize: remove middle", testSize-3, listTestSize.size());

        int elementsToDelete = listTestSize.size();
        for (int i=0; i<elementsToDelete; i++) {
            listTestSize.remove(0);
        }
        assertEquals("testSize: delete all", 0, listTestSize.size());

        for (int i=0; i<testSize; i++) {
            listTestSize.add(i, i+100);
        }
        assertEquals("testSize: total number of elements ", testSize, listTestSize.size());

	}

	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        int testSize = 10;  // should be mor than 3
        MyLinkedList<Integer> listTestAddIndex = new MyLinkedList<>();

        for (int i=0; i<testSize; i++) {
            listTestAddIndex.add(i, 100 + i);
        }
        assertEquals("testAddIndex: total number of elements ", testSize, listTestAddIndex.size());

        for (int i=0; i<testSize; i++) {
            assertEquals("testAddIndex: check the value ", (Integer) (i + 100), listTestAddIndex.get(i));
        }

		listTestAddIndex.add(5, 55);
        assertEquals("testAddIndex: check the value ", (Integer) (55), listTestAddIndex.get(5));
        assertEquals("testAddIndex: total number of elements ", testSize+1, listTestAddIndex.size());

        try {
            listTestAddIndex.add(1, null);
            fail("Check possibility to add null");
        }
        catch (NullPointerException e) {
        }

        try {
            listTestAddIndex.add(-1, 23);
            fail("Check out of bounds");
        }
        catch (IndexOutOfBoundsException e) {
        }

        try {
            listTestAddIndex.add(testSize+2, 23);
            fail("Check out of bounds");
        }
        catch (IndexOutOfBoundsException e) {
        }

	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
        MyLinkedList<String> listTestSet = new MyLinkedList<String>();
        String replacedElem;

        // setup data
        listTestSet.add("BBB");
        assertEquals("testSet: correct element ","BBB", listTestSet.get(0));
        listTestSet.add(0, "AAA");
        assertEquals("testSet: correct element ","AAA", listTestSet.get(0));
        assertEquals("testSet: correct element ","BBB", listTestSet.get(1));
        listTestSet.add(2, "CCC");

        assertEquals("testSet: correct element ","CCC", listTestSet.get(2));

        // check
        replacedElem = listTestSet.set(0, "XXX");
        assertEquals("testSet: total number of elements ", 3, listTestSet.size());
        assertEquals("testSet: correct element ","XXX", listTestSet.get(0));
        assertEquals("testSet: correct element ","AAA", replacedElem);

        assertEquals("testSet: correct element ","CCC", listTestSet.get(2));
        replacedElem = listTestSet.set(2, "ZZZ");
        assertEquals("testSet: total number of elements ", 3, listTestSet.size());
        assertEquals("testSet: correct element ","ZZZ", listTestSet.get(2));
        assertEquals("testSet: correct element ","CCC", replacedElem);

        replacedElem = listTestSet.set(1, "YYY");
        assertEquals("testSet: total number of elements ", 3, listTestSet.size());
        assertEquals("testSet: correct element ","YYY", listTestSet.get(1));
        assertEquals("testSet: correct element ","BBB", replacedElem);

        assertEquals("testSet: correct element ","XXX", listTestSet.get(0));

        try {
            listTestSet.set(-1, "fail");
            fail("Check out of bounds");
        }
        catch (IndexOutOfBoundsException e) {
        }

        try {
            listTestSet.set(3, "fail");
            fail("Check out of bounds");
        }
        catch (IndexOutOfBoundsException e) {
        }

        try {
            listTestSet.set(1, null);
            fail("Set null");
        }
        catch (NullPointerException e) {
        }

	}
	
	
}
