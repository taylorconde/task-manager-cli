# 📋 Catálogo de Tarefas

Aplicação de linha de comando em Java para gerenciar produtos e tarefas (*todos*).  
Projeto desenvolvido com **Gradle**, **Java 21**, **Jackson** e **Lombok**.

---

## 🚀 Funcionalidades

- Listar produtos  
- Buscar produto por nome  
- Listar todos  
- Adicionar novo todo  
- Alternar status de um todo (concluído / pendente)  
- Remover todo  

---

## 🛠️ Tecnologias

- **Java 21**  
- **Gradle** (plugin `application`)  
- **Jackson** (serialização/deserialização JSON)  
- **Lombok** (redução de boilerplate)  
- **JUnit 5** (testes)  

---

## 📦 Como compilar e rodar

### 1. Clonar o repositório
```bash
git clone https://github.com/seu-usuario/catalogo-tarefas.git
cd catalogo-tarefas
```

### 2. Rodar com Gradle
```bash
./gradlew run --console=plain --no-daemon
```

### 3. Rodar com script `run.sh`
```bash
chmod +x run.sh
./run.sh
```

Esse script compila o projeto e executa o *fat jar* automaticamente.

### 4. Gerar *fat jar* manualmente (opcional)
```bash
./gradlew fatJar
java -jar app/build/libs/app-0.0.1-SNAPSHOT-all.jar
```

---

## ▶️ Executando pela IDE

Além do terminal, você pode rodar o projeto direto pela sua IDE favorita:

- **IntelliJ IDEA**: abra o projeto, vá até `Main.java` e clique em "Run".  
- **Eclipse**: importe como projeto Gradle e rode `Main.java` como aplicação Java.  
- **VS Code**: abra a pasta, vá até `Main.java` e clique em "Run | Debug".  

O menu interativo aparecerá no console da IDE.

---

## 📂 Estrutura do projeto

```
catalogo-tarefas/
 ├── app/
 │   ├── src/main/java/br/com/taylor/catalogotarefas/
 │   │    ├── Main.java
 │   │    ├── model/        # Entidades (Product, Todo)
 │   │    ├── service/      # Lógica de negócio (ProductService, TodoService)
 │   │    ├── util/         # Utilitários (ConsolePrinter)
 │   │    └── http/         # Cliente HTTP
 │   └── build.gradle.kts
 ├── run.sh
 ├── settings.gradle.kts
 └── README.md
```

---

## 🖥️ Uso

Ao rodar, o menu interativo aparece:

```
=== Catálogo de Tarefas ===
1. Listar produtos
2. Buscar produto por nome
3. Listar todos
4. Adicionar todo
5. Alternar status de um todo
6. Remover todo
0. Sair
Escolha uma opção:
```

Digite o número da opção desejada e siga as instruções no console.

---

## ✅ Próximos passos (opcional)

- Adicionar testes unitários para `ProductService` e `TodoService`.  
- Melhorar mensagens de erro e validação de entrada.  
- Documentar exemplos de uso com dados reais.  

---
