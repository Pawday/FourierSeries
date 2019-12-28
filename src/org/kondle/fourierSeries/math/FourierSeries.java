package org.kondle.fourierSeries.math;

public class FourierSeries
{
    private Function f;

    private int integrateLimit = 1000;
    private int coeffCount = 20;
    public FourierSeries(Function f)
    {
        this.f = f;
    }


    public void setIntegrateLimit(int integrateLimit)
    {
        this.integrateLimit = integrateLimit;
    }

    public void setCoeffCount(int coeffCount)
    {
        this.coeffCount = coeffCount;
    }

    public double[][] calculateCoefficients()
    {
        double[][] coffs = new double[coeffCount * 2 + 1][4];
        for (int i = 0; i < coffs.length; i++)
            for (int i2 = 0; i2 < 4; i2++)
                coffs[i][i2] = 0d;

        for(int i = 0; i < coffs.length; i++)
        {
            for (int igr = 1; igr <= integrateLimit; igr++)
            {
                coffs[i][0] += f.getPoint(igr / (double) integrateLimit)[0] *
                        Math.sin(2 * Math.PI * -(i - coeffCount) * igr / (double) integrateLimit) / integrateLimit;
                coffs[i][1] += f.getPoint(igr / (double) integrateLimit)[0] *
                        Math.cos(2 * Math.PI * -(i - coeffCount) * igr / (double) integrateLimit) / integrateLimit;
                coffs[i][2] += f.getPoint(igr / (double) integrateLimit)[1] *
                        Math.sin(2 * Math.PI * -(i - coeffCount) * igr / (double) integrateLimit) / integrateLimit;
                coffs[i][3] += f.getPoint(igr / (double) integrateLimit)[1] *
                        Math.cos(2 * Math.PI * -(i - coeffCount) * igr / (double) integrateLimit) / integrateLimit;
            }
        }
        return coffs;
    }
}
