package arraySorter;
import arrayGenerator.ArrayGenerator;
import arrayGenerator.IntegerArrayGenerator;
import arraySorter.QuickSortTest;
import scope.IntegerScope;

public class IntegerQuickSortTest extends QuickSortTest<Integer> {
    @Override
    ArrayGenerator<Integer> getGenerator() {
        return new IntegerArrayGenerator(new IntegerScope());
    }
}