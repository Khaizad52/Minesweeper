package Minesweeper;

import java.util.*;

public class Field {
     
    public int[][] playField = new int[10][10];
    public int[][] mineField = new int[10][10];

    public void startGame(){
        System.out.println("\n\n||===||==||=== Welcome to MineSweeper ===||===||===||\n");
        setupField(1);
    
        boolean flag = true;
        while(flag){
            playingField();
            flag = playerMove();
    
            if(checkWin()){
                displayHidden();
                System.out.println("\n\n||===||==||=== Congrats on the win!! ===||===||===||\n");
                break;
            }
        }
    }

    public void setupField(int diff){
        
        int bomb = 0;
        while(bomb != 7){

            Random random = new Random();
            int r = random.nextInt(10);
            int c = random.nextInt(10);
            mineField[r][c] = 100;
            bomb++;
        }
        buildHidden();
    }
 
    public void buildHidden(){

        for(int r = 0; r < 10; r++){

            for(int c = 0; c < 10; c++){

                int count=0;
                if(mineField[r][c]!=100){

                    if(r != 0){
                        if(mineField[r-1][c]==100) count++;
                        if(c != 0){
                            if(mineField[r-1][c-1]==100) count++;
                        }
                    }
                    if(r != 9){
                        if(mineField[r+1][c]==100) count++;
                        if(c!=9){
                            if(mineField[r+1][c+1]==100) count++;
                        }
                    }
                    if(c != 0){
                        if(mineField[r][c-1]==100) count++;
                        if(r != 9){
                            if(mineField[r+1][c-1]==100) count++;
                        }
                    }
                    if(c != 9){
                        if(mineField[r][c+1]==100) count++;  
                        if(r != 0){
                            if(mineField[r-1][c+1]==100) count++;
                        }
                    }
                    mineField[r][c] = count;
                }
            }
        }
    }

    public void playingField()
    {
        System.out.print("\t ");
        for(int r = 0; r < 10; r++){

            System.out.print(" " + r + "  ");
        }

        System.out.print("\n");
        for(int r = 0; r < 10; r++){

            System.out.print(r + "\t| ");
            for(int c = 0; c < 10; c++){

                if(playField[r][c] == 0){
                    System.out.print(">");
                }
                else if(playField[r][c] == 50){
                    System.out.print(" ");
                }
                else{
                    System.out.print(playField[r][c]);
                }
                System.out.print(" | ");
            }
            System.out.print("\n");
        }
    }
    
    public boolean playerMove(){
        Scanner scan = new Scanner(System.in);
        System.out.print("\nEnter a Row: ");
        int r = scan.nextInt();
        System.out.print("\nEnter a Column: ");
        int c = scan.nextInt();

        if(r < 0 || r > 9 || c < 0 || c > 9 || playField[r][c] != 0){
            System.out.println("\nIncorrect Number!!");
            return true;
        }

        if(mineField[r][c] == 100){
            displayHidden();
            System.out.println("Mine Activated!! \n||===Game Over===||");
            return false;
        }
        else if(playField[r][c]==0){
            fixVisible(r, c);
        }
        else{
            fixNeighbours(r, c);
        }
        return true;
    }

    public  void fixVisible(int r, int c){
        playField[r][c] = 50;
        if(r != 0){
            playField[r-1][c] = mineField[r-1][c];
            if(playField[r-1][c] == 0) playField[r-1][c] = 50;

            if(c != 0){
                playField[r-1][c-1] = mineField[r-1][c-1];
            if(playField[r-1][c-1] == 0) playField[r-1][c-1] = 50;
            }
        }
        if(r != 9){
            playField[r+1][c] = mineField[r+1][c];
            if(playField[r+1][c] == 0) playField[r+1][c] = 50;

            if(c != 9){
                playField[r+1][c+1] = mineField[r+1][c+1];
            if(playField[r+1][c+1] == 0) playField[r+1][c+1] = 50;
            }
        }
        if(c != 0){
            playField[r][c-1] = mineField[r][c-1];
            if(playField[r][c-1] == 0) playField[r][c-1] = 50;

            if(r != 9){
                playField[r+1][c-1] = mineField[r+1][c-1];
            if(playField[r+1][c-1] == 0) playField[r+1][c-1] = 50;
            }
        }
        if(c != 9){
            playField[r][c+1] = mineField[r][c+1];
            if(playField[r][c+1] == 0) playField[r][c+1] = 50;

            if(r != 0){
                playField[r-1][c+1] = mineField[r-1][c+1];
            if(playField[r-1][c+1] == 0) playField[r-1][c+1] = 50;
            }
        }
    }

    public void fixNeighbours(int r, int c){
        Random random = new Random();
        int x = random.nextInt()%4;

        if(x == 0){
            if(r != 0){
                if(mineField[r-1][c] != 100){
                    playField[r-1][c] = mineField[r-1][c];
                    if(playField[r-1][c] == 0) playField[r-1][c] = 50;
                }
            }
            if(c != 0){
                if(mineField[r][c-1] != 100){
                    playField[r][c-1] = mineField[r][c-1];
                    if(playField[r][c-1] == 0) playField[r][c-1] = 50;
                }
            }
            if(r != 0 && c != 0){
                if(mineField[r-1][c-1] != 100){
                    playField[r-1][c-1] = mineField[r-1][c-1];
                    if(playField[r-1][c-1] == 0) playField[r-1][c-1] = 50;
                }
            }
        }
        else if(x == 1){
            if(r != 0){
                if(mineField[r-1][c] != 100){
                    playField[r-1][c] = mineField[r-1][c];
                    if(playField[r-1][c] == 0) playField[r-1][c] = 50;
                }
            }
            if(c != 9){
                if(mineField[r][c+1] != 100){
                    playField[r][c+1] = mineField[r][c+1];
                    if(playField[r][c+1] == 0) playField[r][c+1] = 50;
                }
            }
            if(r != 0 && c != 9){
                if(mineField[r-1][c+1] != 100){
                    playField[r-1][c+1] = mineField[r-1][c+1];
                    if(playField[r-1][c+1] == 0) playField[r-1][c+1] = 50;
                }
            }
        }
        else if(x == 2){
            if(r != 9){
                if(mineField[r+1][c] != 100){
                    playField[r+1][c] = mineField[r+1][c];
                    if(playField[r+1][c] == 0) playField[r+1][c] = 50;
                }
            }
            if(c != 9){
                if(mineField[r][c+1] != 100){
                    playField[r][c+1] = mineField[r][c+1];
                    if(playField[r][c+1] == 0) playField[r][c+1] = 50;
                }
            }
            if(r != 9 && c != 9){
                if(mineField[r+1][c+1] != 100){
                    playField[r+1][c+1] = mineField[r+1][c+1];
                    if(playField[r+1][c+1] == 0) playField[r+1][c+1] = 50;
                }
            }
        }
        else{
            if(r != 9){
                if(mineField[r+1][c] != 100){
                    playField[r+1][c] = mineField[r+1][c];
                    if(playField[r+1][c] == 0) playField[r+1][c] = 50;
                }
            }
            if(c != 0){
                if(mineField[r][c-1] != 100){
                    playField[r][c-1] = mineField[r][c-1];
                    if(playField[r][c-1] == 0) playField[r][c-1] = 50;
                }
            }
            if(r != 9 && c != 0){
                if(mineField[r+1][c-1] != 100){
                    playField[r+1][c-1] = mineField[r+1][c-1];
                    if(playField[r+1][c-1] == 0) playField[r+1][c-1] = 50;
                }
            }
        }
    }

    public boolean checkWin(){
        for(int r = 0; r < 10; r++){
            for(int c = 0; c < 10; c++){
                if(playField[r][c] == 0){
                    if(mineField[r][c] != 100){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void displayHidden()
    {
        System.out.print("\t ");
        for(int r = 0; r < 10; r++)
        {
            System.out.print(" " + r + "  ");
        }
        System.out.print("\n");
        for(int r = 0; r < 10; r++)
        {
            System.out.print(r + "\t| ");
            for(int c = 0; c < 10; c++)
            {
                if(mineField[r][c]==0)
                {
                    System.out.print(" ");
                }
                else if(mineField[r][c] == 100)
                {
                    System.out.print("X");
                }
                else
                {
                    System.out.print(mineField[r][c]);
                }
                System.out.print(" | ");
            }
            System.out.print("\n");
        }
    }
}
