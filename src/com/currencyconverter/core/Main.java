package com.currencyconverter.core;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.currencyconverter.utils.UIConverterInterface;
import com.currencyconverter.utils.UIInterface;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;

public class Main {
    private static String api_key = "99881050a7-9f36e617e2-rswtvk";

    public static void main(String[] args) throws IOException, InterruptedException, JSONException {
        UIInterface ui = new UIInterface();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.fastforex.io/fetch-all?api_key=" + api_key))
                .header("accept", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject jsonObj = new JSONObject(response.body());
        JSONObject resultObject = new JSONObject(jsonObj.get("results").toString());

        /*
        System.out.println("Gépeld be milyen valutáról szeretnél váltani!");

        Scanner sc = new Scanner(System.in);

        String valuteFromChange = "";
        String valuteToChange = "";

        while (sc.hasNextLine()) {
            String valute = sc.nextLine().toUpperCase();

            if (valuteFromChange.trim().isEmpty()) {
                if (resultObject.has(valute)) {
                    valuteFromChange = valute;

                    System.out.println("Gépeld be milyen valutára szeretnéd váltani!");
                } else {
                    System.out.println("Ez a valuta nem ismert az API számára.");
                }
            } else {
                if (resultObject.has(valute)) {
                    if (!valuteFromChange.equals(valute)) {
                        valuteToChange = valute;

                        break;
                    } else {
                        System.out.println("Ugyanarra a valutára nem tudsz váltani.");
                    }
                } else {
                    System.out.println("Ez a valuta nem ismert az API számára.");
                }
            }
        }

        System.out.println(valuteFromChange + " >> " + valuteToChange);

        applyChange(valuteFromChange, valuteToChange);
        */
    }

    public static void applyChange(String valuteFromChange, String valuteToChange, UIInterface ui) throws JSONException, IOException, InterruptedException {
        HttpRequest changeRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.fastforex.io/fetch-all?from=" + valuteFromChange + "&api_key=" + api_key))
                .header("accept", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> changeResponse = HttpClient.newHttpClient().send(changeRequest, HttpResponse.BodyHandlers.ofString());
        JSONObject jsonObj = new JSONObject(changeResponse.body());

        if (!jsonObj.has("results")) {
            JOptionPane.showMessageDialog(null, "Ez a valuta nem létezik!", "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }

        JSONObject resultObject = new JSONObject(jsonObj.get("results").toString());

        if (resultObject.has(valuteToChange)) {
            String exchangeRate = resultObject.get(valuteToChange).toString();

            ui.dispose();

            requestAmount(Double.parseDouble(exchangeRate), valuteToChange);
        } else {
            JOptionPane.showMessageDialog(null, "Ez a valuta nem létezik!", "Error", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    public static void requestAmount(double exchangeRate, String valuteToChange) {
        UIConverterInterface ui = new UIConverterInterface(exchangeRate, valuteToChange);
    }
}