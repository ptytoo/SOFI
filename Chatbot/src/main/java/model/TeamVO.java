package model;

/**
 * @author student
 *
 */
public class TeamVO {

	private String team_name;
	private String team_name_kr;
	private String lastResult;
	private String teamRankingGroup; 
	private long teamCode;
	private long    gainGoal;
	private long    gameCount;
	private long    gainPoint;
	private long    foul;
	private long    lost;
	private long    won;
	private long rank;
	private long drawn;
	private String manager;
	public String getTeam_name() {
		return team_name;
	}
	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}
	public String getTeam_name_kr() {
		return team_name_kr;
	}
	public void setTeam_name_kr(String team_name_kr) {
		this.team_name_kr = team_name_kr;
	}
	public String getLastResult() {
		return lastResult;
	}
	public void setLastResult(String lastResult) {
		this.lastResult = lastResult;
	}
	public String getTeamRankingGroup() {
		return teamRankingGroup;
	}
	public void setTeamRankingGroup(String teamRankingGroup) {
		this.teamRankingGroup = teamRankingGroup;
	}
	public long getTeamCode() {
		return teamCode;
	}
	public void setTeamCode(long teamCode) {
		this.teamCode = teamCode;
	}
	public long getGainGoal() {
		return gainGoal;
	}
	public void setGainGoal(long gainGoal) {
		this.gainGoal = gainGoal;
	}
	public long getGameCount() {
		return gameCount;
	}
	public void setGameCount(long gameCount) {
		this.gameCount = gameCount;
	}
	public long getGainPoint() {
		return gainPoint;
	}
	public void setGainPoint(long gainPoint) {
		this.gainPoint = gainPoint;
	}
	public long getFoul() {
		return foul;
	}
	public void setFoul(long foul) {
		this.foul = foul;
	}
	public long getLost() {
		return lost;
	}
	public void setLost(long lost) {
		this.lost = lost;
	}
	public long getWon() {
		return won;
	}
	public void setWon(long won) {
		this.won = won;
	}
	public long getRank() {
		return rank;
	}
	public void setRank(long rank) {
		this.rank = rank;
	}
	public long getDrawn() {
		return drawn;
	}
	public void setDrawn(long drawn) {
		this.drawn = drawn;
	}
	
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	@Override
	public String toString() {
		return "팀명 " + team_name + "\n팀명(한국어) " + team_name_kr
				+ "\n감독 " + manager
				+ "\n최근전적 " + lastResult
				+ "\n득점골 " + gainGoal
				+ "\n게임 횟수 " + gameCount + "\n승점 " + gainPoint + "\n파울 " + foul 
				+ "\n전적 " +won+ "승 " +lost + "패 " +drawn+"무 "
				+ "\n순위 " +rank;
	}
	
	
	
	
	
}
