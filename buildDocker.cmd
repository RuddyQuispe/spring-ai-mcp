echo 'construyendo imagenes'

docker build -f ai-mcp-server/Dockerfile ai-mcp-server/ -t ai-mcp-server:0.0.1
docker build -f ai-mcp-client/Dockerfile ai-mcp-client/ -t ai-mcp-client:0.0.1

echo 'Se finalizo la construccion de imagenes'