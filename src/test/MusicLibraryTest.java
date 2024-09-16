package test;

import task.musicplayer.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The `MusicLibraryTest` class contains unit tests for the methods of the `MusicLibrary` class.
 * It includes tests for adding music tracks, albums, and artists, performing operations on the music tracks,
 * and checking functionality related to track and album searching and playback.
 * These tests ensure the correctness of the MusicLibrary implementation.
 *
 *  * @author Nathan Li
 *  * @version 2023.10.18
 */
class MusicLibraryTest {

    //"John Lennon", "Imagine"
    //"Elton John", "Rocket Man"

    //"The Beatles", "Hey Jude", "Paul McCartney, George Harrison, Ringo Starr"
    //"Queen", "Bohemian Rhapsody", "Brian May, John Deacon, Roger Taylor"
    //"The Rolling Stones", "Paint It Black", "Keith Richards, Mick Jagger, Charlie Watts, Bill Wyman"


    @org.junit.jupiter.api.Test
    void getTracksList() {

        // 创建一个音乐库对象
        MusicLibrary musicLibrary = new MusicLibrary();


        // 添加一些音乐曲目到音乐库（这里使用假数据，实际应用中你可能会使用Mock数据）

        Scanner scanner=null;
        try {
            scanner = new Scanner(new File("src/test/testTxt/musicTest.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        MusicTrack track1 = new MusicTrack();

        MusicTrack track2 =  new MusicTrack();
        track1.createFromTextFile(scanner);
        track2.createFromTextFile(scanner);
        musicLibrary.addMusicTrack(track1);
        musicLibrary.addMusicTrack(track2);

        // 调用被测试的方法
        List<MusicTrack> tracksList = musicLibrary.getTracksList();

        // 验证返回的曲目列表不为空
        assertNotNull(tracksList);

        // 验证曲目列表中包含添加的曲目
        assertEquals(2, tracksList.size());
        assertEquals("Imagine", tracksList.get(0).getName());
        assertEquals("Hey Jude", tracksList.get(1).getName());
    }

    @org.junit.jupiter.api.Test
    void getAlbumsList() {
        MusicLibrary musicLibrary = new MusicLibrary();

        Scanner scanner=null;
        try {
            scanner = new Scanner(new File("src/test/testTxt/musicTest.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        MusicTrack track1 = new MusicTrack();
        MusicTrack track2 = new MusicTrack();
        MusicTrack track3 = new MusicTrack();
        track1.createFromTextFile(scanner);
        track2.createFromTextFile(scanner);
        track3.createFromTextFile(scanner);


        musicLibrary.addMusicTrack(track1);
        musicLibrary.addMusicTrack(track2);
        musicLibrary.addMusicTrack(track3);

        Album album1 = new Album();
        Album album2 = new Album();

        try {
            scanner = new Scanner(new File(
                    "src/test/testTxt/albumTest.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        album1.createFromTextFile(scanner, musicLibrary,0);

        musicLibrary.addAlbum(album1);
        musicLibrary.addAlbum(album2);

        assertEquals(2, musicLibrary.getAlbumsList().size());
        assertEquals(album1, musicLibrary.getAlbumsList().get(0));
        assertEquals(album2, musicLibrary.getAlbumsList().get(1));
    }


    @org.junit.jupiter.api.Test
    void getArtistList() {
    }

    @org.junit.jupiter.api.Test
    void addMusicTrack() {
        MusicLibrary musicLibrary = new MusicLibrary();
        List artistList= new ArrayList<>();

        Date date = new Date(2001-04-12);
        artistList.add("Rocket Man");
        MusicTrack track = new MusicTrack("Elton John", artistList,date, 3.5, 4, "C:/Music/Elton John.mp3", 5.2);

        musicLibrary.addMusicTrack(track);

        assertEquals(1, musicLibrary.getTracksList().size());
        assertEquals(track, musicLibrary.getTracksList().get(0));
    }

    @org.junit.jupiter.api.Test
    void addAlbum() {
    }

    @org.junit.jupiter.api.Test
    void addArtist() {
    }


    @org.junit.jupiter.api.Test
    void backupToDiscs() {

        MusicLibrary musicLibrary = new MusicLibrary();

        Scanner scanner=null;
        try {
            scanner = new Scanner(new File("src/test/testTxt/musicTest.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(scanner.hasNext()){
            MusicTrack track1 = new MusicTrack();
            track1.createFromTextFile(scanner);
            musicLibrary.addMusicTrack(track1);
        }

        String backupResult = musicLibrary.backupToDiscs(musicLibrary.getTracksList(), 12);

        assertNotNull(backupResult, "Backup result should not be null");
        System.out.println(backupResult);



    }

    @org.junit.jupiter.api.Test
    void findTrackByName() {
        MusicLibrary musicLibrary = new MusicLibrary();
        List artistList= new ArrayList<>();

        Date date = new Date(2001-04-12);
        artistList.add("Rocket Man");
        MusicTrack track = new MusicTrack("Elton John", artistList,date, 3.5, 4, "C:/Music/Elton John.mp3", 5.2);
        musicLibrary.addMusicTrack(track);
        musicLibrary.playMusicTrack(track);
        assertEquals(track, musicLibrary.findTrackByName("Elton John"));
        assertNull(musicLibrary.findTrackByName("NonExistentTrack"));

    }

    @org.junit.jupiter.api.Test
    void findAlbumByName() {


    }

    @org.junit.jupiter.api.Test
    void findArtistByName() {
    }

    @org.junit.jupiter.api.Test
    void playMusicTrack() {
        MusicLibrary musicLibrary = new MusicLibrary();
        List artistList= new ArrayList<>();

        Date date = new Date(2001-04-12);
        artistList.add("Rocket Man");
        MusicTrack track = new MusicTrack("Elton John", artistList,date, 3.5, 4, "C:/Music/Elton John.mp3", 5.2);
        musicLibrary.addMusicTrack(track);
        musicLibrary.playMusicTrack(track);
        assertEquals(1, track.getPlayCount());

    }

    @org.junit.jupiter.api.Test
    void playAlbum() {
    }

    @org.junit.jupiter.api.Test
    void getTracksWithLowestRating() {
        MusicLibrary musicLibrary = new MusicLibrary();

        Scanner scanner=null;
        try {
            scanner = new Scanner(new File("src/test/testTxt/musicTest.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        MusicTrack track1 = new MusicTrack();
        MusicTrack track2 = new MusicTrack();
        MusicTrack track3 = new MusicTrack();
        track1.createFromTextFile(scanner);
        track2.createFromTextFile(scanner);
        track3.createFromTextFile(scanner);


        musicLibrary.addMusicTrack(track1);
        musicLibrary.addMusicTrack(track2);
        musicLibrary.addMusicTrack(track3);

        List<MusicTrack> lowestRatedTracks = musicLibrary.getTracksWithLowestRating();

        assertEquals(1, lowestRatedTracks.size());
        assertEquals(track3, lowestRatedTracks.get(0));
    }
}





