package com.doubleDimple.activityDemo.utils;

import org.activiti.engine.impl.cfg.IdGenerator;

import java.util.UUID;

public class DefIdGenerator implements IdGenerator {


    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    @Override
    public String getNextId() {
        return DefIdGenerator.uuid();
    }


}
