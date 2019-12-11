package arraySorter;

import arrayGenerator.ArrayGenerator;
import arrayGenerator.CharacterArrayGenerator;
import timer.Timer;

/**
 * Time character bubble sorters.
 *
 * @author Hugh Osborne
 * @version October 2019
 */
public class CharacterSelectionSortTimer extends SelectionSortTimer<Character> {
    @Override
    public Timer getTimer(int size) {
        ArrayGenerator<Character> generator = new CharacterArrayGenerator();
        setArray(generator.getArray(size));
        return this;
    }

    public static void main(String[] args) {
        SelectionSortTimer timer = new CharacterSelectionSortTimer();
        timer.timingSequence();
    }
}