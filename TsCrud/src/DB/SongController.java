package DB;

import Models.Album;
import Models.Song;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SongController extends DBConnection{
    private PreparedStatement ps;
    private ResultSet rs;
    public SongController() {
    }

    public void add(Song song) {
        try {
            ps = getCon().prepareStatement("INSERT INTO Song (album_id, title, duration) VALUES (?,?,?");
            ps.setInt(1, song.getAlbum().getId());
            ps.setString(2, song.getTitle());
            ps.setInt(3, song.getDuration());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SongController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Song> getAll() {
        ArrayList<Song> songs = new ArrayList<Song>();
        try {
            ps = getCon().prepareStatement("Select * from song");
            rs = ps.executeQuery();
            while (rs.next()) {
                Song song = new Song();
                song.setId(rs.getInt("id"));
                song.setId(rs.getInt("album_id"));
                song.setTitle(rs.getString("title"));
                song.setDuration(rs.getInt("duration"));
                songs.add(song);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SongController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return songs;
    }

    public void update(Song song) {
        try {
            ps = getCon().prepareStatement("UPDATE Song SET title = ?, duration = ? WHERE id = ?");
            ps.setString(1, song.getTitle());
            ps.setInt(2, song.getDuration());
            ps.setInt(3,song.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SongController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Song song) {
        try {
            ps = getCon().prepareStatement("DELETE FROM Song WHERE id = ?");
            ps.setInt(1,song.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SongController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
