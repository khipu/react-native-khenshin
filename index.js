import { NativeModules } from 'react-native';

const { RNKhenshin } = NativeModules;

export default {

    startPaymentById: (paymentId) => {
        return new Promise((resolve, reject) => {
            try {
                RNKhenshin.startPaymentById(paymentId, (status, result) => {
                    resolve({status, result: JSON.parse(result)})
                });
            } catch(e) {
                reject(e)
            }
        });
    }
}
