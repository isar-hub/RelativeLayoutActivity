package com.example.relativelayoutactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {
    EditText edUsername , edEmail, edPassword , edConfirm;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edUsername = findViewById(R.id.editTextRegUsername);
        edEmail = findViewById(R.id.editTextRegEmail);
        edPassword = findViewById(R.id.editTextRegPassword);
        edConfirm = findViewById(R.id.editTextRegConfirmPassword);
        btn = findViewById(R.id.buttonRegister);
        tv = findViewById(R.id.textviewExistingUser);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iNext;
                iNext = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(iNext);


            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirm = edConfirm.getText().toString();

                if (username.length() == 0 || email.length() == 0 || password.length() == 0 || confirm.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.compareTo(confirm) == 0) {
                        if (isValid(password)) {
                            // Register user with Firebase Authentication
                            FirebaseAuth mAuth = FirebaseAuth.getInstance();
                            mAuth.createUserWithEmailAndPassword(email, password)
                                    .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                // Registration success, update username (Optional)
                                                FirebaseUser user = mAuth.getCurrentUser();
                                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                                        .setDisplayName(username)
                                                        .build();
                                                assert user != null;
                                                user.updateProfile(profileUpdates);

                                                Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                                finish();
                                            } else {
                                                // Registration failed
                                                Toast.makeText(getApplicationContext(), "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        } else {
                            Toast.makeText(getApplicationContext(), "Password must contain at least 8 characters, having a letter, digit, and special symbol", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Password and Confirm Password didn't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });




//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String username = edUsername.getText().toString();
//                String email = edEmail.getText().toString();
//                String password = edPassword.getText().toString();
//                String confirm = edConfirm.getText().toString();
//
//
//
//                Database db = new Database(getApplicationContext() , "healthcare" , null , 1);
//
//
//                if(username.length()==0||email.length()==0||password.length()==0||confirm.length()==0){
//                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
//                }else{
//                    if(password.compareTo(confirm)==0){
//                        if(isValid(password)){
//                            db.register(username , email , password);
//
//                            Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(RegisterActivity.this ,MainActivity.class));
//
//                        }else{
//                            Toast.makeText(getApplicationContext(), "Password must contain at least 8 characters, having letter , digit and special symbol", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }else{
//                        Toast.makeText(getApplicationContext(), "Password and Confirm Password didn't match", Toast.LENGTH_SHORT).show();
//
//                    }
//                }
//
//            }
//        });
    }
    public static boolean isValid(String passwordhere){
        int f1 = 0, f2= 0, f3=0;
        if(passwordhere.length()<8){
            return false;

        }else{
            for(int p=0; p<passwordhere.length();p++){
                if(Character.isLetter(passwordhere.charAt(p))){
                    f1 = 1;
                }
            }
            for(int r = 0; r<passwordhere.length();r++){
                if(Character.isDigit(passwordhere.charAt(r))){
                    f2= 1;
                }
            }
            for(int s=0; s<passwordhere.length(); s++){
                char c = passwordhere.charAt(s);
                if(c>=33&&c<=46||c==64){
                    f3 =1;

                }
            }
            if(f1 ==1 &&f2==1 &&f3==1)
                return true;
            return false;
        }
    }
}