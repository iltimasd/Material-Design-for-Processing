import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import de.looksgood.ani.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class sliders extends PApplet {

Button button1;
Slider slider1;
Slider slider2; 
Fab action1;

public void setup() {
  
  pload();
  slider2 = new Slider(50, 150, 100);
  slider1 = new Slider(50, 50, 300);
  button1 = new Button("i'm a button!", 250, 100);
  action1=  new Fab(width/2,height/2);
}

public void draw() {
  background(255);
  slider1.display();
  slider2.display();
  button1.display();
  action1.display();


  button1.setColor("red");
  button1.raised=true;
  action1.setColor("deep purple");

  slider1.setColor("blue");
  slider2.setColor("teal");
}





public void mouseReleased() {
  slider1.MouseReleased();
  slider2.MouseReleased();
  button1.MouseReleased();
    action1.MouseReleased();
;
}


public void mousePressed() {
  slider1.MousePressed();
  slider2.MousePressed();
  button1.MousePressed();
  action1.MousePressed();

}

public void mouseDragged() {
  slider1.MouseDragged();
  slider2.MouseDragged();
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
  float g = 0.0f;
  boolean s;
  boolean l;
  boolean a1=true;
  boolean a2=true;
  boolean a3=true;

  boolean invertText=false;
  boolean raised=false;

  int light=g300;
  int medium=g500;
  int dark=g700;
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
  public void setColor(String input) {
    for (int i = 0; i < names.length; ++i) {
      if (names[i].equals(input)) {
        light=c[i][3];
        medium=c[i][5];
        dark=c[i][7];
      }
    }
  }

  public void update() {
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
          Ani.to(this, .3f, "rf", sqrt((w*w)+(h*h)));
        } else {
          Ani.to(this, .1f, "ty", -2);
          Ani.to(this, .1f, "g", 4);
        }
        a1=false;
      }
      if (!l) {
      }
    } else {
      if (a3==true) {
        Ani.to(this, .1f, "ty", 0);
        Ani.to(this, .1f, "g", 0);
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

  public void display() {
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
  public void ink() {
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
  public void MouseReleased() {
    l = false;
    a1=true;
    a2=true;
    a3=true;
  }
  public void MousePressed() {
    if (s) { 

      if (a2==true) {

        Ani.to(this, .3f, "rc", w);
        Ani.to(this, .3f, "o", 0);
        a2=false;
      }
      l = true;
    } else {
      l = false;
    }
  }
}


int g500=0xff9e9e9e;
int g700=0xff616161;
int g300=0xffe0e0e0;
int p500=0xff2196f3;
int p700=0xff64b5f6;
int p300=0xff1976d2;

PFont rob14;
Ani ani;

public void pload() {
  rob14=loadFont("Roboto-Medium-64.vlw");
  Ani.init(this);
  Ani.setDefaultEasing(Ani.QUART_IN_OUT);
}

class Slider { 
  float defw=16;
  float w=defw;
  float xpos;
  float ypos;
  float size;
  float val;
  int light=g300;
  int medium=g500;
  int dark=g700;
  boolean s;
  boolean l;
  // The Constructor is defined with arguments.
  Slider( float tempXpos, float tempYpos, float tempSize) { 
    xpos = tempXpos;
    ypos = tempYpos;
    size = tempSize;
  }
  public void setColor(String input) {
    for (int i = 0; i < names.length; ++i) {
      if (names[i].equals(input)) {
        light=c[i][3];
        medium=c[i][5];
        dark=c[i][7];
      }
    }
  }
  public void update() {
    val=constrain(val, 0, size);
    if (mouseX>xpos+val-(w/2) && mouseX<xpos+val+(w/2) && mouseY>ypos-(w/2) && mouseY<ypos+(w/2)) {
      s = true;  
      if (!l) {
      }
    } else {
      s = false;
    }
  }
  public void display() {
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
      strokeWeight(1.5f);
    }
    ellipse(xpos+val, ypos, w, w);
  }
  public float value() {
    return map(val, 0, size, 0, 1.0f);
  }
  public void MouseReleased() {
    l = false;
    Ani.to(this, 0.3f, "w", defw);
  }
  public void MousePressed() {
    if (s) { 
      l = true;
      Ani.to(this, 0.2f, "w", 24);
    } else {
      l = false;
    }
  }
  public void MouseDragged() {
    if (l) {
      val=mouseX-xpos;
    }
  }
}



class Fab {
  
  int light=g300;
  int medium=g500;
  int dark=g700;
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
  public void setColor(String input) {
    for (int i = 0; i < names.length; ++i) {
      if (names[i].equals(input)) {
        light=c[i][3];
        medium=c[i][5];
        dark=c[i][7];
      }
    }
  }

  public void update() {
        if (mouseX>x && mouseX<x+r && mouseY>y && mouseY<y+r) {
      s = true;
      a3=true;
      if (a1==true) {

          Ani.to(this, .1f, "ty", -2);
        
        a1=false;
      }
      if (!l) {
      }
    } else {
      if (a3==true) {
        Ani.to(this, .1f, "ty", 0);
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

  public void display() {
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
  public void ink() {
    ellipseMode(CENTER);
    fill(dark, o);
    ellipse(mouseX, mouseY, rc, rc);
  }
  public void MouseReleased() {
    l = false;
    a1=true;
    a2=true;
    a3=true;
  }
  public void MousePressed() {
    if (s) { 
      println("s: "+s);
      if (a2==true) {
        o=255;
        rc=0;
        Ani.to(this, .4f, "rc", r);
        Ani.to(this, .3f, "o", 0);
        a2=false;
      }
      l = true;
    } else {
      l = false;
    }
  }
}
String[] names={"red","pink","purple","deep purple","indigo","blue","light blue","cyan","teal","green","light green","lime","yellow","amber","orange","deep orange","brown","grey","blue grey"};
int[][] c = {
{0xffFFEBEE,0xffFFCDD2,0xffEF9A9A,0xffE57373,0xffEF5350,0xffF44336,0xffE53935,0xffD32F2F,0xffC62828,0xffB71C1C},
{0xffFCE4EC,0xffF8BBD0,0xffF48FB1,0xffF06292,0xffEC407A,0xffE91E63,0xffD81B60,0xffC2185B,0xffAD1457,0xff880E4F},
{0xffF3E5F5,0xffE1BEE7,0xffCE93D8,0xffBA68C8,0xffAB47BC,0xff9C27B0,0xff8E24AA,0xff7B1FA2,0xff6A1B9A,0xff4A148C},
{0xffEDE7F6,0xffD1C4E9,0xffB39DDB,0xff9575CD,0xff7E57C2,0xff673AB7,0xff5E35B1,0xff512DA8,0xff4527A0,0xff311B92},
{0xffE8EAF6,0xffC5CAE9,0xff9FA8DA,0xff7986CB,0xff5C6BC0,0xff3F51B5,0xff3949AB,0xff303F9F,0xff283593,0xff1A237E},
{0xffE3F2FD,0xffBBDEFB,0xff90CAF9,0xff64B5F6,0xff42A5F5,0xff2196F3,0xff1E88E5,0xff1976D2,0xff1565C0,0xff0D47A1},
{0xffE1F5FE,0xffB3E5FC,0xff81D4FA,0xff4FC3F7,0xff29B6F6,0xff03A9F4,0xff039BE5,0xff0288D1,0xff0277BD,0xff01579B},
{0xffE0F7FA,0xffB2EBF2,0xff80DEEA,0xff4DD0E1,0xff26C6DA,0xff00BCD4,0xff00ACC1,0xff0097A7,0xff00838F,0xff006064},
{0xffE0F2F1,0xffB2DFDB,0xff80CBC4,0xff4DB6AC,0xff26A69A,0xff009688,0xff00897B,0xff00796B,0xff00695C,0xff004D40},
{0xffE8F5E9,0xffC8E6C9,0xffA5D6A7,0xff81C784,0xff66BB6A,0xff4CAF50,0xff43A047,0xff388E3C,0xff2E7D32,0xff1B5E20},
{0xffF1F8E9,0xffDCEDC8,0xffC5E1A5,0xffAED581,0xff9CCC65,0xff8BC34A,0xff7CB342,0xff689F38,0xff558B2F,0xff33691E},
{0xffF9FBE7,0xffF0F4C3,0xffE6EE9C,0xffDCE775,0xffD4E157,0xffCDDC39,0xffC0CA33,0xffAFB42B,0xff9E9D24,0xff827717},
{0xffFFFDE7,0xffFFF9C4,0xffFFF59D,0xffFFF176,0xffFFEE58,0xffFFEB3B,0xffFDD835,0xffFBC02D,0xffF9A825,0xffF57F17},
{0xffFFF8E1,0xffFFECB3,0xffFFE082,0xffFFD54F,0xffFFCA28,0xffFFC107,0xffFFB300,0xffFFA000,0xffFF8F00,0xffFF6F00},
{0xffFFF3E0,0xffFFE0B2,0xffFFCC80,0xffFFB74D,0xffFFA726,0xffFF9800,0xffFB8C00,0xffF57C00,0xffEF6C00,0xffE65100},
{0xffFBE9E7,0xffFFCCBC,0xffFFAB91,0xffFF8A65,0xffFF7043,0xffFF5722,0xffF4511E,0xffE64A19,0xffD84315,0xffBF360C},
{0xffEFEBE9,0xffD7CCC8,0xffBCAAA4,0xffA1887F,0xff8D6E63,0xff795548,0xff6D4C41,0xff5D4037,0xff4E342E,0xff3E2723},
{0xffFAFAFA,0xffF5F5F5,0xffEEEEEE,0xffE0E0E0,0xffBDBDBD,0xff9E9E9E,0xff757575,0xff616161,0xff424242,0xff212121},
{0xffECEFF1,0xffCFD8DC,0xffB0BEC5,0xff90A4AE,0xff78909C,0xff607D8B,0xff546E7A,0xff455A64,0xff37474F,0xff263238}
};
int[] grey = {0xffFAFAFA,0xffF5F5F5,0xffEEEEEE,0xffE0E0E0,0xffBDBDBD,0xff9E9E9E,0xff757575,0xff616161,0xff424242,0xff212121};
/*

Ink masking
https://gist.github.com/atduskgreg/6835219

*/
  public void settings() {  size(400, 400); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sliders" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
