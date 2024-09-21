/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    public Connection conexionBD;
    public final String bd = "db_escuela";
    public final String url_conexion = String.format("jdbc:mysql://localhost:3306/%s", bd);
    public final String usuario = "root";
    public final String contra = "1234";
    public final String jdbc = "com.mysql.cj.jdbc.Driver";
    
    public void abrir_conexion(){
        try{
            Class.forName(jdbc);
            conexionBD = DriverManager.getConnection(url_conexion, usuario, contra);
            //JOptionPane.showMessageDialog(null, "Se ha conectado correctamente", "Exito en la conexion", JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | ClassNotFoundException | SQLException ex){
            System.out.println("Error....." + ex.getMessage());
        }
    }
    
    public void cerrar_conexion(){
        try{
            conexionBD.close();
        }catch(SQLException ex){
            System.out.println("Error...." + ex.getMessage());
            
        }
    }
}
