package com.elvistezen.grupolucky.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.elvistezen.grupolucky.R;
import java.util.List;
public class PhraseAdapter extends RecyclerView.Adapter<PhraseAdapter.PhraseViewHolder> {
    private List<Phrase> phraseList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Phrase phrase);
    }

    public PhraseAdapter(List<Phrase> phraseList, OnItemClickListener listener) {
        this.phraseList = phraseList;
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public PhraseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new PhraseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhraseViewHolder holder, int position) {
        Phrase phrase = phraseList.get(position);
        holder.textViewAutor.setText(phrase.getAuthor());
        holder.textViewFrase.setText(phrase.getPhrase());

        // Set click listener for each phrase
        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(phrase));
    }

    @Override
    public int getItemCount() {
        return phraseList.size();
    }

    public static class PhraseViewHolder extends RecyclerView.ViewHolder {

        TextView textViewAutor;
        TextView textViewFrase;


        public PhraseViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAutor = itemView.findViewById(R.id.textViewTitulo);
            textViewFrase = itemView.findViewById(R.id.textViewFrase);  // Referencia al TextView del cuerpo
        }
    }
}
