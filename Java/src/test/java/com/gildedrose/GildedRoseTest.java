package com.gildedrose;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }
    @Test
    void outputAssertion() throws IOException {
        String actualOutput = null;
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/output.txt"));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        try {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            actualOutput = stringBuilder.toString();
        } finally {
            reader.close();
        }
    }

}
