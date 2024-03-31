package com.betelgeuse.corp.mycards.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.betelgeuse.corp.mycards.Model.Card
import com.betelgeuse.corp.mycards.R

class CardAdapter(
    private val cardList: List<Card>,
    private val getActivity: MainActivity
): RecyclerView.Adapter<CardAdapter.MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return MyViewHolder(view)
    }

    private var eyeButtonClickListener: OnEyeButtonClickListener? = null
    interface OnEyeButtonClickListener {
        fun onEyeButtonClick(position: Int)
    }
    fun setOnEyeButtonClickListener(listener: OnEyeButtonClickListener) {
        eyeButtonClickListener = listener
    }

    private var trashButtonClickListener: OnTrashButtonClickListener? = null
    interface OnTrashButtonClickListener{
        fun onTrashButtonClick(position: Int)
    }
    fun setOnTrashButtonClickListener(listener: OnTrashButtonClickListener){
        trashButtonClickListener = listener
    }

    private var infoButtonClickListener: InfoClickListener? = null
    interface InfoClickListener {
        fun onInfoButtonClick(position: Int)
    }
    fun setInfoButtonClickListener(listener: InfoClickListener) {
        infoButtonClickListener = listener
    }


    override fun getItemCount(): Int {
        return cardList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.quantity.text = cardList[position].quantityPoints
        holder.cashBack.text = cardList[position].cashBackProcent
        holder.level.text = cardList[position].levelPerson
        holder.eyeButton.setOnClickListener {
            eyeButtonClickListener?.onEyeButtonClick(position)
        }
        holder.trashButton.setOnClickListener {
            trashButtonClickListener?.onTrashButtonClick(position)
        }
        holder.info.setOnClickListener {
            infoButtonClickListener?.onInfoButtonClick(position)
        }
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val quantity: TextView = itemView.findViewById(R.id.quantityPoints)
        val cashBack: TextView = itemView.findViewById(R.id.cashBackProcent)
        val level: TextView = itemView.findViewById(R.id.levelPerson)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
        val eyeButton: ImageButton = itemView.findViewById(R.id.eyeBTN)
        val trashButton: ImageButton = itemView.findViewById(R.id.trashBTN)
        val info: Button = itemView.findViewById(R.id.infoBtn)

    }
}
