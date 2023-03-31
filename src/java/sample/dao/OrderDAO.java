/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import sample.dto.Order;
import sample.dto.OrderDetail;
import sample.utils.DBUtils;

/**
 *
 * @author Duy
 */
public class OrderDAO {

    public static ArrayList<Order> getListOfOrder() {
        ArrayList<Order> listOfOrder = new ArrayList<>();

        try {
            Connection connection = DBUtils.makeConnection();
            if (connection != null) {
                String sqlQuery = "select * from [dbo].[Orders]";

                Statement st = connection.createStatement();
                ResultSet table = st.executeQuery(sqlQuery);

                if (table != null) {
                    while (table.next()) {
                        int OrderID = table.getInt("OrderID");
                        String orderDate = table.getString("OrdDate");
                        String shipDate = table.getString("shipdate");
                        int status = table.getInt("status");
                        int accID = table.getInt("accID");

                        Order order = new Order(OrderID, orderDate, shipDate, status, accID);
                        listOfOrder.add(order);
                    }
                }
                //b4:  close connection
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfOrder;
    }

    public static ArrayList<Order> getOrders(String email) {
        ArrayList<Order> list = new ArrayList<>();
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select OrderID,OrdDate,shipdate,a.status,a.AccID from Orders a, Accounts b \n"
                        + "where a.AccID=b.accID and email like ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int orderID = table.getInt("OrderID");
                        String orderDate = table.getString("OrdDate");
                        String shipDate = table.getString("shipdate");
                        int status = table.getInt("status");
                        int accID = table.getInt("AccID");
                        Order order = new Order(orderID, orderDate, shipDate, status, accID);
                        list.add(order);
                    }
                }
                cn.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<OrderDetail> getOrdersDetail(int orderID) {
        ArrayList<OrderDetail> list = new ArrayList<>();
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select DetailId,OrderID,PID,PName,price,imgPath,quantity \n"
                        + "from  OrderDetails a, Plants b \n"
                        + "where a.FID=b.PID and OrderID= ? ";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, orderID);
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int DetailId = table.getInt("DetailId");
                        int plantID = table.getInt("PID");
                        String PlanName = table.getString("PName");
                        int price = table.getInt("price");
                        String imgPath = table.getString("imgPath");
                        int quantity = table.getInt("quantity");

                        OrderDetail orderDetail = new OrderDetail(DetailId, orderID, plantID, PlanName, price, imgPath, quantity);
                        list.add(orderDetail);
                    }
                }
                cn.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean insertOrder(String email, HashMap<String, Integer> cart) {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                int accid = 0, orderid = 0;
                cn.setAutoCommit(false); // off auto commit
                //get account by id email
                String sql = "select accID from Accounts where Accounts.email = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    accid = rs.getInt("accID");
                }

                //insert a new order
                System.out.println("accountID: " + accid);
                Date d = new Date(System.currentTimeMillis());
                System.out.println("order date: " + d);
                sql = "insert Orders (OrdDate, status, AccID) values(?,?,?)";
                pst = cn.prepareStatement(sql);
                pst.setDate(1, d);
                pst.setInt(2, 1);
                pst.setInt(3, accid);
                pst.executeUpdate();

                //get order id that is the lagest number
                sql = "select top 1 orderID from Orders order by orderID desc";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    orderid = rs.getInt("orderID");
                }
                //insert order details
                System.out.println("orderid " + orderid);
                Set<String> pids = cart.keySet();
                for (String pid : pids) {
                    sql = "insert OrderDetails values(?,?,?) ";
                    pst = cn.prepareStatement(sql);
                    pst.setInt(1, orderid);
                    pst.setInt(2, Integer.parseInt(pid.trim()));
                    pst.setInt(3, cart.get(pid));
                    pst.executeUpdate();
                    cn.commit();
                    cn.setAutoCommit(true);
                }
                return true;
            } else {
                System.out.println("k chen order dc");
            }
        } catch (Exception e) {

            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            result = false;
            System.out.println(e.getMessage());
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static ArrayList<Order> filterOrders(String dateStart, String dateEnd) {
        ArrayList<Order> list = new ArrayList<>();
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select OrderID,[OrdDate],shipdate,a.status,a.AccID from Orders a, Accounts b \n"
                        + "where a.AccID = b.accID and [OrdDate] between ? and ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, dateStart);
                pst.setString(2, dateEnd);
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int orderID = table.getInt("OrderID");
                        String orderDate = table.getString("OrdDate");
                        String shipDate = table.getString("shipdate");
                        int status = table.getInt("status");
                        int accID = table.getInt("AccID");
                        Order order = new Order(orderID, orderDate, shipDate, status, accID);
                        list.add(order);
                    }
                }
                cn.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
     public static ArrayList<Order> getAllOrders() {
        Connection cn = null;
        Order order = null;
        ArrayList<Order> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();

            if (cn != null) {
                String sql = "select o.OrderID,o.OrdDate,o.shipdate,o.status,o.AccID,a.fullname \n"
                        + "from dbo.Orders o join dbo.Accounts a on o.AccID=a.accID\n";

                PreparedStatement pst = cn.prepareStatement(sql);                
                ResultSet rs = pst.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        int orderID = rs.getInt("OrderID");
                        String orderDate = rs.getString("OrdDate");
                        String shipDate = rs.getString("shipdate");
                        int status = rs.getInt("status");
                        int accID = rs.getInt("accID");
                        String fullname = rs.getString("fullname");

                        order = new Order(orderID, orderDate, shipDate, status, accID, fullname);
                        list.add(order);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // bước 4: đóng connection
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
public static ArrayList<Order> getOrdersByStatus(String email, int status) {
        Connection cn=null;
        Order ord=null;
        ArrayList<Order> list=new ArrayList<>();
        try {
            cn=DBUtils.makeConnection();
            if (cn!=null) {
                String sql = "select o.OrderID,o.OrdDate,o.shipdate,o.status,o.AccID\n"
                        + "from dbo.Orders o\n"
                        + "join dbo.Accounts a on o.AccID=a.accID\n"
                        + "where a.email=? and o.status=?";
                PreparedStatement pst=cn.prepareStatement(sql);
                pst.setString(1, email);
                pst.setInt(2, status);
                ResultSet rs=pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {                       
                        int orderID = rs.getInt("OrderID");
                        String orderDate = rs.getString("OrdDate");
                        String shipDate = rs.getString("shipdate");
                        status = rs.getInt("status");
                        int accid = rs.getInt("AccID");
                        ord = new Order(orderID, orderDate, shipDate, status, accid);
                        list.add(ord);
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

public static boolean updateOrderStatus(int id,int status) {
        Connection cn=null;
        try {
            cn=DBUtils.makeConnection();
            if(cn!=null) {
                String sql="update Orders set status=? where OrderID=?";
                PreparedStatement pst=cn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setInt(2, id);
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

 public static boolean updateOrderDate(String date,int id) {
        Connection cn=null;
        try {
            cn=DBUtils.makeConnection();
            if(cn!=null) {
                String sql="update Orders set OrdDate=? where OrderID=?";
                PreparedStatement pst=cn.prepareStatement(sql);
                pst.setString(1, date);
                pst.setInt(2, id);
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
 
  public static boolean updateShipDateWhenOrderCompleted(int id) {
        Connection cn=null;
        try {
            cn=DBUtils.makeConnection();
            if(cn!=null) {
                String sql="update Orders set ShipDate=? where OrderID=?";
                PreparedStatement pst=cn.prepareStatement(sql);
                String date=null;
                Date d=new Date(System.currentTimeMillis());
                date=d.toString();
                pst.setString(1, date);
                pst.setInt(2, id);
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
  
   public static boolean updateShipDateNullWhenOrderCanceled(int id) {
        Connection cn=null;
        try {
            cn=DBUtils.makeConnection();
            if(cn!=null) {
                String sql="update Orders set ShipDate=? where OrderID=?";
                PreparedStatement pst=cn.prepareStatement(sql);
                String date=null;   
                pst.setString(1, date);
                pst.setInt(2, id);
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
   
   public static ArrayList<Order> getAllOrdersByUserName(String fullNameInput) {
        Connection cn = null;
        Order order = null;
        ArrayList<Order> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();

            if (cn != null) {
                String sql = "select o.OrderID,o.OrdDate,o.shipdate,o.status,o.AccID,a.fullname \n"
                        + "from dbo.Orders o join dbo.Accounts a on o.AccID=a.accID\n"
                        + "where a.fullname like ?";

                PreparedStatement pst = cn.prepareStatement(sql);   
                 pst.setString(1, "%"+ fullNameInput +"%");
                ResultSet rs = pst.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        int orderID = rs.getInt("OrderID");
                        String orderDate = rs.getString("OrdDate");
                        String shipDate = rs.getString("shipdate");
                        int status = rs.getInt("status");
                        int accID = rs.getInt("accID");
                        String fullname = rs.getString("fullname");

                        order = new Order(orderID, orderDate, shipDate, status, accID, fullname);
                        list.add(order);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // bước 4: đóng connection
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
   
   
}
