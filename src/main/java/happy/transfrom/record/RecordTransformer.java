package happy.transfrom.record;

import happy.domain.record.Record;
import happy.domain.user.User;
import happy.model.record.BackFillRecordRequest;
import happy.model.record.RecordBackFill;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecordTransformer {

    public List<Record> transformToRecords(BackFillRecordRequest recordRequests, User user) {
        return recordRequests.getRecords().stream().map(record -> transformToRecord(record, user)).collect(Collectors.toList());
    }

    private Record transformToRecord(RecordBackFill recordBackFill, User user) {
        Record record = new Record();
        record.setTimestamp(ZonedDateTime.parse(recordBackFill.getDateTime()));
        record.setState(recordBackFill.getState());
        record.setUser(user);
        return record;
    }
}
