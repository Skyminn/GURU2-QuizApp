package com.example.quizapp

object QuestionData {
    fun getQuestion(): ArrayList<Question> {
        val queList: ArrayList<Question> = arrayListOf()

        val q1 = Question(
            1,
            "9 X □ = 27 \n □ = ?",
            /*1,
            2,
            3,
            4 , */
            3
        )

        val q2 = Question(
            2,
            "□ X 6 = 42 \n □ = ?",
            /*1,
            2,
            3,
            4 , */
            7
        )

        val q3 = Question(
            3,
            "구슬 49개를 7개씩 주머니에 나누어 넣었다.\n 주머니는 총 몇 개 일까?",
            /*1,
            2,
            3,
            4 , */
            7
        )

        val q4 = Question(
            3,
            "2 X 4 = ?",
            /*1,
            2,
            3,
            4 , */
            8
        )

        val q5 = Question(
            3,
            "4 X □ = 20 \n □ = ?",
            /*1,
            2,
            3,
            4 , */
            5
        )

        queList.add(q1)
        queList.add(q2)
        queList.add(q3)
        queList.add(q4)
        queList.add(q5)
        //queList.add(q6)
        //queList.add(q7)

        return queList
    }
}