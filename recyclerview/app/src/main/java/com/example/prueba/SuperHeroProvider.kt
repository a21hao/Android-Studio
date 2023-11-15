package com.example.prueba

class SuperHeroProvider {
    companion object{
        val superheroList = listOf<SuperHero>(
            SuperHero(
                "Jumanji",
                "Chris Van Allsburg",
                "13 de diciembre de 2019",
                R.drawable.jumanji
            ),
            SuperHero(
                "Avatar",
                "James Cameron",
                "18 de desembre de 2009",
                R.drawable.avatar_3gqz
            ),
            SuperHero(
                "Avatar",
                "James Cameron",
                "16 de desembre de 2022",
                R.drawable.baixa
            ),
            SuperHero(
                "Harry Potter",
                "J.K. Rowling",
                "14 de novembre de 2001",
                R.drawable.baixa_1
            ),
            SuperHero(
                "Señor de los Anillos",
                "J. R. R. Tolkien",
                "19 de diciembre de 2001",
                R.drawable.unnamed
            ),
        )
    }
}