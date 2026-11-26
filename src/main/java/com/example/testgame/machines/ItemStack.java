package com.example.testgame.machines;

import com.example.testgame.items.Item;

public class ItemStack {
    private final Item item;
    private final int amount;

    public ItemStack(Item item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }
}
