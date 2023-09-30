package servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartItemBean;
import model.GetItemBeans;
import model.ItemBean;
import model.ShoppingCartBean;
import model.UserBean;

/**
 * ショッピングカートへのオペレーションクラス
 */
@WebServlet("/CartOperationServlet")
public class CartOperationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    public static final String ADD_BUTTON_NAME = "買い物かごへ";
    public static final String REMOVE_BUTTON_NAME = "買い物かごから削除";
    public static final String GOTO_PAYMENT = "現金で決済する";
    public static final String GOTO_SHOPPING = "買い物を続ける";
    public static final String GOTO_NEW_SHOPPING = "再度買い物を続ける";
	/**
	 * コンストラクタ.
	 */
    public CartOperationServlet() {
        super();
    }
    /**
     * Getメソッドで呼び出された場合の処理.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		//文字コードセット
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();	//現状のセッションを取得
		if (session == null) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/logout.jsp");
			rd.forward(request, response);
		}
		ShoppingCartBean cartBean = (ShoppingCartBean)session.getAttribute("CartBean");
		//支払い済の場合、カートの商品を全て削除する。
		if (cartBean.isPaymentCompleted()) {
			cartBean.removeAllCartItemBean();
			cartBean.setPaymentCompleted(false);	//未決済状態に戻す
		}
		// 商品一覧画面に移動
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/cartList.jsp");
		rd.forward(request, response);
	}	
    /**
     * POSTメソッドで呼び出された場合の処理（買い物かごに追加）
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//文字コードセット
		request.setCharacterEncoding("UTF-8");
		
		RequestDispatcher requestDispatcher = null;		//セッション取得
		HttpSession session = request.getSession();	//現状のセッションを取得
		//ログインユーザ情報を取得（暗黙オブジェクトsession)
		UserBean userBean = (UserBean)session.getAttribute("LoginUser");

		if (userBean != null) { //ログイン状態
			// 『買い物かごへ』ボタンが押された場合、商品IDを得る
			String itemId = getNameFromValue(request,ADD_BUTTON_NAME);
			if (null != itemId) {
				int quantity = Integer.parseInt(request.getParameter(itemId + "counter"));
				if (quantity != 0) {	//購入商品数が0でない場合
					//購入した商品のItemBeanを取得するため、商品一覧オブジェクトを取得
					ItemBean itemBean = new GetItemBeans().getItem(itemId);
					// セッションからカート情報を取得
					ShoppingCartBean cartBean = (ShoppingCartBean)session.getAttribute("CartBean");
					if (cartBean == null) { //// カートが未生成の場合、カートを生成する。
						cartBean = new ShoppingCartBean();
					}
					// CartItemBeanを生成してcartBeanに追加し、セッションにセット
					cartBean.addCartItemBean(new CartItemBean(itemBean,quantity));
					// カートをセッションに保存する
					session.setAttribute("CartBean", cartBean);
					requestDispatcher = request.getRequestDispatcher("/ShoppingServlet");
				}	
			} 
			
			// 『買い物から削除』ボタンが押された場合、リスト内の何行目かを得る
			String lineId = getNameFromValue(request,REMOVE_BUTTON_NAME);
			if (null != lineId) {
				ShoppingCartBean cartBean = (ShoppingCartBean)session.getAttribute("CartBean");
				// 『買い物から削除』の商品をカートから削除
				cartBean.removeCartItemBean(Integer.parseInt(lineId));
				// 商品を削除したセッションをセット
				session.setAttribute("CartBean", cartBean);
				requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/cartList.jsp");
			}
			// ショッピングを続ける（商品一覧画面にもどる）が押された場合
			String value = getNameFromValue(request,GOTO_SHOPPING);
			if (null != value) {
				requestDispatcher = request.getRequestDispatcher("/ShoppingServlet");
			}
			// 支払い画面へ（決済画面に移動）が押された場合
			value = getNameFromValue(request,GOTO_PAYMENT);
			if (null != value) {
				requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/finished.jsp");
				ShoppingCartBean cartBean = (ShoppingCartBean)session.getAttribute("CartBean");
				cartBean.setPaymentCompleted(true);	//支払い終了フラグON
			}
			// 再度買い物を続ける場合
			value = getNameFromValue(request,GOTO_NEW_SHOPPING);
			if (null != value) {
				//カート情報削除
				session.removeAttribute("CartBean");
				requestDispatcher = request.getRequestDispatcher("/ShoppingServlet");
			}
		} else { //ログイン状態でない
			requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		}
		requestDispatcher.forward(request, response);
	}
	/**
	 * リクエストパラメータの中に、指定したvalueのパラメータがあればそのパラメータのnameを返す。
	 * @param request
	 * @param value
	 * @return name
	 */
	protected String getNameFromValue(HttpServletRequest request,String value) {
		String ret = null;
		// パラメータ名を順番に取得
		Enumeration<String> names = request.getParameterNames();
		
		while(names.hasMoreElements()) {
			String name = names.nextElement();
			if(value.equals((String)request.getParameter(name))) {
				ret = name;
			}
		}
		return ret; 
	}
}