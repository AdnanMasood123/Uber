package com.example.uber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class customer_login_Activity extends AppCompatActivity
{
    private EditText Email,Password;
    private Button RegisterButton;

    private FirebaseAuth mAuth;
    private DatabaseReference RootRef;
    private String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        mAuth=FirebaseAuth.getInstance();
     //   RootRef=FirebaseDatabase.getInstance().getReference();



        getSupportActionBar().hide();



        Email = (EditText) findViewById(R.id.login_email);
        Password = (EditText) findViewById(R.id.login_password);
        RegisterButton = (Button) findViewById(R.id.login_button);





        RegisterButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                   Register();
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

                        Intent intent=new Intent(getApplicationContext(),Customer_map_Activity.class);
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


                        Toast.makeText(customer_login_Activity.this, "Account Created SuccessFully", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        String message = task.getException().toString();
                        Toast.makeText(customer_login_Activity.this, "Error" + message, Toast.LENGTH_SHORT).show();

                    }

                }
            });
        }

    }




    private void sendUserToMainActivity()
    {
        Intent intent=new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }




}




