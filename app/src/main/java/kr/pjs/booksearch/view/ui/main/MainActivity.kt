package kr.pjs.booksearch.view.ui.main

import kr.pjs.booksearch.R
import kr.pjs.booksearch.databinding.ActivityMainBinding
import kr.pjs.booksearch.view.base.DataBindingActivityWithoutVM

/**
 * 메인 액티비티 클래스
 * 싱글 액티비티 구조로 navigation 이용해 화면 전환을 한다.
 */
class MainActivity : DataBindingActivityWithoutVM<ActivityMainBinding>(R.layout.activity_main) {}