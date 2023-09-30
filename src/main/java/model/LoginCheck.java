package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.UserDao;


/**
 * ログインチェックを行うクラス
 * @author k-yoneda
 *
 */

public class LoginCheck {

	UserDao userDao = null;

	/**
	 * コンストラクタ
	 */
	public LoginCheck() {
		userDao = new UserDao();
	}
	
	/**
	 * userテーブルにuserIdとpasswordをもつUserBeanオブジェクトを返し、NGの場合NULLを返す
	 * 
	 * @param userId
	 * @param password
	 * @return ログインOK:UserBean NG:NULL
	 * 
	 */
	public UserBean execute(String userId, String password) {
		//yu-zhttp://localhost:8080/ShoppingSite/LoginServletaパスワードチェック
		UserBean userBean = null;
		
		try {
			ResultSet rs = userDao.checkUser(userId, password);
			//対象が仮に複数あっても、最初にマッチしたユーザを使用する。
			if (rs != null && rs.next()) {
				String userCode = rs.getString("usercode");
				String userName = rs.getString("username");
				userBean = new UserBean(userId,userCode,userName);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			userDao.close();
		}
		return userBean;
	}
}
