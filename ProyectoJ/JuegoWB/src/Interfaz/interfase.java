package Interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.EventListener;
import java.util.Objects;

public class interfase extends JFrame implements ActionListener {
    private JPasswordField psf;
    private JTextField tx;
    private JButton b1;
    private JButton b2;

    public interfase(){
        setTitle("LOGIN");
        setBounds(500,500,400,400);
        setLayout(new FlowLayout());
        psf = new JPasswordField(20);
        tx = new JTextField(20);
        b1 = new JButton("Mostrar contraseña");
        b2 = new JButton("Iniciar Sesion");
        b1.addActionListener(this);
        b2.addActionListener(this);
        add(tx);
        add(psf);
        System.out.println("\n");
        add(b2);
        add(b1);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean iniciada = false;
        if (e.getSource() == b1) {
            psf.setEchoChar((char) 0);
        } else if (e.getSource() == b2) {
            File archivo = new File("C:/Users/julia/OneDrive/Escritorio/ProyectoJ/JuegoWB/Basededatos.txt");
            FileReader fr = null;
            try {
                fr = new FileReader(archivo);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            BufferedReader br = new BufferedReader(fr);
            String linea;
            try {
                linea = br.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            while (linea != null) {
                String[] uscon = linea.split(":");
                if (uscon[0].equals(tx.getText()) && Objects.equals(uscon[1], new String(psf.getPassword()))) {
                    System.out.println("Correcto!");
                    iniciada = true;
                    break;
                } else {
                    System.out.println("Incorrecto!");
                }
                try {
                    linea = br.readLine();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if(iniciada){

            }
        }
    }
    public static void main(String[] args){
        new interfase();
    }



}

