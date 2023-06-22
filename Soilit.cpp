#include <Arduino.h>
#include <WiFi.h>
#include <WebServer.h>
#include <ArduinoJson.h>

const char *SSID = "Soilit";
const char *PWD = "123454321";

WebServer server(80);

int temperature = 0;
const int lm35Pin = 15;

void connectToWiFi() {
  Serial.print("Connecting to ");
  Serial.println(SSID);

  WiFi.begin(SSID, PWD);

  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }

  Serial.print("Connected. IP: ");
  Serial.println(WiFi.localIP());
}

void setup_routing() {
  server.on("/temperature", HTTP_GET, getTemperature);

  server.begin();
}

float readTemperature() {
  int sensorValue = analogRead(lm35Pin);
  float milliVolts = (sensorValue / 4095.0) * 3300; // Mengonversi nilai ADC menjadi mV
  float temperature = milliVolts / 10; // Konversi mV menjadi suhu dalam derajat Celsius
  
  return temperature;
}

void getTemperature() {
  StaticJsonDocument<250> jsonDocument;
  char buffer[250];

  jsonDocument["type"] = "temperature";
  jsonDocument["value"] = temperature;
  jsonDocument["unit"] = "°C";
  serializeJson(jsonDocument, buffer);

  server.send(200, "application/json", buffer);
}

void setup() {
  Serial.begin(9600);

  // Membaca suhu dan menyimpannya pada variabel temperature
  temperature = readTemperature();
  Serial.print("Temperature: ");
  Serial.print(temperature);
  Serial.println(" °C");
  
  // Mematikan sensor LM35
  pinMode(lm35Pin, INPUT);
  digitalWrite(lm35Pin, LOW);

  // Mengaktifkan WiFi
  connectToWiFi();
  setup_routing();
}

void loop() {
  server.handleClient();

  // Tidak ada pembacaan suhu di loop utama
}
