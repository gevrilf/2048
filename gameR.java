package game;
// Gevril Fernandez
import processing.core.*;

import java.awt.*;

public class gameR extends PApplet {
	int side = 20, cs = 100, score = 0, dead = 0;
	static int jr=3, c3=0, c2=0, t=0, t1=0, g=0, gz=0;
	int[][] gameBoard = new int[4][4];
	int[][] bac = new int[4][4];
	static boolean b = true;
	static boolean b2 = true;


	public void setup() {
	  size(500, 500);
	  newGame();
	}

	public void draw() {
	  background(255);
	  fill(150);
	  rect(0, 0, width, height, 10);
	  drawGrid();
	  label("score: " + score, 10, 10, 100, 50, 0, 10, LEFT);
	  if(canMove()==false)//
		  gameOverMessage();
	}
	public void spawn() { // spawns new value in random empty locations
	  t=(int)random(0, 4);
	  t1=(int)random(0, 4);
	  while (gameBoard[t][t1]!=0) {
	    t=(int)random(0, 4);
	    t1=(int)random(0, 4);
	  }
	  int val=random(0, 1)<.9?2:4;
	  gameBoard[t][t1]=val;
	  println("spawned x: " + t + " y: " +t1);
	  t1=0;
	  t=0;
	}
	public void mousePressed(){ // starts new game
		if(mousePressed==true)
			newGame();
	}

	public void keyPressed() { // calls move method for the array
	  for(int z=0;z<4;z++)
	  for(int x=0;x<4;x++)
	  bac[z][x]=gameBoard[z][x];
	  if (keyCode==39) {
	    moveRight();
		compareArrays();
		if(b2==false)
		spawn();
	  }
	  else if (keyCode==37) {
	    moveLeft();
	    compareArrays();
	    if(b2==false)
	    spawn();
	  }
	  else if (keyCode==40) {
	    moveDown();
	    compareArrays();
	    if(b2==false)
	    spawn();
	  }
	  else if (keyCode==38) {
	    moveUp();
	    compareArrays();
	    if(b2==false)
	    spawn();
	  }
	  b2=true;
	}
	
	public boolean canMove(){
		jr=3; 
		  c2=0; 
		  c3=0;
		  for (int i=0; i<4;i++) {
		    for (int j=4; j>0; j--) {

		      if (jr==2&&gameBoard[i][jr+1]==gameBoard[i][jr]&&gameBoard[i][jr]!=0) { //merge 2-3
		        return true;
		      }
		      else if (jr==2&&gameBoard[i][jr+1]==0 ) {// move if empty
		    	  return true;
		      }
		      else if (jr==1&&gameBoard[i][jr+1]==0&&gameBoard[i][jr+2]==0) { // move if spot 3 if empty
		    	  return true;
		      }
		      else if (jr==1&&gameBoard[i][jr+1]==0&&gameBoard[i][jr+2]==gameBoard[i][jr]&&c3==0) { // merge spot 1-3 if same
		    	  return true;
		      }
		      else if (jr==1&&gameBoard[i][jr+1]==gameBoard[i][jr]&&gameBoard[i][jr]!=0) {// merge 1-2
		    	  return true;
		      }
		      else if (jr==1&&gameBoard[i][jr+1]==0) {// move 1-2
		    	  return true;
		      }
		      else if (jr==0&&gameBoard[i][jr+1]==0&&gameBoard[i][jr+2]==0&&gameBoard[i][jr+3]==0) {// move to spot 3
		    	  return true;
		      }
		      else if (jr==0&&gameBoard[i][jr+1]==0&&gameBoard[i][jr+2]==0&&gameBoard[i][jr+3]==gameBoard[i][jr]&&c3==0) {// merge 0 - 3
		    	  return true;
		      }
		      else if (jr==0&&gameBoard[i][jr+1]==0&&gameBoard[i][jr+2]==gameBoard[i][jr]&&c2==0) {//merge 0-2
		    	  return true;
		      }
		      else if (jr==0&&gameBoard[i][jr+1]==0&&gameBoard[i][jr+2]==0) {// move spot 2
		    	  return true;
		      }
		      else if (jr==0&&gameBoard[i][jr+1]==gameBoard[i][jr]) {//merge 0-1
		    	  return true;
		      }
		      else if (jr==0&&gameBoard[i][jr+1]==0) {
		    	  return true;
		      }
		      jr--;
		    }
		    c2=0; 
		    c3=0;
		    jr=3;
		  }
		  
		  jr=0; 
		  c2=0; 
		  c3=0;
		  for (int i=0; i<4;i++) {
		    for (int j=0; j<4; j++) {

		      if (jr==1&&gameBoard[i][jr-1]==gameBoard[i][jr]&&gameBoard[i][jr]!=0) { //merge 2-3
		    	  return true;
		      }
		      else if (jr==1&&gameBoard[i][jr-1]==0 ) {// move if empty
		    	  return true;
		      }
		      else if (jr==2&&gameBoard[i][jr-1]==0&&gameBoard[i][jr-2]==0) { // move if spot 3 if empty
		    	  return true;
		      }
		      else if (jr==2&&gameBoard[i][jr-1]==0&&gameBoard[i][jr-2]==gameBoard[i][jr]&&c3==0) { // merge spot 1-3 if same
		    	  return true;
		      }
		      else if (jr==2&&gameBoard[i][jr-1]==gameBoard[i][jr]&&gameBoard[i][jr]!=0) {// merge 1-2
		    	  return true;
		      }
		      else if (jr==2&&gameBoard[i][jr-1]==0) {// move 1-2
		    	  return true;
		      }
		      else if (jr==3&&gameBoard[i][jr-1]==0&&gameBoard[i][jr-2]==0&&gameBoard[i][jr-3]==0) {// move to spot 3
		    	  return true;
		      }
		      else if (jr==3&&gameBoard[i][jr-1]==0&&gameBoard[i][jr-2]==0&&gameBoard[i][jr-3]==gameBoard[i][jr]&&c3==0) {// merge 0 - 3
		    	  return true;
		      }
		      else if (jr==3&&gameBoard[i][jr-1]==0&&gameBoard[i][jr-2]==gameBoard[i][jr]&&c2==0) {//merge 0-2
		    	  return true;
		      }
		      else if (jr==3&&gameBoard[i][jr-1]==0&&gameBoard[i][jr-2]==0) {// move spot 2
		    	  return true;
		      }
		      else if (jr==3&&gameBoard[i][jr-1]==gameBoard[i][jr]) {//merge 0-1
		    	  return true;
		      }
		      else if (jr==3&&gameBoard[i][jr-1]==0) {
		    	  return true;
		      }
		      jr++;
		    }
		    c2=0; 
		    c3=0;
		    jr=0;
		  }
		  
		  jr=0; 
		  c2=0; 
		  c3=0;
		  for (int i=0; i<4;i++) {
		    for (int j=0; j<4; j++) {

		      if (jr==1&&gameBoard[jr-1][i]==gameBoard[jr][i]&&gameBoard[jr][i]!=0) { //merge 2-3
		    	  return true;
		      }
		      else if (jr==1&&gameBoard[jr-1][i]==0 ) {// move if empty
		    	  return true;
		      }
		      else if (jr==2&&gameBoard[jr-1][i]==0&&gameBoard[jr-2][i]==0) { // move if spot 3 if empty
		    	  return true;
		      }
		      else if (jr==2&&gameBoard[jr-1][i]==0&&gameBoard[jr-2][i]==gameBoard[jr][i]&&c3==0) { // merge spot 1-3 if same
		    	  return true;
		      }
		      else if (jr==2&&gameBoard[jr-1][i]==gameBoard[jr][i]&&gameBoard[jr][i]!=0) {// merge 1-2
		    	  return true;
		      }
		      else if (jr==2&&gameBoard[jr-1][i]==0) {// move 1-2
		    	  return true;
		      }
		      else if (jr==3&&gameBoard[jr-1][i]==0&&gameBoard[jr-2][i]==0&&gameBoard[jr-3][i]==0) {// move to spot 3
		    	  return true;
		      }
		      else if (jr==3&&gameBoard[jr-1][i]==0&&gameBoard[jr-2][i]==0&&gameBoard[jr-3][i]==gameBoard[jr][i]&&c3==0) {// merge 0 - 3
		    	  return true;
		      }
		      else if (jr==3&&gameBoard[jr-1][i]==0&&gameBoard[jr-2][i]==gameBoard[jr][i]&&c2==0) {//merge 0-2
		    	  return true;
		      }
		      else if (jr==3&&gameBoard[jr-1][i]==0&&gameBoard[jr-2][i]==0) {// move spot 2
		    	  return true;
		      }
		      else if (jr==3&&gameBoard[jr-1][i]==gameBoard[jr][i]) {//merge 0-1
		    	  return true;
		      }
		      else if (jr==3&&gameBoard[jr-1][i]==0) {
		    	  return true;
		      }
		      jr++;
		    }
		    c2=0; 
		    c3=0;
		    jr=0;
		  }
		  
		  jr=3; 
		  c2=0; 
		  c3=0;
		  for (int i=0; i<4;i++) {
		    for (int j=4; j>0; j--) {

		      if (jr==2&&gameBoard[jr+1][i]==gameBoard[jr][i]&&gameBoard[jr][i]!=0) { //merge 2-3
		    	  return true;
		      }
		      else if (jr==2&&gameBoard[jr+1][i]==0 ) {// move if empty
		    	  return true;
		      }
		      else if (jr==1&&gameBoard[jr+1][i]==0&&gameBoard[jr+2][i]==0) { // move if spot 3 if empty
		    	  return true;
		      }
		      else if (jr==1&&gameBoard[jr+1][i]==0&&gameBoard[jr+2][i]==gameBoard[jr][i]&&c3==0) { // merge spot 1-3 if same
		    	  return true;
		      }
		      else if (jr==1&&gameBoard[jr+1][i]==gameBoard[jr][i]&&gameBoard[jr][i]!=0) {// merge 1-2
		    	  return true;
		      }
		      else if (jr==1&&gameBoard[jr+1][i]==0) {// move 1-2
		    	  return true;
		      }
		      else if (jr==0&&gameBoard[jr+1][i]==0&&gameBoard[jr+2][i]==0&&gameBoard[jr+3][i]==0) {// move to spot 3
		    	  return true;
		      }
		      else if (jr==0&&gameBoard[jr+1][i]==0&&gameBoard[jr+2][i]==0&&gameBoard[jr+3][i]==gameBoard[jr][i]&&c3==0) {// merge 0 - 3
		    	  return true;
		      }
		      else if (jr==0&&gameBoard[jr+1][i]==0&&gameBoard[jr+2][i]==gameBoard[jr][i]&&c2==0) {//merge 0-2
		    	  return true;
		      }
		      else if (jr==0&&gameBoard[jr+1][i]==0&&gameBoard[jr+2][i]==0) {// move spot 2
		    	  return true;
		      }
		      else if (jr==0&&gameBoard[jr+1][i]==gameBoard[jr][i]) {//merge 0-1
		    	  return true;
		      }
		      else if (jr==0&&gameBoard[jr+1][i]==0) {
		    	  return true;
		      }
		      
		      jr--;
		    }
		    c2=0; 
		    c3=0;
		    jr=3;
		  }
		
		return false;
	}
	
	public void compareArrays() { // compares arrays 
	    
	    for (int i = 0; i < 4; i++) {

	        for (int a = 0; a < 4; a++) {

	            if (gameBoard[i][a] == bac[i][a]) {
	                b = true;
	            
	            } else {
	                b = false;
	                b2=false;
	           
	            }
	        	   
	        }
	    }       
	}
	  
	

	public void drawGrid() { // draws the 16 rectangles on the board

	  for (int j = 0; j < gameBoard.length; j++)
	    for (int i = 0; i < gameBoard[j].length; i++) {
	      float x = side + (side + cs) * i;
	      float y = side + (side + cs) * j;

	      if (gameBoard[j][i] < 1) {
	        fill(180);
	        rect(x, y, cs, cs, 5);
	      } 
	      else if (gameBoard[j][i] < 9) {
	        fill(245);
	        rect(x, y, cs, cs, 5);
	        label("" + gameBoard[j][i], x, y + 22, cs, cs, 0, 30, CENTER);
	      } 
	      else if (gameBoard[j][i] < 128) {
	        fill(255, 0, 0);
	        rect(x, y, cs, cs, 5);
	        label("" + gameBoard[j][i], x, y + 22, cs, cs, 0, 30, CENTER);
	      } 
	      else if (gameBoard[j][i] < 1024) {
	        fill(0, 255, 0);
	        rect(x, y, cs, cs, 5);
	        label("" + gameBoard[j][i], x, y + 22, cs, cs, 0, 30, CENTER);
	      } 
	      else {
	        fill(0, 0, 255);
	        rect(x, y, cs, cs, 5);
	        label("" + gameBoard[j][i], x, y + 22, cs, cs, 0, 30, CENTER);
	      }
	    }
	}

	void label(String t, float x, float y, float w, float h, float c, float s, int align) { // custom text method
	  fill(c);
	  textAlign(align);
	  textSize(s);
	  text(t, x, y, w, h);
	}

	public void newGame() { //generates random 2 point son grid
	  gameBoard = new int[4][4];
	  gameBoard[(int)random(0, 4)][(int)random(0, 4)]= 2;
	  gameBoard[(int)random(0, 4)][(int)random(0, 4)]= 2;
	  dead=0;
	  score=0;
	}
	
	public void gameOverMessage(){ //makes a transparent square throughout whole screen
		fill(255,155);
		rect(0,0,width,height);
		label("GAME OVER CLICK YOUR MOUSE TO RESTART", width/3, height/3, 200, 200, 0, 20, LEFT);
	}
	




	public void moveRight() {
	  jr=3; 
	  c2=0; 
	  c3=0;
	  for (int i=0; i<4;i++) {
	    for (int j=4; j>0; j--) {

	      if (jr==2&&gameBoard[i][jr+1]==gameBoard[i][jr]&&gameBoard[i][jr]!=0) { //merge 2-3
	        gameBoard[i][jr+1]+=gameBoard[i][jr];
	        score+=gameBoard[i][jr+1];
	        gameBoard[i][jr]=0;
	        c3=1;
	      }
	      else if (jr==2&&gameBoard[i][jr+1]==0 ) {// move if empty
	        gameBoard[i][jr+1]=gameBoard[i][jr];
	        gameBoard[i][jr]=0;
	      }
	      else if (jr==1&&gameBoard[i][jr+1]==0&&gameBoard[i][jr+2]==0) { // move if spot 3 if empty
	        gameBoard[i][jr+2]=gameBoard[i][jr];
	        gameBoard[i][jr]=0;
	      }
	      else if (jr==1&&gameBoard[i][jr+1]==0&&gameBoard[i][jr+2]==gameBoard[i][jr]&&c3==0) { // merge spot 1-3 if same
	        gameBoard[i][jr+2]+=gameBoard[i][jr];
	        score+=gameBoard[i][jr+2];
	        gameBoard[i][jr]=0;
	      }
	      else if (jr==1&&gameBoard[i][jr+1]==gameBoard[i][jr]&&gameBoard[i][jr]!=0) {// merge 1-2
	        gameBoard[i][jr+1]+=gameBoard[i][jr];
	        score+=gameBoard[i][jr+1];
	        gameBoard[i][jr]=0;
	        c2=1;
	      }
	      else if (jr==1&&gameBoard[i][jr+1]==0) {// move 1-2
	        gameBoard[i][jr+1]=gameBoard[i][jr];
	        gameBoard[i][jr]=0;
	      }
	      else if (jr==0&&gameBoard[i][jr+1]==0&&gameBoard[i][jr+2]==0&&gameBoard[i][jr+3]==0) {// move to spot 3
	        gameBoard[i][jr+3]=gameBoard[i][jr];
	        gameBoard[i][jr]=0;
	      }
	      else if (jr==0&&gameBoard[i][jr+1]==0&&gameBoard[i][jr+2]==0&&gameBoard[i][jr+3]==gameBoard[i][jr]&&c3==0) {// merge 0 - 3
	        gameBoard[i][jr+3]+=gameBoard[i][jr];
	        score+=gameBoard[i][jr+3];
	        gameBoard[i][jr]=0;
	      }
	      else if (jr==0&&gameBoard[i][jr+1]==0&&gameBoard[i][jr+2]==gameBoard[i][jr]&&c2==0) {//merge 0-2
	        gameBoard[i][jr+2]+=gameBoard[i][jr];
	        score+=gameBoard[i][jr+2];
	        gameBoard[i][jr]=0;
	      }
	      else if (jr==0&&gameBoard[i][jr+1]==0&&gameBoard[i][jr+2]==0) {// move spot 2
	        gameBoard[i][jr+2]=gameBoard[i][jr];
	        gameBoard[i][jr]=0;
	      }
	      else if (jr==0&&gameBoard[i][jr+1]==gameBoard[i][jr]) {//merge 0-1
	        gameBoard[i][jr+1]+=gameBoard[i][jr];
	        score+=gameBoard[i][jr+1];
	        gameBoard[i][jr]=0;
	      }
	      else if (jr==0&&gameBoard[i][jr+1]==0) {
	        gameBoard[i][jr+1]=gameBoard[i][jr];
	        gameBoard[i][jr]=0;
	      }
	      jr--;
	    }
	    c2=0; 
	    c3=0;
	    jr=3;
	  }
	}

	public void moveLeft() {
	  jr=0; 
	  c2=0; 
	  c3=0;
	  for (int i=0; i<4;i++) {
	    for (int j=0; j<4; j++) {

	      if (jr==1&&gameBoard[i][jr-1]==gameBoard[i][jr]&&gameBoard[i][jr]!=0) { //merge 2-3
	        gameBoard[i][jr-1]+=gameBoard[i][jr];
	        score+=gameBoard[i][jr-1];
	        gameBoard[i][jr]=0;
	        c3=1;
	      }
	      else if (jr==1&&gameBoard[i][jr-1]==0 ) {// move if empty
	        gameBoard[i][jr-1]=gameBoard[i][jr];
	        gameBoard[i][jr]=0;
	      }
	      else if (jr==2&&gameBoard[i][jr-1]==0&&gameBoard[i][jr-2]==0) { // move if spot 3 if empty
	        gameBoard[i][jr-2]=gameBoard[i][jr];
	        gameBoard[i][jr]=0;
	      }
	      else if (jr==2&&gameBoard[i][jr-1]==0&&gameBoard[i][jr-2]==gameBoard[i][jr]&&c3==0) { // merge spot 1-3 if same
	        gameBoard[i][jr-2]+=gameBoard[i][jr];
	        score+=gameBoard[i][jr-2];
	        gameBoard[i][jr]=0;
	      }
	      else if (jr==2&&gameBoard[i][jr-1]==gameBoard[i][jr]&&gameBoard[i][jr]!=0) {// merge 1-2
	        gameBoard[i][jr-1]+=gameBoard[i][jr];
	        score+=gameBoard[i][jr-1];
	        gameBoard[i][jr]=0;
	        c2=1;
	      }
	      else if (jr==2&&gameBoard[i][jr-1]==0) {// move 1-2
	        gameBoard[i][jr-1]=gameBoard[i][jr];
	        gameBoard[i][jr]=0;
	      }
	      else if (jr==3&&gameBoard[i][jr-1]==0&&gameBoard[i][jr-2]==0&&gameBoard[i][jr-3]==0) {// move to spot 3
	        gameBoard[i][jr-3]=gameBoard[i][jr];
	        gameBoard[i][jr]=0;
	      }
	      else if (jr==3&&gameBoard[i][jr-1]==0&&gameBoard[i][jr-2]==0&&gameBoard[i][jr-3]==gameBoard[i][jr]&&c3==0) {// merge 0 - 3
	        gameBoard[i][jr-3]+=gameBoard[i][jr];
	        score+=gameBoard[i][jr-3];
	        gameBoard[i][jr]=0;
	      }
	      else if (jr==3&&gameBoard[i][jr-1]==0&&gameBoard[i][jr-2]==gameBoard[i][jr]&&c2==0) {//merge 0-2
	        gameBoard[i][jr-2]+=gameBoard[i][jr];
	        score+=gameBoard[i][jr-2];
	        gameBoard[i][jr]=0;
	      }
	      else if (jr==3&&gameBoard[i][jr-1]==0&&gameBoard[i][jr-2]==0) {// move spot 2
	        gameBoard[i][jr-2]=gameBoard[i][jr];
	        gameBoard[i][jr]=0;
	      }
	      else if (jr==3&&gameBoard[i][jr-1]==gameBoard[i][jr]) {//merge 0-1
	        gameBoard[i][jr-1]+=gameBoard[i][jr];
	        score+=gameBoard[i][jr-1];
	        gameBoard[i][jr]=0;
	      }
	      else if (jr==3&&gameBoard[i][jr-1]==0) {
	        gameBoard[i][jr-1]=gameBoard[i][jr];
	        gameBoard[i][jr]=0;
	      }
	      jr++;
	    }
	    c2=0; 
	    c3=0;
	    jr=0;
	  }
	}

	public void moveUp() {
	  jr=0; 
	  c2=0; 
	  c3=0;
	  for (int i=0; i<4;i++) {
	    for (int j=0; j<4; j++) {

	      if (jr==1&&gameBoard[jr-1][i]==gameBoard[jr][i]&&gameBoard[jr][i]!=0) { //merge 2-3
	        gameBoard[jr-1][i]+=gameBoard[jr][i];
	        score+=gameBoard[jr-1][i];
	        gameBoard[jr][i]=0;
	        c3=1;
	      }
	      else if (jr==1&&gameBoard[jr-1][i]==0 ) {// move if empty
	        gameBoard[jr-1][i]=gameBoard[jr][i];
	        gameBoard[jr][i]=0;
	      }
	      else if (jr==2&&gameBoard[jr-1][i]==0&&gameBoard[jr-2][i]==0) { // move if spot 3 if empty
	        gameBoard[jr-2][i]=gameBoard[jr][i];
	        gameBoard[jr][i]=0;
	      }
	      else if (jr==2&&gameBoard[jr-1][i]==0&&gameBoard[jr-2][i]==gameBoard[jr][i]&&c3==0) { // merge spot 1-3 if same
	        gameBoard[jr-2][i]+=gameBoard[jr][i];
	        score+=gameBoard[jr-2][i];
	        gameBoard[jr][i]=0;
	      }
	      else if (jr==2&&gameBoard[jr-1][i]==gameBoard[jr][i]&&gameBoard[jr][i]!=0) {// merge 1-2
	        gameBoard[jr-1][i]+=gameBoard[jr][i];
	        score+=gameBoard[jr-1][i];
	        gameBoard[jr][i]=0;
	        c2=1;
	      }
	      else if (jr==2&&gameBoard[jr-1][i]==0) {// move 1-2
	        gameBoard[jr-1][i]=gameBoard[jr][i];
	        gameBoard[jr][i]=0;
	      }
	      else if (jr==3&&gameBoard[jr-1][i]==0&&gameBoard[jr-2][i]==0&&gameBoard[jr-3][i]==0) {// move to spot 3
	        gameBoard[jr-3][i]=gameBoard[jr][i];
	        gameBoard[jr][i]=0;
	      }
	      else if (jr==3&&gameBoard[jr-1][i]==0&&gameBoard[jr-2][i]==0&&gameBoard[jr-3][i]==gameBoard[jr][i]&&c3==0) {// merge 0 - 3
	        gameBoard[jr-3][i]+=gameBoard[jr][i];
	        score+=gameBoard[jr-3][i];
	        gameBoard[jr][i]=0;
	      }
	      else if (jr==3&&gameBoard[jr-1][i]==0&&gameBoard[jr-2][i]==gameBoard[jr][i]&&c2==0) {//merge 0-2
	        gameBoard[jr-2][i]+=gameBoard[jr][i];
	        score+=gameBoard[jr-2][i];
	        gameBoard[jr][i]=0;
	      }
	      else if (jr==3&&gameBoard[jr-1][i]==0&&gameBoard[jr-2][i]==0) {// move spot 2
	        gameBoard[jr-2][i]=gameBoard[jr][i];
	        gameBoard[jr][i]=0;
	      }
	      else if (jr==3&&gameBoard[jr-1][i]==gameBoard[jr][i]) {//merge 0-1
	        gameBoard[jr-1][i]+=gameBoard[jr][i];
	        score+=gameBoard[jr-1][i];
	        gameBoard[jr][i]=0;
	      }
	      else if (jr==3&&gameBoard[jr-1][i]==0) {
	        gameBoard[jr-1][i]=gameBoard[jr][i];
	        gameBoard[jr][i]=0;
	      }
	      jr++;
	    }
	    c2=0; 
	    c3=0;
	    jr=0;
	  }
	}


	public void moveDown() {
	  jr=3; 
	  c2=0; 
	  c3=0;
	  for (int i=0; i<4;i++) {
	    for (int j=4; j>0; j--) {

	      if (jr==2&&gameBoard[jr+1][i]==gameBoard[jr][i]&&gameBoard[jr][i]!=0) { //merge 2-3
	        gameBoard[jr+1][i]+=gameBoard[jr][i];
	        score+=gameBoard[jr+1][i];
	        gameBoard[jr][i]=0;
	        c3=1;
	      }
	      else if (jr==2&&gameBoard[jr+1][i]==0 ) {// move if empty
	        gameBoard[jr+1][i]=gameBoard[jr][i];
	        gameBoard[jr][i]=0;
	      }
	      else if (jr==1&&gameBoard[jr+1][i]==0&&gameBoard[jr+2][i]==0) { // move if spot 3 if empty
	        gameBoard[jr+2][i]=gameBoard[jr][i];
	        gameBoard[jr][i]=0;
	      }
	      else if (jr==1&&gameBoard[jr+1][i]==0&&gameBoard[jr+2][i]==gameBoard[jr][i]&&c3==0) { // merge spot 1-3 if same
	        gameBoard[jr+2][i]+=gameBoard[jr][i];
	        score+=gameBoard[jr+2][i];
	        gameBoard[jr][i]=0;
	      }
	      else if (jr==1&&gameBoard[jr+1][i]==gameBoard[jr][i]&&gameBoard[jr][i]!=0) {// merge 1-2
	        gameBoard[jr+1][i]+=gameBoard[jr][i];
	        score+=gameBoard[jr+1][i];
	        gameBoard[jr][i]=0;
	        c2=1;
	      }
	      else if (jr==1&&gameBoard[jr+1][i]==0) {// move 1-2
	        gameBoard[jr+1][i]=gameBoard[jr][i];
	        gameBoard[jr][i]=0;
	      }
	      else if (jr==0&&gameBoard[jr+1][i]==0&&gameBoard[jr+2][i]==0&&gameBoard[jr+3][i]==0) {// move to spot 3
	        gameBoard[jr+3][i]=gameBoard[jr][i];
	        gameBoard[jr][i]=0;
	      }
	      else if (jr==0&&gameBoard[jr+1][i]==0&&gameBoard[jr+2][i]==0&&gameBoard[jr+3][i]==gameBoard[jr][i]&&c3==0) {// merge 0 - 3
	        gameBoard[jr+3][i]+=gameBoard[jr][i];
	        score+=gameBoard[jr+3][i];
	        gameBoard[jr][i]=0;
	      }
	      else if (jr==0&&gameBoard[jr+1][i]==0&&gameBoard[jr+2][i]==gameBoard[jr][i]&&c2==0) {//merge 0-2
	        gameBoard[jr+2][i]+=gameBoard[jr][i];
	        score+=gameBoard[jr+2][i];
	        gameBoard[jr][i]=0;
	      }
	      else if (jr==0&&gameBoard[jr+1][i]==0&&gameBoard[jr+2][i]==0) {// move spot 2
	        gameBoard[jr+2][i]=gameBoard[jr][i];
	        gameBoard[jr][i]=0;
	      }
	      else if (jr==0&&gameBoard[jr+1][i]==gameBoard[jr][i]) {//merge 0-1
	        gameBoard[jr+1][i]+=gameBoard[jr][i];
	        score+=gameBoard[jr+1][i];
	        gameBoard[jr][i]=0;
	      }
	      else if (jr==0&&gameBoard[jr+1][i]==0) {
	        gameBoard[jr+1][i]=gameBoard[jr][i];
	        gameBoard[jr][i]=0;
	      }
	      jr--;
	    }
	    c2=0; 
	    c3=0;
	    jr=3;
	  }
	}


	
}
