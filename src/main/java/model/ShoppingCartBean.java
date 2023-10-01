package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 買い物かごクラス
 */
public class ShoppingCartBean implements Serializable {
	
	private ArrayList<CartItemBean> cartItemBeanList;
	private boolean isPaymentCompleted; 
	/**
	 * デフォルトコンストラクタ
	 */
	public ShoppingCartBean() {
		cartItemBeanList = new ArrayList<CartItemBean>();
		isPaymentCompleted = false;
	}
	/**
	 * 買い物かごにItemを追加する。
	 * @param cartItemBean 買い物かごに入れる商品
	 */
	public void addCartItemBean(CartItemBean cartItemBean) {
		cartItemBeanList.add(cartItemBean);
	}
	/**
	 * カートから指定番目の商品を削除する。
	 * @param num 削除する商品の並び順
	 */
	public void removeCartItemBean(int num) {
		cartItemBeanList.remove(num);
	}
	
	/**
	 * カートから商品をすべて削除する。
	 */
	public void removeAllCartItemBean() {
		cartItemBeanList.clear();
	}
		
	
	/**
	 * カート内商品の合計購入金額を取得
	 * @return 合計販売金額
	 */
	public long getTotalAmount() {
		int amount = 0;
		for (CartItemBean cartItem:cartItemBeanList) {
			amount += cartItem.getAmount();
		}
		return amount;
	}
	/**
	 * カート内商品の合計購入点数を取得
	 * @return 合計販売点数
	 */
	public int getTotalCount() {
		int count = 0;
		for (CartItemBean cartItem:cartItemBeanList) {
			count += cartItem.getCount();
		}
		return count;
	}
	/**
	 * CartItemListのIteratorを返す。
	 * @return
	 */
	public Iterator<CartItemBean> getCartItemBeanList() {
		return cartItemBeanList.iterator();
	}
	public boolean isPaymentCompleted() {
		return isPaymentCompleted;
	}
	public void setPaymentCompleted(boolean isPaymentCompleted) {
		this.isPaymentCompleted = isPaymentCompleted;
	}
	
	
}
