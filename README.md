# Chat Distribuido con WebSockets

Este proyecto implementa un sistema de chat en tiempo real utilizando **WebSockets**, con múltiples clientes desarrollados en distintos lenguajes, conectados a un servidor central.

---

## Tecnologías utilizadas

| Componente        | Lenguaje / Framework     |
|-------------------|--------------------------|
| 🧠 Servidor        | Python 3 + `websockets`  |
| 🖥 Cliente 1 (GUI) | Java 17 + Swing + WebSocket (`Java-WebSocket`) |
| 🖥 Cliente 2 (GUI) | Java 17 + Swing + WebSocket (`Java-WebSocket`) |
| 🌐 Cliente Web     | HTML + JavaScript WebSocket |
| 🛠 Build           | Maven (`pom.xml`)        |

---

##  Estructura del proyecto

chatclient1/
├── backend/
│ └── server.py
├── frontend/
│ ├── chat.html
│ └── chat.js
├── src/
│ └── main/java/com/chat/client/
│ ├── ChatGUI.java
│ ├── ChatClient1.java
│ └── ChatClient2.java
├── pom.xml
└── README.md

---

##  Requisitos

- Java 17
- Python 3.9+
- Maven (`mvn -v` para verificar)

---

##  Instalación y ejecución

### 🟢 1. Iniciar el servidor (Python)

```bash
cd backend
python server.py

Debe imprimir:
🟢 Servidor WebSocket activo en ws://localhost:8765

2. Compilar los clientes Java (Swing)
mvn clean package

3. Ejecutar cliente 1:
java -cp target/chatclient1-1.0-SNAPSHOT-jar-with-dependencies.jar com.chat.client.ChatClient1

4. Ejecutar cliente 2:
java -cp target/chatclient1-1.0-SNAPSHOT-jar-with-dependencies.jar com.chat.client.ChatClient2

5. Ejecutar cliente Web
Abre el archivo frontend/chat.html en el navegador.
Ingresa tu nombre y comienza a chatear.

Prueba del sistema

Con todos los componentes ejecutándose:

Envía un mensaje desde ChatClient1
Debería aparecer en ChatClient2 y en el navegador
Y viceversa
Todos los clientes están conectados al mismo servidor WebSocket (ws://localhost:8765) y comparten mensajes en tiempo real.


---

## 🧰 Requisitos

- Java 17
- Maven
- Python 3
- Navegador moderno

---

## 🛠️ Crear el proyecto Java con Maven

Desde VS Code o terminal:

```bash
mvn archetype:generate -DgroupId=com.chat.client -DartifactId=chatclient1 -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

Luego edita el archivo pom.xml generado para que incluya:

<properties>
  <maven.compiler.source>17</maven.compiler.source>
  <maven.compiler.target>17</maven.compiler.target>
</properties>

<dependencies>
  <dependency>
    <groupId>org.java-websocket</groupId>
    <artifactId>Java-WebSocket</artifactId>
    <version>1.5.2</version>
  </dependency>
</dependencies>

<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-compiler-plugin</artifactId>
      <version>3.8.1</version>
      <configuration>
        <source>17</source>
        <target>17</target>
        <compilerArgs>
          <arg>--add-modules</arg>
          <arg>java.desktop</arg>
        </compilerArgs>
      </configuration>
    </plugin>

    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-assembly-plugin</artifactId>
      <version>3.3.0</version>
      <configuration>
        <descriptorRefs>
          <descriptorRef>jar-with-dependencies</descriptorRef>
        </descriptorRefs>
        <archive>
          <manifest>
            <mainClass>com.chat.client.ChatClient1</mainClass>
          </manifest>
        </archive>
      </configuration>
      <executions>
        <execution>
          <id>make-assembly</id>
          <phase>package</phase>
          <goals>
            <goal>single</goal>
          </goals>
        </execution>
      </executions>
    </plugin>
  </plugins>
</build>