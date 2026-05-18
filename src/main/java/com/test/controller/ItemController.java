package com.test.controller;

import com.test.dao.ItemDAO;
import com.test.model.Item;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.RequestDispatcher;

@WebServlet(urlPatterns = {"/", "/onboard", "/insert", "/details", "/delete", "/update", "/processUpdate"})
public class ItemController extends HttpServlet {

    ItemDAO dao = new ItemDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        try {

            if (action.equals("/onboard")) {
                request.getRequestDispatcher("onboard.jsp").forward(request, response);

            } else if (action.equals("/insert")) {

                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                double price = Double.parseDouble(request.getParameter("price"));
                String type = request.getParameter("type");

                Item item = new Item(id, name, price, type);
                dao.insertItem(item);

                request.setAttribute("msg", "Item Added Successfully");
                request.getRequestDispatcher("onboard.jsp").forward(request, response);

            } else if (action.equals("/details")) {

                String idValue = request.getParameter("id");

                if (idValue != null && !idValue.trim().isEmpty()) {
                    int id = Integer.parseInt(idValue.trim());
                    Item item = dao.getItemById(id);
                    if (item == null) {
                        request.setAttribute("msg", "Item not found!");
                    } else {
                        request.setAttribute("item", item);
                    }
                }

                request.getRequestDispatcher("details.jsp").forward(request, response);

            } else if (action.equals("/delete")) {

                String idValue = request.getParameter("id");

                if (idValue != null) {
                    int id = Integer.parseInt(idValue);
                    dao.deleteItem(id);
                    request.setAttribute("msg", "Item Deleted Successfully");
                }

                request.getRequestDispatcher("delete.jsp").forward(request, response);

            } else if (action.equals("/update")) {
                request.getRequestDispatcher("update.jsp").forward(request, response);

            } else if (action.equals("/processUpdate")) {

                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                double price = Double.parseDouble(request.getParameter("price"));
                String type = request.getParameter("type");

                Item item = new Item(id, name, price, type);
                dao.updateItem(item);

                request.setAttribute("msg", "Item Updated Successfully");
                request.getRequestDispatcher("update.jsp").forward(request, response);

            } else {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}