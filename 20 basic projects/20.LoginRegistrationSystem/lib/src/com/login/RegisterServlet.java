package com.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class RegisterServlet extends HttpServlet {

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String name =
                    request.getParameter("name");

            String email =
                    request.getParameter("email");

            String password =
                    request.getParameter("password");

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "INSERT INTO users(name,email,password) VALUES(?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);

            int row = ps.executeUpdate();

            if (row > 0) {

                response.sendRedirect("login.jsp");

            } else {

                response.getWriter().println(
                        "Registration Failed");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}