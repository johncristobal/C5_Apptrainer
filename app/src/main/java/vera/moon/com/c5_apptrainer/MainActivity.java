package vera.moon.com.c5_apptrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Integer> numbers;

    public int locationcorrect;

    public int a,b,score=0,numberQ=0;

    public TextView result,suma,points,seconds;

    public Button startB, but1,but2,but3,but0,play;

    public RelativeLayout relative;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relative = (RelativeLayout)findViewById(R.id.rel);

        numbers = new ArrayList<Integer>();

        startB = (Button)findViewById(R.id.startB);
        GridLayout gid = (GridLayout)relative.findViewById(R.id.gridLayout);
        suma = (TextView)findViewById(R.id.suma);
        result = (TextView)findViewById(R.id.result);
        points = (TextView)findViewById(R.id.points);
        seconds = (TextView)findViewById(R.id.seconds);

        but0 = (Button)findViewById(R.id.button0);
        but1 = (Button)findViewById(R.id.button1);
        but2 = (Button)findViewById(R.id.button2);
        but3 = (Button)findViewById(R.id.button3);
        play = (Button)findViewById(R.id.again);

        //Two differntes random
        /*Random rand = new Random();
        a = rand.nextInt(21);
        b = rand.nextInt(21);
        suma.setText(a + "+" + b);*/
        playAgaon(findViewById(R.id.again));
    }

    public void startMethod(View v){
        startB.setVisibility(View.INVISIBLE);

        relative.setVisibility(View.VISIBLE);
    }

    //Seleccionaa boton
    public void chooseAnswer(View v){
        String buttonnum = v.getTag().toString();

        if(buttonnum.equals(""+locationcorrect)){
            Log.d("Match","Correct Answer");

            score++;

            result.setText("Correct!");


        }else {
            result.setText("Wrong!");
        }

        numberQ++;

        points.setText(score+"/"+numberQ);

        generateQ();
    }

    public void generateQ(){

        //Two differntes random
        Random rand = new Random();
        a = rand.nextInt(21);
        b = rand.nextInt(21);
        suma.setText(a + "+" + b);

        locationcorrect = rand.nextInt(4);

        int incorrectAns;

        numbers.clear();

        //Add results to array
        for(int i=0;i<4;i++){
            if(i == locationcorrect){
                numbers.add(a+b);
            }else{
                incorrectAns = rand.nextInt(41);
                //If the anser is repeated
                while(incorrectAns == (a+b)){
                    incorrectAns = rand.nextInt(41);
                }
                numbers.add(incorrectAns);
            }
        }

        //set buttons
        but0.setText(numbers.get(0)+"");
        but1.setText(numbers.get(1)+"");
        but2.setText(numbers.get(2)+"");
        but3.setText(numbers.get(3)+"");
    }

    public void playAgaon(View v){
        score = 0;
        numberQ = 0;

        seconds.setText("30s");
        points.setText("0/0");
        result.setText("");

        play.setVisibility(View.INVISIBLE);

        generateQ();

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long l) {
                //Each second

                seconds.setText((l/1000)+"");

            }

            @Override
            public void onFinish() {

                seconds.setText((0)+"s");

                result.setText("your score: "+score+"/"+numberQ);

                play.setVisibility(View.VISIBLE);
            }
        }.start();
    }
}
