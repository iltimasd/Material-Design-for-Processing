import de.looksgood.ani.*;

color g500=#9e9e9e;
color g700=#616161;
color g300=#e0e0e0;
color p500=#2196f3;
color p700=#64b5f6;
color p300=#1976d2;

PFont rob14;
Ani ani;

void pload() {
  rob14=loadFont("Roboto-Medium-64.vlw");
  Ani.init(this);
  Ani.setDefaultEasing(Ani.QUART_IN_OUT);
}

class Button {

  int h=36;
  int rf=0;
  int rc=0;
  int x;
  int ty;
  int y;
  int o=255;

  float w;
  float m;
  float g = 0.0;
  boolean s;
  boolean l;
  boolean a1=true;
  boolean a2=true;
  boolean a3=true;
  boolean bang=false;
  boolean invertText=false;
  boolean raised=false;

  color light=g300;
  color medium=g500;
  color dark=g700;
  String text;
  float tw;
  PGraphics mask;
  PGraphics ink;

  Button(String temptext, int tempx, int tempy) {
    text=temptext.toUpperCase();
    x=tempx;
    y=tempy;
    textSize(14);
    ink=createGraphics(width, height);
  }
  void setColor(String input) {
    for (int i = 0; i < names.length; ++i) {
      if (names[i].equals(input)) {
        light=c[i][3];
        medium=c[i][5];
        dark=c[i][7];
      }
    }
  }

  void update() {
    tw=textWidth(text);
    if ((tw+32)<88) {
      m= 88-(tw+32);
    } else {
      m=0;
    }
    w=tw+m+32;

    if (mouseX>x && mouseX<x+w && mouseY>y && mouseY<y+h) {

      s = true;
      a3=true;
      if (a1==true) {
        if (!raised) {
          Ani.to(this, .3, "rf", sqrt((w*w)+(h*h)));
        } else {
          Ani.to(this, .1, "ty", -2);
          Ani.to(this, .1, "g", 4);
        }
        a1=false;
      }
      if (!l) {
      }
    } else {
      if (a3==true) {
        Ani.to(this, .1, "ty", 0);
        Ani.to(this, .1, "g", 0);
        ty=0;
        a3=false;
      }
      s = false;
      rf=0;
      rc=0;
      o=255;
      a2=true;
      a1=true;
    }
  }

  void display() {
    pushMatrix();
    translate(0, ty);
    update();
    noStroke();
    //fill(light);
    //rect(x, y, w, h, 3);
    if (raised) {
      for (int i=1; i<g; i++) {
        stroke(map(i, 1, g, 180, 255));
        line(x-i, y+h+i-1, x+w+i, y+h+i-1);
        line(x+w+i-1, y, x+w+i-1, y+h+i);
        line(x-i+1, y, x-i+1, y+h+i);
      }
    }
    ink();
    if (!raised) {
      fill(medium);
    } else {
      fill(255);
    }
    textAlign(CENTER, BASELINE);
    text(text, x+ (w/2), y+22);
    popMatrix();

  }
  void ink() {
    image(ink, 0, 0);
    ink.beginDraw();
    if (!raised) {

      ink.background(255);
    } else {
      ink.background(medium);
    }
    ink.ellipseMode(CENTER);
    ink.noStroke();
    if (!raised) {
      ink.fill(medium, 22);
    } else {
      ink.fill(0, 22);
    }
    ink.ellipse(x+w/2, y+h/2, rf, rf);

    ink.fill(dark, o);
    ink.ellipse(mouseX, mouseY, rc, rc);
    ink.endDraw();
    mask = createGraphics(width, height);
    mask.beginDraw();
    mask.background(0);
    mask.fill(255);
    mask.noStroke();
    mask.rect(x, y, tw+m+32, 36, 3);
    mask.endDraw();
    ink.mask(mask);
  }
  boolean bang(){
  return bang;
  }
  void MouseReleased() {
    l = false;
    a1=true;
    a2=true;
    a3=true;
    bang=false;
  }
  void MousePressed() {
    if (s) { 

      if (a2==true) {
        bang=true;
        o=255;
        rc=0;
        Ani.to(this, .3, "rc", w);
        Ani.to(this, .3, "o", 0);
        a2=false;
                bang=false;

      }
      l = true;
    } else {
      l = false;
    }
  }

    void MouseClicked() {
    if (s) { 
        bang=true;
      }
  }
}


class Slider { 
  float defw=16;
  float w=defw;
  float xpos;
  float ypos;
  float size;
  float val;
  color light=g300;
  color medium=g500;
  color dark=g700;
  boolean s;
  boolean l;
  // The Constructor is defined with arguments.
  Slider( float tempXpos, float tempYpos, float tempSize) { 
    xpos = tempXpos;
    ypos = tempYpos;
    size = tempSize;
  }
  void setColor(String input) {
    for (int i = 0; i < names.length; ++i) {
      if (names[i].equals(input)) {
        light=c[i][3];
        medium=c[i][5];
        dark=c[i][7];
      }
    }
  }
  void update() {
    val=constrain(val, 0, size);
    if (mouseX>xpos+val-(w/2) && mouseX<xpos+val+(w/2) && mouseY>ypos-(w/2) && mouseY<ypos+(w/2)) {
      s = true;  
      if (!l) {
      }
    } else {
      s = false;
    }
  }
  void display() {
    update();
    noStroke();
    fill(grey[5]);
    ellipseMode(CENTER);
    rect(xpos, ypos, size, 2);
    fill(medium);
    rect(xpos, ypos, val, 2);
    if (val!=0) {
      noStroke();
      fill(medium);
    } else {
      fill(grey[3]);
      stroke(grey[5]);
      strokeWeight(1.5);
    }
    ellipse(xpos+val, ypos, w, w);
  }
  float value() {
    return map(val, 0, size, 0, 1.0);
  }
  void MouseReleased() {
    l = false;
    Ani.to(this, 0.3, "w", defw);
  }
  void MousePressed() {
    if (s) { 
      l = true;
      Ani.to(this, 0.2, "w", 24);
    } else {
      l = false;
    }
  }
  void MouseDragged() {
    if (l) {
      val=mouseX-xpos;
    }
  }
}

class Card{
  float x;
  float y;
  float w;
  float h;
    float g = 4;
    boolean a = true;
  Card(float tempx, float tempy, float tempw,float temph){
    x=tempx;
    y=tempy;
    w=tempw;
    h=temph;
  }

  void display(){
      pushMatrix();
          for (int i=1; i<g; i++) {
        stroke(map(i, 1, g, 180, 255));
        line(x-i, y+h+i-1, x+w+i, y+h+i-1);
        line(x+w+i-1, y, x+w+i-1, y+h+i);
        line(x-i+1, y, x-i+1, y+h+i);
      }
      fill(255);
      rect(x, y, w, h);
          popMatrix();

  }
  void resize(float newW, float newH){
    if(a){
    Ani.to(this,0.3,"w",newW);
    Ani.to(this,0.4,"h",newH);
    a=false;
  }
  }

class Fab {
  
  color light=g300;
  color medium=g500;
  color dark=g700;
  int x;
  int y;
  int ty;
  int r=56;
  int appS=r+5;
  int g=16;
  int rf;
  int rc;
  int o=255;
    boolean s;
  boolean l;
  boolean a1=true;
  boolean a2=true;
  boolean a3=true;
  Fab( int tempx, int tempy) {
    x=tempx;
    y=tempy;
  }
  void setColor(String input) {
    for (int i = 0; i < names.length; ++i) {
      if (names[i].equals(input)) {
        light=c[i][3];
        medium=c[i][5];
        dark=c[i][7];
      }
    }
  }

  void update() {
        if (mouseX>x && mouseX<x+r && mouseY>y && mouseY<y+r) {
      s = true;
      a3=true;
      if (a1==true) {

          Ani.to(this, .1, "ty", -2);
        
        a1=false;
      }
      if (!l) {
      }
    } else {
      if (a3==true) {
        Ani.to(this, .1, "ty", 0);
        ty=0;
        a3=false;
      }
      s = false;
      rf=0;
      rc=0;
      a2=true;
      a1=true;
    }
  }

  void display() {
    pushMatrix();
    translate(0, ty);
    update();
    noStroke();
        ellipseMode(CENTER);
    fill(0,12);
    for (int i=0; i<g; i++ ) {
      ellipse(x+(r/2), y+6-(i/2)+(r/2), appS-i, appS-i);
    }
    fill(medium);
    ellipseMode(CORNER);
    ellipse(x, y, r, r);


     ink();    
    popMatrix();
  }
  void ink() {
    ellipseMode(CENTER);
    fill(dark, o);
    ellipse(mouseX, mouseY, rc, rc);
  }
  void MouseReleased() {
    l = false;
    a1=true;
    a2=true;
    a3=true;
  }
  void MousePressed() {
    if (s) { 
      if (a2==true) {
        o=255;
        rc=0;
        Ani.to(this, .3, "rc", r);
        Ani.to(this, .32, "o", 0);
        // if(Ani.to.isEnded()){
        //   println("true");
        // }
        a2=false;
      }
      l = true;
    } else {
      l = false;
    }
  }
}

}