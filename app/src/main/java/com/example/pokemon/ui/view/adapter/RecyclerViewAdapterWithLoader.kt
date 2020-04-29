package com.example.pokemon.ui.view.adapter

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import com.example.pokemon.utils.GeneralUtils.isNullOrEmpty
import com.example.pokemon.utils.RecyclerViewUtils.getVisibleViewHolders
import com.example.pokemon.utils.UIUtils.setProgressBarColor

abstract class RecyclerViewAdapterWithLoader(protected val context: Context) :
    RecyclerView.Adapter<RecyclerViewAdapterWithLoader.ViewHolder>() {
    protected var updatingList = false
    protected val inflater: LayoutInflater
    protected val resources: Resources
    protected var loaderColor: Int? = null
    protected var noDataTextColor: Int? = null
    protected var noDataTextValue: String? = null
    protected var emptyLoadingTextValue: String? = null
    protected var emptyLoadingTextColor: Int? = null
    fun setNoDataText(text: String?) {
        noDataTextValue = text
    }

    fun setEmptyLoadingText(text: String?) {
        emptyLoadingTextValue = text
    }

    fun setEmptyLoadingTextColor(color: Int) {
        emptyLoadingTextColor = color
    }

    fun setLoaderColor(color: Int) {
        loaderColor = color
    }

    fun setNoDataTextColor(color: Int) {
        noDataTextColor = color
    }

    fun setUpdating(isUpdating: Boolean) {
        if (this.updatingList != isUpdating) {
            this.updatingList = isUpdating
            try {
                notifyDataSetChanged()
            } catch (var3: Exception) {
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val count = mGetItemCount()
        return if (count == 0) {
            if (updatingList) -2 else -1
        } else {
            if (updatingList && count > 0 && position + 1 == this.itemCount) -3 else mGetItemViewType(
                position
            )
        }
    }

    override fun onViewRecycled(holder: ViewHolder) {
        holder.unbind()
    }

    protected open fun mGetItemViewType(position: Int): Int {
        return 0
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return when (viewType) {
            -3 -> onCreateLoadingItemViewHolder(inflater, parent)
            -2 -> onCreateEmptyLoadingViewHolder(inflater, parent)
            -1 -> onCreateEmptyViewHolder(inflater, parent)
            else -> mOnCreateViewHolder(inflater, parent, viewType)
        }
    }

    protected fun onCreateEmptyViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ViewHolder {
        return EmptyViewHolder(
            onCreateEmptyItemView(inflater, parent)
        )
    }

    protected fun onCreateEmptyLoadingViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ViewHolder {
        return EmptyLoadingViewHolder(
            onCreateEmptyLoadingItemView(inflater, parent)
        )
    }

    protected fun onCreateLoadingItemViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ViewHolder {
        return LoadingViewHolder(
            onCreateLoadingView(inflater, parent)
        )
    }

    protected open fun mOnCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return onCreateDefaultViewHolder(inflater, parent)!!
    }

    protected fun onCreateDefaultViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ViewHolder? {
        return null
    }

    protected fun onCreateEmptyLoadingItemView(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): View {
        val view =
            inflater.inflate(R.layout.layout_recycler_empty_loading, parent, false)
        if (loaderColor != null) {
            setProgressBarColor(
                view.findViewById<View>(R.id.progress_middle) as ProgressBar,
                loaderColor!!
            )
        }
        if (emptyLoadingTextColor != null) {
            (view.findViewById<View>(R.id.text) as TextView).setTextColor(emptyLoadingTextColor!!)
        }
        if (emptyLoadingTextValue != null) {
            (view.findViewById<View>(R.id.text) as TextView).text = emptyLoadingTextValue
        }
        view.findViewById<View>(R.id.text).visibility =
            if (isNullOrEmpty(emptyLoadingTextValue)) View.GONE else View.VISIBLE
        return view
    }

    protected fun onCreateEmptyItemView(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): View {
        val view =
            inflater.inflate(R.layout.layout_recycler_empty_view, parent, false)
        if (noDataTextValue != null) {
            (view.findViewById<View>(R.id.text) as TextView).text = noDataTextValue
        }
        if (noDataTextColor != null) {
            (view.findViewById<View>(R.id.text) as TextView).setTextColor(noDataTextColor!!)
        }
        return view
    }

    protected fun onCreateLoadingView(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): View {
        val view =
            inflater.inflate(R.layout.layout_recycler_loading, parent, false)
        if (loaderColor != null) {
            setProgressBarColor(
                view.findViewById<View>(R.id.progress) as ProgressBar,
                loaderColor!!
            )
        }
        return view
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        val count = mGetItemCount()
        return if (count == 0) 1 else count + if (updatingList) 1 else 0
    }

    abstract fun mGetItemCount(): Int
    fun unbindAll(recyclerView: RecyclerView?) {
        val vhs =
            getVisibleViewHolders(recyclerView!!)
        val var3: Iterator<*> = vhs.iterator()
        while (var3.hasNext()) {
            val vh = var3.next() as RecyclerView.ViewHolder
            if (vh is ViewHolder) {
                vh.unbind()
            }
        }
    }

    class LoadingViewHolder(itemView: View?) :
        ViewHolder(itemView)

    class EmptyLoadingViewHolder(itemView: View?) :
        ViewHolder(itemView)

    class EmptyViewHolder(itemView: View?) :
        ViewHolder(itemView)

    abstract class ViewHolder(itemView: View?) :
        RecyclerView.ViewHolder(itemView!!), View.OnClickListener {
        open fun bind(position: Int) {}
        fun unbind() {}
        override fun onClick(v: View) {}
    }

    companion object {
        protected const val DEFAULT_ITEM = 0
        protected const val EMPTY_ITEM = -1
        protected const val EMPTY_LOADING_ITEM = -2
        protected const val LOADING_ITEM = -3
    }

    init {
        inflater = LayoutInflater.from(context)
        resources = context.resources
    }
}