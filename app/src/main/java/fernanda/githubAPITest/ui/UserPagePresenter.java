package fernanda.githubAPITest.ui;

import fernanda.githubAPITest.DataLayer;
import fernanda.githubAPITest.model.GitHub;
import retrofit2.Retrofit;

public class UserPagePresenter implements UserPageContract.Actions {

    private DataLayer dataLayer;
    private UserPageContract.View view;

    public UserPagePresenter(Retrofit retrofit, UserPageContract.View view) {
        dataLayer = new DataLayer(retrofit);
        this.view = view;
    }

    @Override
    public void loadUser(String user) {
        dataLayer.getUser(user)
                .compose(DataLayer.applyObservableSchedulers())
                .subscribe(this::onSuccess, this::onError);
    }

    private void onSuccess(GitHub gitHub) {
        view.showUser(gitHub);
    }

    private void onError(Throwable throwable) {
        view.showError("USER DOESN'T EXIST");


    }
}
