package fernanda.githubAPITest.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import fernanda.githubAPITest.dagger.module.AppModule;
import fernanda.githubAPITest.dagger.module.NetModule;
import fernanda.githubAPITest.ui.MainActivity;
import fernanda.githubAPITest.ui.OrganizationPage;
import fernanda.githubAPITest.ui.UserPage;

/**
 * Created by fernanda on 03/11/16.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {

    void inject(MainActivity activity);

    void inject(UserPage fragment);

    void inject(OrganizationPage fragment);
}

