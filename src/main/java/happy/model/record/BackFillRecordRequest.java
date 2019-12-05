package happy.model.record;

import java.util.List;

public class BackFillRecordRequest {
    List<RecordBackFill> records;

    public List<RecordBackFill> getRecords() {
        return records;
    }

    public void setRecords(List<RecordBackFill> records) {
        this.records = records;
    }
}
