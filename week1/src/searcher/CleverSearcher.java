package searcher;

import java.util.Arrays;

public class CleverSearcher extends Searcher {

    CleverSearcher(int[] array, int k) {
        super(array, k);
    }

    public int findElement() throws IndexingError {
        int[] largeArray = getArray();
        int k = getIndex();
        if (k<= 0 || k > largeArray.length ) {
            throw new IndexingError();
        }

        int[] smallArray = Arrays.copyOf(largeArray, k);

        Arrays.sort(smallArray);

        for (int index = k; index < largeArray.length; index++){

            if (smallArray[0] < largeArray[index]){
                smallArray[0] = largeArray[index];
                boolean largest = true;
                for (int i =0; (i < smallArray.length-1) && (largest); i++){
                    if (smallArray[i] > smallArray[i+1]){
                        int hold = smallArray[i+1];
                        smallArray[i+1] = smallArray[i];
                        smallArray[i] = hold;
                    }else{
                        largest = false;
                    }
                }

            }
        }

        return smallArray[0];
    }
}