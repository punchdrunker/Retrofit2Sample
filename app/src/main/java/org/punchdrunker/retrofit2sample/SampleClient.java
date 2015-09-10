package org.punchdrunker.retrofit2sample;

import org.punchdrunker.retrofit2sample.data.api.Owner;
import org.punchdrunker.retrofit2sample.data.api.SampleService;
import org.punchdrunker.retrofit2sample.data.api.VariousService;
import org.punchdrunker.retrofit2sample.data.api.model.Contributor;

import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.ProtoConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public final class SampleClient {
    public static final String API_URL = "https://api.github.com";

    public static void call() throws IOException {
        // Create a very simple REST adapter which points the GitHub API.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of our GitHub API interface.
        SampleService github = retrofit.create(SampleService.class);

        // Create a call instance for looking up Retrofit contributors.
        Owner square = new Owner("square");
        Call<List<Contributor>> call = github.contributors(square, "retrofit");
        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Response<List<Contributor>> response) {
                // System.out.println(response.headers().toString());
                // System.out.println(response.raw()); // => okhttp/Response

                List<Contributor> contributors = response.body();
                for (Contributor contributor : contributors) {
                    System.out.println(contributor.login + " (" + contributor.contributions + ")");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                // do nothing
            }
        });
        // if you call.enqueue() twice,  IllegalStateException occurres
    }

    public static void callWithUrl() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SampleService github = retrofit.create(SampleService.class);

        Call<List<Contributor>> nextCall = github.contributorsPaginate("https://api.github.com/repos/square/moshi/contributors");
        nextCall.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Response<List<Contributor>> response) {
                List<Contributor> contributors = response.body();
                for (Contributor contributor : contributors) {
                    System.out.println(contributor.login + " (" + contributor.contributions + ")");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                // do nothing
            }
        });
    }

    public static void callMultiConverter() throws IOException {
        // multiple converter
        Retrofit retrofitMulti = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ProtoConverterFactory.create())
                .build();
        VariousService service = retrofitMulti.create(VariousService.class);
        Call<List<Contributor>> call = service.repoContributors("square", "retrofit");
        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Response<List<Contributor>> response) {
                List<Contributor> contributors = response.body();
                for (Contributor contributor : contributors) {
                    System.out.println(contributor.login + " (" + contributor.contributions + ")");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                // do nothing
            }
        });
    }
}
