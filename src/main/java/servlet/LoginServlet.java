package servlet;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginCheck;
import model.UserBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * ログイン状態確認
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserBean loginUser = (UserBean)session.getAttribute("LoginUser");
		RequestDispatcher dispatcher;
		//ログイン情報がのこっていれば/ログイン情報がのこっていれば商品一覧画面に遷移する。
		if (loginUser == null) {	
			dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		} else {
			dispatcher = request.getRequestDispatcher("/ShoppingServlet");
		}
		dispatcher.forward(request,response) ;
	}
	
	private final int MAX_INACTIVE_INTERVAL = 300;	//無操作自動ログアウト時間
	/**
	 * ログイン処理
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字コードセット
		request.setCharacterEncoding("UTF-8");
		//セッション取得
		HttpSession session = request.getSession();	//現状のセッションを取得
		session.setMaxInactiveInterval(MAX_INACTIVE_INTERVAL);
		//入力データ取得
		String userId = request.getParameter ("userId");
		String password = request.getParameter ("password"); 
		
		//ユーザIDとログインのチェック
		LoginCheck loginCheck = new LoginCheck();
		UserBean userBean = loginCheck.execute(userId,password);
		//画面遷移
		RequestDispatcher dispatcher;
		if (userBean == null) {	//ユーザなしまたはパスワードエラーの場合
			dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginError.jsp");
		} else {
			// ログイン情報をセッションスコープにセット
			session.setAttribute("LoginUser", userBean);
			dispatcher = request.getRequestDispatcher("/ShoppingServlet");
		}
		dispatcher.forward(request,response);
	}
}
