package happy.service.record;

import happy.domain.record.Record;
import happy.repository.record.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {

    private RecordRepository recordRepository;

    @Autowired
    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @CacheEvict({"totalToday", "totalMonth", "total", "averageMonth", "averageTotal", "percentileTotal", "percentileMonth"})
    public void addRecords(List<Record> records) {
        recordRepository.saveAll(records);
    }
}
