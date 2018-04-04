package fernanda.githubAPITest.injections.module;

import dagger.Module;
import dagger.Provides;
import fernanda.githubAPITest.ui.OrganizationPageContract;
import fernanda.githubAPITest.ui.OrganizationPagePresenter;
import retrofit2.Retrofit;

@Module
public class OrganizationPageModule {
    private final OrganizationPageContract.View view;

    public OrganizationPageModule(OrganizationPageContract.View view) {
        this.view = view;
    }

    @Provides
    @CustomScope
    OrganizationPagePresenter providesOrganizationPagePresenter(Retrofit retrofit) {
        return new OrganizationPagePresenter(retrofit, view);
    }
}