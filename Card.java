public class Card{
	private Suit suit; 
	//Definition: 1~4, Clubs=1, Diamonds=2, Hearts=3, Spades=4
	private int rank; 
	//1~13
	/**
	 * @param s suit
	 * @param r rank
	 */
	enum Suit {Clubs, Diamonds, Hearts, Spades}//四種花色
    public String[] Rank = {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};//12種rank
	public Card(Suit s,int r){
		suit=s;
		rank=r;
	}	
	//suit-->enum ; rank --> string;
	
	public void printCard(){
		//print suit+rank
        		System.out.println(suit+""+rank);
	}
	public Suit getSuit(){	
		return suit;
	}
	public int getRank(){
		return rank;
	}
}

	