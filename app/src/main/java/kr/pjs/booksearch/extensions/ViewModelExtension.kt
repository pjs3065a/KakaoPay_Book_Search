package kr.pjs.booksearch.extensions

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import kr.pjs.booksearch.data.ARGS
import kr.pjs.booksearch.data.remote.api.SearchDataSourceImpl
import kr.pjs.booksearch.data.remote.repository.SearchRepositoryImpl
import kr.pjs.booksearch.utils.resolveClass
import kr.pjs.booksearch.view.ui.searchdetail.SearchDetailViewModel
import kr.pjs.booksearch.view.ui.searchinput.SearchInputViewModel

fun Fragment.getViewModelFactory(): ViewModelFactory {
    return ViewModelFactory(
        owner = this,
        defaultArgs = arguments,
        context = requireContext()
    )
}

fun ComponentActivity.getViewModelFactory(): ViewModelFactory {
    return ViewModelFactory(
        owner = this,
        intent = intent,
        context = this
    )
}

@Suppress("UNCHECKED_CAST", "CanBeParameter")
class ViewModelFactory constructor(
    private val owner: SavedStateRegistryOwner,
    private val defaultArgs: Bundle? = null,
    private val intent: Intent? = null,
    private val context: Context
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = with(modelClass) {
        when (this) {
            resolveClass<SearchInputViewModel>() -> {
                SearchInputViewModel(SearchRepositoryImpl(SearchDataSourceImpl()))
            }

            resolveClass<SearchDetailViewModel>() -> {
                SearchDetailViewModel(defaultArgs?.getParcelable(ARGS))
            }

            else -> newInstance()
        }
    } as T
}