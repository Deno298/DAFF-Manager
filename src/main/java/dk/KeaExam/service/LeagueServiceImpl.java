package dk.KeaExam.service;

import dk.KeaExam.model.League;
import dk.KeaExam.model.Player;
import dk.KeaExam.model.Team;
import dk.KeaExam.model.User;
import dk.KeaExam.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


@Service
public class LeagueServiceImpl implements LeagueService {

    @Autowired
    private TeamService teamService;

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PlayerService playerService;

    @Override
    public Model createLeague(League league, String year, String month, String dayOfMonth, String hour, String minute, String teamName, Model model) {

        String wishedDraftDateStart = year + "-" + month + "-" + dayOfMonth + " " + hour + ":" + minute;

        //Finding currently logged in user
        User user = userService.getCurrentUser();
        League leagueExists = getOneLeague(league.getLeagueName());

        if(userService.isUserAllowedToCreateLeague()) {
            if (leagueExists == null) {

                Team team = teamService.findByTeamName(teamName);
                if (team == null) {


                    //Checking if the date is valid
                    if (isDateValid(wishedDraftDateStart)) {

                        //Setters
                        setDraftDate(wishedDraftDateStart, league);
                        league.setOwnerid(user.getId());

                        //Saves
                        saveLeague(league);

                        userService.addUserToLeague(league, teamName, user);


                    } else {
                        //Sending errormessages
                        model.addAttribute("errormsg", "Hovsa din dato er vist ikke korrekt");
                        model.addAttribute("error", "error");
                    }
                } else {
                    model.addAttribute("errormsg", "Dit team navn er allerede i brug");
                    model.addAttribute("error", "error");
                }
            } else {
                model.addAttribute("errormsg", "Liga navnet eksistere allerede");
                model.addAttribute("error", "error");
            }
        } else {
            model.addAttribute("errormsg", " eksistere allerede");
            model.addAttribute("error", "error");
        }
        return model;

    }

    public void saveLeague(League league){
        leagueRepository.save(league);
    }
    @Override
    public List<League> findAllLeagues() {
        return leagueRepository.findAll();
    }

    @Override
    public League getOneLeague(Integer leagueId) {
        return leagueRepository.getOne(leagueId);
    }

    @Override
    public List<League> findAllAvailableLeagues() {
        //Get currently logged in user
        User user = userService.getCurrentUser();
        //Get all leagues
        List<League> leagues = findAllLeagues();

        //Iterate through them, if the user already exist in a league remove the league from the list
        for (Iterator<League> it = leagues.iterator(); it.hasNext(); ) {
            League league = it.next();
            if (league.containsUser(user)) {
                it.remove();
            }
        }

        //Return the list
        return leagues;
    }

    @Override
    public List<User> generateDraftOrder(ArrayList<User> usersInLeague, String draftType) {
        int repeater = 3;
        //list we wants to return
        List<User> draftOrder = new ArrayList<>();

        //Randomizes order of users, users comparable method returns a random value
        Collections.sort(usersInLeague);

        //adds user to final list
        draftOrder.addAll(usersInLeague);


        if (draftType.equals("snake")) {
            Collections.reverse(usersInLeague);
            draftOrder.addAll(usersInLeague);
            repeater = 2;
        }
        for (int i = 0; i < repeater; i++) {
            draftOrder.addAll(draftOrder);
        }

        return draftOrder;
    }

    @Override
    public List<Player> playersInLeague(League league) {
        List<Player> players = new ArrayList<>();
        for (Team team : league.getTeams()) {
            if (!teamService.getListOfPlayers(team).isEmpty()) {
                players.addAll(teamService.getListOfPlayers(team));
            }
        }
        return players;
    }

    @Override
    public List<Player> availablePlayersInLeague(League league) {

        List<Player> players = playerService.findAllPlayers();
        List<Player> playersInleagues = playersInLeague(getOneLeague(1));

        //Iterate through them, if the user exist remove the league from the list
        for (Player player : playersInleagues) {

            for (Iterator<Player> it = players.iterator(); it.hasNext(); ) {
                Player playerIt = it.next();
                if (playerIt.getFirstName().equals(player.getFirstName())) {
                    it.remove();
                }
            }
        }
        System.out.println(players);
        return players;
    }


    public boolean isDateValid(String date) {

        boolean validDate = true;

        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            dtf.parse(date);
            LocalDateTime localDateTime = LocalDateTime.parse(date, dtf);
            LocalDateTime now = LocalDateTime.now();
            if(now.compareTo(localDateTime) > 0){
                validDate = false;
            }
        } catch (DateTimeParseException e) {
            validDate = false;
        }
        return validDate;
    }

    public void setDraftDate (String date, League league) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime draftDate = LocalDateTime.parse(date, dtf);
        league.setDraftDate(draftDate);
    }

    @Override
    public League getOneLeague(String leagueName) {
        return leagueRepository.findByLeagueName(leagueName);
    }
}


