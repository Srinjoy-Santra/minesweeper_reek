/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;
import java.io.*;
        
/**
 *
 * @author nEW u
 */
public final class Androidmines {

    /**
     * @param args the command line arguments
     */
    static int  score[]=new int[6];
    static String  name[]=new String[6];
    int n;//size
    int board[][];//board contents
    int nboard[][];
    int bombnumber;
    public Androidmines() {
        n=9;
        board=new int[n+1][n+1];
        nboard=new int[n+1][n+1];   
        bombnumber=n+1;
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                board[i][j]=0;
        for(int plant=0;plant<bombnumber;plant++)
        {
            int   x=(int)(Math.random()*(double)n*1.0);
            int y=(int)(Math.random()*(double)n*1.0);
            if(board[x][y]==1)
            {
                plant--;
                continue;
            }
            board[x][y]=1;
        }
        for(int p=0;p<n;p++)
        {
            for(int q=0;q<n;q++)
            {
                int u=0;
                int h,d;
                if(p==0&&q==0)h=1;//topleft
                else if(p==n&&q==n)h=2;//bottomright
                else if(p==0&&q==n)h=3;//topright  
                else if(p==n&&q==0)h=4;//bottomleft
                else if(p==0&&q!=n)h=5;//top
                else if(p!=n&&q==0)h=6;//left
                else if(p==n&&q!=n)h=7;//bottom
                else if(p!=n&&q==n)h=8;//right
                else/* if((p!=n&&q!=n)&&(p!=0&&q!=0))*/h=9;//other
                if((h==2)||(h==7)||(h==8)||(h==9)){d=bombcheck(p-1,q-1);if(d==1)u++;d=0;}                    //c-1 r-1 
                if((h==2)||(h==4)||(h==6)||(h==7)||(h==8)||(h==9)){ d=bombcheck(p-1,q);if(d==1)u++;d=0;}     //c-1 r
                if((h==4)||(h==6)||(h==7)||(h==9)){d=bombcheck(p-1,q+1);if(d==1)u++;d=0;}                    //c-1 r+1
                if((h==1)||(h==4)||(h==5)||(h==6)||(h==7)||(h==9)){d=bombcheck(p,q+1);if(d==1)u++;d=0;}      //c r+1
                if((h==1)||(h==5)||(h==6)||(h==9)){d=bombcheck(p+1,q+1);if(d==1)u++;d=0;}                    //c+1 r+1
                if((h==1)||(h==3)||(h==5)||(h==6)||(h==8)||(h==9)){d=bombcheck(p+1,q);if(d==1)u++;d=0;}      //c+1 r
                if((h==3)||(h==5)||(h==8)||(h==9)){d=bombcheck(p+1,q-1);if(d==1)u++;d=0;}                    //c+1 r-1
                if((h==2)||(h==3)||(h==5)||(h==7)||(h==8)||(h==9)){d=bombcheck(p,q-1);if(d==1)u++;d=0;}      //c r-1
                d=bombcheck(p,q);

                if((u==0)&&(d!=1)){nboard[p][q]=-1; }//no mine and
                else if(d!=1) nboard[p][q]=u;
            }
        }
        help();
        
    }
    
    
    public static void main(String[] args)throws IOException {
        int sd;
        while(1>0)
        {
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            System.out.println();
            System.out.println("Enter 1 for Play");          
            System.out.println("Enter 2 for Help");
            System.out.println("Enter 3 for Highscores");
            System.out.println("Enter 4 for Exit");

            System.out.println("Enter your choice : ");
            int ch;
            try
            {
                ch=Integer.parseInt(br.readLine());
                Androidmines am=new Androidmines();
                switch(ch)
                {  
                    case 1  :   sd= am.game_function();
                    if( sd!=-1)am.perfor(sd);//sd=1 if won else %completed
                    break;   

                    case 2  : am.help();
                    break;

                    case 3 :   
                    am.stats();
                    break;

                    case 4 :
                    System.out.println("End of the program........");
                    System.exit(0);
                    break;
                    default :  System.out.println("Wrong choice input........");
                    continue;
                }

                System.out.println();
                System.out.println("Rate the game out of five");
                ch=Integer.parseInt(br.readLine());
                if((ch==0)||(ch==2)||(ch==3))System.out.println("we will try to improve our game");
                else System.out.println("thank you for complimenting our efforts");
            }
            catch(Exception e)
            {
                System.out.println("invalid");
            }
        }

    }
    
    private int game_function()throws IOException
    {
        int c=0;//int f=0;
        while(1>0)
        {
            try
            {                       
                    BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
                    System.out.println();
                    System.out.println("Enter either of these :");               
                    System.out.println("Enter h for help ; or");
                    System.out.println("Enter e for exit from the present game ; or");
                    System.out.println("Enter f for flag ; or");
                    System.out.println("Enter cell number:");
                    String s=br.readLine();

                switch (s.charAt(0)) {
                    case 'h':
                        help();
                        break;
                    case 'e':
                        return -1;
                    case 'f': 
                    {
                        System.out.println("Enter cell number where you want to place a flag");
                        int jk=Integer.parseInt(br.readLine());
                        int p=jk/9;
                        int q=jk%9;
                        if(p>=9||q>=9||p<0||q<0)
                        {
                            System.out.println("No such cell number is present");
                            continue;
                        }
                        
                        else if(board[p][q]==3)
                        {
                            System.out.println("You have already uncovered the cell");
                            continue;
                        }
                        board[p][q]=9;
                        int ff=display(p,q);
                        //c++; 
                        continue;
                        
                        }
                        default:
                        {
                            int yx=Integer.parseInt(s);
                            int p=yx/n;
                            int q=yx%n;
                            if(p>=n||q>=n||p<0||q<0)
                            {
                                System.out.println("No such cell number is present");
                                continue;
                            }   
                            else if(board[p][q]==9)
                            {            
                                board[p][q]=bombcheck(p,q);
                            }
                            else {
                                     int t=uncover(p,q);
                                     if(t==3){System.out.println("You have already uncovered the cell");
                                     continue;}
                                    }
                            int d=bombcheck(p,q);
                            if(d==1)
                            {
                                board[p][q]=2;
                                this.displaybomb(p,q);
                                System.out.println("*************************");
                                System.out.println("You have stepped a mine");
                                System.out.println("Better luck next time!");
                                System.out.println("*************************");
                                return c*100/(9*9-this.bombnumber);
                            }
                            else if(d==2)
                            {
                                int cc=this.display(p,q);
                                if(cc==0)c++; else c+=cc;
                                if(c==n*n-bombnumber)
                                {
                                    System.out.println("*************************");
                                    System.out.println("CONGRATULATIONS!!! You have WON!");
                                    System.out.println("You have completed 100% of the game");
                                    System.out.println("*************************");
                                    return 100;
                                }
                                else
                                {
                                    System.out.println("*************************");
                                    System.out.println("You have "+(long)(n*n-c-bombnumber)+" more cells to uncover");
                                    System.out.println("You have completed "+(double)(c*100/((n*n)-bombnumber))+"% of the game");
                                    System.out.println("*************************");
                                }
                            }           break;
                        }
                }
                    
            }
            catch(Exception e)
            {
                System.out.println("Wrong option!");
                //continue;
            }           
              
        }
    }    
    private int uncover(int p,int q)
    {
        if(board[p][q]==3)
            return 3;
        return 0;
    }

    public   void perfor(int w)throws IOException
    {
       
                score[0]=w;
                String nam;
                System.out.println("Please enter your name");
                BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
                nam=br.readLine();
                name[0]=nam;
                int i,j,small,tmp,pos;
                String snam,stem;
                for(i=0;i<6;i++)
                { 
                    small = score[i];
                    snam = name[i];
                    pos = i;
                    for(j=i+1;j<5;j++)
                    { 
                        if(score[j] < small)
                        { small =  score[j];
                            snam=name[j];
                            pos = j; 
                        }  
                    }
                    tmp = score[i];
                    stem = name[i];
                    score[i] = score[pos];
                    name[i]=name[pos];
                    score[pos] = tmp;
                    name[pos]=stem;
                }
               stats();
    }

    public void stats()
    {
        System.out.print("The scores of minsweeper in the ");

         
        System.out.println();
        System.out.println("**************************************");
        System.out.println("|players                  |%completed "); 
        System.out.println("--------------------------------------");
        for(int i=4;i>=0;i--)
        {
            try

            {

                if(name[i].length()==25)
                {
                    System.out.println((name[i])+"|"+(score[i])); 
                }
                else if(name[i].length() <25)
                {

                    System.out.print("|"+(name[i]));
                    for(int count=0;count<25-name[i].length();count++)
                        System.out.print(" ");
                    if((score[i]>0&&score[i]<10))
                        System.out.println(("| "+score[i])+"|");
                    else System.out.println(("|"+score[i])+"|");
                }
            }
            catch(Exception e)
            {
                System.out.println("|no entries yet           | "+score[i]+"|");
            }
        }
        System.out.println("--------------------------------------");
        System.out.println("**************************************");
    }

    public int bombcheck(int p,int q)
    {
        if(board[p][q]==1)
        { 
            return 1;
        }                           
        else
        {
            return 2;
        }
    }

    public int display(int p,int q)
    {

        int c=0,ul=0;
        if(board[p][q]!=9){if (nboard[p][q]==-1) ul=1;}

        for(int i=0; i<n;i++)
        {
            for(int k=0 ; k<=n*5 ;k++ )
                System.out.print("_");
            System.out.println();
            for(int j=0; j<n; j++)
            {
                if(board[i][j]==9)System.out.print("|(#F)");
                else if
                ((ul==1)&&(nboard[i][j]==-1))
                {
                    System.out.print("|(#"+0+")");
                    board[i][j]=3;
                    c++;
                }

                else if(i==p&&j==q)
                {
                    System.out.print("|(#"+nboard[i][j]+")");
                    board[p][q]=3;
                }

                else if(board[i][j]==3){
                    if(nboard[i][j]==-1) System.out.print("|(#"+0+")");
                    else System.out.print("|(#"+nboard[i][j]+")");
                }
                else
                {
                    int yy=(n*i)+j;
                    if(yy<10)
                        System.out.print("| "+yy+" ");                    
                    else
                        System.out.print("|"+yy+" ");
                }
            }
            System.out.print("|");    
            System.out.println();     
        }
        for(int k=0 ; k<=n*5 ; k++)
            System.out.print("_");
        System.out.println();
        return c;
    }

    /**
     *
     * @param p
     * @param q
     */
    public   void displaybomb(int p,int q)
    {
        for(int i=0; i<n; i++)
        {
            for(int j=0 ; j<=n*5 ; j++)
                System.out.print("_");
            System.out.println();
            for(int j=0; j<n; j++)
                switch (board[i][j]) {
                    case 2:
                        System.out.print     ("|  X ");
                        break;
                    case 1:
                        System.out.print     ("|  B ");
                        break;
                    default:
                        System.out.print("|    ");
                        break;
                }
            System.out.print("|");   
            System.out.println();
        }
        for(int j=0 ; j<=n*5 ; j++)
            System.out.print("_");     
        System.out.println();
    }          

    public   void help()

    {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        System.out.println("MINESWEEPER: RULES AND BASICS");
        System.out.println();
        System.out.println("The Object :");
        System.out.println();
        System.out.println("Find the empty squares while avoiding the mines."); 
        System.out.println("The board"); 
        System.out.println("Minesweeper has three standard boards to choose from, each progressively more difficult.");
        System.out.println("Beginner: 36 tiles, 6 mines ; Intermediate: 81 tiles, 10 mines ; Expert: 144 tiles, 22 mines; Custom");
        System.out.println("How to play");
        System.out.println();
        System.out.println("The rules in Minesweeper are simple:");
        System.out.println("Uncover a mine, and the game ends.");
        System.out.println("Uncover an empty square, and you keep playing.");
        System.out.println("Uncover a number, and it tells you how many mines lay hidden in the ");
        System.out.println("Eight surrounding squares—information you use to deduce which nearby squares are safe to click.");
        System.out.println();
        System.out.println("if the grid is of" +n+"*"+n);
        show();
    }

    public   void show()
    {
        for(int i=0; i<n; i++)
        {
            for(int j=0 ; j<=n*5; j++)
                System.out.print("_");
            System.out.println();
            for(int k=0; k<n; k++)
            {
                int yy=(n*i)+k;
                if(yy<10)
                    System.out.print("| "+yy+" ");
                else
                    System.out.print("|"+yy+" ");
            }
            System.out.println();
        }
        for(int j=0 ; j<=n*5; j++)
            System.out.print("_");     
        System.out.println();
    }     
}
