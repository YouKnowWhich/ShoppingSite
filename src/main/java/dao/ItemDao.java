package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ItemDAOクラス.
 */
public class ItemDao {

	private Connection con = null;
	private ResultSet  rs  = null;
	private PreparedStatement ps = null;
	
	/**
	 * データベースから全ての商品情報を取得
	 * @return	ユーザ情報（ResultSet）
	 * @throws SQLException
	 */
	public ResultSet selectAllItem() throws SQLException {
	
		try {
			
			// JDBCドライバのロード
			// 「com.mysql.cj.jdbc.Driver」クラス名
			Class.forName("com.mysql.cj.jdbc.Driver");
	
			// データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
			con = DriverManager.getConnection(DaoParam.URL,DaoParam.USER,DaoParam.PASSWORD);

			// SQL文を生成
			ps = con.prepareStatement("select * from item");
			

			// SQLを実行
			rs = ps.executeQuery();
			
		} catch(ClassNotFoundException ce) {
			
			// JDBCドライバが見つからなかった場合
			ce.printStackTrace();
		}
		
		return rs;
	}
	
	/**
	 * データベースから引数で渡される商品IDの商品情報を取得
	 * @param  商品ID itemId 
	 * @return	ユーザ情報（ResultSet）
	 * @throws SQLException
	 */
	public ResultSet selectItem(String itemId) throws SQLException {
	
		try {
			
			// JDBCドライバのロード
			// 「com.mysql.cj.jdbc.Driver」クラス名
			Class.forName("com.mysql.cj.jdbc.Driver");
	
			// データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
			con = DriverManager.getConnection(DaoParam.URL,DaoParam.USER,DaoParam.PASSWORD);
			
			// SQL文を生成
			ps = con.prepareStatement("select * from item where item_id = ?");
			//ps = con.prepareStatement("select * from item ");
			// 生成したSQL文の「？」の部分にIDとパスワードをセット
			
			ps.setString(1, itemId);

			// SQLを実行
			rs = ps.executeQuery();
			
		} catch(ClassNotFoundException ce) {
			
			// JDBCドライバが見つからなかった場合
			ce.printStackTrace();
		}
		
		return rs;
	}
	
	/**
	 * コネクションをクローズします.
	 */
	public void close() {
		
		try {
			
			// データベースとの接続を解除する
			if(con != null) {
				con.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(rs != null) {
				rs.close();
			}
			
		} catch (SQLException se) {
			
			// データベースとの接続解除に失敗した場合
			se.printStackTrace();
		}
	}
}
