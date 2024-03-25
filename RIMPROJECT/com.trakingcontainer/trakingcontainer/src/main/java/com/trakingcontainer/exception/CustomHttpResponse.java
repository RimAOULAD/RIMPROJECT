/**
 * This class represents a custom HTTP response for exceptions.

 * Cette classe représente une réponse HTTP personnalisée pour les exceptions.
 */

package com.trakingcontainer.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomHttpResponse {
    private String message; // The message to be included in the HTTP response// Le message à inclure dans la réponse HTTP
}


