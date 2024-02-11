package com.example.likeherotozero.beans;

import com.example.likeherotozero.entity.UserEntity;
import com.example.likeherotozero.service.UserService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

@Named("login")
@RequestScoped
public class LoginBean {
    private String username;
    private String password;

    private UserService userService = new UserService();

    public String login()
    {
        UserEntity user = userService.checkLogin(username, password);
        if (user != null) {
            user.setPassword(null);
            //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userId", user.getUserId());
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            session.setAttribute("user", user);

            return "backend.xhtml";
        } else {
            return "login.xhtml";
        }
    }

    public String logout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        if (session != null)
        {
            session.invalidate();
        }

        return "login.xhtml";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}