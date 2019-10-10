package com.lfkdsk.bika;/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.lfkdsk.bika.api.BaseRetrofitManager;
import com.lfkdsk.bika.api.BiKaApiService;
import com.lfkdsk.bika.api.RestWakaClient;
import com.lfkdsk.bika.request.SignInBody;
import com.lfkdsk.bika.response.*;
import com.lfkdsk.bika.utils.BikaJni;
import com.lfkdsk.bika.utils.HttpDns;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public final class BikaApi extends BaseRetrofitManager<BiKaApiService> {
    public static List<String> dns = Arrays.asList("104.20.180.50", "104.20.181.50");

    private String TAG = "BikaApi";
    //-------------------    bika   API  --------------------------;
    private String API_KEY = "C69BAF41DA5ABD1FFEDC6D2FEA56B";
    private String BASE_URL_PIKA = "https://picaapi.picacomic.com/";
    private String CERT_URL = "picaapi.picacomic.com";
    private String buildVersion = "42";
    private String uuid = "07c3d76a-128e-3fe7-bc99-f6db60713e72";
    private String version = "2.2.0.0.1.1";
    private int channel = 2;
    private String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ODZkMTQ5MWU2MzU4OTJhM2VkOThlMTUiLCJlbWFpbCI6Imxma2RzayIsInJvbGUiOiJtZW1iZXIiLCJuYW1lIjoiaGFoYWhhZHNrIiwidmVyc2lvbiI6IjIuMi4wLjAuMS4xIiwiYnVpbGRWZXJzaW9uIjoiNDIiLCJwbGF0Zm9ybSI6ImFuZHJvaWQiLCJpYXQiOjE1NzA2MjEwNzUsImV4cCI6MTU3MTIyNTg3NX0.XAR7lU-I72pdvF-req973-_vVUhzRBUeR1aPyFx4EmU";
    private String imageServer = "https://s3.picacomic.com/static/";
    private OkHttpClient client;

    public void initClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS);
        builder.dns(new HttpDns());
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                String uid = UUID.randomUUID().toString().replace("-", "");
                String url = original.url().toString().replace(BASE_URL_PIKA, "");
                String time = String.valueOf(System.currentTimeMillis() / 1000);

                String[] params = {BASE_URL_PIKA, url, time, uid, original.method(), API_KEY, version, buildVersion};
                String signature = BikaJni.INSTANCE.getStringCon(params);
                Response response = chain.proceed(
                        original.newBuilder()
                                .header("api-key", API_KEY)
                                .header("accept", "application/vnd.picacomic.com.v1+json")
                                .header("app-channel", "2")
                                .header("time", time)
                                .header("authorization", token)
                                .header("nonce", uid)
                                .header("signature", signature)
                                .header("app-version", version)
                                .header("app-uuid", uuid)
                                .header("image-quality", "original")//哔咔服务器加载图片质量
                                .header("app-platform", "android")
                                .header("app-build-version", buildVersion)
                                .header("user-agent", "okhttp/3.8.1")
                                .method(original.method(), original.body())
                                .build()
                );

                String serverTime = response.headers().get("Server-Time");
                if (serverTime != null) {
//                    String diffTime = java.lang.Long.parseLong(serverTime) - System.currentTimeMillis() / 1000;
//                    PreferenceHelper.setTimeDifference(context, diffTime)
                }
                return response;
            }
        });

        BiKaApiService api = new Retrofit.Builder()
                .baseUrl(BASE_URL_PIKA)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build()).build()
                .create(BiKaApiService.class);


        this.setApi(api);
        this.client = builder.build();
    }

    public List<String> dns() throws IOException {
        retrofit2.Response<WakaInitResponse> res = new RestWakaClient().getApiService().getWakaInit().execute();
        if (res.body() != null) {
            return dns = res.body().addresses;
        }

        return Collections.emptyList();
    }

    public String token(String name, String password) throws IOException {
        retrofit2.Response<GeneralResponse<SignInResponse>> res = this.getApi()
                .signIn(new SignInBody(name, password)).execute();
        if (res.body() == null) {
            return null;
        }

        GeneralResponse<SignInResponse> body = res.body();
        SignInResponse data = body.data;
        if (data == null) {
            return null;
        }
        return token = data.getToken();
    }

    public List<Category> categories() throws IOException {
        retrofit2.Response<GeneralResponse<CategoryResponse>> res = BikaApi.getInstance().getApi().getCategories(token).execute();
        if (res.body() == null) {
            return null;
        }

        GeneralResponse<CategoryResponse> body = res.body();
        CategoryResponse data = body.data;
        if (data == null || data.categories == null) {
            return null;
        }

        return data.categories.stream()
                .filter(category -> category.getActive() || category.getCategoryId() != null)
                .collect(Collectors.toList());
    }

    public ComicPage page(String category, int page) throws IOException {
        retrofit2.Response<GeneralResponse<ComicListResponse>> res =
                getInstance().getApi().getComicList(token, page, category, null, null, null, "ua", null, null).execute();
        if (res.body() == null) {
            return null;
        }

        GeneralResponse<ComicListResponse> body = res.body();
        ComicListResponse data = body.data;
        if (data == null || data.getComics() == null) {
            return null;
        }

        return data.getComics();
    }

    public String initImage(String token) throws IOException {
        retrofit2.Response<GeneralResponse<InitialResponse>> res = getInstance().getApi().getInit(token).execute();
        if (res.body() == null) {
            return null;
        }

        GeneralResponse<InitialResponse> body = res.body();
        InitialResponse data = body.data;
        if (data == null) {
            return null;
        }

        return imageServer = data.imageServer;
    }

    public ComicEpisodeData eps(String comicId, int page) throws IOException {
        retrofit2.Response<GeneralResponse<ComicEpisodeResponse>> res = getInstance().getApi().getComicEpisode(token, comicId, page).execute();
        if (res.body() == null) {
            return null;
        }

        GeneralResponse<ComicEpisodeResponse> body = res.body();
        ComicEpisodeResponse data = body.data;
        if (data == null) {
            return null;
        }

        return data.getEps();
    }

    public Request pageRequest(String categoryName, int page) {
        return getInstance().getApi().getComicList(token, page, categoryName, null, null, null, "ua", null, null).request();
    }

    public Request chapterRequest(String comicId, int page) {
        return getApi().getComicEpisode(token, comicId, page).request();
    }

    public Request searchRequest(String query, int page) {
        return getApi().getComicListWithSearchKey(token, page, query).request();
    }

    public static final class INSTANCE {
        static final BikaApi instance = new BikaApi();
    }

    private BikaApi() {
    }

    public static BikaApi getInstance() {
        return INSTANCE.instance;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getImageServer() {
        return imageServer;
    }

    public void setImageServer(String imageServer) {
        this.imageServer = imageServer;
    }

    public OkHttpClient getClient() {
        return client;
    }
}
