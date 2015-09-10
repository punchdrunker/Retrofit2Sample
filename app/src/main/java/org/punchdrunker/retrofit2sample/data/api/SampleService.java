package org.punchdrunker.retrofit2sample.data.api;

import org.punchdrunker.retrofit2sample.data.api.model.Contributor;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Url;

/**
 * Created by takafumi.nanao on 9/10/15.
 */
public interface SampleService {
    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> contributors(
            @Path("owner") Owner owner,
            @Path("repo") String repo);

    @GET
    Call<List<Contributor>> contributorsPaginate(
            @Url String url);
}
