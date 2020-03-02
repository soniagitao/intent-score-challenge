package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchActivity extends AppCompatActivity {

    private TextView homeText;
    private TextView awayText;
    private ImageView homeLogo;
    private ImageView awayLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        homeText = findViewById(R.id.txt_home);
        awayText = findViewById(R.id.txt_away);
        homeLogo = findViewById(R.id.home_logo);
        awayLogo = findViewById(R.id.away_logo);

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
        startActivity(intent);
    }

    public void handleaway(View view) {
        Intent intent = new Intent(this, ScorerActivity.class);
        startActivity(intent);
    }
}
