/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dto;

/**
 *
 * @author Duy
 */
public class Account {
    public int accID;
    private String email;
    private String password;
    public String fullname;
    private String phone;
    private int status;
    private int role;

    public Account() {
    }

    public Account(int accID) {
        this.accID = accID;
    }

    public Account(int accID, String fullname) {
        this.accID = accID;
        this.fullname = fullname;
    }
    
    
    
    public Account(int accID, String email, String password, String fullname, String phone, int status, int role) {
        this.accID = accID;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.phone = phone;
        this.status = status;
        this.role = role;
    }

    public int getAccID() {
        return accID;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPhone() {
        return phone;
    }

    public int getStatus() {
        return status;
    }

    public int getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "Account{" + "accID=" + accID + ", email=" + email + ", password=" + password + ", fullname=" + fullname + ", phone=" + phone + ", status=" + status + ", role=" + role + '}';
    }
}
