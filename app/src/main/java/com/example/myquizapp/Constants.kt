package com.example.myquizapp

object Constants {

    const val USER_NAME : String = "userName"
    const val TOTAL_QUESTIONS : String = "totalQuestions"
    const val CORRECT_ANSWERS : String = "correctAnswers"

    fun getQuestions() : ArrayList<Question>{
        val questionList = ArrayList<Question>()
        val question : String = "What is the arabic word for the following animal?"

        //1
        val que1 = Question(
            1,
            question,
            R.drawable.dog,
            "قطة", "نور", "كلب", "أمل",
            2
        )
        questionList.add(que1)

        //2
        val que2 = Question(
            2,
            question,
            R.drawable.cat,
            "قطة", "أمل", "عشق", "يبتسم",
            0
        )
        questionList.add(que2)

        //3
        val que3 = Question(
            3,
            question,
            R.drawable.tiger,
            "يبتسم", "نمر", "أمل", "سعادة",
            1
        )
        questionList.add(que3)

        //4
        val que4 = Question(
            4,
            question,
            R.drawable.lizard,
            "سعادة", "نور", "فردوس", "سحلية",
            3
        )
        questionList.add(que4)

        //5
        val que5 = Question(
            5,
            question,
            R.drawable.snake,
            "ثعبان", "متلألئة", "شوق", "افتنان",
            0
        )
        questionList.add(que5)

        //6
        val que6 = Question(
            6,
            question,
            R.drawable.bear,
            "شفيعي", "يتحمل", "افتنان", "أزهر",
            1
        )
        questionList.add(que6)

        //7
        val que7 = Question(
            7,
            question,
            R.drawable.camel,
            "شفيعي", "جمل", "شوق", "افتنان",
            1
        )
        questionList.add(que7)

        //8
        val que8 = Question(
            8,
            question,
            R.drawable.fish,
            "نمر", "قطة", "أمل", "سمك",
            3
        )
        questionList.add(que8)

        //9
        val que9 = Question(
            9,
            question,
            R.drawable.crocodile,
            "نمر", "شفيعي", "تمساح", "شوق",
            2
        )
        questionList.add(que9)

        return questionList
    }
}