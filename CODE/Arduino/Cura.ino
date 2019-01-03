/*
 *  This sketch sends data via HTTP GET requests to data.sparkfun.com service.
 *
 *  You need to get streamId and privateKey at data.sparkfun.com and paste them
 *  below. Or just customize this script to talk to other HTTP servers.
 *
 */

#include <ESP8266WiFi.h>

float initial_angle0=0;
float initial_angle1=0;
int state=0;
const char* ssid     = "salabs";
const char* password = "shresthag";

const char* host = "http://makerssteam-com.stackstaging.com";
const char* streamId   = "....................";
const char* privateKey = "....................";

// I2Cdev and MPU6050 must be installed as libraries, or else the .cpp/.h files
// for both classes must be in the include path of your project
#include "I2Cdev.h"
#include "MPU6050.h"
#include "math.h"

// Arduino Wire library is required if I2Cdev I2CDEV_ARDUINO_WIRE implementation
// is used in I2Cdev.h
#if I2CDEV_IMPLEMENTATION == I2CDEV_ARDUINO_WIRE
    #include "Wire.h"
#endif

// class default I2C address is 0x68
// specific I2C addresses may be passed as a parameter here
// AD0 low = 0x68 (default for InvenSense evaluation board)
// AD0 high = 0x69
MPU6050 accelgyro0(0x68);
MPU6050 accelgyro1(0x69); // <-- use for AD0 high

int16_t ax0, ay0, az0;
int16_t gx0, gy0, gz0;

int16_t ax1, ay1, az1;
int16_t gx1, gy1, gz1;



// uncomment "OUTPUT_READABLE_ACCELGYRO" if you want to see a tab-separated
// list of the accel X/Y/Z and then gyro X/Y/Z values in decimal. Easy to read,
// not so easy to parse, and slow(er) over UART.
#define OUTPUT_READABLE_ACCELGYRO

// uncomment "OUTPUT_BINARY_ACCELGYRO" to send all 6 axes of data as 16-bit
// binary, one right after the other. This is very fast (as fast as possible
// without compression or data loss), and easy to parse, but impossible to read
// for a human.
//#define OUTPUT_BINARY_ACCELGYRO


#define LED_PIN 13
bool blinkState = false;



void setup() {
    //  ESP.wdtFeed();
  // join I2C bus (I2Cdev library doesn't do this automatically)
    #if I2CDEV_IMPLEMENTATION == I2CDEV_ARDUINO_WIRE
        Wire.begin();
    #elif I2CDEV_IMPLEMENTATION == I2CDEV_BUILTIN_FASTWIRE
        Fastwire::setup(400, true);
    #endif

    // initialize serial communication
    // (38400 chosen because it works as well at 8MHz as it does at 16MHz, but
    // it's really up to you depending on your project)
  
  Serial.begin(38400);
  delay(10);

   Serial.println("Initializing I2C devices...");
    accelgyro0.initialize();
    accelgyro1.initialize();

    // verify connection
    Serial.println("Testing device connections...");
    Serial.println(accelgyro0.testConnection()&&accelgyro1.testConnection() ? "MPU6050 connection successful" : "MPU6050 connection failed");

    // use the code below to change accel/gyro offset values
    /*
    Serial.println("Updating internal sensor offsets...");
    // -76  -2359 1688  0 0 0
    Serial.print(accelgyro.getXAccelOffset()); Serial.print("\t"); // -76
    Serial.print(accelgyro.getYAccelOffset()); Serial.print("\t"); // -2359
    Serial.print(accelgyro.getZAccelOffset()); Serial.print("\t"); // 1688
    Serial.print(accelgyro.getXGyroOffset()); Serial.print("\t"); // 0
    Serial.print(accelgyro.getYGyroOffset()); Serial.print("\t"); // 0
    Serial.print(accelgyro.getZGyroOffset()); Serial.print("\t"); // 0
    Serial.print("\n");
    accelgyro.setXGyroOffset(220);
    accelgyro.setYGyroOffset(76);
    accelgyro.setZGyroOffset(-85);
    Serial.print(accelgyro.getXAccelOffset()); Serial.print("\t"); // -76
    Serial.print(accelgyro.getYAccelOffset()); Serial.print("\t"); // -2359
    Serial.print(accelgyro.getZAccelOffset()); Serial.print("\t"); // 1688
    Serial.print(accelgyro.getXGyroOffset()); Serial.print("\t"); // 0
    Serial.print(accelgyro.getYGyroOffset()); Serial.print("\t"); // 0
    Serial.print(accelgyro.getZGyroOffset()); Serial.print("\t"); // 0
    Serial.print("\n");
    */

    // configure Arduino LED for
    pinMode(LED_PIN, OUTPUT);
    

  // We start by connecting to a WiFi network

  Serial.println();
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);
  
  WiFi.begin(ssid, password);
  
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    //ESP.wdtFeed();
    Serial.print(".");
  }

  Serial.println("");
  Serial.println("WiFi connected");  
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
}

int value = 0;

void loop() {

    // ESP.wdtFeed();
    // read raw accel/gyro measurements from device
    accelgyro0.getMotion6(&ax0, &ay0, &az0, &gx0, &gy0, &gz0);
    accelgyro1.getMotion6(&ax1, &ay1, &az1, &gx1, &gy1, &gz1);
    

    // these methods (and a few others) are also available
    //accelgyro.getAcceleration(&ax, &ay, &az);
    //accelgyro.getRotation(&gx, &gy, &gz);

    #ifdef OUTPUT_READABLE_ACCELGYRO
        // display tab-separated accel/gyro x/y/z values

        float angle = (atan(ax0/az0)-atan(ax1/az1))*57.296;
        
        String str_angle = String(angle);
        Serial.println("angle======================"+str_angle);
        //Serial.print("a/g:\t");
        //Serial.print(ax0); Serial.print("\t");
        //Serial.print(ay0); Serial.print("\t");
        //Serial.print(az0); Serial.print("\t");
        //Serial.print(gx0); Serial.print("\t");
        //Serial.print(gy0); Serial.print("\t");
        //Serial.println(gz0);

         //Serial.print(ax1); Serial.print("\t");
        //Serial.print(ay1); Serial.print("\t");
        //Serial.print(az1); Serial.print("\t");
        //Serial.print(gx1); Serial.print("\t");
        //Serial.print(gy1); Serial.print("\t");
        //Serial.println(gz1);
    #endif

    #ifdef OUTPUT_BINARY_ACCELGYRO
       /* Serial.write((uint8_t)(ax >> 8)); Serial.write((uint8_t)(ax & 0xFF));
        Serial.write((uint8_t)(ay >> 8)); Serial.write((uint8_t)(ay & 0xFF));
        Serial.write((uint8_t)(az >> 8)); Serial.write((uint8_t)(az & 0xFF));
        Serial.write((uint8_t)(gx >> 8)); Serial.write((uint8_t)(gx & 0xFF));
        Serial.write((uint8_t)(gy >> 8)); Serial.write((uint8_t)(gy & 0xFF));
        Serial.write((uint8_t)(gz >> 8)); Serial.write((uint8_t)(gz & 0xFF));*/
    #endif

    // blink LED to indicate activity
    blinkState = !blinkState;
    digitalWrite(LED_PIN, blinkState);
  int var =0;
  if (initial_angle0> initial_angle1)
  {
    state= -1;
    if ((angle-initial_angle1)>20)
    {
      var =1;
      }
    }  
  if (initial_angle0< initial_angle1)
  {
    state= +1;
    if ((initial_angle1-angle)>20)
    {
      var =1;
      }
    
    }   
  if (initial_angle0==initial_angle1)
  {
    state= 0;
    }    
    
  initial_angle0 = initial_angle1;
  initial_angle1 = angle;
  
   
   
  if (var==1)
  {
  ++value;

  Serial.print("connecting to ");
  Serial.println(host);
  
  // Use WiFiClient class to create TCP connections
  WiFiClient client;
  const int httpPort = 80;
  if (!client.connect(host, httpPort)) {
    Serial.println("connection failed");
    return;
  }
  
  // We now create a URI for the request
  String url = "/input/";
  url += streamId;
  url += "?private_key=";
  url += privateKey;
  url += "&value=";
  url += value;
  
  Serial.print("Requesting URL: ");
  Serial.println(url);


  String webink = "http://makerssteam-com.stackstaging.com/realtime.php?userid=steam_protype&angle="+str_angle;
  // This will send the request to the server
  client.print(String("GET ") + webink + " HTTP/1.1\r\n" +
               "Host: " + host + "\r\n" + 
               "Connection: close\r\n\r\n");
  unsigned long timeout = millis();
  while (client.available() == 0) {
    if (millis() - timeout > 5000) {
      Serial.println(">>> Client Timeout !");
      client.stop();
      return;
    }
  }
  
  // Read all the lines of the reply from server and print them to Serial
  while(client.available()){
    String line = client.readStringUntil('\r');
    Serial.print(line);
  }
  
  Serial.println();
  Serial.println("closing connection");
  }
  
}

