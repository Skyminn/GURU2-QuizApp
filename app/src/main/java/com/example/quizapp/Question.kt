package com.example.quizapp

data class Question(
    var id: Int,
    var question: String,/*
    var option_one: Int,
    var option_two: Int,
    var option_three: Int,
    var option_four: Int, */
    var correct_answer: Int
)
