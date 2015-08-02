package com.niko.hannes.nikomobileapp.ui.home.edit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.niko.hannes.nikomobileapp.R;
import com.niko.hannes.nikomobileapp.ui.home.MenuItemVM;

public class AvatarEditActivity  extends AppCompatActivity {

    private static final String EXTRA_TITLE = "AvatarEditActivity.extraImage";
    private static final String EXTRA_AVATAR = "AvatarEditActivity.extraAvatar";

    public static void navigate(AppCompatActivity activity, MenuItemVM viewModel) {
        Intent intent = new Intent(activity, AvatarEditActivity.class);
        intent.putExtra(EXTRA_TITLE, viewModel.title);
        intent.putExtra(EXTRA_AVATAR, viewModel.avatar);

        ActivityCompat.startActivity(activity, intent, Bundle.EMPTY);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avatar_edit_activity);

        setUpGridView();
    }

    private boolean isInEditMode() {
        final Intent intent = getIntent();
        boolean edit = false;
        if (intent != null) {
            edit = intent.getBooleanExtra(EXTRA_TITLE, false);
        }
        return edit;
    }

    private void setUpGridView() {
        mAvatarGrid = (GridView) findViewById(R.id.avatars);
        mAvatarGrid.setAdapter(new AvatarAdapter(this));
        mAvatarGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSelectedAvatarView = view;
                mSelectedAvatar = Avatar.values()[position];
            }
        });
        mAvatarGrid.setNumColumns(calculateSpanCount());
        mAvatarGrid.setItemChecked(mSelectedAvatar.ordinal(), true);
    }



}