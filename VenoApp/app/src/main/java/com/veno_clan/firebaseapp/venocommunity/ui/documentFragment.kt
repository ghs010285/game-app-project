package com.veno_clan.firebaseapp.venocommunity.ui

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.veno_clan.firebaseapp.venocommunity.R
import com.veno_clan.firebaseapp.venocommunity.WriteActivity
import com.veno_clan.firebaseapp.venocommunity.model.DocumentModel
import kotlinx.android.synthetic.main.document_fragment.view.*
import kotlinx.android.synthetic.main.item_document.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class documentFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener{

    var firestore : FirebaseFirestore? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        firestore = FirebaseFirestore.getInstance()

        var mainView =inflater.inflate(R.layout.document_fragment, container, false)
        mainView.document_recyclerView.adapter = DocumentRecyclerViewAdpater()
        mainView.document_recyclerView.layoutManager = LinearLayoutManager(activity)

        return mainView
    }

    override fun onRefresh() {

    }

    inner class DocumentRecyclerViewAdpater : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        val documentList : ArrayList<DocumentModel> = ArrayList()
        val documentUidList : ArrayList<String> = ArrayList()
        init {
//            var uid = FirebaseAuth.getInstance().currentUser?.uid

            firestore?.collection("document")?.orderBy("timestamp", Query.Direction.DESCENDING )?.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                documentList.clear()
                documentUidList.clear()

                for (snapshot in querySnapshot!!.documents){
                    val item = snapshot.toObject(DocumentModel::class.java)
                    documentList.add(item!!)
                    documentUidList.add(snapshot.id)
                }
                notifyDataSetChanged()
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.item_document, parent, false)
            return CustomViewHolder(view)
        }
        inner class CustomViewHolder(view: View?) : RecyclerView.ViewHolder(view!!)

        override fun getItemCount(): Int {
            return documentList.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            var timeStamp = SimpleDateFormat("yyyy년MM월dd일 HH시mm분").format(Date())
            val viewHolder = (holder as CustomViewHolder).itemView


            viewHolder.document_username.text = documentList[position].userName //유저이름 가져오기
            viewHolder.document_title.text = documentList[position].wrtie_title //제목 가져오기
            viewHolder.document_context.text = documentList[position].content //내용 가져오기
            viewHolder.document_time.text = documentList[position].timestamp.toString() //시간 가져오기
        }
    }





    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if(id == R.id.Wirte_doecument){
            startActivity(Intent(context, WriteActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

}