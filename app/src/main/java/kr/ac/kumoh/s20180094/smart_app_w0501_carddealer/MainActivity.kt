package kr.ac.kumoh.s20180094.smart_app_w0501_carddealer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import kr.ac.kumoh.s20180094.smart_app_w0501_carddealer.databinding.ActivityMainBinding
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val model: CardViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        model.cards.observe(this, Observer<IntArray> {
            val res = IntArray(5)
            for (i in it.indices) {
                res[i] = resources.getIdentifier(
                    getCardName(it[i]),
                    "drawable",
                    packageName
                )
            }
            binding.card1.setImageResource(res[0])
            binding.card2.setImageResource(res[1])
            binding.card3.setImageResource(res[2])
            binding.card4.setImageResource(res[3])
            binding.card5.setImageResource(res[4])
        })

        binding.btnCard.setOnClickListener {
            model.deal()
        }
        //binding.card1.setImageResource(R.drawable.c_3_of_diamonds)

//        binding.btnCard.setOnClickListener {
//            val res = IntArray(5)
//            for(i in 0 until res.size){
//                res[i] = resources.getIdentifier(
//                    getCardName(Random.nextInt(52)),
//                    "drawable",
//                    packageName
//                )
//            }
//            binding.card1.setImageResource(res[0])
//            binding.card2.setImageResource(res[1])
//            binding.card3.setImageResource(res[2])
//            binding.card4.setImageResource(res[3])
//            binding.card5.setImageResource(res[4])
//        }

    }

    private fun getCardName(c: Int): String{
        val shape = when (c/13) {
            0 -> "spades"
            1 -> "diamonds"
            2 -> "hearts"
            3 -> "clubs"
            else -> "error"
        }
        val number= when(c%13){
            0 -> "ace"
            in 1..9 -> ((c%13)+1).toString()
            10 -> "jack"
            11 -> "queen"
            12 -> "king"
            else -> "error"
        }
        if(number=="jack" || number=="queen" || number =="king")
            return "c_${number}_of_${shape}2"
        else
            return "c_${number}_of_${shape}"
    }
}