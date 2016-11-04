package fernanda.githubAPITest.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fernanda.githubAPITest.R;

/**
 * Created by fernanda on 03/11/16.
 */

public class FirstPageFragment extends Fragment{

    @BindView(R.id.search_repo_button)
    Button searchRepoButton;

    @BindView(R.id.search_user_button)
    Button searchUserButton;

    private Unbinder unbinder;

//    //Create a retrofit call object
//    RestAPI service = retrofit.create(RestAPI.class);
//    Call<List<Github>> call = service.contributors("square", "retrofit");
//
//    //Enque the call
//    call.enqueue(new Callback<List<Github>>() {
//        @Override
//        public void onResponse(Call<List<Github>> call, Response<List<Github>> response) {
//            //Set the response to the textview
//            List<Github> contributors = response.body();
//            String text = "";
//            for(Github contributor : contributors) {
//                text = text + contributor.getLogin() + " " + contributor.getContributions() + " " + contributor.getId() + "\n";
//            }
//            textView.setText(text);
//        }
//
//        @Override
//        public void onFailure(Call<List<Github>> call, Throwable t) {
//            //Set the error to the textview
//            textView.setText(t.toString());
//        }
//    });


    public FirstPageFragment() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_firstpage, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog();
            }
        });
    }

    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Search User");
        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("Go", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    UserPage userPage = new UserPage();
                    userPage.setUsername(input.getText().toString());
                    getFragmentManager().beginTransaction()
                            .replace(R.id.fragment_firstpage, userPage)
                            .addToBackStack(null)
                            .commit();
            }
        });
        builder.show();
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
