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
implementation("com.github.moashrafff:cardvalidator-ui:<version>")
```

---

## 🚀 Usage

### Card Number Input
```kotlin
CardNumberTextField(
    onCardNumberChange = { number -> /* handle input */ },
    onCardNumberValidChange = { isValid -> /* react to validation */ }
)
```

### Expiry Date Input
```kotlin
CardExpiryDateTextField(
    onExpiryDateChange = { expiry -> /* handle input */ },
    onExpiryDateValidChange = { isValid -> /* react to validation */ }
)
```

### CVV Input
```kotlin
CardCvvTextField(
    onCvvChange = { cvv -> /* handle input */ },
    onCvvValidChange = { isValid -> /* react to validation */ }
)
```

---

## 📚 Examples

- Real-time error messages  
- Smart expiry date formatting  
- Auto focus navigation on completion  

*(Add screenshots or gifs here to showcase the UI.)*

---

## ⚖️ License

This project is licensed under the [MIT License](LICENSE).  
You are free to use it in commercial and open-source projects, with attribution.

---

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!  
Feel free to open a pull request or start a discussion.
