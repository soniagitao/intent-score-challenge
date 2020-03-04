package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchActivity extends AppCompatActivity {

    private static final int REQUEST_HOME = 0;
    private static final int REQUEST_AWAY = 1;
    public static final String HASIL_KEY = "hasil";

    private TextView homeText;
    private TextView awayText;
    private ImageView homeLogo;
    private ImageView awayLogo;
    private TextView scorehome;
    private TextView scoreaway;
    int scoreHome = 0;
    int scoreAway = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        homeText = findViewById(R.id.txt_home);
        awayText = findViewById(R.id.txt_away);
        homeLogo = findViewById(R.id.home_logo);
        awayLogo = findViewById(R.id.away_logo);
        scorehome = findViewById(R.id.score_home);
        scoreaway = findViewById(R.id.score_away);

        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan memindah activity ke scorerActivity dimana pada scorer activity di isikan nama pencetak gol
        //3.Dari activity scorer akan mengirim kembali ke activity matchactivity otomatis nama pencetak gol dan skor bertambah +1
        //4.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang beserta nama pencetak gol ke ResultActivity, jika seri di kirim text "Draw",

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String home = extras.getString(MainActivity.HOMETEAM_KEY);
            homeText.setText(home);

            String away = extras.getString(MainActivity.AWAYTEAM_KEY);
            awayText.setText(away);

            Bitmap bmp1 = (Bitmap) extras.get("homelogo");
            homeLogo.setImageBitmap(bmp1);

            Bitmap bmp2 = (Bitmap) extras.get("awaylogo");
            awayLogo.setImageBitmap(bmp2);
        }
    }

    public void handlehome(View view) {
        Intent intent = new Intent(this, ScorerActivity.class);
        startActivityForResult(intent, REQUEST_HOME);
    }

    public void handleaway(View view) {
        Intent intent = new Intent(this, ScorerActivity.class);
        startActivityForResult(intent, REQUEST_AWAY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_HOME) {
            String returnString = data.getStringExtra("keyName");

            TextView TVhome = (TextView) findViewById(R.id.txt_home1);
            String ScorerHome = " ";
            ScorerHome += "\n" + returnString;
            TVhome.setText(ScorerHome);
            scoreHome++;
            scorehome.setText(String.valueOf(scoreHome));

        }else if (requestCode == REQUEST_AWAY){
            String returnString = data.getStringExtra("keyName");

            TextView TVaway = (TextView) findViewById(R.id.txt_away1);
            String ScorerAway = " " ;
            ScorerAway += "\n" + returnString;
            TVaway.setText(ScorerAway);
            scoreAway++;
            scoreaway.setText(String.valueOf(scoreAway));
        }

    }

    public void handleResult(View view) {
        String hasil = null;
        if (scoreHome == scoreAway){
            hasil = "Draw";
        }else if (scoreHome>scoreAway) {
            hasil = homeText.getText().toString();
        }else if (scoreAway>scoreHome) {
            hasil = awayText.getText().toString();
        }

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(HASIL_KEY, hasil);
        startActivity(intent);
    }
}
