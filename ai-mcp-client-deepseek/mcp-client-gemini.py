from mcp.client.sse import sse_client
from mcp import ClientSession
from google import genai
import asyncio

async def main():
    mcp_server_url = "http://192.168.100.4:8080/sse"  # Tu endpoint SSE

    async with sse_client(mcp_server_url) as (read_stream, write_stream):
        async with ClientSession(read_stream, write_stream) as session:
            await session.initialize()
            tools = await session.list_tools()
            print("tools MCP server: ", tools)
            # response = await session.call_tool("getWeather", {"city": ""})
            # print("Respuesta del servidor:", response)

            client = genai.Client(api_key="abcdefghijklamnopqrs-1234567890-ABCDEF")
            prompt = "Saludame!"

            response = await client.aio.models.generate_content(
                model="gemini-2.5-flash",
                contents=prompt,
                config = genai.types.GenerateContentConfig(
                    tools=[session] # uses the session, will automatically call the tool using automatic function calling
                )
            )
            
            print(response.text)


if __name__ == "__main__":
    try:
        asyncio.run(main())
    except Exception as e:
        import traceback
        traceback.print_exc()
