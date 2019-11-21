import { NativeModules } from 'react-native';
var CryptoJS = require("crypto-js");

const { RNKhipu } = NativeModules;

_merchantId = null;
_secret = null;
_paymentsUrl = 'https://khipu.com/api/2.0/payments';

const _getSignature = (method, url, query) => {
  var toSign = method + "&" + encodeURIComponent(url);
  const querySorted = Object.keys(query)
              .sort()
              .map(q => encodeURIComponent(q)+"="+encodeURIComponent(query[q]))
              .join("&");
  toSign += "&" + querySorted;
  console.log(toSign);
  return CryptoJS.HmacSHA256(toSign, _secret)
};

const _getHeaders = (method, url, data) => ({
    'Content-Type': 'application/x-www-form-urlencoded',
    'Authorization': _merchantId + ':' + _getSignature(method, url, data)
});

export default {
    init: ({merchantId, secret}) => {
        _merchantId = merchantId;
        _secret = secret;
    },

    createPayment: (data) => {
        const method = 'POST';
        const url = _paymentsUrl;
        const query = Object.keys(data)
            .sort()
            .map(q => encodeURIComponent(q)+"="+encodeURIComponent(data[q]))
            .join("&");
        const headers = _getHeaders(method, url, data)
        const options = { method, headers, body: query };
        console.log(options);

        return fetch(url, options).then(response => response.json())
    },

    getPaymentById: (id) => {
        const method = 'GET';
        const url = _paymentsUrl + '/' + id;
        const query = { };
        const headers = _getHeaders(method, url, query)
        const options = { method, headers };

        return fetch(url, options).then(response => response.json())
    },

    startPaymentById: (paymentId) => {
        return new Promise((resolve, reject) => {
            try {
                RNKhipu.startPaymentById(paymentId, (status, result) => {
                    resolve({status, result: JSON.parse(result)})
                });
            } catch(e) {
                reject(e)
            }
        });
    }
}
