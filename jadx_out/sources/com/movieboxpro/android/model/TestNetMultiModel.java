package com.movieboxpro.android.model;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class TestNetMultiModel {
    public static final int CANCLED = 5;
    public static final int COMPLETE = 3;
    public static final int FAILED = 2;
    public static final int NOT_START = 0;
    public static final int READY = 4;
    public static final int TESTING = 1;
    private Entry addEntry;
    private long contentLength;
    private String country;
    private long currSize;
    private String description;
    private int display_order;
    private String domain;
    private String domainIp;
    private long endTime;
    private String group_id;
    private int id;
    private boolean init;
    private boolean isSelect = false;
    private LineData lineData;

    @SerializedName("100m_url")
    private String newUrl;
    private int our;
    private float ratio;
    private long size;
    private String speed;
    private List<Integer> speeds;
    private long startTime;
    private int status;
    private int type;
    private String url;
    private List<Entry> values;

    public int getOur() {
        return this.our;
    }

    public void setOur(int our) {
        this.our = our;
    }

    public String getDomainIp() {
        return this.domainIp;
    }

    public void setDomainIp(String domainIp) {
        this.domainIp = domainIp;
    }

    public long getContentLength() {
        return this.contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public String getNewUrl() {
        return this.newUrl;
    }

    public void setNewUrl(String newUrl) {
        this.newUrl = newUrl;
    }

    public LineData getLineData() {
        return this.lineData;
    }

    public void setLineData(LineData lineData) {
        this.lineData = lineData;
    }

    public long getSize() {
        return this.size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public List<Entry> getValues() {
        return this.values;
    }

    public void setValues(List<Entry> values) {
        this.values = values;
    }

    public Entry getAddEntry() {
        return this.addEntry;
    }

    public void setAddEntry(Entry addEntry) {
        this.addEntry = addEntry;
    }

    public boolean isInit() {
        return this.init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public List<Integer> getSpeeds() {
        return this.speeds;
    }

    public void setSpeeds(List<Integer> speeds) {
        this.speeds = speeds;
    }

    public long getCurrSize() {
        return this.currSize;
    }

    public void setCurrSize(long currSize) {
        this.currSize = currSize;
    }

    public String getGroup_id() {
        return this.group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public TestNetMultiModel() {
    }

    public TestNetMultiModel(int id, int status, int type) {
        this.id = id;
        this.status = status;
        this.type = type;
    }

    public String getSpeed() {
        return this.speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getDisplay_order() {
        return this.display_order;
    }

    public void setDisplay_order(int display_order) {
        this.display_order = display_order;
    }

    public float getRatio() {
        return this.ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSelect() {
        return this.isSelect;
    }

    public void setSelect(boolean select) {
        this.isSelect = select;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
