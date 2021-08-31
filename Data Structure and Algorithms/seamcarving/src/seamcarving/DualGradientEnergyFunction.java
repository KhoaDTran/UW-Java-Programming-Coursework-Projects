package seamcarving;

import edu.princeton.cs.algs4.Picture;

import java.awt.*;

public class DualGradientEnergyFunction implements EnergyFunction {
    private boolean horizontalCheck;
    @Override
    public double apply(Picture picture, int x, int y) {
        if (x < 0 || x >= picture.width() || y < 0 || y >= picture.height()) { //check this line of code
            throw new IndexOutOfBoundsException();
        }
        Color leftColor = null;
        Color rightColor;
        double xGradChange = 0.0;
        double yGradChange = 0.0;
        boolean verticalPath = false;
        boolean xforward = false;
        boolean xbackwards = false;
        if (x == 0 && picture.width() == 1) {
            verticalPath = true;
        } else if (x == 0) {
            xforward = true;
            leftColor  = picture.get(x + 1, y);
        } else {
            leftColor = picture.get(x - 1, y);
        }
        if (x == picture.width() - 1) {
            xbackwards = true;
            rightColor = picture.get(x - 2, y);
        } else if (x == 0) {
            rightColor = picture.get(x + 2, y);
        } else {
            rightColor = picture.get(x + 1, y);
        }
        if (!xforward && !xbackwards) {
            double redChange = rightColor.getRed() - leftColor.getRed();
            double greenChange = rightColor.getGreen() - leftColor.getGreen();
            double blueChange = rightColor.getBlue() - leftColor.getBlue();
            xGradChange = (redChange * redChange) + (greenChange * greenChange) + (blueChange * blueChange);
        } else {
            double redChange = -3 * picture.get(x, y).getRed() + 4 * leftColor.getRed() - rightColor.getRed();
            double greenChange = -3 * picture.get(x, y).getGreen() + 4 * leftColor.getGreen() - rightColor.getGreen();
            double blueChange = -3 * picture.get(x, y).getBlue() + 4 * leftColor.getBlue() - rightColor.getBlue();
            xGradChange = (redChange * redChange) + (greenChange * greenChange) + (blueChange * blueChange);
        }
        yGradChange = doY(picture, x, y);
        if (verticalPath) {
            xGradChange = 0;
        }
        if (horizontalCheck) {
            yGradChange = 0;
        }
        double energy = Math.sqrt(xGradChange+ yGradChange);
        return energy;
    }


    private double doY(Picture picture, int x, int y) {
        boolean yforward = false;
        boolean ybackwards = false;
        boolean horizontalPath = false;
        double yGradChange = 0.0;
        Color upperColor = null;
        Color lowerColor = null;
        if (y == 0 && picture.height() == 1) {
            horizontalPath = true;
        } else if (y == 0) {
            yforward = true;
            lowerColor = picture.get(x, y + 1);
        } else {
            lowerColor = picture.get(x, y - 1);
        }
        if (y == picture.height() - 1) {
            ybackwards = true;
            upperColor = picture.get(x, y - 2);
        } else if (y == 0) {
            upperColor = picture.get(x, y + 2);
        } else {
            upperColor = picture.get(x, y + 1);
        }
        if (!yforward && !ybackwards) {
            double redChange = upperColor.getRed() - lowerColor.getRed();
            double greenChange = upperColor.getGreen() - lowerColor.getGreen();
            double blueChange = upperColor.getBlue() - lowerColor.getBlue();
            yGradChange = (redChange * redChange) + (greenChange * greenChange) + (blueChange * blueChange);
        } else {
            double redChange = -3 * picture.get(x, y).getRed() + 4 * lowerColor.getRed() - upperColor.getRed();
            double greenChange = -3 * picture.get(x, y).getGreen() + 4 * lowerColor.getGreen() - upperColor.getGreen();
            double blueChange = -3 * picture.get(x, y).getBlue() + 4 * lowerColor.getBlue() - upperColor.getBlue();
            yGradChange = (redChange * redChange) + (greenChange * greenChange) + (blueChange * blueChange);
        }
        this.horizontalCheck = horizontalPath;
        return yGradChange;
    }
}
