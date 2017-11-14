package io.futurestud.retrofit1.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ceo.futurestudretrofit.R;

import java.util.List;

import io.futurestud.retrofit1.api.model.GitHubRepo;

/**
 * Created by Jean Claude on 11/13/2017.
 */

public class GitHubRepoAdapter extends ArrayAdapter<GitHubRepo> {

    private Context context;
    private List<GitHubRepo> values;

    public GitHubRepoAdapter(Context context, List<GitHubRepo> values) {
        super(context, R.layout.list_item_pagination, values);

        this.context = context;
        this.values = values;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_pagination, parent, false);
        }

        TextView textView = row.findViewById(R.id.list_item_pagination_text);

        GitHubRepo item = values.get(position);
        String repoName = item.getName();
        String repoId = item.getId();
        textView.setText(repoId + " - " + repoName);

        return row;
    }
}
