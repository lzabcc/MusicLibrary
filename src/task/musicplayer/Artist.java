package task.musicplayer;

/**
 * This class represents an Artist in the music player system.
 * It maintains details of an artist including their name and band affiliation.
 *
 * <p>
 * An artist can be a solo musician or a member of a band. The class provides methods
 * to get and set the artist's name, and it overrides the toString method for a custom
 * string representation of the artist.
 * </p>
 *
 * @author Nathan Li
 * @version 2023.10.8
 */
public class Artist {

    private String name;

    /**
     * Constructor of Artist Class aim to Create an artist object.
     *
     */
    public Artist() {
        this.name = null;
    }

    public Artist(String name) {
        this.name = name;
    }

    /**
     * Getter and Setter method.
     *
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "artistName: '" + getName() + "'";
    }
}
