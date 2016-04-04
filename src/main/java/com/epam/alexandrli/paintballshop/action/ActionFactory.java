package com.epam.alexandrli.paintballshop.action;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private Map<String, Action> actions;

    public ActionFactory() {
        actions = new HashMap<>();
        actions.put("GET/home", new ShowPageAction("home"));
        actions.put("GET/login", new ShowPageAction("login"));
        actions.put("GET/register", new ShowPageAction("register"));
        actions.put("POST/login", new LoginAction());

    }

    public Action getAction(String actionName) {
        return actions.get(actionName);
    }
}
