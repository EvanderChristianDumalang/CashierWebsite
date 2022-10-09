package app;

import org.sql2o.Sql2o;

public class BaseDao{
    protected static Sql2o db;

    static{
        db = new Sql2o("jdbc:mysql://localhost:3306/cafe", "root", "");
    }
}
