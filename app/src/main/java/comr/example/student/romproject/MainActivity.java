package comr.example.student.romproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn1, btn2, btnsendregister,btnsendlogin;
    FirebaseAuth mAuth;
    Dialog d;
    EditText etmail,etpass,etmaillog,etpasslog;
    TextView topscore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        topscore = findViewById(R.id.topscore);
        SharedPreferences preferences1 = getSharedPreferences("maxscore1", Context.MODE_PRIVATE);
        int value1 = preferences1.getInt("maxscore2", 0);
        topscore.setText("Your top score is: " + value1);
    }
        @Override

        public void onClick(View v) {
            if (btn1 == v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
            if (btn2== v) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                startActivity(intent);
            }
            if(btnsendregister==v)
            {
                register();
            }
            if(btnsendlogin==v)
            {
                login();
            }
        }

    private void login() {
        mAuth.signInWithEmailAndPassword(etmail.getText().toString(), etpass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity.this, "Authentication successfull.",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
        d.dismiss();
    }

    private void register() {
        mAuth.createUserWithEmailAndPassword(etmail.getText().toString(), etpass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                            startActivity(intent);
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity.this, "Authentication successfull.",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
        d.dismiss();

    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return  true;

    }



    @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            super.onOptionsItemSelected(item);

            int id = item.getItemId();
            if (id == R.id.action_register) {
                createRegisterDialog();
               // Toast.makeText(this, "Registering...", Toast.LENGTH_SHORT).show();
            } else if (R.id.action_login == id) {
              //  Toast.makeText(this, "Logging in...", Toast.LENGTH_SHORT).show();
                createLoginDialog();
            } else if (R.id.action_exit == id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure?");
                builder.setCancelable(true);
                builder.setPositiveButton("Yes", new HandleAlertDialogListener());
                builder.setNegativeButton("No", new HandleAlertDialogListener2());
                AlertDialog dialog = builder.create();
                dialog.show();
            }

            return true;

        }

    private void createLoginDialog() {
        d= new Dialog(this);
        d.setContentView(R.layout.logindialog);
        d.setTitle("Login");
        d.setCancelable(true);
        etmaillog=(EditText)d.findViewById(R.id.etmaillog);
        etpasslog=(EditText)d.findViewById(R.id.etpasslog);
        btnsendlogin=(Button)d.findViewById(R.id.btnsendlogin);
        btnsendlogin.setOnClickListener(this);
        d.show();
    }

    private void createRegisterDialog() {
        d= new Dialog(this);
        d.setContentView(R.layout.registerdialog);
        d.setTitle("Register");
        d.setCancelable(true);
        etmail=(EditText)d.findViewById(R.id.etmail);
        etpass=(EditText)d.findViewById(R.id.etpass);
        btnsendregister=(Button)d.findViewById(R.id.btnsendregister);
        btnsendregister.setOnClickListener(this);
        d.show();


    }

    private class HandleAlertDialogListener implements DialogInterface.OnClickListener {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Exiting...", Toast.LENGTH_LONG).show();
            }
        }
        private class HandleAlertDialogListener2 implements DialogInterface.OnClickListener {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Good choice :)", Toast.LENGTH_LONG).show();
            }
        }
    }
