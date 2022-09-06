package com.isometric.game;

import com.isometric.core.Item;

import java.util.LinkedList;

public class PlayerDatabase {

    public static int copper = 0;
    public static int silver = 0;
    public static int gold = 0;
    public static long overflow = 0;

    public static LinkedList<Item> items = new LinkedList<>();
    public static int maxItems = 25;

    public static boolean addItem(Item newItem) {
        boolean added = false;
        if (items.size() < maxItems) {
            items.add(newItem);
            added = true;
        }
        return added;
    }


    public static void addCopper(int amount) {
        float temp = ((float) copper + (float) amount) / (float) 100;
        addSilver((int) Math.floor(temp));
        copper = Math.round(((float) temp - (float) Math.floor(temp)) * 100);
    }

    public static void addSilver(int amount) {
        float temp = ((float) silver + (float) amount) / (float) 100;
        addGold((int) Math.floor(temp));
        silver = Math.round(((float) temp - (float) Math.floor(temp)) * 100);
    }

    public static void addGold(int amount) {
        float temp = ((float) gold + (float) amount) / (float) 100;
        overflow += Math.floor(temp);
        gold = Math.round(((float) temp - (float) Math.floor(temp)) * 100);
    }
}
