package moi.fr.corentinogramme;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CardFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView picture;
        public TextView name;
        public TextView description;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_card, parent, false));
            picture = (ImageView) itemView.findViewById(R.id.card_image);
            name = (TextView) itemView.findViewById(R.id.card_title);
            description = (TextView) itemView.findViewById(R.id.card_text);
        }
    }

    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        private final String[] mPosts;
        private final String[] mPostsDesc;
        private final Drawable[] mPostsPictures;

        public ContentAdapter(Context context) {
            Resources resources = context.getResources();
            mPosts = resources.getStringArray(R.array.posts);
            mPostsDesc = resources.getStringArray(R.array.posts_descs);
            TypedArray a = resources.obtainTypedArray(R.array.posts_pictures);
            mPostsPictures = new Drawable[a.length()];
            for (int i = 0; i < mPostsPictures.length; i++) {
                mPostsPictures[i] = a.getDrawable(i);
            }
            a.recycle();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.picture.setImageDrawable(mPostsPictures[position]);
            holder.name.setText(mPosts[position]);
            holder.description.setText(mPostsDesc[position]);
        }

        @Override
        public int getItemCount() {
            return mPosts.length;
        }
}
}
