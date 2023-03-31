/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import sample.dto.Account;
import sample.utils.DBUtils;

/**
 *
 * @author Duy
 */
public class AccountDao {
       public static Account getAccount(String email, String password) throws Exception {
        Connection cn = null;
        Account acc = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select accID,email,password,fullname,phone,status,role\n"
                        + "from dbo.Accounts\n"
                        + "where status = 1 and email = ? and password = ?";
                //thay the tai vi tri ?
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                //ResultSet de thuc thi cau query va tra ve tap gia tri cua cau 
                //query tren
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int AccID = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String Fullname = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int Status = rs.getInt("status");
                    int Role = rs.getInt("role");
                    acc = new Account(AccID, Email, Password, Fullname, Phone, Status, Role);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return acc;
    }

    public static ArrayList<Account> getListOfAccounts() {
        ArrayList<Account> listOfAccounts = new ArrayList<>();

        try {
            Connection connection = DBUtils.makeConnection();
            if (connection != null) {
                String sqlQuery = "select * from [dbo].[Accounts]";

                Statement st = connection.createStatement();
                ResultSet table = st.executeQuery(sqlQuery);

                if (table != null) {
                    while (table.next()) {
                        int accid = table.getInt("accID");
                        String email = table.getString("email");
                        String password = table.getString("password");
                        String fullname = table.getString("fullname");
                        String phone = table.getString("phone");
                        int status = table.getInt("status");
                        int role = table.getInt("role");

                        Account acc = new Account(accid, email, password, fullname, phone, status, role);
                        listOfAccounts.add(acc);
                    }
                }
                //b4:  close connection
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfAccounts;
    }

    public static void checkAdminOrUser(String email, String pass) throws Exception {

        ArrayList<Account> list = getListOfAccounts();
        if (list != null) {
            for (Account account : list) {
                if (account.getEmail().equals(email) && account.getPassword().equals(pass) && account.getRole() == 1) {
                    System.out.println("I am admin");
                    return;
                } else if (account.getEmail().equals(email) && account.getPassword().equals(pass) && account.getRole() == 0) {
                    System.out.println("I am user");
                    return;
                }
            }
        }
        System.out.println("login fail!");
    }

    public static boolean updateAccounts(String email, int status) {
        Connection connection = null;

        try {
            connection = DBUtils.makeConnection();
            if (connection != null) {
                String sqlSelectEmail = "select [email] from [dbo].[Accounts]\n"
                        + "where email = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectEmail);
                preparedStatement.setString(1, email);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs != null && rs.next()) {
                    String Email = rs.getString("email");
                    if (Email.equalsIgnoreCase(email)) {
                        String sqlQuery = "update dbo.Accounts \n"
                                + "set status = ?\n"
                                + "where email = ?";
                        preparedStatement = connection.prepareStatement(sqlQuery);
                        preparedStatement.setInt(1, status);
                        preparedStatement.setString(2, email);

                        preparedStatement.executeUpdate();
                        return true;
                    }
                }
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateAccount(String email, String newPassword, String newFullname, String newPhone) {

        Connection connection = null;
        try {
            connection = DBUtils.makeConnection();
            if (connection != null) {
                String sqlSelectEmail = "select [email] from [dbo].[Accounts]\n"
                        + "where email = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectEmail);
                preparedStatement.setString(1, email);
                ResultSet rs = preparedStatement.executeQuery();

                if (rs != null && rs.next()) {
                    String Email = rs.getString("email");
                    if (Email.equalsIgnoreCase(email)) {
                        String sqlQuery = "update [dbo].[Accounts] \n"
                                + "set [fullname] = ? ,[phone] = ?, [password] = ?\n"
                                + "where [email] = ?";

                        preparedStatement = connection.prepareStatement(sqlQuery);
                        preparedStatement.setString(1, newFullname);
                        preparedStatement.setString(2, newPhone);
                        preparedStatement.setString(3, newPassword);
                        preparedStatement.setString(4, email);

                        preparedStatement.executeUpdate();
                        return true;
                    }
                }
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean insertAccount(String newEmail, String newPassword, String newFullname, String newPhone, int newSatus, int newRole) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sqlInsert = "insert into Accounts (email, password, fullname, phone, role, status) \n"
                        + "VALUES (?,?,?,?,?,? ); ";
                PreparedStatement pst = cn.prepareStatement(sqlInsert);
                pst.setString(1, newEmail);
                pst.setString(2, newPassword);
                pst.setString(3, newFullname);
                pst.setString(4, newPhone);
                pst.setInt(5, newRole);
                pst.setInt(6, newSatus);

                pst.executeUpdate();
                cn.close();
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
     public static boolean updateToken(String email, String token) throws Exception {
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "update dbo.Accounts set email=?,token=? where email like ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2,token);
            pst.setString(3, email);
            pst.executeUpdate();
            cn.close();
            return true;
        }
        return false;
    }
     
      public static Account getAccountToken(String token) throws Exception {
        Account acc = null;//chua dap an
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select accID,email,password,fullname,phone,status,role\n"
                    + "from dbo.Accounts\n"
                    + "where token=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, token);
            ResultSet table = pst.executeQuery();
            if (table != null && table.next()) {
                String email=table.getString("email");
                int accid = table.getInt("accID");
                String Password = table.getString("password");
                String Fullname = table.getString("fullname");
                String Phone = table.getString("phone");
                int Status = table.getInt("status");
                int Role = table.getInt("role");
                acc = new Account(accid, email, Password, Fullname, Phone, Status, Role);
            }
            cn.close();
        }
        return acc;
    }
      
       public static ArrayList<Account> getAccountByName(String inputName) {
        ArrayList<Account> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select [accID],[fullname], [email], [password], [phone], [status], [role]  from [dbo].[Accounts] where [fullname] like ?";
                
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1,"%"+ inputName +"%");
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int accid = table.getInt("accID");
                        String email = table.getString("email");
                        String password = table.getString("password");
                        String fullname = table.getString("fullname");
                        String phone = table.getString("phone");
                        int status = table.getInt("status");
                        int role = table.getInt("role");

                        Account acc = new Account(accid, email, password, fullname, phone, status, role);
                        list.add(acc);
                    }
                }
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
       public static boolean updateAccount(String email, String newFullname,String newPhone) {
        Connection cn=null;
        try {
            cn=DBUtils.makeConnection();
            if(cn!=null) {
                String sql="update Accounts set fullname=?, phone=? where email=?";
                PreparedStatement pst=cn.prepareStatement(sql);
                pst.setString(1, newFullname);
                pst.setString(2, newPhone);
                pst.setString(3, email);
                pst.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn!=null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
       
       
}
