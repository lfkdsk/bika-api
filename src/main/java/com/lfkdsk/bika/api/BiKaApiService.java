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

import com.lfkdsk.bika.request.SignInBody;
import com.lfkdsk.bika.response.*;
import retrofit2.Call;
import retrofit2.http.*;

public interface BiKaApiService {
    @GET("init")
    Call<WakaInitResponse> getWakaInit();

    @GET("init?platform=android")
    Call<GeneralResponse<InitialResponse>> getInit(@Header("authorization") String str);

    @POST("auth/sign-in")
    Call<GeneralResponse<SignInResponse>> signIn(@Body SignInBody signInBody);

    @GET("categories")
    Call<GeneralResponse<CategoryResponse>> getCategories(@Header("authorization") String str);

    @GET("comics")
    Call<GeneralResponse<ComicListResponse>> getComicList(@Header("authorization") String authorization, @Query("page") int page, @Query("c") String c, @Query("t") String t, @Query("a") String a, @Query("f") String f, @Query("s") String s, @Query("ct") String ct, @Query("ca") String ca);

    @GET("comics/{comicId}")
    Call<GeneralResponse<ComicDetailResponse>> getComicWithId(@Header("authorization") String str, @Path("comicId") String str2);

    @GET("comics/{comicId}/eps")
    Call<GeneralResponse<ComicEpisodeResponse>> getComicEpisode(@Header("authorization") String str, @Path("comicId") String str2, @Query("page") int i);

    @GET("comics/{comicId}/order/{order}/pages")
    Call<GeneralResponse<ComicPagesResponse>> getPagesWithOrder(@Header("authorization") String str, @Path("comicId") String str2, @Path("order") int i, @Query("page") int i2);
}
