package com.example.myquizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var questionIndex : Int = 0
    private var questionsList : ArrayList<Question>? = null
    private var selectedOptionIndex : Int = 10

    private var mUserName : String? = null
    private var mCorrectAnswers : Int = 0

    private var progressBar : ProgressBar? = null
    private var tvProgressBar : TextView? = null
    private var tvQuestion : TextView? = null
    private var ivImage : ImageView? = null

    private var tvOptionOne : TextView? = null
    private var tvOptionTwo : TextView? = null
    private var tvOptionThree : TextView? = null
    private var tvOptionFour : TextView? = null

    private var btnSubmit : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        progressBar = findViewById(R.id.progressBar)
        tvProgressBar = findViewById(R.id.tv_progress)
        tvQuestion = findViewById(R.id.tv_question)
        ivImage = findViewById(R.id.iv_image)
        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.btn_submit)

        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)

        btnSubmit?.setOnClickListener(this)

        setQuestion()

        //defaultOptionsView()
    }

    private fun setQuestion() {
        defaultOptionsView()
        questionsList = Constants.getQuestions()

        val question: Question = questionsList!![questionIndex]

        ivImage?.setImageResource(question.image)
        progressBar?.progress = questionIndex + 1
        tvProgressBar?.text = "${questionIndex + 1}/${questionsList!!.size}"
        tvQuestion?.text = question.question
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour

        if(questionIndex + 1== questionsList!!.size){
            btnSubmit?.text = "FINISH"
        }else{
            btnSubmit?.text = "NEXT"
        }
    }

    private fun defaultOptionsView(){

        val optionsList = ArrayList<TextView>()

        tvOptionOne?.let { optionsList.add(0, it) }
        tvOptionTwo?.let { optionsList.add(1, it) }
        tvOptionThree?.let { optionsList.add(2, it) }
        tvOptionFour?.let { optionsList.add(3, it) }

        for(option in optionsList){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this,
                R.drawable.default_option_border_bg)
        }
    }

    private fun selectedOptionsView(tv : TextView, selectedOptionNum : Int){

        defaultOptionsView()

        selectedOptionIndex = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))

        tv.setTypeface(tv.typeface, Typeface.BOLD)

        tv.background = ContextCompat.getDrawable(this,
            R.drawable.selected_option_border_bg
        )
    }

    override fun onClick(view: View?) {

        when(view?.id){

            R.id.tv_option_one -> {
                tvOptionOne?.let { selectedOptionsView(it, 0) }
            }

            R.id.tv_option_two -> {
                tvOptionTwo?.let { selectedOptionsView(it, 1) }
            }

            R.id.tv_option_three -> {
                tvOptionThree?.let { selectedOptionsView(it, 2) }
            }

            R.id.tv_option_four -> {
                tvOptionFour?.let { selectedOptionsView(it, 3) }
            }

            R.id.btn_submit -> {
                if (selectedOptionIndex == 10){
                    questionIndex++

                    when{
                        questionIndex + 1 <= questionsList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, questionsList?.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
                else{
                    val question = questionsList?.get(questionIndex)
                    if(question!!.correctAnswerIndex != selectedOptionIndex){
                        answerView(selectedOptionIndex, R.drawable.wrong_option_border_bg)
                    }
                    else{
                        mCorrectAnswers++
                    }
                        answerView(question.correctAnswerIndex, R.drawable.correct_option_border_bg)
                }

                if(questionIndex + 1== questionsList!!.size){
                    btnSubmit?.text = "FINISH"
                }else{
                    btnSubmit?.text = "NEXT"
                }

                selectedOptionIndex = 10
            }
        }
    }

    private fun answerView(answerIndex : Int, drawable : Int){
        when(answerIndex){
            0 -> {
                tvOptionOne?.background = ContextCompat.getDrawable(this, drawable)
            }
            1 -> {
                tvOptionTwo?.background = ContextCompat.getDrawable(this, drawable)
            }
            2 -> {
                tvOptionThree?.background = ContextCompat.getDrawable(this, drawable)
            }
            3 -> {
                tvOptionFour?.background = ContextCompat.getDrawable(this, drawable)
            }
        }
    }
}