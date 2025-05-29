# 🎮 Stick Hero Game (Java + Maven)

A thrilling command-line game inspired by the classic **Stick Hero**, developed using **Java** and **Maven**. The objective is simple yet addictive: help the character cross platforms by growing sticks of just the right length. Built following OOP principles, this project demonstrates core game logic, input handling, and a fun CLI interface.

---

## 🚀 Features

### 🕹️ **Gameplay Highlights**
- 🌉 **Stretch the stick to bridge platforms**
- ❌ **Fall if the stick is too short or too long**
- 🔄 **Automatic scoring and restart mechanism**
- 🧠 **Challenge your timing and judgment**

---

## 🎮 How to Play
- Press `Enter` or a specific key (e.g., `G`) to **start growing the stick**.
- Release the key to **drop the stick horizontally**.
- If the stick reaches the next platform, your hero **walks across** and gains a point.
- If the stick is too short or too long, the hero **falls** and the game is over.
- Option to **restart or exit** after each round.

---
## ⚙️ Prerequisites & Setup

### 📦 Requirements
- Java JDK 8+
- Maven
- Command-line environment

### 🛠️ Build and Run

```bash
# Clone the repo
git clone https://github.com/yourusername/StickHero

# Navigate to the project directory
cd stick-hero-game

# Compile the project
mvn compile

# Run the game
mvn exec:java -Dexec.mainClass="com.stickhero.Main"
