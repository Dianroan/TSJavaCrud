package Models;

public class Song {
    private int id;
    private String title;
    private int duration;
    private Album album;

    public Song() {
    }

    public Song(int id, String title, int duration, Album album) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.album = album;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}