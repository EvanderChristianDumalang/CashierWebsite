package model;

import lombok.Getter;

public class Account{
    @Getter private Integer id;
    @Getter private String username;
    @Getter private String password;
    @Getter private String type;

    public Account(){
        id = 0;
        username = password = type = "";
    }

    public Account(Integer id, String username, String password, String type){
        this.id = id;
        this.username = username;
        this.password = password;
        this.type = type;
    }
}
