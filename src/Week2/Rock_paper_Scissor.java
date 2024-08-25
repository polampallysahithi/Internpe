package Week2;

import java.util.Random;
import java.util.Scanner;

public class Rock_paper_Scissor {

	public static void main(String[] args) {
		Scanner sc  = new Scanner(System.in);

		while(true) {
			String userChoice = getuserChoice(sc);
			if(userChoice.equals("quit")) {
				System.out.println("Thanks for playing !!");
				break;
			}
			
			String OponentChoice = getOponentChoice();
			System.out.println("System's choice : "+ OponentChoice);
			
			String result = winner(userChoice,OponentChoice );
			System.out.println(result);
			System.out.println();
			
		}

	}

	public static String getuserChoice(Scanner sc) {
		System.out.println("Please enter 'rock','paper','scissor' ");
		System.out.println("if you want to exit ...please type quit");
		String userInput = sc.nextLine().toLowerCase();

		while(!userInput.equals("paper") &&	!userInput.equals("rock") && !userInput.equals("scissor") && !userInput.equals("quit")){
			System.out.println("Invalid input! please try again");
			userInput = sc.nextLine().toLowerCase();

		}
		return userInput;
	}

	public static String getOponentChoice() {

		Random rdm = new Random();
		String[] choices = {"rock" , "paper" , "scissor"};

		return choices[rdm.nextInt(3)];
	}

	public static String winner(String userChoice, String getOponentChoice) {
		if(userChoice.equals(getOponentChoice)) {
			return "It's a tie!!";	
		}
		else if(
				(userChoice.equals("paper") && getOponentChoice.equals("rock"))||
				(userChoice.equals("rock")) && getOponentChoice.equals("scissor") ||
				(userChoice.equals("scissor") && getOponentChoice.equals("paper"))
				) {
			return "Congratulations :) you won";

		}
		else {
			return "Sorry :( you lost";
		}


	}



}

