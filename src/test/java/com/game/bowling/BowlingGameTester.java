package com.game.bowling;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class BowlingGameTester {

	private BowlingGame game;
	
	Map<Integer, Integer> map = new HashMap();

	void rollMany(int n, int pins, BowlingGame g) {
		for (int i = 0; i < n; i++)
			g.roll(pins);
	}

	@Before
	public void startUpGame() {
		this.game = new BowlingGame();
	}

	@Test
	public void allZeroRolls() {
		rollMany(20, 0, game);
		assertEquals(0, game.scoreFrame(map));
	}

	@Test
	public void allOneRolls() {
		rollMany(20, 1, game);
		assertEquals(20, game.scoreFrame(map));
	}

	@Test
	public void justOneSpare() {
		game.roll(5);
		game.roll(5);
		game.roll(3);
		rollMany(17, 0, game);
		assertEquals(16, game.scoreFrame(map));
	}

	@Test
	public void justOneStrike() {
		game.roll(10);
		game.roll(3);
		game.roll(4);
		rollMany(16, 0, game);
		assertEquals(24, game.scoreFrame(map));
	}

	@Test
	public void perfectGame() {
		rollMany(12, 10, game);
		assertEquals(300, game.scoreFrame(map));
	}
	
	@Test
	public void validateInputStrike() {
		assertEquals(GameRunner.validateInput("Strike", 1, game), true);
	}

	@Test
	public void validateInputSpare() {
		assertEquals(GameRunner.validateInput("Spare", 2, game), true);
	}
	
	@Test
	public void validateInputMiss() {
		assertEquals(GameRunner.validateInput("Miss", 1, game), true);
	}
	@Test
	public void validateInputWrong() {
		game= new BowlingGame();
		game.roll(8);
		assertEquals(GameRunner.validateInput("2", 2, game), false);
	}
	@Test
	public void validateInputRight() {
		game= new BowlingGame();
		game.roll(8);
		assertEquals(GameRunner.validateInput("1", 2, game), true);
	}
	
	@Test
	public void validateInputHappy() {
		
		assertEquals(GameRunner.validateInput("9", 1, game), true);
	}
}
