import asyncio
import websockets

clients = set()

async def chat(websocket):
    clients.add(websocket)
    try:
        async for message in websocket:
            print(f"Recibido: {message}")
            for client in clients:
                if client != websocket:
                    await client.send(message)
    finally:
        clients.remove(websocket)

async def main():
    async with websockets.serve(chat, "localhost", 8765):
        print("Servidor WebSocket activo en ws://localhost:8765")
        await asyncio.Future()  

if __name__ == "__main__":
    asyncio.run(main())
