INSERT INTO `orders` (`order_id`, `username`, `price`, `date_order`) VALUES
	(1, 'test1', 13.20, '2022-01-13 10:51:57'),
	(2, 'test3', 4.40, '2022-01-13 10:51:57');

INSERT INTO `pizza` (`pizza_id`, `order_id`, `size`, `topping1`, `price`) VALUES
	(1, 1, 'big', 'salami', 6.60),
	(2, 1, 'big', 'paprica', 6.60),
	(3, 2, 'small', 'ham', 4.40);