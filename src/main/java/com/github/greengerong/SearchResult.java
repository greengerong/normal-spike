package com.github.greengerong;

/**
 * ***************************************
 * *
 * Auth: green gerong                     *
 * Date: 2015                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 * *
 * ****************************************
 */
public class SearchResult<T> {

    private T[] result;

    public T[] getResult() {
        return result;
    }

    public void setResult(T[] result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "result=" + result +
                '}';
    }
}
