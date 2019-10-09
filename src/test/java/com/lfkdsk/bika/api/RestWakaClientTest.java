package com.lfkdsk.bika.api;

import com.lfkdsk.bika.BikaApi;
import com.lfkdsk.bika.request.SignInBody;
import com.lfkdsk.bika.response.CategoryResponse;
import com.lfkdsk.bika.response.GeneralResponse;
import com.lfkdsk.bika.response.SignInResponse;
import com.lfkdsk.bika.response.WakaInitResponse;
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
}