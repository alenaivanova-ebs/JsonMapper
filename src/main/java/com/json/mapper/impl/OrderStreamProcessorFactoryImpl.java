package com.json.mapper.impl;

import com.google.auto.service.AutoService;

import java.time.Duration;

import com.json.mapper.api.OrderStreamProcessor;
import com.json.mapper.api.OrderStreamProcessorFactory;

@AutoService(OrderStreamProcessorFactory.class)
public final class OrderStreamProcessorFactoryImpl implements OrderStreamProcessorFactory {


    @Override
    public OrderStreamProcessor createProcessor(int maxOrders, Duration maxTime) {
        return new OrderStreamProcessorImpl(maxOrders, maxTime);
    }
}
