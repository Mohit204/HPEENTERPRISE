# HPEENTERPRISE
HPEENTERPRISE

DataBase Design :

I am going  with five tables :
 1)Buisness User[User Table]
 2)Comment Table
 3)Order Table
 4)Reply Table
 5)Customer Table
 
 
 Project Package Design :
 
1)endpoints:
   All resources will be in this package

2)bl :
 All the business logic things will be here 
 
3)entities :
  All DB related hibernate beans will be here

4)models:
 All multipurpose java beans will be here 
 
 
 
 Will attach Database design snapshot in email.
 Postman Collection also attached in email.

  Rest End points :
  
  1)Posting a comment:
  http://localhost:8080/HPEENTERPRISE/hpe/post/comment [Post]
   Sample Request Body->
      {
	   	"userId":1,
    	"orderId":1,
	    "text" : "Hardware is done"
	}
  
    Sample Response : 
     CommentId : 3  (Successfull creation of Comment  entity in system)
   
  
  2)Reply to a comment
  http://localhost:8080/HPEENTERPRISE/hpe/reply/comment/ [POST]
  Sample Request Body ->
   {
	 "commentId":1,
	 "userId":1,
	 "text" : "Thanks for hardware update"
	}
  
 
 Sample Response : 
    ReplyId : 4 (Successfull creation of Reply entity in system)
  
  
  3)http://localhost:8080/HPEENTERPRISE/hpe/allCommentsWithReplies/{order_id} [GET]
  
  Sample Response : 
    [
    {
        "comment": {
            "text": "Hardware is done",
            "orderId": 1,
            "userId": 1,
            "commentId": 1
        },
        "replies": [
            {
                "text": "Thanks for hardware update",
                "userId": 1,
                "commentId": 1,
                "replyId": 1
            },
            {
                "text": "Thanks for hardware update",
                "userId": 1,
                "commentId": 1,
                "replyId": 2
            },
            {
                "text": "Thanks for hardware update",
                "userId": 1,
                "commentId": 1,
                "replyId": 3
            }
        ]
    }
]
  
  
  
  DataBase Create Scripts :
  
  CREATE TABLE `comment` (
  `comment_id` bigint(200) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(200) NOT NULL,
  `order_id` bigint(200) NOT NULL,
  `text` varchar(100) NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

CREATE TABLE `customer` (
  `customer_id` bigint(200) NOT NULL,
  `customer_name` varchar(45) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `order` (
  `order_id` bigint(200) NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(200) NOT NULL,
  `order_details` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `reply` (
  `reply_id` bigint(200) NOT NULL AUTO_INCREMENT,
  `comment_id` bigint(200) NOT NULL,
  `user_id` bigint(200) NOT NULL,
  `text` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`reply_id`),
  KEY `comment_id_idx_1` (`comment_id`),
  KEY `user_id_idx_1` (`user_id`),
  CONSTRAINT `comment_id_1` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`comment_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_id_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

CREATE TABLE `user` (
  `user_id` bigint(200) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL,
  `user_details` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
