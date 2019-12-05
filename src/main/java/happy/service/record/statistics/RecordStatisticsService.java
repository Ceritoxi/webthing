package happy.service.record.statistics;

import happy.dao.RecordDao;
import happy.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecordStatisticsService {

    private RecordDao recordDao;

    @Autowired
    public RecordStatisticsService(RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    @Cacheable("totalToday")
    public Duration getTotalTodayForUser(ZoneId zoneId, User user) {
        List<ZonedDateTime> timestampsOfUserForDate = getTimestampsForToday(zoneId, user);
        return calculateDuration(timestampsOfUserForDate);
    }

    @Cacheable("total")
    public Duration getTotalForUser(ZoneId zoneId, User user) {
        List<ZonedDateTime> timestampsOfUserForDate = getTimestamps(zoneId, user);
        return calculateDuration(timestampsOfUserForDate);
    }

    @Cacheable("totalMonth")
    public Duration getTotalMonthForUser(ZoneId zoneId, int year, int month, User user) {
        List<ZonedDateTime> timestampsOfUserForDate = getTimestampsForMonth(zoneId, year, month, user);
        Duration duration = calculateDuration(timestampsOfUserForDate);
        return duration;
    }

    @Cacheable("averageMonth")
    public Duration getAverageMonthForUser(ZoneId zoneId, int year, int month, User user) {
        List<ZonedDateTime> timestampsOfUserForDate = getTimestampsForMonth(zoneId, year, month, user);
        Duration duration = calculateDuration(timestampsOfUserForDate);
        return average(timestampsOfUserForDate, duration);
    }

    @Cacheable("averageTotal")
    public Duration getAverageTotalForUser(ZoneId zoneId, User user) {
        List<ZonedDateTime> timestampsOfUserForDate = getTimestamps(zoneId, user);
        Duration duration = calculateDuration(timestampsOfUserForDate);
        return average(timestampsOfUserForDate, duration);
    }

    private Duration average(List<ZonedDateTime> timestampsOfUserForDate, Duration duration) {
        long count = timestampsOfUserForDate.stream().map(ZonedDateTime::toLocalDate).distinct().count();
        return Duration.ofSeconds(duration.getSeconds() / count);
    }

    private List<ZonedDateTime> getTimestampsForToday(ZoneId zoneId, User user) {
        ZonedDateTime date = ZonedDateTime.now().withZoneSameInstant(zoneId);
        List<ZonedDateTime> timestampsOfUserForDate = recordDao.getRecordsOfUserForDate(ZonedDateTime::toLocalDate, date, user);
        if (timestampsOfUserForDate.size() % 2 != 0) {
            timestampsOfUserForDate.add(date);
        }
        return timestampsOfUserForDate;
    }

    private List<ZonedDateTime> getTimestampsForMonth(ZoneId zoneId, int year, int month, User user) {
        ZonedDateTime date = ZonedDateTime.now().withZoneSameInstant(zoneId);
        List<ZonedDateTime> timestampsOfUserForDate = recordDao.getRecordsOfUserForDate(dateTime -> dateTime.toLocalDate().withDayOfMonth(1), date.withYear(year).withMonth(month).withDayOfMonth(1), user);
        if (timestampsOfUserForDate.size() % 2 != 0) {
            timestampsOfUserForDate.add(date);
        }
        return timestampsOfUserForDate;
    }

    private List<ZonedDateTime> getTimestamps(ZoneId zoneId, User user) {
        ZonedDateTime date = ZonedDateTime.now().withZoneSameInstant(zoneId);
        List<ZonedDateTime> timestampsOfUserForDate = recordDao.getRecordsOfUserForDate(dateTime -> dateTime.withYear(1).withMonth(1).withDayOfMonth(1).toLocalDate(), date, user);
        if (timestampsOfUserForDate.size() % 2 != 0) {
            timestampsOfUserForDate.add(date);
        }
        return timestampsOfUserForDate;
    }

    private Duration calculateDuration(List<ZonedDateTime> timestampsOfUserForDate) {
        Duration duration = Duration.ZERO;
        for (int i = 0; i < timestampsOfUserForDate.size(); i += 2) {
            ZonedDateTime start = timestampsOfUserForDate.get(i);
            ZonedDateTime end = timestampsOfUserForDate.get(i + 1);
            duration = duration.plus(Duration.between(start, end));
        }
        return duration;
    }

    public Duration getTodayToGoalDifference(ZoneId zoneId, User user) {
        Duration totalTodayForUser = getTotalTodayForUser(zoneId, user);
        return user.getPersonalConfigurations().getDesiredGoal().minus(totalTodayForUser);
    }

    public Duration getMonthToGoalDifference(ZoneId zoneId, int year, int month, User user) {
        Duration averageMonthForUser = getAverageMonthForUser(zoneId, year, month, user);
        return user.getPersonalConfigurations().getDesiredGoal().minus(averageMonthForUser);
    }

    @Cacheable("percentileTotal")
    public Duration getTotalPercentile(ZoneId zoneId, double percentile, User user) {
        List<ZonedDateTime> timestamps = getTimestamps(zoneId, user);
        Collection<Duration> mapping = getDistinctDayDurations(timestamps);
        return calculatePercentile(mapping, percentile);
    }

    private Collection<Duration> getDistinctDayDurations(List<ZonedDateTime> timestamps) {
        Map<LocalDate, Duration> mapping = new HashMap<>();
        for (int i = 0; i < timestamps.size(); i += 2) {
            ZonedDateTime start = timestamps.get(i);
            ZonedDateTime end = timestamps.get(i + 1);
            Duration between = Duration.between(start, end);
            mapping.merge(start.toLocalDate(), between, Duration::plus);
        }
        return mapping.values();
    }

    @Cacheable("percentileMonth")
    public Duration getMonthPercentile(ZoneId zoneId, int year, int month, double percentile, User user) {
        List<ZonedDateTime> timestamps = getTimestampsForMonth(zoneId, year, month, user);
        Collection<Duration> mapping = getDistinctDayDurations(timestamps);
        return calculatePercentile(mapping, percentile);
    }

    private Duration calculatePercentile(Collection<Duration> durations, double percentile) {
        List<Duration> sortedDurations = durations.stream().sorted().collect(Collectors.toList());
        double nth = ((durations.size() - 1)) * percentile;
        return Math.floor(nth) != nth ? Duration.ofSeconds(sortedDurations.get((int) Math.floor(nth)).plus(sortedDurations.get((int) Math.ceil(nth))).getSeconds() / 2) : sortedDurations.get((int) Math.floor(nth));
    }
}
