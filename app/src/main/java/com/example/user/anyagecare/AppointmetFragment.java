package com.example.user.anyagecare;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.anyagecare.pojo.Appointment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class AppointmetFragment extends Fragment {
    private FloatingActionButton fabAddApp;
    private Toolbar toolbar;
    private View view;

    private FirebaseAuth auth;
    private DatabaseReference appointmentDB;

    private List<Appointment> appointmentList;
    private LinearLayoutManager layoutManager;
    private RecyclerView rvAppointment;
    private AppointmentAdapter appointmentAdapter;


    public AppointmetFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_appointmet, container, false);
        toolbar = view.findViewById(R.id.toolbar_appoint);
        fabAddApp= view.findViewById(R.id.fabAddApp);

        toolbar.setTitle("Appointment");
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        auth = FirebaseAuth.getInstance();

        appointmentDB = FirebaseDatabase.getInstance().getReference("Appointment/"+auth.getUid());

        rvAppointment = view.findViewById(R.id.rvAppointment);

        appointmentList = new ArrayList<>();



        appointmentDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                appointmentList.clear();
                for (DataSnapshot catalogSnapshot : dataSnapshot.getChildren()) {
                    rvAppointment = view.findViewById(R.id.rvAppointment);
                    Appointment catalog = catalogSnapshot.getValue(Appointment.class);
                    appointmentList.add(catalog);

                    layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    appointmentAdapter = new AppointmentAdapter(getActivity(), appointmentList);

                    rvAppointment.setLayoutManager(layoutManager);

                    rvAppointment.setAdapter(appointmentAdapter);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        fabAddApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddAppointmentActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.itLogout) {
            logoutUser();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void logoutUser() {
        auth.signOut();
        if (auth.getCurrentUser() == null)
        {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
    }
}
