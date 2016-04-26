package com.epam.alexandrli.paintballshop.action;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private Map<String, Action> actions;

    public ActionFactory() {
        actions = new HashMap<>();
        actions.put("GET/locale", new SelectLocaleAction());
        actions.put("GET/login", new ShowPageAction("login"));
        actions.put("GET/forbidden", new ShowPageAction("forbidden"));
        actions.put("GET/cart", new ShowPageAction("cart"));
        actions.put("GET/register", new ShowRegisterPageAction());
        actions.put("GET/home", new ShowHomePageAction());
        actions.put("GET/catalog", new ShowCatalogPageAction());
        actions.put("GET/product", new ShowProductPageAction());
        actions.put("GET/user/profile/data", new ShowEditUserDataPageAction());
        actions.put("GET/user/profile/address", new ShowEditUserAddressPageAction());
        actions.put("GET/edit/user", new ShowEditUserPageAction());
        actions.put("GET/edit/product", new ShowEditProductPageAction());
        actions.put("GET/user/orders", new ShowUserOrdersPageAction());
        actions.put("GET/logout", new LogoutAction());
        actions.put("GET/user/profile", new ShowProfilePageAction());
        actions.put("GET/order", new ShowOrderPageAction());
        actions.put("GET/cart/deleteitem", new RemoveItemAction());
        actions.put("GET/cart/clear", new ClearCartAction());
        actions.put("GET/cart/buy", new BuyCartAction());
        actions.put("GET/manage/users", new ShowManageUsersPageAction());
        actions.put("GET/manage/products", new ShowManageProductsPageAction());
        actions.put("GET/manage/orders", new ShowManageOrdersPageAction());
        actions.put("GET/manage/storage", new ShowManageStoragePageAction());
        actions.put("GET/add/product", new ShowAddProductPageAction());
        actions.put("GET/delete/user", new DeleteUserAction());
        actions.put("GET/delete/product", new DeleteProductAction());
        actions.put("GET/delete/order", new DeleteOrderAction());
        actions.put("GET/delete/storageItem", new DeleteStorageItemAction());
        actions.put("POST/edit/user", new EditUserAction());
        actions.put("POST/register", new RegisterAction());
        actions.put("POST/login", new LoginAction());
        actions.put("POST/edit/data", new EditUserDataAction());
        actions.put("POST/edit/address", new EditUserAddressAction());
        actions.put("POST/edit/product", new EditProductAction());
        actions.put("POST/add/product", new AddProductAction());
        actions.put("POST/edit/orderStatus", new EditOrderStatusAction());
        actions.put("POST/edit/storage/itemAmount", new EditStorageItemAmountAction());
        actions.put("POST/refill/balance", new RefillBalanceAction());
        actions.put("POST/cart/add", new AddToCartAction());
        actions.put("POST/cart/description", new AddCartDescriptionAction());
        actions.put("POST/cart/recount", new RecountCartAction());
        actions.put("POST/set/pagesize", new SetPageSizeAction());
    }

    public Action getAction(String actionName) {
        return actions.get(actionName);
    }
}
