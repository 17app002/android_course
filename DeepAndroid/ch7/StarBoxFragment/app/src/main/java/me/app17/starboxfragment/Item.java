package me.app17.starboxfragment;

import java.io.Serializable;

/***
 * 商品物件
 * 實作Serializable介面可被序列化
 */
public class Item implements Serializable {
    //擺放圖形id
    private int resId;
    private String title;
    private String subTitle;
    private int price;
    private String info;

    public String getSubTitle() {
        return subTitle;
    }

    public Item(int resId, String title, String subTitle, int price, String info) {
        this.resId = resId;
        this.title = title;
        this.price = price;
        this.info = info;
        this.subTitle = subTitle;
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
