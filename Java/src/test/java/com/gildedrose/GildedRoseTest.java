package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {
    private static final String NORMAL = "foo";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";

    @Test
    void quality_and_sell_in_decreases_over_time() {
        Item[] items = new Item[] { new Item(NORMAL, 10, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(19, app.items[0].quality);
    }

    @Test
    void quality_degrades_twice_as_fast_after_sell_by_date_has_passed() {
        Item[] items = new Item[] { new Item(NORMAL, 0, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(18, app.items[0].quality);
    }

    @Test
    void sell_in_can_be_negative() {
        Item[] items = new Item[] { new Item(NORMAL, 0, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
    }

    @Test
    void quality_can_never_be_negative() {
        Item[] items = new Item[] { new Item(NORMAL, 10, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void quality_for_aged_brie_increase_the_older_it_gets() {
        Item[] items = new Item[] { new Item(AGED_BRIE, 10, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(21, app.items[0].quality);
    }

    @Test
    void quality_cannot_increase_over_50() {
        Item[] items = new Item[] { new Item(AGED_BRIE, 10, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void quality_for_aged_brie_increase_twice_as_fast_after_sell_by_date() {
        Item[] items = new Item[] { new Item(AGED_BRIE, 0, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(22, app.items[0].quality);
    }

    @Test
    void quality_for_aged_brie_increase_twice_as_fast_after_sell_by_date_but_does_not_go_over_50() {
        Item[] items = new Item[] { new Item(AGED_BRIE, 0, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void sulfuras_quality_never_decreases() {
        Item[] items = new Item[] { new Item(SULFURAS, 10, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, app.items[0].quality);
    }

    @Test
    void sulfuras_never_has_to_be_sold() {
        Item[] items = new Item[] { new Item(SULFURAS, 10, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(10, app.items[0].sellIn);
    }

    @Test
    void backstage_passes_increase_in_quality_when_there_is_more_than_10_days() {
        Item[] items = new Item[] { new Item(BACKSTAGE_PASS, 11, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(21, app.items[0].quality);
    }

    @Test
    void backstage_passes_increase_in_quality_by_2_when_there_is_10_days_or_less() {
        Item[] items = new Item[] { new Item(BACKSTAGE_PASS, 10, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(22, app.items[0].quality);
    }

    @Test
    void backstage_passes_increase_in_quality_by_3_when_there_is_5_days_or_less() {
        Item[] items = new Item[] { new Item(BACKSTAGE_PASS, 5, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(23, app.items[0].quality);
    }

    @Test
    void backstage_passes_cannot_have_more_than_50_quality_even_when_increases_by_2() {
        Item[] items = new Item[] { new Item(BACKSTAGE_PASS, 10, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void backstage_passes_cannot_have_more_than_50_quality_even_when_increases_by_3() {
        Item[] items = new Item[] { new Item(BACKSTAGE_PASS, 5, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void backstage_passes_quality_drops_to_0_after_sell_in_date() {
        Item[] items = new Item[] { new Item(BACKSTAGE_PASS, 0, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

}
