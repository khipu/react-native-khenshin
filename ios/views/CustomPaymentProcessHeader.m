//
//  KHPaymentProcessHeader.m
//  Khipu
//
//  Created by Iván Galaz-Jeria on 11/11/16.
//  Copyright © 2016 Khipu. All rights reserved.
//

#import "CustomPaymentProcessHeader.h"
#import "UIImageView+AFNetworking.h"

@interface CustomPaymentProcessHeader()

@property (weak, nonatomic) IBOutlet UIImageView *merchantImageView;
@property (weak, nonatomic) IBOutlet UILabel *merchantName;
@property (weak, nonatomic) IBOutlet UILabel *subject;
@property (weak, nonatomic) IBOutlet UILabel *amount;

@end

@implementation CustomPaymentProcessHeader

- (void) configureWithSubject:(NSString*) subject
    formattedAmountAsCurrency:(NSString*) amount
                 merchantName:(NSString*) merchantName
             merchantImageURL:(NSString*) merchantImageURL
                paymentMethod:(NSString *) paymentMethod {
    [self downloadMerchantImageWithMerchantImageURL:merchantImageURL];
    [self.merchantName setText:merchantName];
    [self.subject setText:subject];
    [self.amount setText:amount];

}

- (void)downloadMerchantImageWithMerchantImageURL:(NSString*) pictureURL {


    NSURLRequest *merchantPictureRequest = [NSURLRequest requestWithURL:[NSURL URLWithString:pictureURL]
                                                            cachePolicy:NSURLRequestReturnCacheDataElseLoad
                                                        timeoutInterval:90];

    [self.merchantImageView setImageWithURLRequest:merchantPictureRequest
                                  placeholderImage:[UIImage imageNamed:@"Merchant Stand In"]
                                           success:^(NSURLRequest * _Nonnull request, NSHTTPURLResponse * _Nullable response, UIImage * _Nonnull image) {
#pragma unused(request)
#pragma unused(response)
                                               [self.merchantImageView setImage:image];
                                           } failure:^(NSURLRequest * _Nonnull request, NSHTTPURLResponse * _Nullable response, NSError * _Nonnull error) {
#pragma unused(error)
#pragma unused(request)
#pragma unused(response)
                                           }
     ];
}
@end
