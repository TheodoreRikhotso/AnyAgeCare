package com.example.user.anyagecare;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ProgressFragment extends Fragment {


    public ProgressFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_progress, container, false);

        Button btnTest =view.findViewById(R.id.btnTest);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("message");
//                Firebase.setAndroidContext(getActivity());
//
//                myRef.setValue("Hello, World!");

                Toast.makeText(getActivity(),"Progress",Toast.LENGTH_LONG).show();
                myRef.child("message").setValue("Hello", new DatabaseReference.CompletionListener() {
                    public void onComplete(DatabaseError error, DatabaseReference ref) {
                        if(error == null){
                            Toast.makeText(getActivity(),"Error "+error.getMessage(),Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getActivity(),"Error "+error.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        return view;
    }

   
}
