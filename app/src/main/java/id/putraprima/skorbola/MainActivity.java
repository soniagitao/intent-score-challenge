package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE_HOME = 1 ;
    private static final int GALLERY_REQUEST_CODE_AWAY = 2 ;

    public static final String HOMETEAM_KEY = "home";
    public static final String AWAYTEAM_KEY = "away";
    public static final String IMAGEHOME_KEY = "imageHome";
    public static final String IMAGEAWAY_KEY = "imageAway";

    private EditText homeTeam;
    private EditText awayTeam;
    private ImageView imagehome;
    private ImageView imageaway;
    private Bitmap fotohome;
    private Bitmap fotoaway;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeTeam = findViewById(R.id.home_team);
        awayTeam = findViewById(R.id.away_team);
        imagehome = findViewById(R.id.home_logo);
        imageaway = findViewById(R.id.away_logo);

        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team

        //4. Ganti Logo Away Team

    }



    //3. Ganti Logo Home Team
    public void handleChangeAvatar(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    public void handleChangeAvatar2(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 0) {
            return;
        }

        if (requestCode == GALLERY_REQUEST_CODE_HOME) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    Bitmap bmp = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    imagehome.setImageBitmap(bmp);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }

        if (requestCode == GALLERY_REQUEST_CODE_AWAY) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    Bitmap bmp = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    imageaway.setImageBitmap(bmp);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    //5. Next Button Pindah Ke MatchActivity
    public void handleNext(View view) {
        String home = homeTeam.getText().toString();
        String away = awayTeam.getText().toString();

        if ((home).equals("") || (away).equals("")){
            Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }else {
            imagehome.setDrawingCacheEnabled(true);
            fotohome = imagehome.getDrawingCache();
            imageaway.setDrawingCacheEnabled(true);
            fotoaway = imageaway.getDrawingCache();

            Intent intent = new Intent(this, MatchActivity.class);

            intent.putExtra(HOMETEAM_KEY, home);
            intent.putExtra(AWAYTEAM_KEY, away);
            intent.putExtra(IMAGEHOME_KEY, String.valueOf(imagehome));
            intent.putExtra(IMAGEAWAY_KEY, String.valueOf(imageaway));

            startActivity(intent);
        }
    }
}
