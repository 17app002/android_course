package me.app17.coffeeshop;

import java.io.Serializable;

/***
 * 商品物件
 * 實作Serializable介面可被序列化
 */
public class Item implements Serializable {
    //擺放圖形id
    private int resId;
    private String title;
    private int price;
    private String info;

    public Item(int resId, String title, int price, String info) {
        this.resId = resId;
        this.title = title;
        this.price = price;
        this.info = info;
    }

    public int getResId() {
        return resId;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "Item{" +
                "resId=" + resId +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", info='" + info + '\'' +
                '}';
    }




}
