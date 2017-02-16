package org.wickedsource.budgeteer.web.pages.dashboard.dailyratechart;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.joda.money.Money;
import org.wickedsource.budgeteer.service.statistics.StatisticsService;

import java.util.List;

public class AverageDailyChartModelNEW {

    @SpringBean
    private StatisticsService service;

    private long projectId;

    private int numberOfDays;

    public AverageDailyChartModelNEW(long projectId, int numberOfDays) {
        this.projectId = projectId;
        this.numberOfDays = numberOfDays;
        Injector.get().inject(this);
    }

    public List<Money> getAvgDailyRateForPreviousDays() {
        return service.getAvgDailyRateForPreviousDays(projectId, numberOfDays);
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }
}