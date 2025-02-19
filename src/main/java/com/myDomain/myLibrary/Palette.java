package com.myDomain.myLibrary;

import processing.core.*;

/**
 * This is an example class within library myLibrary.
 * Make sure you rename this class as well as the name of the example
 * package 'com.myDomain.myLibrary' to your own library naming convention.
 */

public class Palette {

    // parent is a reference to the parent sketch, and to Processing commands
    PApplet parent;

    /**
     * a Constructor, usually called in the setup() method in your sketch to
     * initialize and start the Library.
     *
     * @param theParent the parent PApplet
     */
    public Palette(PApplet theParent) {
        this.parent = theParent;
    }

    /**
     * getPalette returns a an array containing five colors defined as integers
     */
    public int[] getPalette() {
        int numColors = 5;
        int[] palette = new int[numColors];
        this.parent.pushStyle();
        this.parent.colorMode(PConstants.HSB, 1.0F, 1.0F, 1.0F);
        int mix = this.parent.color(this.parent.random(1.0F), 1.0F, 1.0F);
        float h, s, b;
        for (int i = 0; i < palette.length; i++) {
            h = (this.parent.random(1.0F) + this.parent.hue(mix)) / 2;
            s = this.parent.random(0.8F, 1.0F);
            b = this.parent.random(1.0F) < 0.2F ? 0.2F : 1.0F;
            palette[i] = this.parent.color(h, s, b);
        }
        this.parent.popStyle();
        return palette;
    }

}
