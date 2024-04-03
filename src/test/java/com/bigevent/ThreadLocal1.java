package com.bigevent;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
@Slf4j
public class ThreadLocal1 {
    ThreadLocal t=new ThreadLocal<>();
@Test
    public void test1(){
        t.set("long");
       log.info("{}",t.get());
    }
@Test
    public void test2(){
        log.info("{}",t.get());
    }
}
