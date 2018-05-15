package com.noah.newsreport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class ArticleAdapter extends ArrayAdapter<Article> {

    private static final String LOCATION_SEPARATOR = "T";
    private static final String LOG_TAG = ArticleAdapter.class.getName();


    public ArticleAdapter (Activity activity, ArrayList<Article> articles) {
        super(activity, 0, articles);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Find the Article at the given position in the list of earthquakes
        Article currentArticle = getItem(position);

        TextView title = (TextView) listItemView.findViewById(R.id.title);
        title.setText(currentArticle.getTitle());

        //TextView author = (TextView) listItemView.findViewById(R.id.author);
       // author.setText(currentArticle.getAuthor());

        TextView date = (TextView) listItemView.findViewById(R.id.date);
        // Create a new Date object from the Date of the article

        try {
            Date dateObject = parseJSONDate(currentArticle.getDate());
            date.setText(formatDate(dateObject));
        } catch (ParseException e) {
            Log.e(LOG_TAG,"Date is not valid.",e);
        }


        TextView section = (TextView) listItemView.findViewById(R.id.section);
        section.setText(currentArticle.getSection());

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }

    /**
     * Accepts string and returns date
     * taken from http://www.java2s.com/Code/Java/Data-Type/ISO8601dateparsingutility.htm
     */
    public static Date parseJSONDate( String input ) throws java.text.ParseException {

        //NOTE: SimpleDateFormat uses GMT[-+]hh:mm for the TZ which breaks
        //things a bit.  Before we go on we have to repair this.
        SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ssz" );

        //this is zero time so we need to add that TZ indicator for
        if ( input.endsWith( "Z" ) ) {
            input = input.substring( 0, input.length() - 1) + "GMT-00:00";
        } else {
            int inset = 6;

            String s0 = input.substring( 0, input.length() - inset );
            String s1 = input.substring( input.length() - inset, input.length() );

            input = s0 + "GMT" + s1;
        }

        return df.parse( input );

    }


    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        return dateFormat.format(dateObject);
    }

}
