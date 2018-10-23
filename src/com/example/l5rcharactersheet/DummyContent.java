package com.example.l5rcharactersheet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyContent {

    public static class DummyItem {

        public String id;
        public String content;
        public int layout;

        public DummyItem(String id, String content, int layout) {
            this.id = id;
            this.content = content;
            this.layout = layout;
        }

        @Override
        public String toString() {
            return content;
        }
    }

    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    static {
        addItem(new DummyItem("1", "Status", R.layout.fragment_l5rcs_summary));
        addItem(new DummyItem("2", "Inventory", R.layout.fragment_l5rcs_blank));
        addItem(new DummyItem("3", "Spells", R.layout.fragment_l5rcs_blank));
        addItem(new DummyItem("4", "Techniques", R.layout.fragment_l5rcs_blank));
        addItem(new DummyItem("5", "Techniques", R.layout.fragment_l5rcs_blank));
        addItem(new DummyItem("6", "Character Info",R.layout.fragment_l5rcs_blank));
        addItem(new DummyItem("7", "Notes", R.layout.fragment_l5rcs_blank));
        addItem(new DummyItem("8", "XP", R.layout.fragment_l5rcs_xp));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }
}
