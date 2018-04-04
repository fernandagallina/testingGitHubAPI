package fernanda.githubAPITest;

import java.util.List;

import fernanda.githubAPITest.model.GitHub;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class DataLayer {

    private RestAPI service;

    public DataLayer(Retrofit retrofit) {
        this.service = retrofit.create(RestAPI.class);
    }

    public Observable<GitHub> getUser(String link) {
        return service.user(link);
    }

    public static <T> SingleTransformer<T, T> applySingleSchedulers() {
        return single -> single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }

    public static <T> ObservableTransformer<T, T> applyObservableSchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }

    public Single<List<GitHub>> orgMembers(String organization) {
        return service.orgMembers(organization);
    }

    public Observable<GitHub> getOrg(String organization) {
        return service.org(organization);
    }
}