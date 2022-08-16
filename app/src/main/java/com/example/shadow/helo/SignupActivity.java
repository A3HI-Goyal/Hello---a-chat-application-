package com.example.shadow.helo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shadow.helo.Models.Users;
import com.example.shadow.helo.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    ActivitySignupBinding binding;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        // Initialize Firebase Auth for taking Email and Password from user for Sign Up
        auth=FirebaseAuth.getInstance();
        // Initialize Firebase Database for storing the data from editText in Firebase
        database=FirebaseDatabase.getInstance();
        // Initialize Progress Dialog
        progressDialog=new ProgressDialog(SignupActivity.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("We're creating your account");

        binding.btnSignUp.setOnClickListener(view -> {
            progressDialog.show();
            auth.createUserWithEmailAndPassword
                            (binding.etEmail.getText().toString(), binding.etPassword.getText().toString()).
                    addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                Users user = new Users(binding.etUsername.getText().toString(), binding.etEmail.getText().toString(), binding.etPassword.getText().toString());
                                String id = task.getResult().getUser().getUid();
                                database.getReference().child("Users").child(id).setValue(user);

                                Toast.makeText(SignupActivity.this, "User Created Sucessfully", Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(SignupActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        binding.clickSignIn.setOnClickListener(view -> {
            Intent intent=new Intent(SignupActivity.this,SigninActivity.class);
            startActivity(intent);
        });






    }


}