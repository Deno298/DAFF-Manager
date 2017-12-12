package dk.KeaExam.service;

import dk.KeaExam.model.League;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Service class for Draft Service
 * Author Emil Cronfeld
 * Author Dennis Fagerstrøm Petersen
 */
@Service
public class DraftServiceImpl implements DraftService {

    /**
     * Method converting a date to a string
     * @param league League which dates need to be converted
     * @return Draftdate as a String
     */
    @Override
    public String convertDatetoString(League league) {
        LocalDateTime localDateTime = league.getDraftDate();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String reversedDraftDate = localDateTime.format(dtf);
        System.out.println(reversedDraftDate);
        return reversedDraftDate;
    }
}
