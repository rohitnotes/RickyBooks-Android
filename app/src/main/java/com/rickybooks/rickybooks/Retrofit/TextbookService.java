package com.rickybooks.rickybooks.Retrofit;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import okhttp3.RequestBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface TextbookService {
    // MessagesFragment
    @GET("conversations/{conversation_id}/messages")
    Call<JsonArray> getMessages(
        @Header("Authorization") String tokenString,
        @Path("conversation_id") String conversationId
    );

    // ConversationsFragment
    @GET("conversations/{user_id}")
    Call<JsonArray> getConversations(
        @Header("Authorization") String tokenString,
        @Path("user_id") String userId
    );

    // DetailsFragment && MessagesFragment
    @FormUrlEncoded
    @POST("conversations/{conversation_id}/messages")
    Call<JsonObject> sendMessage(
        @Header("Authorization") String tokenString,
        @Path("conversation_id") String conversationId,
        @Field("body") String body,
        @Field("user_id") String userId
    );

    // DetailsFragment
    @FormUrlEncoded
    @POST("conversations")
    Call<JsonObject> createConversation(
        @Header("Authorization") String tokenString,
        @Field("sender_id") String userId,
        @Field("recipient_id") String sellerId,
        @Field("textbook_id") String textbookId
    );

    // LoginFragment
    @POST("login")
    Call<JsonObject> login(
        @Body JsonObject user
    );

    // RegisterFragment
    @POST("users")
    Call<JsonObject> register(
        @Body JsonObject user
    );

    // BuyFragment
    @GET("textbooks")
    Call<JsonArray> getTextbooks();

    // SellFragment
    @FormUrlEncoded
    @POST("textbooks")
    Call<String> postTextbookForm(
        @Header("Authorization") String tokenString,
        @Field("user_id") String userId,
        @Field("textbook_title") String textbookTitle,
        @Field("textbook_author") String textbookAuthor,
        @Field("textbook_edition") String textbookEdition,
        @Field("textbook_condition") String textbookCondition,
        @Field("textbook_type") String textbookType,
        @Field("textbook_coursecode") String textbookCoursecode,
        @Field("textbook_price") String textbookPrice
    );

    // SellFragment
    @GET("aws/{textbook_id}/{extension}")
    Call<String> getSignedPostUrl(
        @Header("Authorization") String tokenString,
        @Path("textbook_id") String textbookId,
        @Path("extension") String extension
    );

    // SellFragment
    @PUT
    Call<Void> putImageAws(
        @Url String url,
        @Body RequestBody imageFile
    );

    // ProfileFragment
    @GET("users/{user_id}/textbooks")
    Call<JsonArray> getUserTextbooks(
        @Path("user_id") String userId
    );

    // ProfileFragment
    @DELETE("textbooks/{textbook_id}")
    Call<String> deleteTextbook(
        @Header("Authorization") String tokenString,
        @Path("textbook_id") String textbookId
    );

    // ProfileFragment
    @DELETE
    Call<Void> deleteImage(
        @Url String url
    );

    // ProfileFragment
    @DELETE("logout")
    Call<Void> logout(
        @Header("Authorization") String tokenString
    );
}