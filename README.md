Our "Music Library Management System" project aims to create a music information management system similar to iTunes. The system primarily involves the management of artists, music tracks, albums, and music libraries. 
This project have solved the following problems below:

Problem: Basic I

• Define a Java class to store information about artists, including their name and membership to bands if appropriate.

• Define a Java class to store information about music tracks. Include methods to:

o Create a track.

o Set and get the title, artist, date, length, rating, location, and size of the track.

o Add a guest artist to the track.

o Get a list of all individuals on the track, including band members and guest artists.

o Add to the play count of a track. 

o Get the play count of a track.

Problem: Basic II
• Define a Java class to represent albums. Provide methods to:

o Create an album.

o Set and get the name and type of the album and the artist.

o Define the list of tracks for the album.

o Get the overall running time of the album.

o Get the overall file size of the album.

o Get the average rating of tracks on the album.


Problem: Basic III

• Define a Java class to represent a music library. Provide methods to:

o Create a library.

o Add tracks and/or albums to the library.

o Create a list of tracks from the library with the lowest rating.

Problem: Extensions I
Define a special type of album, the compilation album. These albums may or may not have an artist or may have a number of different artists. Each track on a compilation album should also store the original album that the track came from. For example, Bohemian Rhapsody by Queen was originally on the album A Night at the Opera and subsequently appeared on the compilation album Greatest Hits Vol I, part of the Queen Greatest Hits series.

Problem: Extensions II
Write a method for a library that works out how to back up all the music tracks onto a small number of discs. Different types of discs formats such as CD or DVD have different capacities in terms of total file size, so the capacity of the disc should be a parameter to the method.



I have designed four primary classes: Artist, MusicTrack, Album, and MusicLibrary. These classes represent the main entities of the system. Additionally, to enhance system efficiency, I implemented two key strategies. Firstly, I utilized HashMaps to optimize the common task of searching for specific music tracks and artists within a large collection. By storing music tracks and artist objects as key-value pairs, with names as keys, I streamlined the retrieval process, significantly improving the efficiency of track and artist lookups in the Music Library. Secondly, I replaced direct output operations with logs to reduce I/O overhead. Moving on to Extension 2, I employed four algorithms: "First Fit," "Best Fit," "Worst Fit," and "First Fit Decreasing." Although I attempted to implement the "Simulated Annealing Algorithm" for optimization, I encountered challenges and ultimately reverted to the aforementioned algorithms. 

Here is the diagram of this project.
![image](https://github.com/user-attachments/assets/bf778d2d-71f8-4054-826b-d56dafe99d0b)
