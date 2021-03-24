#import "RNKhenshin.h"
#import <React/RCTBridge.h>
#import <React/RCTBundleURLProvider.h>
#import <React/RCTRootView.h>
#import <khenshin/khenshin.h>

@implementation RNKhenshin

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

- (void) configureEngine {
  @try {
    [KhenshinInterface initWithBuilderBlock:^(KhenshinBuilder *builder) {
      builder.APIUrl = @"https://khipu.com/app/enc/";
      builder.allowCredentialsSaving = YES;
      builder.keepCookies = NO;
      builder.decimalSeparator = @".";
      builder.groupingSeparator = @",";
      builder.skipExitPage = NO;
      builder.processHeader = nil;
      builder.hideWebAddressInformationInForm = YES;
      //builder.automatonTimeout = 90.0;
    }];
  } @catch (NSException *exception) {
    NSLog(@"Khenshin failed to init: %@", [exception reason]);
  }

  [KhenshinInterface setAutomatonTimeout:90.0];
}

RCT_EXPORT_MODULE()

// Export methods to a native module
// https://facebook.github.io/react-native/docs/native-modules-ios.html
RCT_EXPORT_METHOD(startPaymentById
  : (NSString *)paymentId
  : (RCTResponseSenderBlock) callback
) {
  [[NSOperationQueue mainQueue] addOperationWithBlock:^{
    if (![KhenshinInterface validateInitialization]) {
      [self configureEngine];
    }

    [KhenshinInterface startEngineWithPaymentExternalId:paymentId
                                         userIdentifier:@""
                                      isExternalPayment:NO
                                                success:^(NSURL *returnURL) {
                                                  callback(@[ @"CONCILIATING", @"{\"success\": true}" ]);
                                                }
                                                failure:^(NSURL *returnURL) {
                                                  callback(@[ @"TASK_DUMPED", @"{\"success\": false}" ]);
                                                }
                                                animated:YES
    ];
  }];
}


@end
