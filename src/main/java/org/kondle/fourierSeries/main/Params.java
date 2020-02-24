package org.kondle.fourierSeries.main;

public class Params
{
    public String pathFile;
    public int framesCount;
    public int scale = 1;
    public int rotateCount = 1;
    public int coefficientsCount = 10;

    public boolean hideCircles = false;
    public boolean hideArrows = false;
    public boolean hidePath = false;
    public int additionalSides = 0;




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
                case "-h":
                    String[] hideObjects = args[i * 2 + 1].split(",");

                    for (int b = 0; b < hideObjects.length; b++)
                    {
                        if (hideObjects[b].equals("arrows")) {this.hideArrows = true; continue;}
                        if (hideObjects[b].equals("circles")) {this.hideCircles = true; continue;}
                        if (hideObjects[b].equals("path")) {this.hidePath = true;}
                    }
                break;
                case "-a":
                    this.additionalSides = Integer.parseInt(args[i * 2 + 1]);
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
