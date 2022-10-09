import app.Path;
import controllers.*;

import static spark.Spark.*;

public class Application{
    public static void main(String[] args){
        staticFileLocation("/templates");

        notFound("<html><body><h1>404 Not found!</h1></body></html>");

        /*--------------------------------------------------------------------------*/

        get(Path.Web.LOGIN, Controller.serveViewLoginPage);
        post(Path.Web.LOGIN, Controller.handleLogin);

        /*--------------------------------------------------------------------------*/

        get(Path.Web.MAIN, Controller.serveViewDashboard);
        get(Path.Web.MAIN, Controller.serveViewPayment);
        post(Path.Web.MAIN, Controller.handlePayment);

        /*--------------------------------------------------------------------------*/

        get(Path.Web.SUCCESS, Controller.serveViewPaymentSuccess);

        /*--------------------------------------------------------------------------*/

        get(Path.Web.CHECKOUT_ADD, Controller.serveViewCart);
        post(Path.Web.CHECKOUT_ADD, Controller.handleAddCart);

        get(Path.Web.CHECKOUT_EDIT, Controller.serveEditCart);
        post(Path.Web.CHECKOUT_EDIT, Controller.handleEditCart);

        get(Path.Web.CHECKOUT_DELETE, Controller.serveDeleteCart);
        post(Path.Web.CHECKOUT_DELETE, Controller.handleDeleteCart);

        /*--------------------------------------------------------------------------*/

        get(Path.Web.MENU, Controller.serveViewInventory);

        get(Path.Web.MENU_ADD, Controller.serveAddInventory);
        post(Path.Web.MENU_ADD, Controller.handleAddInventory);

        get(Path.Web.MENU_EDIT, Controller.serveEditInventory);
        post(Path.Web.MENU_EDIT, Controller.handleEditInventory);

        get(Path.Web.MENU_DELETE, Controller.serveDeleteInventory);
        post(Path.Web.MENU_DELETE, Controller.handleDeleteInventory);

        /*--------------------------------------------------------------------------*/

        get(Path.Web.ACCOUNT, Controller.serveViewEmployee);

        get(Path.Web.ACCOUNT_ADD, Controller.serveAddEmployee);
        post(Path.Web.ACCOUNT_ADD, Controller.handleAddEmployee);

        get(Path.Web.ACCOUNT_EDIT, Controller.serveEditEmployee);
        post(Path.Web.ACCOUNT_EDIT, Controller.handleEditEmployee);

        get(Path.Web.ACCOUNT_DELETE, Controller.serveDeleteEmployee);
        post(Path.Web.ACCOUNT_DELETE, Controller.handleDeleteEmployee);
    }
}