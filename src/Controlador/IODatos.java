/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.sql.*;

/**
 *
 * @author marco
 */
public class IODatos {
    
    private static final String Ruta = "jdbc:mysql://34.229.80.204:3306/marcos";
    private static final String Usu = "marcos";
    private static final String Pass = "marcos";

    public static void añadirUsu(Usuario u) {
        
        try (Connection con = DriverManager.getConnection(Ruta,Usu,Pass)){
            
            String insert="insert into user values(?,?);";
            
            PreparedStatement pt = con.prepareStatement(insert);
            
            
            pt.setString(1,u.getNombre());
            pt.setString(2, u.getContrasena());
            
            pt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(IODatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Usuario añadido");
        
    }
    
    
    
    public static javax.swing.table.DefaultTableModel cargarTablaUsuarios(){
        DefaultTableModel mTabla= new DefaultTableModel();
        
        int cont=0;
        
        try (Connection con = DriverManager.getConnection(Ruta, Usu, Pass);){
            String select = "select * from user";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(select);
            
            mTabla.addColumn("Nombre");
            mTabla.addColumn("Contraseña");
            
            rs = st.executeQuery(select);
            
            while (rs.next()) {
                Object[] fila = {rs.getString(1), rs.getString(2)};
                mTabla.addRow(fila);
               
                
            }
            

            } catch (SQLException ex) {
            Logger.getLogger(IODatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        return mTabla;
    }

    public static void eliminarUsuario(String nombre) {
        
        try (Connection con = DriverManager.getConnection(Ruta,Usu,Pass)){
            
            String insert="Delete from user where username='"+nombre+"'";
            
            PreparedStatement pt = con.prepareStatement(insert);
            
            pt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(IODatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Usuario Eliminado");
        
    }

    public static void modUSuario(String usu, String pass ) {
        
        try (Connection con = DriverManager.getConnection(Ruta,Usu,Pass)){
            
            String insert="update user set password='"+pass+"' where username='"+usu+"';";
            
            PreparedStatement pt = con.prepareStatement(insert);
            
            pt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(IODatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Usuario modificado");
    }
    
}
