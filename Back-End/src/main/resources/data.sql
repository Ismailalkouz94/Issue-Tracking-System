
REPLACE INTO `postion` (`pos_id`, `descption`, `pos_key`) VALUES
(1, 'Developer', 'DEV'),
(2, 'Quality', 'QA'),
(3, 'Project Manger', 'PM');

REPLACE INTO `role` (`role_id`, `name`) VALUES
(1, 'ADMIN'),
(2, 'USER');

REPLACE INTO `status` (`status_id`, `name`) VALUES
(1, 'CREATED'),
(2, 'REOPEN'),
(3, 'RESOLVE'),
(4, 'CLOSED');

REPLACE INTO `type` (`type_id`, `name`) VALUES
(1, 'TASK'),
(2, 'ISSUE');


REPLACE INTO `user` (`user_id`, `aciive`, `email`, `fist_name`, `last_name`, `password`, `user_name`, `pos_id`, `role_id`) VALUES
(1, 1, 'admin@gmail.com', 'Admin', 'Admin', '$2a$10$zswmPMTPQx/haoyigz5z0uGCG37QjBHurv7LRMuEUnTUfVLgUXSu6', 'admin', 3, 1);
