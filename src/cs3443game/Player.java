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
		if(this.score > o.score)
			return 1;
		else if(this.score == o.score)
			return 0;
		else 
			return -1;
	}
	
	public String toString()
	{
		return "Player: " + username + "	Score: " + score;
	}

	public void setPoints(int points) {
		score = points;
		
	}
	
}