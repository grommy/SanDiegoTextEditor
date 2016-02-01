package spelling;


/** A class for timing the Dictionary Implementations
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 */

public class DictionaryBenchmarking {

	
	public static void main(String [] args) {

	    // Run each test more than once to get bigger numbers and less noise.
	    // You can try playing around with this number.
	    int trials = 500;

	    // The text to test on
	    String dictFile = "data/dict.txt";
		
	    // The amount of words to increment each step
	    // You can play around with this
		int increment = 2000;

		// The number of steps to run.  
		// You can play around with this.
		int numSteps = 5;
		
		// The number of words to start with. 
		// You can play around with this.
		int start = 50000;
		
		String notInDictionary = "notaword";
		
		// TODO: Play around with the numbers above and graph the output to see trends in the data
		for (int numToCheck = start; numToCheck < numSteps*increment + start; 
				numToCheck += increment)
		{
			// Time the creation of finding a word that is not in the dictionary.
			DictionaryLL llDict = new DictionaryLL();
			DictionaryBST bstDict = new DictionaryBST();
			


			
			double startTime = System.nanoTime()/10000000.0;
//            DictionaryLoader.loadDictionary(llDict, dictFile, numToCheck);
//			for (int i = 0; i < trials; i++) {
//				llDict.isWord(notInDictionary);
//			}
			double endTime = System.nanoTime()/10000000.0;
			double timeLL = (endTime - startTime)/100.0;
			
//			startTime = System.nanoTime();
			startTime = System.nanoTime()/10000000.0;
            DictionaryLoader.loadDictionary(bstDict, dictFile, numToCheck);
			for (int i = 0; i < trials; i++) {
				bstDict.isWord(notInDictionary);
			}
//			endTime = System.nanoTime();
			endTime = System.nanoTime()/10000000.0;
			double timeBST = (endTime - startTime)/100.0;
			
			System.out.println(numToCheck + "\t" + timeLL + "\t" + timeBST);
			
		}
	
	}
	
}
