import aiohttp
import asyncio
import json


async def send_mcp_message(base_url, endpoint, session_id, method_name, params=None):
    url = f"{base_url}{endpoint}"
    payload = {"method": method_name, "params": params or {}}
    async with aiohttp.ClientSession() as session:
        async with session.post(url, json=payload) as resp:
            response = await resp.text()
            print(f"Respuesta del servidor para método '{method_name}':", response)
            return response


async def listen_sse_and_get_tools(sse_url, base_url):
    session_id = None
    endpoint = None
    tools_received = False

    async with aiohttp.ClientSession() as session:
        async with session.get(sse_url) as resp:
            event = {}
            async for line in resp.content:
                line = line.decode().strip()
                if not line:
                    if "data" in event:
                        data = event["data"]
                        if data.startswith("/mcp/message?sessionId="):
                            endpoint = data
                            session_id = data.split("sessionId=")[-1]
                            print(f"Endpoint de mensajes detectado: {endpoint}")

                            # Prueba diferentes métodos
                            methods_to_try = [
                                "tools/list_tools",
                                "tools/get_tools",
                                "list_tools",
                                "get_tools",
                                "tools.list",
                                "tools.get",
                                "listTools",
                                "getTools",
                            ]

                            for method in methods_to_try:
                                print(f"Probando método: {method}")
                                response = await send_mcp_message(
                                    base_url, endpoint, session_id, method
                                )
                                if (
                                    "No handler registered" not in response
                                    and "Method not found" not in response
                                ):
                                    print(f"¡Método encontrado: {method}!")

                        elif data.strip().startswith("{") or data.strip().startswith(
                            "["
                        ):
                            try:
                                parsed = json.loads(data)
                                print("¡Herramientas recibidas!", parsed)
                                tools_received = True
                                return parsed
                            except Exception:
                                print(
                                    "Evento recibido (data no es JSON válido):", event
                                )
                        else:
                            print("Evento recibido (data no es JSON):", event)
                    else:
                        print("Evento recibido (sin data):", event)
                    event = {}
                    if tools_received:
                        break
                else:
                    if ":" in line:
                        key, value = line.split(":", 1)
                        event[key] = value.strip()
                    else:
                        pass


async def main():
    sse_url = "http://192.168.100.4:8080/sse"
    base_url = "http://192.168.100.4:8080"
    tools = await listen_sse_and_get_tools(sse_url, base_url)
    if tools:
        print("Lista de herramientas obtenida exitosamente!")


if __name__ == "__main__":
    asyncio.run(main())
