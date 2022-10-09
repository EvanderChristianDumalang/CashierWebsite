package model;

import lombok.Getter;

public class Checkout {
    @Getter private Integer id;
    @Getter private Integer idp;
    @Getter private String name;
    @Getter private Integer quantity;
    @Getter private Integer price;
    @Getter private Integer total;

    public Checkout(){
        id = idp = quantity = price = total = 0;
        name = "";
    }
    public Checkout(Integer id, Integer idp, String name, Integer quantity, Integer price, Integer total){
        this.id = id;
        this.idp = idp;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }
}
