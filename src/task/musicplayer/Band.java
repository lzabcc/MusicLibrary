package task.musicplayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a music band with a name and a list of members.
 * Extends the Artist class, inheriting the name attribute.
 * Provides methods to manage band members and obtain string representations of the band.
 *
 * @author Nathan Li
 * @version 2023.10.8
 */
public class Band extends Artist {

    private List<Artist> members;

    /**
     * Constructs a new Band object with the specified name.
     *
     * @param name The name of the band.
     */
    public Band(String name) {
        super(name);
        this.members = new ArrayList<>();
    }

    /**
     * Constructs a new Band object with the specified name and members.
     *
     * @param name    The name of the band.
     * @param members List of artists who are members of the band.
     */
    public Band(String name, List<Artist> members) {
        super(name);
        this.members = new ArrayList<>(members);
    }

    /**
     * Gets a new list containing all the members of the band.
     *
     * @return A new list containing all the members of the band.
     */
    public List<Artist> getMembers() {
        return new ArrayList<>(members);
    }

    /**
     * Sets the members of the band using a new list, ensuring external modification does not affect the band's data.
     *
     * @param members List of artists who are members of the band.
     */
    public void setMembers(List<Artist> members) {
        this.members = new ArrayList<>(members);
    }

    /**
     * Adds an artist as a member of the band.
     *
     * @param artist The artist to be added as a member of the band.
     * @throws IllegalArgumentException if the artist is null.
     */
    public void addMember(Artist artist) {
        if (artist != null) {
            members.add(artist);
        } else {
            throw new IllegalArgumentException("Artist cannot be null.");
        }
    }

    /**
     * Removes an artist from the band's members.
     *
     * @param artist The artist to be removed from the band.
     */
    public void removeMember(Artist artist) {
        members.remove(artist);
    }

    /**
     * Overrides the default toString() method to provide a custom string representation of the band object.
     *
     * @return A string representation of the band object.
     */
    @Override
    public String toString() {
        return "bandName:'" + getName() + "'\n"+
                "   members:'" + members + "'";
    }

    /**
     * Returns a string representation of the band's members, each member's name prefixed with "BandMember: ".
     *
     * @return A string representation of the band's members.
     */
    public String toBandString() {
        StringBuilder result = new StringBuilder();
        for (Artist artist : this.getMembers()) {
            result.append("BandMember: ").append(artist.toString()).append("\n");
        }
        return result.toString();
    }
}
