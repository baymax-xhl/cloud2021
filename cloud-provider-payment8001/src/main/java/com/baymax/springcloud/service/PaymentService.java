package com.baymax.springcloud.service;

import com.baymax.springcloud.entities.Payment;

/**
 * @description: some desc
 * @author: baymax
 * @email: xxx@xx.com
 * @date: 2021/2/22 12:59 下午
 */
public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(Long id);
}
