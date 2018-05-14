package com.noah.newsreport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ArticleAdapter extends ArrayAdapter<Article> {

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

        TextView author = (TextView) listItemView.findViewById(R.id.author);
        author.setText(currentArticle.getAuthor());

        TextView date = (TextView) listItemView.findViewById(R.id.date);
        // Create a new Date object from the Date of the article
        Date dateObject = new Date(currentArticle.getDate());
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current Article in that TextView
        date.setText(formattedDate);

        TextView section = (TextView) listItemView.findViewById(R.id.section);
        section.setText(currentArticle.getSection());

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        return dateFormat.format(dateObject);
    }

}
