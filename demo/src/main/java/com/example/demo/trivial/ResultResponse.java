package com.example.demo.trivial;

import java.util.List;

public class ResultResponse {
    private int response_code;
    private List<PreguntaResponse> results;
    public int getResponse_code() {
        return response_code;
    }
    public List<PreguntaResponse> getResults() {
        return results;
    }
    public ResultResponse(){
        
    }
    public void setResponse_code(int response_code) {
        this.response_code = response_code;
    }
    public void setResults(List<PreguntaResponse> results) {
        this.results = results;
    }
}
