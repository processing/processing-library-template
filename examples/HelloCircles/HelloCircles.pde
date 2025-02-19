import com.myDomain.myLibrary.*;

Palette p;
Grid g;

void setup() {
  size(1280, 720);
  p = new Palette(this);
  makeGrid();
}

void draw() {
  background(16);
  g.show();
  displayText();
}

void mouseClicked() {
  makeGrid();
}

void makeGrid() {
  g = new Grid(this, p.getPalette());
}

void displayText() {
  fill(240);
  textAlign(CENTER, CENTER);
  textSize(200);
  text("Hello Library", width / 2, height / 2);
}