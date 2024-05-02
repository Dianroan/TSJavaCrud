package GUI;
import DB.AlbumController;
import Models.Album;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AlbumWindow extends JFrame {
    private JLabel titleLabel, yearLabel;
    private JTextField titleField, yearField;
    private JButton addButton, updateButton, deleteButton;
    private JList<Album> albumList;
    private DefaultListModel<Album> listModel;
    private ArrayList<Album> albums;
    private AlbumController albumController;

    public AlbumWindow() {
        super("Album CRUD");
        this.init();
    }

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AlbumWindow());
    }
*/
    public void init(){
        this.setTitle("Taylor Swift Crud");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.titleLabel = new JLabel("Title: ");
        this.yearLabel = new JLabel("Release Year: ");
        this.titleField = new JTextField(20);
        this.yearField = new JTextField(10);
        this.addButton = new JButton("Add");
        this.updateButton = new JButton("Update");
        this.deleteButton = new JButton("Delete");
        this.albumController = new AlbumController();
        this.albums = this.albumController.getAll();
        this.listModel = new DefaultListModel<>();
        this.albumList = new JList<>(listModel);
        this.albumList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.albumList.addListSelectionListener(e -> {
            int selectedIndex = this.albumList.getSelectedIndex();
            if (selectedIndex != -1) {
                Album selectedAlbum = this.listModel.getElementAt(selectedIndex);
                this.titleField.setText(selectedAlbum.getTitle());
                this.yearField.setText(String.valueOf(selectedAlbum.getRelease_year()));
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
                                .addComponent(yearLabel)
                        )
                        .addGroup(
                                layout.createParallelGroup()
                                .addComponent(titleField)
                                .addComponent(yearField)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(addButton)
                                        .addComponent(updateButton)
                                        .addComponent(deleteButton)
                                )
                        )
                )
                .addComponent(albumList)
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
                        .addComponent(yearLabel)
                        .addComponent(yearField)
                )
                .addGroup(
                        layout.createParallelGroup()
                        .addComponent(addButton)
                        .addComponent(updateButton)
                        .addComponent(deleteButton)
                )
                .addComponent(albumList)
        );

        this.addButton.addActionListener(e -> addAlbum());
        this.updateButton.addActionListener(e -> updateAlbum());
        this.deleteButton.addActionListener(e -> deleteAlbum());
        this.updateList();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addAlbum() {
        String title = titleField.getText();
        int year = Integer.parseInt(yearField.getText());
        Album album = new Album(title, year);
        albumController.add(album);
        updateList();
    }

    private void updateAlbum() {
        int selectedIndex = albumList.getSelectedIndex();
        if (selectedIndex != -1) {
            Album selectedAlbum = listModel.getElementAt(selectedIndex);
            String title = titleField.getText();
            int year = Integer.parseInt(yearField.getText());
            selectedAlbum.setTitle(title);
            selectedAlbum.setRelease_year(year);
            albumController.update(selectedAlbum);
            updateList();
        }
    }

    private void deleteAlbum() {
        int selectedIndex = albumList.getSelectedIndex();
        if (selectedIndex != -1) {
            Album selectedAlbum = listModel.getElementAt(selectedIndex);
            albumController.delete(selectedAlbum);
            updateList();
        }
    }

    private void updateList(){
        listModel.clear();
        this.albums = this.albumController.getAll();
        for (Album album: albums){
            listModel.addElement(album);
        }
    }
}
