#include <ESP8266WiFi.h>
#include <thermistor.h>
#include <Servo.h>

const char *ssid = "BATCAVERNINHA";
const char *password = "eusouhacker";

Servo servo1;
Servo servo2;
// declaraÃ§Ã£o dos pinos dos componentes
int pinServo1 = D4;
int pinServo2 = D3;
int ledPin = D2; 
int pinNTC = A0; 

int temperatura =0;
WiFiServer server(80);
THERMISTOR thermistor(pinNTC, 10000, 3950, 10000);

void setup()
{
  Serial.begin(115200);
  delay(10);

  servo1.attach(pinServo1);
  servo1.write(0); // posiÃ§Ã£o inicial do servo1

  servo2.attach(pinServo2);
  servo2.write(180); // posiÃ§Ã£o inicial do servo2

  pinMode(ledPin, OUTPUT);
  digitalWrite(ledPin, LOW);

  Serial.begin(9600);

  // Connect to WiFi network
  Serial.println();
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);

  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED)
  {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.println("WiFi connected");

  // Start the server
  server.begin();
  Serial.println("Server started");

  // Print the IP address
  Serial.print("Use this URL to connect: ");
  Serial.print("http://");
  Serial.print(WiFi.localIP());
  Serial.println("/");
}

void loop()
{
  // Check if a client has connected
  WiFiClient client = server.available();
  if (!client)
  {
    return;
  }

  // Wait until the client sends some data
  //Serial.println("new client");
  while (!client.available())
  {
    delay(1);
  }

  // Read the first line of the request
  String request = client.readStringUntil('\r');
  Serial.println(request);
  client.flush();

  // Match the request

  int value = LOW;
  int value1 = LOW;
  if (request.indexOf("/LED=ON") != -1)
  {
    digitalWrite(ledPin, HIGH);
    value = HIGH;
  }
  if (request.indexOf("/LED=OFF") != -1)
  {
    digitalWrite(ledPin, LOW);
    value = LOW;
  }
  if (request.indexOf("/SERVO=ON") != -1)
  {
    servo1.write(180);
    servo2.write(0);
    value1 = HIGH;
  }
  if (request.indexOf("/SERVO=OFF") != -1)
  {
    servo1.write(0);
    servo2.write(180);
    value1 = LOW;
  }
  // temperatura
  temperatura = thermistor.read();
  temperatura = (float(analogRead(pinNTC)) * 5 / (1023)) / 0.01; //fazendo a conversÃ£o para Celsius
  Serial.print("Temperatura: ");
  Serial.print(temperatura);
  Serial.println(" Â°C");

  Serial.println("");

  delay(1);
  // Set ledPin according to the request
  //digitalWrite(servoPin, value);

  // RETORNAR A RESPOSTA
  client.println("HTTP/1.1 200 OK");
  client.println("Content-Type: text/html");
  client.println(""); // do not forget this one
  client.println("<!DOCTYPE HTML>");
  client.println("<html>");

  client.println("<meta charset ='utf-8'/>");
  client.println("<meta name = 'viewport' content = 'width=device-width, initial-scale=1'>");

  client.println(F("<link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css' rel='stylesheet'></link>"));

  client.println("<div class='jumbotron'>");
  client.println("<h1 class='text-center'>PROJETO AQUARIUMâ€™S</h1>");                 //TITULO DO PROJETO
  //client.println("<h2 class='text-center'>D1 MINI - ESP8266</h2>");                  //NOME DA PLACA UTILIZADA
  //client.println("<h2 class='text-center'>GRUPO: Helter, Sabrina e Wallisson</h2>"); //NOME DO GRUPO
  client.println("</div>");

  client.println("<div class='col-md-2'>");
  client.println("<div class='alert alert-info text-center' role='alert'>Alimentar</div>");        // quadrado com o nome Alimentar
  client.println("<a class='btn btn-success btn-lg center-block' href=\"/SERVO=ON\"> Abrir </a>"); //botÃ£o de ligar
  client.println("<br>");
  client.println("<a class='btn btn-danger btn-lg center-block' href=\"/SERVO=OFF\"> Fechar </a>"); //botÃ£o de desligar
  client.println("</div>");
 
  client.println("<div class='col-md-2'>");
  client.println("<div class='alert alert-info text-center' role='alert'>Lampada</div>");        // quadrado com o nome Alimentar
  client.println("<a class='btn btn-success btn-lg center-block' href=\"/LED=ON\"> Ligar </a>"); //botÃ£o de ligar
  client.println("<br>");
  client.println("<a class='btn btn-danger btn-lg center-block' href=\"/LED=OFF\"> Desligar </a>"); //botÃ£o de desligar
  client.println("</div>");
 

  //Temperatura Html
  temperatura = (float(analogRead(pinNTC)) * 5 / (1023)) / 0.01;
  client.print("<div class='col-md-6'>");
  client.print("<div class='alert alert-info text-center' role='alert'>Temperatura: ");
  client.print(temperatura);
  client.print(" Â°C ");
  client.println("</div> </div>");

  client.println("</html>");

  delay(1);
  Serial.println("Client disonnected");
  Serial.println("");
}

