package searcher;

class CleverSearcherTest extends SearcherTest.java {
    protected Searcher createSearcher(int[] array, int index) throws IndexingError {
        return new CleverSearcher (array, index);
    }
}