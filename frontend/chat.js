let userName = "ClienteWeb";
const socket = new WebSocket("ws://localhost:8765");

const mensajeInput = document.getElementById("mensaje");
const chatBox = document.getElementById("chat-box");
const enviarBtn = document.getElementById("enviarBtn");

socket.onopen = () => {
  mostrarMensaje(`${userName} conectado al servidor.`);
};

socket.onmessage = (event) => {
  mostrarMensaje(" " + event.data);
};

socket.onclose = () => {
  mostrarMensaje("Conexión cerrada.");
};

socket.onerror = (error) => {
  mostrarMensaje("⚠️ Error en la conexión.");
  console.error(error);
};

function enviarMensaje() {
  const mensaje = mensajeInput.value.trim();
  if (mensaje && socket.readyState === WebSocket.OPEN) {
    const mensajeFormateado = `${userName}: ${mensaje}`;
    socket.send(mensajeFormateado);
    mostrarMensaje(" Cliente Web: " + mensaje);
    mensajeInput.value = "";
    chatBox.scrollTop = chatBox.scrollHeight;
  }
}

function mostrarMensaje(texto) {
  const p = document.createElement("p");
  p.textContent = texto;
  chatBox.appendChild(p);
  chatBox.scrollTop = chatBox.scrollHeight;
}

enviarBtn.addEventListener("click", enviarMensaje);
mensajeInput.addEventListener("keypress", (e) => {
  if (e.key === "Enter") enviarMensaje();
});
