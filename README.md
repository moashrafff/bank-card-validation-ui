# 💳 Bank Card Validator

A lightweight Kotlin library for **validating and detecting payment card details**, bundled with **Jetpack Compose UI components** for seamless integration into Android apps.

---

## ✨ Features

- 🔍 **Card brand detection**  
  Detect Visa, Mastercard, and other brands based on card number patterns.

- ✅ **Validation utilities**  
  - Card number format & Luhn checksum validation  
  - CVV format & length validation per brand  
  - Expiry date parsing and expiration check  

- 🎨 **UI components (Jetpack Compose)**  
  Ready-to-use composables for:
  - Card Number  
  - Expiry Date  
  - CVV  
  Each with real-time validation and error feedback.

- 📦 **Published via JitPack**  
  - `cardvalidationengine` → core validation logic (JAR)  
  - `cardvalidator-ui` → Compose UI components (AAR)  

---

## 📦 Installation

Add [JitPack](https://jitpack.io) to your project:

```gradle
repositories {
    maven { url = uri("https://jitpack.io") }
}
```

Then add the dependencies:

```gradle
// Core validation (pure Kotlin/JVM)
implementation("com.github.moashrafff:BankCardValidator:cardvalidationengine:<version>")

// UI components (Jetpack Compose, Android only)
implementation("com.github.moashrafff:BankCardValidator:cardvalidator-ui:<version>")
```

---

## 🚀 Usage

### Core API

```kotlin
// Detect card brand
val brand = CardBrandDetector.detectCardType("4111111111111111")

// Validate card number
val result = CardValidator.isCardNumberValid("4111111111111111")

// Validate CVV
val cvvResult = CardValidator.isCvvValid("4111111111111111", "123")

// Validate expiry date
val expiryResult = CardValidator.isExpiryDateValid("12/25")
```

### Compose UI Components

```kotlin
@Composable
fun PaymentForm() {
    Column {
        CardNumberTextField(
            onCardNumberChange = { number -> /* handle input */ },
            onCardNumberValidChange = { isValid -> /* react to validation */ }
        )

        CardExpiryDateTextField(
            onExpiryDateChange = { expiry -> /* handle input */ },
            onExpiryDateValidChange = { isValid -> /* react to validation */ }
        )

        CardCvvTextField(
            onCvvChange = { cvv -> /* handle input */ },
            onCvvValidChange = { isValid -> /* react to validation */ }
        )
    }
}
```

---

## 📚 Examples

- Real-time validation feedback  
- Automatic brand logo display  
- Smart expiry date formatting  

*(Add screenshots or gifs here to showcase the UI.)*

---

## ⚖️ License

This project is licensed under the [MIT License](LICENSE).  
You are free to use it in commercial and open-source projects, with attribution.

---

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!  
Feel free to open a pull request or start a discussion.
