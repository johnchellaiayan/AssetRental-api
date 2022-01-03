# AssetRentalMgmt-api
 AssetManagement

### Database creation
create database assetmgmt;

use assetmgmt;

 CREATE TABLE `audit_data` (
  `id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `login_id` int DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `tag_id` varchar(1) DEFAULT NULL,
  `audit_timestamp` varchar(19) DEFAULT NULL,
  `operation` varchar(1) DEFAULT NULL,
  `application_screen` varchar(1) DEFAULT NULL,
  `application` varchar(1) DEFAULT NULL,
  `host` varchar(1) DEFAULT NULL,
  `message` varchar(20) DEFAULT NULL,
  `component_name` varchar(1) DEFAULT NULL,
  `table_name` varchar(1) DEFAULT NULL,
  `stacktrace` varchar(1) DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `created_at` varchar(19) DEFAULT NULL,
  `additional_info` varchar(1) DEFAULT NULL,
  `input_json` varchar(1) DEFAULT NULL,
  `url` varchar(1) DEFAULT NULL,
  `http_method` varchar(1) DEFAULT NULL
) ENGINE=InnoDB'


### Run below command after installing maven

mvn spring-boot:run

### Application will run on 27070 and check in browser
 http://localhost:27070/swagger-ui.html
