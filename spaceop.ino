   #include <SoftwareSerial.h>
//SoftwareSerial BT1(3, 2); // RX | TX
int errorLED = 13;
String ssid     = "Cesar";  // SSID delwifi
String password = "zzzzzzzzzz"; // Circuits no usa password

//String host     = "polaris.cageek.com.mx"; // Open Weather Map API
//const int httpPort   = 80;
//String uri     = "/index.php?id=85" ;

String host     = "polaris.cageek.com.mx"; // Open Weather Map API
const int httpPort   = 80;
///////////////////////////////////////////////////////////////////
float watt;
////////////////////////////////////////////////////////////////////
#include <DHT.h>
#include <Wire.h>
#include <LiquidCrystal_I2C.h>
DHT dht(39,22);

LiquidCrystal_I2C lcd (0x3F,16,2);
/////////////////////////////////////////
float LDRValue = 0;     // result of reading the analog pin
float lux;
int LDRpin;
////////////////////////////////////////////////////////////////
float vPow = 4.7;
float r1 = 100000; //Valor de la resitencia 1 100kΩ
float r2 = 10000;  //Valor de la resitencia 2 10kΩ
////////////////////////////////////////////////////////////
#include <math.h><br>
////////////////////////////////////////////////////////////////////
#include <Wire.h>
#include <Adafruit_BMP085.h>
Adafruit_BMP085 bmp;
///////////////////////////////////////////////////////////////////
void setup()
  {  
    //////////////////////////////////////////////////////////////////
    
 ////////////dht
 dht.begin();
 ///configuracion de lcd 
 Wire.begin();
 lcd.init();
 lcd.clear();
 lcd.backlight();
 lcd.setCursor(4,0);
 lcd.print("POLARIS");
 lcd.setCursor(0,1);
 lcd.print("preparandose :v");
 delay(3000);
 lcd.clear();
//SE INICIA COMUNICACION SERIAL Y SE DECLARAN LAS SALIDAS
   

    /////////////////////////////////////////////////////////////////
    pinMode(errorLED, OUTPUT);
  Serial.begin(9200);
    // BT1.begin(115200);
   // Serial.println("AT");
   //BT1.print("AT");
    delay(5000);        // tiempo para que el ESP responda

    Serial.println("AT+CWMODE=3");
    delay(5000);
  //if (Serial.find("OK")) digitalWrite(errorLED, HIGH);  // Checa si el ESP funciona
    // Se conecta circuits al simulador de wifi
      // Se conecta circuits al simulador de wifi
  Serial.println("AT+CWJAP=\"" + ssid + "\",\"" + password + "\"");
  delay(5000);        // Esperar a que responda
// if (Serial.find("OK")) digitalWrite(errorLED, HIGH); // checar si funciona
  Serial.println("AT+CIPMUX=1");
  delay(5000);
 // if (Serial.find("OK")) digitalWrite(errorLED, HIGH); 
 Serial.println("AT+CIPSTART=\"TCP\",\"" + host + "\"," + httpPort);
  delay(10000);
  if (Serial.find("OK")) digitalWrite(errorLED, HIGH);
 //  Serial.println("AT+CIPSTART=\"TCP\",\"" + host + "\"," + httpPort);
 // delay(5000);        // Wait a little for the ESP to respond
//  if (Serial.find("OK")) digitalWrite(errorLED, HIGH); // check if the ESP is running well
  

    
  }

  double Thermister(int RawADC) {  
double Temp;
Temp = log(((10240000/RawADC) - 10000));
Temp = 1 / (0.001129148 + (0.000234125 + (0.0000000876741 * Temp * Temp ))* Temp );
Temp = Temp - 273.15;// Converierte de Kelvin a Celsius
//Para convertir Celsius a Farenheith esriba en esta linea: Temp = (Temp * 9.0)/ 5.0 + 32.0; 
return Temp;
} 


void loop()
  { 
    //////////////////////////////////////////////////////////////////////////

float humedad = dht.readHumidity();
float temperatura = dht.readTemperature();
  LDRValue = analogRead(LDRpin)*.00488; // read the value from the LDR
  lux= (2500/LDRValue)-500;
     // print the value to the serial port
//outputValue= map (sensorValue, 0, 1024, 0, 255);
String temperaturaString = String(temperatura);  
/////////////////////////////////////////////////////
 float v = ((analogRead(2) * vPow) / 1024.0)*10;
  float v2 = v / (r2 / (r1 + r2));
////////////////////////////////////////////////////
int val;//Crea una variable entera
double temp;//Variable de temperatura = temp
val=analogRead(1);//Lee el valor del pin analogo 0 y lo mantiene como val
temp=Thermister(val);//Realiza la conversión del valor analogo a grados Celsius
//Escribe la temperatura en el monitor serial
/////////////////////////////////////////////////////////////////
watt=(v*v)/27;

Serial.println(temperatura);
Serial.println(humedad);
Serial.println(lux);   
Serial.println(temp);
Serial.println(bmp.readPressure());
//Serial.println(bmp.readAltitude());
Serial.println(bmp.readSealevelPressure());
Serial.println(v);
Serial.println(watt);



 lcd.setCursor(0,0);
 lcd.print("temp:");
lcd.print(temperatura);
 lcd.setCursor(11,0);
 lcd.print("V:");
  lcd.print(v);
 lcd.setCursor(0,1);
 lcd.print("hum:");
 lcd.print(humedad);
 lcd.setCursor(10,1);
 lcd.print("lu:");
lcd.println(lux);   
  

delay(500);



String uri     = "/app/recepcion.php?temp=" + temperaturaString + "&humedad=" + humedad + "&lumens=" + lux + "&temppanel=" + temp + "&presion=" + bmp.readPressure() + "&nivelpresion=" + bmp.readSealevelPressure() + "&voltaje=" + v + "&watt=" + watt + "" ;
    //////////////////////////////////////////////////////////////////////
String httpPacket = "GET " + uri + " HTTP/1.1\r\nHost: " + host + "\r\n\r\n";
  int length = httpPacket.length();
  
  // Send our message length
  Serial.print("AT+CIPSEND=");
  Serial.println(length);
  delay(10000); // Wait a little for the ESP to respond
 // if (Serial.find(">")) digitalWrite(errorLED, HIGH); // check if the ESP is running well */

  // Send our http request
  Serial.print(httpPacket);
  delay(10000); // Wait a little for the ESP to respond
 // if (Serial.find("SEND OK\r\n")) digitalWrite(errorLED, HIGH); // check if the ESP is running well
   }
