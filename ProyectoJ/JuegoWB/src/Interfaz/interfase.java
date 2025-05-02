package Interfaz;

import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.Scanner;

public class interfase extends JFrame implements ActionListener {
    private final JPasswordField psf;
    private final JTextField tx;
    private final JButton b1;
    private final JButton b2;
    private final JButton b3;
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;

    public interfase(){
        setTitle("LOGIN");
        setBounds(500,500,250,200);
        setLayout(new FlowLayout());
        l1 = new JLabel();
        l2 = new JLabel();
        l3 = new JLabel();
        psf = new JPasswordField(20);
        tx = new JTextField(20);
        b1 = new JButton("Mostrar contraseña");
        b2 = new JButton("Iniciar Sesion");
        b3 = new JButton("Crear Cuenta");
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        add(l3);
        add(tx);
        add(l1);
        add(psf);
        add(l2);
        System.out.println("\n");
        add(b2);
        add(b3);
        add(b1);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            if(psf.getEchoChar()==((char) 0)){
                psf.setEchoChar('•');
            }else {
                psf.setEchoChar((char) 0);
            }
        } else if (e.getSource() == b2) {
            //Metodo sin base de datos y con archivo con formato nombre_usuario:contraseña_usuario
            /*File archivo = new File("C:/Users/julia/OneDrive/Escritorio/ProyectoJ/JuegoWB/Basededatos.txt");
           try(BufferedReader br = new BufferedReader(new FileReader(archivo))){
               String linea;
               linea = br.readLine();
               while (linea != null) {
                   String[] uscon = linea.split(":");
                   if (uscon[0].equals(tx.getText()) && Objects.equals(uscon[1], new String(psf.getPassword()))) {
                       System.out.println("Correcto!");
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
           }catch(IOException ex){
               ex.printStackTrace();
           }*/
            l1.setText("");
            l2.setText("");
            try {
                l3.setText("");
                Connection conex = ConexionBD.getConexion();
                Statement stmt = conex.createStatement();
                String usuario = tx.getText();
                String password = new String(psf.getPassword());
                String sql = "SELECT * FROM USUARIOS WHERE Usuario = '"+usuario+"' AND Contraseña = '"+password+"'";
                ResultSet rs = stmt.executeQuery(sql);
                if(rs.next()){
                    System.out.println("Correcto!");
                }else{
                    System.out.println("Incorrecto!");
                    l3.setText("Usuario o contraseña Incorrecto");
                    l3.setForeground(Color.red);
                }
                conex.close();
                stmt.close();
                rs.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }else if(e.getSource() == b3){
            l1.setText("");
            l2.setText("");
            try {
                Connection conex = ConexionBD.getConexion();
                Statement stmt = conex.createStatement();
                String usuario = tx.getText();
                String password = new String(psf.getPassword());
                String sql = "SELECT * FROM USUARIOS WHERE Usuario = '"+usuario+"'";
                ResultSet rs = stmt.executeQuery(sql);
                if(rs.next()){
                    System.out.println("ERROR: YA EXISTE LA CUENTA");
                }else{
                    boolean tabien = true;
                    if(usuario.isEmpty()){
                        l1.setText("Introduzca un nombre valido");
                        l1.setForeground(Color.red);
                        tabien = false;
                    }
                    if(password.isEmpty()){
                        l2.setText("Introduzca una contraseña valida");
                        l2.setForeground(Color.red);
                        tabien = false;
                    }
                    if(tabien) {
                        sql = "INSERT INTO usuarios (usuario, contraseña) VALUES ('" + usuario + "', '" + password + "')";
                        stmt.execute(sql);
                    }
                }
                conex.close();
                stmt.close();
                rs.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    public static void main(String[] args){
        new interfase();
    }
}

