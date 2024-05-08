package com.example.eep523hw2.ui.home

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.eep523hw2.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), SensorEventListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var sensorManager: SensorManager
    private var temperatureSensor: Sensor? = null
    private var magneticFieldSensor: Sensor? = null
    private var proximitySensor: Sensor? = null
    private var pressureSensor: Sensor? = null
    private var lightSensor: Sensor? = null
    private var humiditySensor: Sensor? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        initializeSensors()

        homeViewModel.text.observe(viewLifecycleOwner) {
            binding.textHome.text = it
        }

        return root
    }

    private fun initializeSensors() {
        temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
        magneticFieldSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        humiditySensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event ?: return
        val value = event.values[0]
        when (event.sensor.type) {
            Sensor.TYPE_AMBIENT_TEMPERATURE -> binding.textViewTemperature.text = "Ambient Temperature: $value"
            Sensor.TYPE_MAGNETIC_FIELD -> binding.textViewMagneticField.text = "Magnetic Field: $value"
            Sensor.TYPE_PROXIMITY -> binding.textViewProximity.text = "Proximity: $value"
            Sensor.TYPE_PRESSURE -> binding.textViewPressure.text = "Pressure: $value"
            Sensor.TYPE_LIGHT -> binding.textViewLight.text = "Light: $value"
            Sensor.TYPE_RELATIVE_HUMIDITY -> binding.textViewHumility.text = "Humidity: $value"
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Handle sensor accuracy changes if needed
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, temperatureSensor, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, magneticFieldSensor, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, pressureSensor, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, humiditySensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
