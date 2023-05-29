package com.example.petmonsters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BoardFriendFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BoardFriendFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BoardFriendFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BoardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BoardFriendFragment newInstance(String param1, String param2) {
        BoardFriendFragment fragment = new BoardFriendFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private PetMonstersApplication app;
    private ArrayList<Message> messagesArray;
    private LinearLayout mainLayout, horizontalLayout, verticalLayout;
    private ImageView newMessageBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_board_friend, container, false);

        getAppContext();

        mainLayout = view.findViewById(R.id.board_main_layout);
        newMessageBtn = view.findViewById(R.id.new_message_btn);

        newMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FriendActivity)getActivity()).addNote();
            }
        });

        try {
            getMessages();
            loadMessages();
        }
        catch (ExecutionException | InterruptedException | JSONException e) { e.printStackTrace(); }


        return view;
    }

    private void getAppContext(){
        app = (PetMonstersApplication) getActivity().getApplicationContext();
    }

    private void getMessages() throws ExecutionException, InterruptedException, JSONException {
        messagesArray = DatabaseHandler.readMessages(app.getCurrentFriend().getUsername());
    }

    private void loadMessages(){
        for(int i = 0; i < messagesArray.size(); i++){
            Message messageObj = messagesArray.get(i);
            String name = messageObj.getFrom();
            String to = messageObj.getTo();
            String title = messageObj.getTitle();
            String message = messageObj.getMessage();

            generateMessage(i, app.getContext(), name, to, title, message);
        }
    }

    @SuppressLint("ResourceAsColor")
    private void generateMessage(int index, Context context, String _name, String _to, String _title, String _message){
        if (index % 2 == 0) {
            horizontalLayout = new LinearLayout(context);
            LinearLayout.LayoutParams boxParameters = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
            horizontalLayout.setLayoutParams(boxParameters);
            boxParameters.setMargins(0, 10, 0, 10);
            boxParameters.setLayoutDirection(LinearLayout.HORIZONTAL);
            mainLayout.addView(horizontalLayout);
        }


        verticalLayout = new LinearLayout(context);
        LinearLayout.LayoutParams verticalLayoutParams = new LinearLayout.LayoutParams(250, 400, 1.0f);
        verticalLayout.setLayoutParams(verticalLayoutParams);
        verticalLayoutParams.setMargins(5, 0, 5, 0);
        verticalLayout.setOrientation(LinearLayout.VERTICAL);
        verticalLayout.setBackgroundColor(R.color.purple_700);

        ScrollView scrollView = new ScrollView(context);
        LinearLayout.LayoutParams scrollParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scrollView.setLayoutParams(scrollParams);

        LinearLayout miniLayout = new LinearLayout(context);
        LinearLayout.LayoutParams miniParameters = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        miniLayout.setLayoutParams(miniParameters);
        miniLayout.setOrientation(LinearLayout.HORIZONTAL);

        TextView name = new TextView(context);
        name.setText(_name);
        LinearLayout.LayoutParams nameParameters = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        name.setLayoutParams(nameParameters);
        name.setGravity(Gravity.CENTER);
        name.setTextColor(R.color.black);
        name.setTextSize(20);


        verticalLayout.addView(name);

        TextView title = new TextView(context);
        title.setText(_title);
        LinearLayout.LayoutParams titleParameters = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        title.setLayoutParams(titleParameters);
        title.setGravity(Gravity.LEFT);
        title.setTextColor(R.color.black);


        verticalLayout.addView(title);

        TextView message = new TextView(context);
        message.setText(_message);
        LinearLayout.LayoutParams messageParameters = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        message.setLayoutParams(messageParameters);
        message.setGravity(Gravity.LEFT);
        message.setTextColor(R.color.black);

        scrollView.addView(message);

        verticalLayout.addView(scrollView);
        horizontalLayout.addView(verticalLayout);
    }

    public void reload(){
        try {
            getMessages();
            loadMessages();
        }
        catch (ExecutionException | InterruptedException | JSONException e) { e.printStackTrace(); }

    }
}