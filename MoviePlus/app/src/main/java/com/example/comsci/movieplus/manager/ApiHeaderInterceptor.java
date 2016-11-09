package com.example.comsci.movieplus.manager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Teerakiat C. on 7/5/2016.
 */
public class ApiHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder requestBuilder = original.newBuilder()
                .method(original.method(), original.body());

        Request request = requestBuilder.build();

        return chain.proceed(request);
    }
}
