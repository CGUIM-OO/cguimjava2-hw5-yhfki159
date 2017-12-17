import java.util.ArrayList;
import java.util.Random;

public class Deck{
	private ArrayList<Card> cards;
	private ArrayList<Card> usedCard;
	public int nUsed;
	private ArrayList<Card> openCard;//存放此副牌中所有打開的牌
	//~~
    public Deck(int nDeck){
    	//先讓cards;usedCard 在此使用
    	cards = new ArrayList<Card>();
    	usedCard=new ArrayList<Card>();
    	//設nDeck為給副牌的數量 
    	for(int n=0;n<nDeck;n++){
        for (Card.Suit s : Card.Suit.values()) {
            for (int r=1; r<=13; r++) {
            	cards.add(new Card(s, r));
            }
        }
        }
    	shuffle();//給完之後 洗牌
    } 
    //~~
    public Card getOneCard(boolean isOpened) {//取得一張牌的方法
        Card c = cards.get(0);//取第一張(0)並且設c為取得的牌~
        if(isOpened != true)//要開的牌才紀錄
		{
			openCard.add(c);
		}
		usedCard.add(c);//取完後丟入使用的牌(usedCard)
		nUsed++;//每拿一張 nUsed的數量+1
		//
		cards.remove(c);//從原本的那副牌刪掉 也就是 52張 因為被拿一張 所以剩51張~
		//
		if (cards.size() == 0)//取了52張 即 全部取完
		{
			shuffle();//洗牌
		}
		return c;
    }
    //~~
    public void shuffle() {//洗牌方法
    	if(usedCard.size() != 0)//當使用的牌不為0時 
		{
			for(Card c : usedCard)//從usedCard的裡面使用
			{
				cards.add(c);//新增一張牌(c)
			}
			usedCard.clear();////最後清除
			nUsed = 0;//清除之後nUsed=0
		}

		Random rnd = new Random();//隨機取牌 所要用的取得牌的參數
        for (int i = 0; i < cards.size(); i++) {
        	int j = rnd.nextInt(cards.size());
            Card temp = cards.get(j);
            cards.set(j, cards.get(i));
            cards.set(i, temp);
        }
    }
	//~~
	//
    public void printDeck(){
		for(Card c:cards)
		{
			c.printCard();//將取得的牌印出
		}
    }
	//取得並回傳
    public ArrayList<Card> getOpenedCard(){
    	return openCard;
    }
	public ArrayList<Card> getAllCards(){
		return cards;
	}
	public ArrayList<Card> getUsedCard() {
		return usedCard;
	}
	public void setUsedCard(ArrayList<Card> usedCard) {
		this.usedCard = usedCard;
	}
}