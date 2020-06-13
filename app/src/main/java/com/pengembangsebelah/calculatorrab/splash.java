package com.pengembangsebelah.calculatorrab;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcel;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.pengembangsebelah.calculatorrab.myutils.Check;
import com.pengembangsebelah.calculatorrab.myutils.PlayMusic;
import com.pengembangsebelah.calculatorrab.myutils.dialogmaker.DialogMakerHandler;
import com.pengembangsebelah.calculatorrab.myutils.dialogmaker.DialogMakerUtils;
import com.pengembangsebelah.calculatorrab.ui.KopetensiDasar;
import com.pengembangsebelah.calculatorrab.ui.PaduanActi;
import com.pengembangsebelah.calculatorrab.ui.Profile;

public class splash extends AppCompatActivity {
    public static splash instant;
    public boolean ismenu= false;
    RelativeLayout imageView1,imageView2,imageView3,imageView4;
    public PlayMusic playMusic;
    boolean canExit = true;
    static int gg = 0;
    MediaController controller ;

    DialogMakerUtils dialogMakerUtils;
    public void ShowDialogNotifVideo(final MainActivity.AddSucces addSucces, final boolean setImg, final String mess){
        canExit = false;
        final DialogMakerHandler dialogMakerHandler = new DialogMakerHandler() {
            @Override
            public void handler(View view) {

                VideoView videoView = view.findViewById(R.id.video_notifa);
                controller.setMediaPlayer(videoView);
                Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.coba3);
                videoView.setVideoURI(uri);
                videoView.setMediaController(controller);
//                videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    @Override
//                    public void onCompletion(MediaPlayer mediaPlayer) {
//                        dialogMakerUtils.Dismiss();
//                        addSucces.Success("No");
//                    }
//                });
                videoView.start();
                videoView.seekTo(1);

                TextView textView = view.findViewById(R.id.text_notifa);
                textView.setText(mess);
                TextView btn1 = view.findViewById(R.id.btn_1);
                btn1.setText("Skip");

                final TextInputEditText namaProject = view.findViewById(R.id.input_name_project);
                final TextInputEditText location = view.findViewById(R.id.input_location);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogMakerUtils.Dismiss();
                        addSucces.Success("No");
                    }
                });
            }
        };

        dialogMakerUtils = new DialogMakerUtils(this,R.layout.dialog_video_layout,dialogMakerHandler);
        dialogMakerUtils.IsCancelable(false);
        dialogMakerUtils.Show();
    }

    public void ShowDialogNotif(final MainActivity.AddSucces addSucces, final boolean setImg, final String mess){
        canExit = false;
        final DialogMakerHandler dialogMakerHandler = new DialogMakerHandler() {
            @Override
            public void handler(View view) {
                ImageView img = view.findViewById(R.id.image_notifa);
                if(!setImg){img.setVisibility(View.GONE);}
                TextView textView = view.findViewById(R.id.text_notifa);
                textView.setText(mess);
                TextView btn1 = view.findViewById(R.id.btn_1);
                btn1.setText("Ya");
                TextView btn2 = view.findViewById(R.id.btn_2);
                btn2.setText("Tidak");

                final TextInputEditText namaProject = view.findViewById(R.id.input_name_project);
                final TextInputEditText location = view.findViewById(R.id.input_location);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogMakerUtils.Dismiss();
                        addSucces.Success("Ya");
                    }
                });
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogMakerUtils.Dismiss();
                    }
                });
            }
        };

        dialogMakerUtils = new DialogMakerUtils(this,R.layout.dialog_video_layout,dialogMakerHandler);
        dialogMakerUtils.SetOnDismis(dimisLis);
        dialogMakerUtils.Show();
    }
    DialogMakerUtils.Listener dimisLis = new DialogMakerUtils.Listener() {
        @Override
        public void OnDismis() {
            canExit = true;
        }
    };
    @Override
    public void onBackPressed() {
        if(canExit){
            ShowDialogNotif(new MainActivity.AddSucces() {
                @Override
                public void Success(String s) {
                    finish();
                }
            },false,"Yakin ingin keluar applikasi?");
        }else {
            super.onBackPressed();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instant = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        playMusic = new PlayMusic(this);
        controller = new MediaController(this);
        if(!splash.instant.playMusic.IsPlaying()) {
            splash.instant.playMusic.Start();
        }

        if(Check.getInteger("awalans",this,0)==0&&gg==0){
            ShowDialogNotifVideo(new MainActivity.AddSucces() {
                @Override
                public void Success(String s) {
                    tampil();
                }
            },false,"");
        }
        animatedButton();

        final FloatingActionButton floatingActionButton = findViewById(R.id.floating_add_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("JUNADI", "onClick: "+splash.instant.playMusic.IsPlaying());
                if(splash.instant.playMusic.IsPlaying()) {
                    splash.instant.playMusic.Stop();
                    floatingActionButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_volume_up_black_24dp));
                }else {
                    splash.instant.playMusic.Start();
                    floatingActionButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_volume_off_black_24dp));
                }
            }
        });
        if(splash.instant.playMusic.IsPlaying()) {
            floatingActionButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_volume_off_black_24dp));
        }else {
            floatingActionButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_volume_up_black_24dp));
        }

    }
    void tampil(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Hallo Siswa!\nSebelum menggunakan aplikasi ini harap membuka paduan yang ada\nSelamat Mencoba")
                .setTitle("SELAMAT DATANG")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gg=1;
                        dialog.dismiss();
                    }
                })
                .setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
//                    Intent i = new Intent(splash.this, PaduanActi.class);
//                    startActivity(i);
            }
        });
        dialog.show();
        gg=1;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!splash.instant.playMusic.IsPlaying()&&!ismenu) {
            playMusic.Start();
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        if(splash.instant.playMusic.IsPlaying()&&!ismenu) {
            splash.instant.playMusic.Stop();
        }
    }


    private Animation mRotateAnimation1 = null;
    private Animation mRotateAnimation2 = null;
    private Animation mRotateAnimation3 = null;
    private Animation mRotateAnimation4 = null;



    void animatedButton(){
//        imageView1 = findViewById(R.id.imageView);
//        imageView2 = findViewById(R.id.imageView2);
//        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.linear4);

//        mRotateAnimation1 = AnimationUtils.loadAnimation(this, R.anim.rotation_rev);
//        mRotateAnimation2 = AnimationUtils.loadAnimation(this, R.anim.rotation_rev);
//        mRotateAnimation3 = AnimationUtils.loadAnimation(this, R.anim.rotation_rev);
        mRotateAnimation4 = AnimationUtils.loadAnimation(this, R.anim.bubul);

//        imageView1.startAnimation(mRotateAnimation1);
//        imageView2.startAnimation(mRotateAnimation2);
//        imageView3.startAnimation(mRotateAnimation3);
        imageView4.startAnimation(mRotateAnimation4);

//        imageView1.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                //event.
//                //v.clearAnimation();
//                return false;
//            }
//        });
//        imageView2.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                //v.clearAnimation();
//                return false;
//            }
//        });
//        imageView3.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                //v.clearAnimation();
//                return false;
//            }
//        });
        imageView4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //v.clearAnimation();
                return false;
            }
        });

    }

    public void OnClick(View j){
        if(splash.instant.playMusic.IsPlaying()) {
            playMusic.Stop();
        }
        MainActivity.Init(splash.this);
        //finish();
    }

    public void KD(View view){
        ismenu = true;
        Intent i = new Intent(this, KopetensiDasar.class);
        startActivity(i);
    }
    public void Paduan(View view){
        ismenu = true;
        Intent i = new Intent(this, PaduanActi.class);
        startActivity(i);
    }
    public void Profile(View view){
        ismenu = true;
        Intent i = new Intent(this, Profile.class);
        startActivity(i);
    }
}
