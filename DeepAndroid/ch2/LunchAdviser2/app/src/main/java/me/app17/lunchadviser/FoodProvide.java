package me.app17.lunchadviser;

import java.util.ArrayList;
import java.util.List;

public class FoodProvide {

    List<String> getFood(String type) {
        List<String> foods = new ArrayList<>();

        if (type.equals("早餐")) {
            foods.add("1.中式套餐");
            foods.add("2.兒童套餐");
            foods.add("3.招牌豬鐵板麵套餐");
        } else if (type.equals("午餐")) {
            foods.add("1.麻婆豆腐飯");
            foods.add("2.脆皮燒肉飯");
            foods.add("3.蜜汁叉燒飯");
        } else if (type.equals("晚餐")) {
            foods.add("1.泰式酸辣雞");
            foods.add("2.海鮮湯麵");
            foods.add("3.肉絲炒麵");
        }

        return foods;
    }
}
