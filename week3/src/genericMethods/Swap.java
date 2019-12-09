package genericMethods;

public class Swap {

    public static <T> T[] swap (T[] objectArray, int index1, int index2){

        T holdVar;
        holdVar = objectArray[index1];

        objectArray[index1] = objectArray[index2];
        objectArray[index2] = holdVar;


        return objectArray;
    }
}
