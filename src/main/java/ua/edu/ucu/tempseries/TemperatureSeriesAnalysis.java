package ua.edu.ucu.tempseries;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {

    static final int MINVALUE = -273;
    private double[] series;
    private int size;

    public TemperatureSeriesAnalysis() {
        this.series = new double[] {};
        this.size = 0;
    }

    public TemperatureSeriesAnalysis(double[] series) {
        this.series = series;
        this.size = series.length;
        for (int i = 0; i < size; i++) {
            if (series[i] < MINVALUE) {
                throw new InputMismatchException("Its too cold here!");
            }
        }
    }

    private void IsEmpty() {
        if (this.size == 0) {
            throw new IllegalArgumentException("No temperature data available");
        }
    }

    public double average() {

        IsEmpty();
        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum += series[i];
        }
        return sum / this.size;
    }

    public double deviation() {
        IsEmpty();
        double sum = 0;
        for (int i = 0; i < size; i++) {
            double difference = series[i] - average();
            sum += difference*difference;
        }
        return Math.sqrt(sum / size);
    }

    public double min() {
        IsEmpty();
        double min = series[0];
        for (int i = 0; i < size; i++) {
            if (series[i] < min) {
                min = series[i];
            }
        }
        return min;
    }

    public double max() {
        IsEmpty();
        double max = series[0];
        for (int i = 0; i < size; i++) {
            if (series[i] > max) {
                max = series[i];
            }
        }
        return max;
    }

    public double findTempClosestToZero() {
        IsEmpty();
        double closest = series[0];
        for (int i = 0; i < size; i++) {
            if (Math.abs(series[i]) < Math.abs(closest)) {
                closest = series[i];
            }
        }
        return closest;
    }

    public double findTempClosestToValue(double value) {
        IsEmpty();
        double minDif = Math.abs(series[0]-value);
        double diff = 0, closest = 0;
        for (int i = 0; i < size; i++) {
            diff = series[i] - value;
            if (Math.abs(diff) < minDif) {
                minDif = diff;
                closest = series[i];
            }
            else if (diff > minDif && Math.abs(diff) == minDif) {
                minDif = diff;
                closest = series[i];
            }
        }
        return closest;
    }

    public double[] findTempsLessThen(double tempValue) {
        IsEmpty();
        double[] lessTemps = new double[size];
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (series[i] < tempValue) {
                    lessTemps[j] = series[i];
                    j++;
                }
            }
            return lessTemps;
        }

    public double[] findTempsGreaterThen(double tempValue) {
        IsEmpty();
        double[] lessTemps = new double[size];
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (series[i] > tempValue) {
                lessTemps[j] = series[i];
                j++;
            }
        }
        return lessTemps;
    }

    public TempSummaryStatistics summaryStatistics() {
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        for (int i = 0; i < temps.length; i++) {
            if (temps[i] < MINVALUE) {
                throw new InputMismatchException("Its too cold here!");
            }
        }
        if (series.length - size <= temps.length){
            double[] newTemps = new double[2*(size + temps.length)];
            System.arraycopy(series, 0, newTemps, 0, size);
            series = newTemps;
        }
        for (double temp: temps) {
            series[size] = temp;
            size++;
        }

        int tempSum = 0;
        for (int i = 0; i < size; i++) {
            tempSum += series[i];
        }
        return tempSum;


    }
}
