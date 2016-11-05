package shecklock.example.com.cleanration;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by sherlock on 4/11/16.
 */
public class fragment_notifications extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_notification, container, false);
        LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.ll);
        return rootView;
    }
}
