import java.util.ArrayList;

public class Table {
	final int MAXPLAYER = 4;
	//
	private Deck deck;
	private Player[] player; 
	private Dealer dealer;
    //
	int[] pos_betArray;
	//
	public Table(int nDeck)
	{
		Deck deck = new Deck(nDeck);
		Player[] player = new Player[MAXPLAYER];//宣告一個長度是MAXPLAYER 的Player array
		player[0] =null;
		player[1] =null;
		player[2] =null;
		player[3] =null;
		//請初始化上述新增型別為Player[]的變數(instance field)
	}
	public void set_player(int pos/*牌桌位置*/, Player p/*Player instance*/){
	//將Player放到牌桌上 (即Player[]的變數為setter)，最多MAXPLAYER人。
			player[pos]=p;
	}
		     
	public Player[] get_player(){
		return player;
	}
		     //回傳所有在牌桌上的player為getter
	public void set_dealer(Dealer d){
		dealer =d; //將Dealer放到牌桌上 (即Dealer為變數之setter)
	} 
	public Card get_face_up_card_of_dealer(){
		distribute_cards_to_dealer_and_players();
		return dealer.getOneRoundCard().get(1);
	}
		    //- 回傳dealer打開的那張牌，也就是第二張牌
	private void ask_each_player_about_bets(){
		for (int i = 0;i<MAXPLAYER;i++) {
			//
			player[i].sayHello();//請每個玩家打招呼
		    player[i].makeBet();//請每個玩家下注
		    pos_betArray[i]=player[i].makeBet();//下的注要存在pos_betArray
		}
		//
	}
	private void distribute_cards_to_dealer_and_players(){
		for (int i = 0;i<MAXPLAYER;i++) {
			ArrayList<Card> playercards=new ArrayList<Card>();//- 發牌給玩家跟莊家，先發兩張打開的牌給玩家
			playercards.add(deck.getOneCard(true));
			playercards.add(deck.getOneCard(true));
			player[i].setOneRoundCard(playercards);//兩個位子 用setOneRoundCard塞
		}
		ArrayList<Card> playercards2=new ArrayList<Card>(); //- 再一張蓋著的牌，以及一張打開的牌給莊家。
		playercards2.add(deck.getOneCard(false));
		playercards2.add(deck.getOneCard(true));
		dealer.setOneRoundCard(playercards2);
		//
		System.out.print("Dealer's face up card is ");	
		get_face_up_card_of_dealer().printCard();
		//- 發牌給莊家後，在畫面上印出莊家打開的牌"Dealer's face up card is " (提示: printCard())
	}
		    
	private void ask_each_player_about_hits(){
		
		for (int i = 0;i<MAXPLAYER;i++) {
		player[i].hit_me(this);
		boolean hit=false;
		//
		do{
			ArrayList<Card> playercards=new ArrayList<Card>();
			hit=player[i].hitMe(this); //this
			if(hit){
				playercards.add(deck.getOneCard(true));
				player[i].setOneRoundCard(playercards);
				System.out.print("Hit! ");
				System.out.println(player[i].getName()+"'s Cards now:");
				for(Card c : playercards){
					c.printCard();
				}
			}
			else{
				System.out.println(player[i].getName()+", Pass hit!");
				System.out.println(player[i].getName()+", Final Card:");
				for(Card c : playercards){
					c.printCard();
				}
			}
		  }while(hit);
		//
		//- 問每個玩家要不要牌 (提示: hit_me(Table tbl))
	    //- 請參照HW4.java，使用do while迴圈詢問各個玩家
	    //- 詢問順序: 按照加入牌局的順序而定 (位置)
	    //- 如果玩家要牌，請在畫面上印出"Hit! "+ 玩家的名字+ "’s cards now: "，並把玩家要牌後的完整牌印出。最後將新的牌用setOneRoundCard()設定回玩家物件。
	    //- 如果爆了，請不要再問玩家是否要牌
	    //- 如果玩家不要牌了，請在畫面上印出 玩家的名字+"Pass hit!"
		}
		
	}
	private void ask_dealer_about_hits(){
		for (int i = 0;i<MAXPLAYER;i++) {
		dealer.hit_me(this);
		boolean hit2=false;
		do{
			ArrayList<Card> playercards2=new ArrayList<Card>();
			hit2=player[i].hitMe(this); //this
			if(hit2){
				playercards2.add(deck.getOneCard(true));
				player[i].setOneRoundCard(playercards2);
				System.out.print("Hit! ");
				System.out.println(player[i].getName()+"'s Cards now:");
				for(Card c : playercards2){
					c.printCard();
				}
			}
			else{
				System.out.println(player[i].getName()+", Pass hit!");
				System.out.println(player[i].getName()+", Final Card:");
				for(Card c : playercards2){
					c.printCard();
				}
			}
		  }while(hit2);
		}
		System.out.print("Dealer's hit is over!");//- 詢問莊家是否要牌，完成後，印出"Dealer's hit is over!"
	}
		    
	private void calculate_chips(){
		System.out.print("Dealer's card value is \"+dealer.getTotalValue()+\" ,Cards:\"+dealer.printAllCard()");
		if (dealer.getTotalValue()>player[MAXPLAYER].getTotalValue()) {
		
		}
		
	}
		    //- 印出莊家的點數和牌"Dealer's card value is "+總點數+" ,Cards:"+牌們 (提示: printAllCard())
		    //- 莊家跟每一個玩家的牌比
		    //- 針對每個玩家，先印出 玩家姓名+" card value is "+玩家總點數
		    //- 看誰贏，要是莊家贏，把玩家籌碼沒收，印出", Loss "+下注籌碼數+" Chips, the Chips now is: "+玩家最新籌碼數(提示: get_current_chips())
		    //- 要是莊家輸，則賠玩家與下注籌碼相符之籌碼，印出",Get "+下注籌碼數+" Chips, the Chips now is: "+玩家最新籌碼數(提示: get_current_chips())
		    //- 不輸不贏，印出",chips have no change! The Chips now is: "+玩家最新籌碼數(提示: get_current_chips())
	public int[] get_players_bet(){
		 return pos_betArray;
	}
	/*新增一method play()如下*/
	public void play(){
				ask_each_player_about_bets();
				distribute_cards_to_dealer_and_players();
				ask_each_player_about_hits();
				ask_dealer_about_hits();
				calculate_chips();
	}
}
