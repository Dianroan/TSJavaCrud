package GUI;

import DB.AlbumController;
import DB.SongController;
import Models.Album;
import Models.Song;

import javax.swing.*;
import java.util.ArrayList;

public class SongWindow extends JFrame{
    private JLabel titleLabel, albumLabel, durationLabel;
    private JComboBox<String> albumComboBox;
    private JTextField titleField, durationField;
    private JButton addButton, updateButton, deleteButton, getBackButton;
    private JList<Song> songList;
    private DefaultListModel<Song> listModel;
    private ArrayList<Song> songs;
    private SongController songController;
    private AlbumController albumController;

    public SongWindow() {
        super("Song CRUD");
        this.init();
    }

    private void init() {
        this.setTitle("Taylor Swift Crud");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.albumComboBox = new JComboBox<>();

        this.titleLabel = new JLabel("Title: ");
        this.durationLabel = new JLabel("Duration:");
        this.albumLabel = new JLabel("Album:");

        this.titleField = new JTextField(20);
        this.durationField = new JTextField(10);

        this.addButton = new JButton("Add");
        this.updateButton = new JButton("Update");
        this.deleteButton = new JButton("Delete");
        this.getBackButton = new JButton("Back");

        this.albumController = new AlbumController();
        this.songController = new SongController();

        this.songs = this.songController.getAll();
        this.listModel = new DefaultListModel<>();
        this.songList = new JList<>(listModel);
        this.songList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.loadAlbumsFromDatabase();
        this.songList.addListSelectionListener(e -> {
            int selectedIndex = this.songList.getSelectedIndex();
            if (selectedIndex != -1) {
                Song selectedSong = this.listModel.getElementAt(selectedIndex);
                this.titleField.setText(selectedSong.getTitle());
                this.durationField.setText(String.valueOf(selectedSong.getDuration()));
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);


        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(
                                        layout.createParallelGroup()
                                                .addComponent(titleLabel)
                                                .addComponent(albumLabel)
                                                .addComponent(durationLabel)
                                )
                                .addGroup(
                                        layout.createParallelGroup()
                                                .addComponent(titleField)
                                                .addComponent(albumComboBox)
                                                .addComponent(durationField)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(addButton)
                                                        .addComponent(updateButton)
                                                        .addComponent(deleteButton)
                                                )
                                )
                        )
                        .addComponent(songList)
                        .addComponent(getBackButton)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(
                                layout.createParallelGroup()
                                        .addComponent(titleLabel)
                                        .addComponent(titleField)
                        )
                        .addGroup(
                                layout.createParallelGroup()
                                .addComponent(albumLabel)
                                .addComponent(albumComboBox))
                        .addGroup(
                                layout.createParallelGroup()
                                        .addComponent(durationLabel)
                                        .addComponent(durationField)
                        )
                        .addGroup(
                                layout.createParallelGroup()
                                        .addComponent(addButton)
                                        .addComponent(updateButton)
                                        .addComponent(deleteButton)
                        )
                        .addComponent(songList)
                        .addComponent(getBackButton)
        );

        this.addButton.addActionListener(e -> addSong());
        this.updateButton.addActionListener(e -> updateSong());
        this.deleteButton.addActionListener(e -> deleteSong());
        this.getBackButton.addActionListener(e -> getBack());

        this.updateList();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void getBack() {
        new CrudWindow();
        dispose();
    }

    private void addSong() {
        String albumTitle = albumComboBox.getSelectedItem().toString();
        int album_id = albumController.getIdByTitle(albumTitle);
        String title = titleField.getText();
        int duration = Integer.parseInt(durationField.getText());
        Song song = new Song(title,duration,album_id);
        songController.add(song);
        updateList();
        loadAlbumsFromDatabase();
    }

    private void updateSong() {
        int selectedIndex = songList.getSelectedIndex();
        if (selectedIndex != -1) {
            Song selectedSong = listModel.getElementAt(selectedIndex);
            String title = titleField.getText();
            int duration = Integer.parseInt(durationField.getText());
            String albumTitle = albumComboBox.getSelectedItem().toString();
            int album_id = albumController.getIdByTitle(albumTitle);
            selectedSong.setTitle(title);
            selectedSong.setDuration(duration);
            selectedSong.setAlbum_id(album_id);
            songController.update(selectedSong);
            updateList();
            loadAlbumsFromDatabase();
        }
    }

    private void deleteSong() {
        int selectedIndex = songList.getSelectedIndex();
        if (selectedIndex != -1) {
            Song selectedSong = listModel.getElementAt(selectedIndex);
            songController.delete(selectedSong);
            updateList();
            loadAlbumsFromDatabase();
        }
    }

    private void updateList() {
        listModel.clear();
        this.songs = this.songController.getAll();
        for (Song song: songs){
            listModel.addElement(song);
        }
    }

    private void loadAlbumsFromDatabase() {
        albumComboBox.removeAllItems();
        for (Album album: this.albumController.getAll()){
            albumComboBox.addItem(album.getTitle());
        }
    }
}