/**
 * User Sign in Class
 *
 */

package com.example.fuelway;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuelway.database.userDB;
import com.example.fuelway.model.user;

public class SigninScreen extends AppCompatActivity {
    EditText username, nic, mobile, password;
    Button register;
    TextView signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_screen);
        getSupportActionBar().hide();

        username = findViewById(R.id.usernameedittxt);
        mobile = findViewById(R.id.emailedittxt);
        nic = findViewById(R.id.nicedittxt);
        password = findViewById(R.id.passedittxt);
        register = findViewById(R.id.regbtn);
        signin = findViewById(R.id.signinlink);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSave_onClick(view);
            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LoginScreen.class);
                startActivity(i);
            }
        });
    }

    //Save User details in sqllite
    private void buttonSave_onClick(View view) {
        try {
            if(username.length() > 0 && password.length() > 0) {

                userDB accountDB = new userDB(getApplicationContext());
                user account = new user();
                account.setPassword(password.getText().toString());
                account.setNic(nic.getText().toString());
                account.setUserMobile(mobile.getText().toString());
                account.setName(username.getText().toString());
                user temp = accountDB.checkUsername(username.getText().toString());

                if (temp == null) {
                    if (accountDB.create(account)) {
                        Intent i = new Intent(SigninScreen.this, LoginScreen.class);
                        startActivity(i);
                        Toast.makeText(SigninScreen.this, "Successfully Registered.", Toast.LENGTH_SHORT).show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setTitle("ERROR");
                        builder.setMessage("Can not create new account");
                        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        builder.show();
                    }
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("ERROR");
                    builder.setMessage("Username already exists");
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    builder.show();

                }
            }


        }catch (Exception e){
            AlertDialog.Builder builder = new  AlertDialog.Builder(view.getContext());
            builder.setTitle("ERROR");
            builder.setMessage(e.getMessage());
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.show();
        }
    }
}