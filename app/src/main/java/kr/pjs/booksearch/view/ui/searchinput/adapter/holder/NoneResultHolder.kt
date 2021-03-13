package kr.pjs.booksearch.view.ui.searchinput.adapter.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.pjs.booksearch.databinding.HolderSearchNoneBinding

class NoneResultHolder private constructor(mBinding: HolderSearchNoneBinding) :
    RecyclerView.ViewHolder(mBinding.root) {
    companion object {
        fun create(parent: ViewGroup): NoneResultHolder {
            return NoneResultHolder(
                HolderSearchNoneBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}