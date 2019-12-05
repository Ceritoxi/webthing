package happy.service.record;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class Prettifier {

    public String prettifyDuration(Duration duration) {
        return dayPart(duration) + timePart(duration);
    }

    private String timePart(Duration duration) {
        return LocalTime.MIDNIGHT.plus(duration).format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    private String dayPart(Duration duration) {
        long days = duration.toDays();
        return days > 0 ? days + " Days " : "";
    }

    public String nthSuffixOf(double percentile) {
        int baseNumber = (int) (percentile * 100);
        String base = baseNumber + "";
        if (baseNumber > 10 && baseNumber < 20) {
            return base + "th";
        } else if (base.endsWith("3")) {
            return base + "rd";
        } else if (base.endsWith("2")) {
            return base + "nd";
        } else {
            return base + "th";
        }
    }
}
