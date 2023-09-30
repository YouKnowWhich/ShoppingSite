package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetItemBeans;
import model.ItemBean;
import model.ShoppingCartBean;

/**
 * 商品一覧ページ処理クラス.
 */
@WebServlet("/ShoppingServlet")
public class ShoppingServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
      
	/**
	 * コンストラクタ.
	 */
    public ShoppingServlet() {
        super();
    }
    
    /**
     * Getメソッドで呼び出された場合の処理.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {

		//セッション取得
		HttpSession session = request.getSession();	//現状のセッションを取得
    	
    	// 商品一覧を取得
		ArrayList<ItemBean> beanList = new GetItemBeans().getAllItem();
		// 商品一覧をセッションスコープの属性にセット
		session.setAttribute("ItemList", beanList);	
		
		// カートが未生成の場合、生成してセッションにセットする。
		ShoppingCartBean cartBean = (ShoppingCartBean)session.getAttribute("CartBean");
		if (cartBean == null) {
			cartBean = new ShoppingCartBean();
			session.setAttribute("CartBean", cartBean);
		}
		
		// 商品一覧画面に移動
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/itemList.jsp");
		rd.forward(request, response);
	}	
    
    /**
     * POSTメソッドで呼び出された場合の処理.
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}	
}