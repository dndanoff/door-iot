#include <Arduino.h>
#include <WiFi.h>
#include <WiFiClientSecure.h>
 
const int doorA = 2;
const int doorB = 4;

const char* ssid = "Stage Guest";
const char* password =  "12345678";

void setup() {
  pinMode(doorA, INPUT);
  pinMode(doorB, INPUT);

  Serial.begin(115200);
  WiFi.mode(WIFI_STA);
  WiFi.disconnect();
  delay(4000);   //Delay needed before calling the WiFi.begin
  WiFi.begin(ssid, password); 
  while (WiFi.status() != WL_CONNECTED) { //Check for the connection
    delay(1000);
    Serial.println("Connecting to WiFi..");
  }
  Serial.println("Connected to the WiFi network");
}
 
void loop() {
 if(WiFi.status()== WL_CONNECTED){   //Check WiFi connection status
   WiFiClientSecure client;
   if (client.connect("iot.test.dreamix.eu", 443)) {
    byte stateA = digitalRead(doorA);
    byte stateB = digitalRead(doorB);
    String payload = (String)"{ \"doorReadings\":[{\"value\": "+stateA+", \"doorName\":\"doorA\"}, {\"value\": "+stateB+", \"doorName\":\"doorB\"}]}";
    Serial.println("Sending payload: "+payload); 
    client.println("POST /api/v1/door-readings/multiple HTTP/1.1");
    client.println("Host: iot.test.dreamix.eu");
    client.println("User-Agent: ESP8266/1.0");
    client.println("Authorization: Basic ZG9vcnJlYWRlckBkcm14OkMyNDQxMDEwMjAxOQ=="); 
    client.println("Connection: close");
    client.println("Content-Type: application/json;");
    client.print("Content-Length: ");
    client.println(payload.length());
    client.println();
    client.println(payload);
    delay(10);
    String response = client.readString();
    Serial.println(response);
   }else{
    Serial.println("Error in connecting to server"); 
   }
 }else{
    Serial.println("Error in WiFi connection");   
 }
  delay(10000);  //Send a request every 10 seconds
}