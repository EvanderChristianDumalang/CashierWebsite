package dao;

import app.BaseDao;
import model.Account;
import model.Checkout;
import model.Menu;
import model.AccountType;
import org.sql2o.Connection;

import java.util.List;

public class Dao extends BaseDao{
    public static List<Checkout> getAllCheckout(){
        String sql = "SELECT c.id, idproduct idp, name, quantity, price, quantity*price total " +
                "FROM checkout c JOIN menu m ON c.idproduct = m.id";
        try(Connection con = db.open()){
            return con.createQuery(sql).executeAndFetch(Checkout.class);
        }
    }
    public static void AddCheckout(Integer idp, Integer quantity){
        String sql = "INSERT INTO checkout(idproduct, quantity) VALUES(:id, :qty)";
        try(Connection con = db.open()){
            con.createQuery(sql)
                    .addParameter("id", idp)
                    .addParameter("qty", quantity)
                    .executeUpdate();
        }
    }
    public static void updateCheckout(Integer id, Integer quantity){
        String sql = "UPDATE checkout SET quantity = :qty WHERE id = :id";
        try(Connection con = db.open()){
            con.createQuery(sql)
                    .addParameter("qty", quantity)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
    public static void deleteCheckout(Integer id){
        String sql = "DELETE FROM checkout WHERE id = :id";
        try(Connection con = db.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    public static Boolean menuExist(Integer id){
        String sql = "SELECT COUNT(*) FROM checkout WHERE idproduct = :id";
        try(Connection con = db.open()){
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeScalar(Boolean.class);
        }
    }
    public static void AddCheckoutQty(Integer id){
        String sql = "UPDATE checkout SET quantity = quantity + 1 WHERE idproduct = :id";
        try(Connection con = db.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    public static Integer getTotal(){
        String sql = "SELECT COALESCE(SUM(m.price*c.quantity), 0) " +
                "FROM checkout c JOIN menu m ON c.idproduct = m.id";
        try(Connection con = db.open()){
            return con.createQuery(sql).executeScalar(Integer.class);
        }
    }

    public static void flushCheckout(){
        String clearCart = "TRUNCATE TABLE checkout";
        try(Connection con = db.open()){
            con.createQuery(clearCart).executeUpdate();
        }
    }

    public List<Account> getAllAccount(){
        String sql = "SELECT acc.id, username, password, t.type " +
                "FROM account acc JOIN type t ON acc.type = t.id";
        try(Connection con = db.open()){
            return con.createQuery(sql).executeAndFetch(Account.class);
        }
    }
    public Account getAccount(Integer id){
        String sql = "SELECT acc.id, username, password, t.type " +
                "FROM account acc JOIN type t ON acc.type = t.id " +
                "WHERE acc.id = :id";
        try(Connection con = db.open()){
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Account.class);
        }
    }
    public void insertAccount(String username, String password, Integer type){
        String sql = "INSERT INTO account(username, password, type) VALUES(:username, :pass, :type)";
        try(Connection con = db.open()){
            con.createQuery(sql)
                    .addParameter("username", username)
                    .addParameter("pass", password)
                    .addParameter("type", type)
                    .executeUpdate();
        }
    }
    public void updateAccount(Integer id, String username, String password, Integer type){
        String sql = "UPDATE account SET username = :username, password = :pass, type = :type WHERE id = :id";
        try(Connection con = db.open()){
            con.createQuery(sql)
                    .addParameter("username", username)
                    .addParameter("pass", password)
                    .addParameter("type", type)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
    public void deleteAccount(Integer id){
        String sql = "DELETE FROM account WHERE id = :id";
        try(Connection con = db.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    public List<AccountType> getAllAccountsType(){
        String sql = "SELECT*FROM type";
        try(Connection con = db.open()){
            return con.createQuery(sql).executeAndFetch(AccountType.class);
        }
    };

    public static Boolean verifyLogin(String username, String pass){
        String sql = "SELECT COUNT(*) FROM account WHERE username = :id AND password = :pass";
        try(Connection con = db.open()){
            return con.createQuery(sql)
                    .addParameter("id", username)
                    .addParameter("pass", pass)
                    .executeScalar(Boolean.class);
        }
    }
    public static Integer getAccountType(String username, String pass){
        String sql = "SELECT type FROM account WHERE username = :id AND password = :pass";
        try(Connection con = db.open()){
            return con.createQuery(sql)
                    .addParameter("id", username)
                    .addParameter("pass", pass)
                    .executeScalar(Integer.class);
        }
    }

    public static List<Menu> getAllMenu(){
        String sql = "SELECT m.id, m.name, price, picture " +
                "FROM menu m ";
        try(Connection con = db.open()){
            return con.createQuery(sql).executeAndFetch(Menu.class);
        }
    }

    public static Menu getMenu(Integer id){
        String sql = "SELECT id, name, price, picture " +
                "FROM menu " +
                "WHERE id = :id";
        try(Connection con = db.open()){
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Menu.class);
        }
    }
    public static void insertMenu(String name, Integer price, String picture){
        String sql = "INSERT INTO menu(name, price, picture) VALUES(:name, :price, :picture)";
        try(Connection con = db.open()){
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("price", price)
                    .addParameter("picture", picture)
                    .executeUpdate();
        }
    }
    public static void updateMenu(Integer id, String name, Integer price, String picture){
        String sql = "UPDATE menu SET name = :name, price = :price, picture = :picture WHERE id = :id";
        try(Connection con = db.open()){
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("price", price)
                    .addParameter("picture", picture)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    public static void deleteMenu(Integer id){
        String sql = "DELETE FROM menu WHERE id = :id";
        try(Connection con = db.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}

