public class Card {

    private final int rank;
    private final int suit;

    /*
    Clubs 7→ 0
    Diamonds 7→ 1
    Hearts 7→ 2
    Spades 7→ 3


    Ace 7→ 1
    Jack 7→ 11
    Queen 7→ 12
    King 7→ 13
     */

    //class variables
    public static final String[] SUITS = {"Clubs", "Diamonds", "Hearts", "Spades"};
    public static final String[] RANKS = {null, "Ace", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "10", "Jack", "Queen", "King"};

    public Card(int rank, int suit) {
        this.rank = rank;
        this.suit = suit;
    }


    public String toString() {
        return RANKS[this.rank] + " of " + SUITS[this.suit];
    }

    public boolean equals(Card that){
        return this.suit == that.suit
             &&   this.rank == that.rank;
    }

    public int compareTo(Card that){
        if (this.suit < that.suit){
            return -1;
        }

        if (this.suit > that.suit) {
            return 1;
        }
        if (this.rank < that.rank){
            return -1;
        }
        if (this.rank > that.rank){
            return 1;
        }
        return 0;
    }

    public int getRank() {
        return rank;
    }

    public int getSuit() {
        return suit;
    }

    public static void printDeck(Card[] cards) {
        for (int i = 0; i < cards.length; i++) {
            System.out.println(cards[i]);
        }
    }

    public static int search(Card[] cards, Card target) {
        for (int i = 0; i < cards.length; i++) {
            if (cards[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(Card[] cards, Card target) {
        int low = 0;
        int high = cards.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2; // step 1
            int comp = cards[mid].compareTo(target);
            if (comp == 0) { // step 2
                return mid;
            } else if (comp < 0) { // step 3
                low = mid + 1;
            } else { // step 4
                high = mid - 1;
            }
        }
        return -1;
    }

    public static int binarySearch2(Card[] cards, Card target,
                                   int low, int high) {
        if (high < low) {
            return -1;
        }
        int mid = (low + high) / 2; // step 1
        int comp = cards[mid].compareTo(target);
        if (comp == 0) { // step 2
            return mid;
        } else if (comp < 0) { // step 3
            return binarySearch2(cards, target, mid + 1, high);
        } else { // step 4
            return binarySearch2(cards, target, low, mid - 1);
        }
    }

    public static void main(String[] args){

        Card card = new Card(12,2);
        System.out.println(card);


    }
}
