package genericMethods;

public class Swap {

    public static <T> T[] swap (T[] objectArray, int index1, int index2){

        if (index1 < 0 || index1> objectArray.length){
            throw new ArrayIndexOutOfBoundsException("Index1 must contain a value within the array");
        }
        if (index2 < 0 || index2> objectArray.length){
            throw new ArrayIndexOutOfBoundsException("Index2 must contain a value within the array");
        }
        if (objectArray.length == 0)
        {
            throw new NullPointerException("objectArray must not be empty");
        }
            T holdVar;
            holdVar = objectArray[index1];
            objectArray[index1] = objectArray[index2];
            objectArray[index2] = holdVar;

        return objectArray;
    }
}
