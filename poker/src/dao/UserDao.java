package dao;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import user.UserBean;

public class UserDao extends Dao{
	/**
     * ユーザー名とパスワードを指定してユーザーテーブルからユーザー情報を取得する
     * @return ユーザーの情報を格納したユーザーBean
     */
    public UserBean getUser(String name,String password) {
    	PreparedStatement pstmt = null;
    	UserBean user = null;
    	//SQL文：ユーザ―名とパスワードが一致する特定のユーザーをselect
        String strSql = "select * from poker.user where name=? and password=?";
        //データベースへの接続
        open();
        //ユーザー名とパスワードをSQL文へ
        try{
        	pstmt=conn.prepareStatement(strSql);
        	pstmt.setString(1, name);
        	pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            //userを取得
            if (rs.next()) {
                user = new UserBean();
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setCoin(rs.getInt("coin"));
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                pstmt.close();
                conn.close();
                conn=null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }
    /**
     * ユーザー名を指定してユーザーテーブルからユーザー情報を取得する
     * @return ユーザーの情報を格納したユーザーBean
     */
    public UserBean updateUser(String name) {
    	PreparedStatement pstmt = null;
    	UserBean user = null;
    	//SQL文：特定のユーザーをselect
        String strSql = "select * from poker.user where name=?";
      //データベースへの接続
        open();
        //ユーザー名をSQL文へ
        try{
        	pstmt=conn.prepareStatement(strSql);
        	pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            //userを取得
            if (rs.next()) {
                user = new UserBean();
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setCoin(rs.getInt("coin"));
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                pstmt.close();
                conn.close();
                conn=null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }
    /**
     * ユーザーテーブルへ指定ユーザーの情報を追加する
     * @return boolean型adduser 成功時true
     */
    public Boolean addUser(String name,String password) {
    	Boolean adduser=true;
        PreparedStatement pstmt = null;
        //データベースへの接続
        open();
        //SQL文：userテーブルへインサート
        String strSql = "INSERT INTO poker.user (name,password,coin)"
        		+ " VALUES(?,?,100)";
        try{
        	pstmt=conn.prepareStatement(strSql);
        	pstmt.setString(1,name);
        	pstmt.setString(2, password);
        	pstmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            //既にユーザー名がテーブル内に存在していた場合をキャッチ
            adduser=false;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                pstmt.close();
                conn.close();
                conn=null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return adduser;
    }


    /**
     * ユーザー名とbetを指定して coin = coin-bet を行う
     *
     */
    public void betcoin(String name,int bet){
    	PreparedStatement pstmt = null;
    	int setcoin=returnbetcoin(name,bet);
    	//データベースへの接続
        open();
    	//SQL文：ユーザーを指定しcoin=coin-betを行う
        String strSql = "update poker.user set coin=? where name=?";
        //ユーザー名とbet枚数をSQL文へ
        try{
        	pstmt=conn.prepareStatement(strSql);
        	pstmt.setInt(1, setcoin);
        	pstmt.setString(2, name);
        	pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                pstmt.close();
                conn.close();
                conn=null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * ユーザー名とbetを指定して coin = coin+add を行う
     *
     */
    public void addcoin(String name,int add){
    	PreparedStatement pstmt = null;
    	int setcoin=returnaddcoin(name,add);
    	//データベースへの接続
        open();
    	//SQL文：ユーザーを指定しcoin=coin+addを行う
        String strSql = "update poker.user set coin=? where name=?";
        //ユーザー名とbet枚数をSQL文へ
        try{
        	pstmt=conn.prepareStatement(strSql);
        	pstmt.setInt(1, setcoin);
        	pstmt.setString(2, name);
        	pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                pstmt.close();
                conn.close();
                conn=null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * ユーザー名とbetを指定して coin-bet を取得する
     * @return int setcoin = coin-bet
     */
    public int returnbetcoin(String name,int bet){
    	PreparedStatement pstmt = null;
    	int setcoin = 0;
    	//データベースへの接続
        open();
    	//SQL文：ユーザーを指定しcoin-betを取得する
        String strSql = "SELECT coin-? FROM poker.user where name=?";
        //ユーザー名とbet枚数をSQL文へ
        try{
        	pstmt=conn.prepareStatement(strSql);
        	pstmt.setInt(1, bet);
        	pstmt.setString(2, name);
        	ResultSet rs = pstmt.executeQuery();
        	//coin - bet を取得する
            if (rs.next()) {
            	String sql="coin-"+bet;
            	setcoin=rs.getInt(sql);
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                pstmt.close();
                conn.close();
                conn=null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return setcoin;
    }
    /**
     * ユーザー名とbetを指定して coin+add を取得する
     * @return int setcoin = coin-bet
     */
    public int returnaddcoin(String name,int add){
    	PreparedStatement pstmt = null;
    	int setcoin = 0;
    	//SQL文：ユーザーを指定しcoin+addを取得する
        String strSql = "SELECT coin+? FROM poker.user where name=?";
        //データベースへの接続
        open();
        //ユーザー名とadd枚数をSQL文へ
        try{
        	pstmt=conn.prepareStatement(strSql);
        	pstmt.setInt(1, add);
        	pstmt.setString(2, name);
        	ResultSet rs = pstmt.executeQuery();
        	//coin+add を取得する
            if (rs.next()) {
            	String sql="coin+"+add;
            	setcoin=rs.getInt(sql);
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                pstmt.close();
                conn.close();
                conn=null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return setcoin;
    }
}
