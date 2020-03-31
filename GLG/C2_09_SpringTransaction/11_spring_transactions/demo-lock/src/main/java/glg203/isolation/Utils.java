package glg203.isolation;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import glg203.isolation.dao.TimeTableRepository;
import glg203.isolation.model.TimeTable;

/**
 * Utils
 */
@Service
public class Utils {

    @Autowired
    TimeTableRepository timeTableRepository;

    @Transactional
    public  void display(int owner, String toDisplay) {
        TimeTable timeTable =   new TimeTable(owner, toDisplay);
        timeTableRepository.save(timeTable);
    }

    public List<TimeTable> getLog() {
        return timeTableRepository.findAll(Sort.by("date"));
    }

    public  void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {          
            // NE RIEN FAIRE.
        }
    }
}