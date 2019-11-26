package com.alexmedia.mongcaifood;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginWithApp extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,View.OnClickListener {
    //goitrucc
    ImageView imgLogin,imgIntro;
    Animation animeTop,animeBottom,bouncein,float_in;
    SignInButton btnGoogle;
    FirebaseAuth mAuth;
    private static final String TAG = "LoginWithApp";
    String email,name;
    String idToken;
    FirebaseAuth.AuthStateListener authStateListener;
    private static final int GOOGLE_SIGN = 1;
    GoogleApiClient mgoogleclient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_app);
        /// gọi qua layout
        animeBottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);
        animeTop = AnimationUtils.loadAnimation(this,R.anim.fromtop);
        bouncein = AnimationUtils.loadAnimation(this,R.anim.bouncein);
        float_in = AnimationUtils.loadAnimation(this,R.anim.float_in);
        imgIntro = findViewById(R.id.imgLogo);
        btnGoogle = findViewById(R.id.btnLoginGoogle);
        btnGoogle.startAnimation(float_in);
        imgIntro.startAnimation(bouncein);
        mAuth = FirebaseAuth.getInstance();
        if (checkNetwork()){

        }else if (!checkNetwork())
        {
            createDialog();
        }
        ///  gọi set chung
        //gọi animation
        //google signin
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    Log.d(TAG,"onAuthStateChanged:signed_in:" + user.getUid());
                }else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mgoogleclient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(mgoogleclient);
                startActivityForResult(intent,GOOGLE_SIGN);
            }
        });
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    gotoProfile();
                }
            }
        };
    }
//thêm thư viện google
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOOGLE_SIGN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    private void handleSignInResult(GoogleSignInResult result){
        GoogleSignInAccount acc = result.getSignInAccount();
        if(acc != null){
            idToken = acc.getIdToken();
            name = acc.getDisplayName();
            email = acc.getEmail();
        }
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken,null);
        firebaseAuthWithGoogle(credential);
    }
    private void firebaseAuthWithGoogle(AuthCredential credential){
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
                        if(task.isSuccessful()){
                            Toast.makeText(LoginWithApp.this, "Login successful", Toast.LENGTH_SHORT).show();
                            gotoProfile();
                        }else{
                            Log.w(TAG, "signInWithCredential" + task.getException().getMessage());
                            task.getException().printStackTrace();
                            Toast.makeText(LoginWithApp.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
    //cac main
    private void gotoProfile(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onClick(View v) {

    }
    public void createDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialogerror, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertLayout);
        alert.setCancelable(false);
        Button btnExitConnect = alertLayout.findViewById(R.id.btnExit);
        btnExitConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
    private boolean checkNetwork(){
        boolean wifiConnect = false;
        boolean mobileConnected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connectivityManager.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()){
            wifiConnect = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
            mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            if (wifiConnect){
                wifiConnect = true;
                Toast.makeText(this, "Kết nối mạng wifi", Toast.LENGTH_SHORT).show();
            }else if (mobileConnected){
                mobileConnected = true;
                Toast.makeText(this, "Kết nối mạng 3G/4G", Toast.LENGTH_SHORT).show();
            }
        }
        return wifiConnect||mobileConnected;
    }
}
