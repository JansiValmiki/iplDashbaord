package io.javabrains.ipldashboard.data;
import java.time.LocalDate;
import org.springframework.batch.item.ItemProcessor;

import io.javabrains.ipldashboard.model.Match;
public class MatchDataProcessor  implements ItemProcessor<MatchInput, Match>{
	
	  @Override
	  public Match process(final MatchInput matchInput) throws Exception {
		  
	    Match match = new Match();
	     
	    match.setId(Long.parseLong(matchInput.getId()));
	    match.setCity(matchInput.getCity());
	    
	    match.setDate(LocalDate.parse(matchInput.getDate()));
	    
	    match.setPlayerOfMatch(matchInput.getPlayer_of_match());
	    match.setVenue(matchInput.getVenue());
	    
	    // Set Team1 and Team2 based on the innings order who won the toss
	    String firstInningsTeam, secondInningsTeam;
	    if("bat".equals(matchInput.getToss_decision())) {
	    	firstInningsTeam = matchInput.getToss_winner(); // is toss winner
	    	secondInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) 
	    			? matchInput.getTeam2(): matchInput.getTeam1();// If team1 won toss then team2 is toss looser else team1 is looser
	    }else {
	    	secondInningsTeam = matchInput.getToss_winner(); // is toss winner
	    	firstInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) 
	    			? matchInput.getTeam2(): matchInput.getTeam1(); // If team1 won toss then team2 is toss looser else team1 is looser
	    }
	    // So now order is fixed so we do this
	    match.setTeam1(firstInningsTeam);
	    match.setTeam2(secondInningsTeam);
	    
	    match.setTossWinner(matchInput.getToss_winner());;
	    match.setTossDecision(matchInput.getToss_decision());;
	    match.setResult(matchInput.getResult());
	    match.setResultMargin(matchInput.getResult_margin());;
	    match.setUmpire1(matchInput.getUmpire1());
	    match.setUmpire2(matchInput.getUmpire2());
	    
	    return match;
	  }

}
