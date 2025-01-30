package com.myDomain.myLibrary;

import processing.core.*;
//import static java.lang.Math.random;

public class Palette {

   PApplet parent;

    public Palette(PApplet theParent) { this.parent = theParent; }
    public int[] getPalette() {
        int numColors = 5;
        int[] palette = new int[numColors];
        this.parent.pushStyle();
        this.parent.colorMode(PConstants.HSB, 1.0F, 1.0F, 1.0F);
        int mix = this.parent.color(this.parent.random(1.0F), 1.0F, 1.0F);
        float h, s, b;
        for (int i=0; i<palette.length; i++) {
            h = (this.parent.random(1.0F) + this.parent.hue(mix)) / 2;
            s = this.parent.random(0.8F, 1.0F);
            b = this.parent.random(1.0F) < 0.2F ? 0.2F : 1.0F;
            palette[i] = this.parent.color(h, s, b);
        }
        this.parent.popStyle();
        return palette;
    }

}
