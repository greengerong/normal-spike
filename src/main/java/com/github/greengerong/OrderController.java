package com.github.greengerong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

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
@Controller
public class OrderController {
    private IOrderService orderService;

    @Autowired
    public OrderController(@Qualifier("name1") IOrderService orderService) {
        this.orderService = orderService;
    }

    public String add(int id) {
        return orderService.add(id);
    }

}
