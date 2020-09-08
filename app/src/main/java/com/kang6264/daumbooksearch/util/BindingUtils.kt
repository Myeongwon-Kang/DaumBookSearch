package com.kang6264.daumbooksearch.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.kang6264.daumbooksearch.R
import java.text.DecimalFormat

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("authorsTitle")
fun bindAuthorsTitle(view: TextView, authors: List<String>?) {
    if (!authors.isNullOrEmpty()) {
        view.text = view.context.getString(R.string.author_format, authors.joinToString())
    }
}

@BindingAdapter("authors")
fun bindAuthors(view: TextView, authors: List<String>?) {
    if (!authors.isNullOrEmpty()) {
        view.text = authors.joinToString()
    }
}

@BindingAdapter("datetime")
fun bindDatetime(view: TextView, datetime: String?) {
    if (!datetime.isNullOrEmpty()) {
        view.text = DateUtil.convertDate(
            datetime,
            com.kang6264.daumbooksearch.util.DateFormat.ISO8601,
            com.kang6264.daumbooksearch.util.DateFormat.NORMAL
        )
    }
}

@BindingAdapter("price", "salePrice")
fun bindPriceAndPercentage(view: TextView, price: Int, salePrice: Int) {
    if (price > 0 && salePrice > 0) {
        var percentage = (price.toDouble() - salePrice.toDouble()) / price.toDouble() * 100.0
        val form = DecimalFormat("0.##")
        val numberFormat = DecimalFormat("###,###")
        view.text = view.context.getString(
            R.string.sale_detail_price_format,
            numberFormat.format(salePrice),
            form.format(percentage) + "%"
        )
    }
}

@BindingAdapter("salePrice")
fun bindSalePrice(view: TextView, salePrice: Int) {
    val numberFormat = DecimalFormat("###,###")
    view.text = view.context.getString(
        R.string.sale_price_format,
        numberFormat.format(salePrice)
    )
}