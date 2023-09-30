package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * UserDAOクラス.
 */
public class UserDao {

	private Connection con = null;
	private ResultSet  rs  = null;
	private PreparedStatement ps = null;
	
	/**
	 * データベースから指定されたIDとパスワードを使ってユーザ情報を検索します.
	 * @param userId	ログインID
	 * @param pass	パスワード
	 * @return	ユーザ情報（ResultSet）
	 * @throws SQLException
	 */
	public ResultSet checkUser(String userId, String password) throws SQLException {
	
		try {
			
			// JDBCドライバのロード
			// 「com.mysql.cj.jdbc.Driver」クラス名
			Class.forName("com.mysql.cj.jdbc.Driver");
	
			// データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
			con = DriverManager.getConnection(DaoParam.URL,DaoParam.USER,DaoParam.PASSWORD);
			
			// SQL文を生成
			ps = con.prepareStatement("select usercode,username from user where userid = ? and password = ?");
			
			// 生成したSQL文の「？」の部分にIDとパスワードをセット
			ps.setString(1, userId);
			ps.setString(2, password);
			
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
