import java.util.ArrayList; 

public class Player /*extends Card*/{//�_�l
	//���]3�Ѽ�
	private String name;//�W�l
	private int chips;//�w�X
	private int bet;//��`���B
	private ArrayList<Card> oneRoundCard;//�������d
    //
	public Player(String name, int chips) {//player���W�l+�w�X
		oneRoundCard=new ArrayList<Card>();
		this.name=name;
		this.chips=chips;
	}
	public String getName() {//���o�W�l
		return name;
	}
	public int makeBet(){//�c���w�X
		if(chips != 0) 
		{
			bet = bet;
		}
		else {
			bet = 0;
		}
		return bet;
	}
	public void setOneRoundCard(ArrayList<Card> cards){//�]�w���P���ұo�쪺�d
		oneRoundCard = cards;
	}
	public boolean hitMe(){//�O�_�n�P�A�O�^��true�A���A�n�P�h�^��false
		if (getTotalValue()<=16)
			return true;//16�I�H�U�n�P
		else
			return false;//17�I�H�W���n�P
	}
	public int getTotalValue(){
		int countfortotal = 0;
		//�^�Ǧ��P���ұo���d�I�ƥ[�`
		for(Card c : oneRoundCard) 
		{
			if(c.getRank()<10) 
			{
				countfortotal += c.getRank();
			}
			else {
				countfortotal += 10;
			}
		}
		return countfortotal;
	}
	public int getCurrentChips() {
		return chips;//�^�Ǫ��a�{���w�X
	}
	public void increaseChips (int diff){//���a�w�X�ܰ�
		diff = bet;
	}
	public void sayHello(){//Say Hello
        System.out.println("Hello, I am " + name + ".");
        System.out.println("I have " + chips + " chips.");
	}
}
