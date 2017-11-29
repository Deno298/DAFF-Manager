package dk.KeaExam.service;

import dk.KeaExam.model.League;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class DraftServiceImpl implements DraftService {

    @Override
    public String convertDatetoString(League league) {
        LocalDateTime localDateTime = league.getDraftDate();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String reversedDraftDate = localDateTime.format(dtf);
        System.out.println(reversedDraftDate);
        return reversedDraftDate;
    }
}
