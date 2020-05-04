package com.girlesc.enguard.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.girlesc.enguard.R;

import java.io.IOException;
import java.util.UUID;

public class VoiceRecordingActivity<REQUEST_PERMISSION_CODE> extends AppCompatActivity {

    Button btnRecord, btnPlay;
    String pathSave = "";
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;

    final int REQUEST_PERMISSION_CODE = 1000;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_recording);

        btnPlay = (Button) findViewById(R.id.playBtn);
        btnRecord = (Button) findViewById(R.id.recordBtn);
        final Button btnStopPlay = (Button) findViewById(R.id.stopplayBtn);
        final Button btnStopRecord = (Button) findViewById(R.id.stoprecordBtn);

        if (checkPermissionFromDevice()) {
            btnRecord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pathSave = Environment.getExternalStoragePublicDirectory("something")
                            .getAbsolutePath() + "/"
                            + UUID.randomUUID().toString() + "_audio_record.3gp";

                    try {
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    btnPlay.setEnabled(false);
                    btnStopPlay.setEnabled(false);

                    Toast.makeText(VoiceRecordingActivity.this, getResources().getString(R.string.tv_recording), Toast.LENGTH_SHORT).show();
                }
            });

            btnStopRecord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mediaRecorder.stop();
                    btnStopRecord.setEnabled(false);
                    btnPlay.setEnabled(true);
                    btnRecord.setEnabled(true);
                    btnStopPlay.setEnabled(false);
                }
            });

            btnPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnStopPlay.setEnabled(true);
                    btnStopRecord.setEnabled(false);
                    btnRecord.setEnabled(false);

                    mediaPlayer = new MediaPlayer();
                    try {
                        mediaPlayer.setDataSource(pathSave);
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    mediaPlayer.start();
                    Toast.makeText(VoiceRecordingActivity.this, getResources().getString(R.string.tv_playing), Toast.LENGTH_SHORT).show();
                }
            });

            btnStopPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnStopRecord.setEnabled(false);
                    btnRecord.setEnabled(true);
                    btnStopPlay.setEnabled(false);
                    btnPlay.setEnabled(true);

                    if (mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        setUpMediaRecorder();
                    }

                }
            });
        } else {
            requestPermission();
        }

    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO
        }, REQUEST_PERMISSION_CODE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, getResources().getString(R.string.tv_grant_permission), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, getResources().getString(R.string.tv_deny_permission), Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }


    private boolean checkPermissionFromDevice() {
        int write_external_storage_result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int record_audio_result = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        return write_external_storage_result == PackageManager.PERMISSION_GRANTED && record_audio_result == PackageManager.PERMISSION_GRANTED;
    }


    private void setUpMediaRecorder() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(pathSave);
    }
}
