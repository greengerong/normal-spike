package com.github.greengerong;

import java.io.Serializable;
import java.util.Date;

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
public class CacheItem implements Serializable {
    private double value;
    private Date date;

    public CacheItem(double value, Date date) {

        this.value = value;
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CacheItem cacheItem = (CacheItem) o;

        if (Double.compare(cacheItem.value, value) != 0) return false;
        return date.equals(cacheItem.date);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(value);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + date.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CacheItem{" +
                "value=" + value +
                ", date=" + date +
                '}';
    }
}
