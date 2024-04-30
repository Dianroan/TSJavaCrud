package GUI;

import javax.swing.*;
import java.awt.*;

public class CrudWindow extends JFrame {

    public JButton albumsButton;
    public JButton songsButton;
    public JTextArea pageTitle;
    public ImageIcon imageIcon;
    public GroupLayout gl;

    public CrudWindow() throws HeadlessException {
        super();
        this.init();
    }

    public void init(){
        this.setTitle("Taylor Swift Crud");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        albumsButton = new JButton("Albums");
        songsButton = new JButton("Songs");
        pageTitle = new JTextArea();
        gl = new GroupLayout(getContentPane());

        JButton a,b,c,d;
        a = new JButton("1");
        b = new JButton("2");
        c = new JButton("3");
        d = new JButton("4");


        // Añade una separación entre los botones
        gl.setAutoCreateContainerGaps(true); // Coloca un espacio entre los componentes y la ventana
        // gl.setAutoCreateGaps(true); // Coloca un espacio entre los componentes hacia la derecha y hacia abajo
        gl.setHorizontalGroup(
                gl.createParallelGroup()
                        .addComponent(a,100,100,100)
                        .addComponent(b,100,100,100)
                        .addGroup(
                                gl.createSequentialGroup()
                                        .addComponent(c)
                                        .addComponent(d)
                        )
        );
        gl.setVerticalGroup(
                gl.createSequentialGroup()
                        .addComponent(a,100,100,100)
                        .addComponent(b,100,100,100)
                        .addGroup(
                                gl.createParallelGroup()
                                        .addComponent(c)
                                        .addComponent(d)

                        )
        );
        setLayout(gl);
        pack(); // Ajusta el tamaño de la ventana al tamaño del paquete
    }
}
