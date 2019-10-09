package com.lfkdsk.bika.api;/*
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

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

import java.util.concurrent.TimeUnit;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

public class BaseRetrofitManager<ApiType> {
    protected ApiType api;

    private Retrofit.Builder buildRetrofit() {
        Interceptor http = new HttpLoggingInterceptor().setLevel(BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(http)
                .build();

        return new Retrofit.Builder().client(client);
    }

    public Retrofit buildRetrofit(String baseUrl) {
        return buildRetrofit().baseUrl(baseUrl).build();
    }

    public ApiType getApi() {
        return api;
    }

    public void setApi(ApiType api) {
        this.api = api;
    }
}
