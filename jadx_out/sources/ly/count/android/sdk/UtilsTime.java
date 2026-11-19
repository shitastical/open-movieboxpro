package ly.count.android.sdk;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public class UtilsTime {
    private static final TimeUniquesEnsurer timeGenerator = new TimeUniquesEnsurer();

    public static class Instant {
        public final int dow;
        public final int hour;
        public final long timestampMs;

        protected Instant(long j, int i, int i2) {
            this.timestampMs = j;
            this.hour = i;
            this.dow = i2;
        }

        public static Instant get(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("timestampInMillis must be greater than or equal to zero");
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(j);
            return new Instant(j, calendar.get(11), calendar.get(7) - 1);
        }
    }

    public static synchronized Instant getCurrentInstant() {
        return Instant.get(currentTimestampMs());
    }

    public static synchronized long currentTimestampMs() {
        return timeGenerator.uniqueTimestamp();
    }

    public static int currentTimestampSeconds() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    private static class TimeUniquesEnsurer {
        final long addition;
        final List<Long> lastTsMs;

        private TimeUniquesEnsurer() {
            this.lastTsMs = new ArrayList(10);
            this.addition = 0L;
        }

        long currentTimeMillis() {
            return System.currentTimeMillis();
        }

        synchronized long uniqueTimestamp() {
            long jCurrentTimeMillis = currentTimeMillis();
            if (this.lastTsMs.size() > 2 && jCurrentTimeMillis < ((Long) Collections.min(this.lastTsMs)).longValue()) {
                this.lastTsMs.clear();
                this.lastTsMs.add(Long.valueOf(jCurrentTimeMillis));
                return jCurrentTimeMillis;
            }
            while (this.lastTsMs.contains(Long.valueOf(jCurrentTimeMillis))) {
                jCurrentTimeMillis++;
            }
            while (this.lastTsMs.size() >= 10) {
                this.lastTsMs.remove(0);
            }
            this.lastTsMs.add(Long.valueOf(jCurrentTimeMillis));
            return jCurrentTimeMillis;
        }
    }
}
