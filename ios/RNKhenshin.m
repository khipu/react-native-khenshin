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
  __typeof(self) __weak welf = self;


  @try {
    [KhenshinInterface initWithBuilderBlock:^(KhenshinBuilder *builder) {
      builder.APIUrl = @"https://khipu.com/app/enc/";
      builder.keepCookies = NO;
      builder.allowCredentialsSaving = YES;
      builder.mainButtonStyle = KHMainButtonFatOnForm;
      builder.hideWebAddressInformationInForm = YES;
      builder.principalColor = [welf principalColor];
      builder.darkerPrincipalColor = [welf principalColor];
      builder.secondaryColor = [welf secondaryColor];
      builder.continueButtonTextTintColor = [UIColor blackColor];
      builder.navigationBarTextTint = [UIColor blackColor];
      builder.barTintColor = [welf barTintColor];
      builder.processHeader = [[[NSBundle mainBundle] loadNibNamed:@"CustomPaymentProcessHeader" owner:self options:nil] objectAtIndex:0];
    }];
  } @catch (NSException *exception) {
    NSLog(@"Khenshin failed to init: %@", [exception reason]);
  }
  [KhenshinInterface setPreferredStatusBarStyle:UIStatusBarStyleLightContent];
  [KhenshinInterface setAutomatonTimeout:90.0];
}

- (UIColor *)principalColor {
  return [UIColor colorWithRed:255 / 255.0
                         green:221 / 255.0
                          blue:0 / 255.0
                         alpha:1.0];
}

- (UIColor *)secondaryColor {
  return [UIColor colorWithRed:183 / 255.0
                         green:36 / 255.0
                          blue:51 / 255.0
                         alpha:1.0];
}

- (UIColor *)barTintColor {
  return [UIColor colorWithRed:249 / 255.0
                         green:249 / 255.0
                          blue:249 / 255.0
                         alpha:1.0];
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
