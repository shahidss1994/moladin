package com.moladin.com.moladin.page.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.moladin.com.moladin.commom.BindableAdapter
import com.moladin.com.moladin.commom.diffCallback
import com.moladin.com.moladin.commom.viewState.ItemRepoViewState
import com.moladin.com.moladin.page.main.viewModel.MainViewModel
import com.moladin.databinding.ItemRepoListBinding

class RepoListAdapter(private val viewModel: MainViewModel) :
    RecyclerView.Adapter<RepoListAdapter.ListRecyclerViewHolder<ItemRepoViewState, MainViewModel>>(),
    BindableAdapter<ItemRepoViewState> {

    override var items: List<ItemRepoViewState> by diffCallback(emptyList()) { o, n ->
        o.id == n.id
    }

    override fun getItemId(position: Int) = items[position].hashCode().toLong()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListRecyclerViewHolder<ItemRepoViewState, MainViewModel> {
        return ListRecyclerViewHolder.ListRecyclerSectionViewHolder(
            parent
        )
    }

    override fun onBindViewHolder(
        holder: ListRecyclerViewHolder<ItemRepoViewState, MainViewModel>,
        position: Int
    ) {
        holder.bind(items[position], viewModel)
    }

    sealed class ListRecyclerViewHolder<VIEW_STATE, in VIEW_MODEL : ViewModel>(
        view: View
    ) :
        RecyclerView.ViewHolder(view) {

        abstract fun bind(viewState: VIEW_STATE, viewModel: VIEW_MODEL)

        class ListRecyclerSectionViewHolder(val binding: ItemRepoListBinding) :
            ListRecyclerViewHolder<ItemRepoViewState, MainViewModel>(
                binding.root
            ) {

            companion object {
                operator fun invoke(parent: ViewGroup) =
                    ListRecyclerSectionViewHolder(
                        ItemRepoListBinding.inflate(
                            LayoutInflater.from(parent.context),
                            parent,
                            false
                        )
                    )
            }

            override fun bind(
                viewState: ItemRepoViewState,
                viewModel: MainViewModel
            ) {
                binding.viewModel = viewModel
                binding.viewState = viewState
            }

        }
    }

}