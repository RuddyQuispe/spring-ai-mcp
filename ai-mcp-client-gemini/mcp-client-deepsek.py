from mcp.client.sse import sse_client
from mcp import ClientSession
from openai import OpenAI


async def main():
    mcp_server_url = "http://192.168.100.4:8080/sse"

    async with sse_client(mcp_server_url) as (read_stream, write_stream):
        async with ClientSession(read_stream, write_stream) as session:
            await session.initialize()
            tools = await session.list_tools()
            # response = await session.call_tool("getWeather", {"city": ""})
            # print("Respuesta del servidor:", response)

            client = OpenAI(api_key="sk-ABCDEFGHIJKLMNOPQRSTUVXYZabcdefg", base_url="https://api.deepseek.com")
            messages = [{"role": "user", "content": "¿Cuál es el clima en Madrid?"}]

            def convert_tool_for_deepseek(tool):
                return {
                    "type": "function",
                    "function": {
                        "name": tool["name"],
                        "description": tool.get("description", ""),
                        "parameters": tool.get("inputSchema", {
                            "type": "object",
                            "properties": {},
                            "required": []
                        })
                    }
                }

            tools_json = []
            for tool in tools.tools:
                tools_json.append(convert_tool_for_deepseek(tool))
            print("tools_json: ")
            print(tools_json)
            response = client.chat.completions.create(
                model="deepseek-chat",
                messages=messages,
                tools=tools_json
            )
            print(response.choices[0].message.content)


if __name__ == "__main__":
    import asyncio
    asyncio.run(main())