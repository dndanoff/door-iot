#include <Arduino.h>
#include <WiFi.h>
#include <HTTPClient.h>
 
const int doorA = 2;
const int doorB = 4;

const char* ssid = "Stage Guest";
const char* password =  "12345678";

void setup() {
  pinMode(doorA, INPUT);
  pinMode(doorB, INPUT);

  Serial.begin(115200);
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
   HTTPClient http;
   byte stateA = digitalRead(doorA);
   byte stateB = digitalRead(doorB);   
   http.begin("http://MY_HOST:8080/api/v1/readings/multiple");  //Specify destination for HTTP request
   http.addHeader("Content-Type", "application/json");             //Specify content-type header
   int httpResponseCode = http.POST((String)"{ \"doorReadings\":[{\"value\": "+stateA+", \"doorName\":\"doorA\"}, {\"value\": "+stateB+", \"doorName\":\"doorB\"}]}");   //Send the actual POST request
   if(httpResponseCode>0){
    String response = http.getString();                       //Get the response to the request
    Serial.println(httpResponseCode);   //Print return code
    Serial.println(response);           //Print request answer
   }else{
    Serial.print("Error on sending POST: ");
    Serial.println(httpResponseCode);
   }
   http.end();  //Free resources
 }else{
    Serial.println("Error in WiFi connection");   
 }
  delay(10000);  //Send a request every 10 seconds
}