# react-native-khenshin

## Add the library to your project

    npm install https://github.com/khipu/react-native-khenshin#1.0.3 --save

## Install and configure

- [x] [react-native 0.59.x](docs/INSTALL.0.59.x.md)
- [x] [react-native 0.60.x to 0.62.x](docs/INSTALL.0.60.x--0.62.x.md)

## Usage

```javascript
import React from 'react';
import {TouchableOpacity, Text} from 'react-native';
import Khipu from 'react-native-khenshin';

export default class MyApp extends React.Component {
 
  onStartPayment = () => {
    Khipu.startPaymentById('paymentId')
      .then(({status, result}) => {
        if (status === 'CONCILIATING') {
         // khipu is conciliating the payment
        } else if (status === 'USER_CANCELED') {
        // The user cancelled the transaction
        } else {
        // Error!, see `result` for details
        console.log(result);
        }
      })
      .catch(err => console.log({err}));
  };
 
  render() {
    return (
      <TouchableOpacity onPress={this.onStartPayment}>
        <Text>Start payment</Text>
      </TouchableOpacity>
    );
  }
}
```
