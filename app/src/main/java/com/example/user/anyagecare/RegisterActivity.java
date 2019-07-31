package com.example.user.anyagecare;

import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class RegisterActivity extends AppCompatActivity {
    Button btnSignup;
    TextView btnLogin, btnForgotPass;
    EditText etName, etSurname,etContact,etEmail;
    RelativeLayout activity_sign_up;
    private ProgressDialog mDialog;

    private FirebaseAuth auth;
    Snackbar snackbar;
    private static final int ALERT_DIALOG = 1;
    //profile
    private DatabaseReference databaseProfile;
    private String email,name, surname,contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnSignup =findViewById(R.id.btnRegister);
        etName = findViewById(R.id.etName);
        etSurname =  findViewById(R.id.etSurname);
        etContact = findViewById(R.id.etContact);
        etEmail =  findViewById(R.id.etEmail);

        //profile


        //email & password
        email = etName.getText().toString();
        surname = etSurname.getText().toString();
        contact = etContact.getText().toString();
        name = etEmail.getText().toString();

    }
}
