import java.util.Random;
import java.util.Scanner;
public class hangman {
    public static void main(String args[]){
        int numLetters;
        int lives = 5;
        String magicWord = "";
        String userGuess = "";
        String template = "";
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Welcome to my Hangman Game! In this game, You will guess letters to figure out the mystery word! If you think you know the answer, you may also guess the word completely! Good Luck! :)");

        //Randomly select from list of words
        Random rand = new Random();
        //array of words
        //after initializing list of words,
        //String[] array = new String[3];
        //String[] array = {"try", "cat"};
        String[] array = new String[]{"Apple","Banana","Orange","Pineapple","Pomegranate","Blueberry","Strawberry"};

        // randomly getting the magic word, making sure it never leaves the bounds of the array
        magicWord = array[rand.nextInt(array.length)];

        numLetters = magicWord.length();

        for(int i = 0; i < magicWord.length(); i++){
            template = template.concat("_");
        }
       //System.out.println(magicWord);
        //System.out.println(template);

        while(!userGuess.equalsIgnoreCase(magicWord) ){
            if(!template.contains("_")){ // Can't you combine these?
                break;
            }
            System.out.println("");
            System.out.println("The hidden word has " + numLetters + " characters in it.");
            System.out.println("The Magic Word: " + template);
            System.out.print("Enter a letter or guess: ");
            //take user input, one letter or guess
            userGuess = keyboard.nextLine();

            if(userGuess.isEmpty()){
                continue;
            }
            if(userGuess.length() == 1){//one letter
                System.out.println("One letter entered: " + userGuess );
                if(magicWord.contains(userGuess.toLowerCase()) || magicWord.contains(userGuess.toUpperCase())){
                    userGuess = userGuess.toLowerCase();
                    if(template.contains(userGuess.toLowerCase()) || template.contains(userGuess.toUpperCase())){
                        System.out.println("You've already entered this letter! Try another or guess the word!");
                        continue;
                    }
                            //check for all lowercase letters
                            for(int i = 0; i < magicWord.length(); i++) {
                                if(magicWord.charAt(i) == userGuess.charAt(0)){
                                    template = template.substring(0,i) + userGuess + template.substring(i+1);
                                }
                            }
                            //check for uppercase letters
                            userGuess = userGuess.toUpperCase();
                            for(int j = 0; j < magicWord.length(); j++){
                                if(magicWord.charAt(j) == userGuess.charAt(0)) {
                                    template = template.substring(0, j) + userGuess + template.substring(j + 1);
                                }
                            }
                    System.out.println("Letter found in word! Replacing!");
                }else{
                    System.out.println(userGuess + " is not in the Magic Word! ");
                    //decrease lives by 1
                    lives--;
                    if(lives == 0){
                        gameOverLoss(magicWord);
                    }
                    System.out.println("You have " + lives +" lives left.");
                }
            }else{//If guess is a word
                System.out.println("User Entered a Word!");
                //System.out.println("Whole Word entered");
                if(userGuess.equalsIgnoreCase("STOP")){
                    System.out.println("You have entered the Stop command. Forfeiting game.");
                    gameOverLoss(magicWord);
                }if(userGuess.equalsIgnoreCase(magicWord)){
                    break;
                }else{
                    lives--;
                    System.out.println("Wrong Guess, lost a life.");
                    System.out.println("You have " + lives +" lives left.");
                    if(lives == 0){
                        gameOverLoss(magicWord);
                    }
                }
            }
        }

        //while loop will contain user until they get the word.
        gameOverWin(magicWord);
    }

    public static void gameOverLoss(String magicWord){
        System.out.println("Oh no! You ran out of lives. :(");
        System.out.println("The Magic Word was " + magicWord +"." );
        System.out.println("Try again next time! :)");
        System.exit(0);
    }

    public static void gameOverWin(String magicWord){
        System.out.println("Yay! You got the Magic Word! :)");
        System.out.println("The Magic Word was " + magicWord +"." );
        System.out.println("Thanks for playing! Come play again! :)");
    }


}