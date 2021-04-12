package com.example.neighbors.fragments

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.neighbors.NavigationListener
import com.example.neighbors.R
import com.example.neighbors.adapter.ListNeighborHandler
import com.example.neighbors.adapter.ListNeighborsAdapter
import com.example.neighbors.data.NeighborRepository
import com.example.neighbors.databinding.ListNeighborsFragmentBinding
import com.example.neighbors.models.Neighbor

/*
 * Cette classe est un fragment
 * Cette classe est aussi un ListNeighborHandler
 */

class ListNeighborsFragment : Fragment(), ListNeighborHandler {

    private lateinit var recyclerView: RecyclerView
    lateinit var binding: ListNeighborsFragmentBinding
    /**
     * Fonction permettant de définir une vue à attachée à un fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.list_neighbors_fragment, container, false)

        recyclerView = binding.root.findViewById(R.id.neighbors_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.liste_neighbor_fragment)
        }
        setData()
        onAddNeighbor()
    }

    private fun setData() {
        NeighborRepository.getInstance().getNeighbours().observe(
            viewLifecycleOwner,
            Observer {
                val adapter = ListNeighborsAdapter(it, this)
                recyclerView.adapter = adapter
            }
        )
    }

    private fun delete(neighbor: Neighbor) {
        NeighborRepository.getInstance().deleteNeighbor(neighbor)
    }

    override fun onDeleteNeibor(neighbor: Neighbor) {
        // Toast.makeText(context, "Woow", Toast.LENGTH_LONG).show()

        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setTitle(getString(R.string.confirm_delete))
                setMessage(getString(R.string.message_delete))
                setPositiveButton(
                    R.string.ok
                ) { _, _ ->
                    delete(neighbor)
                }
                setNegativeButton(
                    R.string.cancel
                ) { _, _ ->
                }
            }

            builder.create()
        }
        alertDialog?.show()
    }

    override fun onAddFavorite(neighbor: Neighbor) {
        NeighborRepository.getInstance().favoriteNeighbor(neighbor)
        // Toast.makeText(context, "Favoris ajouté", Toast.LENGTH_LONG).show()
    }

    override fun onWebsiteButton(neighbor: Neighbor) {
        val url = Uri.parse("http://${neighbor.webSite}")
        val i = Intent(Intent.ACTION_VIEW, url)
        startActivity(i)
    }

    override fun onAddNeighbor() {
        binding.addNeighborButton.setOnClickListener {
            (activity as? NavigationListener)?.let {
                it.showFragment(AddNeighborFragment())
            }
        }
    }
}
