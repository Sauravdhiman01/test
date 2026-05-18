package com.test.dao;

import com.test.model.Item;
import java.sql.*;

public class ItemDAO {

    String url = "jdbc:mysql://localhost:3306/testdb";
    String username = "root";
    String password = "1234";

    public Connection getConnection() {
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }

    public void insertItem(Item item) {
        try {
            Connection con = getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "insert into items values (?, ?, ?, ?)"
            );

            ps.setInt(1, item.getId());
            ps.setString(2, item.getName());
            ps.setDouble(3, item.getPrice());
            ps.setString(4, item.getType());

            ps.executeUpdate();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Item getItemById(int id) {
        Item item = null;

        try {
            Connection con = getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "select * from items where id=?"
            );

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                item = new Item(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("type")
                );
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return item;
    }

    public void deleteItem(int id) {
        try {
            Connection con = getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "delete from items where id=?"
            );

            ps.setInt(1, id);
            ps.executeUpdate();

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateItem(Item item) {
        try {
            Connection con = getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "update items set name=?, price=?, type=? where id=?"
            );

            ps.setString(1, item.getName());
            ps.setDouble(2, item.getPrice());
            ps.setString(3, item.getType());
            ps.setInt(4, item.getId());

            ps.executeUpdate();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}