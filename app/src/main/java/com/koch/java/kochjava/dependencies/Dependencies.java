package com.koch.java.kochjava.dependencies;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.koch.java.kochjava.base.Core;
import com.koch.java.kochjava.base.model.Joke;
import com.koch.java.kochjava.base.model.MyObjectBox;
import com.koch.java.kochjava.base.model.User;
import com.koch.java.kochjava.base.model.repositories.JokeRepository;
import com.koch.java.kochjava.base.model.repositories.UserRepository;
import com.koch.java.kochjava.models.net.api.JokeServices;
import com.koch.java.kochjava.models.repositories.JokeRepositoryAndroid;
import com.koch.java.kochjava.models.repositories.UserRepositoryAndroid;
import com.koch.java.kochjava.ui.activities.MainActivity;
import com.koch.java.kochjava.ui.fragments.FeedFragment;
import com.koch.java.kochjava.ui.fragments.ProfileFragment;
import com.koch.java.kochjava.ui.view.viewmodel.JokeViewModel;
import com.koch.java.kochjava.ui.view.viewmodel.factory.ViewModelFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.BindsInstance;
import dagger.Component;
import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Dependencies {
    @Singleton
    @Component(modules = {AndroidInjectionModule.class, CoreModule.class})
    interface CoreComponent extends AndroidInjector<Core>{
        @Component.Builder interface Builder {
            @BindsInstance Builder application(Core core);

            CoreComponent build();
        }

        void inject(Application application);
    }

    @Module(includes = {NetworkModule.class, StorageModule.class, ViewModelModule.class})
    public static abstract class CoreModule{
        @ContributesAndroidInjector abstract MainActivity mainActivityInjector();
        @ContributesAndroidInjector abstract FeedFragment feedFragmentInjector();
        @ContributesAndroidInjector abstract ProfileFragment profileFragmentInjector();
    }

    @Module
    public static class StorageModule{
        @Provides @Singleton BoxStore provideStorageModule(Core context){
            return MyObjectBox.builder().androidContext(context).build();
        }

        @Provides Box<Joke> provideBoxJokeModule(BoxStore box){ return box.boxFor(Joke.class); }
        @Provides Box<User> provideBoxUserModule(BoxStore box){ return box.boxFor(User.class); }

        @Provides @Singleton UserRepository provideUserRepository(UserRepositoryAndroid repo){ return repo; }
        @Provides @Singleton JokeRepository provideJokeRepository(JokeRepositoryAndroid repo){ return repo; }
    }

    @Module(includes = JsonModule.class)
    public static class NetworkModule{
        @Provides public JokeServices createJokeApi(Retrofit retrofit){
            return retrofit.create(JokeServices.class);
        }

        @Provides public Retrofit createRetrofit(OkHttpClient httpClient,
                                                 GsonConverterFactory jsonConverter){
            return new Retrofit.Builder()
                    .baseUrl(JokeServices.Config.url)
                    .addConverterFactory(jsonConverter)
                    .client(httpClient)
                    .build();
        }

        @Provides
        public OkHttpClient httpClient(HttpLoggingInterceptor httpLoggingInterceptor){
            return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        }

        @Provides
        public HttpLoggingInterceptor httpLoggingInterceptor(){
            HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            return logInterceptor;
        }
    }

    @Module public static class JsonModule{
        @Provides
        public Gson gson(){
            GsonBuilder gsonBuilder = new GsonBuilder();
            return gsonBuilder.create();
        }

        @Provides
        public GsonConverterFactory jsonConverter(Gson gson){
            return GsonConverterFactory.create(gson);
        }
    }

    @Target({ElementType.METHOD, ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    @interface ViewModelKey{
        Class<? extends ViewModel> value();
    }

    @Module public static abstract class ViewModelModule {

        @Binds abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

        @Binds
        @IntoMap
        @ViewModelKey(JokeViewModel.class)
        abstract ViewModel jokeViewModel(JokeViewModel viewModel);
    }

    public static void initialize(Core core){
        DaggerDependencies_CoreComponent.builder()
                .application(core).build().inject(core);
    }
}
