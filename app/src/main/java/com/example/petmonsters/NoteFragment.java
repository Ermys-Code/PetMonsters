package com.example.petmonsters;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NoteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NoteFragment.
     */
    // TODO: Rename and change types and number of parameters
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

        try { setOnClickListeners(); }
        catch (ExecutionException | InterruptedException e) { e.printStackTrace(); }

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

    private void setOnClickListeners() throws ExecutionException, InterruptedException {
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try { PostNote(); }
                catch (ExecutionException | InterruptedException e) { e.printStackTrace(); }
            }
        });
    }

    private void PostNote() throws ExecutionException, InterruptedException {
        if (titleEt.getText().toString().equals("")){
            return;
        }
        if (messageEt.getText().toString().equals("")){
            return;
        }

        Message newMessage = new Message(app.getUser().getUsername(), app.getCurrentFriend().getUsername(), titleEt.getText().toString(), messageEt.getText().toString());

        if (DatabaseHandler.createMessage(newMessage)) {
            Utilities.ShowShortToast(app.getContext(), "Note posted");
            ((FriendActivity)getActivity()).getBoardFragment().reload();
            ((FriendActivity)getActivity()).closeNoteFragment();
        } else {
            Utilities.ShowShortToast(app.getContext(), "Error posting note");
        }
    }
}