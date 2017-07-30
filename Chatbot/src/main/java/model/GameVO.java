package model;

public class GameVO {
	private String game_date;
	private String homeTeamName;
	private String awayTeamName;
	private int homeTeamScore;
	private int awayTeamScore;
	//private Date gameStartTime;
	private String stadium;
	
	public String getGame_date() {
		return game_date;
	}
	public void setGame_date(String game_date) {
		this.game_date = game_date;
	}
	public String getHomeTeamName() {
		return homeTeamName;
	}
	public void setHomeTeamName(String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}
	public String getAwayTeamName() {
		return awayTeamName;
	}
	public void setAwayTeamName(String awayTeamName) {
		this.awayTeamName = awayTeamName;
	}
	public int getHomeTeamScore() {
		return homeTeamScore;
	}
	public void setHomeTeamScore(int homeTeamScore) {
		this.homeTeamScore = homeTeamScore;
	}
	public int getAwayTeamScore() {
		return awayTeamScore;
	}
	public void setAwayTeamScore(int awayTeamScore) {
		this.awayTeamScore = awayTeamScore;
	}
//	public Date getGameStartTime() {
//		return gameStartTime;
//	}
//	public void setGameStartTime(Date gameStartTime) {
//		this.gameStartTime = gameStartTime;
//	}
	public String getStadium() {
		return stadium;
	}
	public void setStadium(String stadium) {
		this.stadium = stadium;
	}
	@Override
	public String toString() {
		return "경기 날짜 : " + game_date +"<br/>경기장 : " + stadium+ "<br/>" 
				+ homeTeamName + " : " + awayTeamName+"<br/>" + homeTeamScore+ " : " +awayTeamScore+"<br/>====================<br/>";
	}
}
