package com.gmmp.easylearn.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmmp.easylearn.R
import com.gmmp.easylearn.activity.LoginActivity
import com.gmmp.easylearn.activity.MeuCanalActivity
import com.gmmp.easylearn.model.Usuario
import com.gmmp.easylearn.model.ViewDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_perfil.view.*
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass.
 */
class MinhaContaFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)

        iniciar(view)

        return view
    }

    private fun iniciar(view: View) {
        view.menuMeuCanal.setOnClickListener { startActivity(Intent(activity, MeuCanalActivity::class.java)) }

        view.menuEditarConta.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.frameContainer, EditarPerfilFragment())
            transaction?.commit()
        }

        view.menuCompatilharEasyCash.setOnClickListener {
            val myIntent = Intent(Intent.ACTION_SEND)
            myIntent.type = "type/plain"
            val shareBody = "EasyLearning: Baixe e ganhe promoções na compra de qualquer curso usando o código abaixo, não perca!"
            val shareSub = id
            myIntent.putExtra(Intent.EXTRA_SUBJECT, shareBody)
            myIntent.putExtra(Intent.EXTRA_TEXT, shareSub)
            startActivity(Intent.createChooser(myIntent, "Compartilhar EasyCash"))
        }

        view.menuSair.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            activity?.startActivity(Intent(context, LoginActivity::class.java))
            activity?.finish()
        }


    }

}
