package linkedLists;
import arrayGenerator.generator.ArrayGenerator;
import org.junit.jupiter.api.Test;

abstract class ListTest<T extends Comparable<? super T>> {
   abstract ArrayGenerator<T> getGenerator();

  
   }
}
