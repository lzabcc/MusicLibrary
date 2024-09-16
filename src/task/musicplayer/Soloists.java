package task.musicplayer;

/**
 * Represents a solo musician, extending the Artist class.
 * This class is used to create solo music artists.
 *
 * @author Nathan Li
 * @version 2023.10.8
 */

public class Soloists extends Artist {

    /**
     * Default constructor for Soloists class.
     * Initializes name to null.
     */
    public Soloists() {
        super();
    }

    /**
     * Parameterized constructor for Soloists class.
     * Initializes name with the provided value.
     *
     * @param name The name of the solo musician.
     */
    public Soloists(String name) {
        super(name);
    }

    /**
     * Overrides the toString() method to provide a custom string representation of the solo musician.
     *
     * @return A string representation of the solo musician.
     */
    @Override
    public String toString() {
        return "Solo Musician: '" + getName() + "'";
    }
}
