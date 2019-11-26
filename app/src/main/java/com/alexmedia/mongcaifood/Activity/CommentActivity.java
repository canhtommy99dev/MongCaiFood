package com.alexmedia.mongcaifood.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alexmedia.mongcaifood.LoginWithApp;
import com.alexmedia.mongcaifood.Model.ModelComment;
import com.alexmedia.mongcaifood.R;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    ImageView btnBack;
    CircleImageView imageAvater;
    EditText edtComment;
    SeekBar seekBarRating;
    Button commentTrucTiep;
    TextView account1,textViewRating;
    String id,name,comment,imagecomment;
    int seek;
    DatabaseReference dataCommentDanhGia;
    Intent intent;
    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;
    private Uri imgUrl;
    private static final int CHOOSE_IMAGE = 1;
    StorageTask mUploadTasks;
    ProgressBar pgmc;
    StorageReference anhmc;
    GoogleSignInAccount account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        intent = getIntent();
        btnBack = findViewById(R.id.imgBa2);
        imageAvater = findViewById(R.id.accountgooglecomment1);
        edtComment = findViewById(R.id.editTextName);
        seekBarRating = findViewById(R.id.seekBarRating);
        commentTrucTiep = findViewById(R.id.buttonAddTrack);
        account1 = findViewById(R.id.account_gooogke);
        textViewRating = findViewById(R.id.txtDanhSo);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        anhmc = FirebaseStorage.getInstance().getReference("CuaHang/KhachHang");

        gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();
        seekBarRating.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textViewRating.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        dataCommentDanhGia = FirebaseDatabase.getInstance().getReference("CommentBaiViet").child(intent.getStringExtra(InfomationFoody.ID));
        commentTrucTiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = account1.getText().toString();
                comment = edtComment.getText().toString();
                seek = seekBarRating.getProgress();
                imagecomment = imgUrl.toString();
                if (!TextUtils.isEmpty(comment)){
                    id =  dataCommentDanhGia.push().getKey();
                    ModelComment modelComment = new ModelComment(id,name,comment,seek,imagecomment);
                    modelComment.setId(id);
                    dataCommentDanhGia.child(id).setValue(modelComment);
                    Toast.makeText(CommentActivity.this, "Cảm ơn bạn đã comment đến cửa hàng", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(CommentActivity.this, "Yêu cầu nhập Comment", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    private void handleSignInResult(GoogleSignInResult result){
        if(result.isSuccess()){
            GoogleSignInAccount account=result.getSignInAccount();
            account1.setText(account.getDisplayName());
            imgUrl = account.getPhotoUrl();
            try{
                Glide.with(this).load(account.getPhotoUrl()).into(imageAvater);
            }catch (NullPointerException e){
                Toast.makeText(getApplicationContext(),"image not found",Toast.LENGTH_LONG).show();
            }
        }else{
            gotoMainActivity();
        }
    }
    private void gotoMainActivity(){
        Intent intent = new Intent(getApplicationContext(), LoginWithApp.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()){
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        }else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult result) {
                    handleSignInResult(result);
                }
            });
        }
    }
}
