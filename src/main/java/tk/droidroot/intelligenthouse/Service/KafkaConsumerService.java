package tk.droidroot.intelligenthouse.Service;

import javassist.bytecode.stackmap.BasicBlock;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import tk.droidroot.intelligenthouse.Controllers.*;
import tk.droidroot.intelligenthouse.DTO.*;

@Service
public class KafkaConsumerService {

    @Autowired
    AlarmController alarmController;

    @Autowired
    DeviceController deviceController;

    @Autowired
    TemperatureSensorController temperatureSensorController;

    @Autowired
    HumiditySensorController humiditySensorController;

    @Autowired
    GasSensorController gasSensorController;

    @Autowired
    LightSensorController lightSensorController;

    @Autowired
    TemperatureSensorDataController temperatureSensorDataController;

    @Autowired
    HumiditySensorDataController humiditySensorDataController;

    @Autowired
    GasSensorDataController gasSensorDataController;

    @Autowired
    LightSensorDataController lightSensorDataController;

    @KafkaListener(topics="alarm")
    public void receiveAlarm(@Payload String alarm, @Headers MessageHeaders headers) {
        System.out.printf("Received alarm message {%s}\n", alarm);

        AlarmDTO dto = new AlarmDTO();
        JSONObject alarm_json = new JSONObject(alarm);

        dto.setName(alarm_json.getString("name"));
        dto.setTime(alarm_json.getString("time"));
        dto.setState((alarm_json.getInt("state") == 1 ? true : false));

        try {
            alarmController.create(dto);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics="device")
    public void receiveDevice(@Payload String device, @Headers MessageHeaders headers) {
        System.out.printf("Received device message {%s}\n", device);

        DeviceDTO dto = new DeviceDTO();
        JSONObject device_json = new JSONObject(device);

        dto.setName(device_json.getString("name"));
        dto.setState(device_json.getInt("state") == 1 ? true : false);

        try {
            deviceController.create(dto);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @KafkaListener(topics="temperature_sensor")
    public void receiveTemperatureSensor(@Payload String temperatureSensor, @Headers MessageHeaders headers) {
        System.out.printf("Received temperature_sensor message {%s}\n", temperatureSensor);

        TemperatureSensorDTO dto = new TemperatureSensorDTO();
        JSONObject ts_json = new JSONObject(temperatureSensor);

        dto.setName(ts_json.getString("name"));

        try {
            temperatureSensorController.create(dto);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @KafkaListener(topics="humidity_sensor")
    public void receiveHumiditySensor(@Payload String humiditySensor, @Headers MessageHeaders headers) {
        System.out.printf("Received humidity_sensor message {%s}\n", humiditySensor);

        HumiditySensorDTO dto = new HumiditySensorDTO();
        JSONObject hs_json = new JSONObject(humiditySensor);

        dto.setName(hs_json.getString("name"));
        try {
            humiditySensorController.create(dto);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @KafkaListener(topics="gas_sensor")
    public void receiveGasSensor(@Payload String gasSensor, @Headers MessageHeaders headers) {
        System.out.printf("Received gas_sensor message {%s}\n", gasSensor);

        GasSensorDTO dto = new GasSensorDTO();
        JSONObject gs_json = new JSONObject(gasSensor);

        dto.setName(gs_json.getString("name"));

        try {
            gasSensorController.create(dto);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics="light_sensor")
    public void receiveLightSensor(@Payload String lightSensor, @Headers MessageHeaders headers) {
        System.out.printf("Received light_sensor message {%s}\n", lightSensor);

        LightSensorDTO dto = new LightSensorDTO();
        JSONObject ls_json = new JSONObject(lightSensor);

        dto.setName(ls_json.getString("name"));

        try {
            lightSensorController.create(dto);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics="temperature_sensor_data")
    public void receiveTemperatureSensorData(@Payload String temperatureSensorData, @Headers MessageHeaders headers) {
        System.out.printf("Received temperature_sensor_data message {%s}\n", temperatureSensorData);

        TemperatureSensorDataDTO dto = new TemperatureSensorDataDTO();
        JSONObject tsd_json = new JSONObject(temperatureSensorData);

        dto.setTemperatureSensor(tsd_json.getLong("temp_sensor"));
        dto.setData(tsd_json.getDouble("data"));
        dto.setDate(tsd_json.getString("date"));

        try {
            temperatureSensorDataController.create(dto);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics="humidity_sensor_data")
    public void receiveHumidityData(@Payload String humiditySensorData, @Headers MessageHeaders headers) {
        System.out.printf("Received humidity_sensor_data message {%s}\n", humiditySensorData);

        HumiditySensorDataDTO dto = new HumiditySensorDataDTO();
        JSONObject hsd_json = new JSONObject(humiditySensorData);

        dto.setHumiditySensor(hsd_json.getLong("hum_sensor"));
        dto.setData(hsd_json.getDouble("data"));
        dto.setDate(hsd_json.getString("date"));

        try {
            humiditySensorDataController.create(dto);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics="gas_sensor_data")
    public void receiveGasSensorData(@Payload String gasSensorData, @Headers MessageHeaders headers) {
        System.out.printf("Received gas_sensor_data message {%s}\n", gasSensorData);

        GasSensorDataDTO dto = new GasSensorDataDTO();
        JSONObject gsd_json = new JSONObject(gasSensorData);

        dto.setGasSensor(gsd_json.getLong("gas_sensor"));
        dto.setData(gsd_json.getDouble("data"));
        dto.setDate(gsd_json.getString("date"));

        try {
            gasSensorDataController.create(dto);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @KafkaListener(topics="light_sensor_data")
    public void receiveLightSensorData(@Payload String lightSensorData, @Headers MessageHeaders headers) {
        System.out.printf("Received light_sensor_data message {%s}\n", lightSensorData);

        LightSensorDataDTO dto = new LightSensorDataDTO();
        JSONObject lsd_json = new JSONObject(lightSensorData);

        dto.setLightSensor(lsd_json.getLong("light_sensor"));
        dto.setData(lsd_json.getDouble("data"));
        dto.setDate(lsd_json.getString("date"));

        try {
            lightSensorDataController.create(dto);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
