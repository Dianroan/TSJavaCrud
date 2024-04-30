package Models;

public class Album {
    private int id;
    private String title;
    private int release_year;

    public Album() {
    }

    public Album(String title, int release_year) {
        this.title = title;
        this.release_year = release_year;
    }

    public Album(int id, String title, int release_year) {
        this.id = id;
        this.title = title;
        this.release_year = release_year;
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

    public int getRelease_year() {
        return release_year;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }
}