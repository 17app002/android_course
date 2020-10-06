package me.app17.starboxfragment;

import java.util.ArrayList;
import java.util.List;

/***
 * 存放目錄
 */
public class Catalog {
    private int id;
    private String title;

    private List<Item> itemList = new ArrayList<>();


    public Catalog(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
