package org.kondle.fourierSeries.math;

public class SeriesFunction implements Function
{
    private double[][] coffs;
    private int coefDelay;

    public SeriesFunction(double[][] coffs)
    {
        this.coffs = coffs;
        this.coefDelay = coffs.length / 2;
    }

    public double[] getPoint(double time)
    {
        double x = 0;
        double y = 0;
        for (int i = 0; i < coffs.length; i++)
        {
            x += this.coffs[i][0] * Math.sin(2 * Math.PI * (i - this.coefDelay) * time);
            x += this.coffs[i][1] * Math.cos(2 * Math.PI * (i - this.coefDelay) * time);
            y += this.coffs[i][2] * Math.sin(2 * Math.PI * (i - this.coefDelay) * time);
            y += this.coffs[i][3] * Math.cos(2 * Math.PI * (i - this.coefDelay) * time);
        }
        return new double[]{x,y};
    }
}
