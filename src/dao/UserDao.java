/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Objecto de acceso a datos de usuarios.
 * 
 * @author Mauricio
 */
public class UserDao {

    public UserDao() {

    }
    
    /**
     * Crea un nuevo usuario.
     * @param username
     * @param password 
     */
    public void create(String username, String password) {
        try {
            Connection conn = Connector.connect();

            String sql = "INSERT INTO users(username, password) VALUES (?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            //pstmt.close();
            //conn.close();
        } catch (SQLException ex) {
        	Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Busca un usuario por nombre de usuario.
     * @param username
     * @return el nombre de usuario en caso de estar registrado.
     */
    public String getUsername(String username) {
        try {
            Connection conn = Connector.connect();

            String sql = "SELECT username FROM users WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            
            String aux = rs.getString("username");
            rs.close();
            pstmt.close();
            conn.close();
            return aux;

        } catch (SQLException ex) {
            //Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    /**
     * Retorna la contraseña de un usuario.
     * @param username
     * @return contraseña del usuario en caso de que exista.
     */
    public String getPassword(String username) {
        try {
            Connection conn = Connector.connect();

            String sql = "SELECT username, password FROM users WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            String aux = rs.getString("password");
            rs.close();
            pstmt.close();
            conn.close();

            return aux;

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
