package dk.KeaExam.service;

import dk.KeaExam.model.League;
import dk.KeaExam.model.Player;
import dk.KeaExam.model.Team;
import dk.KeaExam.model.User;
import dk.KeaExam.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Service Class for League Service
 * Author Emil Cronfeld
 * Author Dennis Fagerstrøm Petersen
 */
@Service
public class LeagueServiceImpl implements LeagueService {

    @Autowired
    private ErrorService errorService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PlayerService playerService;

    /**
     * Controls if a player is eligible to join a league and communicate with services which can add them
     * @param leagueId LeagueId of league wished to join
     * @param password Password of the league wished to join
     * @param teamName The users wished teamname
     * @param model Model to carry error messages
     * @return The Model
     */
    @Override
    public Model joinLeague(Integer leagueId, String password, String teamName, Model model) {

        //finding the league the user pressed on
        League league = getOneLeague(leagueId);

        //checks if the users requested teamname already exists in the database
        Team teamExist = teamService.findByTeamName(teamName);

        if(league.getPassword().equals(password) && teamExist == null){
            userService.addUserToLeague(league);
        } else {
            errorService.teamNameAlreadyInUse(model);
        }
        return model;
    }

    /**
     * Responsible for creating leagues
     * @param league League to be created
     * @param year Year of draft date
     * @param month Month of draft date
     * @param dayOfMonth Day of draft date
     * @param hour Hour of draft date
     * @param minute Minute of draft date
     * @param teamName Creators wished teamname in league
     * @param model Model to carry error messages
     * @return The model
     */
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
                        teamService.addTeamToLeague(teamName, league);
                        userService.addUserToLeague(league);


                    } else {
                        //Sending errormessages
                       errorService.DateNotCorrect(model);
                    }
                } else {
                    errorService.teamNameAlreadyInUse(model);
                }
            } else {
                errorService.ligaNameAlreadyInUse(model);
            }
        } else {
            errorService.tooManyLeagues(model);
        }
        return model;
    }

    /**
     * Save a league
     * @param league League to be saved
     */
    public void saveLeague(League league){
        leagueRepository.save(league);
    }

    /**
     * Find all leagues
     * @return List containing all leagues
     */
    @Override
    public List<League> findAllLeagues() {
        return leagueRepository.findAll();
    }

    /**
     * Finds one league
     * @param leagueId Leagueid of league to find
     * @return League
     */
    @Override
    public League getOneLeague(Integer leagueId) {
        return leagueRepository.getOne(leagueId);
    }

    /**
     * Finds all available leagues for a user
     * @return List of Leagues
     */
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

    /**
     * Generates draftorder
     * @param leagueid Leagueid of the league which needs a draftord created
     * @return The draft order, a list of users
     */
    @Override
    public List<User> generateDraftOrder(int leagueid) {
        List<User> usersInLeague = new ArrayList<>(getOneLeague(leagueid).getUsers());

        List <String> usersInLeagueToString = new ArrayList<>();

        for (User user : usersInLeague){
            usersInLeagueToString.add(user.getUsername());

        }

        System.out.println(usersInLeagueToString);

        String draftType = "snake";

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


    /* fix senere
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

        return players;
    }
*/

    /**
     * Checks if a date is valid
     * @param date The date to be checked
     * @return Boolean.
     */
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

    /**
     * Formats date so it can be set in league
     * @param date Date to be formatted
     * @param league League to set date to
     */
    public void setDraftDate (String date, League league) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime draftDate = LocalDateTime.parse(date, dtf);
        league.setDraftDate(draftDate);
    }

    /**
     * Finds a league by leaguename
     * @param leagueName Leaguename of league to be found
     * @return League
     */
    @Override
    public League getOneLeague(String leagueName) {
        return leagueRepository.findByLeagueName(leagueName);
    }

    /**
     * Get standing
     * @param league League of standing to be found
     * @return A list of teams sorted based on points
     */
    @Override
    public List<Team> getStandings(League league) {
        //Get all the teams from the selected league and sorting the list based on points.. see team comparable.
        List<Team> teams = teamService.getAllTeamsInLeague(league);
        Collections.sort(teams);
        return teams;
    }
}


