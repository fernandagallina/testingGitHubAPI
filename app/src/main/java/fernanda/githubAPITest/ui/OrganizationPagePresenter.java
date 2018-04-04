package fernanda.githubAPITest.ui;

import java.util.List;

import fernanda.githubAPITest.DataLayer;
import fernanda.githubAPITest.RestAPI;
import fernanda.githubAPITest.model.GitHub;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class OrganizationPagePresenter implements OrganizationPageContract.Actions {

    private final OrganizationPageContract.View view;
    private final DataLayer dataLayer;

    public OrganizationPagePresenter(Retrofit retrofit, OrganizationPageContract.View view) {
        this.view = view;
        dataLayer = new DataLayer(retrofit);
    }

    @Override
    public void loadOrganization(String organization) {
        dataLayer.getOrg(organization)
                .compose(DataLayer.applyObservableSchedulers())
                .subscribe(this::onLoadOrganizationSuccess, this::onLoadOrganizationFailure);

    }

    private void onLoadOrganizationFailure(Throwable throwable) {
        view.showError(throwable.getMessage());
    }

    private void onLoadOrganizationSuccess(GitHub gitHub) {
        view.showOrganization(gitHub);
    }

    @Override
    public void loadMembers(String organization) {
        dataLayer.orgMembers(organization)
                .compose(DataLayer.applySingleSchedulers())
                .subscribe(this::onLoadMembersSuccess, this::onLoadOrganizationFailure);

    }

    private void onLoadMembersSuccess(List<GitHub> followers) {
       StringBuilder text = new StringBuilder();
        for (GitHub follower : followers) {
            text.append(follower.login).append("\n");
        }
        view.showSimpleDialog("Members", text.toString());
    }
}
