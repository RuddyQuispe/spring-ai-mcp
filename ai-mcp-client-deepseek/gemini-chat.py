from google import genai

client = genai.Client(api_key="abcdefghijklamnopqrs-1234567890-ABCDEF")

response = client.models.generate_content(
    model="gemini-2.5-flash",
    contents="Explicame sobre spring boot eureka",
)

print(response.text)
