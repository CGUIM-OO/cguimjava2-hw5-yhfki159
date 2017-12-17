import java.util.ArrayList;
import java.util.Random;

public class Deck{
	private ArrayList<Card> cards;
	private ArrayList<Card> usedCard;
	public int nUsed;
	private ArrayList<Card> openCard;//�s�񦹰ƵP���Ҧ����}���P
	//~~
    public Deck(int nDeck){
    	//����cards;usedCard �b���ϥ�
    	cards = new ArrayList<Card>();
    	usedCard=new ArrayList<Card>();
    	//�]nDeck�����ƵP���ƶq 
    	for(int n=0;n<nDeck;n++){
        for (Card.Suit s : Card.Suit.values()) {
            for (int r=1; r<=13; r++) {
            	cards.add(new Card(s, r));
            }
        }
        }
    	shuffle();//�������� �~�P
    } 
    //~~
    public Card getOneCard(boolean isOpened) {//���o�@�i�P����k
        Card c = cards.get(0);//���Ĥ@�i(0)�åB�]c�����o���P~
        if(isOpened != true)//�n�}���P�~����
		{
			openCard.add(c);
		}
		usedCard.add(c);//�������J�ϥΪ��P(usedCard)
		nUsed++;//�C���@�i nUsed���ƶq+1
		//
		cards.remove(c);//�q�쥻�����ƵP�R�� �]�N�O 52�i �]���Q���@�i �ҥH��51�i~
		//
		if (cards.size() == 0)//���F52�i �Y ��������
		{
			shuffle();//�~�P
		}
		return c;
    }
    //~~
    public void shuffle() {//�~�P��k
    	if(usedCard.size() != 0)//��ϥΪ��P����0�� 
		{
			for(Card c : usedCard)//�qusedCard���̭��ϥ�
			{
				cards.add(c);//�s�W�@�i�P(c)
			}
			usedCard.clear();////�̫�M��
			nUsed = 0;//�M������nUsed=0
		}

		Random rnd = new Random();//�H�����P �ҭn�Ϊ����o�P���Ѽ�
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
			c.printCard();//�N���o���P�L�X
		}
    }
	//���o�æ^��
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