package controllers;

import app.Path;
import app.View;
import dao.Dao;
import model.Account;
import model.Menu;
import model.AccountType;
import spark.Request;
import spark.Response;
import spark.Route;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {
    private static Dao dao = new Dao();

    public static Route serveViewEmployee = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        List<Account> accounts = dao.getAllAccount();
        model.put("accountList", accounts);
        model.put("type", req.session().attribute("type"));
        model.put("username", req.session().attribute("username"));
        model.put("activeMenu", 2);
        return View.render(req, model, Path.Template.ACCOUNT);
    };

    public static Route serveAddEmployee = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        List<AccountType> accountTypes = dao.getAllAccountsType();

        model.put("accountTypeList", accountTypes);
        model.put("account", new Account());
        model.put("type", req.session().attribute("type"));
        model.put("username", req.session().attribute("username"));
        model.put("activeMenu", 2);

        return View.render(req, model, Path.Template.ACCOUNT_DETAIL);
    };
    public static Route handleAddEmployee = (Request req, Response res) -> {
        String username = req.queryParams("username");
        String password = req.queryParams("password");
        Integer type = Integer.parseInt(req.queryParams("type"));

        dao.insertAccount(username, password, type);
        res.redirect(Path.Web.ACCOUNT);
        return null;
    };

    public static Route serveEditEmployee = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        List<AccountType> accountTypes = dao.getAllAccountsType();
        Integer id = Integer.parseInt(req.params("id"));

        model.put("accountTypeList", accountTypes);
        model.put("account", dao.getAccount(id));
        model.put("type", req.session().attribute("type"));
        model.put("username", req.session().attribute("username"));
        model.put("activeMenu", 2);

        return View.render(req, model, Path.Template.ACCOUNT_DETAIL);
    };
    public static Route handleEditEmployee = (Request req, Response res) -> {
        Integer id = Integer.parseInt(req.params("id"));
        String username = req.queryParams("username");
        String password = req.queryParams("password");
        Integer type = Integer.parseInt(req.queryParams("type"));

        dao.updateAccount(id, username, password, type);
        res.redirect(Path.Web.ACCOUNT);
        return null;
    };

    public static Route serveDeleteEmployee = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        return View.render(req, model, Path.Template.ACCOUNT);
    };
    public static Route handleDeleteEmployee = (Request req, Response res) -> {
        Integer id = Integer.parseInt(req.params("id"));
        dao.deleteAccount(id);
        res.redirect(Path.Web.ACCOUNT);
        return null;
    };
    public static Route serveViewLoginPage = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        return View.render(req, model, Path.Template.LOGIN);
    };
    public static Route handleLogin = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        String username = req.queryParams("username");
        String password = req.queryParams("password");
        if(!Dao.verifyLogin(username, password)){
            model.put("authenticationFailed", true);
            return View.render(req, model, Path.Template.LOGIN);
        }
        req.session().attribute("username", username);
        req.session().attribute("type", Dao.getAccountType(username, password));
        req.session().attribute("sortItem", -1);
        res.redirect(Path.Web.MAIN);
        return null;
    };
    public static Route serveViewDashboard = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("menuList", Dao.getAllMenu());

        model.put("qtyExceeded", req.session().attribute("qtyExceeded"));
        model.put("totalPrice", Dao.getTotal());
        model.put("checkout", Dao.getAllCheckout());
        model.put("type", req.session().attribute("type"));
        model.put("activeMenu", 1);
        model.put("username", req.session().attribute("username"));
        return View.render(req, model, Path.Template.MAIN);
    };

    public static Route serveViewCart = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        return View.render(req, model, Path.Template.MAIN);
    };
    public static Route handleAddCart = (Request req, Response res) -> {
        Integer id = Integer.parseInt(req.params("id"));

        if(Dao.menuExist(id)){
            Dao.AddCheckoutQty(id);
        }else{
            Dao.AddCheckout(id, 1);
        }
        res.redirect(Path.Web.MAIN);
        return null;
    };

    public static Route serveEditCart = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        return View.render(req, model, Path.Template.MAIN);
    };
    public static Route handleEditCart = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        Integer idproduct = Integer.parseInt(req.queryParams("id"));

        Integer id = Integer.parseInt(req.params("id"));
        Integer qty = Integer.parseInt(req.queryParams("quantity"));

        req.session().attribute("qtyExceeded", false);
        Dao.updateCheckout(id, qty);
        res.redirect(Path.Web.MAIN);
        return null;
    };

    public static Route serveDeleteCart = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        return View.render(req, model, Path.Template.MAIN);
    };
    public static Route handleDeleteCart = (Request req, Response res) -> {
        Integer id = Integer.parseInt(req.params("id"));
        Dao.deleteCheckout(id);
        res.redirect(Path.Web.MAIN);
        return null;
    };

    public static Route serveViewInventory = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        List<Menu> menus = Dao.getAllMenu();

        model.put("menuList", menus);
        model.put("type", req.session().attribute("type"));
        model.put("activeMenu", 2);
        model.put("username", req.session().attribute("username"));
        return View.render(req, model, Path.Template.MENU);
    };
    public static Route serveAddInventory = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();

        model.put("menu", new Menu());
        model.put("type", req.session().attribute("type"));
        model.put("activeMenu", 2);
        model.put("username", req.session().attribute("username"));
        return View.render(req, model, Path.Template.MENU_DELETE);
    };
    public static Route handleAddInventory = (Request req, Response res) -> {
        String name = req.queryParams("name");
        Integer price = Integer.parseInt(req.queryParams("price"));
        String picture = req.queryParams("picture");
        Dao.insertMenu(name, price, picture);
        res.redirect(Path.Web.MENU);
        return null;
    };

    public static Route serveEditInventory = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        Integer id = Integer.parseInt(req.params("id"));

        model.put("menu", Dao.getMenu(id));
        model.put("type", req.session().attribute("type"));
        model.put("username", req.session().attribute("username"));
        model.put("activeMenu", 2);

        return View.render(req, model, Path.Template.MENU_DELETE);
    };
    public static Route handleEditInventory = (Request req, Response res) -> {
        Integer id = Integer.parseInt(req.params("id"));
        String name = req.queryParams("name");
        Integer price = Integer.parseInt(req.queryParams("price"));
        String picture = req.queryParams("picture");

        Dao.updateMenu(id, name, price, picture);
        res.redirect(Path.Web.MENU);
        return null;
    };

    public static Route serveDeleteInventory = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        return View.render(req, model, Path.Template.MENU);
    };
    public static Route handleDeleteInventory = (Request req, Response res) -> {
        Integer id = Integer.parseInt(req.params("id"));
        Dao.deleteMenu(id);
        res.redirect(Path.Web.MENU);
        return null;
    };

    public static Route serveViewPayment = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();

        model.put("moneyInsufficient", req.session().attribute("noMoney"));
        model.put("totalPay", Dao.getTotal());
        model.put("checkout", Dao.getAllCheckout());
        model.put("type", req.session().attribute("type"));
        model.put("activeMenu", 1);
        model.put("username", req.session().attribute("username"));
        return View.render(req, model, Path.Template.MAIN);
    };
    public static Route handlePayment = (Request req, Response res) -> {
        Integer mustPay = Dao.getTotal();
        Integer givenMoney = Integer.parseInt(req.queryParams("payMoney"));
        if(mustPay > givenMoney){
            res.redirect(Path.Web.MAIN);
            return null;
        }
        Dao.flushCheckout();
        req.session().attribute("noMoney", false);
        req.session().attribute("changeMoney", givenMoney - mustPay);
        res.redirect(Path.Web.SUCCESS);
        return null;
    };

    public static Route serveViewPaymentSuccess = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();

        model.put("transactionTime", LocalDateTime.now());
        model.put("changeMoney", req.session().attribute("changeMoney"));
        model.put("type", req.session().attribute("type"));
        model.put("activeMenu", 1);
        model.put("username", req.session().attribute("username"));
        return View.render(req, model, Path.Template.SUCCESS);
    };
}
