package com.github.greengerong;

import org.springframework.stereotype.Service;

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
@Service("name1")
public class OrderService implements IOrderService {

    @Override
    public String add(int id) {
        return String.format("Add %d", id);
    }
}
