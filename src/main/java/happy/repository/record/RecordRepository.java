package happy.repository.record;

import happy.domain.record.Record;
import happy.domain.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecordRepository extends CrudRepository<Record, Long> {
    List<Record> findAllByUser(User user);
}
