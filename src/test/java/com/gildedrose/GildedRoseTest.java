package com.gildedrose;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class GildedRoseTest {
	public static class DayInfo {
		List<String> itemInfos = new ArrayList<String>();
	}

	public static class ExceptedResult {
		List<DayInfo> dayInfos = new ArrayList<DayInfo>();

		public DayInfo getLastDayInfo() {
			return dayInfos.get(dayInfos.size() - 1);
		}

		public String getItemByDayIndex(int day, int itemIndex) {
			return dayInfos.get(day).itemInfos.get(itemIndex);
		}
	}

	ExceptedResult expectedResult = new ExceptedResult();

	@Before
	public void setup() throws IOException {
		BufferedReader isr = null;
		try {
			isr = new BufferedReader(
					new InputStreamReader(GildedRoseTest.class.getResourceAsStream("expected_result.txt")));
			String line = null;
			while ((line = isr.readLine()) != null) {
				if ("".equals(line.trim()) || line.equals("name, sellIn, quality") || line.equals("OMGHAI!")) {
					continue;
				}
				// sb.append(line).append("\r\n");
				if (line.contains("---") && line.contains("day")) {
					DayInfo di = new DayInfo();
					expectedResult.dayInfos.add(di);
				} else {
					expectedResult.getLastDayInfo().itemInfos.add(line);
				}
			}
		} finally {
			isr.close();
		}
	}

	@Test
	public void foo() {
		Item[] items = new Item[] { new Item("+5 Dexterity Vest", 10, 20), // 0
				new Item("Aged Brie", 2, 0), // 1
				new Item("Elixir of the Mongoose", 5, 7), // 2
				new Item("Sulfuras, Hand of Ragnaros", 0, 80), // 3
				new Item("Sulfuras, Hand of Ragnaros", -1, 80), // 4
				new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20), // 5
				new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49), // 6
				new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49), // 7
				// this conjured item does not work properly yet
				new Item("Conjured Mana Cake", 3, 6) };// 8

		GildedRose app = new GildedRose(items);
		for (int i = 1; i <= 99; i++) {
			app.updateQuality();
			for (int j = 0; j < items.length; j++) {
				Assert.assertEquals(expectedResult.getItemByDayIndex(i, j), items[j].toString());
			}
		}

	}

}
