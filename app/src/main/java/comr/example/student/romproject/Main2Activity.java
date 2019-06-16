package comr.example.student.romproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;

import java.util.Random;


public class Main2Activity extends AppCompatActivity  {
    ImageView cardleft, cardright;
    TextView scoreleft, scoreright,time1;
    Button b_deal;
    Random r;
    int leftScore, rightScore ;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

            cardleft = (ImageView) findViewById(R.id.cardleft);
            cardright = (ImageView) findViewById(R.id.cardright);
            scoreleft = (TextView) findViewById(R.id.scoreleft);
            scoreright = (TextView) findViewById(R.id.scoreright);
            b_deal = (Button) findViewById(R.id.b_deal);
            r = new Random();

        SharedPreferences preferences1 = getSharedPreferences("maxscore1", Context.MODE_PRIVATE);
        int sp1 = preferences1.getInt("maxscore2", 0);



            if(leftScore>rightScore&&timer>=120){
                AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                builder.setMessage("You WON!");
                builder.setCancelable(true);
                builder.setPositiveButton("OK", new emptydialog());
                AlertDialog dialog = builder.create();
                dialog.show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                if(sp1<leftScore){
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("maxscore2", leftScore);
                editor.commit();
            }}
            if(rightScore>leftScore&&timer>=120) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                builder.setMessage("You Lost!");
                builder.setCancelable(true);
                builder.setPositiveButton("OK", new emptydialog());
                AlertDialog dialog = builder.create();
                dialog.show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }

            b_deal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int leftCard = r.nextInt(13) + 2; // cards 2-14
                    int rightCard = r.nextInt(13) + 2; // cards 2-14

                    //show card images

                    int leftImage = getResources().getIdentifier("card" + leftCard, "drawable", getPackageName());

                    cardleft.setImageResource(leftImage);

                    int rightImage = getResources().getIdentifier("card" + rightCard, "drawable", getPackageName());
                    cardright.setImageResource(rightImage);


                    //compare cards,add points and display them

                    if (leftCard > rightCard) {
                        leftScore++;
                        scoreleft.setText(String.valueOf(leftScore));

                    } else if (leftCard < rightCard) {
                        rightScore++;
                        scoreright.setText(String.valueOf(rightScore));

                    } else {
                        Toast.makeText(Main2Activity.this, "war", Toast.LENGTH_SHORT).show();
                    }

                }
            });


        }


    public class emptydialog implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
        }
    }

    public class GameThread extends Thread {
        public void run() {
            super.run();
            while (true) {
                    try {
                        Thread.sleep(1000);
                        gamehandler.sendEmptyMessage(0);
                        time = time + 1;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


}