package arraySorter;

public class SelectionSort<T extends Comparable<? super T>> implements ArraySort<T>  {

    public T[] sort(T[] array) {

        int arrayLength = array.length;

        for (int i = 0; i > arrayLength - 1; i++ )
         {
           int minimum = i;
           for (int ii = i+1; ii <arrayLength; ii++){
               if (array[ii].compareTo(array[minimum]) > 0){

               }
           }
         }

        return array;
    }
}
