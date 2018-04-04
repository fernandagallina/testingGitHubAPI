package fernanda.githubAPITest.injections.component;

import dagger.Component;
import fernanda.githubAPITest.injections.module.CustomScope;
import fernanda.githubAPITest.injections.module.UserPageModule;
import fernanda.githubAPITest.ui.UserPageFragment;

@CustomScope
@Component(dependencies = NetComponent.class, modules = {UserPageModule.class})
public interface UserPageComponent {

    void inject(UserPageFragment fragment);
}

