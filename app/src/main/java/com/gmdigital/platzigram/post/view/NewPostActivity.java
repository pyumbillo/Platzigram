package com.gmdigital.platzigram.post.view;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.gmdigital.platzigram.PlatzigramApplication;
import com.gmdigital.platzigram.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


import java.io.ByteArrayOutputStream;

import static com.squareup.picasso.Picasso.*;

public class NewPostActivity extends AppCompatActivity {
    private static final String TAG = "Post Activity";
    private ImageView imgPhoto;
    private Button btnCreatePost;
    private String photoPath;
    private PlatzigramApplication app;
    private StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        app= (PlatzigramApplication) getApplicationContext();
        storageReference=app.getStorageReference();
        imgPhoto=(ImageView) findViewById(R.id.imgPhoto);
        btnCreatePost= findViewById(R.id.btnCreatePost);

        if (getIntent().getExtras()!=null)
        {
            photoPath= getIntent().getExtras().getString("PHOTO_PATH_TEMP");
            showPhoto();
        }
        btnCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadPhoto();
            }
        });

    }

    private void uploadPhoto() {
        imgPhoto.setDrawingCacheEnabled(true);
        imgPhoto.buildDrawingCache();
        Bitmap bitmap = imgPhoto.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] photoByte = baos.toByteArray();
        String photoName = photoPath.substring(photoPath.lastIndexOf("/")+1,photoPath.length());
        StorageReference photoReference = storageReference.child("postImages/"+photoName);

        UploadTask  uploadTask = photoReference.putBytes(photoByte);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG,"URL Photo: "+e.toString());
                FirebaseCrash.report(e);
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri uriPhoto= taskSnapshot.getUploadSessionUri();
                String photoURL = uriPhoto.toString();
                Log.w(TAG,"URL Photo: "+photoURL);
                finish();
            }
        });


    }

    private void showPhoto(){
        Picasso.get().load(photoPath).into(imgPhoto);
    }
}
