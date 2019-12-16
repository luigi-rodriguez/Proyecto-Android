package com.example.youtube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide; // Importación de la libreria de Glide

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


public class MainActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener, YouTubePlayer.PlaybackEventListener {

    // Se crea una variable donde contiene la clave generada y guardada desde los servicios de google
    String claveYoutube= "AIzaSyCfKOUTc9zx_1_4FdMMRw63vDOMH4MMLoU";

    // se crear una variable
    YouTubePlayerView youtubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    // se llama los metododos utilizados para las imagenes
      ImageView ImageView = findViewById(R.id.imageView1);
      String GifURL1 = "https://i.pinimg.com/originals/81/6c/10/816c1034174a65f999d1af8db905828f.gif";
        // Muestra la image Gif con Glide
        Glide.with(this)
                .load(GifURL1)
                .placeholder(R.drawable.image1)
                .into(ImageView);

    // se llama los metododos utilizados para las imagenes
      ImageView imageView2 = findViewById(R.id.imageView2);
      String GifURL = "https://i.pinimg.com/originals/83/df/a4/83dfa4bd8729fceba2fc7d3e7bf13ac0.gif";
        // Muestra la image Gif con Glide
        Glide.with(this).load(GifURL).into(imageView2);


        // se llama la vista de la ventana del youtube
        youtubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_view);

        // se obtiene la clave
        youtubePlayerView.initialize(claveYoutube, this);

    }

    @Override // si se ejecuta de forma corecta
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean fuerestaurado) {
        if (!fuerestaurado) {
            //youTubePlayer.cueVideo("ZF0958S0sW8"); // https://www.youtube.com/watch?v=ZF0958S0sW8
            //youTubePlayer.cueVideo("BZ2obKR8yOo"); // https://www.youtube.com/watch?v=BZ2obKR8yOo
            //youTubePlayer.cueVideo("GA9JvTWUXF4"); // https://www.youtube.com/watch?v=GA9JvTWUXF4
            //youTubePlayer.cueVideo("GA9JvTWUXF4"); // https://www.youtube.com/watch?v=hHUClrp8jvI
            //youTubePlayer.cueVideo("GA9JvTWUXF4"); // https://www.youtube.com/watch?v=RjMWh1vve6E
            youTubePlayer.cueVideo("XNtTEibFvlQ");// https://www.youtube.com/watch?v=XNtTEibFvlQ
        }
    }

    @Override // si NO ejecuta de forma corecta
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) { // si Existe un error...
            youTubeInitializationResult.getErrorDialog(this, 1).show();
        }
        else {
            String error = "Error al Inicializar Youtube" + youTubeInitializationResult.toString();

            Toast.makeText(getApplication(),error,Toast.LENGTH_LONG).show();
        }
    }

    protected void OnActivityResult(int requestCode, int resultcode, Intent data) {
        if (requestCode == 1) { //valida que si devuelve un 1 se resolvio el erro
            getYoutubePlayerProvider().initialize(claveYoutube, this);
        }
    }

    protected YouTubePlayer.Provider getYoutubePlayerProvider() {
        return youtubePlayerView;
    }

    @Override // evento que indica PLAY al video
    public void onPlaying() {

    }
    @Override // evento que indica PAUSA al video
    public void onPaused() {

    }
    @Override // evento que indica DETIENE al video
    public void onStopped() {

    }
    @Override
    public void onBuffering(boolean b) {

    }
    @Override
    public void onSeekTo(int i) {

    }





}