# SOLID-Based Order Processing System

A Java implementation of the **SOLID Design Principles** for efficient order processing and management. This project demonstrates how to create a flexible, maintainable, and extensible order processing system by applying SOLID principles to separate concerns and enable easy extension without modifying existing code.

## 📋 Overview

The SOLID-Based Order Processing System allows you to process orders with automatic invoice generation and email notifications. The system is built using SOLID principles, making it highly modular and allowing you to easily swap implementations or add new features without changing the core orchestration logic.

## 🏗️ Architecture

This project implements the **SOLID Design Principles** with the following components:

- **Order Interface**: Defines the contract for order operations
- **InvoiceGenerator Interface**: Defines the contract for invoice generation
- **EmailNotifier Interface**: Defines the contract for email notifications
- **OrderManager**: Orchestrates the entire order processing flow
- **Concrete Implementations**: OrderProcessor, InvoiceService, and EmailService
- **Main Application**: Demonstrates the SOLID principles in action

### Design Pattern Benefits

- **Single Responsibility**: Each class has one reason to change
- **Open/Closed**: Easy to extend with new implementations without modifying existing code
- **Liskov Substitution**: Any implementation can be swapped without breaking the system
- **Interface Segregation**: Focused interfaces avoid fat, monolithic contracts
- **Dependency Inversion**: High-level modules depend on abstractions, not concrete classes

## 📊 UML Class Diagram

![image](https://github.com/user-attachments/assets/e2d22dfc-e584-43a8-822b-7b5291cc0ccd)

The following diagram illustrates:
- The architecture of the SOLID-Based Order Processing System
- Relationships between the core components:
  - The `Order`, `InvoiceGenerator`, and `EmailNotifier` interfaces
  - Concrete implementations: `OrderProcessor`, `InvoiceService`, `EmailService`
  - The `OrderManager` orchestrator that depends on abstractions
  - The main application class `OrderTest`
- How SOLID principles are applied in this project

## 🚀 Features

- **Modular Order Processing**: Separate interfaces for order operations, invoice generation, and notifications
- **Dependency Injection**: Constructor-based injection for loose coupling
- **Extensible Design**: Easy to add new implementations without changing existing code
- **Clean Architecture**: Well-structured, maintainable code following SOLID principles
- **Type-Safe Operations**: Strong typing for all operations
- **Testable Components**: Easy to mock and test individual components

## 📁 Project Structure

```
order-system-solid/
├── src/
│   ├── Order.java                  # Order interface
│   ├── OrderProcessor.java         # Order implementation
│   ├── InvoiceGenerator.java       # Invoice generation interface
│   ├── InvoiceService.java         # Invoice generator implementation
│   ├── EmailNotifier.java          # Email notification interface
│   ├── EmailService.java           # Email notifier implementation
│   ├── OrderManager.java           # Main orchestrator
│   └── OrderTest.java              # Main application demo
└── README.md                       # Project documentation
```

## 🛠️ Installation & Setup

### Prerequisites

- Java Development Kit (JDK) 11 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, VS Code, etc.)

### Getting Started

1. **Clone or download** the project files
2. **Navigate** to the project directory
3. **Compile** the Java files:
   ```bash
   javac src/*.java
   ```
4. **Run** the application:
   ```bash
   java -cp src OrderTest
   ```

## 📖 Usage

### Basic Usage

The main application (`OrderTest.java`) demonstrates how to use the SOLID-based system:

```java
// Create concrete implementations
Order order = new OrderProcessor();
InvoiceGenerator invoiceGenerator = new InvoiceService();
EmailNotifier emailNotifier = new EmailService();

// Create the orchestrator with dependency injection
OrderManager orderManager = new OrderManager(order, invoiceGenerator, emailNotifier);

// Process an order
orderManager.processOrder(10.0, 2, "John Doe", "123 Main St", "order_123.pdf", "johndoe@example.com");
```

### Expected Output

When you run the application, you'll see:

```
Order total: $20.0
Order placed for: John Doe at 123 Main St
Invoice generated: order_123.pdf
Email notification sent to: johndoe@example.com
```

### Swapping Implementations

One of the key benefits of SOLID principles is the ability to swap implementations:

```java
// Create a custom SMS notifier
class SmsNotifier implements EmailNotifier {
    @Override
    public void sendEmailNotification(String email) {
        System.out.println("[SMS fallback] Notification sent to: " + email);
    }
}

// Use the custom implementation
EmailNotifier notifier = new SmsNotifier();
OrderManager manager = new OrderManager(new OrderProcessor(), new InvoiceService(), notifier);
```

### Adding New Invoice Strategies

```java
// Create a custom HTML invoice generator
class HtmlInvoiceService implements InvoiceGenerator {
    @Override
    public void generateInvoice(String fileName) {
        System.out.println("HTML invoice generated: " + fileName.replace(".pdf", ".html"));
    }
}

// Use the custom implementation
InvoiceGenerator generator = new HtmlInvoiceService();
OrderManager manager = new OrderManager(new OrderProcessor(), generator, new EmailService());
```

## 🔧 Extending the Project

### Adding New Order Types

To add a new order type (e.g., `PremiumOrder`):

1. **Create** a new class implementing the `Order` interface
2. **Implement** the required methods (`calculateTotal()`, `placeOrder()`)
3. **Use** it with the existing `OrderManager`

Example:
```java
public class PremiumOrder implements Order {
    @Override
    public void calculateTotal(double price, int quantity) {
        double total = price * quantity * 1.1; // 10% premium
        System.out.println("Premium order total: $" + total);
    }
    
    @Override
    public void placeOrder(String customerName, String address) {
        System.out.println("Premium order placed for: " + customerName + " at " + address);
    }
}
```

### Adding New Notification Channels

```java
public class SlackNotifier implements EmailNotifier {
    @Override
    public void sendEmailNotification(String email) {
        System.out.println("Slack notification sent to: " + email);
    }
}
```

## 🎯 Design Patterns Used

### Dependency Injection Pattern
- **Purpose**: Inject dependencies through constructors for loose coupling
- **Benefits**: Easy to swap implementations and test components in isolation
- **Implementation**: `OrderManager` receives implementations via constructor

### Strategy Pattern
- **Purpose**: Define a family of algorithms and make them interchangeable
- **Benefits**: Runtime selection of different behaviors
- **Implementation**: `Order`, `InvoiceGenerator`, and `EmailNotifier` interfaces

### Interface Segregation Pattern
- **Purpose**: Create focused interfaces instead of monolithic ones
- **Benefits**: Clients only depend on methods they actually use
- **Implementation**: Separate interfaces for different responsibilities

## 🎨 SOLID Principles Applied

### Single Responsibility Principle (SRP)
- Each class has one reason to change
- `OrderProcessor` handles order operations only
- `InvoiceService` handles invoice generation only
- `EmailService` handles email notifications only

### Open/Closed Principle (OCP)
- Open for extension, closed for modification
- Add new implementations without changing existing code
- `OrderManager` doesn't need changes when adding new implementations

### Liskov Substitution Principle (LSP)
- Any implementation can be substituted without breaking the system
- All implementations properly fulfill their interface contracts

### Interface Segregation Principle (ISP)
- Focused interfaces avoid fat, monolithic contracts
- `Order`, `InvoiceGenerator`, and `EmailNotifier` are specific and focused

### Dependency Inversion Principle (DIP)
- High-level modules depend on abstractions, not concrete classes
- `OrderManager` depends on interfaces, not implementations

## 🤝 Contributing

Feel free to contribute to this project by:
- Adding new order types or implementations
- Improving documentation
- Enhancing the SOLID principles implementation
- Adding unit tests
- Creating new notification channels or invoice formats

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

**Note**: This implementation demonstrates clean code principles and SOLID design patterns best practices. The SOLID principles are particularly useful when building systems that need to be flexible, maintainable, and extensible. This approach is ideal when you need interchangeable behaviors (e.g., different order types, invoice formats, or notification channels) without modifying the orchestration code (`OrderManager`).
