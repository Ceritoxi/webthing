package happy.service.record.statistics;

import happy.domain.user.User;
import happy.model.record.Statistics;
import happy.service.record.Prettifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;

@Service
public class StatisticsAssemblerService {

    private RecordStatisticsService recordStatisticsService;
    private Prettifier prettifier;

    @Autowired
    public StatisticsAssemblerService(RecordStatisticsService recordStatisticsService, Prettifier prettifier) {
        this.recordStatisticsService = recordStatisticsService;
        this.prettifier = prettifier;
    }

    public Statistics assembleStatisticsForUser(ZoneId zoneId, int year, int month, double percentile, User user) {
        Statistics statistics = new Statistics();
        statistics.setTotalToday(prettifier.prettifyDuration(recordStatisticsService.getTotalTodayForUser(zoneId, user)));
        statistics.setAverageMonth(prettifier.prettifyDuration(recordStatisticsService.getAverageMonthForUser(zoneId, year, month, user)));
        statistics.setAverageTotal(prettifier.prettifyDuration(recordStatisticsService.getAverageTotalForUser(zoneId, user)));
        statistics.setMonthToGoalDifference(prettifier.prettifyDuration(recordStatisticsService.getMonthToGoalDifference(zoneId, year, month, user)));
        statistics.setTimeGoal(prettifier.prettifyDuration(user.getPersonalConfigurations().getDesiredGoal()));
        statistics.setTodayToGoalDifference(prettifier.prettifyDuration(recordStatisticsService.getTodayToGoalDifference(zoneId, user)));
        statistics.setPercentileMonth(prettifier.prettifyDuration(recordStatisticsService.getMonthPercentile(zoneId, year, month, percentile, user)));
        statistics.setPercentileTotal(prettifier.prettifyDuration(recordStatisticsService.getTotalPercentile(zoneId, percentile, user)));
        statistics.setPercentileSuffix(prettifier.nthSuffixOf(percentile));
        statistics.setTotalMonth(prettifier.prettifyDuration(recordStatisticsService.getTotalMonthForUser(zoneId, year, month, user)));
        statistics.setTotal(prettifier.prettifyDuration(recordStatisticsService.getTotalForUser(zoneId, user)));
        return statistics;
    }
}
