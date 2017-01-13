////
/*
Code Finals- Code Snippets: Google Material Design for Processing (fka GUIcake)

A series of accessible and hackable classes to be used in any Processing sketch
simply add Material Design UI/UX

Parsons the New School for Design
Code 1|Fall 2016
iltimasd.github.io

***refer to credits.pde for attribution and dependencies

*/
////


Button button1;
Button button2;

Slider slider1;
Slider slider2; 
Card card1;
String buttonText="How to use button:\n\nCreate a variable with the data type button:\n\nButton firstButton;\n\nCreate the Button object using it's constructor:\n\nfirstButton(\"this is the button label\",x,y); \n//h and width are predetermined\n\nuse the display() method to show the button:\n\nfirstButton.display();\n\nyou can recolor the button using setColor():\n\nfirstButton.setColor(\"teal\");\n//refer to colors.pde for list of acceptable colors\n\nand you can use the button function by \nchecking if the bang() method is true:\n\n\tif (button1.bang()) {\n\t//do this\n\t}";
void setup() {
  size(800, 800);
  pload();
  slider2 = new Slider(150, 150, 100);
  slider1 = new Slider(150, 50, 150);
  button1 = new Button("I'm a button!!", 400, 415);
    button2 = new Button("read my mind", 400, 545);

  card1 = new Card(20,20,width-40,height-40);
}

void draw() {
  //for live code purposes
  button1.text="I'm a button!!!!!!!!!!";
  //
  
  
  background(255);
  card1.display();
  fill(0);
  textAlign(CORNER);
  text(buttonText, 40, 60);
  button1.display();
  if (button1.bang()) {
    button1.setColor("red");
      card1.resize(200,200);
  }
  button1.raised=true;
      button2.setColor("teal");

    button2.display();
    if (button2.bang()) {
       text("true", 450, 610);

    } else {
       text("false", 450, 610);

    }
     
  slider2.display();
  slider2.setColor("red");
    rect(slider2.value()*width,300,30,30);
}





void mouseReleased() {
  slider1.MouseReleased();
  slider2.MouseReleased();
  button1.MouseReleased();
    button2.MouseReleased();

}

void mouseClicked(){
    button1.MouseClicked();
        button2.MouseClicked();

}

void mousePressed() {
  slider1.MousePressed();
  slider2.MousePressed();
  button1.MousePressed();
    button2.MousePressed();

}

void mouseDragged() {
  slider1.MouseDragged();
  slider2.MouseDragged();
}