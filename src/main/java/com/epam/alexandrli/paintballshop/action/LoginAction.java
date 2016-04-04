package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.dao.GenericDao;
import com.epam.alexandrli.paintballshop.dao.JdbcDaoFactory;
import com.epam.alexandrli.paintballshop.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginAction implements Action {
    private ActionResult home = new ActionResult("home", true);
    private ActionResult loginAgain = new ActionResult("login");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        GenericDao<User> userDao = null;
        try {
            JdbcDaoFactory jdbcDaoFactory = new JdbcDaoFactory();
            userDao = jdbcDaoFactory.getDao(User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        List<User> users = userDao.findAllByParams(params);
        if (!users.isEmpty()) {
            req.getSession().setAttribute("user", users.get(0));
            return home;
        } else {
            req.setAttribute("loginError", "Invalid Login or Password");
            return loginAgain;
        }
    }
}
