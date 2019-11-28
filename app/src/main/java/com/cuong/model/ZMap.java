package com.cuong.model;

public class ZMap {
    private Result results;
    private String status;

    public ZMap() {
    }

    public ZMap(Result results, String status) {
        this.results = results;
        this.status = status;
    }

    @Override
    public String toString() {
        return "ZMap: " + "\n" +
                "\t"+ "Result: " + this.results + "\n" +
                "\t"+ "Status: " + this.status;
    }

    public Result getResults() {
        return results;
    }

    public void setResults(Result results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
