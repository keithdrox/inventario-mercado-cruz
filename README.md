git clone https://github.com/keithdrox/inventario-mercado-cruz.git
cd invetario-mercado-cruz
cp .env.example.env
docker compose up -d --build
# API en http://localhost:8080/api/v1/productos
# Compilar el informe:
cd docs/informe && pdflatex informe && bibtex informe && pdflatex informe && pdflatex informe