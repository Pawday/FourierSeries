package org.kondle.fourierSeries.math;

public class Function
{
    private double[][] points;

    public Function(double[][] points)
    {
        this.points = points;
    }

    public double[] getPoint(double time)
    {
        double[] retPoint = new double[2];
        double[] prPoint = this.points[(int) Math.ceil(time * (this.points.length - 1)) - 1];
        double[] nextPoint = this.points[(int) Math.ceil(time * (this.points.length - 1))];

        double middlePointPos = (time * points.length) - Math.floor(time * points.length);

        retPoint[0] = (nextPoint[0] - prPoint[0]) * middlePointPos + prPoint[0];
        retPoint[1] = (nextPoint[1] - prPoint[1]) * middlePointPos + prPoint[1];

        return retPoint;
    }
}

