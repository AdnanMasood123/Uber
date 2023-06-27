package com.example.uber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class driver_register_Activity extends AppCompatActivity
{
    private TextView AlreadyAccount;
    private EditText Email,Password;
    private Button Register;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_register);

        mAuth=FirebaseAuth.getInstance();

        getSupportActionBar().hide();
        Email=(EditText)findViewById(R.id.login_email);
        Password=(EditText)findViewById(R.id.login_password);
        Register=(Button)findViewById(R.id.register_btn);
        AlreadyAccount=(TextView)findViewById(R.id.Alreadycust);



        Register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Register();
            }

        });



        AlreadyAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(getApplicationContext(),driver_login_Activity.class);
                startActivity(intent);
            }
        });
    }




    private void Register()
    {

        String email = Email.getText().toString();
        String password = Password.getText().toString();


        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Email is Required", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Password is Required", Toast.LENGTH_SHORT).show();
        }
        else
        {


            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
            {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if (task.isSuccessful())
                    {
                        Intent intent=new Intent(getApplicationContext(),driver_map_Activity.class);
                        startActivity(intent);

                        // currentUserId=mAuth.getCurrentUser().getUid();


/*

                        HashMap<String,String> ProfileMap=new HashMap<>();
                        ProfileMap.put("uid",currentUserId);
                        ProfileMap.put("Email",email);
                        ProfileMap.put("password",password);


                        RootRef.child("Users").child(currentUserId).setValue(ProfileMap).addOnCompleteListener(new OnCompleteListener<Void>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<Void> task)
                            {

                                if(task.isSuccessful())
                                {


                                    Toast.makeText(customer_login_Activity.this,"Data Uploaded on Firebase SuccessFully",Toast.LENGTH_SHORT).show();

                                }
                                else
                                {
                                    String message=task.getException().toString();
                                    Toast.makeText(customer_login_Activity.this,"Error"+message,Toast.LENGTH_SHORT).show();

                                }
                            }
                        });


*/


                        Toast.makeText(driver_register_Activity.this, "Account Created SuccessFully", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        String message = task.getException().toString();
                        Toast.makeText(driver_register_Activity.this, "Error" + message, Toast.LENGTH_SHORT).show();

                    }

                }
            });
        }

    }


}