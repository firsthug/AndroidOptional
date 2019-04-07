package com.example.tema2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.List;


import UserDB.User;
import UserDB.UserRepository;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentOne extends Fragment {

    private List<User> userList = new ArrayList<>();
    private RecyclerView recyclerView;
    private UserAdapter mAdapter;
    private String lastName;
    private String firstName;
    private EditText ed1;
    private  EditText ed2;
    private UserRepository myRepository;
    FragmentCommunication fragmentCommunication;

    public void setFragmentCommunication(FragmentCommunication fragmentCommunication) {
        this.fragmentCommunication = fragmentCommunication;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FragmentCommunication) {
            fragmentCommunication = (FragmentCommunication) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one, container, false);

        recyclerView =  view.findViewById(R.id.rv_container);
        myRepository = new UserRepository(super.getContext());

        mAdapter = new UserAdapter(userList,this.getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        Button addButton = view.findViewById(R.id.button3);
        Button deleteButton = view.findViewById(R.id.button4);
        ed1 = view.findViewById(R.id.editText3);
        ed2 = view.findViewById(R.id.editText4);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastName=ed1.getText().toString();
                firstName = ed2.getText().toString();

                User user = new User(firstName,lastName);
                myRepository.insertTask(user, fragmentCommunication.getActivityOfFrag());
                prepareUserData();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastName=ed1.getText().toString();
                firstName = ed2.getText().toString();
                for(User myUser :userList)
                {   if(myUser.firstName.equals(firstName) && myUser.lastName.equals(lastName))
                         {myRepository.removeTask(myUser,fragmentCommunication.getActivityOfFrag());
                          }}
                prepareUserData();
            }
        });

        prepareUserData();
        return view;
    }



    private void prepareUserData() {

        /*
        User user = new User("Nume1","Prenume1");
        userList.add(user);

        user = new User("Nume2","Prenume2");
        userList.add(user);

        user = new User("Nume3","Prenume3");
        userList.add(user);

        user = new User("Nume4","Prenume4");
        userList.add(user);

        user = new User("Nume5","Prenume4");
        userList.add(user);

        user = new User("Nume6","Prenume4");
        userList.add(user);

        user = new User("Nume7","Prenume4");
        userList.add(user);
        */
        userList.clear();
        userList.addAll(myRepository.displayTask(fragmentCommunication.getActivityOfFrag()));

        mAdapter.notifyDataSetChanged();
    }

}
