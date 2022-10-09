package app;

import lombok.Getter;

public class Path{
    public static class Web{
        @Getter public static final String LOGIN = "/";
        @Getter public static final String MAIN = "/main/";
        @Getter public static final String CHECKOUT = "/main/checkout/";
        @Getter public static final String CHECKOUT_ADD = "/main/checkout/add/:id/";
        @Getter public static final String CHECKOUT_EDIT = "/main/checkout/edit/:id/";
        @Getter public static final String CHECKOUT_DELETE = "/main/checkout/delete/:id/";

        @Getter public static final String SUCCESS = "/main/success/";

        @Getter public static final String MENU = "/menu/";
        @Getter public static final String MENU_ADD = "/menu/add/";
        @Getter public static final String MENU_EDIT = "/menu/:id/edit/";
        @Getter public static final String MENU_DELETE = "/menu/:id/delete/";

        @Getter public static final String ACCOUNT = "/account/";
        @Getter public static final String ACCOUNT_ADD = "/account/add/";
        @Getter public static final String ACCOUNT_EDIT = "/account/:id/edit/";
        @Getter public static final String ACCOUNT_DELETE = "/account/:id/delete/";
    }

    public static class Template{
        public static final String LOGIN = "/web/main/login.vm";
        public static final String MAIN = "/web/main/main.vm";
        public static final String SUCCESS = "/web/main/success.vm";

        public static final String MENU = "/web/menu/menu.vm";
        public static final String MENU_DELETE = "/web/menu/menu_detail.vm";

        public static final String ACCOUNT = "/web/account/account.vm";
        public static final String ACCOUNT_DETAIL = "/web/account/account_detail.vm";
    }
}