package app.auaf.cconverter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActorsDataAdapter extends RecyclerView.Adapter<ActorsDataAdapter.ActorsViewHolder> {


    ArrayList<Actor> actorArrayList;
    Context context;

    public ActorsDataAdapter(Context context,ArrayList<Actor> actorArrayList) {
        this.actorArrayList = actorArrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public ActorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.item_row_actor,null,false);
        return new  ActorsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorsViewHolder holder, int position) {

        holder.tvPhoneActor.setText(actorArrayList.get(position).getPhone());
        holder.tvNameActor.setText(actorArrayList.get(position).getName());
        Glide.with(context).load(actorArrayList.get(position).getImage()).into(holder.idPhotoActor);
    }

    @Override
    public int getItemCount() {
        return actorArrayList.size();
    }

    public  class ActorsViewHolder extends RecyclerView.ViewHolder{
        CircleImageView idPhotoActor;
        TextView tvNameActor,tvPhoneActor;

        public ActorsViewHolder(@NonNull View itemView) {
            super(itemView);

            idPhotoActor=itemView.findViewById(R.id.idPhotoActor);
            tvNameActor=itemView.findViewById(R.id.idNnameActor);
            tvPhoneActor=itemView.findViewById(R.id.idPhoneActor);
        }
    }
}
