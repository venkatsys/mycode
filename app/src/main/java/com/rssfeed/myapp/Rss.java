package com.rssfeed.myapp;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by venkatesanr on 20-05-2015.
 */
@Root(name = "rss" , strict = false)
public class Rss {
    @Element
    private Channel channel;
    @Attribute
    private String version;
    public Channel getChannel() {
        return channel;
    }
}
