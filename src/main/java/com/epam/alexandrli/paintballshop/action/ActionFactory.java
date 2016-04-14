package com.epam.alexandrli.paintballshop.action;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private Map<String, Action> actions;

    public ActionFactory() {
        actions = new HashMap<>();
        actions.put("GET/home", new ShowHomePageAction());
        actions.put("GET/login", new ShowPageAction("login"));
        actions.put("GET/userorders", new ShowUserOrdersPageAction());
        actions.put("GET/logout", new LogoutAction());
        actions.put("GET/register", new ShowRegisterPageAction());
        actions.put("POST/register", new RegisterAction());
        actions.put("POST/login", new LoginAction());
        actions.put("GET/locale", new SelectLocaleAction());
        actions.put("GET/userprofile", new ShowEditProfilePageAction());
        actions.put("POST/editprofile", new EditProfileAction());
        actions.put("POST/transfercash", new TransferCashAction());
        actions.put("POST/addtocart", new AddToCartAction());
        actions.put("POST/addcartdescription", new AddCartDescriptionAction());
        actions.put("GET/catalog", new ShowCatalogPageAction());
        actions.put("GET/product", new ShowProductPageAction());
        actions.put("GET/order", new ShowOrderPageAction());
        actions.put("GET/removeitem", new RemoveItemAction());
        actions.put("GET/clearcart", new ClearCartAction());
        actions.put("GET/placeorder", new PlaceOrderAction());

    }

    public Action getAction(String actionName) {
        return actions.get(actionName);
    }
}
