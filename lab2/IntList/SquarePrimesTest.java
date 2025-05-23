package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
        IntList lst2 = IntList.of(0, 15, 16, 17, 18);
        changed = IntListExercises.squarePrimes(lst2);
        assertEquals("0 -> 15 -> 16 -> 289 -> 18", lst2.toString());
        assertTrue(changed);
        IntList lst3 = IntList.of(0, 0, 0, 0, 0);
        changed = IntListExercises.squarePrimes(lst3);
        assertEquals("0 -> 0 -> 0 -> 0 -> 0", lst3.toString());
        assertFalse(changed);
        IntList lst4 = IntList.of(null);
        changed = IntListExercises.squarePrimes(lst4);
        assertEquals("0", lst4.toString());
        assertFalse(changed);
        IntList lst5 = IntList.of(2, 0, 0, 0, 0);
        changed = IntListExercises.squarePrimes(lst3);
        assertEquals("2 -> 0 -> 0 -> 0 -> 0", lst5.toString());
        assertFalse(changed);
        IntList lst6 = IntList.of(2, 17, 5);
        changed = IntListExercises.squarePrimes(lst6);
        assertEquals("4 -> 289 -> 25", lst6.toString());
    }
}
