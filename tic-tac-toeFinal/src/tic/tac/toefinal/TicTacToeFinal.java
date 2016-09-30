
package tic.tac.toefinal;

import static java.lang.Math.max;
import static java.lang.Math.min;
import java.util.Random;
import java.util.Scanner;


public class TicTacToeFinal {
    
    static int level = 1;

  public static void  printMatrix(char box[]) {
     System.out.print("\n");
     System.out.println("|"+box[1]+ "|"+ box[2] +"|"+box[3] +"|");
     System.out.println("|"+box[4]+ "|"+ box[5] +"|"+box[6] +"|");
     System.out.println("|"+box[7]+ "|"+ box[8] +"|"+box[9] +"|");
     System.out.println("-------");
     System.out.print("\n");
 
}
    
    public static int  game(char box[],int filled) {
 
    /*checking rows*/
    if(box[1]==box[2] && box[2]==box[3] && box[2]!=' ')
        return box[2]=='x'? 10 : -10 ;
 
    if(box[4]==box[5] && box[5]==box[6] && box[4]!=' ')
        return box[4]=='x'? 10 : -10 ;
 
    if(box[7]==box[8] && box[8]==box[9] && box[8]!=' ')
        return box[8]=='x'? 10 : -10 ;
 
    /*checking columns*/
    if(box[1]==box[4] && box[4]==box[7] && box[1]!=' ')
        return box[1]=='x'? 10 : -10 ;
 
    if(box[2]==box[5] && box[5]==box[8] && box[2]!=' ')
        return box[2]=='x'? 10 : -10 ;
 
    if(box[3]==box[6] && box[6]==box[9] && box[3]!=' ')
        return box[3]=='x'? 10 : -10 ;
 
    /*checking diagonals*/
    if(box[1]==box[5] && box[5]==box[9] && box[1]!=' ')
            return box[1]=='x'? 10 : -10 ;
 
    if(box[3]==box[5] && box[5]==box[7] && box[3]!=' ')
            return box[3]=='x'? 10 : -10 ;
 
    /* If DRAW return 0*/
    if(filled==9)
        return 0;
 
    /* If game is not over then return -1*/
    return -1;
}
    
    public static void displayResult(char box[],int filled) {
    int x=game(box,filled);
    //printMatrix(box);
    if(x==10) {
        System.out.println("[[[Player X won]]]\n");
    } else if(x==-10) {
       System.out.println("[[[Player O won]]]\n");
    } else {
        System.out.println("[[[...DRAW...]]]\n");
    }
    level ++;
}
    
    /* Returns Array={index , benifit } */
public static int[] AlphaBeta(char box[] , int alpha ,int beta ,boolean player, int filled) {
    
    int[] a=new int[2];
    int x=game(box,filled);
    int i,j,k,l,index=0;
 
    /* Base case */
    if(x!=-1) {
        a[0]=-1;
        a[1]= x;
        return a;
    }
 
    /* Main Part*/
    if(player==true) {
        int mx=-100;    // Max. possible score of 'X'
      //  int *a;
        /*Check all possible moves*/
        for(i=1;i<10;i++) {
            if(box[i]==' ') {
                /* Fill this box*/
                box[i]='x';
                filled++;
 
 
                a=AlphaBeta(box , alpha , beta ,!player,filled);
                if(mx < a[1]) {
                    mx=a[1];
                    index=i;
                    alpha=max(alpha,mx);
 
                    if(beta <= alpha) {
                        box[i]=' ';
                        filled--;
                        break;
                    }
                }
                /* Unfill this box*/
                box[i]=' ';
                filled--;
            }
        }
        a[0] = index;
        a[1]=mx;
        return a;
    }
    else {
 
        int mn=+100;   // Min. possible score of 'O'
        //int *a;
        for(i=1;i<10;i++) { 
            if(box[i]==' ')
            { box[i]='o';
            filled++; 
            a=AlphaBeta(box , alpha , beta ,!player,filled);
            if(mn > a[1]) {
            
            
                    mn=a[1];
                    index=i;
                    beta=min(beta , mn);
 
                    if(beta <= alpha) {
                        box[i]=' ';
                        filled--;
                        break;
                    }
 
                }
 
                box[i]=' ';
                filled--;
            }
        }
        a[0]=index;
        a[1]=mn;
        return a;
    }
}

public static int toWinO(char box[] ,int index){
    
    if(box[1]==box[2] && box[2]=='o' && box[3] == ' ')
        return 3 ;
   else if(box[2]==box[3] && box[2]=='o' && box[1] == ' ')
        return 1 ;
   else if(box[1]==box[3] && box[1]=='o' && box[2] == ' ')
        return 2 ;
 
   else if(box[4]==box[5] && box[5]=='o' && box[6] == ' ')
        return 6;
   else if(box[4]==box[6] && box[6]=='o' && box[5] == ' ')
        return 5; 
   else if(box[6]==box[5] && box[5]=='o' && box[4] == ' ')
        return 4;
    
  else  if(box[7]==box[8] && box[7]=='o' && box[9] == ' ')
        return 9;
   else if(box[7]==box[9] && box[7]=='o' && box[8] == ' ')
        return 8;
  else  if(box[8]==box[9] && box[8]=='o' && box[7] == ' ')
        return 7;
 
    /*checking columns*/
  else  if(box[1]==box[4] && box[4]=='o' && box[7] == ' ')
        return 7;
   else if(box[1]==box[7] && box[7]=='o' && box[4] == ' ')
        return 4;
   else if(box[4]==box[7] && box[8]=='o' && box[1] == ' ')
        return 1;
    
   else if(box[2]==box[5] && box[5]=='o' && box[8] == ' ')
        return 8;
   else if(box[2]==box[8] && box[8]=='o' && box[5] == ' ')
        return 5;
   else if(box[5]==box[8] && box[8]=='o' && box[2] == ' ')
        return 2;
    
   else if(box[3]==box[6] && box[6]=='o' && box[9] == ' ')
        return 9;
   else if(box[3]==box[9] && box[9]=='o' && box[6] == ' ')
        return 6;
  else  if(box[6]==box[9] && box[9]=='o' && box[3] == ' ')
        return 3;
 
    /*checking diagonals*/
   else if(box[1]==box[5] && box[5]=='o' && box[9] == ' ')
        return 9;
   else if(box[1]==box[9] && box[9]=='o' && box[5] == ' ')
        return 5;
   else if(box[5]==box[9] && box[9]=='o' && box[1] == ' ')
        return 1;
    
   else  if(box[3]==box[5] && box[5]=='o' && box[7] == ' ')
        return 7;
   else if(box[3]==box[7] && box[7]=='o' && box[5] == ' ')
        return 5;
  else  if(box[5]==box[7] && box[5]=='o' && box[3] == ' ')
        return 3;
    //System.out.println(random);
    else
        return index;
    

}


    public static int toWinX(char box[],int index){
    //Random rn = new Random();
    //int random = rn.nextInt(10 - 1 + 1) + 1;
    
    if(box[1]==box[2] && box[2]=='x' && box[3] == ' ')
        return 3 ;
   else if(box[2]==box[3] && box[2]=='x' && box[1] == ' ')
        return 1 ;
   else if(box[1]==box[3] && box[1]=='x' && box[2] == ' ')
        return 2 ;
 
   else if(box[4]==box[5] && box[5]=='x' && box[6] == ' ')
        return 6;
   else if(box[4]==box[6] && box[6]=='x' && box[5] == ' ')
        return 5; 
   else if(box[6]==box[5] && box[5]=='x' && box[4] == ' ')
        return 4;
    
  else  if(box[7]==box[8] && box[7]=='x' && box[9] == ' ')
        return 9;
   else if(box[7]==box[9] && box[7]=='x' && box[8] == ' ')
        return 8;
  else  if(box[8]==box[9] && box[8]=='x' && box[7] == ' ')
        return 7;
 
    /*checking columns*/
  else  if(box[1]==box[4] && box[4]=='x' && box[7] == ' ')
        return 7;
   else if(box[1]==box[7] && box[7]=='x' && box[4] == ' ')
        return 4;
   else if(box[4]==box[7] && box[8]=='x' && box[1] == ' ')
        return 1;
    
   else if(box[2]==box[5] && box[5]=='x' && box[8] == ' ')
        return 8;
   else if(box[2]==box[8] && box[8]=='x' && box[5] == ' ')
        return 5;
   else if(box[5]==box[8] && box[8]=='x' && box[2] == ' ')
        return 2;
    
   else if(box[3]==box[6] && box[6]=='x' && box[9] == ' ')
        return 9;
   else if(box[3]==box[9] && box[9]=='x' && box[6] == ' ')
        return 6;
  else  if(box[6]==box[9] && box[9]=='x' && box[3] == ' ')
        return 3;
 
    /*checking diagonals*/
   else if(box[1]==box[5] && box[5]=='x' && box[9] == ' ')
        return 9;
   else if(box[1]==box[9] && box[9]=='x' && box[5] == ' ')
        return 5;
   else if(box[5]==box[9] && box[9]=='x' && box[1] == ' ')
        return 1;
    
   else  if(box[3]==box[5] && box[5]=='x' && box[7] == ' ')
        return 7;
   else if(box[3]==box[7] && box[7]=='x' && box[5] == ' ')
        return 5;
  else  if(box[5]==box[7] && box[5]=='x' && box[3] == ' ')
        return 3;
    //System.out.println(random);
    else
        return index;
    
}
    public static void main(String[] args) {
        while(level <= 3){
        Scanner s =new Scanner(System.in);
        int filled=0; 
        char[] box ={' ',' ',' ',' ',' ',' ',' ',' ',' ',' '};
        char currentPlayer;
        int index = 0,i,over,here;
        
        

        /* Choose first move */
        if(level == 1)
        System.out.println("[[[LEVEL 1]]]--- computer plays sub-optimally!\n");
        else if(level == 2)
        System.out.println("[[[LEVEL 2]]]--- computer plays sub-optimally!\n");
        else if(level == 3)
                System.out.println("[[[LEVEL 3]]]--- computer plays optimally!\n");
        System.out.println("You are Mr. O\n");
        System.out.println("Who will start game ? ( Enter 'O' or 'X') : ");
        currentPlayer=s.next().charAt(0);
        Random rn = new Random();
        int random = rn.nextInt(9 - 1 + 1) + 1;
        
        while(game(box,filled)==-1 && filled<9){ //game not over
            
            System.out.println("Player: "+currentPlayer);
            
            if(currentPlayer=='x' || currentPlayer == 'X'){ // then player = true
                int []a=new int[2];
                    a=AlphaBeta(box , -100 ,100 , true,filled);
                    index=a[0];
                    if(level == 1){
                        int oWin = toWinO(box,random);
                        if(box[random] == ' ' && random != oWin){
                            System.out.println("random = " +random);
                            index = toWinX(box,random);              
                        }
                        //index = toWinX(box,random);
                    }
                    else if(level == 2){
                        System.out.println("random = " +random);
                        if(box[random] == ' '){
                         index = toWinX(box,random);
                        }
                        else
                            index = toWinX(box,index);
                    }
                    else{
                        
                        index = toWinX(box,index);
                    }
                    
            }
            else if (currentPlayer == 'o' || currentPlayer == 'O'){ //then player = false
                while(true) {
                    System.out.println("Mr. O, Please enter index: ");
                    index=s.nextInt();
                    
                    if(box[index]!=' ')
                       System.out.println("Enter valid index : ");
                    else
                        break;
                    
                }
            }
            else{
                while(currentPlayer != 'o' && currentPlayer != 'O' && currentPlayer != 'x' && currentPlayer != 'X'){
                System.out.println("ERROR!! you must enter x or o");
                System.out.println("Who will start game ? ( Enter 'O' or 'X') : ");
                currentPlayer=s.next().charAt(0);}
                if(currentPlayer == 'o' || currentPlayer == 'O'){
                    System.out.println("Mr. O,Please enter index: ");
                 index=s.nextInt();
                }
                if(currentPlayer=='x' || currentPlayer == 'X'){ // then player = true
                int []a=new int[2];
                    a=AlphaBeta(box , -100 ,100 , true,filled);
                    index=a[0];
                    index = toWinX(box,index);
                }
            }
            
            /* Fill the BOX */
            box[index] = (currentPlayer);
            filled++;
 
            currentPlayer= (currentPlayer=='o'?'x':'o') ;
 
           // system("cls");
            printMatrix(box);
        
        }
        displayResult(box,filled);
    }
    }
    
}
