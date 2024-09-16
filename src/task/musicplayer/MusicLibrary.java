package task.musicplayer;

import java.util.*;

import java.util.logging.Logger;

/**
 * MusicLibrary class represents a music library for managing music tracks and albums.
 * This class provide add method to add music tracks and albums to the library and perform some track- and album-related operations.
 *
 * @author Nathan Li
 * @version 2023.10.8
 */
public  class MusicLibrary {
    private static final Logger logger1 = Logger.getLogger(MusicLibrary.class.getName());
    private List<Album> albumsList;

    private HashMap<String, MusicTrack> trackMap;
    private HashMap<String, Artist> artistMap;
    /**
     *
     * B3.1 Create a library.Constructor of MusicLibrary Class aim to Create MusicLibrary object.
     *
     */
    public MusicLibrary() {
        //this.tracksList = new ArrayList<>();
        this.albumsList = new ArrayList<>();
        //this.artistList = new ArrayList<>();
        //this.bandList = new ArrayList<>();
        this.trackMap = new HashMap<>();
        this.artistMap = new HashMap<>();
    }

    public List<MusicTrack> getTracksList() {
        return new ArrayList<>(trackMap.values());
    }

    public List<Album> getAlbumsList() {
        return albumsList;
    }

    public List<Artist> getArtistList() {
        return new ArrayList<>(artistMap.values());
    }



    /**
     * B3.2 Add tracks to the library.
     *
     * @param track The track  from which need added to the list.
     *
     */
    public void addMusicTrack(MusicTrack track) {
        trackMap.put(track.getName(), track);
    }

    /**
     * B3.2 Add albumsList to the library.
     *
     * @param album The  album from which need added to the list.
     *
     */
    public void addAlbum(Album album) {
        albumsList.add(album);
    }

    public void addArtist(List<Artist> artistList){
        for (Artist artist: artistList){
            artistMap.put(artist.getName(), artist);
        }
    }



    /**
     * B3.3 Create a list of tracks from the library with the lowest rating.
     *
     * @return lowestRatedTracks Tracks with the lowest rating.
     *
     */
    public List<MusicTrack> getTracksWithLowestRating() {
        double lowestRating = Integer.MAX_VALUE;
        List<MusicTrack> lowestRatedTracks = new ArrayList<>();
        for (MusicTrack track : this.getTracksList()) {
            if (track.getRating() < lowestRating) {
                lowestRating = track.getRating();
                lowestRatedTracks.clear();
                lowestRatedTracks.add(track);
            } else if (track.getRating() == lowestRating) {
                lowestRatedTracks.add(track);
            }
        }
        return lowestRatedTracks;
    }


    /**
     * B3.3 Create a list of tracks from the library with the lowest rating.
     *
     * @param musicTracksDemo list of music track sizes in megabytes.
     * @param discCapacity The capacity of each disc in megabytes.
     * @return backupSolution A list of lists, where each inner list represents the music tracks
     *         on a single disc.
     */

    public String backupToDiscs(List<MusicTrack> musicTracksDemo, double discCapacity) {
        DiskPackingAlgorithm diskPackingAlgorithm = new DiskPackingAlgorithm();

        Map<String, List<Double>> methodsToResults = new HashMap<>();
        Map<String, Long> methodsToTimes = new HashMap<>();

        // Run and measure time for each algorithm
        for (String method : Arrays.asList("First Fit", "Worst Fit", "Best Fit", "First Fit Decreasing")) {
            long startTime = System.nanoTime();
            List<Double> discs = null;

            switch (method) {
                case "First Fit":
                    discs = diskPackingAlgorithm.firstFit(musicTracksDemo, discCapacity);
                    break;
                case "Worst Fit":
                    discs = diskPackingAlgorithm.worstFit(musicTracksDemo, discCapacity);
                    break;
                case "Best Fit":
                    discs = diskPackingAlgorithm.bestFit(musicTracksDemo, discCapacity);
                    break;
                case "First Fit Decreasing":
                    discs = diskPackingAlgorithm.firstFitDecreasing(musicTracksDemo, discCapacity);
                    break;
            }

            long endTime = System.nanoTime();
            long executionTime = endTime - startTime;

            methodsToResults.put(method, discs);
            methodsToTimes.put(method, executionTime);
        }

        String optimalMethod = null;
        int smallestDisc = Integer.MAX_VALUE;
        long smallestTime = Long.MAX_VALUE;

        for (Map.Entry<String, List<Double>> entry : methodsToResults.entrySet()) {
            String method = entry.getKey();
            List<Double> discs = entry.getValue();
            long executionTime = methodsToTimes.get(method);

            if (discs.size() < smallestDisc || (discs.size() == smallestDisc && executionTime < smallestTime)) {
                smallestDisc = discs.size();
                smallestTime = executionTime;
                optimalMethod = method;
            }

            logger1.info(method + " Result:\n" + diskPackingAlgorithm.displayDiscs(discs, discCapacity, getMethodNumber(method)) + "total time: " + executionTime + "\n");
        }

        logger1.info("Optimal method is: " + optimalMethod);
        logger1.info("Disc use less is " + smallestDisc);
        logger1.info("Time use less is " + smallestTime);

        return diskPackingAlgorithm.displayDiscs(methodsToResults.get(optimalMethod), discCapacity, getMethodNumber(optimalMethod));
    }
    private int getMethodNumber(String method) {
        switch (method) {
            case "First Fit":
                return 1;
            case "Worst Fit":
                return 2;
            case "Best Fit":
                return 3;
            default:
                return 4;
        }
    }




    /**
     * Customize Find track from the library.
     *
     * @param selectedSongName  A song track need to be searched.
     * @return tracks Tracks have been found.
     *
     */
    public MusicTrack findTrackByName(String selectedSongName) {
        return trackMap.get(selectedSongName);
    }

    public Album findAlbumByName(String selectedAlbumName) {
        for(Album album: albumsList){
            if (album.getName().equals(selectedAlbumName)){
                return album;
            }
        }
        return null;

    }

    public Artist findArtistByName(String selectedArtistName) {

        return artistMap.get(selectedArtistName);
    }


    /**
     * Play music and increase the play count.
     * @param  music  A song track need to be searched.
     */

    public void playMusicTrack(MusicTrack music){
        music.increasePlayCount();

    }

    public void playAlbum(MusicTrack music){
        music.increasePlayCount();
    }




}
