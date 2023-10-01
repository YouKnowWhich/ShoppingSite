package model;
/**
 * カート内商品クラス
 */
public class CartItemBean extends ItemBean {
	
	private int count;		//購入個数
	private int amount;	//count × price
	/**
	 * デフォルトコンストラクタ
	 */
	public CartItemBean() {}
	/**
	 * コンストラクタ
	 * @param itemId 商品コード
	 * @param itemName 商品名
	 * @param price 価格
	 * @param count 購入点数
	 * @param amount 購入金額
	 */
	public CartItemBean(ItemBean itemBean, int count) {
		super(itemBean.getItemId(), itemBean.getItemName(), itemBean.getPrice());
		this.count = count;
		this.amount = itemBean.getPrice() * count;
	}
	//アクセサメソッド
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
