package trump;

import java.util.ArrayList;
import java.util.Arrays;

public class Trumplogic {
	//手札格納変数
	public Trump[] hand=new Trump[5];
	//山札格納変数
	public ArrayList<Trump> fieldtrump=new ArrayList<Trump>();
	public Trump[] trumparray=new Trump[52];
	//成立役格納変数
	public String judge="";
	//成立役に対する配当
	public int add=0;
	//コンストラクタにて52枚一組のトランプを作成
	public Trumplogic(){
		int count1=0;
		for(int i=0;i<52;i++){
			trumparray[i]=new Trump();
		}
		for(int i=1;i<53;i++){
			if(i<=13){
				trumparray[count1].setSuit("スペード");
				trumparray[count1].setNum(i);
				count1++;
			}else if(i<=26){
				trumparray[count1].setSuit("クラブ");
				trumparray[count1].setNum(i-13);
				count1++;
			}else if(i<=39){
				trumparray[count1].setSuit("ダイヤ");
				trumparray[count1].setNum(i-26);
				count1++;
			}else{
				trumparray[count1].setSuit("ハート");
				trumparray[count1].setNum(i-39);
				count1++;
			}
		}
	}
	//新規山札実体化
	public void setfield(){
		fieldtrump.clear();
		for(int i=0;i<52;i++){
			fieldtrump.add(trumparray[i]);
		}
	}
	//5枚の手札を配布
	public void hand() {
		int num;
		for(int i=0;i<5;i++){
			num=(int)(Math.random()*(52-i));
			hand[i]=fieldtrump.get(num);
			fieldtrump.remove(num);
		}
	}
	//手札を交換
	public void chenge(String[] keep,Trump[] hand){
		int fieldnum=47;//52-5枚
		int randomnum;
		for(int i=0;i<5;i++){
			if(keep[i]==null){
				randomnum=(int)(Math.random()*fieldnum);
				hand[i]=fieldtrump.get(randomnum);
				fieldtrump.remove(randomnum);
				fieldnum--;
			}
		}
	}
	//役の判定
	public void judgelogic(Trump[] hand){
		int duplication=0;
		int[] num=new int[5];
		for(int i=0;i<5;i++){
			num[i]=hand[i].getNum();
		}
		Arrays.sort(num);
		boolean flash=false,straight=false;
		//スートが全て同じか判定
		if(hand[0].getSuit().equals(hand[1].getSuit())&&hand[1].getSuit().equals(hand[2].getSuit())&&
				hand[2].getSuit().equals(hand[3].getSuit())&&hand[3].getSuit().equals(hand[4].getSuit())){
			flash=true;
		}
		//数字が連番か判定
		if(num[0]==(num[1]-1)&&num[0]==(num[2]-2)&&num[0]==(num[3]-3)&&num[0]==(num[4]-4)){
			straight=true;
		}
		if(flash&&straight){
			judge="ストレートフラッシュ!";
		}else if(flash){
			judge="フラッシュ!";
		}else if(straight){
			judge="ストレート!";
		}else{
			//数字の重複回数を探索
			for(int i=0;i<hand.length;i++){
				for(int j=i+1;j<hand.length;j++){
					if(hand[i].getNum()==hand[j].getNum()){
						duplication++;
					}
				}
			}
			if(duplication==1){
				judge="ワンペア";
			}else if(duplication==2){
				judge="ツーペア";
			}else if(duplication==3){
				judge="スリーカード";
			}else if(duplication==4){
				judge="フルハウス!";
			}else if(duplication==6){
				judge="フォーカード!";
			}else{
				judge="ありません";
			}
		}
		if(judge.equals("ストレートフラッシュ!")){
			add=50;
		}else if(judge.equals("フォーカード!")){
			add=30;
		}else if(judge.equals("フルハウス!")){
			add=8;
		}else if(judge.equals("ストレート!")){
			add=10;
		}else if(judge.equals("フラッシュ!")){
			add=6;
		}else if(judge.equals("スリーカード")){
			add=3;
		}else if(judge.equals("ツーペア")){
			add=2;
		}else if(judge.equals("ワンペア")){
			add=1;
		}else if(judge.equals("ありません")){
			add=0;
		}
	}
}
