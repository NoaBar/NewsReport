package com.noah.newsreport;

public class Article {

    /**
     * Title of article
     */
    private String mTitle;

    /**
     * Author of article
     */
    private String mAuthor;

    /**
     * The date the article was published
     */
    private long mDate;

    /**
     * Section of article
     */
    private String mSection;

    /**
     * Section of article
     */
    private String mUrl;

    /**
     * Constructor
     * Create a new Article object.
     * @param title title of article
     * @param author author of article
     * @param date Date of article
     * @param section section of article
     * @param url URL of article
     */
    public Article(String title, String author, long date, String section, String url){
        mTitle = title;
        mAuthor = author;
        mDate = date;
        mSection = section;
        mUrl= url;
    }

    /**Getters
     */

    public String getTitle(){
        return mTitle;
    }

    public String getAuthor(){
        return mAuthor;
    }

    public long getDate(){
        return mDate;
    }

    public String getSection(){
        return mSection;
    }

    public String getUrl(){
        return mUrl;
    }

}
