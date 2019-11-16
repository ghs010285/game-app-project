package com.veno_clan.firebaseapp.venocommunity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.veno_clan.firebaseapp.venocommunity.model.DocumentModel
import kotlinx.android.synthetic.main.activity_write.*
import java.text.SimpleDateFormat
import java.util.*

class WriteActivity : AppCompatActivity() {

    var store : FirebaseFirestore? = null
    private var auth : FirebaseAuth? = null
    var user : FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        store = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.write_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if(id == R.id.ok_upload){ //만약 업로드 버튼을 누른다면 바로 밑에있는 if문 실행
            if(input_sosik.text.toString().isNullOrEmpty()){ //만약 내용이 비어있다면
                Toast.makeText(this, "내용을 입력해 주세요",Toast.LENGTH_SHORT).show() //"내용을 입력해 주세요" 안내문 띄어주기
            }else if (input_title.text.toString().isNullOrEmpty()){ //만약 제목도 비어있다면
                Toast.makeText(this, "제목을 입력해 주세요", Toast.LENGTH_SHORT).show()//"제목을 입력해 주세요"
            }else { //둘 다 글이 있다면
                upload() //업로드 이벤트 발생하기
                finish() //현재 액티비티를 종료하고 마지막에 열어놨던 MainActivity.xml을 열어준다
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun upload(){
            val timeStamp = SimpleDateFormat("yyyy년MM월dd일_HH시mm분ss초").format(Date())
            val documentModel = DocumentModel()
            documentModel.timestamp = System.currentTimeMillis()
            documentModel.userEmail = auth?.currentUser?.email
            documentModel.uid = auth?.currentUser?.uid
//            userModel.userName =
            documentModel.content = input_sosik.text.toString()
            documentModel.wrtie_title = input_title.text.toString()

            store?.collection("document")?.document(timeStamp)?.set(documentModel)
    }
}
