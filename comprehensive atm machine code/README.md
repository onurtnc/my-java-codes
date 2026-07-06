# ATM Simulator (Java)

This project is a **console-based ATM simulator** developed using **Java**.  
It aims to mimic the core functionalities of a real ATM, focusing on user interaction, error handling, and transaction history management.

## 🚀 Features
- 🔐 PIN authentication (3 attempt limit)
- 💰 Balance inquiry
- 💵 Cash deposit (with amount limits)
- 💸 Cash withdrawal (quick selection + custom amount)
- 📋 Transaction history (with date & time)
- ℹ️ Account information display
- 🔄 PIN change
- ❌ Protection and validation against invalid inputs

## 🛠 Technologies Used
- Java SE
- `Scanner` (user input handling)
- `ArrayList` (transaction history storage)
- `SimpleDateFormat` & `Date` (timestamp handling)

## How to Run
```bash
javac ATMSimulator.java
java ATMSimulator
```
