package com.movieboxpro.android.adapter;

import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.movieboxpro.android.model.TestNetMultiModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.androidtv.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: TestNetAdapter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0002H\u0015J \u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0018\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u0007H\u0002J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/movieboxpro/android/adapter/TestNetAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/TestNetMultiModel;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "<init>", "()V", "speedTime", "", "speedInterval", "onDetachedFromRecyclerView", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "convert", "helper", "item", "addEntry", "lineData", "Lcom/github/mikephil/charting/data/LineData;", "lineChart", "Lcom/github/mikephil/charting/charts/LineChart;", "entry", "Lcom/github/mikephil/charting/data/Entry;", "initLineChart", "chart", "size", "formatSpeed", "", "speedKbS", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TestNetAdapter extends BaseQuickAdapter<TestNetMultiModel, BaseViewHolder> {
    private final long speedInterval;
    private final long speedTime;

    @Override // com.chad.library.adapter.base.BaseQuickAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
    }

    public TestNetAdapter() {
        super(R.layout.adapter_test_speed_item, null, 2, null);
        this.speedTime = 8000L;
        this.speedInterval = 80L;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, TestNetMultiModel item) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        ConstraintLayout constraintLayout = (ConstraintLayout) helper.getView(R.id.container);
        TextView textView = (TextView) helper.getView(R.id.tvTitle);
        TextView textView2 = (TextView) helper.getView(R.id.tvCountry);
        TextView textView3 = (TextView) helper.getView(R.id.tvSpeed);
        ImageView imageView = (ImageView) helper.getView(R.id.ivSelected);
        final LineChart lineChart = (LineChart) helper.getView(R.id.lineChart);
        if (item.isSelect()) {
            CommonExtKt.visible(imageView);
            constraintLayout.setBackgroundResource(R.drawable.test_speed_selected_selector);
        } else {
            CommonExtKt.gone(imageView);
            constraintLayout.setBackgroundResource(R.drawable.white_light_dark_selector);
        }
        int status = item.getStatus();
        if (status == 0) {
            CommonExtKt.invisible(lineChart);
            TextView textView4 = textView3;
            CommonExtKt.gone(textView4);
            CommonExtKt.gone(textView4);
        } else if (status == 1) {
            CommonExtKt.visible(textView3);
            CommonExtKt.textColor(textView3, R.color.color_main_blue);
            String strMultiply = CommonUtils.multiply(CommonUtils.divide(String.valueOf(item.getCurrSize()), String.valueOf(TimeUtils.getCurrentTime() - item.getStartTime()), 10), "0.9765625", 0);
            Intrinsics.checkNotNull(strMultiply);
            String speed = formatSpeed(Double.parseDouble(strMultiply) * ((double) 1024));
            if (item.getSpeeds() == null) {
                item.setSpeeds(new ArrayList());
            }
            item.getSpeeds().add(Integer.valueOf((int) Float.parseFloat(strMultiply)));
            textView3.setText(speed);
            CommonExtKt.visible(lineChart);
            if (!item.isInit()) {
                initLineChart(lineChart, item.getSize());
                LineDataSet lineDataSet = new LineDataSet(item.getValues(), "");
                lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
                lineDataSet.setCubicIntensity(0.2f);
                lineDataSet.setDrawFilled(true);
                lineDataSet.setDrawCircles(false);
                lineDataSet.setLineWidth(1.0f);
                lineDataSet.setCircleRadius(4.0f);
                lineDataSet.setCircleColor(-1);
                lineDataSet.setFillDrawable(ContextCompat.getDrawable(getContext(), R.drawable.line_chart_fill_shape));
                lineDataSet.setColor(Color.rgb(2, 164, 255));
                lineDataSet.setDrawHorizontalHighlightIndicator(false);
                lineDataSet.setFillFormatter(new IFillFormatter() { // from class: com.movieboxpro.android.adapter.TestNetAdapter$$ExternalSyntheticLambda0
                    @Override // com.github.mikephil.charting.formatter.IFillFormatter
                    public final float getFillLinePosition(ILineDataSet iLineDataSet, LineDataProvider lineDataProvider) {
                        return TestNetAdapter.convert$lambda$0(lineChart, iLineDataSet, lineDataProvider);
                    }
                });
                LineData lineData = new LineData(lineDataSet);
                lineData.setValueTextSize(9.0f);
                lineData.setDrawValues(false);
                lineChart.setData(lineData);
                item.setInit(true);
                item.setLineData(lineData);
            } else if (item.getAddEntry() != null) {
                LineData lineData2 = item.getLineData();
                Intrinsics.checkNotNullExpressionValue(lineData2, "getLineData(...)");
                Entry addEntry = item.getAddEntry();
                Intrinsics.checkNotNullExpressionValue(addEntry, "getAddEntry(...)");
                addEntry(lineData2, lineChart, addEntry);
            }
        } else if (status == 2) {
            CommonExtKt.invisible(lineChart);
            CommonExtKt.textColor(textView3, R.color.red);
            textView3.setText("Failed");
            CommonExtKt.visible(textView3);
        } else if (status == 3) {
            CommonExtKt.visible(lineChart);
            CommonExtKt.visible(textView3);
            CommonExtKt.textColor(textView3, R.color.color_main_blue);
            List<Integer> speeds = item.getSpeeds();
            textView3.setText(formatSpeed((speeds != null ? CollectionsKt.averageOfInt(speeds) : 1.0d) * ((double) 1024)));
        } else if (status == 4 || status == 5) {
            CommonExtKt.invisible(lineChart);
            CommonExtKt.gone(textView3);
        }
        if (textView2 != null) {
            textView2.setSelected(true);
        }
        if (textView2 != null) {
            textView2.setText(item.getDescription());
        }
        if (textView != null) {
            textView.setText(item.getCountry());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float convert$lambda$0(LineChart lineChart, ILineDataSet iLineDataSet, LineDataProvider lineDataProvider) {
        return lineChart.getAxisLeft().getAxisMinimum();
    }

    private final void addEntry(LineData lineData, LineChart lineChart, Entry entry) {
        lineData.addEntry(entry, 0);
        lineData.notifyDataChanged();
        lineChart.notifyDataSetChanged();
        lineChart.moveViewToAnimated(entry.getX(), entry.getY(), YAxis.AxisDependency.LEFT, 1000L);
        lineChart.invalidate();
    }

    private final void initLineChart(LineChart chart, long size) {
        chart.getDescription().setEnabled(false);
        chart.setViewPortOffsets(0.0f, 0.0f, 0.0f, 0.0f);
        chart.setTouchEnabled(false);
        chart.setDragEnabled(false);
        chart.setScaleEnabled(false);
        chart.setPinchZoom(false);
        chart.setDrawGridBackground(false);
        chart.setMaxHighlightDistance(300.0f);
        chart.setNoDataText("");
        XAxis xAxis = chart.getXAxis();
        xAxis.setEnabled(false);
        xAxis.setAxisMaximum(this.speedTime / this.speedInterval);
        chart.getAxisLeft().setEnabled(false);
        chart.getAxisRight().setEnabled(false);
        chart.getLegend().setEnabled(false);
    }

    private final String formatSpeed(double speedKbS) {
        if (speedKbS < 0.0d) {
            return "0";
        }
        if (speedKbS < 1024.0d) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format(Locale.getDefault(), "%sB/S", Arrays.copyOf(new Object[]{Integer.valueOf((int) speedKbS)}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            return str;
        }
        if (speedKbS < 1048576.0d) {
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            String str2 = String.format(Locale.getDefault(), "%sKB/S", Arrays.copyOf(new Object[]{Integer.valueOf((int) (speedKbS / ((double) 1024)))}, 1));
            Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
            return str2;
        }
        if (speedKbS < 1.073741824E9d) {
            StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
            String str3 = String.format(Locale.getDefault(), "%.1fMB/S", Arrays.copyOf(new Object[]{Double.valueOf(speedKbS / ((double) 1048576))}, 1));
            Intrinsics.checkNotNullExpressionValue(str3, "format(...)");
            return str3;
        }
        StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
        String str4 = String.format(Locale.getDefault(), "%.1fGB/S", Arrays.copyOf(new Object[]{Double.valueOf(speedKbS / ((double) 1073741824))}, 1));
        Intrinsics.checkNotNullExpressionValue(str4, "format(...)");
        return str4;
    }
}
