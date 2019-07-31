package com.example.user.anyagecare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.anyagecare.pojo.User;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    Button btnSignup;
    TextView btnLogin, btnForgotPass;
    EditText etName, etSurname,etContact,etEmail,etPassword;
    RelativeLayout activity_sign_up;
    private ProgressDialog mDialog;

    private FirebaseAuth auth;
    Snackbar snackbar;
    private static final int ALERT_DIALOG = 1;
    //profile
    private DatabaseReference databaseProfile;
    private String email,name, surname,contact,password;
    private DatabaseReference db;
    Boolean dismiss = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnSignup =findViewById(R.id.btnRegister);
        etName = findViewById(R.id.etName);
        etSurname =  findViewById(R.id.etSurname);
        etContact = findViewById(R.id.etContact);
        etEmail =  findViewById(R.id.etEmail);

        etPassword =  findViewById(R.id.edPassword);
        //profile




        db = FirebaseDatabase.getInstance().getReference("Users");
        auth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //email & password
                email = etEmail.getText().toString();
                surname = etSurname.getText().toString();
                contact = etContact.getText().toString();
                name = etName.getText().toString();
                password = etPassword.getText().toString();

//                if (name.isEmpty()) {
//                    etName.setError("Username must not be empty");
//                } else {
//                    if (email.isEmpty()) {
//
//                        etEmail.setError("Email is empty");
//                    } else {
//
//                        if (password.isEmpty()) {
//                            etPassword.setError("Password is empty");
//                        } else {
//                            if (password.length() >= 4) {
                //signUpUser(email, password);

                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>(){
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    String id = auth.getCurrentUser().getUid();
                                    db = FirebaseDatabase.getInstance().getReference("Users/" + id + "/Profile");
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("mAIN", "createUserWithEmail:success");

                                    //updateUI(user);
                                    Firebase.setAndroidContext(RegisterActivity.this);

//                                    User user_ = new User();
//
//                                    user_.setContact(contact);
//                                    user_.setName(name);
//                                    user_.setSurname(surname);
//                                    user_.setEmail(email);
//                                    db.setValue(user_);
                                    // Write a message to the database
                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    DatabaseReference myRef = database.getReference("message");

                                    myRef.setValue("Hello, World!");

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("sss", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(RegisterActivity.this, "Authentication failed."+ task.getException(),
                                            Toast.LENGTH_SHORT).show();

                                }

                                // ...
                            }
                        });

//
                        Toast.makeText(RegisterActivity.this, "Email "+email+" Password "+password, Toast.LENGTH_SHORT).show();
//                            } else {
//                                etPassword.setError("Password must contains more than 6 characters  ");
//                            }
//                        }
//                    }
//                }
            }
                  });


            }

            private void signUpUser(final String email, final String password) {
//        mDialog.setMessage("Sending Verification Email ...");
//        mDialog.show();s
                auth.createUserWithEmailAndPassword("9199999989@pintech.com", "corrfecthorsebatterystaple")
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(RegisterActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_LONG).show();
                                } else {
                                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                    finish();
                                }

                            }
                        });


            }


        }

