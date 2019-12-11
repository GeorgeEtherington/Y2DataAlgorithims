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
public class CharacterQuickSortTimer extends QuickSortTimer<Character> {
    @Override
    public Timer getTimer(int size) {
        ArrayGenerator<Character> generator = new CharacterArrayGenerator();
        setArray(generator.getArray(size));
        return this;
    }

    public static void main(String[] args) {
        QuickSortTimer timer = new CharacterQuickSortTimer();
        timer.timingSequence();
    }
}