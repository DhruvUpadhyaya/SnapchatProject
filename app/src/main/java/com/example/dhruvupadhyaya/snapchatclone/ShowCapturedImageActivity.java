package com.example.dhruvupadhyaya.snapchatclone;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ShowCapturedImageActivity extends AppCompatActivity {

    String Uid;

    Bitmap rotateBitmap;
    private Button mStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_captured_image);

        Bundle extras = getIntent().getExtras();

        assert extras != null;
        byte[] b = extras.getByteArray("capture");

        if (b!=null){

            ImageView image = findViewById(R.id.imageCapture);

            Bitmap decodedBitmap = BitmapFactory.decodeByteArray(b,0,b.length);

            Bitmap rotateBitmap = rotate(decodedBitmap);

            image.setImageBitmap(rotateBitmap);
        }

        Uid = FirebaseAuth.getInstance().getUid();
         mStory = findViewById(R.id.storyButtonId);


        mStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToStories();
            }
        });
    }

    private void saveToStories() {
        final DatabaseReference userStoryDb = FirebaseDatabase.getInstance().getReference().child("users").child(Uid).child("story");

        final String key = userStoryDb.push().getKey();

       final StorageReference filePath = FirebaseStorage.getInstance().getReference().child("captures").child(key);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        rotateBitmap.compress(Bitmap.CompressFormat.JPEG,20,baos);

        final byte[] dataToUpload = baos.toByteArray();

      final   UploadTask uploadTask  = filePath.putBytes(dataToUpload);

        uploadTask.addOnSuccessListener(this,new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {




                      // Uri uri = filePath.putBytes(dataToUpload);

                        Long currentTimeStamp = System.currentTimeMillis();
                        Long endTimeStamp =currentTimeStamp+(24*60*60*1000);

                        Map<String,Object> mapToUpload = new HashMap<>();
                        mapToUpload.put("imageUrl",uri.toString());
                        mapToUpload.put("timeStampBeg",currentTimeStamp);
                        mapToUpload.put("timeStampEnd",endTimeStamp);



                        userStoryDb.child(key).setValue(mapToUpload);
                       finish();
                        //return;
                        }


                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ShowCapturedImageActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        finish();
                        return;
                    }
                });
            }

        });









    }

    private Bitmap rotate(Bitmap decodedBitmap) {
        int w = decodedBitmap.getWidth();
        int h = decodedBitmap.getHeight();

        Matrix matrix = new Matrix();
        matrix.setRotate(90);

        return  Bitmap.createBitmap(decodedBitmap,0,0,w,h,matrix,true);
    }
}
