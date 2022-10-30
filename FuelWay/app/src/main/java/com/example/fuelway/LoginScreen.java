/**
 * Author: Senith S
 *
 *
 * Login Class
 *
 */


package com.example.fuelway;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuelway.database.userDB;
import com.example.fuelway.model.user;

public class LoginScreen extends AppCompatActivity {

    EditText username, password;
    Button login;
    TextView signup, ownerlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        getSupportActionBar().hide();

        username = findViewById(R.id.unamelog);
        password = findViewById(R.id.passtxtlg);
        login = findViewById(R.id.logbtn);
        signup = findViewById(R.id.signuplink);
        ownerlg= findViewById(R.id.ownerlg);

        //UnderLine Reference = https://stackoverflow.com/questions/5645789/how-to-set-underline-text-on-textview
        String text="Click Here, Owner Login";
        SpannableString content = new SpannableString(text);
        content.setSpan(new UnderlineSpan(), 0, text.length(), 0);
        ownerlg.setText(content);

//Button Activities
        ownerlg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), OwnerLogin.class);
                startActivity(i);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SigninScreen.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLogin_onClick(view);
            }
        });
    }
//Login Method
    private void buttonLogin_onClick(View view) {

        userDB accountDB = new userDB(getApplicationContext());
        String uname = username.getText().toString();
        String pass = password.getText().toString();
        user account = accountDB.login(uname, pass);
//        user acc = accountDB.type(uname);
        if (account == null){
            AlertDialog.Builder builder = new  AlertDialog.Builder(view.getContext());
            builder.setTitle("ERROR");
            builder.setMessage("Invalid User Details");
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.show();

        }else {
                Intent intent = new Intent(LoginScreen.this, UserScreen.class);
                intent.putExtra("account", account);
                intent.putExtra("userMobile", account.getUserMobile());
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Login Success.", Toast.LENGTH_SHORT).show();
        }
    }
}