package com.myDomain.myLibrary;

import processing.core.PApplet;
import processing.core.PConstants;

import java.util.ArrayList;

/**
 * This is an example class within library myLibrary.
 * Make sure you rename this class as well as the name of the example
 * package 'com.myDomain.myLibrary' to your own library naming convention.
 */

public class Grid {

    // parent is a reference to the parent sketch, and to Processing commands
    PApplet parent;

    ArrayList<Dot> dots;

    /**
     * a Constructor, usually called in the setup() method in your sketch to
     * initialize and start the Library.
     *
     * @param theParent the parent PApplet
     * @param palette   an array of color values from the Palette class
     */
    public Grid(PApplet theParent, int[] palette) {

        // parent is a reference to the parent sketch, and to Processing commands
        parent = theParent;

        dots = new ArrayList<Dot>();

        int gridSizeY = (int) parent.random(3, 16);
        int gridSizeX = (int) (gridSizeY * ((float) parent.width / parent.height));
        float cellSizeY = parent.height / (float) gridSizeY;
        float cellSizeX = parent.width / (float) gridSizeX;
        float dotSpeed = parent.random(0.008F, 0.064F);

        for (int y = 0; y < gridSizeY; y++) {
            for (int x = 0; x < gridSizeX; x++) {
                float xpos = x * cellSizeX + cellSizeX / 2;
                float ypos = y * cellSizeY + cellSizeY / 2;
                int dotColor = palette[(int) parent.random(palette.length)];
                dots.add(new Dot(xpos, ypos, PApplet.min(cellSizeX, cellSizeY), dotSpeed, dotColor));
            }
        }
    }

    /**
     * show both updates and displays the grid by calling the
     * update and display methods of the inline Dot class (see below)
     */
    public void show() {
        for (Dot d : dots) {
            d.update();
            d.display();
        }
    }

    // Dot is an inline class of the Grid class.
    private class Dot {

        float xpos, ypos;
        float diameterOriginal, diameterCurrent;
        float pulseValue, pulseSpeed;
        int colorFill;

        /**
         * a Constructor called from the constructor of the parent Grid class.
         *
         * @param x horisontal position of the dot
         * @param y vertical position of the dot
         * @param d diameter of the dot
         * @param s pulse speed of the dot
         * @param c color of the dot
         */
        public Dot(float x, float y, float d, float s, int c) {
            xpos = x;
            ypos = y;
            diameterOriginal = d;
            diameterCurrent = diameterOriginal;
            pulseValue = parent.random(PConstants.TWO_PI);
            pulseSpeed = parent.random(s * 0.5F, s * 2.0F);
            colorFill = c;
        }

        /**
         * display draws a dot
         */
        public void display() {
            parent.pushStyle();
            parent.noStroke();
            parent.circle(xpos, ypos, diameterCurrent);
            parent.popStyle();
            parent.fill(colorFill);
        }

        /**
         * update updates the dots current diameter based on its pulse value and original diameter
         */
        public void update() {
            pulseValue += pulseSpeed;
            diameterCurrent = PApplet.map(PApplet.sin(pulseValue), -1, 1, diameterOriginal * 0.1F, diameterOriginal * 0.9F);
        }
    }
}
