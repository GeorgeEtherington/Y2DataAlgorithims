package arraySorter;

public class QuickSort<T extends Comparable<? super T>> implements ArraySort<T> {
    public T[] sort(T[] array) {
        int end = array.length-1;
        int start = 0;

        quickSort(array, start, end);

        return array;
    }
    private int partition(T[] array, int start, int end){
          T pivot = array[end];
          int i = (start-1);

          for (int ii = start; ii < end; ii++ ){
              if (array[ii].compareTo(pivot) < 0){
                  i++;

                  T holdVar = array[i];
                  array[i] = array[ii];
                  array[ii] = holdVar;
              }
          }

          T holdVar = array[i+1];
          array[i+1] = array[end];
          array[end] = holdVar;

          return i+1;

    }

    private void quickSort(T[] array, int start, int end){
            if (start < end){
                 int partitionVal = partition(array, start, end);

                quickSort(array, start, partitionVal-1);
                quickSort(array, partitionVal+1, end);
            }
        }
    }

