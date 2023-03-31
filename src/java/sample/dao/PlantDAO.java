/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import sample.dto.Category;
import sample.dto.Plant;
import sample.utils.DBUtils;

/**
 *
 * @author Duy
 */
public class PlantDAO {
   

    public static ArrayList<Plant> getPlants (String keyword, String searchby) {
        ArrayList<Plant> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null && searchby != null) {
                String sql = "select PID,PName,price,imgPath,description,status,Plants.CateID as 'CateID',CateName\n"
                        + "from Plants join Categories on Plants.CateID=Categories.CateID\n";
                if (searchby.equalsIgnoreCase("byname")) {
                    sql = sql + "where Plants.PName like ?";
                } else sql = sql + "where CateName like ?";
                
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + keyword + "%");
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("PID");
                        String name = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgpath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateid = rs.getInt("CateID");
                        String catename = rs.getString("CateName");
                        Plant plant = new Plant(id, name, price, imgpath, description, status, cateid, catename);
                        list.add(plant);
                    }
                }
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static Plant getPlant(int pid) {
        Plant p = null;
        Connection conn = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "SELECT PID, PName, price, imgPath, description, status, Plants.CateID as 'CateID', CateName "
                        + "FROM Plants, Categories "
                        + "WHERE Plants.CateID = Categories.CateID and PID=? ";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, pid);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    pid = rs.getInt("PID");
                    String name = rs.getString("PName");
                    int price = rs.getInt("price");
                    String imgpath = rs.getString("imgPath");
                    String description = rs.getString("description");
                    int status = rs.getInt("status");
                    int cateid = rs.getInt("CateID");
                    String catename = rs.getString("CateName");
                    p = new Plant(pid, name, price, imgpath, description, status, cateid, catename);                
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return p;
    }
    
    public static ArrayList<Plant> printAllPlants() {
        ArrayList<Plant> list = new ArrayList<>();
        Connection cn = null;

        try {
            cn = DBUtils.makeConnection();

            String sql = "select PID, PName, price,imgPath, description, status, Plants.CateID as 'CateID', CateName \n"
                    + "from Plants join Categories on Plants.CateID=Categories.CateID";

            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("PID");
                        String name = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgpath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateid = rs.getInt("CateID");
                        String catename = rs.getString("CateName");
                        Plant plant = new Plant(id, name, price, imgpath, description, status, cateid, catename);
                        list.add(plant);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
     public static ArrayList<Plant> getPlants(String name) {
        ArrayList<Plant> list=new ArrayList<>();
        Connection cn=null;
        try {
            cn=DBUtils.makeConnection();
            if (cn!=null) {
                String sql = "select PID,PName,price,imgPath,description,status,Plants.CateID as 'CateID',CateName\n"
                        + "from Plants join Categories on Plants.CateID=Categories.CateID\n"
                        + "where PName like ?";
                PreparedStatement pst=cn.prepareStatement(sql);
                pst.setString(1, "%"+ name +"%");
                ResultSet rs=pst.executeQuery();
                if (rs!=null) {
                    while (rs.next()) {                        
                        int id=rs.getInt("PID");
                        name=rs.getString("PName");
                        int price=rs.getInt("price");
                        String imgpath=rs.getString("imgPath");
                        String description=rs.getString("description");
                        int status=rs.getInt("status");
                        int cateid=rs.getInt("CateID");
                        String catename=rs.getString("CateName");
                        Plant plant=new Plant(id, name, price, imgpath, description, status, cateid, catename);
                        list.add(plant);
                    }
                }
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
     
     public static boolean changePlantStatus(String name, int status) {
        Plant p=null;
        Connection cn=null;
        try {
            cn=DBUtils.makeConnection();
            if (cn!=null) {
                String sql="update dbo.Plants set status=? where PName=?";
                PreparedStatement pst=cn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setString(2, name);
                pst.executeUpdate();
                pst.close();
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
    
     
    public static boolean createNewPlant(String Pname, int price, String imgPath, String description, int status, int CateID) {
        Connection cn = null;
        try {
            cn=DBUtils.makeConnection();
            if(cn!=null) {
                String sql = "insert into Plants(PName, Price, imgPath, description, status, CateID)\n"
                        + "values (?,?,?,?,?,?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                
                pst.setString(1, Pname );
                pst.setInt(2, price);
                pst.setString(3, imgPath );
                pst.setString(4, description );
                pst.setInt(5, status);
                pst.setInt(6, CateID);
                
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
     public static boolean updatePlant(String PName, int price,String img, String desc, int status, int cateId, int pid) {
        Connection cn=null;
        try {
            cn=DBUtils.makeConnection();
            if(cn!=null) {
                String sql="update [dbo].[Plants] set [PName]=?, [price]=?, [imgPath]=?, [description]=?, [status]=?, [CateID]=? where [PID]=?";
                PreparedStatement pst=cn.prepareStatement(sql);
                
                pst.setString(1, PName);
                pst.setInt(2, price);
                pst.setString(3, img);
                pst.setString(4, desc);
                pst.setInt(5, status);
                pst.setInt(6, cateId);
                pst.setInt(7, pid);
                
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
