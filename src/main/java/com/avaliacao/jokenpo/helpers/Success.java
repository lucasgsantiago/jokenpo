package com.avaliacao.jokenpo.helpers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
public class Success {
    private String correlationId;
    private String message = "Registro salvo com sucesso!";

    public Success() {}
    
    public Success(String correlationId) {
        this.correlationId = correlationId;
    }

    public Success(String correlationId,String msg) {
        this.correlationId = correlationId;
        this.message = msg;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    

}
