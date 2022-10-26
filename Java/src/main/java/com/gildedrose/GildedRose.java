package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                items[i].sellIn = items[i].sellIn - 1;

                if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert") &&
                    !items[i].name.equals("Aged Brie")) {
                    handleQualityDecrease(i);
                }
                if (items[i].name.equals("Aged Brie")) {
                    handleBrie(i);
                }
                if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    handlePass(i);
                }
                if (items[i].name.equals("Conjured Mana Cake")) {
                    handleQualityDecrease(i);
                }
            }
        }
    }

    private void handleQualityDecrease(int i) {
        if(isZeroQuality(i)) {
            return;
        }
        if(items[i].quality > 1 && items[i].sellIn < 0) {
            items[i].quality--;
        }
        items[i].quality--;
    }

    private void handleBrie(int i) {
        if (items[i].quality < 50) {
            items[i].quality++;
        }
    }

    private void handlePass(int i) {
        if(isZeroQuality(i)) {
            return;
        }
        if (items[i].sellIn < 0) {
            items[i].quality = 0;
            return;
        }

        improveQuality(i);

        if (items[i].sellIn < 11) {
            improveQuality(i);
        }
        if (items[i].sellIn < 6) {
            improveQuality(i);
        }
    }

    private boolean isZeroQuality(int i) {
        if(items[i].quality == 0) {
            return true;
        }
        return false;
    }

    private void improveQuality(int i) {
        if(items[i].quality == 50) {
            return;
        }
        items[i].quality++;
    }
}
