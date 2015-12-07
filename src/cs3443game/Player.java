package cs3443game;

import java.util.ArrayList;

public class Player implements Comparable<Player>
{
	ArrayList<Player> players = new ArrayList<Player>();
	private String username;
	private int score;
	
	/**
	 * Constructor for setting up a user
	 */
	public Player(String name)
	{
		username = name;
		score = 0;
	}

	@Override
	public int compareTo(Player o)
	{
		return 0;
	}
	
	public String toString()
	{
		return username + score;
	}

	public void setPoints(int points) {
		score = points;
		
	}
	
}