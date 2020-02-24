package org.kondle.fourierSeries.math;

import java.util.Arrays;

public class PointFunction implements Function
{
    private double[][] points;

    public PointFunction(double[][] points)
    {
        this.points = points;
    }

    public double[] getPoint(double time)
    {
        double dIndex = time * (this.points.length - 1);
        int pPointIndex = (int) Math.floor(dIndex);
        double[] prevPoint = this.points[pPointIndex];
        double[] nextPoint = this.points[(int) Math.ceil(dIndex)];
        double diff = dIndex - pPointIndex;
        double[] cordDiffs = {nextPoint[0] - prevPoint[0],nextPoint[1] - prevPoint[1]};
        return new double[]{prevPoint[0] + cordDiffs[0] * diff,prevPoint[1] + cordDiffs[1] * diff};
    }
}

