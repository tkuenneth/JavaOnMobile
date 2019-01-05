//
//  MyViewController.h
//  Hello
//
//  Created by Thomas Künneth on 05.01.19.
//  Copyright © 2019 Thomas Kuenneth. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface MyViewController : UIViewController
@property (weak, nonatomic) IBOutlet UILabel *label;
- (IBAction)button:(UIButton *)sender;

@end

NS_ASSUME_NONNULL_END
