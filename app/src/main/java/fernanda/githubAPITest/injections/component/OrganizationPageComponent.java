package fernanda.githubAPITest.injections.component;

import javax.inject.Singleton;

import dagger.Component;
import fernanda.githubAPITest.injections.module.CustomScope;
import fernanda.githubAPITest.injections.module.OrganizationPageModule;
import fernanda.githubAPITest.ui.OrganizationPageFragment;

@CustomScope
@Component(dependencies = NetComponent.class, modules = {OrganizationPageModule.class})
public interface OrganizationPageComponent {

    void inject(OrganizationPageFragment fragment);
}
