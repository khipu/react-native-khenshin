# react-native-khenshin

## Add the library to yor project

    npm install https://github.com/khipu/react-native-khenshin --save

## Install and configure

- [ ] [react-native 0.49.x](docs/INSTALL.0.49.x.md)
- [x] [react-native 0.59.x](docs/INSTALL.0.59.x.md)
- [x] [react-native 0.60.x to 0.62.x](docs/INSTALL.0.60.x--0.62.x.md)


## Usage

```javascript
import Khipu from 'react-native-khenshin';

Khipu.startPaymentById('paymentId').then(payment => console.log(payment));

```
