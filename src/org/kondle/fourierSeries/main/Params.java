package org.kondle.fourierSeries.main;

public class Params
{
    public String pathFile;
    public int framesCount;
    public int scale = 1;
    public int rotateCount = 1;
    public int coefficientsCount = 10;



    public Params(String[] args)
    {
        for (int i = 0; i < args.length / 2; i++)
        {
            switch (args[i * 2])
            {
                case "-f":
                    this.framesCount = Integer.parseInt(args[i * 2 + 1]);
                break;
                case "-s":
                    this.scale = Integer.parseInt(args[i * 2 + 1]);
                break;
                case "-r":
                    this.rotateCount = Integer.parseInt(args[i * 2 + 1]);
                break;
                case "-p":
                    this.pathFile = args[i * 2 + 1];
                break;
                case "-c":
                    this.coefficientsCount = Integer.parseInt(args[i * 2 + 1]);
                break;
            }
        }
    }


    @Override
    public String toString()
    {
        return "Params{" + "pathFile='" + pathFile + '\'' + ", framesCount=" + framesCount + ", scale=" + scale + ", rotateCount=" + rotateCount + ", coefficientsCount=" + coefficientsCount + '}';
    }
}
