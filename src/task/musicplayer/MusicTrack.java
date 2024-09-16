package task.musicplayer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a music track in the music player system. Each track has a name,
 * list of artists, release date, duration, rating, location, size, guest artists,
 * and play count.
 *
 * <p>
 * The MusicTrack class provides methods to add guest artists, retrieve
 * all artists on the track, increase the play count, and obtain track details.
 * </p>
 *
 * @author Nathan Li
 * @version 2023.10.8
 */
public class MusicTrack {
    private String name;
    private List<Artist> listArtist;
    private Date date;
    private double length;
    private double rating;
    private String location;
    private double size;
    private List<Artist> guestArtist;
    private int playCount;

    /**
     * B1.1 constructor of track aim to Create a track based on whether it's a soloist or a group of singers.
     * @param name      the name
     * @param listArtist the list artist
     * @param date       the date
     * @param length     the length
     * @param rating     the rating
     * @param location   the location
     * @param size       the size
     */
    public MusicTrack(String name, List<Artist> listArtist, Date date, double length, double rating, String location, double size) {
        this.name = name;
        this.listArtist = listArtist;
        this.date = date;
        this.length = length;
        this.rating = rating;
        this.location = location;
        this.size = size;
        this.playCount = 0;
    }


    public MusicTrack() {
        this.listArtist= new ArrayList<>();
        this.guestArtist = new ArrayList<>();
    }

    /**
     * B1.2 Getter and Setter method.
     *
     */

    public void setListArtist(List<Artist> listArtist) {
        this.listArtist = listArtist;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setGuestArtist(List<Artist> guestArtist) {
        this.guestArtist = guestArtist;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public String getName() {
        return name;
    }

    public List<Artist> getListArtist() {
        return listArtist;
    }

    public Date getDate() {
        return date;
    }

    public double getLength() {
        return length;
    }

    public double getRating() {
        return rating;
    }

    public String getLocation() {
        return location;
    }

    public double getSize() {
        return size;
    }

    public List<Artist> getGuestArtist() {
        return guestArtist;
    }

//    /**
//     * Create.
//     */
//    public void createFromInput(){
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Track name: ");
//        String name = scanner.nextLine();
//        this.setName(name);
//
//        System.out.print("Artist name: ");
//        String artistName = scanner.nextLine();
//        Artist artist = new Artist(artistName);
//        this.setArtist(artist);
//
//        System.out.print("Date: ");
//        String dateString = scanner.nextLine();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//        try {
//            this.date = dateFormat.parse(dateString);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        this.setDate(date);
//
//        System.out.print("Length (in seconds): ");
//        int length = scanner.nextInt();
//        this.setLength(length);
//
//        System.out.print("Rating (1-5): ");
//        int rating = scanner.nextInt();
//        this.setRating(rating);
//        scanner.nextLine();
//
//        System.out.print("Location: ");
//        String location = scanner.nextLine();
//        this.setLocation(location);
//
//        System.out.print("Size (in MB): ");
//        int size = scanner.nextInt();
//        this.setSize(size);
//
//
//    }

    /**
     * B1.3 Add a guest artist to the track.
     *
     * @param guestArtist the guest artist
     */
    public void addGuestArtist(Artist guestArtist) {
        List<Artist> newGuestArtist = getGuestArtist();
        newGuestArtist.add(guestArtist);
        setGuestArtist(newGuestArtist);
    }

    /**
     * B1.4 Retrieves a list of all individuals on the specified music track.
     *
     * @return allArtists A list containing all individuals.
     *
     */

    public List<Artist> getAllArtists() {
        List<Artist> allArtists = new ArrayList<>();
        allArtists.addAll(this.getListArtist());
        return allArtists;
    }

    /**
     * B1.5 Add to the play count of a track.
     *
     * @return Modified Count of play.
     */

    public void increasePlayCount () {
            setPlayCount(getPlayCount()+1);
    }

    /**
     * B1.6 Get the play count of a track.
     *
     * @return present Count of play
     */

    public int getPlayCount () {
            return playCount;
    }


    /**
     * Reads track information from a text file using the provided Scanner and populates the MusicTrack object with the read data,
     * including name, artists (both soloists and bands), release date, length, rating, location, and size.
     *
     * @param scanner The Scanner object for reading data from the text file.
     */
    public void createFromTextFile(Scanner scanner) {

        while (scanner.hasNextLine()) {
            this.setName(scanner.nextLine());
            String artistName=null;
            String line=null;
            String bandName =null;
            List<Artist> newArtistLis=getListArtist();

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.startsWith("Band ")) {
                    String[] parts = line.substring(5).split(",");
                    bandName = parts[0].trim();
                    Band band1= new Band(bandName);
                    for (int i = 1; i < parts.length; i++) {
                        String memberName = parts[i].trim();
                        Artist memberArtist = new Artist(memberName);
                        band1.addMember(memberArtist);
                    }

                    newArtistLis.add(band1);

                }
                else {
                    if (line.matches("\\d{4}-\\d{2}-\\d{2}")) {
                        break;
                    } else {
                        artistName = line;
                        Soloists soloists = new Soloists(artistName);
                        newArtistLis.add(soloists);
                    }
                }
            }

            String dateString = line;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                this.setDate(dateFormat.parse(dateString));
            } catch (ParseException e) {
                e.printStackTrace();
            }


            String doubleLength =scanner.nextLine();
            if (doubleLength.indexOf(":")!=-1){
                doubleLength=doubleLength.replace(":",".");
            }
            this.setLength(Double.parseDouble(doubleLength));
            this.setRating(Double.parseDouble(scanner.nextLine()));
            this.setLocation(scanner.nextLine());
            this.setSize(Double.parseDouble(scanner.nextLine()));

            if(scanner.hasNext()&&scanner.nextLine().equals("")){
                break;
            }
        }

    }

    @Override
    public String toString() {
        if (guestArtist==null) {
            return ("Name: " + this.getName() + "\n" +
                    "Artist: " + this.getListArtist() + "\n" +
                    "Date: " + this.getDate() + "\n" +
                    "Length: " + this.getLength() + "\n" +
                    "Rating: " + this.getRating() + "\n" +
                    "Location: " + this.getLocation() + "\n" +
                    "Size: " + this.getSize() + "\n" +
                    "Play Count: " + this.getPlayCount() + "\n");
        } else {
            return ("Name: '" + this.getName() + "'\n" +
                    "Artist: '" + this.getListArtist() + "'\n" +
                    "Date: '" + this.getDate() + "'\n" +
                    "Length: '" + this.getLength() + "'\n" +
                    "Rating: '" + this.getRating() + "'\n" +
                    "Location: '" + this.getLocation() + "'\n" +
                    "Size: '" + this.getSize() + "'\n" +
                    "Play Count: '" + this.getPlayCount() + "'\n" +
                    "GuestArtist: "+this.toStringGuestArtist()+"\n");
        }
    }

    public String toStringGuestArtist() {
        String result="";

        for (Artist artist:this.getGuestArtist()){
            result += "Name: '" + artist.getName() + "'";
        }
        return result;
    }



}

