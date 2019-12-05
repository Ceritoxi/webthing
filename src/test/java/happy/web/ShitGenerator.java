package happy.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import happy.domain.record.State;
import happy.model.record.BackFillRecordRequest;
import happy.model.record.RecordBackFill;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ShitGenerator {

    @Test
    public void generateShit() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ZonedDateTime now = ZonedDateTime.now();
        List<RecordBackFill> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            RecordBackFill recordBackFillStart = new RecordBackFill();
            LocalDate localDate = now.toLocalDate().minusDays(i);
            if (!localDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) && !localDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                recordBackFillStart.setState(State.START);
                recordBackFillStart.setDateTime(ZonedDateTime.of(localDate, LocalTime.of((int) (Math.random() + 9), (int) (Math.random() * 59), (int) (Math.random() * 5)), now.getZone()).format(DateTimeFormatter.ISO_DATE_TIME));
                RecordBackFill recordBackFillEnd = new RecordBackFill();
                recordBackFillEnd.setState(State.END);
                recordBackFillEnd.setDateTime(ZonedDateTime.of(localDate, LocalTime.of((int) (Math.random() + 17), (int) (Math.random() * 59), (int) (Math.random() * 5)), now.getZone()).format(DateTimeFormatter.ISO_DATE_TIME));
                list.add(recordBackFillStart);
                list.add(recordBackFillEnd);
            }
        }
        BackFillRecordRequest backFillRecordRequest = new BackFillRecordRequest();
        backFillRecordRequest.setRecords(list);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("trash.json"), backFillRecordRequest);
    }

}