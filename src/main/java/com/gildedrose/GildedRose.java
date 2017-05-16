package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    private Item[] items;
    
    public GildedRose(Item[] items) {
        this.items = items;
    }

    private String [] commonItem = new String[]{
    		"Aged Brie",
    		"Backstage passes to a TAFKAL80ETC concert"
    };
    
    private String [] legendaryItem = new String[]{
    		"Sulfuras, Hand of Ragnaros"
    };
    
    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
        	if (isContainInArray(commonItem, items[i].name)){
        		processIncreateQuality(items[i]);
            } else {
            	items[i].reduceQuality();
            }

        	items[i].reduceSellingPeriod();
        	
            if (items[i].isOutOfSellingPeriod()) {
            	processOutOfSellingPeriod(items[i]);
            }
        }
    }

	private void processIncreateQuality(Item item) {
		item.increaseQuality();
		if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
			//这一块暂时想不明白如何重构
			if (item.sellIn < 6) {
				item.increaseQuality(2);
		    } else if(item.sellIn >= 6 && item.sellIn < 11){
		        item.increaseQuality(1);
		    }
		}
	}
    
    private void processOutOfSellingPeriod(Item item){
        if (item.name.equals("Aged Brie")) {
        	item.increaseQuality();
        } else  {
	        item.reduceQuality();
	    }
        
	    if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) 
        	item.changeQualityToZero();
    }
    
    public boolean isContainInArray(String [] array, String targetValue) {
        return Arrays.asList(array).contains(targetValue);
    }
}
