package com.trakingcontainer.utils;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
// Classe utilitaire pour les conversions et le formatage
// Utility class for conversions and formatting
public class ConverterHelper {
// Encodage et décodage Base64
// Base64 encoding and decoding
    private static final Base64.Encoder encoder = Base64.getEncoder();
    private static final Base64.Decoder decoder = Base64.getDecoder();
 // Instance de ObjectMapper pour la manipulation de JSON   
 // ObjectMapper instance for JSON manipulation


    private static final ObjectMapper mapper = new ObjectMapper();
 // Convertir un objet Java en format JSON
  // Convert a Java object to JSON format
    public static String convertJava2Json(Object object) {
        String resultJson = "";
        try {

            resultJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ConverterHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultJson;
    }
// Convertir une chaîne JSON en objet Java
// Convert a JSON string to a Java object
    public static <T> T convertJson2Java(String jsonString, Class<T> cls) {
        T result = null;
        try {
            result = mapper.readValue(jsonString, cls);

        } catch (IOException ex) {
            Logger.getLogger(ConverterHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
 // Encoder une chaîne en Base64
 // Encode a string to Base64
    public static String encoder(String str) {
        return encoder.encodeToString(str.getBytes());
    }
 // Décoder une chaîne depuis Base64
 // Decode a string from Base64
    public static String decoder(String str) {
        return new String(decoder.decode(str));
    }
// Format a date with the specified offset and time zone
 // Formater une date avec le décalage horaire et le fuseau horaire spécifiés
    public static String formatDate(OffsetDateTime date, String zone) {
        try {
            ZoneId zoneId = ZoneId.of(zone);
            ZonedDateTime zonedDateTime = date.atZoneSameInstant(zoneId);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            return zonedDateTime.format(formatter);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
