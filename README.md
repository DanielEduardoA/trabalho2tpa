# trabalho2tpa
Para rodar o projeto é necessário ter java 8 ou java 11 instalado na maquina

Para rodar os testes unitários baixe o código na maquina. No prompt de comandos do Windows navegue ate a pasta do projeto e entre nela. Depois execute o seguinte comando: 
.\mvnw clean verify
Observacao não é preciso ter o maven instalado na maquina pois exxiste o wrapper do maven dentro do projeto

Para ver o resultado dos testes basta executar o seguinte comando apos executar o primeiro comando:
start target\site\jacoco\index.html
