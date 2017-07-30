package model;

public class PlayerVO {
	private String player;
	private int game_count;
	private int p_goal;
	private int p_assist;
	private String team;
	private int p_no;
	private String position;
	private int age;
	private int height;
	private int weight;
	private String nationality;
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	public int getGame_count() {
		return game_count;
	}
	public void setGame_count(int game_count) {
		this.game_count = game_count;
	}
	public int getP_goal() {
		return p_goal;
	}
	public void setP_goal(int p_goal) {
		this.p_goal = p_goal;
	}
	public int getP_assist() {
		return p_assist;
	}
	public void setP_assist(int p_assist) {
		this.p_assist = p_assist;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public int getP_no() {
		return p_no;
	}
	public void setP_no(int p_no) {
		this.p_no = p_no;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	@Override
	public String toString() {
		return "선수 명\t" + player + "\n경기 출전 수\t" + game_count + "\n득점\t\t\t" + p_goal + "\n도움\t\t\t"
				+ p_assist + "\n소속 팀\t" + team + "\n등번호\t" + p_no + "\n포지션\t" + position + "\n나이\t\t\t" + age
				+ "\n키\t\t\t" + height + "cm\n몸무게\t" + weight + "kg\n국적\t\t\t" + nationality;
	}
	
	
		
}
