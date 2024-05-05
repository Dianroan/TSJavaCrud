package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrudWindow extends JFrame {

    public JButton albumButton;
    public JButton songButton;

    public CrudWindow() throws HeadlessException {
        super("TS Crud");
        this.init();
    }

    public void init(){
        JLabel label = new JLabel("Taylor Swift Crud");
        label.setFont(new Font("Arial", Font.BOLD, 24));

        albumButton = new JButton("Albums");
        albumButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAlbumWindow();
            }
        });

        songButton = new JButton("Songs");
        songButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openSongWindow();
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(label)
                        .addComponent(albumButton)
                        .addComponent(songButton)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(label)
                        .addGap(20)
                        .addComponent(albumButton)
                        .addGap(10)
                        .addComponent(songButton)
        );

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void openAlbumWindow() {
        new AlbumWindow();
        dispose();
    }

    private void openSongWindow() {
        new SongWindow();
        dispose();
    }
}
