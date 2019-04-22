package fas.com.eddiejusto.fass;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {
    EditText name, phone,health,id_no, email, password;
    Button signup;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth=FirebaseAuth.getInstance();
        name = (EditText) findViewById(R.id.name);
        id_no = (EditText) findViewById(R.id.id_no);
        phone = (EditText) findViewById(R.id.phone_number);
       health = (EditText) findViewById(R.id.school);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        signup=(Button)findViewById(R.id.signin);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jina = name.getText().toString();
                String id_yako = id_no.getText().toString();
                String number = phone.getText().toString();
                String shule = health.getText().toString();
                String emaili = email.getText().toString();
                String paswordi = password.getText().toString();
                mAuth.createUserWithEmailAndPassword(emaili, paswordi)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d("SIGN_IN_SUCCESS", "createUserWithEmail:success");
                                    mAuth.getCurrentUser();
                                    Toast.makeText(MainActivity.this, "Sign up Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(signup.this, MainActivity.class));
                                    finish();
                                } else {
                                    Log.w("ERROR_SIGNIN", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signup.this, MainActivity.class));


            }
        });
    }
}

