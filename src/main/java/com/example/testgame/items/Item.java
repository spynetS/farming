package com.example.testgame.items;

import java.util.Set;


/**
 * Item
 */
public class Item {
    private final String id;
    private final String name;
    private final Set<ItemTag> tags;
    private final int maxStackSize;

    public Item(String id, String name, Set<ItemTag> tags, int maxStackSize) {
        this.id = id;
        this.name = name;
        this.tags = tags;
        this.maxStackSize = maxStackSize;
    }

}
