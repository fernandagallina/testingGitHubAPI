package fernanda.githubAPITest.injections.component;

import javax.inject.Singleton;

import dagger.Component;
import fernanda.githubAPITest.injections.module.AppModule;
import fernanda.githubAPITest.injections.module.NetModule;
import fernanda.githubAPITest.ui.MainActivity;
import fernanda.githubAPITest.ui.UserPageFragment;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {

    Retrofit retrofit();

    void inject(MainActivity activity);
}
