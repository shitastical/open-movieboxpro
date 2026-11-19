package master.flame.danmaku.danmaku.model;

/* JADX INFO: loaded from: classes6.dex */
public class SpecialDanmaku extends BaseDanmaku {
    public long alphaDuration;
    public int beginAlpha;
    public float beginX;
    public float beginY;
    private float[] currStateValues = new float[4];
    public int deltaAlpha;
    public float deltaX;
    public float deltaY;
    public int endAlpha;
    public float endX;
    public float endY;
    public LinePath[] linePaths;
    public float pivotX;
    public float pivotY;
    public float rotateX;
    public float rotateZ;
    public long translationDuration;
    public long translationStartDelay;

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public int getType() {
        return 7;
    }

    public void updateData(float f) {
    }

    private class Point {
        float x;
        float y;

        public Point(float f, float f2) {
            this.x = f;
            this.y = f2;
        }

        public float getDistance(Point point) {
            float fAbs = Math.abs(this.x - point.x);
            float fAbs2 = Math.abs(this.y - point.y);
            return (float) Math.sqrt((fAbs * fAbs) + (fAbs2 * fAbs2));
        }
    }

    public class LinePath {
        public long beginTime;
        float delatX;
        float deltaY;
        public long duration;
        public long endTime;
        Point pBegin;
        Point pEnd;

        public LinePath() {
        }

        public void setPoints(Point point, Point point2) {
            this.pBegin = point;
            this.pEnd = point2;
            this.delatX = point2.x - point.x;
            this.deltaY = point2.y - point.y;
        }

        public float getDistance() {
            return this.pEnd.getDistance(this.pBegin);
        }

        public float[] getBeginPoint() {
            return new float[]{this.pBegin.x, this.pBegin.y};
        }

        public float[] getEndPoint() {
            return new float[]{this.pEnd.x, this.pEnd.y};
        }
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public void layout(IDisplayer iDisplayer, float f, float f2) {
        getRectAtTime(iDisplayer, this.mTimer.currMillisecond);
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public float[] getRectAtTime(IDisplayer iDisplayer, long j) {
        int i;
        LinePath linePath = null;
        if (!isMeasured()) {
            return null;
        }
        long actualTime = j - getActualTime();
        long j2 = this.alphaDuration;
        if (j2 > 0 && (i = this.deltaAlpha) != 0) {
            if (actualTime >= j2) {
                this.alpha = this.endAlpha;
            } else {
                this.alpha = this.beginAlpha + ((int) (i * (actualTime / j2)));
            }
        }
        float f = this.beginX;
        float f2 = this.beginY;
        long j3 = actualTime - this.translationStartDelay;
        long j4 = this.translationDuration;
        if (j4 > 0 && j3 >= 0 && j3 <= j4) {
            float f3 = j3 / j4;
            LinePath[] linePathArr = this.linePaths;
            if (linePathArr != null) {
                for (LinePath linePath2 : linePathArr) {
                    if (j3 >= linePath2.beginTime && j3 < linePath2.endTime) {
                        linePath = linePath2;
                        break;
                    }
                    f = linePath2.pEnd.x;
                    f2 = linePath2.pEnd.y;
                }
                if (linePath != null) {
                    float f4 = linePath.delatX;
                    float f5 = linePath.deltaY;
                    float f6 = (actualTime - linePath.beginTime) / linePath.duration;
                    float f7 = linePath.pBegin.x;
                    float f8 = linePath.pBegin.y;
                    if (f4 != 0.0f) {
                        f = f7 + (f4 * f6);
                    }
                    if (f5 != 0.0f) {
                        f2 = f8 + (f5 * f6);
                    }
                }
            } else {
                float f9 = this.deltaX;
                if (f9 != 0.0f) {
                    f += f9 * f3;
                }
                float f10 = this.deltaY;
                if (f10 != 0.0f) {
                    f2 += f10 * f3;
                }
            }
        } else if (j3 > j4) {
            f = this.endX;
            f2 = this.endY;
        }
        float[] fArr = this.currStateValues;
        fArr[0] = f;
        fArr[1] = f2;
        fArr[2] = f + this.paintWidth;
        this.currStateValues[3] = f2 + this.paintHeight;
        setVisibility(!isOutside());
        return this.currStateValues;
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public float getLeft() {
        return this.currStateValues[0];
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public float getTop() {
        return this.currStateValues[1];
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public float getRight() {
        return this.currStateValues[2];
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public float getBottom() {
        return this.currStateValues[3];
    }

    public void setTranslationData(float f, float f2, float f3, float f4, long j, long j2) {
        this.beginX = f;
        this.beginY = f2;
        this.endX = f3;
        this.endY = f4;
        this.deltaX = f3 - f;
        this.deltaY = f4 - f2;
        this.translationDuration = j;
        this.translationStartDelay = j2;
    }

    public void setAlphaData(int i, int i2, long j) {
        this.beginAlpha = i;
        this.endAlpha = i2;
        int i3 = i2 - i;
        this.deltaAlpha = i3;
        this.alphaDuration = j;
        if (i3 == 0 || i == AlphaValue.MAX) {
            return;
        }
        this.alpha = i;
    }

    public void setLinePathData(float[][] fArr) {
        LinePath[] linePathArr;
        if (fArr != null) {
            int length = fArr.length;
            int i = 0;
            float[] fArr2 = fArr[0];
            this.beginX = fArr2[0];
            this.beginY = fArr2[1];
            float[] fArr3 = fArr[length - 1];
            this.endX = fArr3[0];
            this.endY = fArr3[1];
            if (fArr.length > 1) {
                this.linePaths = new LinePath[fArr.length - 1];
                int i2 = 0;
                while (true) {
                    linePathArr = this.linePaths;
                    if (i2 >= linePathArr.length) {
                        break;
                    }
                    linePathArr[i2] = new LinePath();
                    LinePath linePath = this.linePaths[i2];
                    float[] fArr4 = fArr[i2];
                    Point point = new Point(fArr4[0], fArr4[1]);
                    i2++;
                    float[] fArr5 = fArr[i2];
                    linePath.setPoints(point, new Point(fArr5[0], fArr5[1]));
                }
                float distance = 0.0f;
                for (LinePath linePath2 : linePathArr) {
                    distance += linePath2.getDistance();
                }
                LinePath[] linePathArr2 = this.linePaths;
                int length2 = linePathArr2.length;
                LinePath linePath3 = null;
                while (i < length2) {
                    LinePath linePath4 = linePathArr2[i];
                    linePath4.duration = (long) ((linePath4.getDistance() / distance) * this.translationDuration);
                    linePath4.beginTime = linePath3 == null ? 0L : linePath3.endTime;
                    linePath4.endTime = linePath4.beginTime + linePath4.duration;
                    i++;
                    linePath3 = linePath4;
                }
            }
        }
    }
}
