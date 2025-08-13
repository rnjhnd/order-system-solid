# SOLID-Based Order Processing System

A Java refactoring that demonstrates SOLID principles by decoupling order processing, invoice generation, and notifications into cohesive components coordinated by an orchestration layer.

## 📋 Table of Contents

- [Overview](#overview)
- [Architecture](#architecture)
- [Features](#features)
- [Project Structure](#project-structure)
- [Installation & Usage](#installation--usage)
- [Code Examples](#code-examples)
- [Design Principles Used](#design-principles-used)
- [UML Class Diagram](#uml-class-diagram)
- [Future Enhancements](#future-enhancements)
- [Contributing](#contributing)
- [License](#license)

## 🎯 Overview

This project implements an order processing flow that adheres to SOLID principles. Responsibilities are split across small interfaces and classes so that behavior can be extended without modifying existing code.

### Key Benefits
- **Decoupled components**: Orchestration depends on interfaces, not concrete classes
- **Clear responsibilities**: Each class has a single reason to change
- **Extensible architecture**: Add new invoice/notifier/order implementations safely
- **Testability**: Swap in fakes/mocks via interfaces

## 🏗️ Architecture

### Core Interfaces and Classes
- **`Order`**: Contract for order operations (`calculateTotal`, `placeOrder`)
- **`OrderProcessor`**: Concrete implementation of `Order`
- **`InvoiceGenerator`**: Contract for invoice generation
- **`InvoiceService`**: Concrete invoice generator
- **`EmailNotifier`**: Contract for notifications
- **`EmailService`**: Concrete email notifier
- **`OrderManager`**: Orchestrates the flow by depending on the interfaces above

### Design Principles
- **Loose Coupling** through constructor injection and interfaces
- **Single Responsibility** per class
- **Open/Closed** for adding new behaviors without modifying existing code

## ✨ Features

### Order Processing
- Calculate totals based on price and quantity
- Place orders for a given customer and address

### Invoice Generation
- Generate invoice artifacts (e.g., file names) via `InvoiceGenerator`

### Notifications
- Send email notifications via `EmailNotifier`

### System Flexibility
- Swap implementations without changing orchestration
- Add new notifier or invoice strategies seamlessly

## 📁 Project Structure

```
order-system-solid/
├── src/
│   ├── EmailNotifier.java       # Interface for notifications
│   ├── EmailService.java        # EmailNotifier implementation
│   ├── InvoiceGenerator.java    # Interface for invoices
│   ├── InvoiceService.java      # InvoiceGenerator implementation
│   ├── Order.java               # Interface for order operations
│   ├── OrderProcessor.java      # Order implementation
│   ├── OrderManager.java        # Orchestrates via dependency injection
│   └── OrderTest.java           # Demo application (main method)
└── README.md                    # Project documentation
```

## 🚀 Installation & Usage

### Prerequisites
- Java 11 or higher

### Running the Application
1. Compile all classes from the project root
   ```bash
   javac *.java
   ```
2. Run the demo
   ```bash
   java OrderTest
   ```

### Expected Output
```
Order total: $20.0
Order placed for: John Doe at 123 Main St
Invoice generated: order_123.pdf
Email notification sent to: johndoe@example.com
```

## 💻 Code Examples

### Orchestrating the Flow
```java
Order order = new OrderProcessor();
InvoiceGenerator invoiceGenerator = new InvoiceService();
EmailNotifier emailNotifier = new EmailService();

OrderManager orderManager = new OrderManager(order, invoiceGenerator, emailNotifier);
orderManager.processOrder(10.0, 2, "John Doe", "123 Main St", "order_123.pdf", "johndoe@example.com");
```

### Swapping Implementations (Open/Closed + DIP)
```java
class SmsNotifier implements EmailNotifier {
  @Override
  public void sendEmailNotification(String email) {
        System.out.println("[SMS fallback] Notification sent to: " + email);
    }
}

EmailNotifier notifier = new SmsNotifier();
OrderManager manager = new OrderManager(new OrderProcessor(), new InvoiceService(), notifier);
```

### Adding a New Invoice Strategy
```java
class HtmlInvoiceService implements InvoiceGenerator {
    @Override
    public void generateInvoice(String fileName) {
        System.out.println("HTML invoice generated: " + fileName.replace(".pdf", ".html"));
    }
}

InvoiceGenerator generator = new HtmlInvoiceService();
OrderManager manager = new OrderManager(new OrderProcessor(), generator, new EmailService());
```

## 🎨 Design Principles Used
- **Single Responsibility Principle (SRP)**: Each class focuses on one responsibility
- **Open/Closed Principle (OCP)**: Extend via new implementations without modifying existing classes
- **Liskov Substitution Principle (LSP)**: Any `Order`/`InvoiceGenerator`/`EmailNotifier` implementation can be used interchangeably
- **Interface Segregation Principle (ISP)**: Focused interfaces (`Order`, `InvoiceGenerator`, `EmailNotifier`) avoid fat interfaces
- **Dependency Inversion Principle (DIP)**: `OrderManager` depends on abstractions and receives them via constructor injection

## 📊 UML Class Diagram
<img width="1800" height="1240" alt="UML Class Diagram" src="https://github.com/user-attachments/assets/e2d22dfc-e584-43a8-822b-7b5291cc0ccd" />

The diagram shows the relationships and flow:
- `Order`, `InvoiceGenerator`, and `EmailNotifier` are interfaces that define contracts.
- `OrderProcessor`, `InvoiceService`, and `EmailService` are concrete implementations of those interfaces.
- `OrderManager` depends only on the interfaces and receives implementations via constructor injection.
- `OrderTest` composes the system by instantiating implementations and passing them to `OrderManager`.
- Runtime flow: `OrderManager.processOrder(...)` calls `Order.calculateTotal(...)` and `Order.placeOrder(...)`, then `InvoiceGenerator.generateInvoice(...)`, and finally `EmailNotifier.sendEmailNotification(...)`.

## 🔮 Future Enhancements
- Multiple notification channels (SMS, Slack, Push)
- Additional invoice formats (PDF, HTML, CSV)
- Persistence layer for orders and invoices
- Comprehensive unit tests and CI setup

## 🤝 Contributing
Contributions are welcome!
1. Fork this repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit changes (`git commit -m 'Add amazing feature'`)
4. Push to your branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License
This project is open source and available under the [MIT License](LICENSE).

---

**Note:** This implementation demonstrates clean code and SOLID principles. It uses Strategy-style interfaces (`Order`, `InvoiceGenerator`, `EmailNotifier`) and constructor-based dependency injection to keep components decoupled and easily swappable. This approach is ideal when you need interchangeable behaviors (e.g., different invoice formats or notification channels) without modifying the orchestration code (`OrderManager`).
