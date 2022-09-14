package com.dragon.toolbox.seekbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.SeekBar;

import com.dragon.toolbox.R;
import com.google.android.material.snackbar.Snackbar;

public class SeekbarActivity extends AppCompatActivity {
    private SeekBar mSbNopadding;
    private SeekBar mSb;
    private EditText mEditSeekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);
        mSbNopadding = (SeekBar) findViewById(R.id.sb_nopadding);
        mSb = (SeekBar) findViewById(R.id.sb);
        mSb.post(new Runnable() {
            @Override
            public void run() {
                Rect bounds = mSb.getThumb().getCurrent().getBounds();
                int width = bounds.width();
                int height = bounds.height();
                Log.e("dragon", "width:" + width);
                Log.e("dragon", "height:" + height);
            }
        });
        mSbNopadding.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (!fromUser) {
                    return;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(mSb, 0);
        mEditSeekbar = (EditText) findViewById(R.id.edit_seekbar);
        mEditSeekbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.isFocused()) {
                    v.setFocusable(false);
                }else {

                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.menu_choose:
                toast("choose");
                return true;
            case R.id.menu_save:
                toast("save");
                return true;
            case R.id.menu_leak:
                toast("leak");
                return true;
            case R.id.menu_apple:
                toast("apple");
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void toast(String msg) {
        Snackbar.make(mSb, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_seekbar, menu);
        return true;
    }
}