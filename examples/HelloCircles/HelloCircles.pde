import com.myDomain.myLibrary.*;

RandomCircles circles;
SayHello hello;
Palette myPalette;
color[] palette;

void setup() {
  size(400,400);
  smooth();

  circles = new RandomCircles(this);
  hello = new SayHello(this);
  myPalette = new Palette(this);
  palette = myPalette.getPalette();
  
  PFont font = createFont("Arial", 40);
  textFont(font);
}

void draw() {
  background(0);
  noStroke();
  fill(palette[int(random(palette.length))]);
  circles.drawCircles();
  fill(255);
  text(hello.text(), 40, 200);
}
