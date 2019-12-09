package arraySorter;

public class QuickSort<T extends Comparable<? super T>> implements ArraySort<T> {
    public T[] sort(T[] array) {
        int end = array.length;
        int start = 0;
        if (start < end){
            int partitionVal = partition(array, start, end);

        }
        return array;
    }
    private int partition(T[] array, int start, int end){
          T pivot = array[end];
          int i = (start-1);

          for (int ii = start; ii  )

    }
}
