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
import java.util.List;

@WebServlet("/")
public class UserServlet extends HttpServlet {
    UserDao userDao = new UserDao();
    public UserServlet() {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
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
                showEditForm(req, resp);
                break;

            case "/update" :
                try {
                    updateUser(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;

            case "/list":     // ✅ Add this explicitly
                listUser(req, resp);
                break;

            default:
                listUser(req, resp);
                break;
        }
        }
// to show new user form
        private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            RequestDispatcher dispatcher = req.getRequestDispatcher("add-new-user.jsp");
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
                RequestDispatcher dispatcher = req.getRequestDispatcher("add-new-user.jsp");
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
        userDao.updateUSer(user);
        req.setAttribute("user", user);
        resp.sendRedirect("list");
    }
    // default case
    private void listUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList = userDao.selectAllUser();
        req.setAttribute("userList", userList);
        req.getRequestDispatcher("index.jsp").forward(req, resp);

    }


}
