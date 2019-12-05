package happy.dao;

import happy.domain.record.Record;
import happy.domain.user.User;
import happy.repository.record.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class RecordDao {

    private RecordRepository recordRepository;

    @Autowired
    public RecordDao(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public List<ZonedDateTime> getRecordsOfUserForDate(Function<ZonedDateTime, LocalDate> truncateFunction, ZonedDateTime date, User user) {
        List<Record> recordsForUser = recordRepository.findAllByUser(user);
        return recordsForUser.stream()
                .map(Record::getTimestamp).filter(timestamp -> truncateFunction.apply(timestamp).isEqual(truncateFunction.apply(date)))
                .sorted()
                .collect(Collectors.toList());
    }
}

