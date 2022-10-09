package model;

import lombok.Getter;

public class AccountType {
    @Getter private Integer id;
    @Getter private String type;

    public AccountType(){
        id = 0;
        type = "";
    }
    public AccountType(Integer id, String type){
        this.id = id;
        this.type = type;
    }
}
