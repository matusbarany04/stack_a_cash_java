# Stack-a-Cash

Stack-a-Cash is an exciting Tic-Tac-Toe variant that adds a layer of strategy and complexity. Players aim to align three coins in a row on the top layer of a 3x3 grid. Coins come in three sizes, and smaller ones can stack on larger ones, allowing for dynamic gameplay and strategic depth.

---

## Getting Started

Follow these instructions to set up and run the project on your local machine for development and testing purposes. If you plan to deploy the project, refer to the deployment notes.

---

### Prerequisites

Ensure you have the following software and tools installed:

- **Java Development Kit (JDK) 17 or higher**

#### Installation Instructions

**For Debian-based systems:**
```bash
sudo apt install openjdk-17-jdk
```

**For Arch-based systems:**
```bash
sudo pacman -S jdk17-openjdk
```

---

## Running the App

To run the application, follow these steps:

1. Clone the repository:
   ```bash
   git clone https://github.com/matusbarany04/stack-a-cash.git
   cd stack-a-cash
   ```

2. Compile the application using the Java compiler:
   ```bash
   javac -d out src/**/*.java
   ```

3. Run the application:
   ```bash
   java -cp out com.stackacash.Main
   ```

---

## How to Play

1. **Objective:** Align three coins of any size in a row, column, or diagonal on the top layer of the grid.
2. **Rules:**
   - Coins come in three sizes: small, medium, and large.
   - Smaller coins can stack on top of larger ones, creating a layered grid.
   - Players alternate turns, placing coins or stacking them strategically.
   - The first player to align three coins wins the game.

---

## Development Notes

- The project is structured with a focus on modularity and maintainability.
- Contributions and improvements are welcome. Please follow the guidelines in the `CONTRIBUTING.md` file.

---

## Authors

- **Matúš Bárány**  
  [GitHub Profile](https://github.com/matusbarany04)

---

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

---

## Acknowledgments

- Thanks to the open-source community for providing resources and inspiration.
