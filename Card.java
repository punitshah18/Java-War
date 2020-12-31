
import java.util.*;
public class Card
{

    private int[] p1Num = new int[26];
    private char[] p1Suit = new char[26];
    private char[] cpuSuit = new char[26];
    private int[] cpuNum = new int[26];
    private int arr1[] = new int[13]; //spades
    private int arr2[] = new int[13]; //clubs
    private int arr3[] = new int[13]; //diamonds
    private int arr4[] = new int[13]; //hearts
    private int shuffledDeck[] = new int[52];
    private char spades[] = new char[13];
    private char clubs[] = new char[13];
    private char diamonds[] = new char[13];
    private char hearts[] = new char[13];
    private char suitList[] = new char[52];
    private int playerOne[] = new int[26];
    private int cpu[] = new int[26];

    public Card()
    {

        
        populateSuit(suitList, spades, clubs, diamonds, hearts);
        shuffleSuit(suitList);
        //for dealing cards
        //call this function 4 times
        //each time with a new array (i know this is a waste of space and i shouldnt do that)
        //temp solution for now
        arr1 = RandomizeArray(1,13);
        arr2 = RandomizeArray(1,13);
        arr3 = RandomizeArray(1,13);
        arr4 = RandomizeArray(1,13);
        
        for(int x : arr1){
            System.out.println(x);
        }

        //creates Whole int array
        shuffledDeck = CombineDeck(arr1, arr3, arr2, arr4, shuffledDeck);
        shuffle(shuffledDeck);


        dealCard(shuffledDeck, playerOne, cpu);
        /*
        // you dont need this, this is only for testing
        for(int i = 0; i < 52; i++)
        {
            System.out.println(shuffledDeck[i] + "" + suitList[i] + " : iteration : " + i);
        }
    
        //divides current whole decks to individual ones
        divideDeck(p1Num, cpuNum, p1Suit, cpuSuit, shuffledDeck, suitList);

        //test divided decks? same here u dont need this this is also only testing
        System.out.println("Player 1");
        for(int i = 0; i < 26; i++){
            System.out.println(p1Num[i] + String.valueOf(p1Suit[i]));
        }

        System.out.println("Cpu");
        for(int i = 0; i < 26; i++)
        {
            System.out.println(cpuNum[i] + String.valueOf(cpuSuit[i]));
        }
        */

    }

    //get array of p1 num for gui side (same for rest getters)
    public int[] getP1NumDeck()
    {
        return playerOne;
    }

    public char[] getP1SuitDeck()
    {
        return p1Suit;
    }

    public int[] getCpuNumDeck()
    {
        return cpu;
    }

    public char[] getcpuSuitDeck()
    {
        return cpuSuit;
    }


    public int[] RandomizeArray(int a, int b)
    {

        Random rgen = new Random(); // Random number generator
        int size = b-a+1;
        int[] array = new int[size];

        for(int i=0; i< size; i++)
        {
            array[i] = a+i;
        }

        for (int i=0; i<array.length; i++) 
        {
            int randomPosition = rgen.nextInt(array.length);
            int temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }

        // for(int s: array);
        // System.out.println(s);

        return array;

    }

    //shuffles deck of suitList
    public void shuffleSuit(char[] Deck)
    {
        Random rgen = new Random();

        //shuffled the deck
        for (int i=0; i < Deck.length; i++) 
        {
            int randomPosition = rgen.nextInt(Deck.length);
            char temp = Deck[i];
            Deck[i] = Deck[randomPosition];
            Deck[randomPosition] = temp;
        }

    }
    //shuffles deck of numbers
    public void shuffle(int[] Deck)
    {
        Random rgen = new Random();

        //shuffled the deck
        for (int i=0; i < Deck.length; i++) 
        {
            int randomPosition = rgen.nextInt(Deck.length);
            int temp = Deck[i];
            Deck[i] = Deck[randomPosition];
            Deck[randomPosition] = temp;
        }

    }

    public void dealCard(int[] oldDeck, int[] playerOne, int []cpu)
    {
        int inc = 0;
        for(int i = 0; i < 52; i ++)
        {
            if(i < 26)
            {
                playerOne[i] = oldDeck[i];
            }
            else
            {
                cpu[inc] = oldDeck[i];
                inc++;
            }
        }
    }

    //combined 4 sepeate decks to 1
    public int[] CombineDeck(int[] arr1, int[] arr3,int arr2[], int[] arr4, int[] newDeck)
    {

        int inc = 0;

        for(int i = 0; i < 4; i ++){

            for(int j = 0; j < 13; j++){
                if(i == 0){
                    newDeck[inc] = arr1[j];
                }
                else if(i == 1){
                    newDeck[inc] = arr3[j];
                }
                else if(i == 2){
                    newDeck[inc] = arr2[j];
                }
                else if(i == 3){
                    newDeck[inc] = arr4[j];
                }

                inc++;
            }
        }

        return newDeck;
    }

    public void divideDeck(int[] numArrayP1, int[] numArrayCpu, char[] suitArrayP1, char[] suitArraycpu, int[] intArrayWhole, char[] suitArrayWhole){
        int inc = 26;
        for(int i = 0; i < (intArrayWhole.length/2); i++){
            numArrayP1[i] = intArrayWhole[i];
            suitArrayP1[i] = suitArrayWhole[i];
            numArrayCpu[i] = intArrayWhole[inc+i];
            suitArraycpu[i] = suitArrayWhole[inc+i];
        }
    }

    public char[] populateSuit(char[] suitList, char[] spades, char[] clubs, char[] diamonds, char[] hearts)
    {
        int inc = 0;

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 13; j++){
                if(i == 0){
                    suitList[inc] = 'S';
                }
                else if(i == 1){
                    suitList[inc] = 'C';
                }
                else if(i == 2){
                    suitList[inc] = 'D';
                }
                else if(i == 3){
                    suitList[inc] = 'H';
                }

                inc++;
            }
        }

        return suitList;
    }

}

//THIS IS GONNA BE IN YOUR GUI
// WHEN U WANT TO DRAW A CARD DO THE FOLLOWING (BASSICALLY)

//Assuming you made a card obj already
// Card obj = new Card();
//int[] p1Num = obj.getP1NumDeck

/*
//global iterater int inc?
Action lisener( e -> draw){
    draw({
    if(inc < 26)(you dont want to go out of bounds (that will break your code))
    display p1Num[inc]
    inc++;
    })
}



*/