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
import trump.Trump;
import trump.Trumplogic;
import user.UserBean;

@WebServlet("/PokerServlet")
public class PokerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Trumplogic t=new Trumplogic();
	UserDao userdao=new UserDao();
	UserBean user;
	String name;
	int bet;
	RequestDispatcher dispatcher;
	String servlet;
	String reload="";
	//チェックボックスのチェック状況を格納する変数
	String[] keep=new String[5];

	public PokerServlet() {
        super();
    }

	//URLや履歴等直接リクエストされた場合
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		UserBean user=(UserBean)session.getAttribute("user");
		if(user==null){
			servlet="doget";
			session.setAttribute("servlet", servlet);
			dispatcher=request.getRequestDispatcher("/WEB-INF/login/login_error.jsp");
			dispatcher.forward(request, response);
		}else{
			dispatcher=request.getRequestDispatcher("/WEB-INF/poker/session_error.jsp");
			dispatcher.forward(request, response);
		}
	}

	//各画面へ遷移
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//transitionをリクエストから所得して分岐する
		String transition=request.getParameter("transition");
		HttpSession session=request.getSession();
		switch(transition){

		//ポーカー：配当表画面へ
		case"payout":
			//フォワード
			dispatcher=request.getRequestDispatcher("/WEB-INF/poker/payout.jsp");
			dispatcher.forward(request, response);
			break;

		//ポーカー：ベット画面へ
		case"bet":
			//ブラウザバックされた場合
			if(reload.equals("change")){
				reload="error";
				dispatcher=request.getRequestDispatcher("/WEB-INF/poker/session_error.jsp");
				dispatcher.forward(request, response);
				return;
			}
			reload="bet";
			user=(UserBean)session.getAttribute("user");
			int resultcoin=user.getCoin();
			session.setAttribute("resultcoin", resultcoin);
			//フォワード
			dispatcher=request.getRequestDispatcher("/WEB-INF/poker/bet.jsp");
			dispatcher.forward(request, response);
			break;

		//ポーカー：チェンジ画面へ
		case"change":
			//ベット画面以外から遷移した場合
			if(!reload.equals("bet")){
				reload="error";
				dispatcher=request.getRequestDispatcher("/WEB-INF/poker/session_error.jsp");
				dispatcher.forward(request, response);
				return;
			}else{
				reload="change";
			}
			String strbet=request.getParameter("bet");
			bet=Integer.parseInt(strbet);
			user=(UserBean)session.getAttribute("user");
			//coinの枚数よりbetが大きい場合、コイン不足画面へ遷移する
			if(user.getCoin()<bet){
				reload="";
				dispatcher=request.getRequestDispatcher("/WEB-INF/poker/shortage.jsp");
				dispatcher.forward(request, response);
				return;
			}
			session.setAttribute("bet",bet);
			//データベースからbet枚のcoinを減らす
			user=(UserBean)session.getAttribute("user");
			name=user.getName();
			userdao.betcoin(name,bet);
			//スコープ内のユーザーのアップデート
			user=userdao.updateUser(name);
			session.setAttribute("user", user);
			//トランプ1組を実体化し、5枚の手札を配布
			t.setfield();
			t.hand();
			//手札をセッションスコープに保存
			sethand(request);
			//フォワード
			dispatcher=request.getRequestDispatcher("/WEB-INF/poker/change.jsp");
			dispatcher.forward(request, response);
			break;

		//ポーカー：リザルト画面へ
		case"result":
			//チェンジ画面以外から遷移した場合
			if(!reload.equals("change")){
				reload="error";
				dispatcher=request.getRequestDispatcher("/WEB-INF/poker/session_error.jsp");
				dispatcher.forward(request, response);
				return;
			}else{
				reload="result";
			}
			//手札をセッションスコープから取得
			gethand(request);
			//チェックボックスのチェック状況を取得
			keep[0]=request.getParameter("keep0");
			keep[1]=request.getParameter("keep1");
			keep[2]=request.getParameter("keep2");
			keep[3]=request.getParameter("keep3");
			keep[4]=request.getParameter("keep4");
			//手札の交換を行う
			t.chenge(keep,t. hand);
			//交換後、手札をセッションスコープに保存
			sethand(request);
			//役の判定を行い、セッションスコープに保存
			t.judgelogic(t.hand);
			session.setAttribute("judge",t. judge);
			//獲得枚数get=bet*addをセッションスコープに保存
			bet=Integer.valueOf((Integer)session.getAttribute("bet"));
			int get=bet*t.add;
			session.setAttribute("get", get);
			//データベースのcoinをget枚増やす
			user=(UserBean)session.getAttribute("user");
			name=user.getName();
			userdao.addcoin(name,get);
			//スコープ内のユーザーのアップデート
			user=userdao.updateUser(name);
			session.setAttribute("user", user);
			resultcoin=user.getCoin();
			session.setAttribute("resultcoin", resultcoin);
			//フォワード
			dispatcher=request.getRequestDispatcher("/WEB-INF/poker/result.jsp");
			dispatcher.forward(request, response);
			break;
		}
	}
	/**
	 * 手札をセッションスコープに保存
	 */
	void sethand(HttpServletRequest request){
		HttpSession session=request.getSession();
		session.setAttribute("hand0", t.hand[0]);
		session.setAttribute("hand1", t.hand[1]);
		session.setAttribute("hand2", t.hand[2]);
		session.setAttribute("hand3", t.hand[3]);
		session.setAttribute("hand4", t.hand[4]);
	}
	/**
	 * 手札をセッションスコープから取得
	 */
	void gethand(HttpServletRequest request){
		HttpSession session=request.getSession();
		t.hand[0]=(Trump)session.getAttribute("hand0");
		t.hand[1]=(Trump)session.getAttribute("hand1");
		t.hand[2]=(Trump)session.getAttribute("hand2");
		t.hand[3]=(Trump)session.getAttribute("hand3");
		t.hand[4]=(Trump)session.getAttribute("hand4");

	}

}
