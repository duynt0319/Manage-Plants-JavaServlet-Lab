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
import sample.utils.DBUtils;

/**
 *
 * @author Triệu
 */
public class CategoryDAO {
    public static ArrayList<Category> getCategories() {
        Connection cn=null;
        Category cate=null;
        ArrayList<Category> list=new ArrayList<>();
        try {
            cn=DBUtils.makeConnection();
            if (cn!=null) {
                String sql = "select CateID, CateName\n"
                        + "from dbo.Categories";
                PreparedStatement pst=cn.prepareStatement(sql);
                ResultSet rs=pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int CateID=rs.getInt("CateID");
                        String CateName=rs.getString("CateName");
                        cate=new Category(CateID, CateName);
                        list.add(cate);
                    }
                }
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
        return list;
    }
    public static ArrayList<Category> getCategories(String name) {
        Connection cn=null;
        Category cate=null;
        ArrayList<Category> list=new ArrayList<>();
        try {
            cn=DBUtils.makeConnection();
            if (cn!=null) {
                String sql = "select CateID, CateName\n"
                        + "from dbo.Categories\n"
                        + "where CateName like ?";
                PreparedStatement pst=cn.prepareStatement(sql);
                pst.setString(1, "%"+ name +"%");
                ResultSet rs=pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int CateID=rs.getInt("CateID");
                        String CateName=rs.getString("CateName");
                        cate=new Category(CateID, CateName);
                        list.add(cate);
                    }
                }
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
        return list;
    }
    
    public static boolean createNewCate(String cateName) {
        Connection cn = null;
        try {
            cn=DBUtils.makeConnection();
            if(cn!=null) {
                String sql = "insert into [dbo].[Categories]([CateName])\n"
                        + "values (?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                
                
                pst.setString(1, cateName);
                
                
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
    
    
    public static boolean updatePlant(int cateId, String cateName) {
        Connection cn=null;
        try {
            cn=DBUtils.makeConnection();
            if(cn!=null) {
                String sql="update [dbo].[Categories] set [CateName]=? where [CateID]=?";
                PreparedStatement pst=cn.prepareStatement(sql);
                                
                pst.setInt(2, cateId);
                pst.setString(1, cateName);
                
                
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
