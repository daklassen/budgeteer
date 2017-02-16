package org.wickedsource.budgeteer.web.charts;

import com.pingunaut.wicket.chartjs.chart.impl.Line;

public class ChartThemes {

    public static Line getLineTheme() {
        Line line = new Line();
        line.getOptions().setScaleShowHorizontalLines(true);
        line.getOptions().setScaleShowVerticalLines(false);
        line.getOptions().setScaleSteps(2);
        line.getOptions().setResponsive(true);
        line.getOptions().setTooltipFillColor("#6cc644");

        return line;
    }
}
