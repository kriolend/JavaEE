package org.demo.beans;

/**
 * Created by u0100 on 03.06.2016.
 */
public class HelloWorld {
    private int count;
    private MessageGenerator generator;

    public HelloWorld(int count, MessageGenerator generator) {
        this.count = count;
        this.generator = generator;
    }

    public String getMessage() {
        return generator.generateMessage();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public MessageGenerator getGenerator() {
        return generator;
    }

    public void setGenerator(MessageGenerator generator) {
        this.generator = generator;
    }

    public void initialize() {
        System.out.println("init me");
    }

    public void destroy() {
        System.out.println("destroy me");
    }
}
