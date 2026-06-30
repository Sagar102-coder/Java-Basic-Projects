package com.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class LoginServlet extends HttpServlet {

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String email =
                    request.getParameter("email");

            String password =
                    request.getParameter("password");

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * FROM users WHERE email=? AND password=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                HttpSession session =
                        request.getSession();

                session.setAttribute(
                        "username",
                        rs.getString("name"));

                response.sendRedirect(
                        "welcome.jsp");

            } else {

                response.getWriter().println(
                        "Invalid Email or Password");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}