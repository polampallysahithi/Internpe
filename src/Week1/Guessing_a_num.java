package Week1;

import java.util.*;

public class Guessing_a_num {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Random randomnum = new Random();
		int numbertobeguessed = randomnum.nextInt(100);
		int tries =0;
		System.out.println("Welcome to the GUESSING GAME");
		System.out.println("I have thought of a number ...Can you guess it ??");
		boolean correctlyguessed = false;
		while (!correctlyguessed) {
			System.out.print("Enter your guess : ");
			int guess = sc.nextInt();
			tries++;
			
			if(guess < 1 || guess >100) {
				System.out.println("Invalid input ..please select a number between 0 and 100");
			}
			else if(guess <numbertobeguessed) {
				System.out.println("it's too low .. try again");
			}
			else if(guess > numbertobeguessed) {
				System.out.println("it's too high .. try again");
			}
			else {
				correctlyguessed = true;
				System.out.println("Congratulations !!! you guessed it right");
				System.out.println("It took you "+tries + " tries");
				
			}
		}
	
	}
}
		      