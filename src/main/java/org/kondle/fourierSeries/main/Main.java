package org.kondle.fourierSeries.main;

import org.kondle.fourierSeries.math.FourierSeries;
import org.kondle.fourierSeries.math.Function;
import org.kondle.fourierSeries.math.PointFunction;
import org.kondle.fourierSeries.math.SeriesFunction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main
{

    static private Dimension size = new Dimension(0,0);

    public static void main(String[] args)
    {

        // pathFile framesCount scale [rotateCount]

        //TODO: check validation of args and alert about wrong parts

        Params params = new Params(args);


        System.out.println(params);




        File imgDir = new File("imgs");
        if (!imgDir.exists()) imgDir.mkdir();

        double[][] points;

        {
            StringBuilder pathStr = new StringBuilder();
            try (FileInputStream pathFileIS = new FileInputStream(params.pathFile))
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

        for (int i = 0; i < points.length; i++)
        {
            if (points[i][0] > size.width)
                size.width = (int) Math.ceil(points[i][0]);
            if (points[i][1] > size.height)
                size.height = (int) Math.ceil(points[i][1]);
        }

        Function pointF = new PointFunction(points);
        FourierSeries series = new FourierSeries(pointF);
        series.setCoeffCount(params.coefficientsCount);

        SeriesFunction seriesFunction = new SeriesFunction(series.calculateCoefficients());

        BufferedImage image;

        Stroke shapeStroke = new BasicStroke(2 * params.scale);


        int framesPerCircle = params.framesCount / params.rotateCount;

        for (int i = 0; i <= params.framesCount; i++)
        {
            image = new BufferedImage(size.width * params.scale,size.height * params.scale,BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = (Graphics2D) image.getGraphics();
            Color color;

            // draw path
            if (!params.hidePath)
            {
                g.setStroke(shapeStroke);
                int cond;

                if (i / (params.framesCount / params.rotateCount) == 0)
                {
                    cond = 0;
                }
                else
                {
                    cond = (i - framesPerCircle);
                }
                for (int i2 = i; i2 > cond; i2--)
                {
                    if (i / (params.framesCount / params.rotateCount) == 0)
                    {
                        color = new Color(255,255,255, (int) (255 * (i2 / (double)i)));
                    }
                    else
                    {
                        color = new Color(255,255,255, (int) (255 * ((i2 - (i - framesPerCircle)) / (double) framesPerCircle)));
                    }

                    g.setColor(color);
                    double[] pPoint = seriesFunction.getPoint((double) (i2 - 1) * params.rotateCount / params.framesCount);
                    double[] point = seriesFunction.getPoint((double) i2 * params.rotateCount / params.framesCount);
                    g.drawLine
                            (
                                    (int) (point[0] * 0.98 * params.scale),
                                    (int) (point[1] * 0.98 * params.scale),
                                    (int) (pPoint[0] * 0.98 * params.scale),
                                    (int) (pPoint[1] * 0.98 * params.scale)
                            );
                }
            }
            System.out.println(i);
            try
            {
                ImageIO.write(image,"png",new File("imgs/img" + i + ".png"));
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }



    }
}
