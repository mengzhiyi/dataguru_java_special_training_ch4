package com.gildedrose;

public class Item {

	public String name;
	public int sellIn;
	public int quality;

	public final int MAX_QUALITY = 50;
    public final int MIN_QUALITY = 0;
    
    private final int MIN_SELLING_PERIOD = 0;
    
	
    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

   @Override
   public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
   }
   
   public void increaseQuality(){
	   if(isCommonItem()){
		   if(this.quality < MAX_QUALITY)
			   this.quality++;
	   }
   }

   public void increaseQuality(int inteval){
	   if(isCommonItem()){
		   if(this.quality < MAX_QUALITY)
			   this.quality += inteval;
	   }
   }
   
   public void reduceQuality(){
	   if (isCommonItem()){
		   if(this.quality > MIN_QUALITY)
			   this.quality--;
	   }
   }
   
   public void changeQualityToZero(){
	   this.quality = 0;
   }
   
   public boolean isCommonItem(){
	   return this.quality <= MAX_QUALITY && this.quality >= MIN_QUALITY;
   }
   
   public boolean isLegendaryItem(){
	   return this.quality > MAX_QUALITY;
   }
   
   public void reduceSellingPeriod(){
	   if(isCommonItem())
		   this.sellIn--;
   }
   
   public boolean isOutOfSellingPeriod(){
	   return sellIn < MIN_SELLING_PERIOD;
   }  
}