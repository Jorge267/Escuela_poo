/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel; 

public class Docente extends Persona{
    
    private String codigo_docente, fecha_ingreso_laborar, fecha_ingreso_registro;
    private double salario;
    private int id;
    Conexion cn;

    public Docente(String codigo_docente, String fecha_ingreso_laborar, String fecha_ingreso_registro, double salario, int id, String nit, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        super(nit, nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.codigo_docente = codigo_docente;
        this.fecha_ingreso_laborar = fecha_ingreso_laborar;
        this.fecha_ingreso_registro = fecha_ingreso_registro;
        this.salario = salario;
        this.id = id;
    }
    

    public Docente(){}
    public Docente(String codigo_docente, String fecha_ingreso_laborar, String fecha_ingreso_registro, double salario, String nit, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        super(nit, nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.codigo_docente = codigo_docente;
        this.fecha_ingreso_laborar = fecha_ingreso_laborar;
        this.fecha_ingreso_registro = fecha_ingreso_registro;
        this.salario = salario;
    }

    public String getCodigo_docente() {
        return codigo_docente;
    }

    public void setCodigo_docente(String codigo_docente) {
        this.codigo_docente = codigo_docente;
    }

    public String getFecha_ingreso_laborar() {
        return fecha_ingreso_laborar;
    }

    public void setFecha_ingreso_laborar(String fecha_ingreso_laborar) {
        this.fecha_ingreso_laborar = fecha_ingreso_laborar;
    }

    public String getFecha_ingreso_registro() {
        return fecha_ingreso_registro;
    }

    public void setFecha_ingreso_registro(String fecha_ingreso_registro) {
        this.fecha_ingreso_registro = fecha_ingreso_registro;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel(); 
        
        try{
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "SELECT id_docente as id, nit, nombres, apellidos, direccion, telefono, fecha_nacimiento, codigo_docente, salario, fecha_ingreso_laborar, fecha_ingreso_registro FROM docente;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            
            String encabezado[] = {"ID", "Nit", "Nombres", "Apellidos", "Direccion", "Telefono", "Fecha Nacimiento", "COdigo docente", "Salario", "Ingreso laborar", "Fecha registro"};
            tabla.setColumnIdentifiers(encabezado);
            
            String datos[] = new String[11];
            
            while(consulta.next()){
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("nit");
                datos[2] = consulta.getString("nombres");
                datos[3] = consulta.getString("apellidos");
                datos[4] = consulta.getString("direccion");
                datos[5] = consulta.getString("telefono");
                datos[6] = consulta.getString("fecha_nacimiento");
                datos[7] = consulta.getString("codigo_docente");
                datos[8] = consulta.getString("salario");
                datos[9] = consulta.getString("fecha_ingreso_laborar");
                datos[10] = consulta.getString("fecha_ingreso_registro");
                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
            
        }catch(Exception ex){
            System.out.println("Error al mostrar la tabla..." + ex.getMessage());
        }
        
        return tabla;
    }
    
    @Override
    public void agregar(){
        try{
            PreparedStatement parametro;
            String query = "INSERT INTO docente (nit, nombres, apellidos, direccion, telefono, fecha_nacimiento, codigo_docente, salario, fecha_ingreso_laborar, fecha_ingreso_registro) VALUES (?,?,?,?,?,?,?,?,?,?);";
            cn = new Conexion();
            cn.abrir_conexion();
            
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, this.getNit());
            parametro.setString(2, this.getNombres());
            parametro.setString(3, this.getApellidos());
            parametro.setString(4, this.getDireccion());
            parametro.setString(5, this.getTelefono());
            parametro.setString(6, this.getFecha_nacimiento());
            parametro.setString(7, this.getCodigo_docente());
            parametro.setDouble(8, this.getSalario());
            parametro.setString(9, this.getFecha_ingreso_laborar());
            parametro.setString(10, this.getFecha_ingreso_registro());
            
            int ejecutar = parametro.executeUpdate();
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null, Integer.toString(ejecutar) + " Registros agregados", "Agragar", JOptionPane.INFORMATION_MESSAGE);
            
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error..." + ex.getMessage());
        }
    }

    @Override
    public void actualizar(){
        try{
            PreparedStatement parametro;
            String query = "UPDATE docente SET nit = ?, nombres = ?, apellidos = ?, direccion = ?, telefono = ?, fecha_nacimiento = ?, codigo_docente = ?, salario = ?, fecha_ingreso_laborar = ?, fecha_ingreso_registro = ?" + 
                    "WHERE id_docente = ?"; 
            cn = new Conexion();
            cn.abrir_conexion();
            
             
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, this.getNit());
            parametro.setString(2, this.getNombres());
            parametro.setString(3, this.getApellidos());
            parametro.setString(4, this.getDireccion());
            parametro.setString(5, this.getTelefono());
            parametro.setString(6, this.getFecha_nacimiento());
            parametro.setString(7, this.getCodigo_docente());
            parametro.setDouble(8, this.getSalario());
            parametro.setString(9, this.getFecha_ingreso_laborar());
            parametro.setString(10, this.getFecha_ingreso_registro());
            parametro.setInt(11, this.getId());
            
            int ejecutar = parametro.executeUpdate();
            cn.cerrar_conexion();   
            JOptionPane.showMessageDialog(null, Integer.toString(ejecutar) + " Registro actualizado", "Actualizar", JOptionPane.INFORMATION_MESSAGE);

            
        }catch(Exception ex){
            System.out.println("Error" + ex.getMessage());
        }
    }
    
    
    @Override
    public void eliminar(){
        try{
            PreparedStatement parametro;
            String query = "DELETE FROM docente WHERE id_docente = ?;"; 
            cn = new Conexion();
            cn.abrir_conexion();
            
             
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, this.getId());

            int ejecutar = parametro.executeUpdate();
            cn.cerrar_conexion();   
            JOptionPane.showMessageDialog(null, Integer.toString(ejecutar) + " Registro Eliminado", "Eliminar", JOptionPane.INFORMATION_MESSAGE);

            
        }catch(Exception ex){
            System.out.println("Error" + ex.getMessage());
        }
    }


}
