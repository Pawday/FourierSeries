package org.kondle.fourierSeries.main;

import org.kondle.fourierSeries.math.FourierSeries;
import org.kondle.fourierSeries.math.Function;
import org.kondle.fourierSeries.math.PointFunction;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {

        //TODO: check validation of args and alert about wrong parts

        File pathFile = new File(args[0]);
        int framesCount = Integer.parseInt(args[1]);

        Dimension resolution = new Dimension(Integer.parseInt(args[2].split("x")[0]),Integer.parseInt(args[2].split("x")[1]));

        File imgDir = new File("imgs");
        if (!imgDir.exists()) imgDir.mkdir();

        double[][] points;

        {
            StringBuilder pathStr = new StringBuilder();
            try (FileInputStream pathFileIS = new FileInputStream(pathFile))
            {
                int b;
                while ((b = pathFileIS.read()) != -1)
                {
                    pathStr.append((char)b);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            String[] pointsLines = pathStr.toString().split("\\r\\n");

            points = new double[pointsLines.length][2];
            for (int i = 0; i < pointsLines.length; i++)
            {
                String[] pointStrArr = pointsLines[i].split(",");
                points[i][0] = Double.parseDouble(pointStrArr[0]);
                points[i][1] = Double.parseDouble(pointStrArr[1]);
            }
        }

        Function pointF = new PointFunction(points);


        FourierSeries series = new FourierSeries(pointF);
        series.setCoeffCount(3);



    }
}
