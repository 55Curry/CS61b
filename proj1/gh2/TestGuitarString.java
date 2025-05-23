package gh2;

/* Imports the required audio library from the
 * edu.princeton.cs.introcs package. */

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the GuitarString class.
 * @author Josh Hug
 */
public class TestGuitarString  {

    @Test
    public void testPluckTheAString() {
        GuitarString aString = new GuitarString(GuitarHeroLite.CONCERT_A);
        aString.pluck();
        for (int i = 0; i < 50000; i += 1) {
            StdAudio.play(aString.sample());
            aString.tic();
        }
    }

    @Test
    public void testSample() {
        GuitarString s = new GuitarString(100);
        assertEquals(0.0, s.sample(), 0.0);
        assertEquals(0.0, s.sample(), 0.0);
        assertEquals(0.0, s.sample(), 0.0);
        s.pluck();
        double sample = s.sample();
        assertNotEquals("After plucking, your samples should not be 0.", 0.0, sample);

        assertEquals("Sample should not change the state of your string.", sample, s.sample(), 0.0);
        assertEquals("Sample should not change the state of your string.", sample, s.sample(), 0.0);
    }


    @Test
    public void testTic() {
        GuitarString s = new GuitarString(100);
        assertEquals(0.0, s.sample(), 0.0);
        assertEquals(0.0, s.sample(), 0.0);
        assertEquals(0.0, s.sample(), 0.0);
        s.pluck();
        double sample1 = s.sample();
        assertNotEquals("After plucking, your samples should not be 0.", 0.0, sample1);

        s.tic();
        assertNotEquals("After tic(), your samples should not stay the same.", sample1, s.sample());
    }


    @Test
    public void testTicCalculations() {
        // Create a GuitarString of frequency 11025, which
        // is a Deque of length 4.
        GuitarString s = new GuitarString(11025);
        s.pluck();

        // Record the front four values, ticcing as we go.
        double s1 = s.sample();
        s.tic();
        double s2 = s.sample();
        s.tic();
        double s3 = s.sample();
        s.tic();
        double s4 = s.sample();

        // If we tic once more, it should be equal to 0.996*0.5*(s1 + s2)
        s.tic();

        double s5 = s.sample();
        double expected = 0.996 * 0.5 * (s1 + s2);

        // Check that new sample is correct, using tolerance of 0.001.
        // See JUnit documentation for a description of how tolerances work
        // for assertEquals(double, double)
        assertEquals("Wrong tic value. Try running the testTic method.", expected, s5, 0.001);
    }

    /**
     * Test method that plays a short melody (Twinkle Twinkle Little Star excerpt)
     * using multiple GuitarString objects. This test is primarily for auditory verification.
     */
    @Test
    public void testPlaySimpleMelody() {
        // Define frequencies for the required notes (approximations for C4, D4, E4, G4, A4)
        double C4_FREQ = 261.63;
        double D4_FREQ = 293.66;
        double E4_FREQ = 329.63;
        double G4_FREQ = 392.00;
        double A4_FREQ = 440.00; // Concert A

        // Create GuitarString objects for each note
        GuitarString c = new GuitarString(C4_FREQ);
        GuitarString d = new GuitarString(D4_FREQ);
        GuitarString e = new GuitarString(E4_FREQ);
        GuitarString g = new GuitarString(G4_FREQ);
        GuitarString a = new GuitarString(A4_FREQ);

        // Define the melody sequence using the GuitarString objects
        // Twinkle Twinkle Little Star (excerpt):
        // C C G G A A G | F F E E D D C
        // Note: We are using the objects directly in the array for simplicity
        // F is not strictly needed for the very beginning, but let's include it.
        double F4_FREQ = 349.23; // Add F frequency
        GuitarString f = new GuitarString(F4_FREQ); // Create F string

        GuitarString[] melody = {
                c, c, g, g, a, a, g,
                f, f, e, e, d, d, c
        };

        // Define the number of simulation steps (tics) for each note duration.
        // This determines how long each note plays. Adjust as needed.
        int ticsPerNote = 8000; // Roughly a quarter note duration at SR=44100 / (44100/8000) BPM

        StdOut.println("Playing melody...");

        // Play the melody
        for (GuitarString currentString : melody) {
            // Pluck the string to start the sound
            currentString.pluck();

            // Play the note for the defined duration
            for (int i = 0; i < ticsPerNote; i++) {
                // Play the current sample
                StdAudio.play(currentString.sample());
                // Advance the simulation
                currentString.tic();
            }
            // Optional: Add a small pause between notes if needed, though tic loop handles duration.
            // StdDraw.pause(10); // Requires StdDraw import
        }

        StdOut.println("Melody finished.");

        // JUnit tests typically need assertions. Since this is for auditory check,
        // we don't have specific numerical assertions for the sound itself.
        // You could add a dummy assertion or check some internal state if needed.
        // For now, we just let the test run and listen.
        assertTrue("Melody playback completed (check output audio).", true);
    }
}