package com.rssfeed.myapp;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by venkatesanr on 20-05-2015.
 */
@Root(name = "channel" , strict = false)
public class Channel {
    @ElementList(name = "item" , inline = true)
    public List<Article> articleList;
    @Element
    private String title;
    @Element
    private String link;
    @Element
    private String description;
    @Element
    private String language;
    @Element
    private String copyright;
}
