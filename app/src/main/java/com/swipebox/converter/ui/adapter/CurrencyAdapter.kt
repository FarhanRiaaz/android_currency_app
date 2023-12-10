package com.swipebox.converter.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.swipebox.converter.R
import com.swipebox.converter.data.model.RateItem
import com.swipebox.converter.util.MyExtension.toFormattedDecimal

class CurrencyAdapter(private var currencyList: ArrayList<RateItem>) :
    RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {
    var multiplyerValue = 1

    class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val currencyTitleTv: TextView = itemView.findViewById(R.id.tvCurrencyTitle)
        val currencyValueTv: TextView = itemView.findViewById(R.id.tvCurrencyValue)
        val currencyEurToTargetTv: TextView = itemView.findViewById(R.id.tvEurToTarget)
        val currencyTargetToEurTv: TextView = itemView.findViewById(R.id.tvTargetToEur)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val viewLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.item_rate_card, parent, false
        )
        return CurrencyViewHolder(viewLayout)
    }

    override fun getItemCount(): Int {
        return currencyList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currentItemCurrency = currencyList

        holder.currencyTitleTv.text = currentItemCurrency.get(position).key
        holder.currencyValueTv.text =
            (currentItemCurrency.get(position).value * multiplyerValue).toString()
                .toFormattedDecimal()
        holder.currencyEurToTargetTv.text =
            "1 EUR = ${currentItemCurrency.get(position).value.toString().toFormattedDecimal()}"
        holder.currencyTargetToEurTv.text = "1 ${currentItemCurrency.get(position).key} = " + "${
            1 / currentItemCurrency.get(position).value
        }".toFormattedDecimal()


    }

    fun updateList(conversionRates: List<RateItem>) {
        currencyList.clear()
        currencyList.addAll(conversionRates)
        if (currencyList.isNotEmpty()) currencyList.removeAt(0)
        notifyDataSetChanged()
    }

    fun updateMultiplyer(value: Int) {
        multiplyerValue = value
        notifyDataSetChanged()
    }

}