package com.example.decorum;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class RenderFurnitureActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_render_furniture);

        Intent intent = getIntent();
        String selection = intent.getStringExtra(com.example.decorum.BrowseFurnitureActivity.SELECTED_FURNITURE);
        Log.d("TESTING PUTEXTRA", selection);
    }

}
