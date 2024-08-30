
/***********************************************************************************
* Tristan Jarvey
* CS 250 - Section 809
* Program 08 (VERSION 2)
* 
* This program is designed to allow a single user to play roulette.
***********************************************************************************/

import java.util.Scanner;

public class Program08V2
{
	public static void main(String[] args)	
	{
		Scanner stdIn = new Scanner(System.in);
		int choice = 0; // global menu choice variable
		int currentNumber = 0;
		String currentColor = "";
		int currentBet = 0; // global bet variable
		int chipsNow = 100;
		int cashOut = 0;
		
		int spinNum = 0;
		String spinColor = "";
		
		welcome();
		do
		{
			choice = getMenuChoice(stdIn);
			
			if(choice == 1)
			{
				currentNumber = getNumber(stdIn);
			}
			else if(choice == 2)
			{
				currentColor = getColor(stdIn);
			}
			else
			{
				break;
			}
			currentBet = getBet(stdIn, chipsNow);
			spinNum = (int)(Math.random()*37)+0;
			spinColor = determineColor(spinNum);
			
			System.out.println("\nSpinning the wheel...");
			System.out.println("Spin number: " + spinNum);
			System.out.println("Spin color : " + spinColor + "\n");
			
			// Determine win/loss
			System.out.println(determineWinLoss(choice, currentNumber, spinNum,
					currentColor, spinColor));
			chipsNow = determineChips(choice, chipsNow, currentBet, currentColor,
					spinColor, currentNumber, spinNum);
			System.out.println("You now have " + chipsNow + " chips\n");
			
			if(chipsNow == 0)
			{
				break;
			}
		} while(choice != 3);
		
		report(chipsNow, cashOut);
	}
	
	public static void welcome()
	{
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("$   WELCOME TO  ROULETTE   $");
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("$ NUMBER BETS PAYOUT: 35:1 $");
		System.out.println("$  COLOR BETS PAYOUT: 1:1  $");
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
		System.out.println("You have 100 chips\n");
	}
	
	public static int getMenuChoice(Scanner stdIn)
	{
		System.out.println("1. Pick a number to bet on");
		System.out.println("2. Pick a color to bet on");
		System.out.println("3. Cash out\n");
		int menuChoice = 0;
		boolean flag = true;
		
		do
		{
			System.out.print("Enter your choice (1-3): ");
			menuChoice = stdIn.nextInt();
			if(menuChoice >= 1 && menuChoice <= 3)
			{
				break;
			}
			else
			{
				flag = false;
			}
		} while(flag == false);
		
		return menuChoice;
	}
	
	public static int getNumber(Scanner stdIn)
	{
		int number;
		boolean flag = true;
		
		do
		{
			System.out.print("Enter the number to bet on (1-36): ");
			number = stdIn.nextInt();
			
			if(number >= 1 && number <=36)
			{
				break;
			}
			else
			{
				flag = false;
			}
		} while(flag == false);
		
		return number;
	}
	
	public static String getColor(Scanner stdIn)
	{
		String color;
		boolean flag = true;
		
		do
		{
			System.out.print("Enter the color to bet on (Red or Black): ");
			color = stdIn.next();
			
			if(color.equalsIgnoreCase("Red") || color.equalsIgnoreCase("Black"))
			{
				break;
			}
			else
			{
				flag = false;
			}
		} while(flag == false);
		
		return color;
	}
	
	public static int getBet(Scanner stdIn, int chipsNow)
	{
		int bet;
		boolean flag = true;
		
		do
		{
			System.out.print("Enter the number of chips to bet (1 - 100): ");
			bet = stdIn.nextInt();
			
			if(bet >= 1 && bet <= chipsNow)
			{
				break;
			}
			else
			{
				flag = false;
			}
		} while(flag == false);
		
		return bet;
	}
	
	public static String determineColor(int spinNum)
	{
		String spinColor = "";
		
		if(spinNum%2 == 0 && spinNum != 0)
		{
			spinColor = "Red";
		}
		else if(spinNum%2 == 1)
		{	
			spinColor = "Black";
		}
		else
		{
			spinColor = "Green";
		}
		
		return spinColor;
	}
	
	public static String determineWinLoss(int choice, int currentNumber, int spinNum,
			String currentColor, String spinColor)
	{
		String winLoss = "";
		
		if(choice == 1)
		{
			if(currentNumber == spinNum)
			{
				winLoss = "Congratulations, you won!";
			}
			else
			{
				winLoss = "Sorry, you lost.";
			}
		}
		if(choice == 2)
		{
			if(currentColor.equalsIgnoreCase(spinColor))
			{
				winLoss = "Congratulations, you won!";
			}
			else
			{
				winLoss = "Sorry, you lost.";
			}
		}
		
		return winLoss;
	}
	
	public static int determineChips(int choice, int chipsNow, int currentBet,
			String currentColor, String spinColor, int currentNumber, int spinNum)
	{
		int chips = 0;
		
		if(choice == 1)
		{
			if(currentNumber == spinNum)
			{
				chips = chipsNow + (currentBet*35);
			}
			else
			{
				chips = chipsNow - currentBet;
			}
		}
		if(choice == 2)
		{
			if(currentColor.equalsIgnoreCase(spinColor))
			{
				chips = chipsNow + (currentBet*1);
			}
			else
			{
				chips = chipsNow - currentBet;
			}
		}
		
		return chips;
	}
	
	public static void report(int chipsNow, int cashOut)
	{
		if(chipsNow > 100)
		{
			cashOut = chipsNow - 100;
			System.out.print("Thank you for playing, you won " + cashOut + " chips");
		}
		else if(chipsNow < 100)
		{
			cashOut = 100 - chipsNow;
			System.out.print("Thank you for playing, you lost " + cashOut + " chips");
		}
		else
		{
			System.out.print("Thank you for playing");
		}
	}
}
