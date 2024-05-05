package Models;

import DB.AlbumController;
import DB.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Song extends DBConnection {
    private int id;
    private String title;
    private int duration;
    private int album_id;

    public Song() {
        super();
    }

    public Song(String title, int duration, int album_id) {
        this.title = title;
        this.duration = duration;
        this.album_id = album_id;
    }

    public Song(int id, String title, int duration, int album_id) {
        super();
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.album_id = album_id;
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

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }
    @Override
    public String toString() {
        return title + " ," + duration + " min," + getTitleById(album_id);
    }

    public String getTitleById(int id){
        Album album;
        album = new Album();
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = getCon().prepareStatement("Select id, title, release_year from album where id = ?");
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()) {
                album.setId(rs.getInt("id"));
                album.setTitle(rs.getString("title"));
                album.setRelease_year(rs.getInt("release_year"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlbumController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return album.getTitle();
    }
}