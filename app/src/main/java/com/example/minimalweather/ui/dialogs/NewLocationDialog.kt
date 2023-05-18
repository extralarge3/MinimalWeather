package com.example.minimalweather.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.minimalweather.databinding.DialogNewPlaceBinding

class NewLocationDialog : DialogFragment() {

    private lateinit var listener: NewLocationListener

    interface NewLocationListener{
        fun onPlaceAdded(place: String)
    }

    companion object {
        const val TAG = "NewPlaceDialogFragment"
    }

    private var _binding: DialogNewPlaceBinding? = null
    private val binding get() = _binding!!


    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? NewLocationListener
            ?: throw RuntimeException("Activity must implement the NewShoppingItemDialogListener interface!")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogNewPlaceBinding.inflate(LayoutInflater.from(context))

        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        binding.btnOk.setOnClickListener {

            binding.etPlace.editText?.let {
                listener.onPlaceAdded(it.text.toString())
            }
            dismiss()
        }
        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}