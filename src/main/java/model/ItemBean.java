package model;

import java.io.Serializable;
/**
 * 商品ビーンクラス
 */
public class ItemBean implements Serializable  {
	private String itemId;		//
	private String itemName;	//
	private int price;			//
	//デフォルトコンストラクタ
	public ItemBean() {	}
	//コンストラクタ
	public ItemBean(String itemId, String itemName,int price) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.price = price;
	}
	//アクセサメソッド
	public String getItemId() {
		return itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public int getPrice() {
		return price;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
