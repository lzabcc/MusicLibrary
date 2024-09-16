package task.musicplayer;

/**
 * A compilation album collection of tracks gathered from various sources.
 *
 * @author Nathan Li
 * @version 2023.10.11
 */
import java.util.HashMap;
import java.util.Map;

public class CompilationAlbum extends Album {
    private Map<MusicTrack, String> trackMap;

    public CompilationAlbum(String name, String type) {
        super(name, type, null);
        trackMap = new HashMap<>();
    }

    public CompilationAlbum() {
        super();
        trackMap = new HashMap<>();
    }

    public void addTrackAndOriginalAlbum(MusicTrack musicTrack, String originalAlbumsName) {
        trackMap.put(musicTrack, originalAlbumsName);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        int index = 1;
        for (Map.Entry<MusicTrack, String> entry : trackMap.entrySet()) {
            MusicTrack musicTrack = entry.getKey();
            String originalAlbumsName = entry.getValue();

            result.append(index++)
                    .append(". MusicTrackName: '")
                    .append(musicTrack.getName())
                    .append("'\nOriginalAlbumsName: '")
                    .append(originalAlbumsName)
                    .append("'\n\n");
        }

        return result.toString();
    }
}
