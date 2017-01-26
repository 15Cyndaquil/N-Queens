package com.example.cynda.test;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    static int layoutHeight;
    static int layoutWidth;
    static int layoutTopBoarder;
    static int square;

    static int boardSize = 8;
    String[][] boardOfUsed = setBoard();
    String[][] boardOfButtons = setBoardBT();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        setArea();

        setButtons();

    }


    public static String[][] usedQueen(int usedBT, String[][] board){
        for(int x=0; x<boardSize; x++){
            for(int y=0; y<boardSize; y++){
                if(((x+1)+(boardSize*(y+1)))==usedBT){
                board[x][y]="Q ";
                }
            }
        }
        return board;
    }

    public static String[][] setUsed(String[][] currentBoard, String[][] boardOfButtons){
        String[][] output = new String[boardSize][boardSize];

        int row, column;
        boolean go;

        //Initializes output board
        for(int rowSetup=0; rowSetup<boardSize; rowSetup++){
            for(int columnSetup=0; columnSetup<boardSize; columnSetup++){
                output[rowSetup][columnSetup]="G ";
            }
        }

        for(int rowEdit=0; rowEdit<boardSize; rowEdit++){
            for(int columnEdit=0; columnEdit<boardSize; columnEdit++){

                if(currentBoard[rowEdit][columnEdit].equals("Q ")){
                    output[rowEdit][columnEdit]="Q ";
                    //Marks all squares to the RIGHT of current QUEEN as USED
                    go=true;
                    row=rowEdit; column=columnEdit;
                    while(go){
                        try{
                            row++;
                            output[row][column]="U ";
                        }catch (ArrayIndexOutOfBoundsException exc){go=false;}
                    }

                    //Marks all squares to the LEFT of the current QUEEN as USED
                    go=true;
                    row=rowEdit; column=columnEdit;
                    while(go){
                        try{
                            row--;
                            output[row][column]="U ";
                        }catch (ArrayIndexOutOfBoundsException exc){go=false;}
                    }

                    //Marks all squares ABOVE the current QUEEN as USED
                    go=true;
                    row=rowEdit; column=columnEdit;
                    while(go){
                        try{
                            column++;
                            output[row][column]="U ";
                        }catch (ArrayIndexOutOfBoundsException exc){go=false;}
                    }

                    //Marks all squares BELOW the current QUEEN as USED
                    go=true;
                    row=rowEdit; column=columnEdit;
                    while(go){
                        try{
                            column--;
                            output[row][column]="U ";
                        }catch (ArrayIndexOutOfBoundsException exc){go=false;}
                    }

                    //Marks all squares in a diagonal going RIGHT and ABOVE
                    //current QUEEN as USED
                    go=true;
                    row=rowEdit; column=columnEdit;
                    while(go){
                        try{
                            row++;
                            column++;
                            output[row][column]="U ";
                        }catch (ArrayIndexOutOfBoundsException exc){go=false;}
                    }

                    //Marks all squares in a diagonal going LEFT and BELOW
                    //current QUEEN as USED
                    go=true;
                    row=rowEdit; column=columnEdit;
                    while(go){
                        try{
                            row--;
                            column--;
                            output[row][column]="U ";
                        }catch (ArrayIndexOutOfBoundsException exc){go=false;}
                    }

                    //Marks all squares in a diagonal going RIGHT and BELOW
                    //current QUEEN as USED
                    go=true;
                    row=rowEdit; column=columnEdit;
                    while(go){
                        try{
                            row++;
                            column--;
                            output[row][column]="U ";
                        }catch (ArrayIndexOutOfBoundsException exc){go=false;}
                    }

                    //Marks all squares in a diagonal going LEFT and BELOW
                    //current QUEEN as USED
                    go=true;
                    row=rowEdit; column=columnEdit;
                    while(go){
                        try{
                            row--;
                            column++;
                            output[row][column]="U ";
                        }catch (ArrayIndexOutOfBoundsException exc){go=false;}
                    }
                }
            }
        }
//        final String[][] boardOfUsed = output;

        return output;
    }

    public static String[][] setBoardBT(){
        String[][] board = new String[boardSize][boardSize];

        for(int x=0; x<boardSize; x++){
            for(int y=0; y<boardSize; y++){
                board[x][y] = String.valueOf((x + 1) + (boardSize * (y + 1)));
            }
        }
        return board;
    }

    public static String[][] setBoard(){
        String[][] board = new String[boardSize][boardSize];

        for(int x=0; x<boardSize; x++){
            for(int y=0; y<boardSize; y++){
                board[x][y]="G ";
            }
        }
        return board;
    }

    public static String checkBoard(String[][] currentBoard){
        int used = 0;
        int queen =0;
        String output;

        for(int x=0; x<boardSize; x++){
            for(int y=0; y<boardSize; y++){
                if(currentBoard[x][y].equals("U ")){
                    used++;
                }else if(currentBoard[x][y].equals("Q ")){
                    queen++;
                }
            }
        }
        if((used+queen)==(boardSize*boardSize)){
            if(queen==boardSize){
                output="Success";
            }else{
                output="Failed";
            }
        }else{
            output="Playing";
        }
        return output;
    }

    public  void setButtons(){
        setContentView(R.layout.activity_main);

        final TextView currentStatusTV = (TextView) findViewById(R.id.currentStatusTV);
        LinearLayout resetBTView = (LinearLayout) findViewById(R.id.resetBTView);
        Button resetBoardBT = (Button) findViewById(R.id.resetBT);

        resetBoardBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

        final TextView color = (TextView) findViewById(R.id.colorTV);

        LinearLayout layout = (LinearLayout) findViewById(R.id.button_layout);
        layout.removeAllViews();
        layout.setOrientation(LinearLayout.VERTICAL);  //Can also be done in xml by android:orientation="vertical"

        for (int i = 0; i < boardSize; i++) {
            LinearLayout row = new LinearLayout(this);
            row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            for (int j = 0; j < boardSize; j++) {
                final ImageButton btnTag = new ImageButton(this);
                btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                if(j%2==0){
                    if(i%2==0){
                        btnTag.setBackground(getResources().getDrawable(R.color.white));
                        color.setText("white");
                    }else {
                        btnTag.setBackground(getResources().getDrawable(R.color.black));
                        color.setText("black");
                    }
                }else{
                    if(i%2==0){
                        btnTag.setBackground(getResources().getDrawable(R.color.black));
                        color.setText("black");
                    }else{
                        btnTag.setBackground(getResources().getDrawable(R.color.white));
                        color.setText("white");
                    }
                }

                btnTag.setId((i+1)+(boardSize*(j+1)));
                row.addView(btnTag);
                btnTag.setMaxHeight(square);
                btnTag.setMaxWidth(square);
                btnTag.setMinimumHeight(square);
                btnTag.setMinimumWidth(square);
                btnTag.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for(int x=0; x<boardSize; x++){
                            for(int y=0; y<boardSize; y++){
                                if(Integer.parseInt(boardOfButtons[x][y])==btnTag.getId()){
                                    if(boardOfUsed[x][y].equals("G ")){
                                        boardOfUsed=setUsed(usedQueen(btnTag.getId(), boardOfUsed),boardOfButtons);
                                        btnTag.setBackground(getResources().getDrawable(R.color.blue));
                                        currentStatusTV.setText("Current Status: "+checkBoard(boardOfUsed));
                                    }
                                }
                            }
                        }
                    }
                });
            }
            layout.addView(row);
        }
        layout.addView(currentStatusTV);
        layout.addView(resetBTView);
    }

    public void setArea(){
        Rect rect = new Rect();
        Window win = getWindow();
        win.getDecorView().getWindowVisibleDisplayFrame(rect);
        layoutHeight = rect.height();
        layoutWidth = rect.width();
        layoutTopBoarder = rect.top;

        System.out.println(layoutTopBoarder);

        if((layoutWidth/boardSize)<((layoutHeight/boardSize))){
            square = (layoutWidth/boardSize);
            System.out.println("LayoutWidth: "+rect.width());
        }else if(((layoutHeight/boardSize))<(layoutWidth/boardSize)){
            square = (layoutHeight/boardSize);
            System.out.println("LayoutHeight: "+ layoutHeight/boardSize);
        }else{square=10;}

        System.out.println(rect.width());
        System.out.println(rect.height());


    }

}
