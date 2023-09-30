package model;

import java.io.Serializable;
/**
 * ユーザビーンクラス
 */
public class UserBean implements Serializable  {
	private String userId;		//userID
	private String userCode;	//社員番号
	private String userName;	//氏名
	//デフォルトコンストラクタ
	public UserBean() {	}
	//コンストラクタ
	public UserBean(String userId, String userCode,String userName) {
		this.userId = userId;
		this.userCode = userCode;
		this.userName = userName;
	}
	//アクセサメソッド
	public String getUserId() {
		return userId;
	}
	public String getUserCode() {
		return userCode;
	}
	public String getUserName() {
		return userName;
	}
}
