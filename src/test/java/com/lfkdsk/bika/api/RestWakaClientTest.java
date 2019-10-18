package com.lfkdsk.bika.api;

import com.lfkdsk.bika.BikaApi;
import com.lfkdsk.bika.request.SignInBody;
import com.lfkdsk.bika.response.*;
import org.junit.Assert;
import org.junit.Test;
import retrofit2.Response;

import java.io.IOException;

/*
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
public class RestWakaClientTest {
    public static SignInBody body = new SignInBody("lfkdsk", "lfk2014ws");

    @Test
    public void testInit() throws IOException {
        Response<WakaInitResponse> response = new RestWakaClient().getApiService().getWakaInit().execute();
        System.out.println(response);
    }

    @Test
    public void testBiKaInit() throws IOException {
        Response<WakaInitResponse> response = new RestWakaClient().getApiService().getWakaInit().execute();
        assert response.body() != null;
        System.out.println(response.body().addresses);

        BikaApi.getInstance().initClient();
        Response<GeneralResponse<SignInResponse>> signIn = BikaApi.getInstance()
                .getApi()
                .signIn(body).execute();

        System.out.println(signIn);
        assert signIn.body() != null;
        Assert.assertEquals(200, signIn.body().code);
        Assert.assertEquals("success", signIn.body().message);
        assert signIn.body().data != null;
        Assert.assertNotNull(signIn.body().data.getToken());
    }

    @Test
    public void testCategory() throws IOException {
        Response<WakaInitResponse> response = new RestWakaClient().getApiService().getWakaInit().execute();
        assert response.body() != null;
        System.out.println(response.body().addresses);

        BikaApi.getInstance().initClient();
        Response<GeneralResponse<SignInResponse>> signIn = BikaApi.getInstance()
                .getApi()
                .signIn(body).execute();

        System.out.println(signIn);
        assert signIn.body() != null;
        Assert.assertEquals(200, signIn.body().code);
        Assert.assertEquals("success", signIn.body().message);
        assert signIn.body().data != null;
        Assert.assertNotNull(signIn.body().data.getToken());

        String token = signIn.body().data.getToken();

        Response<GeneralResponse<CategoryResponse>> res = BikaApi.getInstance().getApi().getCategories(token).execute();
        System.out.println(res);
    }

//    [5821859b5f6b9a4f93dbf6e9,嗶咔漢化, 5821859b5f6b9a4f93dbf6d1,全彩, 5821859b5f6b9a4f93dbf6cd,長篇, 5821859b5f6b9a4f93dbf6ca,同人, 5821859b5f6b9a4f93dbf6ce,短篇, 584ea1f45a44ac4f7dce3623,圓神領域, 58542b601b8ef1eb33b57959,碧藍幻想, 5821859b5f6b9a4f93dbf6e5,CG雜圖, 5821859b5f6b9a4f93dbf6e8,英語 ENG, 5821859b5f6b9a4f93dbf6e0,生肉, 5821859b5f6b9a4f93dbf6de,純愛, 5821859b5f6b9a4f93dbf6d2,百合花園, 5821859b5f6b9a4f93dbf6e2,耽美花園, 5821859b5f6b9a4f93dbf6e4,偽娘哲學, 5821859b5f6b9a4f93dbf6d3,後宮閃光, 5821859b5f6b9a4f93dbf6d4,扶他樂園, 5abb3fd683111d2ad3eecfca,單行本, 5821859b5f6b9a4f93dbf6da,姐姐系, 5821859b5f6b9a4f93dbf6db,妹妹系, 5821859b5f6b9a4f93dbf6cb,SM, 5821859b5f6b9a4f93dbf6d0,性轉換, 5821859b5f6b9a4f93dbf6df,足の恋, 5821859b5f6b9a4f93dbf6d5,重口地帶, 5821859b5f6b9a4f93dbf6cc,人妻, 5821859b5f6b9a4f93dbf6d8,NTR, 5821859b5f6b9a4f93dbf6d9,強暴, 5821859b5f6b9a4f93dbf6d6,非人類, 5821859b5f6b9a4f93dbf6cf,艦隊收藏, 5821859b5f6b9a4f93dbf6d7,Love Live, 5821859b5f6b9a4f93dbf6dc,SAO 刀劍神域, 5821859b5f6b9a4f93dbf6e1,Fate, 5821859b5f6b9a4f93dbf6dd,東方, 59041d54ccc747074b47dae4,WEBTOON, 5821859b5f6b9a4f93dbf6e3,禁書目錄, 5bd66e7e8ff47f7c46cf999d,歐美]
//    eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ODZkMTQ5MWU2MzU4OTJhM2VkOThlMTUiLCJlbWFpbCI6Imxma2RzayIsInJvbGUiOiJtZW1iZXIiLCJuYW1lIjoiaGFoYWhhZHNrIiwidmVyc2lvbiI6IjIuMi4wLjAuMS4xIiwiYnVpbGRWZXJzaW9uIjoiNDIiLCJwbGF0Zm9ybSI6ImFuZHJvaWQiLCJpYXQiOjE1NzA2MTQxODUsImV4cCI6MTU3MTIxODk4NX0.BMIaYSeVoQ2c2LbZYweTmQK5WM5-gywyDNaVt1kH2QQ

    @Test
    public void testGetPages() throws IOException {
//        Response<WakaInitResponse> response = new RestWakaClient().getApiService().getWakaInit().execute();
//        assert response.body() != null;
//        System.out.println(response.body().addresses);

        BikaApi.getInstance().initClient();
//        Response<GeneralResponse<InitialResponse>> res0 = BikaApi.getInstance().getApi().getInit(BikaApi.getInstance().getToken()).execute();
//        System.out.println(res0);

        Response<GeneralResponse<ComicListResponse>> res = BikaApi.getInstance().getApi().getComicList(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ODZkMTQ5MWU2MzU4OTJhM2VkOThlMTUiLCJlbWFpbCI6Imxma2RzayIsInJvbGUiOiJtZW1iZXIiLCJuYW1lIjoiaGFoYWhhZHNrIiwidmVyc2lvbiI6IjIuMi4wLjAuMS4xIiwiYnVpbGRWZXJzaW9uIjoiNDIiLCJwbGF0Zm9ybSI6ImFuZHJvaWQiLCJpYXQiOjE1NzA2MjEwNzUsImV4cCI6MTU3MTIyNTg3NX0.XAR7lU-I72pdvF-req973-_vVUhzRBUeR1aPyFx4EmU",
                1, "嗶咔漢化", null, null, null, "ua", null, null).execute();
        System.out.println(res);
    }

    @Test
    public void testGetCat() throws IOException {
        BikaApi.getInstance().initClient();
        Response<GeneralResponse<CategoryResponse>> res = BikaApi.getInstance().getApi().getCategories(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ODZkMTQ5MWU2MzU4OTJhM2VkOThlMTUiLCJlbWFpbCI6Imxma2RzayIsInJvbGUiOiJtZW1iZXIiLCJuYW1lIjoiaGFoYWhhZHNrIiwidmVyc2lvbiI6IjIuMi4wLjAuMS4xIiwiYnVpbGRWZXJzaW9uIjoiNDIiLCJwbGF0Zm9ybSI6ImFuZHJvaWQiLCJpYXQiOjE1NzA2MjEwNzUsImV4cCI6MTU3MTIyNTg3NX0.XAR7lU-I72pdvF-req973-_vVUhzRBUeR1aPyFx4EmU"
        ).execute();
        System.out.println(res);
    }

    @Test
    public void testSearch() throws IOException {
//        Response<WakaInitResponse> response = new RestWakaClient().getApiService().getWakaInit().execute();
//        assert response.body() != null;
//        System.out.println(response.body().addresses);

        BikaApi.getInstance().initClient();
//        Response<GeneralResponse<InitialResponse>> res0 = BikaApi.getInstance().getApi().getInit(BikaApi.getInstance().getToken()).execute();
//        System.out.println(res0);

        Response<GeneralResponse<ComicListResponse>> res = BikaApi.getInstance().getApi().getComicListWithSearchKey(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ODZkMTQ5MWU2MzU4OTJhM2VkOThlMTUiLCJlbWFpbCI6Imxma2RzayIsInJvbGUiOiJtZW1iZXIiLCJuYW1lIjoiaGFoYWhhZHNrIiwidmVyc2lvbiI6IjIuMi4wLjAuMS4xIiwiYnVpbGRWZXJzaW9uIjoiNDIiLCJwbGF0Zm9ybSI6ImFuZHJvaWQiLCJpYXQiOjE1NzA2MjEwNzUsImV4cCI6MTU3MTIyNTg3NX0.XAR7lU-I72pdvF-req973-_vVUhzRBUeR1aPyFx4EmU",
                1, "lovelive").execute();
        System.out.println(res);
    }

    @Test
    public void testTags() throws IOException {
        BikaApi.getInstance().initClient();
//        Response<GeneralResponse<InitialResponse>> res0 = BikaApi.getInstance().getApi().getInit(BikaApi.getInstance().getToken()).execute();
//        System.out.println(res0);

        Response<GeneralResponse<TagListResponse>> res = BikaApi.getInstance().getApi().getTags(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ODZkMTQ5MWU2MzU4OTJhM2VkOThlMTUiLCJlbWFpbCI6Imxma2RzayIsInJvbGUiOiJtZW1iZXIiLCJuYW1lIjoiaGFoYWhhZHNrIiwidmVyc2lvbiI6IjIuMi4wLjAuMS4xIiwiYnVpbGRWZXJzaW9uIjoiNDIiLCJwbGF0Zm9ybSI6ImFuZHJvaWQiLCJpYXQiOjE1NzA2MjEwNzUsImV4cCI6MTU3MTIyNTg3NX0.XAR7lU-I72pdvF-req973-_vVUhzRBUeR1aPyFx4EmU"
                ).execute();
        System.out.println(res);
    }

    @Test
    public void testLoadPages() throws IOException {
        BikaApi.getInstance().initClient();
        BikaApi.getInstance().pages("5d95b0fdacbfe77029cb0aac", 1, 1);
        BikaApi.getInstance().pages("5d95b0fdacbfe77029cb0aac", 1, 2);
    }
}