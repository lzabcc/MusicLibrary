package task.musicplayer;

import java.io.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * The `Main` class serves as the entry point for the Music Player application. It initializes a music library,
 * reads data from text files, performs various operations related to music tracks, albums, and artists,
 * and writes the results to an output text file.
 * It demonstrates the functionality of the Music Library, MusicTrack, Album, CompilationAlbum, and DiskPackingAlgorithm classes.
 * The class also provides methods for reading input from text files and writing output to the "musicLibrary_output.txt" file.
 * The application showcases the implementation of different algorithms for handling music tracks and albums.
 *
 * @author Nathan Li
 * @version 2023.10.13
 */

public class Main {
    public static final int DESIGNATED_LOCATION = 0;
    private static final Logger logger = Logger.getLogger(Main.class.getName());


    /**
     * Reads data from a text file using the provided file path and returns a Scanner object for reading.
     *
     * @param textPath The file path of the text file to be read.
     * @return A Scanner object for reading data from the text file.
     */
    public static Scanner readFromTextFile(String textPath) {
        Scanner scanner=null;
        try {
            scanner = new Scanner(new File(textPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return scanner;
    }

    /**
     * Method to print formatted prompt messages to the specified BufferedWriter.
     *
     * @param out      The BufferedWriter object where the prompt message will be written.
     * @param number   The identifier number for the prompt message section.
     * @param methodName The name of the method associated with the prompt message.
     * @param info     Additional information or context to be included in the prompt message.
     */
    public static void printPromptMessage(BufferedWriter out, String number, String methodName,String info){
        try {
            String firstLine = "-----------"+number+"-----------\n";
            String secondLine = "["+methodName+"]\n";
            String thirdLine = ("("+info+")\n");
            out.write(firstLine);
            out.write(secondLine);
            out.write(thirdLine);
            out.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printPromptMessage(BufferedWriter out, String number, String methodName){
        try {
            String firstLine = "-----------"+number+"-----------\n";
            String secondLine = "["+methodName+"]\n";
            out.write(firstLine);
            out.write(secondLine);
            out.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to print values to the specified BufferedWriter.
     *
     * @param out     The BufferedWriter object where the values will be written.
     * @param values  The string containing the values to be written.
     */
    public static void printValues(BufferedWriter out, String values){
        try {
            out.write(values);
            out.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MusicLibrary musicLibrary = new MusicLibrary();
        BufferedWriter out = null;

        try {
            out = new BufferedWriter(new FileWriter("musicLibrary_output.txt"));
            out.write(" * @Author Nathan Li\n" +
                    " * @Version 2023.10.13\n");
            out.newLine();
            out.write("-----------MusicTrack Method-----------\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        printPromptMessage(out,"B1.1 and B1.2",
                "Create MusicTrack object from File",
                "Each musicTrack constructed successfully.Its values will show in B3.2 'Add tracks and/or albums to the library.' ");
        Scanner scanner_musicTrack = readFromTextFile("src/songs/musicTracks.txt");

        while (scanner_musicTrack.hasNextLine()) {
            MusicTrack musicTrack = new MusicTrack();
            musicTrack.createFromTextFile(scanner_musicTrack);
            musicLibrary.addMusicTrack(musicTrack);
            musicLibrary.addArtist(musicTrack.getAllArtists());
        }



            scanner_musicTrack.close();

        printPromptMessage(out,"B1.3","Add Guest Artist");

        Scanner scanner_GuestArtist = readFromTextFile("src/songs/guestArtist.txt");

            while (scanner_GuestArtist.hasNext()) {
                String searched_name = scanner_GuestArtist.nextLine();
                MusicTrack musicTrack_needToAddGuestArtist = musicLibrary.findTrackByName(searched_name);

                String guestArtistName = scanner_GuestArtist.nextLine();
                Artist guestArtist = new Artist(guestArtistName);
                musicTrack_needToAddGuestArtist.addGuestArtist(guestArtist);
                if (scanner_GuestArtist.hasNext() && "".equals(scanner_GuestArtist.nextLine())) {
                    logger.info(guestArtist.getName() + "added successfully");
                }

                printValues(out, "Name: '" + musicTrack_needToAddGuestArtist.getName() + "'\n" + "GuestArtist: " + musicTrack_needToAddGuestArtist.toStringGuestArtist() + "\n");
            }

        scanner_GuestArtist.close();


        printPromptMessage(out,"B1.4",
                "Retrieves a list of all individuals on the specified music track.",
                "Default show the artist of the fifth song");

        MusicTrack musicTrack_needToShowIndividuals=musicLibrary.getTracksList().get(4);

        for (Artist artist:musicTrack_needToShowIndividuals.getAllArtists()) {
            if (artist instanceof Band){
                 Band bandMember=(Band) artist;
                 printValues(out,"Artist: " + bandMember.toBandString());
            }else{
                Soloists soloists = (Soloists) artist;
                printValues(out,"Artist: " + soloists.toString());
            }

        }
        printValues(out, "GuestArtist:"+musicTrack_needToShowIndividuals.toStringGuestArtist()+"\n");

        printPromptMessage(out,"B1.5 and B1.6",
                "Add to the play count of a track and Get the play count of a track.",
                "Default show and operate the playCount of the second song(previous operation is the first song)");

        MusicTrack musicTrack_needToGetAndAddPlayCount=musicLibrary.getTracksList().get(1);

        printValues(out,"Present PlayCount: "+musicTrack_needToGetAndAddPlayCount.getPlayCount());
        musicTrack_needToGetAndAddPlayCount.increasePlayCount();
        printValues(out,"Success! AfterAddPlayCount: "+musicTrack_needToGetAndAddPlayCount.getPlayCount()+"\n");

        try {
            out.write("-----------Album method-----------\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        printPromptMessage(out,"B2.1,B2.2 and B2.3",
                "Create Album object from File",
                "The file contains two album information\n" +
                        "Default add the first and second song to the first album\n" +
                        "Default add the second and third song to the second album\n"+
                        "Each album constructed successfully.\nIts values are as follows");
        Scanner scanner_album =readFromTextFile("src/songs/album.txt");

        int track_loc_b2 =DESIGNATED_LOCATION;


            while (scanner_album.hasNextLine()) {
                Album albumCreate = new Album();
                albumCreate.createFromTextFile(scanner_album, musicLibrary, track_loc_b2);
                track_loc_b2 += 1;
                printValues(out, albumCreate.toString());
                musicLibrary.addAlbum(albumCreate);

            }

        scanner_album.close();

        printPromptMessage(out, "B2.4,B2.5 and B2.6",
                "Get the overall running time, the overall file size, the average rating of tracks on the album",
                "Default show the first album details");

        Album albumNeedToShowDetails =musicLibrary.getAlbumsList().get(0);
        int overallRunningTime=albumNeedToShowDetails.getOverallRunningTime();
        int overallSize=albumNeedToShowDetails.getOverallFileSize();
        double averageRating=albumNeedToShowDetails.getAverageRating();

        String AlbumPrompt="Album <";
        printValues(out, AlbumPrompt + albumNeedToShowDetails.getName() + "> overall running time '" + overallRunningTime + "'");
        printValues(out, AlbumPrompt + albumNeedToShowDetails.getName() + "> overall size '" + overallSize + "'");
        printValues(out, AlbumPrompt + albumNeedToShowDetails.getName() + "> average rating '" + averageRating+"'\n");

        printPromptMessage(out, "B3.1,3.2 and 3.3",
                "Create a library object. Add tracks and/or albums to the library. Create a list of tracks from the library with the lowest rating");

        List<MusicTrack> musicTrackListLibrary = musicLibrary.getTracksWithLowestRating();

        printValues(out, "Music Library constructed successfully");
        printValues(out, "Its Tracks are as follows\n");

        List<MusicTrack> allTracks = musicLibrary.getTracksList();
        for (MusicTrack track : allTracks) {
            printValues(out, track.toString());
        }

        printPromptMessage(out,"-","album value");
        printValues(out, "Album constructed successfully");
        printValues(out, "Its values are as follows\n");
        List<Album> allAlbums = musicLibrary.getAlbumsList();
        for (Album album : allAlbums) {
            printValues(out, album.toString());
        }
        printPromptMessage(out,"-","lowest track list");
        printValues(out, "A list of tracks from the library with the lowest rating\n");
        for (MusicTrack track : musicTrackListLibrary) {
            printValues(out, "The lowest rating: '" + track.getRating()+"'\n"+"The lowest rating track name: '" + track.getName()+"'\n");
        }


        printPromptMessage(out, "Extensions 1",
                "the compilation album",
                "Default add the first song from first album and second song from second album to the complication album");

        int locE1= DESIGNATED_LOCATION;

        MusicTrack firstTrackAddToAlbum = musicLibrary.getAlbumsList().get(locE1).getTrackList().get(locE1);

        MusicTrack secondTrackAddToAlbum = musicLibrary.getAlbumsList().get(locE1+1).getTrackList().get(locE1+1);

        CompilationAlbum compilationAlbum= new CompilationAlbum("ComplicationAlbum","pop");
        compilationAlbum.addTrackAndOriginalAlbum(firstTrackAddToAlbum,musicLibrary.getAlbumsList().get(locE1).getName());
        compilationAlbum.addTrackAndOriginalAlbum(secondTrackAddToAlbum,musicLibrary.getAlbumsList().get(locE1+1).getName());

        printValues(out, "The complication album constructed successfully.\nIts values are as follows\n");
        printValues(out, compilationAlbum.toString());


        printPromptMessage(out, "Extensions 2",
                "Back up all the music tracks onto a small number of discs",
                "Default discs size is random, range is (10-20)");
        printValues(out,"Best algorithm is");

        Random randomValue = new Random();
        double disc= randomValue.nextDouble()*10+11;
        logger.info("disc capacity is "+String.format("%.2f",disc)+"\n");
        String resultE2 = musicLibrary.backupToDiscs(musicLibrary.getTracksList(),disc);

        printValues(out, resultE2);
        printValues(out, "-----------end -----------");

        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


