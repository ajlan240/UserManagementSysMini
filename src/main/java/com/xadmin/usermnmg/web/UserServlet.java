package com.xadmin.usermnmg.web;

import com.xadmin.usermnmg.bean.User;
import com.xadmin.usermnmg.dao.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.sql.SQLException;

@WebServlet("/")
public class UserServlet extends HttpServlet {
    UserDao userDao = new UserDao();
    public UserServlet() {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch(action) {
            case "/new" :
                showNewForm(req, resp);
                break;

            case "/insert" :
                try {
                    insertUser(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;

            case "/delete" :
                deleteUser(req, resp);
                break;

            case "/edit" :
                break;

            case "/update" :
                break;

            default:
                break;
        }
        }
// to show new user form
        private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            RequestDispatcher dispatcher = req.getRequestDispatcher("user-form.jsp");
            dispatcher.forward(req, resp);
        }
        // to insert new user
        private void insertUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String country = req.getParameter("country");
            User user = new User(name, email, country);
            userDao.insertUser(user);
            resp.sendRedirect("list");
        }
// to delete user
        private void deleteUser(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        userDao.deleteUser(id);
            try {
                resp.sendRedirect("list");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    // to show editor form
        private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        User existingUser;
        existingUser = userDao.selectUser(id);
                RequestDispatcher dispatcher = req.getRequestDispatcher("user-form.jsp");
                req.setAttribute("user", existingUser);
                dispatcher.forward(req, resp);
    }
// update user
    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        User user = new User(id, name, email, country);
        userDao.insertUser(user);
        resp.sendRedirect("list");
    }
    // default case


}
