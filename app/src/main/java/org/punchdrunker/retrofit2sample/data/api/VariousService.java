package org.punchdrunker.retrofit2sample.data.api;

import org.punchdrunker.retrofit2sample.data.api.model.Contributor;

import java.util.List;
import java.util.concurrent.Future;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by takafumi.nanao on 9/10/15.
 */
public interface VariousService {
    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> repoContributors(
            @Path("owner") String owner,
            @Path("repo") String repo);

    @GET("/repos/{owner}/{repo}/contributors")
    Observable<List<Contributor>> repoContributors2(
            @Path("owner") String owner,
            @Path("repo") String repo);

    // this endpoint raises exception
    @GET("/repos/{owner}/{repo}/contributors")
    Future<List<Contributor>> repoContributors3(
            @Path("owner") String owner,
            @Path("repo") String repo);

}
