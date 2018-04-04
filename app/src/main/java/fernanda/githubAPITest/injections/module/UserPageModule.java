package fernanda.githubAPITest.injections.module;

import dagger.Module;
import dagger.Provides;
import fernanda.githubAPITest.ui.UserPageContract;
import fernanda.githubAPITest.ui.UserPagePresenter;
import retrofit2.Retrofit;

/**
 * Created by fernandagallina on 4/4/18.
 */

@Module
public class UserPageModule {
    private final UserPageContract.View view;

    public UserPageModule(UserPageContract.View view) {
        this.view = view;
    }

    @Provides
    @CustomScope
    UserPagePresenter providesUserPagePresenter(Retrofit retrofit) {
        return new UserPagePresenter(retrofit, view);
    }
}
