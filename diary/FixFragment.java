package com.devStereo.owls.diary;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.devStereo.owls.R;

//import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FixFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FixFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FixFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    private EditText topic ;// Topic of write frag
    private EditText contents;//Contents of write frag
    private ImageButton submit;// Submit memo to inner db by using DB manager;
    private ImageButton deletebtn;

    String mytopic;
    String myContents;
    int tid;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FixFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FixFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FixFragment newInstance(String param1, String param2) {
        FixFragment fragment = new FixFragment();
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


Log.i("fixFrage","my tid:"+tid+"my topic"+mytopic+"mycontents"+myContents);
            Toast.makeText(getContext(),"my tid:"+tid+"my topic"+mytopic+"mycontents"+myContents,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("now","in FIxFrage");
        final View view = inflater.inflate(R.layout.fragment_fix,container,false);

        Bundle mybundle= ((MainActivity)MainActivity.mContext).bundleManager.getBundle();

        tid = mybundle.getInt("tid");

        topic = (EditText) view.findViewById(R.id.topic);

        contents = (EditText) view.findViewById(R.id.contents);

        submit = (ImageButton)view.findViewById(R.id.submit);

        deletebtn = (ImageButton) view.findViewById(R.id.deletebtn);
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.sqLiteHelper.deleteData(tid+"");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().hide(FixFragment.this).commit();
                ((MainActivity)MainActivity.mContext).showList(new View(getContext()));
            }
        });

        topic.setText(mybundle.getString("myTopic"));
        contents.setText(mybundle.getString("myContents"));
//
//


        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("","onClickClick");
                String mytopic =topic.getText().toString();
                String myContents = contents.getText().toString();

                MainActivity.sqLiteHelper.editData(mytopic,myContents,tid+"");
                // dbManager.insert("INSERT INTO DIARY VALUES(null,'"+mytopic+"','"+myContents+"');");
                Toast.makeText(getContext(),"write Success",Toast.LENGTH_SHORT);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().hide(FixFragment.this).commit();
                ((MainActivity)MainActivity.mContext).showList(new View(getContext()));
            //    ((MainActivity)MainActivity.mContext).onButtonlist(new View(getContext()));

                //  MainActivity mainActivity = new MainActivity();
//                ((MainActivity)MainActivity.mContext).createStar(new View(getContext()));
            }
        });

//        return  view;
//
        return  view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
