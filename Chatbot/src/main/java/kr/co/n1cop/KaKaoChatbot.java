package kr.co.n1cop;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

import model.GameVO;
import model.PlayerVO;
import model.TeamVO;

@RestController
public class KaKaoChatbot
{
	private StringTokenizer st;
	private StringBuffer sql;
	boolean team_check = false;
	PlayerVO playerVO = new PlayerVO();
	TeamVO teamVO = new TeamVO();
	  String answer = null;
	
	@RequestMapping(value = "/keyboard", method = RequestMethod.GET)
	public KeyboardVO keyboard(){
		KeyboardVO keyboard=new KeyboardVO(new String[] {"Sofi", "대화"});
	
		return keyboard;
	}
	
	
	@RequestMapping(value = "/message", method = RequestMethod.POST)
	public ResponseMessageVO message(@RequestBody RequestMessageVO vo){
		ResponseMessageVO res_vo=new ResponseMessageVO();
		MessageVO mes_vo=new MessageVO();
		
		if(vo.getContent().equals("Sofi")) {
			mes_vo.setText("Sofi는....");
			
			KeyboardVO keyboard=new KeyboardVO(new String[] {"Sofi", "대화하기"});
			PhotoVO photo=new PhotoVO();
			
			photo.setUrl("xx");
			mes_vo.setPhoto(photo);
			res_vo.setKeyboard(keyboard);
		} else {
			String input = vo.getContent();
			String workspace_id = "xxxx";
		    String username = "xxxx";
		    String password = "xxxx";
		    
		    ConversationService service = new ConversationService(ConversationService.VERSION_DATE_2017_02_03);
	        service.setUsernameAndPassword(username, password);
	        MessageRequest newMessage = new MessageRequest.Builder().inputText(input).build();
	        MessageResponse response = service.message(workspace_id, newMessage).execute();
	        List<String> says = response.getText();
	        StringBuffer temp_says = new StringBuffer();
	        for(String s : says) {
	        		temp_says.append(s);
	        }
	        st = new StringTokenizer(temp_says.toString(), "|");  
	        sql = new StringBuffer();
	      
	        
	        for(int i=0; i<st.countTokens(); i++){
	      	   String token = st.nextToken();
	      	   String player_name;
	      	   String team_name;
	      	   switch(token){
	      	   case "intent_player": //선수 질문
	      		   String n_token = st.nextToken().trim();
	      		   player_name = st.nextToken().trim();
	      		   sql.append(" SELECT player,game_count,p_goal,p_assist,team,p_no,position,age,height,weight,nationality");
	      		   sql.append(" FROM sofi_player");
	      		   sql.append("	WHERE player = ?");
	      		   try {
	  				executePlayerSQL(player_name);
	  				switch(n_token){
	  				case "nationality":
	  					answer =  playerVO.getPlayer() + " 선수의 국적은 '" + playerVO.getNationality() + "'입니다.";
	  					break;
	  				case "team":
	  					answer =  playerVO.getPlayer() + " 선수의 팀은 '" + playerVO.getTeam() + "'입니다.";
	  					break;
	  				case "position":
	  					answer =  playerVO.getPlayer() + " 선수의 포지션은 '" + playerVO.getPosition() + "'입니다.";
	  					break;
	  				case "goals":
	  					answer =  playerVO.getPlayer() + " 선수의 골은 " + playerVO.getP_goal() + "골 입니다.";
	  					break;
	  				case "assists":
	  					answer =  playerVO.getPlayer() + " 선수의 어시스트는 " + playerVO.getP_assist() + "도움 입니다.";
	  					break;
	  				case "age":
	  					answer =  playerVO.getPlayer() + " 선수의 나이는 " + playerVO.getAge() + "살 입니다.";
	  					break;
	  				case "height":
	  					answer =  playerVO.getPlayer() + " 선수의 키는 " + playerVO.getHeight() + "cm입니다.";
	  					break;
	  				case "weight":
	  					answer =  playerVO.getPlayer() + " 선수의 몸무게는 " + playerVO.getWeight() + "kg입니다.";
	  					break;
	  				case "p_no":
	  					answer =  playerVO.getPlayer() + " 선수의 등번호는 " + playerVO.getP_no() + "번 입니다.";
	  					break;
	  				case "profile":
	  					answer =  playerVO.toString();
	  					PhotoVO photo=new PhotoVO();
	  					if(playerVO.getPlayer().equals("Son Heung-Min")) {
	  						photo.setUrl("put your image url here");
	  					}else if(playerVO.getPlayer().equals("Ki Sung-Yueng")) {
	  						photo.setUrl("put your image url here");
	  					}else if(playerVO.getPlayer().equals("Eden Hazard")) {
	  						photo.setUrl("put your image url here");
	  					}else if(playerVO.getPlayer().equals("Wayne Rooney")) {
	  						photo.setUrl("put your image url here");
	  					}
	  					photo.setWidth(600);
	  					mes_vo.setPhoto(photo);
	  					break;
	  				}
	  				
	  			} catch (Exception e) {
	  				e.printStackTrace();
	  			}
	      		   break;
	      	   case "intent_team": //팀 질문
	      		   String nt_token = st.nextToken().trim();
	      		   team_name = st.nextToken().trim();
	      		   sql.append(" SELECT team_name,team_name_kr,lastResult,teamRankingGroup,teamCode,gainGoal,gameCount,gainPoint,foul,lost,won,rank,drawn,manager");
	      		   sql.append(" FROM sofi_team");
	      		   sql.append("	WHERE team_name = ?");
	      		   try {
	     				executeTeamSQL(team_name);
	     				switch(nt_token){
	     				case "lastResult":
	     					answer =  teamVO.getTeam_name() + " 팀의 최근 전적은 " + teamVO.getLastResult() + "입니다.";
	     					break;
	     				case "gainGoal":
	     					answer =  teamVO.getTeam_name() + " 팀의 득점골은  " + teamVO.getGainGoal() + "골 입니다.";
	     					break;
	     				case "gameCount":
	     					answer =  teamVO.getTeam_name() + " 팀의 게임수는 " + teamVO.getGameCount() + "게임 입니다.";
	     					break;
	     				case "gainPoint":
	     					answer =  teamVO.getTeam_name() + " 팀의 승점은 " + teamVO.getGainPoint() + "점 입니다.";
	     					break;
	     				case "foul":
	     					answer =  teamVO.getTeam_name() + " 팀의 파울 수는  " + teamVO.getFoul() + "개 입니다.";
	     					break;
	     				case "Result":
	     					answer = teamVO.getTeam_name() + " 팀의 전적은 " + teamVO.getWon()+"승 "+teamVO.getLost()+"패 "+teamVO.getDrawn()+"무 입니다.";
	     					break;
	     				case "rank":
	     					answer =  teamVO.getTeam_name() + " 팀의 순위는 " + teamVO.getRank() + "위 입니다.";
	     					break;
	     				case "manager":
	     					answer = teamVO.getTeam_name()+ "팀의 감독은 '"+teamVO.getManager() + "'입니다.";
	     					break;
	     				case "profile":
	     					answer = teamVO.toString();
	     					break;
	     				case "squad":
	     					answer = teamVO.getTeam_name()+"의 명단 \n";
	     					List<String> result_list = executeSquadSQL(teamVO.getTeam_name());
		 	      			   for(String name_list : result_list){
		 	      				   answer += name_list +"\n";
		 	      			   }
	     					break;
	     				
	     				}
	     			} catch (Exception e) {
	     				e.printStackTrace();
	     			}
	      		   break;
	      	   case "intent_fixture": //경기 일정
	      		   sql.append(" SELECT game_date,homeTeamName,awayTeamName,homeTeamScore,awayTeamScore,stadium");
	      		   sql.append(" FROM sofi_game");
	      		   sql.append(" WHERE game_date BETWEEN date_format(?,'%Y-%m-%d') AND date_format(?,'%Y-%m-%d')+60");
	      		   String date_token = st.nextToken().trim();
	      		   String team_name1=st.nextToken();
	      		   if(st.hasMoreTokens()){
	      			   team_check = true;
	      			   team_name1 = st.nextToken().trim();
	         			   sql.append(" AND (homeTeamName = ? OR awayTeamName = ?)");
	      		   }
	      		   try {
	      			   System.out.println(date_token);
	      			   List<GameVO> result_list = executeGameSQL(date_token, team_name1);
	      			   for(GameVO gVO : result_list){
	      				   answer += gVO.toString() +"\n===============\n";
	      			   }
	      		   } catch (Exception e) {
	      			   e.printStackTrace();
	      		   }
	      		   break;
	      	   }
	      	   
	 		}
	       //System.out.println(answer);
		    mes_vo.setText(answer);
		}
		
		res_vo.setMessage(mes_vo);
		return res_vo;
	}
	//####################################################################################################
	  private List<String> executeSquadSQL(String team_name) throws ClassNotFoundException, SQLException {
		  	Connection cn =null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			List<String> list = new ArrayList<String>();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				cn = DriverManager.getConnection("db address here","id here","pw here");
				ps = cn.prepareStatement("select player from sofi_player where team=?");
				ps.setString(1, team_name);
				rs = ps.executeQuery();
				
				while(rs.next()){
					playerVO.setPlayer(rs.getString("player"));
					list.add(playerVO.getPlayer());
				}
			} finally {
				dbClose(rs, ps, cn);
			}
			return list;
	  }
	  private List<GameVO> executeGameSQL(String date_token,String team_name) throws SQLException, ClassNotFoundException {
	    	Connection cn =null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			List<GameVO> list = new ArrayList<GameVO>();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				cn = DriverManager.getConnection("db address here","id here","pw here");
				ps = cn.prepareStatement(sql.toString());
				ps.setString(1, date_token);
				ps.setString(2, date_token);
				if(team_check){
					ps.setString(3, team_name);
					ps.setString(4, team_name);
				}
				rs = ps.executeQuery();
				
				while(rs.next()){
					GameVO gameVO = new GameVO();
					gameVO.setGame_date(rs.getString("game_date"));
					gameVO.setHomeTeamName(rs.getString("homeTeamName"));
					gameVO.setAwayTeamName(rs.getString("awayTeamName"));
					gameVO.setHomeTeamScore(rs.getInt("homeTeamScore"));
					gameVO.setAwayTeamScore(rs.getInt("awayTeamScore"));
					gameVO.setStadium(rs.getString("stadium"));
					System.out.println(gameVO+"\n");
					list.add(gameVO);
				}
			} finally {
				dbClose(rs, ps, cn);
			}
			return list;
		}

		private void executeTeamSQL(String team_name_test) throws SQLException, ClassNotFoundException {
			Connection cn =null;
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				Class.forName("com.mysql.jdbc.Driver");
				cn = DriverManager.getConnection("db address here","id here","pw here");
				ps = cn.prepareStatement(sql.toString());
				ps.setString(1, team_name_test);
				rs = ps.executeQuery();
				while(rs.next()){
					teamVO.setTeam_name(rs.getString("team_name"));
					teamVO.setTeam_name_kr(rs.getString("team_name_kr"));
					teamVO.setTeamRankingGroup(rs.getString("teamRankingGroup"));
					teamVO.setLastResult(rs.getString("lastResult"));
					teamVO.setTeamCode(rs.getLong("teamCode"));
					teamVO.setGainGoal(rs.getLong("gainGoal"));
					teamVO.setGameCount(rs.getLong("gameCount"));
					teamVO.setGainPoint(rs.getLong("gainPoint"));
					teamVO.setFoul(rs.getLong("foul"));
					teamVO.setLost(rs.getLong("lost"));
					teamVO.setWon(rs.getLong("won"));
					teamVO.setRank(rs.getLong("rank"));
					teamVO.setDrawn(rs.getLong("drawn"));
					teamVO.setManager(rs.getString("manager"));
				}
			} finally {
				dbClose(rs, ps, cn);
			}
			
		}

		private void executePlayerSQL(String n_token) throws SQLException, ClassNotFoundException {
			Connection cn =null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				cn = DriverManager.getConnection("db address here","id here","pw here");
				ps = cn.prepareStatement(sql.toString());
				ps.setString(1, n_token);
				rs = ps.executeQuery();
				while(rs.next()){
					playerVO.setPlayer(rs.getString("player"));
					playerVO.setGame_count(rs.getInt("game_count"));
					playerVO.setP_goal(rs.getInt("p_goal"));
					playerVO.setP_assist(rs.getInt("p_assist"));
					playerVO.setTeam(rs.getString("team"));
					playerVO.setP_no(rs.getInt("p_no"));
					playerVO.setPosition(rs.getString("position"));
					playerVO.setAge(rs.getInt("age"));
					playerVO.setHeight(rs.getInt("height"));
					playerVO.setWeight(rs.getInt("weight"));
					playerVO.setNationality(rs.getString("nationality"));
				}
			} finally {
				dbClose(rs, ps, cn);
			}
		}
	    
	    private void dbClose(ResultSet rs, PreparedStatement ps, Connection cn) {
			if (rs != null) try{rs.close();} catch(Exception e){}
			if (ps != null) try{ps.close();} catch(Exception e){}
			if (cn != null) try{cn.close();} catch(Exception e){}
		}
}
