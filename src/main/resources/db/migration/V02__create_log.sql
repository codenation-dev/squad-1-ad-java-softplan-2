create table log (
	id bigserial not null, 
	created timestamp, 
	description varchar(255), 
	level varchar(255), 
	stacktrace varchar(255), 
	title varchar(255), 
	user_account_id int8, 
	primary key (id)
);
alter table log add constraint fk_log_user foreign key (user_account_id) references user_account;