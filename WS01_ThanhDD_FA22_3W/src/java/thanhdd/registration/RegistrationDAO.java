/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhdd.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import thanhdd.db.DBUtil;

/**
 *
 * @author 15tha
 */
public class RegistrationDAO implements Serializable {

    public List<RegistrationDTO> search(String searchValue) throws SQLException{
        return this.findAll().stream().filter(t -> t.getLastName().contains(searchValue)).collect(Collectors.toList());
    }
    public List<RegistrationDTO> findAll() throws SQLException{
        RegistrationDTO registration = null;
        Connection conn = DBUtil.makeConnection();
        ResultSet rs = null;
        PreparedStatement stm = null;
        List<RegistrationDTO> list = new ArrayList<>();
        String query = "SELECT [username]\n"
                + "      ,[password]\n"
                + "      ,[lastName]\n"
                + "      ,[isAdmin]\n"
                + "  FROM [tblRegistration]";
        stm = conn.prepareStatement(query);
        rs = stm.executeQuery();
        while(rs.next()){
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
                + "  FROM [tblRegistration]"
                + " where [username] = ? and [password] = ?";
        stm = conn.prepareStatement(query);
        stm.setString(1, username);
        stm.setString(2, password);
        rs = stm.executeQuery();
        if(rs.next()){
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
}
