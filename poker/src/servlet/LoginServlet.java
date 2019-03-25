package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import user.UserBean;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserBean user = null;
	UserDao userdao=new UserDao();
	RequestDispatcher dispatcher;
	String servlet;

    public LoginServlet() {
        super();
    }

    //URLや履歴等直接リクエストされた場合
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		servlet="doget";
		HttpSession session = request.getSession();
		session.setAttribute("servlet", servlet);
		dispatcher=request.getRequestDispatcher("/WEB-INF/login/login_error.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		servlet="dopost";
        String transition = request.getParameter("transition");
        HttpSession session = request.getSession();
        UserBean user=new UserBean();
        switch (transition) {

      //セッションを破棄し、ログイン画面へ
        case"loginform":
			session.invalidate();
			dispatcher=request.getRequestDispatcher("/WEB-INF/login/loginform.jsp");
    		dispatcher.forward(request, response);
        	break;

		//メニュー画面へ
		case"menu":
			//フォワード
			dispatcher=request.getRequestDispatcher("/WEB-INF/login/menu.jsp");
    		dispatcher.forward(request, response);
			break;

		//新規登録操作を行い、登録結果画面へ
		case"adduser_process":
			String name=request.getParameter("name");
			String password=request.getParameter("password");
			//新規登録操作を行う。 adduser=  成功→true  失敗→false
			Boolean adduser=userdao.addUser(name, password);
			session.setAttribute("adduser", adduser);
			//フォワード
			dispatcher=request.getRequestDispatcher("/WEB-INF/login/adduser_result.jsp");
    		dispatcher.forward(request, response);
			break;
		//新規登録画面へ
		case"adduser":
			//フォワード
			dispatcher=request.getRequestDispatcher("/WEB-INF/login/adduser.jsp");
    		dispatcher.forward(request, response);
			break;

		//ログイン画面からメニュー画面へ
        case "login_process":
        	//formに入力されたユーザー名とパスワードを取得
        	name=request.getParameter("name");
        	password=request.getParameter("password");
    		user=userdao.getUser(name,password);
    		//データベースからuserを取得できたかどうか判定
    		if (user != null) {
    			session.setAttribute("user", user);
        		dispatcher=request.getRequestDispatcher("/WEB-INF/login/menu.jsp");
        		dispatcher.forward(request, response);
            }else {
            	session.setAttribute("servlet", servlet);
            	dispatcher=request.getRequestDispatcher("/WEB-INF/login/login_error.jsp");
        		dispatcher.forward(request, response);
            }
        }
	}
}
