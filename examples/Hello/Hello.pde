import library.template.*;

HelloLibrary hello;

void setup() {
  size(400,400);
  smooth();
  
  hello = new HelloLibrary(this);
  
  PFont font = createFont("Arial", 40);
  textFont(font);
}

void draw() {
  background(0);
  fill(255);
  text(hello.sayHello(), 40, 200);
}