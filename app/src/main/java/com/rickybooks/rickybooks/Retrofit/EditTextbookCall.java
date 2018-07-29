package com.rickybooks.rickybooks.Retrofit;

import com.rickybooks.rickybooks.MainActivity;
import com.rickybooks.rickybooks.Other.Alert;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EditTextbookCall {
    private MainActivity activity;
    private boolean isSuccessful;

    public EditTextbookCall(MainActivity activity) {
        this.activity = activity;
        isSuccessful = false;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void req(String textbookId, String textbookTitle, String textbookAuthor,
                    String textbookEdition, String textbookCondition, String textbookType,
                    String textbookCoursecode, String textbookPrice) {
        String token = activity.getToken();
        String tokenString = "Token token=" + token;

        try {
            Retrofit retrofit = new RetrofitClient().getClient();
            TextbookService textbookService = retrofit.create(TextbookService.class);

            Call<Void> call = textbookService.editTextbook(tokenString, textbookId, textbookTitle,
                    textbookAuthor, textbookEdition, textbookCondition, textbookType,
                    textbookCoursecode, textbookPrice);
            Response<Void> response = call.execute();

            isSuccessful = response.isSuccessful();
            if(!isSuccessful) {
                Alert alert = new Alert(activity);
                alert.create("Oh no! Server problem!", "Seems like we are unable to " +
                        "reach the server at the moment.\n\nPlease try again later.");
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}