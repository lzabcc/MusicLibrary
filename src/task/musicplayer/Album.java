package task.musicplayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The `Album` class represents a music album containing a collection of tracks.
 * Each album has a name, type, and a list of music tracks associated with it.
 * Users can add tracks to the album and retrieve information such as overall running time,
 * overall file size, and average rating of the tracks.
 *
 * @author Nathan Li
 * @version 2023.10.8
 */
public  class Album {
    private String name;
    private String type;
    private List<MusicTrack> trackList;

    /**
     * B2.1  constructor of Album Class aim to Create an album object.
     *
     * @param name        The name of the album.
     * @param type        The type or genre of the album.
     * @param musicTracks The list of music tracks in the album.
     *
     */
    public Album(String name, String type, List<MusicTrack> musicTracks) {
        this.name = name;
        this.type = type;
        this.trackList = musicTracks;
    }
    public Album(){
        this.name = "";
        this.type = "";
        this.trackList = new ArrayList<>();
    }
    /**
     * B2.2 Set and get the name and type of the album and the artist.
     *
     * @return Assigned attributes
     *
     */

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTrackList(List<MusicTrack> trackList) {
        this.trackList = trackList;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public List<MusicTrack> getTrackList() {
        return trackList;
    }

    public void addTrack(MusicTrack track) {
        trackList.add(track);
    }

//create from input
//    public void initiateAlbum(MusicLibrary musicLibrary){
//        trackList=new ArrayList<>();
//        Scanner scanner1= new Scanner(System.in);
//
//        System.out.print("name: ");
//        String Name="";
//        Name = scanner1.nextLine();
//        this.setName(Name);
//
//        System.out.print("type: ");
//        String Type="";
//        Type = scanner1.nextLine();
//        this.setType(Type);
//
//        String track_name;
//        MusicTrack searchedTrack;
//        do{
//            System.out.print("Track need to be added(enter track name,enter 0 to end): \n");
//            track_name=scanner1.nextLine();
//            if(track_name.equals("0"))break;
//            searchedTrack=musicLibrary.findTrackByName(track_name);
//            trackList.add(searchedTrack);
//        }while(track_name!="0");
//
//    }

    /**
     * B2.3 Define the list of tracks for the album.
     *
     * @param scanner     The Scanner object used to read data from the text file.
     * @param musicLibrary The MusicLibrary object containing the collection of tracks for reference.
     * @param track_loc    The starting index of the track in the MusicLibrary to be added to the album.
     *
     */
    public void createFromTextFile(Scanner scanner,MusicLibrary musicLibrary,int track_loc) {
            int flag=track_loc;
            while (scanner.hasNextLine()) {
                this.setName(scanner.nextLine());
                this.setType(scanner.nextLine());

                for (int i = flag;i<=flag+1;i++) {
                    trackList.add(musicLibrary.getTracksList().get(i));
                }

                if(scanner.hasNext()&&scanner.nextLine().equals("")){
                    break;
                }
            }

    }

    /**
     * B2.4 Get the overall running time of the album.
     *
     * @return A guest already assigned.
     *
     */
    public int getOverallRunningTime() {
        int totalTime = 0;
        for (MusicTrack track : trackList) {
            totalTime += track.getLength();
        }
        return totalTime;
    }

    /**
     * B2.5 Get the overall file size of the album.
     *
     * @return A guest already assigned.
     *
     */
    public int getOverallFileSize() {
        int totalSize = 0;
        for (MusicTrack track : trackList) {
            totalSize += track.getSize();
        }
        return totalSize;
    }

    /**
     * B2.6 Get the average rating of tracks on the album.
     *
     * @return A guest already assigned.
     *
     */
    public double getAverageRating() {
        if (trackList.isEmpty()) {
            return 0;
        }
        int totalRating = 0;
        for (MusicTrack track : trackList) {
            totalRating += track.getRating();
        }
        return (double) totalRating / trackList.size();
    }

    @Override
    public String toString() {
        String musicTrackList="";
        for (MusicTrack track1:trackList){
            musicTrackList+=track1.getName()+",";

        }

        return "AlbumName: '" + name + "'\n" +
                "Type: '" + type + "'\n" +
                "Tracks: '" + musicTrackList+"'\n";

    }



}
