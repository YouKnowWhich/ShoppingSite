package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ItemDao;

/**
 * 必要なItemDaoをItemBeanのクラスで一部または全部を取得するクラス
 */
public class GetItemBeans {
	
	/**
	 * 商品一覧のリストを返却します.
	 * @return	商品一覧のリスト
	 */
	public ArrayList<ItemBean> getAllItem() {
		
		ArrayList<ItemBean> beanList = new ArrayList<ItemBean>();
		ItemDao dao = null;
		ResultSet rs = null;
		
		try {
			// DAOクラスをインスタンス化
			dao = new ItemDao();
			// 現在の商品一覧を検索
			rs  = dao.selectAllItem();
			// 検索結果を1レコードずつ処理
			while(rs.next()) {
				// 商品一覧を格納するBeanクラスをインスタンス化
				ItemBean bean = new ItemBean();
				// 商品IDを設定
				bean.setItemId(rs.getString("item_id"));
				// 商品名を設定
				bean.setItemName(rs.getString("item_name"));
				// 商品価格を設定
				bean.setPrice(rs.getInt("price"));
				// Beanクラスをリストに追加
				beanList.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {	
			// 処理終了時に各接続を解除
			dao.close();
		}
		return beanList;
	}
	
	
	/**
	 * 商品IDを基に商品情報を返却します.
	 * @return	商品情報
	 */
	public ItemBean getItem(String itemId) {
		
		ItemBean bean = new ItemBean();
		ItemDao dao = null;
		ResultSet rs = null;
		
		try {
			// DAOクラスをインスタンス化
			dao = new ItemDao();
			// 現在の商品一覧を検索
			rs  = dao.selectItem(itemId);
			// 検索結果を処理
			while(rs.next()) {
				// 商品IDを設定
				bean.setItemId(itemId);
				// 商品名を設定
				bean.setItemName(rs.getString("item_name"));
				// 商品価格を設定
				bean.setPrice(rs.getInt("price"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 処理終了時に各接続を解除
			dao.close();
		}

		return bean;
	}

}