package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;
import java.text.DecimalFormat;

/* JADX INFO: loaded from: classes4.dex */
public class PercentFormatter extends ValueFormatter {
    public DecimalFormat mFormat;
    private boolean percentSignSeparated;
    private PieChart pieChart;

    public PercentFormatter() {
        this.mFormat = new DecimalFormat("###,###,##0.0");
        this.percentSignSeparated = true;
    }

    public PercentFormatter(PieChart pieChart) {
        this();
        this.pieChart = pieChart;
    }

    public PercentFormatter(PieChart pieChart, boolean z) {
        this(pieChart);
        this.percentSignSeparated = z;
    }

    @Override // com.github.mikephil.charting.formatter.ValueFormatter
    public String getFormattedValue(float f) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mFormat.format(f));
        sb.append(this.percentSignSeparated ? " %" : "%");
        return sb.toString();
    }

    @Override // com.github.mikephil.charting.formatter.ValueFormatter
    public String getPieLabel(float f, PieEntry pieEntry) {
        PieChart pieChart = this.pieChart;
        if (pieChart != null && pieChart.isUsePercentValuesEnabled()) {
            return getFormattedValue(f);
        }
        return this.mFormat.format(f);
    }
}
