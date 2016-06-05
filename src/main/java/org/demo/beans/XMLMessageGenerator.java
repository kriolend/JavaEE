package org.demo.beans;

/**
 * Created by u0100 on 03.06.2016.
 */
public class XMLMessageGenerator implements MessageGenerator{
    @Override
    public String generateMessage() {
        return "from xml";
    }
}
