package DB;

import Models.Album;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlbumController extends DBConnection{
    private PreparedStatement ps;
    private ResultSet rs;

    public AlbumController() {
    }

    public void add(Album album) {
        try {
            ps = getCon().prepareStatement("INSERT INTO Album (title, release_year) VALUES (?,?)");
            ps.setString(1, album.getTitle());
            ps.setInt(2, album.getRelease_year());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AlbumController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Album> getAll() {
        ArrayList<Album> albums = new ArrayList<Album>();
        try {
            ps = getCon().prepareStatement("Select * from album");
            rs = ps.executeQuery();
            while (rs.next()) {
                Album album = new Album();
                album.setId(rs.getInt("id"));
                album.setTitle(rs.getString("title"));
                album.setRelease_year(rs.getInt("release_year"));
                albums.add(album);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlbumController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return albums;
    }

    public void update(Album album) {
        try {
            ps = getCon().prepareStatement("UPDATE Album SET title = ?, release_year = ? WHERE id = ?");
            ps.setString(1, album.getTitle());
            ps.setInt(2, album.getRelease_year());
            ps.setInt(3,album.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AlbumController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void delete(Album album) {
        try {
            ps = getCon().prepareStatement("DELETE FROM Album WHERE id = ?");
            ps.setInt(1,album.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AlbumController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
