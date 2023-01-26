package com.example.unsplash_app_tutorial

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.unsplash_app_tutorial.retrofit.RetrofitClient
import com.example.unsplash_app_tutorial.retrofit.RetrofitManager
import com.example.unsplash_app_tutorial.utils.Constant.TAG
import com.example.unsplash_app_tutorial.utils.RESPONSE_STATE
import com.example.unsplash_app_tutorial.utils.SEARCH_TYPE
import com.example.unsplash_app_tutorial.utils.onMyTextChanged
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_button_search.*

class MainActivity : AppCompatActivity() {
    private var currentSearchType: SEARCH_TYPE = SEARCH_TYPE.PHOTO // 검색 타입

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "MainActivity-onCreate() called")


        //radio group 가벼오기
        search_term_radio_group.setOnCheckedChangeListener { _, checkedId ->

            // switch 문
            when(checkedId) {
                R.id.photo_search_radio_btn -> {
                    Log.d(TAG, "사진검색 버튼 클릭!")
                    search_term_text_layout.hint = "사진검색"
                    search_term_text_layout.startIconDrawable = resources.getDrawable(R.drawable.ic_photo_library_black_24dp, resources.newTheme())
                    this.currentSearchType = SEARCH_TYPE.PHOTO
                }

                R.id.user_search_radio_btn -> {
                    Log.d(TAG, "사용자검색 버튼 클릭!")
                    search_term_text_layout.hint = "사용자검색"
                    search_term_text_layout.startIconDrawable = resources.getDrawable(R.drawable.ic_person_black_24dp, resources.newTheme())
                    this.currentSearchType = SEARCH_TYPE.USER
                }
            }
            Log.d(TAG, "MainActivity - OnCheckedChanged() called / currentSearchType : $currentSearchType")
        }
        search_term_edit_text.onMyTextChanged {

            // 입력된 글자가 하나라도 있다면
            if(it.toString().count() > 0){
                // 검색 버튼을 보여준다.
                frame_search_btn.visibility = View.VISIBLE
                search_term_text_layout.helperText = " "

                // 스크롤뷰를 올린다.
                main_scrollView.scrollTo(0,300)

            } else {
                frame_search_btn.visibility = View.INVISIBLE
                search_term_text_layout.helperText = "검색어를 입력해주세요"
            }

            if (it.toString().count() == 12) {
                Log.d(TAG, "MainActivity - 에러 띄우기 ")
                Toast.makeText(this, "검색어는 12자 까지만 입력 가능합니다.", Toast.LENGTH_SHORT).show()
            }

        }
        // 버튼 클릭시
        btn_search.setOnClickListener {

            Log.d(TAG, "MainActivity - 검색 버튼이 클릭되었다. / currentSearchType : $currentSearchType")
            when(currentSearchType) {
                SEARCH_TYPE.PHOTO-> {
                    val userSearchInput = search_term_edit_text.text.toString()
                    RetrofitManager.instance.searchPhotos(
                        searchTerm = userSearchInput,
                        completion = { responseState, responseArrayList ->
                            when (responseState) {
                                RESPONSE_STATE.OKAY -> {
                                    Log.d(TAG, "api 호출에 성공하였습니다 $responseArrayList")
                                    //Arraylist 전달하기
                                    //bundle에 넣어서 전달하기
                                    val intent = Intent(this, photoActivity::class.java)

                                    val bundle = Bundle()
                                    bundle.putSerializable("array_list", responseArrayList)
                                    intent.putExtra("bundle_array", bundle)
                                    intent.putExtra("searchTerm", userSearchInput)

                                    startActivity(intent)

                                }
                                RESPONSE_STATE.FAIL -> {
                                    Toast.makeText(this, "api 호출 오류 입니다", Toast.LENGTH_SHORT).show()
                                    Log.d(TAG, "api 호충에 실패 하였습니다")
                                }
                            }
                        })
                    this.handleSearchButtonUi()
                }
                SEARCH_TYPE.USER->{
                    val searchTermInput = search_term_edit_text.text.toString()
                    RetrofitManager.instance.searchUser(searchTerm = searchTermInput,completion = {
                        responseState, responseBody->
                        when (responseState) {
                            RESPONSE_STATE.OKAY -> {
                                Log.d(TAG, "api 호출에 성공하였습니다 $responseBody")
                                //Arraylist 전달하기
                                //bundle에 넣어서 전달하기
//                                val intent = Intent(this, photoActivity::class.java)
//
//                                val bundle = Bundle()
//                                bundle.putSerializable("array_list", responseArrayList)
//                                intent.putExtra("bundle_array", bundle)
//                                intent.putExtra("searchTerm", userSearchInput)

 //                               startActivity(intent)

                            }
                            RESPONSE_STATE.FAIL -> {
                                Toast.makeText(this, "api 호출 오류 입니다", Toast.LENGTH_SHORT).show()
                                Log.d(TAG, "api 호충에 실패 하였습니다")
                            }
                        }
                    } )
                }
            }
        }

    }

    private fun handleSearchButtonUi(){

        btn_progress.visibility = View.VISIBLE

        btn_search.text = ""

        Handler().postDelayed({
            btn_progress.visibility = View.INVISIBLE
            btn_search.text = "검색"
        }, 1500)

    }
}