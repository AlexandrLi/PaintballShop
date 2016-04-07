package com.epam.alexandrli.paintballshop.action;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private Map<String, Action> actions;

    public ActionFactory() {
        actions = new HashMap<>();
        actions.put("GET/home", new ShowHomePageAction());
        actions.put("GET/login", new ShowPageAction("login"));
        actions.put("GET/logout", new LogoutAction());
        actions.put("GET/register", new ShowRegisterPageAction());
        actions.put("POST/register", new RegisterAction());
        actions.put("POST/login", new LoginAction());
        actions.put("GET/locale", new SelectLocaleAction());

    }

    public Action getAction(String actionName) {
        return actions.get(actionName);
    }
}
