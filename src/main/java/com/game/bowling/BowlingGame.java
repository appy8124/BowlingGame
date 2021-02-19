package com.game.bowling;

import java.util.Arrays;
import java.util.Map;


//This class Keeps track of current roll and frame, stores roll for each  frame
public class BowlingGame {

	int[] rolls;
	int currentRoll;
	boolean isBonusRoll;

	public BowlingGame() {
		this.rolls = new int[21];
		Arrays.fill(this.rolls, -1);
		this.currentRoll = 0;
		this.isBonusRoll = false;
	}

	//Stores the input roll
	public void roll(int p) {
		rolls[currentRoll++] = p;
	}

	//Calculate scores
	public int scoreFrame(Map<Integer, Integer> map) {
		int score = 0;
		int frame = 0;
		int i = 1;
		while (i <= 10) {
			if (isStrike(frame)) {
				if (canStrikeBonusBeCalculated(frame)) {
					score += 10 + calculateStrikeBonus(frame);
					map.put(i, score);
					frame++;
				} else {
					break;
				}
			} else if (isSpare(frame)) {
				if (canSpareBonusBeCalculated(frame)) {
					score += 10 + calculateSpareBonus(frame);
					map.put(i, score);
					frame += 2;
				} else {

					break;
				}

			} else {
				if (rolls[frame + 1] != -1) {
					score += sumOfRolls(frame);
					map.put(i, score);
					frame += 2;
				}
				if (!(frame < currentRoll))
					break;

			}
			i++;
		}

		return score;
	}

	//Check if the roll is a Strike
	private boolean isStrike(int frame) {
		return rolls[frame] == 10;
	}

	//Check if the roll is a Spare
	private boolean isSpare(int frame) {
		return sumOfRolls(frame) == 10;
	}

	//Calculate the bonus score for Strike
	private int calculateStrikeBonus(int frame) {
		return sumOfRolls(frame + 1);
	}

	//Calculate the bonus score for Spare
	private int calculateSpareBonus(int frame) {
		return rolls[frame + 2];
	}

	//Calculate sum of current and next roll
	private int sumOfRolls(int frame) {
		return rolls[frame] + rolls[frame + 1];
	}

	//Check if Strike bonus can be calculated by checking if next 2 rolls are done 
	private boolean canStrikeBonusBeCalculated(int frame) {
		return (rolls[frame + 1] != -1 && rolls[frame + 2] != -1);

	}

	//Check if Spare bonus can be calculated by checking if the next roll is done 
	private boolean canSpareBonusBeCalculated(int frame) {
		return (rolls[frame + 2] != -1);

	}

}


