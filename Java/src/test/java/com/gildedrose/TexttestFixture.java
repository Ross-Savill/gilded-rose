package com.gildedrose;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TexttestFixture {

    @Test
    void outputTest() throws IOException {
        String actualOutput = runSystem();
        String expectedOutput = null;
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/output.txt"));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        try {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            expectedOutput = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
        Assertions.assertEquals(expectedOutput,actualOutput);
    }

    private static String runSystem() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("OMGHAI!");
        stringBuilder.append("\r\n");

        Item[] items = new Item[] {
                new Item("+5 Dexterity Vest", 10, 20), //
                new Item("Aged Brie", 2, 0), //
                new Item("Elixir of the Mongoose", 5, 7), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                // this conjured item does not work properly yet
                new Item("Conjured Mana Cake", 3, 6)
        };

        GildedRose app = new GildedRose(items);

        int days = 2;

        for (int i = 0; i < days; i++) {
            stringBuilder.append("-------- day " + i + " --------");
            stringBuilder.append("\r\n");
            stringBuilder.append("name, sellIn, quality");
            stringBuilder.append("\r\n");
            for (Item item : items) {
                stringBuilder.append(item);
                stringBuilder.append("\r\n");
            }
            stringBuilder.append("\r\n");
            app.updateQuality();
        }
        return stringBuilder.toString();
    }

    @Test
    void assertMinimumQualityAndDaysVest() {
        Item[] items = new Item[] {
            new Item("+5 Dexterity Vest", 0, 0)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Assertions.assertTrue(items[0].quality == 0 && items[0].sellIn == -1);
    }

    @Test
    void assertSulfurasStaysSame() {
        Item[] items = new Item[] {
            new Item("Sulfuras, Hand of Ragnaros", 10, 10)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Assertions.assertTrue(items[0].quality == 10 && items[0].sellIn == 10);
    }

    @Test
    void assertMaximumBrieQuality() {
        Item[] items = new Item[] {
            new Item("Aged Brie", 50, 50)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Assertions.assertTrue(items[0].quality == 50 && items[0].sellIn == 49);
    }

    @Test
    void assertTicketQualityUpByOne() {
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 30, 30)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Assertions.assertTrue(items[0].quality == 31 && items[0].sellIn == 29);
    }

    @Test
    void assertTicketQualityUpByTwo() {
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 30)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Assertions.assertTrue(items[0].quality == 32 && items[0].sellIn == 9);
    }

    @Test
    void assertTicketQualityUpByThree() {
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 30)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Assertions.assertTrue(items[0].quality == 33 && items[0].sellIn == 4);
    }

    @Test
    void assertTicketQualityMax() {
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 30, 50)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Assertions.assertTrue(items[0].quality == 50 && items[0].sellIn == 29);
    }

    @Test
    void assertTicketQualityDownToZero() {
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 0, 30)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Assertions.assertTrue(items[0].quality == 0 && items[0].sellIn == -1);
    }

    @Test
    void assertConjuredItemDecreasePreSellByDate() {
        Item[] items = new Item[] {
            new Item("Conjured Mana Cake", 10, 10)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Assertions.assertTrue(items[0].quality == 8 && items[0].sellIn == 9);
    }

    @Test
    void assertConjuredItemDecreasePostSellByDate() {
        Item[] items = new Item[] {
            new Item("Conjured Mana Cake", -1, 10)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Assertions.assertTrue(items[0].quality == 6 && items[0].sellIn == -2);
    }
}


