package com.example.recyclerviewexample

data class ExampleItem( val imageResource: Int, var text1 : String, val text2 : String )

//text1 is changed on click, so it is declared as var instead of val