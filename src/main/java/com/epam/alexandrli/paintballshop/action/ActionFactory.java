package com.epam.alexandrli.paintballshop.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ActionFactory {
    Properties properties;
    private Map<String, Action> actions;

    public ActionFactory() {
//        PropertyManager propertyManager = new PropertyManager();
//        try {
//            properties = PropertyManager.getProperties("action.properties");
//        } catch (PropertyManagerException e) {
//            e.printStackTrace();
//        }
        actions = new HashMap<>();
        actions.put("GET/home", new ShowHomePageAction());
        actions.put("GET/login", new ShowPageAction("login"));
        actions.put("GET/edit/data", new ShowEditUserDataPageAction());
        actions.put("GET/edit/address", new ShowEditUserAddressPageAction());
        actions.put("GET/edit/user", new ShowEditUserPageAction());
        actions.put("POST/edit/user", new EditUserAction());
        actions.put("GET/user/orders", new ShowUserOrdersPageAction());
        actions.put("GET/logout", new LogoutAction());
        actions.put("GET/register", new ShowRegisterPageAction());
        actions.put("POST/register", new RegisterAction());
        actions.put("POST/login", new LoginAction());
        actions.put("GET/locale", new SelectLocaleAction());
        actions.put("GET/user/profile", new ShowProfilePageAction());
        actions.put("POST/edit/data", new EditUserDataAction());
        actions.put("POST/edit/address", new EditUserAddressAction());
        actions.put("POST/refill/balance", new RefillBalanceAction());
        actions.put("POST/addtocart", new AddToCartAction());
        actions.put("POST/addcartdescription", new AddCartDescriptionAction());
        actions.put("GET/catalog", new ShowCatalogPageAction());
        actions.put("GET/product", new ShowProductPageAction());
        actions.put("GET/order", new ShowOrderPageAction());
        actions.put("GET/removeitem", new RemoveItemAction());
        actions.put("GET/clearcart", new ClearCartAction());
        actions.put("GET/placeorder", new PlaceOrderAction());
        actions.put("GET/manage/users", new ShowManageUsersPage());
        actions.put("GET/manage/products", new ShowManageProductsPage());
        actions.put("GET/manage/orders", new ShowManageOrdersPage());
        actions.put("GET/manage/storage", new ShowManageStoragePage());
        actions.put("GET/delete/user", new DeleteUserAction());
        actions.put("GET/delete/product", new DeleteProductAction());

    }

    public Action getAction(String actionName) {
//        String actionClassName = properties.getProperty(actionName);
//        Action action = null;
//        try {
//            String packageName = this.getClass().getPackage().getName();
//            action = (Action) Class.forName(packageName + "." + actionClassName).newInstance();
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return action;
        return actions.get(actionName);
    }
}
