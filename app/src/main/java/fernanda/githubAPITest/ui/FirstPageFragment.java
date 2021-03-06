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
import butterknife.OnClick;
import butterknife.Unbinder;
import fernanda.githubAPITest.R;

/**
 * Created by fernanda on 03/11/16.
 */

public class FirstPageFragment extends Fragment{

    @BindView(R.id.mainSearchOrganizationBtn)
    Button searchOrgButton;

    @BindView(R.id.mainSearchUserBtn)
    Button searchUserButton;

    private Unbinder unbinder;

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

    }

    @OnClick(R.id.mainSearchUserBtn)
    public void searchUser() {
        showInputDialog("user");
    }

    @OnClick(R.id.mainSearchOrganizationBtn)
    public void searchOrganization() {
        showInputDialog("org");
    }

    private void showInputDialog(String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        if(title.equals("user")) {
            builder.setTitle("Search User");
            builder.setPositiveButton("Go", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    UserPageFragment userPage = new UserPageFragment();
                    userPage.setUsername(input.getText().toString());
                    getFragmentManager().beginTransaction()
                            .replace(R.id.activity_main, userPage)
                            .addToBackStack(null)
                            .commit();
                }
            });
        } else {
            builder.setTitle("Search Organization");
            builder.setPositiveButton("Go", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    OrganizationPageFragment organizationPage = new OrganizationPageFragment();
                    organizationPage.setOrgName(input.getText().toString());
                    getFragmentManager().beginTransaction()
                            .replace(R.id.activity_main, organizationPage)
                            .addToBackStack(null)
                            .commit();
                }
            });
        }
        builder.show();
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
