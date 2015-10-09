package com.rssfeed.myapp;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by venkatesanr on 20-05-2015.
 */
@Root(name = "item" , strict = false)
public class Article {
    /*@Element
    private String title;
    @Element(required = false)
    private String teasertitle;
    @Element(required = false)
    private String author;
    @Element
    private String link;
    @Element
    private String guid;
    @Element
    private String atypes;
    @Element(required = false)
    private String priority;
    @Element(required = false)
    private String breakingnews;
    @Element
    private String description;
    @Element(required = false)
    private String relnews;
    @Element(required = false)
    private String rellink;
    @Element(required = false)
    private String comments;
    @Element(required = false)
    private String topiclink;
    @Element
    private String pubDate;
    @Element(required = false)
    private String orgDate;
    @Element(required = false)
    private String keywords;*/

    @Element
    public String title;
    @Element(required = false)
    public String author;
    @Element
    public String description;
    @Element(required = false)
    public String img;
    @Attribute(required = false)
    public String src;
    @Element
    public String pubDate;
}
