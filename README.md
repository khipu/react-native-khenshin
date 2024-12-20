# react-native-khenshin

:warning: **Deprecated**: please use the new and improved [react-native-khipu plugin](https://www.npmjs.com/package/react-native-khipu)! :warning:

## Add the library to your project

    yarn add https://github.com/khipu/react-native-khenshin

## Install and configure

- [x] [react-native 0.60.x to 0.72.x](docs/INSTALL.0.60.x--0.72.x.md)

## Usage

```typescript
import React from 'react';
import {SafeAreaView, ScrollView, Button} from 'react-native';
import Khipu from 'react-native-khenshin';

function App(this: any): JSX.Element {
    const onStartPayment = () => {
        Khipu.startPaymentById('mboyd2tem42r')
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
            .catch((err: any) => console.log({err}));
    };

    return (
        <SafeAreaView>
            <ScrollView>
                <Button title={'pagar'} onPress={onStartPayment} />
            </ScrollView>
        </SafeAreaView>
);
}

export default App;

```
## Example project

- [react-native-khenshin-demo](https://github.com/khipu/react-native-khenshin-demo)
