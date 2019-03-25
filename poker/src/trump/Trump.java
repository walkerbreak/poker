package trump;

import java.io.Serializable;

public class Trump implements Serializable{
	private String suit;
	private int num;
	public Trump(){}
	public String getSuit() {
		return suit;
	}
	public void setSuit(String suit) {
		this.suit = suit;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
}
