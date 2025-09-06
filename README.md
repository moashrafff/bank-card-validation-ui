# 💳 Bank Card Validator UI

A lightweight Android library providing **Jetpack Compose UI components** for validating and handling payment card input fields with real-time feedback.  
Built on top of the Bank Card Validator engine.

---

## ✨ Features

- 🎨 **UI components (Jetpack Compose)**  
  Ready-to-use composables for:
  - **Card Number** input with brand detection & validation  
  - **Expiry Date** input with auto-formatting and expiration check  
  - **CVV** input with per-brand length validation  

- ⚡ **Real-time validation feedback**  
  Shows error messages as the user types.  

- 🖼️ **Brand icons support**  
  Automatically displays card brand logos (Visa, Mastercard, etc.).  

- 📦 **Published via JitPack**  
  Distributed as an AAR library.

---

## 📦 Installation

Add [JitPack](https://jitpack.io) to your project:

```gradle
repositories {
    maven { url = uri("https://jitpack.io") }
}
```

Then add the dependency:

```gradle
implementation("com.github.moashrafff:bank-card-validation-ui:<version>")
```

---

## 🚀 Usage

### Card Number Input
```kotlin
CardNumberTextField(
    modifier = Modifier.fillMaxWidth(),
    invalidFormatErrorMessage = "Card number must contain digits only",
    invalidCardNumberErrorMessage = "Invalid card number",
    unknownCardBrandErrorMessage = "Unknown card brand",
    textFieldLabel = "Card Number",
    completeFocusDirection = FocusDirection.Next,
    clearIcon = Icons.Default.Clear,
    errorMessageFontSize = 12f,
    onCardNumberChange = { number ->
        println("Card number changed: $number")
    },
    onCardNumberValidChange = { isValid ->
        println("Card number valid: $isValid")
    }
)
```

### Expiry Date Input
```kotlin
CardExpiryDateTextField(
    modifier = Modifier.fillMaxWidth(),
    invalidFormatErrorMessage = "Expiry must be digits only",
    invalidMonthErrorMessage = "Invalid month",
    expiredCardErrorMessage = "Card expired",
    tooFarMessage = "Date too far in the future",
    textFieldLabel = "Expiry Date",
    completeFocusDirection = FocusDirection.Next,
    clearIcon = Icons.Default.Clear,
    errorMessageFontSize = 12f,
    onExpiryDateChange = { expiry ->
        println("Expiry changed: $expiry")
    },
    onExpiryDateValidChange = { isValid ->
        println("Expiry valid: $isValid")
    }
)
```

### CVV Input
```kotlin
CardCvvTextField(
    modifier = Modifier.fillMaxWidth(),
    invalidFormatErrorMessage = "CVV must contain digits only",
    invalidCvvLengthErrorMessage = "Invalid CVV length",
    textFieldLabel = "CVV",
    canCloseKeyBoardAfterValidation = true,
    optionalCardNumber = "4111111111111111", // Helps validate CVV length by brand
    clearIcon = Icons.Default.Clear,
    errorMessageFontSize = 12f,
    onCvvChange = { cvv ->
        println("CVV changed: $cvv")
    },
    onCvvValidChange = { isValid ->
        println("CVV valid: $isValid")
    }
)
```

---

## 📚 Examples

- Real-time error messages  
- Smart expiry date formatting  
- Auto focus navigation on completion  

| :cardvalidator-ui               |
|---------------------------------|
| ![card-validator-ui](https://github.com/user-attachments/assets/7f568333-4160-4601-a4ce-c53d77b67110) |

---

## ⚖️ License

This project is licensed under the [MIT License](LICENSE).  
You are free to use it in commercial and open-source projects, with attribution.

---

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!  
Feel free to open a pull request or start a discussion.
