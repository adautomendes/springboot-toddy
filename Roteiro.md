# Roteiro

- Instalar Jenkins
- Configurar env vars
- Código (fork deste repo)
- Rodar comandos Maven (clean, install, verify, test)
  - mvn test -Dtest=ToddyControllerTest -Dtoddy.size.min=${toddySizeMin} -Dtoddy.size.max=${toddySizeMax}
- Subir Jenkins
- Configurar BlueOcean
- Codificar pipeline (build, test - com 2 parâmetros e doc - com artefato)