/*
 * Copyright 2015 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.plaid.data.api.dribbble;

import android.support.annotation.StringDef;

import com.example.android.plaid.data.api.dribbble.model.Comment;
import com.example.android.plaid.data.api.dribbble.model.Like;
import com.example.android.plaid.data.api.dribbble.model.Shot;
import com.example.android.plaid.data.api.dribbble.model.User;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by nickbutcher on 1/26/15.
 */
public interface DribbbleService {

    String ENDPOINT = "https://api.dribbble.com/v1/";
    String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss Z";
    int PER_PAGE_MAX = 100;

    /* Shots */
    public static final String SHOT_TYPE_ANIMATED = "animated";
    public static final String SHOT_TYPE_ATTACHMENTS = "attachments";
    public static final String SHOT_TYPE_DEBUTS = "debuts";
    public static final String SHOT_TYPE_PLAYOFFS = "playoffs";
    public static final String SHOT_TYPE_REBOUNDS = "rebounds";
    public static final String SHOT_TYPE_TEAMS = "teams";
    public static final String SHOT_TIMEFRAME_WEEK = "week";
    public static final String SHOT_TIMEFRAME_MONTH = "month";
    public static final String SHOT_TIMEFRAME_YEAR = "year";
    public static final String SHOT_TIMEFRAME_EVER = "ever";
    public static final String SHOT_SORT_COMMENTS = "comments";
    public static final String SHOT_SORT_RECENT = "recent";
    public static final String SHOT_SORT_VIEWS = "views";

    @GET("/shots")
    List<Shot> getPopular();

    @GET("/shots")
    void getPopular(Callback<List<Shot>> callback);

    @GET("/shots")
    void getPopular(@Query("per_page") Integer pageSize, Callback<List<Shot>> callback);


    /* Shot likes */

    @GET("/shots")
    void getPopular(@Query("page") Integer page, @Query("per_page") Integer pageSize,
                    Callback<List<Shot>> callback);

    @GET("/shots?list=debuts")
    void getDebuts(Callback<List<Shot>> callback);

    @GET("/shots?list=debuts")
    void getDebuts(@Query("page") Integer page, @Query("per_page") Integer pageSize,
                   Callback<List<Shot>> callback);

    @GET("/shots?sort=recent")
    void getRecent(Callback<List<Shot>> callback);

    @GET("/shots?sort=recent")
    void getRecent(@Query("page") Integer page, @Query("per_page") Integer pageSize,
                   Callback<List<Shot>> callback);

    @GET("/shots?list=animated")
    void getAnimated(Callback<List<Shot>> callback);

    @GET("/shots?list=animated")
    void getAnimated(@Query("page") Integer page, @Query("per_page") Integer pageSize,
                     Callback<List<Shot>> callback);

    @GET("/shots")
    void getShots(@Query("list") @ShotType String shotType, @Query("timeframe") @ShotTimeframe
    String timeframe, @Query("sort") @ShotSort String shotSort, Callback<List<Shot>> callback);


    /* Comments */

    @GET("/shots/{id}")
    Shot getShot(@Path("id") long shotId);

    @GET("/shots/{id}")
    void getShot(@Path("id") long shotId, Callback<Shot> callback);

    @GET("/user/following/shots")
    List<Shot> getFollowing();

    @GET("/user/following/shots")
    void getFollowing(Callback<List<Shot>> callback);

    @GET("/user/following/shots")
    void getFollowing(@Query("per_page") Integer pageSize, Callback<List<Shot>> callback);

    @GET("/shots/{id}/likes")
    List<Like> getLikes(@Path("id") long shotId);

    @GET("/shots/{id}/likes")
    void getLikes(@Path("id") long shotId, Callback<List<Like>> callback);

    @GET("/shots/{id}/like")
    Like liked(@Path("id") long shotId);

    @GET("/shots/{id}/like")
    void liked(@Path("id") long shotId, Callback<Like> callback);

    @POST("/shots/{id}/like")
    Like like(@Path("id") long shotId);

    @POST("/shots/{id}/like")
    void like(@Path("id") long shotId,
              @Body String ignored,  // can remove when retrofit releases this fix:
              // https://github.com/square/retrofit/commit/19ac1e2c4551448184ad66c4a0ec172e2741c2ee
              Callback<Like> callback);

    @DELETE("/shots/{id}/like")
    void unlike(@Path("id") long shotId);

    @DELETE("/shots/{id}/like")
    void unlike(@Path("id") long shotId, Callback<Void> callback);

    @GET("/shots/{id}/comments")
    List<Comment> getComments(@Path("id") long shotId);

    @GET("/shots/{id}/comments")
    void getComments(@Path("id") long shotId, Callback<List<Comment>> callback);


    /* Users */

    @GET("/shots/{id}/comments")
    void getComments(@Path("id") long shotId, @Query("page") Integer page, @Query("per_page")
    Integer pageSize, Callback<List<Comment>> callback);

    @GET("/shots/{shot}/comments/{id}/likes")
    List<Like> getCommentLikes(@Path("shot") long shotId, @Path("id") long commentId);

    @GET("/shots/{shot}/comments/{id}/likes")
    void getCommentLikes(@Path("shot") long shotId, @Path("id") long commentId,
                         Callback<List<Like>> callback);

    @POST("/shots/{shot}/comments")
    Comment postComment(@Path("shot") long shotId, @Query("body") String body);

    @POST("/shots/{shot}/comments")
    void postComment(@Path("shot") long shotId, @Query("body") String body, Callback<Comment>
            callback);

    @DELETE("/shots/{shot}/comments/{id}")
    void deleteComment(@Path("shot") long shotId, @Path("id") long commentId);



    /* Magic Constants */

    @DELETE("/shots/{shot}/comments/{id}")
    void deleteComment(@Path("shot") long shotId, @Path("id") long commentId, Callback<Void>
            callback);

    @GET("/shots/{shot}/comments/{id}/like")
    Like likedComment(@Path("shot") long shotId, @Path("id") long commentId);

    @GET("/shots/{shot}/comments/{id}/like")
    void likedComment(@Path("shot") long shotId, @Path("id") long commentId, Callback<Like>
            callback);

    @POST("/shots/{shot}/comments/{id}/like")
    Like likeComment(@Path("shot") long shotId, @Path("id") long commentId);

    @POST("/shots/{shot}/comments/{id}/like")
    void likeComment(@Path("shot") long shotId,
                     @Path("id") long commentId,
                     @Body String ignored,  // can remove when retrofit releases this fix:
                     // https://github
                     // .com/square/retrofit/commit/19ac1e2c4551448184ad66c4a0ec172e2741c2ee
                     Callback<Like> callback);

    @DELETE("/shots/{shot}/comments/{id}/like")
    void unlikeComment(@Path("shot") long shotId, @Path("id") long commentId);

    @DELETE("/shots/{shot}/comments/{id}/like")
    void unlikeComment(@Path("shot") long shotId, @Path("id") long commentId, Callback<Void>
            callback);

    @GET("/users/{user}")
    User getUser(@Path("user") long userId);

    @GET("/users/{user}")
    void getUser(@Path("user") long userId, Callback<User> callback);

    @GET("/users/{user}")
    User getUser(@Path("user") String username);

    @GET("/users/{user}")
    void getUser(@Path("user") String username, Callback<User> callback);

    @GET("/user")
    User getAuthenticatedUser();

    @GET("/user")
    void getAuthenticatedUser(Callback<User> callback);

    // Shot Type
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({SHOT_TYPE_ANIMATED, SHOT_TYPE_ATTACHMENTS, SHOT_TYPE_DEBUTS, SHOT_TYPE_PLAYOFFS,
            SHOT_TYPE_REBOUNDS, SHOT_TYPE_TEAMS})
    public @interface ShotType {
    }

    // Shot timeframe
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({SHOT_TIMEFRAME_WEEK, SHOT_TIMEFRAME_MONTH, SHOT_TIMEFRAME_YEAR,
            SHOT_TIMEFRAME_EVER})
    public @interface ShotTimeframe {
    }

    // Short sort order
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({SHOT_SORT_COMMENTS, SHOT_SORT_RECENT, SHOT_SORT_VIEWS})
    public @interface ShotSort {
    }
}
