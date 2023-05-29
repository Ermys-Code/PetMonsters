package com.example.petmonsters;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.concurrent.ExecutionException;

public class NoteFragment extends Fragment {


    private static final String ARG_PARAM1 = Constants.params_1;
    private static final String ARG_PARAM2 = Constants.params_2;

    private String mParam1;
    private String mParam2;

    public NoteFragment() {

    }

    public static NoteFragment newInstance(String param1, String param2) {
        NoteFragment fragment = new NoteFragment();
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

    private ImageView sendBtn;
    private EditText titleEt, messageEt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_note, container, false);

        getAppContext();

        getScreenElements(view);

        try {
            setOnClickListeners();
        }
        catch (Exception e) { System.out.println(e.getMessage()); }

        return view;
    }

    private void getAppContext(){
        app = (PetMonstersApplication) getActivity().getApplicationContext();
    }

    private void getScreenElements(View view){
        sendBtn = view.findViewById(R.id.send_iv);
        titleEt = view.findViewById(R.id.title_et);
        messageEt = view.findViewById(R.id.message_et);
    }

    private void setOnClickListeners() throws Exception {
        sendBtn.setOnClickListener(v -> {
            try {
                PostNote();
            }
            catch (Exception e) { System.out.println(e.getMessage()); }
        });
    }

    private void PostNote() throws Exception {
        if (titleEt.getText().toString().equals("")){
            Utilities.ShowShortToast(app.getContext(), Constants.title_not_valid);
            return;
        }
        if (messageEt.getText().toString().equals("")){
            Utilities.ShowShortToast(app.getContext(), Constants.message_not_valid);
            return;
        }

        Message newMessage = new Message(app.getUser().getUsername(), app.getCurrentFriend().getUsername(), titleEt.getText().toString(), messageEt.getText().toString());

        if (DatabaseHandler.createMessage(newMessage)) {
            Utilities.ShowShortToast(app.getContext(), Constants.note_posted);
            ((FriendActivity)getActivity()).getBoardFragment().reload();
            ((FriendActivity)getActivity()).closeNoteFragment();
        } else {
            Utilities.ShowShortToast(app.getContext(), Constants.error_posting_note);
        }
    }
}