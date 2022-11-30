/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhdd.registration;

public class RegistrationDTO {

    private String username;
    private String password;
    private String lastName;
    private boolean role;

    public RegistrationDTO(String username, String password, String lastName, boolean role) {
        this.username = username;
        this.password = password;
        this.lastName = lastName;
        this.role = role;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    
    

    public String toJson() {
        return "{"
                + "\"username\": \"" + username + "\", "
                + "\"password\": \"" + password + "\", "
                + "\"lastName\": \"" + lastName + "\", "
                + "\"role\": \"" + role + "\""
                + "}";
    }

    public RegistrationDTO() {
    }

    public static void main(String[] args) {
        System.out.println(new RegistrationDTO().toJson());
        System.out.println(new RegistrationDTO().isRole() ? "checked" : "11");
    }
}
