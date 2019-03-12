public class CardClient {

    public static void main(String[] args){

        Card[] cards = new Card[52];

        if (cards[0] == null) {
            System.out.println("No cards yet.");
        }

        int index = 0;
        for (int suit = 0; suit <= 3; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                cards[index] = new Card(rank, suit);
                index++;
            }
        }

    }

}
