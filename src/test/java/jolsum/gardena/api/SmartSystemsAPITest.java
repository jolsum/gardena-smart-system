package jolsum.gardena.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import jolsum.gardena.api.smartsystems.BatteryState;
import jolsum.gardena.api.smartsystems.CommonServiceDataItem;
import jolsum.gardena.api.smartsystems.DataItem;
import jolsum.gardena.api.smartsystems.DeviceDataItem;
import jolsum.gardena.api.smartsystems.LocationDataItem;
import jolsum.gardena.api.smartsystems.SensorDataItem;
import jolsum.gardena.api.smartsystems.ValveActivity;
import jolsum.gardena.api.smartsystems.ValveError;
import jolsum.gardena.api.smartsystems.ValveServiceDataItem;
import org.junit.jupiter.api.Test;

public class SmartSystemsAPITest {

  @Test
  public void testConvert() throws Exception {

    Path path = Paths.get(getClass().getResource("/data_item_list.json").toURI());

    List<DataItem> dataItems =
        Files.lines(path).map(l -> SmartSystemsAPI.convert(l)).collect(Collectors.toList());

    LocationDataItem location = (LocationDataItem) dataItems.get(0);
    assertEquals("2a0717f0-ea50-4e9c-b40f-bdcbb60c0407", location.getId());
    assertEquals("LOCATION", location.getType());
    assertEquals(2, location.getDevices().size());
    assertEquals("My Garden", location.getName());

    DeviceDataItem device1 = (DeviceDataItem) dataItems.get(1);
    assertEquals("5f20dd7a-5874-4963-a844-613ca1f39188", device1.getId());
    assertEquals("DEVICE", device1.getType());
    assertEquals("2a0717f0-ea50-4e9c-b40f-bdcbb60c0407", device1.getLocation().getId());
    assertEquals(2, device1.getServices().size());

    DeviceDataItem device2 = (DeviceDataItem) dataItems.get(2);
    assertEquals("9a79580d-4f12-44d4-84cb-c7c08bff983c", device2.getId());
    assertEquals("DEVICE", device2.getType());
    assertEquals("2a0717f0-ea50-4e9c-b40f-bdcbb60c0407", device2.getLocation().getId());
    assertEquals(3, device2.getServices().size());

    SensorDataItem sensor = (SensorDataItem) dataItems.get(3);
    assertEquals("5f20dd7a-5874-4963-a844-613ca1f39188", sensor.getId());
    assertEquals("SENSOR", sensor.getType());
    assertEquals("5f20dd7a-5874-4963-a844-613ca1f39188", sensor.getDevice().getId());
    assertEquals(23, sensor.getSoilHumidity().getAsInt());
    assertEquals(13, sensor.getSoilTemperature().getAsInt());
    assertEquals(14, sensor.getAmbientTemperature().getAsInt());
    assertEquals(7, sensor.getLightIntensity().getAsInt());

    CommonServiceDataItem common1 = (CommonServiceDataItem) dataItems.get(4);
    assertEquals("5f20dd7a-5874-4963-a844-613ca1f39188", common1.getId());
    assertEquals("COMMON", common1.getType());
    assertEquals("5f20dd7a-5874-4963-a844-613ca1f39188", common1.getDevice().getId());
    assertEquals("Hekk", common1.getName());
    assertEquals(95, common1.getBatteryLevel().getAsInt());
    assertEquals(BatteryState.OK, common1.getBatteryState().getValue());
    assertEquals(40, common1.getRfLinkLevel().getAsInt());
    assertEquals("00022824", common1.getSerial());
    assertEquals("GARDENA smart Sensor", common1.getModelType());
    assertEquals("ONLINE", common1.getRfLinkState());

    ValveServiceDataItem valve = (ValveServiceDataItem) dataItems.get(6);
    assertEquals("9a79580d-4f12-44d4-84cb-c7c08bff983c", valve.getId());
    assertEquals("VALVE", valve.getType());
    assertEquals("9a79580d-4f12-44d4-84cb-c7c08bff983c", valve.getDevice().getId());
    assertEquals("Kontroller", valve.getName());
    assertEquals(ValveActivity.CLOSED, valve.getActivity().getValue());
    assertEquals(ValveError.NO_MESSAGE, valve.getLastErrorCode().getValue());

    dataItems.forEach(System.out::println);
  }
}
