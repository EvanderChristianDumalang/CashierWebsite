package model;

import lombok.Getter;

public class Menu {
    @Getter private Integer id;
    @Getter private String name;
    @Getter private Integer price;
    @Getter private String picture;

    public Menu(){
        id = price = 0;
        name = picture = "";
    }
    public Menu(Integer id, String name, Integer price, String picture){
        this.id = id;
        this.name = name;
        this.price = price;
        this.picture = picture;
    }
}
