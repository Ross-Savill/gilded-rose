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
        String line = null;
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
                new Item("Conjured Mana Cake", 3, 6) };

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
}


