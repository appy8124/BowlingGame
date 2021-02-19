package com.game.bowling;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import com.game.bowling.BowlingGame;
public class GameRunner 
{
	public static void main(String[] args) {
		System.out.println("Game Starting");
		runGame();
	}

	//Display score for each roll
	private static void displayScore(Map<Integer, Integer> map, int frame, int rollsleft) {

		System.out.print("Frame |");
		for (int i = 1; i <= frame; i++) {
			System.out.print(i + "  |");

		}
		System.out.println();
		for (int i = 0; i < map.size() + 1; i++) {
			System.out.print("-----");
		}
		System.out.println();
		System.out.print("Score " + "|");
		for (int i = 1; i <= frame; i++) {
			if (map.containsKey(i))
				System.out.print(map.get(i) + " |");
			else
				System.out.print("* |");

		}
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Current frame : " + frame);
		System.out.println("Rolls left in the frame : " + rollsleft);

	}

	//Starts the game
	private static void runGame() {
		Map<Integer, Integer> map = new HashMap();
		BowlingGame game = new BowlingGame();
		int frame = 1;
		int isBonusAwarded = 0;
		Scanner pinsDropped = new Scanner(System.in);
		while (frame <= 10) {
			int j = 1;
			while (j <= 2) {
				System.out.println("Input the pins dropped");
				String input = pinsDropped.nextLine();
				if (validateInput(input, j, game)) {
					if (input.equals("Strike")) {
						game.roll(10);
						if (frame == 10) {
							isBonusAwarded = 2;
						}
						game.scoreFrame(map);
						displayScore(map, frame, 0);

						break;
					} else if (input.equals("Spare")) {
						game.roll(10 - game.rolls[game.currentRoll - 1]);
						if (frame == 10) {
							isBonusAwarded = 1;
						}

					} else if (input.equals("Miss"))
						game.roll(0);
					else
						game.roll(Integer.parseInt(input));
					game.scoreFrame(map);
					displayScore(map, frame, 2 - j);
					j++;
				} else {
					System.out.println("Invalid input, refer doc for valid inputs.");
					continue;
				}
			}

			frame++;
		}
		frame = 10;
		if (isBonusAwarded == 2) {
			System.out.println("You got an two bonus rolls.");
		} else if (isBonusAwarded == 1) {
			System.out.println("You got one bonus roll.");
		}
		while (isBonusAwarded > 0) {
			System.out.println("Input the pins dropped");
			String input = pinsDropped.nextLine();
			if (input.equals("Strike")) {
				game.roll(10);
			} else if (input.equals("Spare")) {
				game.roll(10 - game.rolls[game.currentRoll - 1]);
			} else if (input.equals("Miss"))
				game.roll(0);
			else
				game.roll(Integer.parseInt(input));
			isBonusAwarded--;
		}
		game.scoreFrame(map);
		displayScore(map, frame, 0);
		System.out.println("Game Over");
		pinsDropped.close();

	}

	//Validate   
	public static boolean validateInput(String input, int rollSequence, BowlingGame game) {
		if (input.equals("Strike") || input.equals("Spare") || input.equals("Miss"))
			return true;
		if (Integer.valueOf(input) > 0 && Integer.valueOf(input) < 10) {
			if (rollSequence == 2) {
				return ((game.rolls[game.currentRoll-1] + Integer.valueOf(input)) < 10);
			}
			return true;
		}
		return false;
	}
}

