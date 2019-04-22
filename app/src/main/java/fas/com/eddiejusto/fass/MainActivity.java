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

public class MainActivity extends AppCompatActivity {
    Button login;
    EditText pass, email1;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button) findViewById(R.id.login);
        email1 = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email1.getText().toString().equals("")){
                    email1.setError("Required");
                }else if (pass.getText().toString().equals("")){
                    pass.setError("Required");
                }else {
                    mAuth.signInWithEmailAndPassword(email1.getText().toString(), pass.getText().toString())
                            .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("SIGN_IN_SUCCESS", "signinWithEmail:success");
                                        mAuth.getCurrentUser();
                                        Toast.makeText(login.this, "Sign in Successful", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(MainActivity.this, scanner.class));
                                        finish();
                                    } else {
                                        Log.w("ERROR_SIGNIN", "sign in WithEmail:failure", task.getException());
                                        Toast.makeText(login.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}



