import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Menu extends JFrame implements ActionListener {
    static JFrame añadirframe;
    static JTextField nombretexto;
    static JTextField autortexto;
    static JTextField duraciontexto;
    static JLabel titulo;
    static JLabel autor;
    static JLabel año;
    static JPanel listaPosicion;
    static JPanel save;
    static DefaultListModel<Cancion> dlm;
    static JButton añadir;
    static JButton editar;
    static JButton guardar;
    static JButton cancelar;
    static JList<Cancion> lista;

    public static void main(String[] args) {
        new Menu();

    }

    public Menu() {

        setTitle("Coleccion Canciones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel marco = new JPanel(new FlowLayout(FlowLayout.CENTER));
        marco.add(añadir = new JButton("Añadir"));
        dlm = new DefaultListModel<Cancion>();
        añadir.addActionListener(this);
        lista = new JList<Cancion>(dlm);
        listaPosicion = new JPanel();
        listaPosicion.add(lista);
        listaPosicion.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(listaPosicion, BorderLayout.NORTH);
        add(marco, BorderLayout.CENTER);

        marco.add(editar = new JButton("Editar"));
        editar.addActionListener(this);
        setLocation(850, 450);
        setMinimumSize(new Dimension(400, 100));

        add(marco);

        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String botones = e.getActionCommand();
        if (botones.equals("Añadir")) {
            lista.clearSelection();
            añadir();
        }
        if (botones.equals("Editar")) {
            editar();
        }
        if (botones.equals("Guardar")) {
            guardar();
        }
        if (botones.equals("Cancelar")) {
            añadirframe.dispose();
            setVisible(true);
        }
    }

    public void añadir() {
        añadirframe = new JFrame();
        añadirframe.setTitle("Canciones");
        JPanel save = new JPanel(new GridLayout(4, 2, 5, 10));
        añadirframe.add(save);
        save.add(new JLabel("Titulo"));
        save.add(nombretexto = new JTextField(20));
        save.add(new JLabel("Autor"));
        save.add(autortexto = new JTextField(20));
        save.add(new JLabel("Duración"));
        save.add(duraciontexto = new JTextField(20));
        añadirframe.setMinimumSize(new Dimension(400, 100));
        añadirframe.setLocation(850, 450);

        save.add(guardar = new JButton("Guardar"));
        save.add(cancelar = new JButton("Cancelar"));
        cancelar.addActionListener(this);
        guardar.addActionListener(this);
        añadirframe.pack();
        añadirframe.setVisible(true);
        dispose();

    }

    public void guardar() {
            try {
                Connection miCon = lecturaDatos.conectar();
                PreparedStatement insertar = miCon.prepareStatement("INSERT INTO `cancion` (`nombre`,`autor`,`duracion`) values (?,?,?)");
                insertar.setString(1, String.valueOf(nombretexto.getText()));
                insertar.setString(2, String.valueOf(autortexto.getText()));
                insertar.setString(3, String.valueOf(duraciontexto.getText()));
                insertar.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        Cancion p = new Cancion(nombretexto.getText(), autortexto.getText(), duraciontexto.getText());
        if (nombretexto.getText().equals("") || autortexto.getText().equals("") || duraciontexto.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debes rellenar todos los apartados");
        } else {
            if (lista.isSelectionEmpty()) {
                dlm.addElement(p);
            } else {
                dlm.setElementAt(p, lista.getSelectedIndex());
            }
            añadirframe.dispose();
            pack();
            setVisible(true);

        }
        }

    public void editar() {
        if (lista.getSelectedIndex() >= 0) {
            añadir();
            Cancion p = (Cancion)lista.getSelectedValue();
            nombretexto.setText(p.getNombre());
            autortexto.setText(p.getAutor());
            duraciontexto.setText(p.getDuracion());
        } else {
            JOptionPane.showMessageDialog(null, "Hay que elegir un elemento de la lista");
        }
    }

}