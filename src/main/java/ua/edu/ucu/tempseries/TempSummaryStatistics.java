package ua.edu.ucu.tempseries;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter

public final class TempSummaryStatistics {
    private  final double average;
    private final double deviation;
    private final double minTemp;
    private final double maxTemp;

    public TempSummaryStatistics(double average, double deviation,
                                 double minTemp, double maxTemp) {
        this.average = average;
        this.deviation = deviation;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }

}