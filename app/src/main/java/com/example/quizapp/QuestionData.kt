package com.example.quizapp

object QuestionData {
    fun getQuestion(): ArrayList<Question> {
        val queList: ArrayList<Question> = arrayListOf()

        val q1 = Question(
            1,
            "9 X □ = 27 \n □ = ?",
            3
        )

        val q2 = Question(
            2,
            "□ X 6 = 42 \n □ = ?",
            7
        )

        val q3 = Question(
            3,
            "구슬 49개를 7개씩 주머니에 나누어 넣었다.\n 주머니는 총 몇 개 일까?",
            7
        )

        val q4 = Question(
            4,
            "지우개와 연필이 하나씩 짝지어져있다.\n" +
                    "총 16개가 있을 때 연필은 몇 개 일까?",
            8
        )

        val q5 = Question(
            5,
            "3 X □ = 15 \n □ = ?",
            5
        )

        val q6 = Question(
            6,
            "□ X 9 = 54 \n □ = ?",
            6
        )

        val q7 = Question(
            7,
            "4 X □ = 28 \n □ = ?",
            7
        )

        queList.add(q1)
        queList.add(q2)
        queList.add(q3)
        queList.add(q4)
        queList.add(q5)
        queList.add(q6)
        queList.add(q7)

        return queList
    }
}