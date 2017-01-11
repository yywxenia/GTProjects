-- Users --
INSERT INTO `user` (`username`, `name`, `password`) VALUES ('mhess', 'Mark Hess', 'password');
INSERT INTO `user` (`username`,`name`,`password`) VALUES ('rnewton','Robert Newton', 'password');
INSERT INTO `user` (`username`,`name`,`password`) VALUES ('yyan','Yiwei Yan', 'password');
INSERT INTO `user` (`username`,`name`,`password`) VALUES ('zhooks','Zachary Hooks', 'password');

-- User Subclasses --
INSERT INTO `municipality` (`username`,`population_size`) VALUES ('zhooks',1500000);
INSERT INTO `individual` (`username`,`job_title`,`hired_date`) VALUES ('mhess','Programmer','2016-11-02 00:00:00');
INSERT INTO `government_agency` (`username`,`jurisdiction`) VALUES ('rnewton','Local');
INSERT INTO `company` (`username`,`headquarters`) VALUES ('yyan','Atlanta');

-- Resources --
INSERT INTO `resource` (`resource_id`,`cost_time_period_id`,`username`,`name`,`model`,`latitude`,`longitude`,`amount`,`primary_esf_id`) VALUES ('2147483641',1,'rnewton','Truck','Toyota',-14,15,15,1);
INSERT INTO `resource` (`resource_id`,`cost_time_period_id`,`username`,`name`,`model`,`latitude`,`longitude`,`amount`,`primary_esf_id`) VALUES ('2147483642',3,'rnewton','Thing','Fun',10,10,68,5);
INSERT INTO `resource` (`resource_id`,`cost_time_period_id`,`username`,`name`,`model`,`latitude`,`longitude`,`amount`,`primary_esf_id`) VALUES ('2147483643',2,'mhess','Truck','Hummer',11,10,1000,1);
INSERT INTO `resource` (`resource_id`,`cost_time_period_id`,`username`,`name`,`model`,`latitude`,`longitude`,`amount`,`primary_esf_id`) VALUES ('2147483644',2,'mhess','Car','Ferrari',11,11,2000,1);

-- Incidents --
INSERT INTO `incident` (`incident_id`,`username`,`description`,`latitude`,`longitude`,`incident_date`) VALUES (2147483647,'rnewton','Hurricane',15,15,'2016-11-02 00:00:00');
INSERT INTO `incident` (`incident_id`,`username`,`description`,`latitude`,`longitude`,`incident_date`) VALUES (2147483646,'mhess','Flood',19,21,'2000-11-02 00:00:00');
INSERT INTO `incident` (`incident_id`,`username`,`description`,`latitude`,`longitude`,`incident_date`) VALUES (2147483645,'mhess','Election',1,1,'1994-11-02 00:00:00');

-- Resource Requests --
INSERT INTO `resource_request` (`resource_request_status_id`, `incident_id`, `resource_id`) VALUES (1, 2147483647, '2147483641');
INSERT INTO `resource_request` (`resource_request_status_id`, `incident_id`, `resource_id`, `start_date`, `return_by_date`) VALUES (2, 2147483647, '2147483642', CURRENT_DATE(), CURRENT_DATE()  + 5);
INSERT INTO `resource_request` (`resource_request_status_id`, `incident_id`, `resource_id`, `start_date`, `return_by_date`) VALUES (3, 2147483647, '2147483643', CURRENT_DATE() - 5, CURRENT_DATE() + 10);
INSERT INTO `resource_request` (`resource_request_status_id`, `incident_id`, `resource_id`, `start_date`, `return_by_date`) VALUES (4, 2147483647, '2147483644', CURRENT_DATE() - 15, CURRENT_DATE() + 5);


-- Resource ESF --
INSERT INTO `resource_esf` (`resource_id`,`esf_id`) VALUES ('2147483641',1);
