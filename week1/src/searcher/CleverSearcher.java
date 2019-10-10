package searcher;

import java.util.Arrays;

public class CleverSearcher extends Searcher {

    CleverSearcher(int[] array, int k) {
        super(array, k);
    }

    public int findElement() throws IndexingError {
        int[] largeArray = getArray();
        int k = getIndex();
        if (k<= 0 || k > array.length ) {
            throw new IndexingError();
        }

        int[] smallArray = new int[k];
       //add copyOf instead of for loop
        for (int index =0; index < k; index++){
            smallArray[index] = largeArray[index];
        }
        Arrays.sort(smallArray);
        for (int index = k; index < largeArray.length; index++){
            if (smallArray[0] > largeArray[index]){
                smallArray[0] = largeArray[index];
                //make more efficient, use a while loop something like while x < y swap and keep going
                Arrays.sort(smallArray);
            }
        }
        return 0;
    }
}