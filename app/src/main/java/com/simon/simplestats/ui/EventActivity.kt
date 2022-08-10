package com.simon.simplestats.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.simon.simplestats.R
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class EventActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
        val viewModel = ViewModelProvider(this)[StatsViewModel::class.java]
        val graphView = findViewById<GraphView>(R.id.graph)
        val registerId = intent.extras?.getInt("register_id")
        registerId?.let { id ->
            viewModel.getEvents(id).observe(this) {
                val series = LineGraphSeries<DataPoint>()
                var lastDate : LocalDate? = null
                var dayCounter  = 0
                var numberOfEvents = 1
                for (event in it){
                    if(lastDate == null){
                        lastDate = Instant.ofEpochSecond(event.time).atZone(ZoneId.systemDefault()).toLocalDate()
                    }else{
                        val currentDate = Instant.ofEpochSecond(event.time).atZone(ZoneId.systemDefault()).toLocalDate()
                        if(currentDate.equals(lastDate)){
                            numberOfEvents++
                        }else{
                            series.appendData(DataPoint(dayCounter.toDouble(), numberOfEvents.toDouble()), true, 10000)
                            numberOfEvents = 1
                            val daysBetween = lastDate.compareTo(currentDate)
                            for(i in 2..daysBetween){
                                dayCounter++
                                series.appendData(DataPoint(dayCounter.toDouble(), 0.0), true, 10000)
                            }
                            dayCounter++
                            lastDate = currentDate
                        }
                    }
                }
                graphView.addSeries(series)
            }
        }
    }
}