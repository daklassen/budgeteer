package org.wickedsource.budgeteer.web.usecase.dashboard.component.burnedbudgetchart;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.joda.money.Money;
import org.wickedsource.budgeteer.service.statistics.StatisticsService;

import java.util.List;

public class BurnedBudgetChartModel extends LoadableDetachableModel<List<Money>> {

    @SpringBean
    private StatisticsService service;

    private long userId;

    private int numberOfWeeks;

    public BurnedBudgetChartModel(long userId, int numberOfWeeks) {
        Injector.get().inject(this);
        this.userId = userId;
        this.numberOfWeeks = numberOfWeeks;
    }

    @Override
    protected List<Money> load() {
        return service.getBudgetBurnedInPreviousWeeks(userId, numberOfWeeks);
    }

    public int getNumberOfWeeks() {
        return this.numberOfWeeks;
    }
}
