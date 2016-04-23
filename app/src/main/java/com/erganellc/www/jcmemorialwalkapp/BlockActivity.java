package com.erganellc.www.jcmemorialwalkapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Jiakuan on 11/30/2015.
 */
public class BlockActivity extends Activity {
    private ImageButton start, wwi, wwii, korea, viet, gulf, iraq, afghan, women, purple, pow;

    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_plazas);

        start = (ImageButton)findViewById(R.id.intro);
        start.setOnClickListener(new Listener("start"));

        wwi = (ImageButton)findViewById(R.id.wwi);
        wwi.setOnClickListener(new Listener("wwi"));

        wwii = (ImageButton)findViewById(R.id.wwii);
        wwii.setOnClickListener(new Listener("wwii"));

        korea = (ImageButton)findViewById(R.id.korea);
        korea.setOnClickListener(new Listener("korea"));

        viet = (ImageButton)findViewById(R.id.vietnam);
        viet.setOnClickListener(new Listener("vietnam"));

        gulf = (ImageButton)findViewById(R.id.gulf);
        gulf.setOnClickListener(new Listener("gulf"));

        iraq = (ImageButton)findViewById(R.id.iraq);
        iraq.setOnClickListener(new Listener("iraq"));

        afghan = (ImageButton)findViewById(R.id.afghan);
        afghan.setOnClickListener(new Listener("afghan"));

        women = (ImageButton)findViewById(R.id.women);
        women.setOnClickListener(new Listener("women"));

        purple = (ImageButton)findViewById(R.id.purple);
        purple.setOnClickListener(new Listener("purple"));

        pow = (ImageButton)findViewById(R.id.pow);
        pow.setOnClickListener(new Listener("pow"));

    }

    class Listener implements View.OnClickListener {
        private String pointName;
        public Listener(String pointName) {
            this.pointName = pointName;
        }
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(BlockActivity.this, PlayerActivity.class);
            intent.putExtra("pointName", pointName);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

//    @Override
//    public void finish() {
//        Intent intent = new Intent(this, MainActivity.class);
//        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
//    }
}
