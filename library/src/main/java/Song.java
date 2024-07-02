public class Song {

    private int id;
    private String songTitle;
    private String artistName;
    private String albumCover;

    public Song(int id, String songTitle, String artistName, String albumCover) {
        this.id = id;
        this.songTitle = songTitle;
        this.artistName = artistName;
        this.albumCover = albumCover;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumCover() {
        return albumCover;
    }

    public void setAlbumCover(String albumCover) {
        this.albumCover = albumCover;
    }

}
