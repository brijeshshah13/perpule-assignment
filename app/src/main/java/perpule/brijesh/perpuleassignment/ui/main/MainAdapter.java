package perpule.brijesh.perpuleassignment.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import perpule.brijesh.perpuleassignment.R;
import perpule.brijesh.perpuleassignment.models.Audio;
import perpule.brijesh.perpuleassignment.ui.audio.AudioActivity;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Audio> mAudios;
    private Context mContext;

    public MainAdapter(List<Audio> audios, Context context) {
        this.mAudios = audios;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_audios,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mDescTV.setText(mAudios.get(position).getDesc());
        holder.mAudioCV.setOnClickListener(v -> {
            MainPresenter.position = position;
            mContext.startActivity(new Intent(mContext, AudioActivity.class));
        });
    }

    @Override
    public int getItemCount() {
        return mAudios.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mDescTV;
        CardView mAudioCV;

        public ViewHolder(View itemView) {
            super(itemView);
            mDescTV = itemView.findViewById(R.id.tv_desc);
            mAudioCV = itemView.findViewById(R.id.cv_audio);
        }
    }

}
