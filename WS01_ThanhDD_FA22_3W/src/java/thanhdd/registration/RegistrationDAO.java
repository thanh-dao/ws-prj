/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhdd.registration;

import com.google.gson.Gson;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import thanhdd.controller.DeleteController;
import thanhdd.db.DBUtil;

/**
 *
 * @author 15tha
 */
public class RegistrationDAO implements Serializable {

    public List<RegistrationDTO> search(String searchValue) throws SQLException {
        return this.findAll().stream().filter(t -> t.getLastName().toLowerCase().contains(searchValue.toLowerCase())).collect(Collectors.toList());
    }

    public boolean delete(String username) throws SQLException{
        String query = "DELETE FROM [Registration] "
                + "where [username] = ?";
        Connection conn = DBUtil.makeConnection();
        PreparedStatement stm = null;
        stm = conn.prepareStatement(query);
        stm.setString(1, username);
        int result = stm.executeUpdate();
        stm.close();
        conn.close();
        return result == 1;
    }
    public boolean update(RegistrationDTO regist) throws SQLException {
        String query = "update [Registration]"
                + "set [password] = ?, [lastName] = ?, [isAdmin] = ? "
                + "where [username] = ? ";
        Connection conn = DBUtil.makeConnection();
        PreparedStatement stm = null;
        stm = conn.prepareStatement(query);
        stm.setString(1, regist.getPassword());
        stm.setString(2, regist.getLastName());
        stm.setString(3, regist.isRole() ? "1" : "0");
        stm.setString(4, regist.getUsername());
        int result = stm.executeUpdate();
        stm.close();
        conn.close();
        return result == 1;
    }

    public List<RegistrationDTO> searchByUserName(String username) throws SQLException {
        return this.findAll().stream().filter(t -> t.getUsername().equalsIgnoreCase(username)).collect(Collectors.toList());
    }

    public List<RegistrationDTO> findAll() throws SQLException {
        RegistrationDTO registration = null;
        Connection conn = DBUtil.makeConnection();
        ResultSet rs = null;
        PreparedStatement stm = null;
        List<RegistrationDTO> list = new ArrayList<>();
        String query = "SELECT [username]\n"
                + "      ,[password]\n"
                + "      ,[lastName]\n"
                + "      ,[isAdmin]\n"
                + "  FROM [Registration]";
        stm = conn.prepareStatement(query);
        rs = stm.executeQuery();
        while (rs.next()) {
            registration = new RegistrationDTO();
            registration.setUsername(rs.getString("username"));
            registration.setPassword(rs.getString("password"));
            registration.setLastName(rs.getString("lastname"));
            registration.setRole(rs.getInt("isAdmin") == 1);
            list.add(registration);
        }
        return list;
    }

    public RegistrationDTO checkLogin(String username, String password) throws SQLException {
        RegistrationDTO registration = null;
        Connection conn = DBUtil.makeConnection();
        ResultSet rs = null;
        PreparedStatement stm = null;
        String query = "SELECT [username]\n"
                + "      ,[password]\n"
                + "      ,[lastName]\n"
                + "      ,[isAdmin]\n"
                + "  FROM [Registration]"
                + " where [username] = ? and [password] = ?";
        stm = conn.prepareStatement(query);
        stm.setString(1, username);
        stm.setString(2, password);
        rs = stm.executeQuery();
        if (rs.next()) {
            registration = new RegistrationDTO();
            registration.setUsername(rs.getString("username"));
            registration.setPassword(rs.getString("password"));
            registration.setLastName(rs.getString("lastname"));
            registration.setRole(rs.getInt("isAdmin") == 1);
        }
        rs.close();
        stm.close();
        conn.close();
        return registration;
    }

    public static void main(String[] args) {
        try {
            boolean isNotError = new RegistrationDAO().delete("khanh");
            if(!isNotError){
                throw new Exception("Error at DeleteController line 41");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeleteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
