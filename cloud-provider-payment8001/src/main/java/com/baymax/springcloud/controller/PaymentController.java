package com.baymax.springcloud.controller;

import com.baymax.springcloud.entities.CommonResult;
import com.baymax.springcloud.entities.Payment;
import com.baymax.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: some desc
 * @author: baymax
 * @email: xxx@xx.com
 * @date: 2021/2/22 1:04 下午
 */
@Slf4j
@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;
    @Value("${server.port}")
    private String serverPort;
    @PostMapping("/payment/create")
    public CommonResult<Integer> create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("******Insertion Result:"+ result);
        if(result > 0 )
        {
            return new CommonResult<Integer>(200,"Insertion Database Success,serverPort:"+serverPort,result);
        }
        return new CommonResult<Integer>(444,"Insertion Database Failing!",null);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("******Insertion Result:"+ payment);
        if(payment != null )
        {
            return new CommonResult<Payment>(200,"Query Database Success,serverPort:"+serverPort,payment);
        }
        return new CommonResult<Payment>(444,"could not query corresponding record,it failing ,query ID:"+id+".",null);
    }

    @GetMapping("/payment/discovery")
    public Object discovery()
    {
        List<String> services = discoveryClient.getServices();

        for (String srv : services) {
            log.info("****srv:"+srv);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance:
             instances) {
            log.info(instance.getInstanceId()
                    + "\t" + instance.getHost()
                    + "\t" + instance.getPort()
                    + "\t" + instance.getUri()
            );
        }
        return discoveryClient;
    }
}
